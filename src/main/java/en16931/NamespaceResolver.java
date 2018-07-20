/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package en16931;

import java.util.Iterator;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import org.w3c.dom.Document;

/**
 * From https://howtodoinjava.com/xml/xpath-namespace-resolution-example/
 */
public class NamespaceResolver implements NamespaceContext
{
    //Store the source document to search the namespaces
    private Document sourceDocument;
 
    public NamespaceResolver(Document document) {
        sourceDocument = document;
    }
 
    //The lookup for the namespace uris is delegated to the stored document.
    @Override
    public String getNamespaceURI(String prefix) {
        switch (prefix) {
            case XMLConstants.DEFAULT_NS_PREFIX:
                return sourceDocument.lookupNamespaceURI(null);
            case "df":
                return "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2";
            default:
                return sourceDocument.lookupNamespaceURI(prefix);
        }
    }
 
    @Override
    public String getPrefix(String namespaceURI) {
        return sourceDocument.lookupPrefix(namespaceURI);
    }
 
    @SuppressWarnings("rawtypes")
    @Override
    public Iterator getPrefixes(String namespaceURI) {
        return null;
    }
}