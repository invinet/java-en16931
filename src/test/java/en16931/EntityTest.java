package en16931;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jtorrents
 */
public class EntityTest {
    
    private Entity instance = new Entity("Acme Inc.", "VAT", "ES34626691F", "ES34626691F","Acme INc.","acme@acme.io","ES76281415Y","ES:VAT");
    
    /**
     * Test of getName method, of class Entity.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Acme Inc.";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Entity.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        instance.setName("foo");
        assertEquals("foo", instance.getName());
    }

    /**
     * Test of getTaxScheme method, of class Entity.
     */
    @Test
    public void testGetTaxScheme() {
        System.out.println("getTaxScheme");
        String expResult = "VAT";
        String result = instance.getTaxScheme();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTaxScheme method, of class Entity.
     */
    @Test
    public void testSetTaxScheme() {
        System.out.println("setTaxScheme");
        String taxScheme = "IRPF";
        instance.setTaxScheme(taxScheme);
        assertEquals(taxScheme, instance.getTaxScheme());
    }

    /**
     * Test of getTaxId method, of class Entity.
     */
    @Test
    public void testGetTaxId() {
        System.out.println("getTaxId");
        String expResult = "ES34626691F";
        String result = instance.getTaxId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTaxId method, of class Entity.
     */
    @Test
    public void testSetTaxId() {
        System.out.println("setTaxId");
        String taxId = "ES34626635R";
        instance.setTaxId(taxId);
        assertEquals(taxId, instance.getTaxId());
    }

    /**
     * Test of getPartyLegalEntityId method, of class Entity.
     */
    @Test
    public void testGetPartyLegalEntityId() {
        System.out.println("getPartyLegalEntityId");
        String expResult = "ES34626691F";
        String result = instance.getPartyLegalEntityId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPartyLegalEntityId method, of class Entity.
     */
    @Test
    public void testSetPartyLegalEntityId() {
        System.out.println("setPartyLegalEntityId");
        String partyLegalEntityId = "ES34626635R";
        instance.setPartyLegalEntityId(partyLegalEntityId);
        assertEquals(partyLegalEntityId, instance.getPartyLegalEntityId());
    }

    /**
     * Test of getRegistrationName method, of class Entity.
     */
    @Test
    public void testGetRegistrationName() {
        System.out.println("getRegistrationName");
        String expResult = "Acme INc.";
        String result = instance.getRegistrationName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRegistrationName method, of class Entity.
     */
    @Test
    public void testSetRegistrationName() {
        System.out.println("setRegistrationName");
        String registrationName = "foo";
        instance.setRegistrationName(registrationName);
        assertEquals(registrationName, instance.getRegistrationName());
    }

    /**
     * Test of getMail method, of class Entity.
     */
    @Test
    public void testGetMail() {
        System.out.println("getMail");
        String expResult = "acme@acme.io";
        String result = instance.getMail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMail method, of class Entity.
     */
    @Test
    public void testSetMail() {
        System.out.println("setMail");
        String mail = "test@test.com";
        instance.setMail(mail);
        assertEquals(mail, instance.getMail());
    }

    /**
     * Test of getEndpoint method, of class Entity.
     */
    @Test
    public void testGetEndpoint() {
        System.out.println("getEndpoint");
        String expResult = "ES76281415Y";
        String result = instance.getEndpoint();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndpoint method, of class Entity.
     */
    @Test
    public void testSetEndpoint() {
        System.out.println("setEndpoint");
        String endpoint = "ES76281476U";
        instance.setEndpoint(endpoint);
        assertEquals(endpoint, instance.getEndpoint());
    }

    /**
     * Test of getEndpointScheme method, of class Entity.
     */
    @Test
    public void testGetEndpointScheme() {
        System.out.println("getEndpointScheme");
        String expResult = "ES:VAT";
        String result = instance.getEndpointScheme();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndpointScheme method, of class Entity.
     */
    @Test
    public void testSetEndpointScheme() {
        System.out.println("setEndpointScheme");
        String endpointScheme = "ZZZ";
        instance.setEndpointScheme(endpointScheme);
        assertEquals(endpointScheme, instance.getEndpointScheme());
    }

    /**
     * Test of setBankInfo method, of class Entity.
     */
    @Test
    public void testSetBankInfo() {
        System.out.println("setBankInfo");
        BankInfo bankInfo = new BankInfo("1223454", "ES1232332", "AAAABBCCDDD", "123");
        instance.setBankInfo(bankInfo);
        assertEquals(bankInfo.getIban(), instance.getBankInfo().getIban());
    }
    
    /**
     * Test of getBankInfo method, of class Entity.
     */
    @Test
    public void testGetbankInfo() {
        System.out.println("getBankInfo");
        String expResult = "ES1232332";
        BankInfo bankInfo = new BankInfo("1223454", "ES1232332", "AAAABBCCDDD", "123");
        instance.setBankInfo(bankInfo);
        String result = instance.getBankInfo().getIban();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPostalAddress method, of class Entity.
     */
    @Test
    public void testSetPostalAddress() {
        System.out.println("setPostalAddress");
        PostalAddress postalAddress = new PostalAddress("easy street", "08080", "Barcelona", "ES");
        instance.setPostalAddress(postalAddress);
        assertEquals(postalAddress.getCity(), instance.getPostalAddress().getCity());
    }
    
    /**
     * Test of getpostalAddress method, of class Entity.
     */
    @Test
    public void testGetPostalAddress() {
        System.out.println("getPostalAddress");
        String expResult = "Barcelona";
        PostalAddress postalAddress = new PostalAddress("easy street", "08080", "Barcelona", "ES");
        instance.setPostalAddress(postalAddress);
        String result = instance.getPostalAddress().getCity();
        assertEquals(expResult, result);
    }    
    
}
