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

import bg.financialproducts.model.Settings;

public class HttpUtil {

    public static int sendGetRequest(Context context, List<NameValuePair> params, int loanType) throws IOException, ParserConfigurationException,
            SAXException, JSONException {
        Settings settings = new Database(context).findLastSettingsRecord();

        if (settings != null) {
            if (settings.isEmpty()) {
                return -1;
            }
        } else {
            return -1;
        }

        String paramString = URLEncodedUtils.format(params, "utf-8");
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(String.format("%s?u_=%s&c_=en-US&id_=%s&", settings.url, settings.username, settings.id) + paramString);
        HttpResponse response = client.execute(request);

        chooseLoanByType(context, loanType, response.getEntity().getContent());

        return response.getStatusLine().getStatusCode();
    }

    private static void chooseLoanByType(Context context, int loanType, InputStream stream) throws IOException, SAXException,
            ParserConfigurationException, JSONException {
        Database database = new Database(context);
        Gson gson = new Gson();

        switch (loanType) {
            case Constants.AUTO:
                database.insertLoan(gson.toJson(XMLParser.parseAuto(stream)), Constants.AUTO);
                break;
            case Constants.CONSUMER:
                database.insertLoan(gson.toJson(XMLParser.parseConsumers(stream)), Constants.CONSUMER);
                break;
            case Constants.CREDIT_CARDS:
                database.insertLoan(gson.toJson(XMLParser.parseCreditCards(stream)), Constants.CREDIT_CARDS);
                break;
            case Constants.DEPOSITS:
                database.insertLoan(gson.toJson(XMLParser.parseDeposits(stream)), Constants.DEPOSITS);
                break;
            case Constants.MORTGAGE:
                database.insertLoan(gson.toJson(XMLParser.parseMortgage(stream)), Constants.MORTGAGE);
                break;
        }
    }
}