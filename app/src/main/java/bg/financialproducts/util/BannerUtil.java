package bg.financialproducts.util;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import java.util.List;

import bg.financialproducts.GlobalVariable;
import bg.financialproducts.R;
import bg.financialproducts.model.BannerSet;

public class BannerUtil {

    public static AsyncTask<Void, Void, List<BannerSet>> available(final Activity activity, final View view, final String screen, final String area) {
        return new AsyncTask<Void, Void, List<BannerSet>>() {

            @Override
            protected List<BannerSet> doInBackground(Void... voids) {
                List<BannerSet> bannerSets;

                do {
                    bannerSets = ((GlobalVariable) activity.getApplication()).getBannerSetList();
                } while (bannerSets == null);

                Log.i("Banner", "Banner set is ready for use");
                return bannerSets;
            }

            @Override
            protected void onPostExecute(List<BannerSet> bannerSets) {
                super.onPostExecute(bannerSets);

                WebView webView = (WebView) view.findViewById(R.id.banner);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setVisibility(View.VISIBLE);

                String template = XMLParser.readHTMLTemplate(activity.getResources());

                for (BannerSet bannerSet : bannerSets) {
                    if (bannerSet.name.contains(screen)) {
                        int value = chooseArea(bannerSet, area);
                        if (value != 0) {
                            template = template.replace("bannerSetValue", String.valueOf(value));
                            webView.loadData(template, "text/html", "utf-8");
                        }

                        break;
                    }
                }
            }
        };
    }

    private static int chooseArea(BannerSet bannerSet, String area) {
        int value;

        switch (area) {
            case Constants.BOTTOM :
                value = bannerSet.bottom;
                break;
            case Constants.SEARCH :
                value = bannerSet.search;
                break;
            case Constants.DETAILS :
                value = bannerSet.details;
                break;
            case Constants.REPAYMENT :
                value = bannerSet.repayment;
                break;
            case Constants.RESULTS :
                value = bannerSet.results;
                break;
            default:
                value = 0;
                break;
        }

        return value;
    }
}
