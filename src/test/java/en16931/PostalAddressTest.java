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
public class PostalAddressTest {
    
    private PostalAddress instance = new PostalAddress("easy street", "08080", "Barcelona", "ES");
    
    /**
     * Test of getAddress method, of class PostalAddress.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        String expResult = "easy street";
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class PostalAddress.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "busy street";
        instance.setAddress(address);
        assertEquals(address, instance.getAddress());
    }

    /**
     * Test of getPostalZone method, of class PostalAddress.
     */
    @Test
    public void testGetPostalZone() {
        System.out.println("getPostalZone");
        String expResult = "08080";
        String result = instance.getPostalZone();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPostalZone method, of class PostalAddress.
     */
    @Test
    public void testSetPostalZone() {
        System.out.println("setPostalZone");
        String postalZone = "08776";
        instance.setPostalZone(postalZone);
        assertEquals(postalZone, instance.getPostalZone());
    }

    /**
     * Test of getCity method, of class PostalAddress.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        String expResult = "Barcelona";
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCity method, of class PostalAddress.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        String city = "Vilafranca";
        instance.setCity(city);
        assertEquals(city, instance.getCity());
    }

    /**
     * Test of getCountry method, of class PostalAddress.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        String expResult = "ES";
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCountry method, of class PostalAddress.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry");
        String country = "AD";
        instance.setCountry(country);
        assertEquals(country, instance.getCountry());
    }
    
}
