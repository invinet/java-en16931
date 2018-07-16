package en16931;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

/**
 *
 * @author jtorrents
 */
public class Invoice {

    private String invoiceId;
    private CurrencyUnit currency;

    public Invoice(String invoiceId, String currency) {
        this.invoiceId = invoiceId;
        this.currency = Monetary.getCurrency(currency);
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public CurrencyUnit getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = Monetary.getCurrency(currency);
    }
    
}
