package en16931;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author jtorrents
 */
public class NamespaceResolverTest {
    
    NamespaceResolver instance;
    
    public NamespaceResolverTest() throws SAXException, IOException, ParserConfigurationException {
        File file = new File("src/test/resources/files/invoice_with_charges.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        instance = new NamespaceResolver(document);
    }

    /**
     * Test of getNamespaceURI method, of class NamespaceResolver.
     */
    @Test
    public void testGetDefaultNamespaceURI() {
        System.out.println("getDefaultNamespaceURI");
        String prefix = "df";
        String expResult = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2";
        String result = instance.getNamespaceURI(prefix);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrefix method, of class NamespaceResolver.
     */
    @Test
    public void testGetPrefix() {
        System.out.println("getPrefix");
        String namespaceURI = "";
        String expResult = null;
        String result = instance.getPrefix(namespaceURI);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrefixes method, of class NamespaceResolver.
     */
    @Test
    public void testGetPrefixes() {
        System.out.println("getPrefixes");
        String namespaceURI = "";
        Iterator expResult = null;
        Iterator result = instance.getPrefixes(namespaceURI);
        assertEquals(expResult, result);
    }
    
}
