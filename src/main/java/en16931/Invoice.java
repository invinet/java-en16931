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
 *
 * @author jtorrents
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

    public Invoice(String invoiceId, String currency, boolean importedFromXml) {
        this.lines = new ArrayList<>();
        this.invoiceId = invoiceId;
        this.currency = Monetary.getCurrency(currency);
        this.importedFromXml = importedFromXml;
    }

    public String getOriginalXml() {
        return originalXml;
    }

    public void setOriginalXml(String originalXml) {
        this.originalXml = originalXml;
    }

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

    public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
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

    public String getPaymentMeansCode() {
        return paymentMeansCode;
    }

    public void setPaymentMeansCode(String paymentMeansCode) {
        if (PAYMENT_CODES.contains(paymentMeansCode)) {
            this.paymentMeansCode = paymentMeansCode;
        } else {
            throw new IllegalArgumentException("paymentMeansCode must be one of: 10, 49, 31, 26, 23, 48, ZZZ");
        }
    }

    public ArrayList<InvoiceLine> getLines() {
        return lines;
    }

    public void addLine(InvoiceLine line) {
        this.lines.add(line);
    }

    public double getChargePercent() {
        return chargePercent;
    }

    public String getStringChargePercent() {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        DecimalFormat formatter = (DecimalFormat)nf;
        formatter.applyPattern("#0.00");
        return formatter.format(chargePercent * 100);
    }

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

    public MonetaryAmount getChargeAmount() {
        if (chargeAmount == null) {
            return Money.of(0, this.currency);
        } else {
            return chargeAmount;
        }
    }

    public boolean isChargeSet() {
        return chargeAmount != null;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = Money.of(chargeAmount, this.currency);
        BigDecimal gross = this.grossSubtotal(null).getNumber().numberValue(BigDecimal.class);
        BigDecimal amount = this.chargeAmount.getNumber().numberValue(BigDecimal.class);
        this.chargePercent = amount.divide(gross, 2, RoundingMode.HALF_UP).doubleValue();
    }

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

    public double getDiscountPercent() {
        return discountPercent;
    }

    public String getStringDiscountPercent() {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        DecimalFormat formatter = (DecimalFormat)nf;
        formatter.applyPattern("#0.00");
        return formatter.format(discountPercent * 100);
    }

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

    public MonetaryAmount getDiscountAmount() {
        if (discountAmount == null) {
            return Money.of(0, this.currency);
        } else {
            return discountAmount;
        }
    }

    public boolean isDiscountSet() {
        return discountAmount != null;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = Money.of(discountAmount, this.currency);
        BigDecimal gross = this.grossSubtotal(null).getNumber().numberValue(BigDecimal.class);
        BigDecimal amount = this.discountAmount.getNumber().numberValue(BigDecimal.class);
        this.discountPercent = amount.divide(gross, 2, RoundingMode.HALF_UP).doubleValue();
    }

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
        MonetaryAmount discount = this.getDiscountAmount();
        MonetaryAmount charge = this.getChargeAmount();
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
        MonetaryAmount discount = this.getDiscountAmount();
        MonetaryAmount charge = this.getChargeAmount();
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
