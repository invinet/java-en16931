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

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;

/**
 * Post an invoice to <a href="https://www.b2brouter.net/">b2brouter.net</a>
 * via its REST API.
 * 
 * You need to have an active project in the plataform that has permisions
 * to upload invoices via REST API.
 */
public class B2bRouter {

    String url;
    String apiKey;
    String project;
    Invoice invoice;

    /**
     *
     * @param project your project ID to which upload the invoice
     * @param apiKey the API key associated with that project
     * @param invoice an Invoice
     */
    public B2bRouter(String project, String apiKey, Invoice invoice) {
        this.apiKey = apiKey;
        this.project = project;
        this.url = String.format("https://app.b2brouter.net/projects/%s/invoices/xml.json", project);
        this.invoice = invoice;
    }

    /**
     * Posts an invoice to <a href="https://www.b2brouter.net/">b2brouter.net</a>
     *
     * @throws UnsupportedEncodingException
     */
    public void postToB2bRouter() throws UnsupportedEncodingException {
        HttpClient client = HttpClients.createDefault();
        InputStream in;
        String inputXML = invoice.toXml();
        HttpEntity entity = new ByteArrayEntity(inputXML.getBytes("UTF-8"));
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("content-type" , "application/octet-stream");
        httpPost.addHeader("X-Redmine-API-Key" , apiKey);
        httpPost.setEntity(entity);
        
        try {
            HttpResponse response = client.execute(httpPost);
            in=response.getEntity().getContent();
            String body = IOUtils.toString(in, Consts.UTF_8);
        } catch (ClientProtocolException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
