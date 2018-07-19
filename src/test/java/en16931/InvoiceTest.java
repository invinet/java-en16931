package en16931;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.UnknownCurrencyException;
import org.javamoney.moneta.Money;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jtorrents
 */
public class InvoiceTest {

    private final Invoice instance;
    private final Entity seller;
    private final Entity buyer;
    private final BankInfo sellerBankInfo;
    private final BankInfo buyerBankInfo;
    private final Tax tax;
    private final Tax tax1;
    private final InvoiceLine line1;
    private final InvoiceLine line2;
    private final InvoiceLine line3;
    private final PostalAddress postal;

    public InvoiceTest() {
        this.postal = new PostalAddress("easy street", "08080", "Barcelona", "ES");
        this.sellerBankInfo = new BankInfo("1234567", "ES661234567321", "AAAABBCCDDD", "123");
        this.buyerBankInfo = new BankInfo("7654321", "ES881272121772", "DDDDCCBBAAA", "123");
        this.instance = new Invoice("1", "EUR", false);
        this.seller = new Entity("Acme Inc.", "VAT", "ES34626691F", "ES34626691F",
                "Acme INc.", "acme@acme.io", "ES76281415Y", "ES:VAT");
        this.seller.setPostalAddress(postal);
        this.seller.setBankInfo(sellerBankInfo);
        this.buyer = new Entity("Corp Inc.", "VAT", "ES76281415Y", "ES76281415Y",
                "Corp INc.", "corp@corp.io", "ES76281415Y", "ES:VAT");
        this.buyer.setPostalAddress(postal);
        this.buyer.setBankInfo(buyerBankInfo);
        this.tax = new Tax(0.21, "S", "IVA", "");
        this.tax1 = new Tax(0.1, "S", "IVA", "");
        this.line1 = new InvoiceLine("foo", "EA", 3, 20.1, "EUR", tax);
        this.line2 = new InvoiceLine("bar", "EA", 5, 2.7, "EUR", tax);
        this.line3 = new InvoiceLine("baz", "EA", 2, 10.3, "EUR", tax1);
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

    /**
     * Test of toXml method, of class Invoice
     */
    @Test
    public void testToXml() {
        System.out.println("toXml");
        instance.addLine(line1);
        instance.addLine(line2);
        instance.addLine(line3);
        instance.setSellerParty(seller);
        instance.setBuyerParty(buyer);
        instance.setIssueDate(new Date());
        instance.setDueDate(new Date());
        String result = instance.toXml();
        System.out.println(result);
    }

    /**
     * Test of save method, of class Invoice
     */
    @Test
    public void testSave() {
        System.out.println("save");
        instance.addLine(line1);
        instance.addLine(line2);
        instance.addLine(line3);
        instance.setSellerParty(seller);
        instance.setBuyerParty(buyer);
        instance.setIssueDate(new Date());
        instance.setDueDate(new Date());
        //instance.setPaymentMeansCode("31");
        instance.save("/tmp/invoice.xml");
    }

    /**
     * Test of charges and friends
     */
    @Test
    public void testChargesAndFriends() {
        System.out.println("testCharge");
        instance.addLine(line1);
        instance.addLine(line2);
        instance.setSellerParty(seller);
        instance.setBuyerParty(buyer);
        instance.setIssueDate(new Date());
        instance.setDueDate(new Date());
        instance.setChargeAmount(10);
        MonetaryAmount expSubTotal = Money.of(83.8, "EUR");
        MonetaryAmount expTotal = Money.of(101.4, "EUR");
        assertEquals(expSubTotal, instance.subtotal(null));
        assertEquals(expTotal, instance.total());
    }

    /**
     * Test of dicount and friends
     */
    @Test
    public void testDiscountAndFriends() {
        System.out.println("testDiscount");
        instance.addLine(line1);
        instance.addLine(line2);
        instance.setSellerParty(seller);
        instance.setBuyerParty(buyer);
        instance.setIssueDate(new Date());
        instance.setDueDate(new Date());
        instance.setDiscountAmount(10);
        MonetaryAmount expSubTotal = Money.of(63.8, "EUR");
        MonetaryAmount expTotal = Money.of(77.2, "EUR");
        assertEquals(expSubTotal, instance.subtotal(null));
        assertEquals(expTotal, instance.total());
    }

    /**
     * Test of dicount and charge together
     */
    @Test
    public void testDiscountAndCharge() {
        System.out.println("discountAndCharge");
        instance.addLine(line1);
        instance.addLine(line2);
        instance.setSellerParty(seller);
        instance.setBuyerParty(buyer);
        instance.setIssueDate(new Date());
        instance.setDueDate(new Date());
        instance.setDiscountPercent(10);
        instance.setChargePercent(5);
        MonetaryAmount expSubTotal = Money.of(70.11, "EUR");
        MonetaryAmount expTotal = Money.of(84.83, "EUR");
        assertEquals(expSubTotal, instance.subtotal(null));
        assertEquals(expTotal, instance.total());
    }

    /**
     * Test of save method with charges and discounts
     */
    @Test
    public void testChargesAndDiscountSave() {
        System.out.println("saveChargesAndDiscounts");
        instance.addLine(line1);
        instance.addLine(line2);
        instance.setSellerParty(seller);
        instance.setBuyerParty(buyer);
        instance.setIssueDate(new Date());
        instance.setDueDate(new Date());
        instance.setDiscountPercent(10);
        instance.setChargePercent(5);
        instance.setPaymentMeansCode("49");
        instance.save("/tmp/invoice_with_charges.xml");
    }

    /**
     * Test of dateToString method, of class Invoice.
     * @throws java.text.ParseException
     */
    @Test
    public void testDateToString() throws ParseException {
        System.out.println("dateToString");
        String expResult = "2018-06-21";
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date date = parser.parse(expResult);
        String result = instance.dateToString(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPaymentMeansCode method, of class Invoice.
     */
    @Test
    public void testGetPaymentMeansCode() {
        System.out.println("getPaymentMeansCode");
        String expResult = "10";
        instance.setPaymentMeansCode("10");
        String result = instance.getPaymentMeansCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPaymentMeansCode method, of class Invoice.
     */
    @Test
    public void testSetPaymentMeansCode() {
        System.out.println("setPaymentMeansCode");
        String paymentMeansCode = "10";
        instance.setPaymentMeansCode(paymentMeansCode);
        assertEquals(paymentMeansCode, instance.getPaymentMeansCode());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetPaymentMeansCodeWrong() {
        System.out.println("setPaymentMeansCodeWrong");
        instance.setPaymentMeansCode("foo");
    }

    /**
     * Test of getChargePercent method, of class Invoice.
     */
    @Test
    public void testGetChargePercent() {
        System.out.println("getChargePercent");
        double expResult = 0.1;
        instance.setChargePercent(10);
        double result = instance.getChargePercent();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getStringChargePercent method, of class Invoice.
     */
    @Test
    public void testGetStringChargePercent() {
        System.out.println("getStringChargePercent");
        String expResult = "10.00";
        instance.setChargePercent(10);
        String result = instance.getStringChargePercent();
        assertEquals(expResult, result);
    }

    /**
     * Test of setChargePercent method, of class Invoice.
     */
    @Test
    public void testSetChargePercent() {
        System.out.println("setChargePercent");
        double percent = 0.1;
        instance.setChargePercent(10);
        assertEquals(percent, instance.getChargePercent(), 0.0);
    }

    /**
     * Test of getChargeAmount method, of class Invoice.
     */
    @Test
    public void testGetChargeAmount() {
        System.out.println("getChargeAmount");
        instance.addLine(line1);
        MonetaryAmount expResult = Money.of(10, "EUR");
        instance.setChargeAmount(10);
        MonetaryAmount result = instance.getChargeAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of isChargeSet method, of class Invoice.
     */
    @Test
    public void testIsChargeSet() {
        System.out.println("isChargeSet");
        instance.addLine(line2);
        assertFalse(instance.isChargeSet());
        instance.setChargePercent(10);
        assertTrue(instance.isChargeSet());
    }

    /**
     * Test of setChargeAmount method, of class Invoice.
     */
    @Test
    public void testSetChargeAmount() {
        System.out.println("setChargeAmount");
        instance.addLine(line3);
        double chargeAmount = 10;
        instance.setChargeAmount(chargeAmount);
        assertEquals(Money.of(chargeAmount, "EUR"), instance.getChargeAmount());
        
    }

    /**
     * Test of getChargeBaseAmount method, of class Invoice.
     */
    @Test
    public void testGetChargeBaseAmount() {
        System.out.println("getChargeBaseAmount");
        instance.addLine(line3);
        instance.setChargeAmount(10);
        String expResult = "20.41";
        String result = instance.getChargeBaseAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiscountPercent method, of class Invoice.
     */
    @Test
    public void testGetDiscountPercent() {
        System.out.println("getDiscountPercent");
        double expResult = 0.1;
        instance.setDiscountPercent(10);
        double result = instance.getDiscountPercent();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getStringDiscountPercent method, of class Invoice.
     */
    @Test
    public void testGetStringDiscountPercent() {
        System.out.println("getStringDiscountPercent");
        String expResult = "10.00";
        instance.setDiscountPercent(10);
        String result = instance.getStringDiscountPercent();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDiscountPercent method, of class Invoice.
     */
    @Test
    public void testSetDiscountPercent() {
        System.out.println("setDiscountPercent");
        double percent = 0.1;
        instance.setDiscountPercent(10);
        assertEquals(percent, instance.getDiscountPercent(), 0.0);
    }

    /**
     * Test of getDiscountAmount method, of class Invoice.
     */
    @Test
    public void testGetDiscountAmount() {
        System.out.println("getDiscountAmount");
        instance.addLine(line1);
        MonetaryAmount expResult = Money.of(15, "EUR");
        instance.setDiscountAmount(15);
        MonetaryAmount result = instance.getDiscountAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of isDiscountSet method, of class Invoice.
     */
    @Test
    public void testIsDiscountSet() {
        System.out.println("isDiscountSet");
        instance.addLine(line1);
        assertFalse(instance.isDiscountSet());
        instance.setDiscountAmount(15);
        assertTrue(instance.isDiscountSet());
        
    }

    /**
     * Test of setDiscountAmount method, of class Invoice.
     */
    @Test
    public void testSetDiscountAmount() {
        System.out.println("setDiscountAmount");
        instance.addLine(line2);
        double discountAmount = 10;
        instance.setDiscountAmount(discountAmount);
        assertEquals(Money.of(discountAmount, "EUR"), instance.getDiscountAmount());
    }

    /**
     * Test of getDiscountBaseAmount method, of class Invoice.
     */
    @Test
    public void testGetDiscountBaseAmount() {
        System.out.println("getDiscountBaseAmount");
        String expResult = "13.51";
        instance.addLine(line2);
        instance.setDiscountAmount(10);
        String result = instance.getDiscountBaseAmount();
        assertEquals(expResult, result);
    }
}
