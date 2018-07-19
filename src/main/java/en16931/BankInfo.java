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
