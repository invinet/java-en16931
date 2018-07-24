/* 
 * Copyright 2018 Invinet Sistemes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package en16931;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;


/**
 * Class that represents an Invoice Line.
 *
 */

public class InvoiceLine {
    
    private String itemName;
    private String unitCode;
    private Double quantity;
    private MonetaryAmount price;
    private MonetaryAmount lineExtensionAmount;
    private CurrencyUnit currency;
    private Tax tax;

    /**
     *
     * @param itemName the name of the item
     * @param unitCode the unit code of the quantity of items
     * @param quantity the quantity of items
     * @param price the price of a single item
     * @param currency the currency of the price
     */
    public InvoiceLine(String itemName, String unitCode, double quantity, double price, String currency) {
        this.currency = Monetary.getCurrency(currency);
        this.itemName = itemName;
        this.unitCode = unitCode;
        this.quantity = quantity;
        this.price = Money.of(price, this.currency);
        //this.price = Monetary.getDefaultAmountFactory()
        //    .setCurrency(this.currency).setNumber(price).create();
    }

    /**
     *
     * @param itemName the name of the item
     * @param unitCode the unit code of the quantity of items
     * @param quantity the quantity of items
     * @param price the price of a single item
     * @param currency the currency of the price
     * @param tax a Tax instance
     */
    public InvoiceLine(String itemName, String unitCode, double quantity, double price, String currency, Tax tax) {
        this.currency = Monetary.getCurrency(currency);
        this.itemName = itemName;
        this.unitCode = unitCode;
        this.quantity = quantity;
        this.price = Money.of(price, this.currency);
        this.tax = tax;
    }
    
    /**
     * Returns the item name of the line
     *
     * @return the item name
     */
    public String getItemName() {
        return itemName;
    }
    
    /**
     * Sets the item name of the line
     *
     * @param itemName the item name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Returns the unit code of the quantity of the line
     *
     * @return the unit code of the line
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * Sets the unit code of the quantity of items of the line
     *
     * @param unitCode the unit code
     */
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    /**
     * Returns the quantity of items of the line
     *
     * @return the quntity of items
     */
    public double getQuantity() {
        return quantity;
    }
    
    /**
     * Sets the quantity of items of the line
     *
     * @param quantity the quantity of items
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the price of a single item of the line
     *
     * @return the price of a single item
     */
    public MonetaryAmount getPrice() {
        return price;
    }
    
    /**
     * Sets the price of a single item of the line
     *
     * @param price the price of a single item
     */
    public void setPrice(double price) {
        this.price = Money.of(price, currency);
        //this.price = Monetary.getDefaultAmountFactory()
        //    .setCurrency(this.currency).setNumber(price).create();
    }

    /**
     * Returns the currency of the price of the line
     *
     * @return the currency of the line
     */
    public CurrencyUnit getCurrency() {
        return currency;
    }
    
    /**
     * Sets the currency of the price of the line
     * 
     * <p>Accepts three letter ISO-4217 currencu codes. 
     *
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = Monetary.getCurrency(currency);
    }

    /**
     * Returns the line extension amount.
     * 
     * <p>That is, the price multiplied by the quantity of items of the line.
     *
     * @return the line extension amount
     */
    public MonetaryAmount getLineExtensionAmount() {
        return this.price.multiply(this.quantity).with(Monetary.getDefaultRounding());
    }

    /**
     * Returns the Tax instance associated with the line
     *
     * @return a Tax instance
     */
    public Tax getTax() {
        return tax;
    }

    /**
     * Associates a Tax instance to the line
     *
     * @param tax a Tax instance
     */
    public void setTax(Tax tax) {
        this.tax = tax;
    }

    /**
     * Returns true if the line is associated to an equivalent Tax instance
     *
     * @param tax a tax instance
     * @return Whether the line is associated to an equivalent Tax instance
     */
    public boolean hasTax(Tax tax) {
        if (tax == null) {
            return true;
        } else {
            return this.tax.equals(tax);
        }
    }
}