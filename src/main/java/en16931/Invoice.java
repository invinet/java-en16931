package en16931;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.CurrencyStyle;

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
    private MonetaryAmount _lineExtensionAmount;
    private MonetaryAmount _taxExclusiveAmount;
    private MonetaryAmount _taxInclusiveAmount;
    private MonetaryAmount _payableAmount;
    private final boolean importedFromXml;
    private final ArrayList<InvoiceLine> lines;

    public Invoice(String invoiceId, String currency, boolean importedFromXml) {
        this.lines = new ArrayList<>();
        this.invoiceId = invoiceId;
        this.currency = Monetary.getCurrency(currency);
        this.importedFromXml = importedFromXml;
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

    public MonetaryAmount grossSubtotal(Tax tax) {
        MonetaryAmount amount = Money.of(0, this.currency);
        for (InvoiceLine line: this.linesWithTaxes(tax)) {
            amount = amount.add(line.getLineExtensionAmount())
                        .with(Monetary.getDefaultRounding());
        }
        return amount;
    }

    public MonetaryAmount taxableBase(Tax tax) {
        MonetaryAmount discount = Money.of(0, this.currency); //TODO
        MonetaryAmount charge = Money.of(0, this.currency); //TODO
        return this.grossSubtotal(tax)
                .subtract(discount)
                .add(charge)
                .with(Monetary.getDefaultRounding());
    }

    public ArrayList<Tax> uniqueTaxes() {
        ArrayList<Tax> taxes = new ArrayList<>();
        for (InvoiceLine line: this.lines) {
            if (!taxes.contains(line.getTax())) {
                taxes.add(line.getTax());
            }
        }
        return taxes;
    }

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

    public MonetaryAmount subtotal(Tax tax) {
        MonetaryAmount discount = Money.of(0, this.currency); //TODO
        MonetaryAmount charge = Money.of(0, this.currency); //TODO
        return this.grossSubtotal(tax)
                .subtract(discount)
                .add(charge)
                .with(Monetary.getDefaultRounding());
    }

    public MonetaryAmount total() {
        return this.subtotal(null)
                .add(this.taxAmount(null))
                .with(Monetary.getDefaultRounding());
    }

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

    public String getLineExtensionAmount() {
        if (this.importedFromXml) {
            return this.moneyToString(_lineExtensionAmount);
        } else {
            return this.moneyToString(this.grossSubtotal(null));
        }
    }

    public void setLineExtensionAmount(String lineExtensionAmount) {
        this._lineExtensionAmount = Money.of(new BigDecimal(lineExtensionAmount), this.currency);
    }

    public String getTaxExclusiveAmount() {
        if (this.importedFromXml) {
            return this.moneyToString(_taxExclusiveAmount);
        } else {
            return this.moneyToString(this.subtotal(null));
        }
    }

    public void setTaxExclusiveAmount(String taxExclusiveAmount) {
        this._taxExclusiveAmount = Money.of(new BigDecimal(taxExclusiveAmount), this.currency);
    }

    public String getTaxInclusiveAmount() {
        if (this.importedFromXml) {
            return this.moneyToString(_taxInclusiveAmount);
        } else {
            return this.moneyToString(this.total());
        }
    }

    public void setTaxInclusiveAmount(String taxInclusiveAmount) {
        this._taxInclusiveAmount = Money.of(new BigDecimal(taxInclusiveAmount), this.currency);
    }

    public String getPayableAmount() {
        if (this.importedFromXml) {
            return this.moneyToString(_payableAmount);
        } else {
            MonetaryAmount prepaidAmount = Money.of(0, this.currency); // TODO
            return this.moneyToString(this.total().subtract(prepaidAmount));
        }
    }

    public void setPayableAmount(String payableAmount) {
        this._payableAmount = Money.of(new BigDecimal(payableAmount), this.currency);
    }
}
