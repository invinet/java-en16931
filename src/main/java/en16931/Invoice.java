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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

/**
 * Class to represent an Invoice in EN16931 format.
 * 
 * <p>The <a href="http://docs.peppol.eu/poacc/billing/3.0/bis/">EN16931 format</a>
 * is an <a href="https://standards.cen.eu/dyn/www/f?p=204:110:0::::FSP_PROJECT:60602&cs=1B61B766636F9FB34B7DBD72CE9026C72">European Standard</a>
 * that establishes a semantic data model of the core elements of an electronic
 * invoice. The semantic model includes only the essential information elements
 * that an electronic invoice needs to ensure legal (including fiscal)
 * compliance and to enable interoperability for cross-border, cross
 * sector and for domestic trade.
 * 
 */
public class Invoice {
    
    private static final Set<String> PAYMENT_CODES = new HashSet<String>(Arrays.asList(
        new String[] {"10", "49", "31", "26", "23", "48", "ZZZ"}
    ));

    private String invoiceId;
    private CurrencyUnit currency;
    private Date issueDate;
    private Date dueDate;
    private Entity sellerParty;
    private Entity buyerParty;
    private String paymentMeansCode;
    private Double chargePercent;
    private MonetaryAmount chargeAmount;
    private Double discountPercent;
    private MonetaryAmount discountAmount;
    private MonetaryAmount _lineExtensionAmount;
    private MonetaryAmount _taxExclusiveAmount;
    private MonetaryAmount _taxInclusiveAmount;
    private MonetaryAmount _payableAmount;
    private String originalXml;
    private final boolean importedFromXml;
    private final ArrayList<InvoiceLine> lines;

    /**
     *
     * @param invoiceId the invoice identification
     * @param currency the currency in 3-letter ISO-4217
     * @param importedFromXml whether the invoice was imported from an XML file 
     */
    public Invoice(String invoiceId, String currency, boolean importedFromXml) {
        this.lines = new ArrayList<>();
        this.invoiceId = invoiceId;
        this.currency = Monetary.getCurrency(currency);
        this.importedFromXml = importedFromXml;
    }

    /**
     * Returns the original XML if the Invoice was created from
     * an XML file.
     *
     * @return the original XML file.
     */
    public String getOriginalXml() {
        return originalXml;
    }

    /**
     * Sets the original XML file.
     *
     * @param originalXml the original XML file as a String.
     */
    public void setOriginalXml(String originalXml) {
        this.originalXml = originalXml;
    }

    /**
     * Returns the XML representation in EN16931 format
     * of the invoice.
     * 
     *
     * @return a string with the XML in EN16931 format.
     */
    public String toXml() {
        if (importedFromXml) {
            return originalXml;
        } else {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/invoice.xml");
            JtwigModel model = JtwigModel.newModel().with("invoice", this);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            template.render(model, baos);
            return baos.toString();
        }
    }

    /**
     * Saves the XML representation of the Invoice to a file.
     *
     * @param path string with a valid path
     */
    public void save(String path) {
        // Is there a better way?
        File file = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            try {
                fileWriter.write(this.toXml());
            } catch (IOException e) {
                System.out.println("Exception at writing");
            } finally {
                fileWriter.close();
            }
        } catch (IOException ex) {
            System.out.println("Exception at opening file");
        }
    }

    /**
     * Returns the invoice identifier.
     *
     * @return the invoice ID.
     */
    public String getInvoiceId() {
        return invoiceId;
    }

    /**
     * Sets the invoice identifier.
     *
     * @param invoiceId the invoice identification
     */
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * Returns the currency of the invoice.
     *
     * @return the currency of the invoice
     */
    public CurrencyUnit getCurrency() {
        return currency;
    }

    /**
     * Sets the currency of the invoice.
     *
     * @param currency a three character string representing
     * the invoice in ISO-4217
     */
    public void setCurrency(String currency) {
        this.currency = Monetary.getCurrency(currency);
    }

    /**
     * Returns the issued date of the invoice.
     *
     * @return the issued date
     */
    public Date getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the issue date of the invoice.
     *
     * @param issueDate the issue date
     */
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Return the due date of the invoice.
     *
     * @return date the due date of the invoice
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the invoice
     *
     * @param dueDate the due date
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns a string representation of a date
     * in ISO-8601 format.
     *
     * @param date the date to be converted
     * @return string the fate in ISO-8601 format.
     */
    public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * Returns the entity playing the role of
     * seller party in the transaction represented
     * by the invoice.
     *
     * @return Entity the seller party
     */
    public Entity getSellerParty() {
        return sellerParty;
    }

    /**
     * Sets the the entity playing the role of
     * seller party in the transaction represented
     * by the invoice.
     *
     * @param sellerParty an Entity
     */
    public void setSellerParty(Entity sellerParty) {
        this.sellerParty = sellerParty;
    }

    /**
     * Returns the entity playing the role of
     * seller party in the transaction represented
     * by the invoice.
     * 
     * @return Entity the buyer party
     */
    public Entity getBuyerParty() {
        return buyerParty;
    }

    /**
     * Sets the entity playing the role of
     * seller party in the transaction represented
     * by the invoice.
     * 
     * @param buyerParty an Entity
     */
    public void setBuyerParty(Entity buyerParty) {
        this.buyerParty = buyerParty;
    }

    /**
     * Returns the payment means code for the transaction
     * represented by the invoice.
     *
     * @return string the payment means code
     */
    public String getPaymentMeansCode() {
        return paymentMeansCode;
    }

    /**
     * Sets the payment means code for the transaction represented by
     * the invoice.
     * 
     * <p>It must be one of:
     * <ul>
     * <li>"10": cash</li>
     * <li>"49": debit</li>
     * <li>"31": transfer</li>
     * <li>"26": cheque</li>
     * <li>"23": cheque_b</li>
     * <li>"48": credit</li>
     * <li>"ZZZ": awarding, reposition, special</li>
     * </ul>
     * 
     * @param paymentMeansCode the code
     */
    public void setPaymentMeansCode(String paymentMeansCode) {
        if (PAYMENT_CODES.contains(paymentMeansCode)) {
            this.paymentMeansCode = paymentMeansCode;
        } else {
            throw new IllegalArgumentException("paymentMeansCode must be one of: 10, 49, 31, 26, 23, 48, ZZZ");
        }
    }

    /**
     * Returns an ArrayList with the invoice lines
     *
     * @return an array with a list of the invoice lines
     */
    public ArrayList<InvoiceLine> getLines() {
        return lines;
    }

    /**
     * Adds an invoice line to the invoice
     *
     * @param line an InvoiceLine
     */
    public void addLine(InvoiceLine line) {
        this.lines.add(line);
    }

    /**
     * Returns the Charge percent of the invoice
     *
     * @return charge percent
     */
    public double getChargePercent() {
        return chargePercent;
    }

    /**
     * Returns a suitabñle string representation of the
     * Charge percent of the invoice.
     *
     * @return string representation of the charge percent.
     */
    public String getStringChargePercent() {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        DecimalFormat formatter = (DecimalFormat)nf;
        formatter.applyPattern("#0.00");
        return formatter.format(chargePercent * 100);
    }

    /**
     * Sets the Cherge percentage
     *
     * @param percent a percentage value
     */
    public void setChargePercent(double percent) {
        if (percent > 1 || percent < -1) {
            this.chargePercent = percent / 100;
        } else {
            this.chargePercent = percent;
        }
        this.chargeAmount = this.grossSubtotal(null)
                                .multiply(this.chargePercent)
                                .with(Monetary.getDefaultRounding());
    }

    /**
     * Returns the Charge amount of the invoice.
     *
     * @return the charge amount
     */
    public MonetaryAmount getChargeAmount() {
        if (chargeAmount == null) {
            return Money.of(0, this.currency);
        } else {
            return chargeAmount;
        }
    }

    /**
     * Returns true if the invoice has a charge.
     *
     * @return boolean
     */
    public boolean isChargeSet() {
        return chargeAmount != null;
    }

    /**
     * Sets the charge amount of the invoice.
     *
     * @param chargeAmount the charge amount
     */
    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = Money.of(chargeAmount, this.currency);
        BigDecimal gross = this.grossSubtotal(null).getNumber().numberValue(BigDecimal.class);
        BigDecimal amount = this.chargeAmount.getNumber().numberValue(BigDecimal.class);
        this.chargePercent = amount.divide(gross, 2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Returns the Charge base amount of the invoice.
     *
     * @return charge base amount
     */
    public String getChargeBaseAmount () {
        if (this.chargeAmount != null && this.chargePercent != null) {
            NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
            DecimalFormat formatter = (DecimalFormat)nf;
            formatter.applyPattern("####0.00");
            double amount = this.chargeAmount.getNumber().numberValue(BigDecimal.class).doubleValue();
            return formatter.format(amount / this.chargePercent);
        } else {
            return "0";
        }
    }

    /**
     * Returns the discount percentage of the invoice.
     *
     * @return dicount percentage.
     */
    public double getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Returns a suitable string representation of the discount
     * percentage of the invoice.
     *
     * @return string the discpount percentage
     */
    public String getStringDiscountPercent() {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        DecimalFormat formatter = (DecimalFormat)nf;
        formatter.applyPattern("#0.00");
        return formatter.format(discountPercent * 100);
    }

    /**
     * Sets the dicount percentage of the invoice.
     *
     * @param percent the discount percentage
     */
    public void setDiscountPercent(double percent) {
        if (percent > 1 || percent < -1) {
            this.discountPercent = percent / 100;
        } else {
            this.discountPercent = percent;
        }
        this.discountAmount = this.grossSubtotal(null)
                                .multiply(this.discountPercent)
                                .with(Monetary.getDefaultRounding());
    }

    /**
     * Returns the discount amount of the invoice.
     *
     * @return discount amount
     */
    public MonetaryAmount getDiscountAmount() {
        if (discountAmount == null) {
            return Money.of(0, this.currency);
        } else {
            return discountAmount;
        }
    }

    /**
     * Returns true if the invoice has a discount sent.
     *
     * @return boolean
     */
    public boolean isDiscountSet() {
        return discountAmount != null;
    }

    /**
     * Sets the discount amount of the invoice.
     *
     * @param discountAmount the discount amount
     */
    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = Money.of(discountAmount, this.currency);
        BigDecimal gross = this.grossSubtotal(null).getNumber().numberValue(BigDecimal.class);
        BigDecimal amount = this.discountAmount.getNumber().numberValue(BigDecimal.class);
        this.discountPercent = amount.divide(gross, 2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Returns the discount base amount of the invoice.
     *
     * @return string the discount base amount.
     */
    public String getDiscountBaseAmount () {
        if (this.discountAmount != null && this.discountPercent != null) {
            NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
            DecimalFormat formatter = (DecimalFormat)nf;
            formatter.applyPattern("####0.00");
            double amount = this.discountAmount.getNumber().numberValue(BigDecimal.class).doubleValue();
            return formatter.format(amount / this.discountPercent);
        } else {
            return "0";
        }
    }

    /**
     * Returns an ArrayList with all InvoiceLines that have the
     * tax passed as argument.
     * 
     * <p>If the tax is null, all lines are returned
     *
     * @param tax a Tax
     * @return list with all lines with a tax
     */
    public ArrayList<InvoiceLine> linesWithTaxes(Tax tax) {
        if (tax == null) {
            return this.lines;
        } else {
            ArrayList<InvoiceLine> relevantLines = new ArrayList<>();
            for (InvoiceLine line: this.lines) {
                if (line.hasTax(tax)) {
                    relevantLines.add(line);
                }
            }
            return relevantLines;
        }
    }

    /**
     * Returns the gross subtotal of the invoice.
     * 
     * <p> if a Tax is passed as argument, the gross subtotal
     * for all lines with that Tax.
     *
     * @param tax a Tax
     * @return the gross subtotal of the invoice.
     */
    public MonetaryAmount grossSubtotal(Tax tax) {
        MonetaryAmount amount = Money.of(0, this.currency);
        for (InvoiceLine line: this.linesWithTaxes(tax)) {
            amount = amount.add(line.getLineExtensionAmount())
                        .with(Monetary.getDefaultRounding());
        }
        return amount;
    }

    /**
     * Returns the taxable base of the invoice.
     * 
     * <p> if a Tax is passed as argument, returns the
     * taxable base for all lines with that tax.
     *
     * @param tax a Tax
     * @return taxable base
     */
    public MonetaryAmount taxableBase(Tax tax) {
        MonetaryAmount discount = this.getDiscountAmount();
        MonetaryAmount charge = this.getChargeAmount();
        return this.grossSubtotal(tax)
                .subtract(discount)
                .add(charge)
                .with(Monetary.getDefaultRounding());
    }

    /**
     * Returns a HashSet with all taxes in the invoice.
     *
     * @return a set of unique taxes.
     */
    public ArrayList<Tax> uniqueTaxes() {
        ArrayList<Tax> taxes = new ArrayList<>();
        for (InvoiceLine line: this.lines) {
            if (!taxes.contains(line.getTax())) {
                taxes.add(line.getTax());
            }
        }
        return taxes;
    }

    /**
     * Returns the monetary amount of the invoice.
     * 
     * <p> if a Tax is passed as argument, the monetary amount
     * for all lines with that Tax
     *
     * @param tax a Tax
     * @return monetary amount
     */
    public MonetaryAmount taxAmount(Tax tax) {
        ArrayList<Tax> taxes = new ArrayList<>();
        if (tax == null) {
            for (Tax tx: this.uniqueTaxes()) {
                taxes.add(tx);
            }
        } else {
            taxes.add(tax);
        }
        MonetaryAmount amount = Money.of(0, this.currency);
        for (Tax t: taxes) {
            amount = amount.add(this.taxableBase(t)
                        .multiply(t.getPercent()))
                        .with(Monetary.getDefaultRounding());
        }
        return amount;
    }

    /**
     * Returns the subtotal of the invoice.
     * 
     * <p> if a Tax is passed as argument, the subtotal
     * for all lines with that Tax.
     *
     * @param tax a Tax
     * @return subtotal
     */
    public MonetaryAmount subtotal(Tax tax) {
        MonetaryAmount discount = this.getDiscountAmount();
        MonetaryAmount charge = this.getChargeAmount();
        return this.grossSubtotal(tax)
                .subtract(discount)
                .add(charge)
                .with(Monetary.getDefaultRounding());
    }

    /**
     * Returns the total for the invoice
     *
     * @return total
     */
    public MonetaryAmount total() {
        return this.subtotal(null)
                .add(this.taxAmount(null))
                .with(Monetary.getDefaultRounding());
    }

    /**
     * Returns a suitable string representation of
     * a monetary amount.
     *
     * @param amount the amount of money to be converted
     * @return a string representation of a monetary amount
     */
    public String moneyToString(MonetaryAmount amount) {
        //MonetaryAmountFormat formatter = MonetaryFormats
        //        .getAmountFormat(AmountFormatQueryBuilder.of(Locale.GERMANY).set(CurrencyStyle.SYMBOL).build());
        // Argh! Could not find a way to print MonetaryAmount without currency!
        // This doesn't feel right ... there must be a better way!
        // Use US locale as we need dot as decimal point!
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setCurrencySymbol("");
        formatter.setDecimalFormatSymbols(symbols);
        return formatter.format(amount.getNumber().numberValue(BigDecimal.class));
    }

    /**
     * Returns the line extension amount of the invoice.
     * 
     * <p>If the Invoice object was imported from an XML this quantity is
     * not computed but extracted directly from the original XML.
     *
     * @return line extension amount
     */
    public String getLineExtensionAmount() {
        if (this.importedFromXml) {
            return this.moneyToString(_lineExtensionAmount);
        } else {
            return this.moneyToString(this.grossSubtotal(null));
        }
    }

    /**
     * Sets the line extension amount of the invoice.
     *
     * @param lineExtensionAmount an amount of money
     */
    public void setLineExtensionAmount(String lineExtensionAmount) {
        this._lineExtensionAmount = Money.of(new BigDecimal(lineExtensionAmount), this.currency);
    }

    /**
     * Returns the tax exclusive amount of the invoice.
     *
     * <p>If the Invoice object was imported from an XML this quantity is
     * not computed but extracted directly from the original XML.
     * 
     * @return tax exclusive amount
     */
    public String getTaxExclusiveAmount() {
        if (this.importedFromXml) {
            return this.moneyToString(_taxExclusiveAmount);
        } else {
            return this.moneyToString(this.subtotal(null));
        }
    }

    /**
     * Sets the tax exclusive amount of the invoice.
     *
     * @param taxExclusiveAmount an amount of money
     */
    public void setTaxExclusiveAmount(String taxExclusiveAmount) {
        this._taxExclusiveAmount = Money.of(new BigDecimal(taxExclusiveAmount), this.currency);
    }

    /**
     * Returns the tax inclusive amount of the invoice.
     * 
     * <p>If the Invoice object was imported from an XML this quantity is
     * not computed but extracted directly from the original XML.
     *
     * @return tax inclusive amount
     */
    public String getTaxInclusiveAmount() {
        if (this.importedFromXml) {
            return this.moneyToString(_taxInclusiveAmount);
        } else {
            return this.moneyToString(this.total());
        }
    }

    /**
     * Sets the tax inclusive amount of the invoice.
     *
     * @param taxInclusiveAmount an amount of money
     */
    public void setTaxInclusiveAmount(String taxInclusiveAmount) {
        this._taxInclusiveAmount = Money.of(new BigDecimal(taxInclusiveAmount), this.currency);
    }

    /**
     * Returns the payable amount of the invoice.
     * 
     * <p>If the Invoice object was imported from an XML this quantity is
     * not computed but extracted directly from the original XML.
     *
     * @return payable amount
     */
    public String getPayableAmount() {
        if (this.importedFromXml) {
            return this.moneyToString(_payableAmount);
        } else {
            MonetaryAmount prepaidAmount = Money.of(0, this.currency); // TODO
            return this.moneyToString(this.total().subtract(prepaidAmount));
        }
    }

    /**
     * Sets the payable amount of the invoice.
     *
     * @param payableAmount an amount of money
     */
    public void setPayableAmount(String payableAmount) {
        this._payableAmount = Money.of(new BigDecimal(payableAmount), this.currency);
    }
}
