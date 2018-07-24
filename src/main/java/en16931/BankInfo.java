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
 *
 * @author jtorrents
 */
public class BankInfo {

    private String account;
    private String iban;
    private String bic;
    private String mandate_reference_identifier;

    public BankInfo(String account, String iban, String bic, String mandate_reference_identifier) {
        this.account = account;
        this.iban = iban;
        this.bic = bic;
        this.mandate_reference_identifier = mandate_reference_identifier;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        if (bic.length() == 8 || bic.length() == 11) {
            this.bic = bic;
        } else {
            throw new IllegalArgumentException("A BIC code has either 8 or 11 charcters");
        }
    }

    public String getMandateReferenceIdentifier() {
        return mandate_reference_identifier;
    }

    public void setMandateReferenceIdentifier(String mandate_reference_identifier) {
        this.mandate_reference_identifier = mandate_reference_identifier;
    }
    
    
}
