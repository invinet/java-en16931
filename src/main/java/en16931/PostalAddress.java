package en16931;

/**
 *
 * @author jtorrents
 */
public class PostalAddress {

    private String address;
    private String postalZone;
    private String city;
    private String country;

    public PostalAddress(String address, String postalZone, String city, String country) {
        this.address = address;
        this.postalZone = postalZone;
        this.city = city;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalZone() {
        return postalZone;
    }

    public void setPostalZone(String postalZone) {
        this.postalZone = postalZone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}
