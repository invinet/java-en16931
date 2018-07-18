package en16931;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;

/*
 * Invoice Line class
 * 
 *
 */
public class InvoiceLine {
    
    private String itemName;
    private String unitCode;
    private double quantity;
    private MonetaryAmount price;
    private MonetaryAmount lineExtensionAmount;
    private CurrencyUnit currency;
    private Tax tax;

    public InvoiceLine(String itemName, String unitCode, double quantity, double price, String currency) {
        this.currency = Monetary.getCurrency(currency);
        this.itemName = itemName;
        this.unitCode = unitCode;
        this.quantity = quantity;
        this.price = Money.of(price, this.currency);
        //this.price = Monetary.getDefaultAmountFactory()
        //    .setCurrency(this.currency).setNumber(price).create();
    }

    public InvoiceLine(String itemName, String unitCode, double quantity, double price, String currency, Tax tax) {
        this.currency = Monetary.getCurrency(currency);
        this.itemName = itemName;
        this.unitCode = unitCode;
        this.quantity = quantity;
        this.price = Money.of(price, this.currency);
        this.tax = tax;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public double getQuantity() {
        return quantity;
    }
    
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public MonetaryAmount getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = Money.of(price, currency);
        //this.price = Monetary.getDefaultAmountFactory()
        //    .setCurrency(this.currency).setNumber(price).create();
    }

    public CurrencyUnit getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = Monetary.getCurrency(currency);
    }

    public MonetaryAmount getLineExtensionAmount() {
        return this.price.multiply(this.quantity).with(Monetary.getDefaultRounding());
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public boolean hasTax(Tax tax) {
        if (tax == null) {
            return true;
        } else {
            return this.tax == tax;
        }
    }
}