package en16931;

import java.util.ArrayList;
import java.util.Date;
import javax.money.CurrencyUnit;
import javax.money.UnknownCurrencyException;
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
    private final Tax tax;
    private final InvoiceLine line1;
    private final InvoiceLine line2;

    public InvoiceTest() {
        this.instance = new Invoice("1", "EUR");
        this.seller = new Entity("Acme Inc.", "VAT", "ES34626691F", "ES34626691F",
                "Acme INc.", "acme@acme.io", "ES76281415Y", "ES:VAT");
        this.buyer = new Entity("Corp Inc.", "VAT", "ES76281415Y", "ES76281415Y",
                "Corp INc.", "corp@corp.io", "ES76281415Y", "ES:VAT");
        this.tax = new Tax(0.21, "S", "IVA", "");
        this.line1 = new InvoiceLine("foo", 3, 20.1, "EUR", tax);
        this.line2 = new InvoiceLine("bar", 5, 2.7, "EUR", tax);
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
        Invoice i = new Invoice("1", "AAA");
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
        ArrayList<InvoiceLine> expResult = new ArrayList<InvoiceLine>();
        expResult.add(line1);
        expResult.add(line2);
        instance.addLine(line1);
        instance.addLine(line2);
        assertEquals(expResult, instance.getLines());
    }
    
}
