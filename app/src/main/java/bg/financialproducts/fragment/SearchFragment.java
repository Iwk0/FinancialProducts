package bg.financialproducts.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.layout.Layout;
import bg.financialproducts.model.BannerSet;
import bg.financialproducts.util.DataHolderClass;
import bg.financialproducts.util.Factories;
import bg.financialproducts.util.KeyBoard;

public class SearchFragment extends Fragment {

    private AsyncTask<Void, Void, List<BannerSet>> asyncTask;
    private Activity activity;
    private View view;
    /*private Spinner loansSpinner;
    private Layout oldLayout;*/
    private ProgressBar progressBar;
    //private Button searchButton;
    /*    private int oldLayoutIndex;*/

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        activity = getActivity();
        ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        //Button searchButton = (Button) view.findViewById(R.id.search);

        Button searchButton = (Button) activity.getLayoutInflater().inflate(R.layout.search_button, (ViewGroup) view, false);

        /*            <Button
                android:id="@+id/search"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:textColor="#fff"
                android:background="@drawable/shadow"
                android:text="@string/search" />*/
        //loansSpinner = (Spinner) view.findViewById(R.id.loans);

        //final Resources resources = getResources();
        /*List<Loan> searchValues = new ArrayList<>();
        searchValues.add(new Loan("1", resources.getString(R.string.auto)));
        searchValues.add(new Loan("2", resources.getString(R.string.consumer)));
        searchValues.add(new Loan("3", resources.getString(R.string.credit_cards)));
        searchValues.add(new Loan("4", resources.getString(R.string.deposits)));
        searchValues.add(new Loan("5", resources.getString(R.string.mortgage)));*/

        DataHolderClass<Integer> dataHolderClass = DataHolderClass.getInstance();
        int layoutId = dataHolderClass.getItem();
        Layout layout = Factories.createView(layoutId, activity);
        ((ViewGroup) layout.getRootView()).addView(searchButton);

        scrollView.addView(layout.getRootView());

/*        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            loansSpinner.setBackgroundDrawable(resources.getDrawable(R.drawable.gradient_spinner));
        } else {
            loansSpinner.setBackground(resources.getDrawable(R.drawable.gradient_spinner));
        }

        loansSpinner.getBackground().setAlpha(180);*/

        /*ArrayAdapter<Loan> adapter = new ArrayAdapter<>(activity, R.layout.spinner_item, searchValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        loansSpinner.setAdapter(adapter);
        loansSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View child, int position, long id) {
                if (oldLayoutIndex != position) {
                    scrollView.removeView(oldLayout.getRootView());
                    Loan loan = (Loan) parent.getSelectedItem();
                    oldLayout = Factories.createView(Integer.parseInt(loan.id), activity);
                    scrollView.addView(oldLayout.getRootView());
                    oldLayoutIndex = position;

                    if (Internet.isConnected(activity)) {
                        if (asyncTask != null && !asyncTask.isCancelled()) {
                            asyncTask.cancel(true);
                        }

                        asyncTask = BannerUtil.available(activity, view, loan.value, Constants.SEARCH);
                        asyncTask.execute();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        /*Loan loan = (Loan) loansSpinner.getSelectedItem();
        oldLayoutIndex = loansSpinner.getSelectedItemPosition();
        oldLayout = Factories.createView(Integer.parseInt(loan.id), activity);
        scrollView.addView(oldLayout.getRootView());*/

        /*Banner zone*/
/*        if (Internet.isConnected(activity)) {
            asyncTask = BannerUtil.available(activity, view, "AutoLoan", Constants.SEARCH);
            asyncTask.execute();
        }*/

        /*searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final List<NameValuePair> pairs = oldLayout.getAllViews();

                if (!pairs.isEmpty()) {
                    new AsyncTask<Integer, Void, Integer>() {

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
                            progressBar.setVisibility(View.VISIBLE);
                            //searchButton.setEnabled(false);
                        }

                        @Override
                        protected Integer doInBackground(Integer... params) {
                            int code = 0;

                            try {
                                code = HttpUtil.sendGetRequest(activity, pairs, params[0]);
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

                                //TODO ако няма намерен резултат да не ме пренасочва, а ми да оставя в същия фрагмент
                            } else if (code == -1) {
                                Toast.makeText(activity, getResources().getString(R.string.no_url_or_username),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(activity, getResources().getString(R.string.no_internet),
                                        Toast.LENGTH_SHORT).show();
                            }

                            //searchButton.setEnabled(true);
                            progressBar.setVisibility(View.GONE);
                        }
                    }.execute(Integer.valueOf(((Loan) loansSpinner.getSelectedItem()).id));
                } else {
                    Toast.makeText(activity, getResources().getString(R.string.empty_fields),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        KeyBoard.hide(view, activity);

        return view;
    }

    @Override
    public void onPause() {
        if (asyncTask != null && !asyncTask.isCancelled()) {
            asyncTask.cancel(true);
        }
        super.onPause();
    }
}