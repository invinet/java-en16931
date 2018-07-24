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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import org.javamoney.moneta.Money;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.xml.sax.SAXException;

/**
 *
 * @author jtorrents
 */
public class XmlParserTest {
    
    private final XmlParser instance;
    
    public XmlParserTest() throws SAXException, IOException, ParserConfigurationException {
        this.instance = new XmlParser("src/test/resources/files/invoice_with_charges.xml");
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getInvoiceID method, of class XmlParser.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetInvoiceID() throws Exception {
        System.out.println("getInvoiceID");
        String expResult = "1";
        String result = instance.getInvoiceID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrency method, of class XmlParser.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurrency() throws Exception {
        System.out.println("getCurrency");
        String expResult = "EUR";
        String result = instance.getCurrency();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIssueDate method, of class XmlParser.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetIssueDate() throws Exception {
        System.out.println("getIssueDate");
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date expResult = parser.parse("2018-07-19");
        Date result = instance.getIssueDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDueDate method, of class XmlParser.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetDueDate() throws Exception {
        System.out.println("getDueDate");
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date expResult = parser.parse("2018-07-19");
        Date result = instance.getDueDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLineExtensionAmount method, of class XmlParser.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetLineExtensionAmount() throws Exception {
        System.out.println("getLineExtensionAmount");
        String expResult = "73.80";
        String result = instance.getLineExtensionAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTaxExclusiveAmount method, of class XmlParser.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTaxExclusiveAmount() throws Exception {
        System.out.println("getTaxExclusiveAmount");
        String expResult = "70.11";
        String result = instance.getTaxExclusiveAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTaxInclusiveAmount method, of class XmlParser.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTaxInclusiveAmount() throws Exception {
        System.out.println("getTaxInclusiveAmount");
        String expResult = "84.83";
        String result = instance.getTaxInclusiveAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPayableAmount method, of class XmlParser.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPayableAmount() throws Exception {
        System.out.println("getPayableAmount");
        String expResult = "84.83";
        String result = instance.getPayableAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSellerParty method, of class XmlParser.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetSellerParty() throws Exception {
        System.out.println("getSellerParty");
        Entity expResult = new Entity("Acme Inc.", "VAT", "ES34626691F", "ES34626691F",
                "Acme Inc.", "acme@acme.io", "ES34626691F", "ES:VAT");
        expResult.setPostalAddress(new PostalAddress("easy street", "08080", "Barcelona", "ES"));
        Entity result = instance.getSellerParty();
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getTaxScheme(), result.getTaxScheme());
        assertEquals(expResult.getTaxId(), result.getTaxId());
        assertEquals(expResult.getPartyLegalEntityId(), result.getPartyLegalEntityId());
        assertEquals(expResult.getRegistrationName(), result.getRegistrationName());
        assertEquals(expResult.getEndpoint(), result.getEndpoint());
        assertEquals(expResult.getEndpointScheme(), result.getEndpointScheme());
        assertEquals(expResult.getMail(), result.getMail());
        assertEquals(expResult.getPostalAddress().getCountry(), result.getPostalAddress().getCountry());
        assertEquals(expResult.getPostalAddress().getCity(), result.getPostalAddress().getCity());
        assertEquals(expResult.getPostalAddress().getAddress(), result.getPostalAddress().getAddress());
        assertEquals(expResult.getPostalAddress().getPostalZone(), result.getPostalAddress().getPostalZone());
    }

    /**
     * Test of getBuyerParty method, of class XmlParser.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetBuyerParty() throws Exception {
        System.out.println("getBuyerParty");
        Entity expResult = new Entity("Corp Inc.", "VAT", "ES76281415Y", "ES76281415Y",
                "Corp Inc.", "corp@corp.io", "ES76281415Y", "ES:VAT");
        expResult.setPostalAddress(new PostalAddress("easy street", "08080", "Barcelona", "ES"));
        Entity result = instance.getBuyerParty();
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getTaxScheme(), result.getTaxScheme());
        assertEquals(expResult.getTaxId(), result.getTaxId());
        assertEquals(expResult.getPartyLegalEntityId(), result.getPartyLegalEntityId());
        assertEquals(expResult.getRegistrationName(), result.getRegistrationName());
        assertEquals(expResult.getEndpoint(), result.getEndpoint());
        assertEquals(expResult.getEndpointScheme(), result.getEndpointScheme());
        assertEquals(expResult.getMail(), result.getMail());
        assertEquals(expResult.getPostalAddress().getCountry(), result.getPostalAddress().getCountry());
        assertEquals(expResult.getPostalAddress().getCity(), result.getPostalAddress().getCity());
        assertEquals(expResult.getPostalAddress().getAddress(), result.getPostalAddress().getAddress());
        assertEquals(expResult.getPostalAddress().getPostalZone(), result.getPostalAddress().getPostalZone());
    }

    /**
     * Test of getLines method, of class XmlParser.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetLines() throws Exception {
        System.out.println("getLines");
        Tax tax = new Tax(0.21, "S", "VAT", "");
        Tax tax1 = new Tax(0.1, "S", "VAT", "");
        InvoiceLine line1 = new InvoiceLine("foo", "EA", 3, 20.1, "EUR", tax);
        InvoiceLine line2 = new InvoiceLine("bar", "EA", 5, 2.7, "EUR", tax);
        ArrayList<InvoiceLine> expResult = new ArrayList<>();
        expResult.add(line1);
        expResult.add(line2);
        ArrayList<InvoiceLine> result = instance.getLines();
        assertEquals(expResult.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            InvoiceLine lr = result.get(i);
            InvoiceLine le = expResult.get(i);
            assertEquals(le.getItemName(), lr.getItemName());
            assertEquals(le.getUnitCode(), lr.getUnitCode());
            assertEquals(le.getPrice(), lr.getPrice());
            assertEquals(le.getQuantity(), lr.getQuantity(), 0.0);
            assertEquals(le.getCurrency(), lr.getCurrency());
            assertEquals(le.getTax(), lr.getTax());
        }
    }

    /**
     * Test of getDiscountAmount method, of class XmlParser.
     */
    @Test
    public void testGetDiscountAmount() throws Exception {
        System.out.println("getDiscountAmount");
        String expResult = "7.38";
        String result = instance.getDiscountAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getChargeAmount method, of class XmlParser.
     */
    @Test
    public void testGetChargeAmount() throws Exception {
        System.out.println("getChargeAmount");
        String expResult = "3.69";
        String result = instance.getChargeAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInvoice method, of class XmlParser.
     */
    @Test
    public void testGetInvoice() throws Exception {
        System.out.println("getInvoice");
        Invoice result = instance.getInvoice();
        // test that readed and computed values are the same
        assertEquals(result.toXml(), instance.originalXml);
        assertEquals(result.grossSubtotal(null), Money.of(new BigDecimal(result.getLineExtensionAmount()), "EUR"));
        assertEquals(result.subtotal(null), Money.of(new BigDecimal(result.getTaxExclusiveAmount()), "EUR"));
        assertEquals(result.total(), Money.of(new BigDecimal(result.getTaxInclusiveAmount()), "EUR"));
        assertEquals(result.total(), Money.of(new BigDecimal(result.getPayableAmount()), "EUR"));
    }

}
