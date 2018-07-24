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
 *
 * @author jtorrents
 */
public class B2bRouter {

    String url;
    String apiKey;
    String project;
    Invoice invoice;

    public B2bRouter(String project, String apiKey, Invoice invoice) {
        this.apiKey = apiKey;
        this.project = project;
        this.url = String.format("https://app.b2brouter.net/projects/%s/invoices/xml.json", project);
        this.invoice = invoice;
    }

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
