package bg.financialproducts.util;

import android.content.Context;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class HttpUtil {

    public static int sendGetRequest(Context context, List<NameValuePair> params, String loanType)
            throws IOException, ParserConfigurationException, SAXException, JSONException {
        String paramString = URLEncodedUtils.format(params, "utf-8");
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(
                "http://affiliate.finzoom.ro/default.aspx?u_=demo&c_=en-US&id_=cl-sr-xml" + paramString);
        HttpResponse response = client.execute(request);

        chooseLoanByType(context, loanType, response.getEntity().getContent());

        return response.getStatusLine().getStatusCode();
    }

    private static void chooseLoanByType(Context context, String loanType, InputStream stream)
            throws IOException, SAXException, ParserConfigurationException, JSONException {
        LoansDAO loansDAO = new LoansDAO(context);
        Gson gson = new Gson();

        switch (loanType) {
            case Constants.AUTO:
                loansDAO.insertLoan(gson.toJson(XMLParser.parseConsumers(stream)), Constants.AUTO);
                break;
            case Constants.CONSUMER:
                loansDAO.insertLoan(gson.toJson(XMLParser.parseConsumers(stream)), Constants.CONSUMER);
                break;
            case Constants.CREDIT_CARDS:
                loansDAO.insertLoan(gson.toJson(XMLParser.parseConsumers(stream)), Constants.CREDIT_CARDS);
                break;
            case Constants.DEPOSITS:
                loansDAO.insertLoan(gson.toJson(XMLParser.parseConsumers(stream)), Constants.DEPOSITS);
                break;
            default:
                loansDAO.insertLoan(gson.toJson(XMLParser.parseConsumers(stream)), Constants.MORTGAGE);
                break;
        }
    }
}