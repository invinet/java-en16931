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
        this.url = String.format("http://localhost:3001/projects/%s/invoices/xml.json", project);
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
            System.out.println(response.toString());
            in=response.getEntity().getContent();
            String body = IOUtils.toString(in, Consts.UTF_8);
            System.out.println(body);
        } catch (ClientProtocolException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
