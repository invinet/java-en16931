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
        System.out.println("getMandateReferenceIdentifier");
        String expResult = "123";
        String result = instance.getMandateReferenceIdentifier();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMandate_reference_identifier method, of class BankInfo.
     */
    @Test
    public void testSetMandate_reference_identifier() {
        System.out.println("setMandateReferenceIdentifier");
        String mandateReferenceIdentifier = "321";
        instance.setMandateReferenceIdentifier(mandateReferenceIdentifier);
        assertEquals(mandateReferenceIdentifier, instance.getMandateReferenceIdentifier());
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void setBicIllegalArgument() {
        instance.setBic("1234");
    }
}
