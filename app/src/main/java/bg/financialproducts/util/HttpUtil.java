package bg.financialproducts.util;

import android.content.Context;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class HttpUtil {

    public static int sendGetRequest(Context context, List<NameValuePair> params, String loan)
            throws IOException, ParserConfigurationException, SAXException, JSONException {
        String paramString = URLEncodedUtils.format(params, "utf-8");
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(
                "http://affiliate.finzoom.ro/default.aspx?u_=demo&c_=en-US&id_=cl-sr-xml" + paramString);
        HttpResponse response = client.execute(request);
        /*save results in database*/
        //TODO да запазя информацията в бази от данни и след това да я визуализирам в различнит фрагменти
/*        JSONObject json = new JSONObject();
        json.put("uniqueArrays", new JSONArray(items));
        String arrayList = json.toString();
        JSONObject json = new JSONObject(stringreadfromsqlite);
        ArrayList items = json.optJSONArray("uniqueArrays");*/
        chooseLoanByName(context, loan, response.getEntity().getContent());

        return response.getStatusLine().getStatusCode();
    }

    private static void chooseLoanByName(Context context, String loan, InputStream stream)
            throws IOException, SAXException, ParserConfigurationException, JSONException {
        LoansDAO loansDAO = new LoansDAO(context);
        JSONObject json = new JSONObject();

        switch (loan) {
            case Constants.AUTO:
                break;
            case Constants.CONSUMER:
                json.put(Constants.LOAN, new JSONArray(XMLParser.parseConsumers(stream)));
                loansDAO.insertLoan(json.toString(), Constants.CONSUMER);
                break;
            case Constants.CREDIT_CARDS:
                break;
            case Constants.DEPOSITS:
                break;
            default:
                break;
        }
    }
}