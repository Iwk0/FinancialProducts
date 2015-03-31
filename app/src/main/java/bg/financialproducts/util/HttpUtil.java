package bg.financialproducts.util;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class HttpUtil {

    public static int sendGetRequest(List<NameValuePair> params, int loansId) throws IOException {
        String paramString = URLEncodedUtils.format(params, "utf-8");
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://affiliate.finzoom.ro/default.aspx?u_=demo&c_=en-US&id_=cl-sr-xml" + paramString);
        HttpResponse response = client.execute(request);
        /*save results in database*/
       // if (loansId == 1) {
            try {
                XMLParser.parseConsumers(response.getEntity().getContent());
            } catch (ParserConfigurationException e) {
                Log.e("ParserException", e.getMessage());
            } catch (SAXException e) {
                Log.e("SAXException", e.getMessage());
            }
        //}

        return response.getStatusLine().getStatusCode();
    }
}