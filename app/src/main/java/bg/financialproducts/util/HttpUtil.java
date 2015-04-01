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
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import bg.financialproducts.model.BaseLoan;

public class HttpUtil {

    public static int sendGetRequest(List<NameValuePair> params, String loan) throws IOException {
        String paramString = URLEncodedUtils.format(params, "utf-8");
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://affiliate.finzoom.ro/default.aspx?u_=demo&c_=en-US&id_=cl-sr-xml" + paramString);
        HttpResponse response = client.execute(request);
        /*save results in database*/
        //TODO да запазя информацията в бази от данни и след това да я визуализирам в различнит фрагменти
/*        JSONObject json = new JSONObject();
        json.put("uniqueArrays", new JSONArray(items));
        String arrayList = json.toString();
        JSONObject json = new JSONObject(stringreadfromsqlite);
        ArrayList items = json.optJSONArray("uniqueArrays");*/
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

    private static List<BaseLoan> chooseLoanByName(String loan) throws IOException, SAXException, ParserConfigurationException {
        List<BaseLoan> loans = new ArrayList<>();

        switch (loan) {
            case Constants.AUTO:
                break;
            case Constants.CONSUMER:
                loans.addAll(XMLParser.parseConsumers(null));
                break;
        }

        return loans;
    }
}