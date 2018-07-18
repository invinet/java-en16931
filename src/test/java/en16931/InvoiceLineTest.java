
package en16931;


import java.math.BigDecimal;
import javax.money.UnknownCurrencyException;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jtorrents
 */
public class InvoiceLineTest {
    
    private final Tax tax;
    private final InvoiceLine instance;

    public InvoiceLineTest() {
        this.tax = new Tax(0.21, "S", "IVA", "");
        this.instance = new InvoiceLine("foo", "EA", 3, 20.1, "EUR", tax);
    }

    /**
     * Test of getItemName method, of class InvoiceLine.
     */
    @Test
    public void testGetItemName() {
        System.out.println("getItemName");
        String expResult = "foo";
        String result = instance.getItemName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setItemName method, of class InvoiceLine.
     */
    @Test
    public void testSetItemName() {
        System.out.println("setItemName");
        String itemName = "bar";
        instance.setItemName(itemName);
        assertEquals(itemName, instance.getItemName());
    }

    /**
     * Test of getQuantity method, of class InvoiceLine.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        double expResult = 3;
        double result = instance.getQuantity();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setQuantity method, of class InvoiceLine.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        double quantity = 2;
        instance.setQuantity(quantity);
        assertEquals(quantity, instance.getQuantity(), 0.0);
    }

    /**
     * Test of getPrice method, of class InvoiceLine.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        BigDecimal expResult = new BigDecimal("20.1");
        MonetaryAmount result = instance.getPrice();
        assertEquals(expResult, result.getNumber().numberValue(BigDecimal.class));
    }

    /**
     * Test of setPrice method, of class InvoiceLine.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        double price = 1.23;
        instance.setPrice(price);
        assertEquals(new BigDecimal("1.23"), instance.getPrice().getNumber().numberValue(BigDecimal.class));
    }

    /**
     * Test of getCurrency method, of class InvoiceLine.
     */
    @Test
    public void testGetCurrency() {
        System.out.println("getCurrency");
        String expResult = "EUR";
        CurrencyUnit result = instance.getCurrency();
        assertEquals(expResult, result.getCurrencyCode());
    }

    /**
     * Test of setCurrency method, of class InvoiceLine.
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
        instance.setCurrency("AAA");
    }

    /**
     * Test of getLineExtensionAmount method, of class InvoiceLine.
     */
    @Test
    public void testGetLineExtensionAmount() {
        System.out.println("getLineExtensionAmount");
        BigDecimal expResult = new BigDecimal("60.3");
        MonetaryAmount result = instance.getLineExtensionAmount();
        assertEquals(expResult, result.getNumber().numberValue(BigDecimal.class));
    }

    /**
     * Test of getTax method, of class InvoiceLine.
     */
    @Test
    public void testGetTax() {
        System.out.println("getTax");
        Tax expResult = new Tax(0.21, "S", "IVA", "");
        Tax result = instance.getTax();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTax method, of class InvoiceLine.
     */
    @Test
    public void testSetTax() {
        System.out.println("setTax");
        Tax tax = new Tax(0.1, "AE", "IVA", "");
        instance.setTax(tax);
        assertEquals(tax, instance.getTax());
    }

    /**
     * Tests of hasTax method, of class InvoiceLine
     */
    @Test
    public void testhasTaxNull() {
        System.out.println("hasTaxNull");
        assertTrue(instance.hasTax(null));
    }

    @Test
    public void testhasTaxTrue() {
        System.out.println("hasTaxTrue");
        assertTrue(instance.hasTax(tax));
    }

    @Test
    public void testhasTaxFalse() {
        System.out.println("hasTaxFalse");
        Tax newTax = new Tax(0.1, "AE", "IVA", "");
        assertFalse(instance.hasTax(newTax));
    }
}
