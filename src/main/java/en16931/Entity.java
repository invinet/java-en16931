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
 * Class that represents an Entity.
 * 
 * It can either play the role of seller (AccountingSupplierParty) or
 * buyer (AccountingCustomerParty) in a transaction described by an Invoice.
 * 
 */
public class Entity {

    private String name;
    private String taxScheme;
    private String taxId;
    private String partyLegalEntityId;
    private String registrationName;
    private String mail;
    private String endpoint;
    private String endpointScheme;
    private PostalAddress postalAddress;
    private BankInfo bankInfo;

    /**
     *
     * @param name the name of entity
     * @param taxScheme the tax scheme
     * @param taxId the tax identification number
     * @param partyLegalEntityId the legal identifier
     * @param registrationName the official registration name
     * @param mail the emal
     * @param endpoint the PEPPOL endpoint ID
     * @param endpointScheme the endpoint scheme.
     */
    public Entity(String name, String taxScheme, String taxId, String partyLegalEntityId, String registrationName, String mail, String endpoint, String endpointScheme) {
        this.name = name;
        this.taxScheme = taxScheme;
        this.taxId = taxId;
        this.partyLegalEntityId = partyLegalEntityId;
        this.registrationName = registrationName;
        this.mail = mail;
        this.endpoint = endpoint;
        this.endpointScheme = endpointScheme;
    }
    
    /**
     * Returns the name of the Entity
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name of the Entity.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the tax scheme of the ENtity
     *
     * @return tax scheme
     */
    public String getTaxScheme() {
        return taxScheme;
    }
    
    /**
     * Sets the tax scheme of the Entity.
     *
     * @param taxScheme the tax scheme
     */
    public void setTaxScheme(String taxScheme) {
        this.taxScheme = taxScheme;
    }

    /**
     * Returns the tax identification number of the Entity
     *
     * @return the tax identification number
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * Set the tax identification number of the Entity
     *
     * @param taxId tax identification number
     */
    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    /**
     * Returns the legal identifier of the Entity
     * 
     * @return the legal identifier
     */
    public String getPartyLegalEntityId() {
        return partyLegalEntityId;
    }

    /**
     * Sets the legal identifier of the Entity
     *
     * @param partyLegalEntityId the legal identifier
     */
    public void setPartyLegalEntityId(String partyLegalEntityId) {
        this.partyLegalEntityId = partyLegalEntityId;
    }

    /**
     * Returns the registration name of the Entity
     *
     * @return the registration name
     */
    public String getRegistrationName() {
        return registrationName;
    }

    /**
     * Sets the registration name of the Entity
     *
     * @param registrationName the registration name
     */
    public void setRegistrationName(String registrationName) {
        this.registrationName = registrationName;
    }

    /**
     * Returns the E-mail of the Entity
     *
     * @return E-mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets the E-mail of the Entity
     *
     * @param mail E-mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Returns the PEPPOL endpoint identifier of the Entity.
     *
     * @return the PEPPOL endpoint identifier
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * Sets the PEPPOL endpoint identifier of the Entity.
     *
     * @param endpoint the PEPPOL endpoint identifier
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Returns the PEPPOL endpoint scheme of the Entity.
     *
     * @return the PEPPOL endpoint scheme
     */
    public String getEndpointScheme() {
        return endpointScheme;
    }

    /**
     * Sets the PEPPOL endpoint scheme of the Entity.
     *
     * @param endpointScheme PEPPOL endpoint scheme
     */
    public void setEndpointScheme(String endpointScheme) {
        this.endpointScheme = endpointScheme;
    }

    /**
     * Returns the PostalAddress of the Entity
     *
     * @return the PostalAddress
     */
    public PostalAddress getPostalAddress() {
        return postalAddress;
    }

    /**
     * Sets the PostalAddress of the Entity.
     *
     * @param postalAddress a PostalAddress instance
     */
    public void setPostalAddress(PostalAddress postalAddress) {
        this.postalAddress = postalAddress;
    }

    /**
     * Returns the bank information of the Entity.
     *
     * @return a BankInfo instance
     */
    public BankInfo getBankInfo() {
        return bankInfo;
    }

    /**
     * Sets the bank information of the Entity.
     *
     * @param bankInfo a BankInfo instance.
     */
    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }
}
