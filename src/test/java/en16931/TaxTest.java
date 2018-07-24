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
public class TaxTest {

    private Tax instance = new Tax(0.21, "S", "IVA", "comment");
    
    /**
     * Test of getPercent method, of class Tax.
     */
    @Test
    public void testGetPercent() {
        System.out.println("getPercent");
        double expResult = 0.21;
        double result = instance.getPercent();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPercent method, of class Tax.
     */
    @Test
    public void testSetPercent() {
        System.out.println("setPercent");
        double percent = 0.1;
        instance.setPercent(percent);
        assertEquals(percent, instance.getPercent(), 0.0);
    }

    @Test
    public void testSetPercent1() {
        System.out.println("setPercent");
        double percent = 10;
        instance.setPercent(percent);
        assertEquals(0.1, instance.getPercent(), 0.0);
    }

    /**
     * Test of getCategory method, of class Tax.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        String expResult = "S";
        String result = instance.getCategory();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCategory method, of class Tax.
     */
    @Test
    public void testSetCategory() {
        System.out.println("setCategory");
        String category = "AE";
        instance.setCategory(category);
        assertEquals(category, instance.getCategory());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetCategoryllegalArgument() {
        instance.setCategory("12");
    }

    /**
     * Test of getName method, of class Tax.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "IVA";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Tax.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "IRPF";
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getComment method, of class Tax.
     */
    @Test
    public void testGetComment() {
        System.out.println("getComment");
        String expResult = "comment";
        String result = instance.getComment();
        assertEquals(expResult, result);
    }

    /**
     * Test of setComment method, of class Tax.
     */
    @Test
    public void testSetComment() {
        System.out.println("setComment");
        String comment = "";
        instance.setComment(comment);
        assertEquals(comment, instance.getComment());
    }

    /**
     * Test of hashCode method, of class Tax.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = -1864798854;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Tax.
     */
    @Test
    public void testEqualsFalse() {
        System.out.println("equals");
        Object obj = new Tax(0.1, "AE", "IVA", "");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsTrue() {
        System.out.println("equals");
        Object obj = new Tax(0.21, "S", "IVA", "");
        assertTrue(instance.equals(obj));
    }
    
    @Test
    public void testEqualsFalseCategory() {
        System.out.println("equals");
        Object obj = new Tax(0.21, "AE", "IVA", "");
        assertFalse(instance.equals(obj));
    }
    
    @Test
    public void testEqualsName() {
        System.out.println("equals");
        Object obj = new Tax(0.21, "S", "VAT", "");
        assertFalse(instance.equals(obj));
    }

        
    @Test
    public void testEqualsNull() {
        System.out.println("equals");
        Object obj = null;
        assertFalse(instance.equals(obj));
    }

    /**
     * Test of getStringPercent method, of class Tax.
     */
    @Test
    public void testGetStringPercent() {
        System.out.println("getStringPercent");
        String expResult = "21.00";
        String result = instance.getStringPercent();
        assertEquals(expResult, result);
    }
}
