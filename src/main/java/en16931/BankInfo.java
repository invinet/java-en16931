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

/**
 * Class to represent the bank information of an Entity.
 * 
 * The Entity can play the role of buyer or seller, and this information
 * will be used depending on the means of payment of each concrete transaction.
 * 
 */
public class BankInfo {

    private String account;
    private String iban;
    private String bic;
    private String mandate_reference_identifier;

    /**
     *
     * @param account the bank account
     * @param iban the IBAN
     * @param bic the bank code
     * @param mandate_reference_identifier the mandate reference identifier (for debit transactions)
     */
    public BankInfo(String account, String iban, String bic, String mandate_reference_identifier) {
        this.account = account;
        this.iban = iban;
        this.bic = bic;
        this.mandate_reference_identifier = mandate_reference_identifier;
    }

    /**
     * Returns the bank account
     *
     * @return the bank account
     */
    public String getAccount() {
        return account;
    }

    /**
     * Sets the bank account.
     * 
     * <p>No validation is performed.
     *
     * @param account the bank account.
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * The IBAN of the Entity
     *
     * @return the IBAN
     */
    public String getIban() {
        return iban;
    }

    /**
     * Sets the IBAN
     *
     * @param iban the IBAN
     */
    public void setIban(String iban) {
        this.iban = iban;
    }

    /**
     * Returns the bank code
     *
     *
     * @return the bank code
     * 
     */
    public String getBic() {
        return bic;
    }

    /**
     * Sets the bank code
     *
     * <p>Only some formal validation on the code is performed.
     * 
     * @param bic
     * 
     * @throws IllegalArgumentException for a malformed code
     */
    public void setBic(String bic) {
        if (bic.length() == 8 || bic.length() == 11) {
            this.bic = bic;
        } else {
            throw new IllegalArgumentException("A BIC code has either 8 or 11 charcters");
        }
    }

    /**
     * Returns the mandate reference identifier
     * 
     * <p>Used in debit transactions
     *
     * @return the mandate reference indentifier
     */
    public String getMandateReferenceIdentifier() {
        return mandate_reference_identifier;
    }

    /**
     * Sets the mandate reference identifier
     *
     * @param mandate_reference_identifier the mandate identifier
     */
    public void setMandateReferenceIdentifier(String mandate_reference_identifier) {
        this.mandate_reference_identifier = mandate_reference_identifier;
    }
    
    
}
