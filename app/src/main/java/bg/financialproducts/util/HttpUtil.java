package bg.financialproducts.util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.List;

public class HttpUtil {

    public static void sendGetRequest(List<NameValuePair> params) throws IOException {
        String paramString = URLEncodedUtils.format(params, "utf-8");
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://affiliate.finzoom.ro/default.aspx?u_=demo&c_=en-US&id_=cl-sr-xml" + paramString);
        HttpResponse response = client.execute(request);

        /*
        * String line = "";
while ((line = rd.readLine()) != null) {
  textView.append(line);
}
*/
    }
}