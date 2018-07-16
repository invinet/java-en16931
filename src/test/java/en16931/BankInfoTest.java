/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package en16931;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jtorrents
 */
public class BankInfoTest {

    private BankInfo instance = new BankInfo("1234567", "ES661234567321", "AAAABBCCDDD", "123");
    
    /**
     * Test of getAccount method, of class BankInfo.
     */
    @Test
    public void testGetAccount() {
        System.out.println("getAccount");
        String expResult = "1234567";
        String result = instance.getAccount();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAccount method, of class BankInfo.
     */
    @Test
    public void testSetAccount() {
        System.out.println("setAccount");
        String account = "7654321";
        instance.setAccount(account);
        assertEquals(account, instance.getAccount());
    }

    /**
     * Test of getIban method, of class BankInfo.
     */
    @Test
    public void testGetIban() {
        System.out.println("getIban");
        String expResult = "ES661234567321";
        String result = instance.getIban();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIban method, of class BankInfo.
     */
    @Test
    public void testSetIban() {
        System.out.println("setIban");
        String iban = "ES123456789";
        instance.setIban(iban);
        assertEquals(iban, instance.getIban());
    }

    /**
     * Test of getBic method, of class BankInfo.
     */
    @Test
    public void testGetBic() {
        System.out.println("getBic");
        String expResult = "AAAABBCCDDD";
        String result = instance.getBic();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBic method, of class BankInfo.
     */
    @Test
    public void testSetBic() {
        System.out.println("setBic");
        String bic = "DDDDCCBBAAA";
        instance.setBic(bic);
        assertEquals(bic, instance.getBic());
    }

    /**
     * Test of getMandate_reference_identifier method, of class BankInfo.
     */
    @Test
    public void testGetMandate_reference_identifier() {
        System.out.println("getMandate_reference_identifier");
        String expResult = "123";
        String result = instance.getMandate_reference_identifier();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMandate_reference_identifier method, of class BankInfo.
     */
    @Test
    public void testSetMandate_reference_identifier() {
        System.out.println("setMandate_reference_identifier");
        String mandate_reference_identifier = "321";
        instance.setMandate_reference_identifier(mandate_reference_identifier);
        assertEquals(mandate_reference_identifier, instance.getMandate_reference_identifier());
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void setBicIllegalArgument() {
        instance.setBic("1234");
    }
}
