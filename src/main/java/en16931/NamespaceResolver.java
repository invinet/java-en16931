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