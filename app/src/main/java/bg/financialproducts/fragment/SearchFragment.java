package bg.financialproducts.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import bg.financialproducts.GlobalVariable;
import bg.financialproducts.MainActivity;
import bg.financialproducts.R;
import bg.financialproducts.layout.Layout;
import bg.financialproducts.model.BannerSet;
import bg.financialproducts.model.Loan;
import bg.financialproducts.util.Factories;
import bg.financialproducts.util.HttpUtil;
import bg.financialproducts.util.Internet;
import bg.financialproducts.util.KeyBoard;
import bg.financialproducts.util.XMLParser;

public class SearchFragment extends Fragment {

    private Activity activity;
    private View view;
    private Spinner loansSpinner;
    private Layout oldLayout;
    private ProgressBar progressBar;
    private Button searchButton;
    private ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        activity = getActivity();
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        loansSpinner = (Spinner) view.findViewById(R.id.loans);

        final Resources resources = getResources();
        List<Loan> searchValues = new ArrayList<>();
        searchValues.add(new Loan("1", resources.getString(R.string.auto)));
        searchValues.add(new Loan("2", resources.getString(R.string.consumer)));
        searchValues.add(new Loan("3", resources.getString(R.string.credit_cards)));
        searchValues.add(new Loan("4", resources.getString(R.string.deposits)));
        searchValues.add(new Loan("5", resources.getString(R.string.mortgage)));

        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            loansSpinner.setBackgroundDrawable(resources.getDrawable(R.drawable.gradient_spinner));
        } else {
            loansSpinner.setBackground(resources.getDrawable(R.drawable.gradient_spinner));
        }

        loansSpinner.getBackground().setAlpha(180);

        ArrayAdapter<Loan> adapter = new ArrayAdapter<>(activity, R.layout.spinner_item, searchValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        loansSpinner.setAdapter(adapter);
        loansSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View child, int position, long id) {
                if (oldLayout != null) {
                    scrollView.removeView(oldLayout.getRootView());
                }

                Loan loan = (Loan) loansSpinner.getSelectedItem();
                oldLayout = Factories.createView(Integer.parseInt(loan.id), activity);
                scrollView.addView(oldLayout.getRootView());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bannerAvailable();

        searchButton = (Button) view.findViewById(R.id.search);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final List<NameValuePair> pairs = oldLayout.getAllViews();

                if (pairs != null) {
                    new AsyncTask<Void, Void, Integer>() {

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
                            progressBar.setVisibility(View.VISIBLE);
                            searchButton.setEnabled(false);
                        }

                        @Override
                        protected Integer doInBackground(Void... params) {
                            int code = 0;

                            try {
                                code = HttpUtil.sendGetRequest(activity, pairs, Integer.valueOf(((Loan) loansSpinner.getSelectedItem()).id));
                            } catch (IOException e) {
                                Log.e("IOException", e.getMessage());
                            } catch (ParserConfigurationException e) {
                                Log.e("ParserConfiguration", e.getMessage());
                            } catch (SAXException e) {
                                Log.e("SAXException", e.getMessage());
                            } catch (JSONException e) {
                                Log.e("JSONException", e.getMessage());
                            }

                            return code;
                        }

                        @Override
                        protected void onPostExecute(Integer code) {
                            super.onPostExecute(code);

                            if (code == 200) {
                                Loan loan = (Loan) loansSpinner.getSelectedItem();
                                Fragment newFragment = Factories.createFragment(Integer.valueOf(loan.id));
                                getFragmentManager().beginTransaction().replace(R.id.content_frame, newFragment).commit();
                                ((MainActivity) activity).selectItem(Integer.valueOf(loan.id) - 1);
                            } else if (code == -1) {
                                Toast.makeText(activity, getResources().getString(R.string.no_url_or_username),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(activity, getResources().getString(R.string.no_internet),
                                        Toast.LENGTH_SHORT).show();
                            }

                            searchButton.setEnabled(true);
                            progressBar.setVisibility(View.GONE);
                        }
                    }.execute();
                } else {
                    Toast.makeText(activity, getResources().getString(R.string.empty_fields),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        KeyBoard.hide(view, activity);

        return view;
    }

    private void bannerAvailable() {
        if (Internet.isConnected(activity)) {
            new AsyncTask<Void, Void, List<BannerSet>>() {

                @Override
                protected List<BannerSet> doInBackground(Void... voids) {
                    List<BannerSet> bannerSets = ((GlobalVariable) activity.getApplication()).getBannerSetList();
                    while (bannerSets == null) {
                        bannerSets = ((GlobalVariable) activity.getApplication()).getBannerSetList();
                    }

                    Log.i("Banner", "Banner set is ready for use");
                    return bannerSets;
                }

                @Override
                protected void onPostExecute(List<BannerSet> bannerSets) {
                    super.onPostExecute(bannerSets);

                    WebView webView = (WebView) view.findViewById(R.id.banner);
                    webView.getSettings().setJavaScriptEnabled(true);

                    String template = XMLParser.readHTMLTemplate(SearchFragment.this.getResources());

                    for (BannerSet bannerSet : bannerSets) {
                        if (bannerSet.name.contains("Home") && bannerSet.bottom != 0) {
                            template = template.replace("bannerSetValue", String.valueOf(bannerSet.bottom));
                            webView.loadData(template, "text/html", "utf-8");
                            webView.setVisibility(View.VISIBLE);
                            break;
                        }
                    }
                }
            }.execute();
        }
    }
}