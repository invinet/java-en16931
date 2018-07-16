package en16931;

/**
 *
 * @author jtorrents
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
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getTaxScheme() {
        return taxScheme;
    }
    
    public void setTaxScheme(String taxScheme) {
        this.taxScheme = taxScheme;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getPartyLegalEntityId() {
        return partyLegalEntityId;
    }

    public void setPartyLegalEntityId(String partyLegalEntityId) {
        this.partyLegalEntityId = partyLegalEntityId;
    }

    public String getRegistrationName() {
        return registrationName;
    }

    public void setRegistrationName(String registrationName) {
        this.registrationName = registrationName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpointScheme() {
        return endpointScheme;
    }

    public void setEndpointScheme(String endpointScheme) {
        this.endpointScheme = endpointScheme;
    }

    public PostalAddress getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(PostalAddress postalAddress) {
        this.postalAddress = postalAddress;
    }

    public BankInfo getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }
}
