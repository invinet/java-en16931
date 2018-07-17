package en16931;

import java.util.ArrayList;
import java.util.Date;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.UnknownCurrencyException;
import org.javamoney.moneta.Money;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author jtorrents
 */
public class InvoiceTest {

    private final Invoice instance;
    private final Entity seller;
    private final Entity buyer;
    private final Tax tax;
    private final Tax tax1;
    private final InvoiceLine line1;
    private final InvoiceLine line2;
    private final InvoiceLine line3;

    public InvoiceTest() {
        this.instance = new Invoice("1", "EUR", false);
        this.seller = new Entity("Acme Inc.", "VAT", "ES34626691F", "ES34626691F",
                "Acme INc.", "acme@acme.io", "ES76281415Y", "ES:VAT");
        this.buyer = new Entity("Corp Inc.", "VAT", "ES76281415Y", "ES76281415Y",
                "Corp INc.", "corp@corp.io", "ES76281415Y", "ES:VAT");
        this.tax = new Tax(0.21, "S", "IVA", "");
        this.tax1 = new Tax(0.1, "AE", "IVA", "");
        this.line1 = new InvoiceLine("foo", 3, 20.1, "EUR", tax);
        this.line2 = new InvoiceLine("bar", 5, 2.7, "EUR", tax);
        this.line3 = new InvoiceLine("bar", 2, 10.3, "EUR", tax1);
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
     * Test of getInvoiceId method, of class Invoice.
     */
    @Test
    public void testGetInvoiceId() {
        System.out.println("getInvoiceId");
        String expResult = "1";
        String result = instance.getInvoiceId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInvoiceId method, of class Invoice.
     */
    @Test
    public void testSetInvoiceId() {
        System.out.println("setInvoiceId");
        String invoiceId = "0001-2018";
        instance.setInvoiceId(invoiceId);
        assertEquals(invoiceId, instance.getInvoiceId());
    }

    /**
     * Test of getCurrency method, of class Invoice.
     */
    @Test
    public void testGetCurrency() {
        System.out.println("getCurrency");
        String expResult = "EUR";
        CurrencyUnit result = instance.getCurrency();
        assertEquals(expResult, result.getCurrencyCode());
    }

    /**
     * Test of setCurrency method, of class Invoice.
     */
    @Test
    public void testSetCurrency() {
        System.out.println("setCurrency");
        String currency = "USD";
        instance.setCurrency(currency);
        assertEquals(currency, instance.getCurrency().getCurrencyCode());
    }

    @Test(expected = UnknownCurrencyException.class)
    public void setCurrencyWithInvalidCurrencyCode() {
        Invoice i = new Invoice("1", "AAA", false);
    }

    
    /**
     * Test of getIssueDate method, of class Invoice.
     */
    @Test
    public void testGetIssueDate() {
        System.out.println("getIssueDate");
        Date date = new Date();
        Date expResult = date;
        instance.setIssueDate(date);
        Date result = instance.getIssueDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIssueDate method, of class Invoice.
     */
    @Test
    public void testSetIssueDate() {
        System.out.println("setIssueDate");
        Date date = new Date();
        Date issueDate = date;
        instance.setIssueDate(issueDate);
        assertEquals(date, instance.getIssueDate());
    }

    /**
     * Test of getDueDate method, of class Invoice.
     */
    @Test
    public void testGetDueDate() {
        System.out.println("getDueDate");
        Date date = new Date();
        Date expResult = date;
        instance.setDueDate(date);
        Date result = instance.getDueDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDueDate method, of class Invoice.
     */
    @Test
    public void testSetDueDate() {
        System.out.println("setDueDate");
        Date date = new Date();
        Date dueDate = date;
        instance.setDueDate(dueDate);
        assertEquals(date, instance.getDueDate());
    }

    /**
     * Test of getSellerParty method, of class Invoice.
     */
    @Test
    public void testGetSellerParty() {
        System.out.println("getSellerParty");
        Entity expResult = seller;
        instance.setSellerParty(expResult);
        Entity result = instance.getSellerParty();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSellerParty method, of class Invoice.
     */
    @Test
    public void testSetSellerParty() {
        System.out.println("setSellerParty");
        Entity sellerParty = seller;
        instance.setSellerParty(sellerParty);
        assertEquals(sellerParty, instance.getSellerParty());
    }

    /**
     * Test of getBuyerParty method, of class Invoice.
     */
    @Test
    public void testGetBuyerParty() {
        System.out.println("getBuyerParty");
        Entity expResult = buyer;
        instance.setBuyerParty(expResult);
        Entity result = instance.getBuyerParty();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBuyerParty method, of class Invoice.
     */
    @Test
    public void testSetBuyerParty() {
        System.out.println("setBuyerParty");
        Entity buyerParty = buyer;
        instance.setBuyerParty(buyerParty);
        assertEquals(buyerParty, instance.getBuyerParty());
    }

    /**
     * Test of getLines method, of class Invoice.
     */
    @Test
    public void testGetLinesEmpty() {
        System.out.println("getLines");
        ArrayList<InvoiceLine> expResult = new ArrayList<>();
        ArrayList<InvoiceLine> result = instance.getLines();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetLines() {
        System.out.println("getLines");
        ArrayList<InvoiceLine> expResult = new ArrayList<>();
        expResult.add(line1);
        instance.addLine(line1);
        ArrayList<InvoiceLine> result = instance.getLines();
        assertEquals(expResult, result);
    }

    /**
     * Test of addLine method, of class Invoice.
     */
    @Test
    public void testAddLine() {
        System.out.println("addLine");
        ArrayList<InvoiceLine> expResult = new ArrayList<>();
        expResult.add(line1);
        expResult.add(line2);
        instance.addLine(line1);
        instance.addLine(line2);
        assertEquals(expResult, instance.getLines());
    }

    /**
     * Test of linesWithTaxes method, of class Invoice.
     */
    @Test
    public void testLinesWithTaxesNull() {
        System.out.println("linesWithTaxesNull");
        ArrayList<InvoiceLine> expResult = new ArrayList<>();
        expResult.add(line1);
        expResult.add(line2);
        expResult.add(line3);
        instance.addLine(line1);
        instance.addLine(line2);
        instance.addLine(line3);
        assertEquals(expResult, instance.linesWithTaxes(null));
    }

    @Test
    public void testLinesWithTaxes() {
        System.out.println("linesWithTaxes");
        ArrayList<InvoiceLine> expResultTax = new ArrayList<>();
        expResultTax.add(line1);
        expResultTax.add(line2);
        ArrayList<InvoiceLine> expResultTax1 = new ArrayList<>();
        expResultTax1.add(line3);
        instance.addLine(line1);
        instance.addLine(line2);
        instance.addLine(line3);
        assertEquals(expResultTax, instance.linesWithTaxes(tax));
        assertEquals(expResultTax1, instance.linesWithTaxes(tax1));
    }

    /**
     * test of grossSubtotal method, of class Invoice
     */
    @Test
    public void testGrossSubtotal() {
        System.out.println("grossSubtotal");
        instance.addLine(line1);
        instance.addLine(line2);
        instance.addLine(line3);
        MonetaryAmount expResultAll = Money.of(94.4, "EUR");
        MonetaryAmount expResultTax = Money.of(73.8, "EUR");
        MonetaryAmount expResultTax1 = Money.of(20.6, "EUR");
        assertEquals(expResultAll, instance.grossSubtotal(null));
        assertEquals(expResultTax, instance.grossSubtotal(tax));
        assertEquals(expResultTax1, instance.grossSubtotal(tax1));
    }

    /**
     * test of taxableBase method, of class Invoice.
     */
    @Test
    public void testTaxableBase() {
        System.out.println("taxableBase");
        instance.addLine(line1);
        instance.addLine(line2);
        instance.addLine(line3);
        MonetaryAmount expResultAll = Money.of(94.4, "EUR");
        MonetaryAmount expResultTax = Money.of(73.8, "EUR");
        MonetaryAmount expResultTax1 = Money.of(20.6, "EUR");
        assertEquals(expResultAll, instance.taxableBase(null));
        assertEquals(expResultTax, instance.taxableBase(tax));
        assertEquals(expResultTax1, instance.taxableBase(tax1));
    }

    /**
     * test of uniqueTaxes method, of class Invoice.
     */
    @Test
    public void testUniqueTaxes() {
        System.out.println("uniqueTaxes");
        ArrayList<Tax> expResult = new ArrayList<>();
        expResult.add(tax);
        expResult.add(tax1);
        instance.addLine(line1);
        instance.addLine(line2);
        instance.addLine(line3);
        assertEquals(expResult, instance.uniqueTaxes());
    }

    /**
     * test of taxAmount method, of class Invoice.
     */
    @Test
    public void testTaxAmount() {
        System.out.println("taxAmount");
        instance.addLine(line1);
        instance.addLine(line2);
        instance.addLine(line3);
        MonetaryAmount expResultAll = Money.of(17.56, "EUR");
        MonetaryAmount expResultTax = Money.of(15.5, "EUR");
        MonetaryAmount expResultTax1 = Money.of(2.06, "EUR");
        assertEquals(expResultAll, instance.taxAmount(null));
        assertEquals(expResultTax, instance.taxAmount(tax));
        assertEquals(expResultTax1, instance.taxAmount(tax1));
    }

    /**
     * test of subtotal method, of class Invoice.
     */
    @Test
    public void testSubtotal() {
        System.out.println("subtotal");
        instance.addLine(line1);
        instance.addLine(line2);
        instance.addLine(line3);
        MonetaryAmount expResultAll = Money.of(94.4, "EUR");
        MonetaryAmount expResultTax = Money.of(73.8, "EUR");
        MonetaryAmount expResultTax1 = Money.of(20.6, "EUR");
        assertEquals(expResultAll, instance.subtotal(null));
        assertEquals(expResultTax, instance.subtotal(tax));
        assertEquals(expResultTax1, instance.subtotal(tax1));
    }

    /**
     * test of total method, of class Invoice.
     */
    @Test
    public void testTotal() {
        System.out.println("total");
        instance.addLine(line1);
        instance.addLine(line2);
        instance.addLine(line3);
        MonetaryAmount expResult = Money.of(111.96, "EUR");
        assertEquals(expResult, instance.total());
    }

    /**
     * test of moneyToString method, of class Invoice.
     */
    @Test
    public void testMoneyToString() {
        System.out.println("total");
        instance.addLine(line1);
        instance.addLine(line2);
        instance.addLine(line3);
        String expResult = "111.96";
        assertEquals(expResult, instance.moneyToString(instance.total()));
    }

    /**
     * Test of getLineExtensionAmount method, of class Invoice.
     */
    @Test
    public void testGetLineExtensionAmount() {
        System.out.println("getLineExtensionAmount");
        String expResult = "99.99";
        Invoice i = new Invoice("1", "EUR", true);
        i.setLineExtensionAmount(expResult);
        String result = i.getLineExtensionAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLineExtensionAmount method, of class Invoice.
     */
    @Test
    public void testSetLineExtensionAmount() {
        System.out.println("setLineExtensionAmount");
        String lineExtensionAmount = "99.99";
        Invoice i = new Invoice("1", "EUR", true);
        i.setLineExtensionAmount(lineExtensionAmount);
        assertEquals(lineExtensionAmount, i.getLineExtensionAmount());
    }

    /**
     * Test of getTaxExclusiveAmount method, of class Invoice.
     */
    @Test
    public void testGetTaxExclusiveAmount() {
        System.out.println("getTaxExclusiveAmount");
        String expResult = "99.99";
        Invoice i = new Invoice("1", "EUR", true);
        i.setTaxExclusiveAmount(expResult);
        String result = i.getTaxExclusiveAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTaxExclusiveAmount method, of class Invoice.
     */
    @Test
    public void testSetTaxExclusiveAmount() {
        System.out.println("setTaxExclusiveAmount");
        String taxExclusiveAmount = "99.99";
        Invoice i = new Invoice("1", "EUR", true);
        i.setTaxExclusiveAmount(taxExclusiveAmount);
        assertEquals(taxExclusiveAmount, i.getTaxExclusiveAmount());
    }

    /**
     * Test of getTaxInclusiveAmount method, of class Invoice.
     */
    @Test
    public void testGetTaxInclusiveAmount() {
        System.out.println("getTaxInclusiveAmount");
        String expResult = "99.99";
        Invoice i = new Invoice("1", "EUR", true);
        i.setTaxInclusiveAmount(expResult);
        String result = i.getTaxInclusiveAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTaxInclusiveAmount method, of class Invoice.
     */
    @Test
    public void testSetTaxInclusiveAmount() {
        System.out.println("setTaxInclusiveAmount");
        String taxInclusiveAmount = "99.99";
        Invoice i = new Invoice("1", "EUR", true);
        i.setTaxInclusiveAmount(taxInclusiveAmount);
        assertEquals(taxInclusiveAmount, i.getTaxInclusiveAmount());
    }

    /**
     * Test of getPayableAmount method, of class Invoice.
     */
    @Test
    public void testGetPayableAmount() {
        System.out.println("getPayableAmount");
        String expResult = "99.99";
        Invoice i = new Invoice("1", "EUR", true);
        i.setPayableAmount(expResult);
        String result = i.getPayableAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPayableAmount method, of class Invoice.
     */
    @Test
    public void testSetPayableAmount() {
        System.out.println("setPayableAmount");
        String payableAmount = "99.99";
        Invoice i = new Invoice("1", "EUR", true);
        i.setPayableAmount(payableAmount);
        assertEquals(payableAmount, i.getPayableAmount());
    }

}
