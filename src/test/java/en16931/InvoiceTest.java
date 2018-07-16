package en16931;

import en16931.Invoice;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.money.UnknownCurrencyException;

/*
 * Test for the Invoice class
 * 
 *
 */
public class InvoiceTest {
    @Test public void invoiceId() {
        Invoice i = new Invoice("1", "EUR");
        assertEquals("1", i.getInvoiceId());
    }

    @Test public void setInvoiceId() {
        Invoice i = new Invoice("1", "EUR");
        assertEquals("1", i.getInvoiceId());
        i.setInvoiceId("0001-2018");
        assertEquals("0001-2018", i.getInvoiceId());
    }

    @Test public void currency() {
        Invoice i = new Invoice("1", "EUR");
        assertEquals("EUR", i.getCurrency().getCurrencyCode());
    }

    @Test public void setCurrency() {
        Invoice i = new Invoice("1", "EUR");
        assertEquals("EUR", i.getCurrency().getCurrencyCode());
        i.setCurrency("USD");
        assertEquals("USD", i.getCurrency().getCurrencyCode());
    }

    @Test(expected = UnknownCurrencyException.class)
    public void setCurrency_with_invalid_currency_code() {
        Invoice i = new Invoice("1", "AAA");
    }
}
