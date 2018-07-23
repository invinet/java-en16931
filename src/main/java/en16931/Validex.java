package en16931;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLContext;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author jtorrents
 */
public class Validex {
    
    String url;
    String apiKey;
    String userId;
    Invoice invoice;

    public Validex(String userId, String apiKey, Invoice invoice) {
        this.apiKey = apiKey;
        this.userId = userId;
        this.url = "https://api2.validex.net/api/validate";
        this.invoice = invoice;
    }

    public boolean isValidatValidex() throws UnsupportedEncodingException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        //HttpClient client = HttpClients.createDefault();
        SSLContext sslContext = new SSLContextBuilder()
            .loadTrustMaterial(null, (certificate, authType) -> true).build();
        CloseableHttpClient client = HttpClients
            .custom()
            .setSSLContext(sslContext)
            .setSSLHostnameVerifier(new NoopHostnameVerifier())
            .build();
        InputStream in;
        String inputXML = invoice.toXml();
        String encoded = Base64.getEncoder().encodeToString(inputXML.getBytes("UTF-8"));
        Map<String, String> params = new HashMap();
        params.put("userId", userId);
        long date = new Date().getTime();
        params.put("filename", String.valueOf(date));
        params.put("fileContents64", encoded);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(params);
        StringEntity entity =  new StringEntity(json,  ContentType.APPLICATION_JSON);
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("content_type" , "json");
        httpPost.addHeader("accept" , "json");
        httpPost.addHeader("Authorization" , String.format("apikey=%s",apiKey));
        httpPost.setEntity(entity);
        
        try {
            HttpResponse response = client.execute(httpPost);
            in=response.getEntity().getContent();
            System.out.println(in);
            String body = IOUtils.toString(in, Consts.UTF_8);
            System.out.println(body);
            System.out.println(response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                JsonParser parser = new JsonParser();
                JsonObject result = parser.parse(body).getAsJsonObject();
                String veredict = null;
                if (result.getAsJsonObject("report") != null) {
                    veredict = result.getAsJsonObject("report").get("result").getAsString();
                } else {
                    veredict = null;
                }
                return veredict != null && !"fatal".equals(veredict);
            } else {
                return false;
            }
        } catch (ClientProtocolException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }
}