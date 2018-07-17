package en16931;

import java.util.ArrayList;
import java.util.Date;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

/**
 *
 * @author jtorrents
 */
public class Invoice {

    private String invoiceId;
    private CurrencyUnit currency;
    private Date issueDate;
    private Date dueDate;
    private Entity sellerParty;
    private Entity buyerParty;
    private ArrayList<InvoiceLine> lines = new ArrayList<InvoiceLine>();

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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Entity getSellerParty() {
        return sellerParty;
    }

    public void setSellerParty(Entity sellerParty) {
        this.sellerParty = sellerParty;
    }

    public Entity getBuyerParty() {
        return buyerParty;
    }

    public void setBuyerParty(Entity buyerParty) {
        this.buyerParty = buyerParty;
    }

    public ArrayList<InvoiceLine> getLines() {
        return lines;
    }

    public void addLine(InvoiceLine line) {
        this.lines.add(line);
    }
    
}
