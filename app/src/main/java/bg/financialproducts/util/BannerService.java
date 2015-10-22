package bg.financialproducts.util;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import bg.financialproducts.GlobalVariable;

public class BannerService extends IntentService {

    public BannerService() {
        super("Banner");
    }

    private void getBannerSet(final String url) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            ((GlobalVariable) BannerService.this.getApplication()).
                    setBannerSetList(XMLParser.parseBannerSet(response.getEntity().getContent()));
            //TODO запазване в базата
        } catch (IOException e) {
            Log.e("IOException", e.getMessage());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Log.e("InterruptedException", ex.getMessage());
            }
        } catch (ParserConfigurationException e) {
            Log.e("ParserConfiguration", e.getMessage());
        } catch (SAXException e) {
            Log.e("SAXException", e.getMessage());
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            do {
                getBannerSet("http://affiliate.finzoom.ro/mobile/banners.xml");
            } while (!Internet.isConnected(this));
        }
    }
}