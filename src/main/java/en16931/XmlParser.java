/* 
 * Copyright 2018 Invinet Sistemes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package en16931;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class to parse an XML file in EN16931 format in to an Invoice Instance.
 *
 */
public class XmlParser {

    private final String path;
    private final Document document;
    private final XPath xpath;
    final String originalXml;

    /**
     *
     * @param path a string representing a path to an XML file
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public XmlParser(String path) throws SAXException, IOException, ParserConfigurationException {
        this.path = path;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        this.document = db.parse(this.path);
        XPathFactory xpathFactory = XPathFactory.newInstance();
        this.xpath = xpathFactory.newXPath();
        this.xpath.setNamespaceContext(new NamespaceResolver(this.document));
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        this.originalXml = new String(encoded, Charset.defaultCharset());
    }
    
    /**
     * Returns the invoice identification.
     *
     * @return the invoice identification.
     * @throws XPathExpressionException
     */
    public String getInvoiceID() throws XPathExpressionException {
        // It seems that I cannot use the default xmlns in XPath expressions
        // https://stackoverflow.com/questions/10720452/java-xpath-queries-with-default-namespace-xmlns
        // we have to use an arbitrary string (eg "df") and map it at NamespaceResolver
        //XPathExpression expr = xpath.compile("/xmlns:Invoice/cbc:ID");
        XPathExpression expr = xpath.compile("/df:Invoice/cbc:ID");
        String invoiceID = (String) expr.evaluate(document, XPathConstants.STRING);
        return invoiceID;
    }

    /**
     * Returns the currency code
     *
     * @return the currency code
     * @throws XPathExpressionException
     */
    public String getCurrency() throws XPathExpressionException {
        XPathExpression expr = xpath.compile("/df:Invoice/cbc:DocumentCurrencyCode");
        String Currency = (String) expr.evaluate(document, XPathConstants.STRING);
        return Currency;
    }

    /**
     * Return the issue date for the invocie
     *
     * @return the issue date
     * @throws XPathExpressionException
     * @throws ParseException
     */
    public Date getIssueDate() throws XPathExpressionException, ParseException {
        XPathExpression expr = xpath.compile("/df:Invoice/cbc:IssueDate");
        String date = (String) expr.evaluate(document, XPathConstants.STRING);
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        return parser.parse(date);
    }

    /**
     * Return the due date of the invoice
     *
     * @return the due date
     * @throws XPathExpressionException
     * @throws ParseException
     */
    public Date getDueDate() throws XPathExpressionException, ParseException {
        XPathExpression expr = xpath.compile("/df:Invoice/cbc:DueDate");
        String date = (String) expr.evaluate(document, XPathConstants.STRING);
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        return parser.parse(date);
    }

    /**
     * Returns the line extension amount of the invoice.
     *
     * @return the line extension amount
     * @throws XPathExpressionException
     */
    public String getLineExtensionAmount() throws XPathExpressionException {
        XPathExpression expr = xpath.compile("/df:Invoice/cac:LegalMonetaryTotal/cbc:LineExtensionAmount");
        String lineExtensionAmount = (String) expr.evaluate(document, XPathConstants.STRING);
        return lineExtensionAmount;
    }

    /**
     * Returns the tax exclusive amount of the invoice
     *
     * @return the tax exclusive amount
     * @throws XPathExpressionException
     */
    public String getTaxExclusiveAmount() throws XPathExpressionException {
        XPathExpression expr = xpath.compile("/df:Invoice/cac:LegalMonetaryTotal/cbc:TaxExclusiveAmount");
        String taxExclusiveAmount = (String) expr.evaluate(document, XPathConstants.STRING);
        return taxExclusiveAmount;
    }

    /**
     * Returns the tax inclusive amount of the invoice
     *
     * @return the tax inclusive amount
     * @throws XPathExpressionException
     */
    public String getTaxInclusiveAmount() throws XPathExpressionException {
        XPathExpression expr = xpath.compile("/df:Invoice/cac:LegalMonetaryTotal/cbc:TaxInclusiveAmount");
        String taxInclusiveAmount = (String) expr.evaluate(document, XPathConstants.STRING);
        return taxInclusiveAmount;
    }

    /**
     * Returns the payable amount of the invoice
     *
     * @return the payable amount
     * @throws XPathExpressionException
     */
    public String getPayableAmount() throws XPathExpressionException {
        XPathExpression expr = xpath.compile("/df:Invoice/cac:LegalMonetaryTotal/cbc:PayableAmount");
        String payableAmount = (String) expr.evaluate(document, XPathConstants.STRING);
        return payableAmount;
    }
    
    /**
     * Returns the Entity that plays the role of Seller party.
     *
     * @return an Entity
     * @throws XPathExpressionException
     */
    public Entity getSellerParty() throws XPathExpressionException {
        XPathExpression exprPartyLegalEntity = xpath.compile("/df:Invoice/cac:AccountingSupplierParty/cac:Party/cac:PartyLegalEntity/cbc:CompanyID");
        String partyLegalEntity = (String) exprPartyLegalEntity.evaluate(document, XPathConstants.STRING);
        XPathExpression exprName = xpath.compile("/df:Invoice/cac:AccountingSupplierParty/cac:Party/cac:PartyName/cbc:Name");
        String name = (String) exprName.evaluate(document, XPathConstants.STRING);
        XPathExpression exprRegistrationName = xpath.compile("/df:Invoice/cac:AccountingSupplierParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName");
        String registrationName = (String) exprRegistrationName.evaluate(document, XPathConstants.STRING);
        XPathExpression exprTaxSchemeId = xpath.compile("/df:Invoice/cac:AccountingSupplierParty/cac:Party/cac:PartyTaxScheme/cbc:CompanyID");
        String taxSchemeId = (String) exprTaxSchemeId.evaluate(document, XPathConstants.STRING);
        XPathExpression exprTaxScheme = xpath.compile("/df:Invoice/cac:AccountingSupplierParty/cac:Party/cac:PartyTaxScheme/cac:TaxScheme/cbc:ID");
        String taxScheme = (String) exprTaxScheme.evaluate(document, XPathConstants.STRING);
        XPathExpression exprEndpointSchemeID = xpath.compile("/df:Invoice/cac:AccountingSupplierParty/cac:Party/cbc:EndpointID");
        String endpointSchemeID = (String) exprEndpointSchemeID.evaluate(document, XPathConstants.STRING);
        XPathExpression exprEndpointScheme = xpath.compile("/df:Invoice/cac:AccountingSupplierParty/cac:Party/cbc:EndpointID/@schemeID");
        String endpointScheme = (String) exprEndpointScheme.evaluate(document, XPathConstants.STRING);
        XPathExpression exprMail = xpath.compile("/df:Invoice/cac:AccountingSupplierParty/cac:Party/cac:Contact/cbc:ElectronicMail");
        String mail = (String) exprMail.evaluate(document, XPathConstants.STRING);
        Entity entity = new Entity(name, taxScheme, taxSchemeId, partyLegalEntity, registrationName, mail, endpointSchemeID, endpointScheme);
        // PostalAddress
        XPathExpression exprCountry = xpath.compile("/df:Invoice/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode");
        String country = (String) exprCountry.evaluate(document, XPathConstants.STRING);
        XPathExpression exprAddress = xpath.compile("/df:Invoice/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:StreetName");
        String address = (String) exprAddress.evaluate(document, XPathConstants.STRING);
        XPathExpression exprCity = xpath.compile("/df:Invoice/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:CityName");
        String city = (String) exprCity.evaluate(document, XPathConstants.STRING);
        XPathExpression exprPostalZone = xpath.compile("/df:Invoice//cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:PostalZone");
        String postalZone = (String) exprPostalZone.evaluate(document, XPathConstants.STRING);
        PostalAddress postal = new PostalAddress(address, postalZone, city, country);
        entity.setPostalAddress(postal);
        return entity;
    }

    /**
     * Returns an Entity that plays the role of Buyer party
     *
     * @return an Entity
     * @throws XPathExpressionException
     */
    public Entity getBuyerParty() throws XPathExpressionException {
        XPathExpression exprPartyLegalEntity = xpath.compile("/df:Invoice/cac:AccountingCustomerParty/cac:Party/cac:PartyLegalEntity/cbc:CompanyID");
        String partyLegalEntity = (String) exprPartyLegalEntity.evaluate(document, XPathConstants.STRING);
        XPathExpression exprName = xpath.compile("/df:Invoice/cac:AccountingCustomerParty/cac:Party/cac:PartyName/cbc:Name");
        String name = (String) exprName.evaluate(document, XPathConstants.STRING);
        XPathExpression exprRegistrationName = xpath.compile("/df:Invoice/cac:AccountingCustomerParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName");
        String registrationName = (String) exprRegistrationName.evaluate(document, XPathConstants.STRING);
        XPathExpression exprTaxSchemeId = xpath.compile("/df:Invoice/cac:AccountingCustomerParty/cac:Party/cac:PartyTaxScheme/cbc:CompanyID");
        String taxSchemeId = (String) exprTaxSchemeId.evaluate(document, XPathConstants.STRING);
        XPathExpression exprTaxScheme = xpath.compile("/df:Invoice/cac:AccountingCustomerParty/cac:Party/cac:PartyTaxScheme/cac:TaxScheme/cbc:ID");
        String taxScheme = (String) exprTaxScheme.evaluate(document, XPathConstants.STRING);
        XPathExpression exprEndpointSchemeID = xpath.compile("/df:Invoice/cac:AccountingCustomerParty/cac:Party/cbc:EndpointID");
        String endpointSchemeID = (String) exprEndpointSchemeID.evaluate(document, XPathConstants.STRING);
        XPathExpression exprEndpointScheme = xpath.compile("/df:Invoice/cac:AccountingCustomerParty/cac:Party/cbc:EndpointID/@schemeID");
        String endpointScheme = (String) exprEndpointScheme.evaluate(document, XPathConstants.STRING);
        XPathExpression exprMail = xpath.compile("/df:Invoice/cac:AccountingCustomerParty/cac:Party/cac:Contact/cbc:ElectronicMail");
        String mail = (String) exprMail.evaluate(document, XPathConstants.STRING);
        Entity entity = new Entity(name, taxScheme, taxSchemeId, partyLegalEntity, registrationName, mail, endpointSchemeID, endpointScheme);
        // PostalAddress
        XPathExpression exprCountry = xpath.compile("/df:Invoice/cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode");
        String country = (String) exprCountry.evaluate(document, XPathConstants.STRING);
        XPathExpression exprAddress = xpath.compile("/df:Invoice/cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cbc:StreetName");
        String address = (String) exprAddress.evaluate(document, XPathConstants.STRING);
        XPathExpression exprCity = xpath.compile("/df:Invoice/cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cbc:CityName");
        String city = (String) exprCity.evaluate(document, XPathConstants.STRING);
        XPathExpression exprPostalZone = xpath.compile("/df:Invoice//cac:AccountingCustomerParty/cac:Party/cac:PostalAddress/cbc:PostalZone");
        String postalZone = (String) exprPostalZone.evaluate(document, XPathConstants.STRING);
        PostalAddress postal = new PostalAddress(address, postalZone, city, country);
        entity.setPostalAddress(postal);
        return entity;
    }
    
    /**
     * Returns an ArrayList of all the InvoiceLines of the Invoice
     *
     * @return an ArrayList of InvoiceLines
     * @throws XPathExpressionException
     */
    public ArrayList<InvoiceLine> getLines() throws XPathExpressionException {
        ArrayList<InvoiceLine> lines = new ArrayList<>();
        XPathExpression expr = xpath.compile("/df:Invoice/cac:InvoiceLine");
        NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            XPathExpression exprItemName = xpath.compile("cac:Item/cbc:Name");
            String itemName = (String) exprItemName.evaluate(node, XPathConstants.STRING);
            XPathExpression exprUnitCode = xpath.compile("cbc:InvoicedQuantity/@unitCode");
            String unitCode = (String) exprUnitCode.evaluate(node, XPathConstants.STRING);
            XPathExpression exprQuantityStr = xpath.compile("cbc:InvoicedQuantity");
            String quantityStr = (String) exprQuantityStr.evaluate(node, XPathConstants.STRING);
            Double quantity = Double.parseDouble(quantityStr);
            XPathExpression exprPriceStr = xpath.compile("cac:Price/cbc:PriceAmount");
            String priceStr = (String) exprPriceStr.evaluate(node, XPathConstants.STRING);
            Double price = Double.parseDouble(priceStr);
            XPathExpression exprCurrency = xpath.compile("cbc:LineExtensionAmount/@currencyID");
            String currency = (String) exprCurrency.evaluate(node, XPathConstants.STRING);
            InvoiceLine line = new InvoiceLine(itemName, unitCode, quantity, price, currency);
            // Tax
            XPathExpression exprTaxPercent = xpath.compile("cac:Item/cac:ClassifiedTaxCategory/cbc:Percent");
            String taxPercentStr = (String) exprTaxPercent.evaluate(node, XPathConstants.STRING);
            Double taxPercent = Double.parseDouble(taxPercentStr);
            XPathExpression exprTaxCategory = xpath.compile("cac:Item/cac:ClassifiedTaxCategory/cbc:ID");
            String taxCategory = (String) exprTaxCategory.evaluate(node, XPathConstants.STRING);
            XPathExpression exprTaxName = xpath.compile("cac:Item/cac:ClassifiedTaxCategory/cac:TaxScheme/cbc:ID");
            String taxName = (String) exprTaxName.evaluate(node, XPathConstants.STRING);
            Tax tax = new Tax(taxPercent, taxCategory, taxName, "");
            line.setTax(tax);
            lines.add(line);
        }
        return lines;
    }

    /**
     * Returns the discount amount of the invoice
     *
     * @return the discount amount
     * @throws XPathExpressionException
     */
    public String getDiscountAmount() throws XPathExpressionException {
        XPathExpression expr = xpath.compile("/df:Invoice/cac:AllowanceCharge");
        NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        String result = null;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            XPathExpression exprIsDiscount = xpath.compile("cbc:ChargeIndicator");
            String isCharge = (String) exprIsDiscount.evaluate(node, XPathConstants.STRING);
            if (isCharge.equals("false")) {
                XPathExpression exprAmount = xpath.compile("cbc:Amount");
                String amount = (String) exprAmount.evaluate(node, XPathConstants.STRING);
                result = amount;
            }
        }
        return result;
    }

    /**
     * Returns the charge amount of the invoice
     *
     * @return the charge amount
     * @throws XPathExpressionException
     */
    public String getChargeAmount() throws XPathExpressionException {
        XPathExpression expr = xpath.compile("/df:Invoice/cac:AllowanceCharge");
        NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        String result = null;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            XPathExpression exprIsDiscount = xpath.compile("cbc:ChargeIndicator");
            String isCharge = (String) exprIsDiscount.evaluate(node, XPathConstants.STRING);
            if (isCharge.equals("true")) {
                XPathExpression exprAmount = xpath.compile("cbc:Amount");
                String amount = (String) exprAmount.evaluate(node, XPathConstants.STRING);
                result = amount;
            }
        }
        return result;
    }
    
    /**
     * Returns an Invoice instance representing the XML file in EN16931 format.
     *
     * @return An Invoice
     * @throws XPathExpressionException
     * @throws ParseException
     */
    public Invoice getInvoice() throws XPathExpressionException, ParseException {
        String invoiceID = this.getInvoiceID();
        String currency = this.getCurrency();
        Invoice invoice = new Invoice(invoiceID, currency, true);
        invoice.setIssueDate(this.getIssueDate());
        invoice.setDueDate(this.getDueDate());
        // add entities
        invoice.setBuyerParty(this.getBuyerParty());
        invoice.setSellerParty(this.getSellerParty());
        // add lines
        for (InvoiceLine line: this.getLines()) {
            invoice.addLine(line);
        }
        // totals
        invoice.setLineExtensionAmount(this.getLineExtensionAmount());
        invoice.setTaxExclusiveAmount(this.getTaxExclusiveAmount());
        invoice.setTaxInclusiveAmount(this.getTaxInclusiveAmount());
        invoice.setPayableAmount(this.getPayableAmount());
        // Dicount and Charge
        String discount = this.getDiscountAmount();
        if (discount != null) {
            invoice.setDiscountAmount(Double.parseDouble(discount));
        }
        String charge = this.getChargeAmount();
        if (charge != null) {
            invoice.setChargeAmount(Double.parseDouble(charge));
        }
        invoice.setOriginalXml(originalXml);
        return invoice;
    }
}