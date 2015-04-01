package bg.financialproducts.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import bg.financialproducts.R;
import bg.financialproducts.layout.Layout;
import bg.financialproducts.model.Loan;
import bg.financialproducts.util.Constants;
import bg.financialproducts.util.Factories;
import bg.financialproducts.util.HttpUtil;
import bg.financialproducts.util.KeyBoard;

public class SearchFragment extends Fragment {

    private Activity activity;
    private View view;
    private Spinner loansSpinner;
    private Layout oldLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        List<Loan> searchValues = new ArrayList<>();
        searchValues.add(new Loan("1", Constants.AUTO));
        searchValues.add(new Loan("2", Constants.CONSUMER));
        searchValues.add(new Loan("3", Constants.CREDIT_CARDS));
        searchValues.add(new Loan("4", Constants.DEPOSITS));
        searchValues.add(new Loan("5", Constants.MORTGAGE));

        activity = getActivity();
        loansSpinner = (Spinner) view.findViewById(R.id.loans);
        ArrayAdapter<Loan> adapter = new ArrayAdapter<>(activity,
                R.layout.spinner_item, searchValues);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loansSpinner.setAdapter(adapter);
        loansSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View child, int position, long id) {
                if (oldLayout != null) {
                    ((LinearLayout) view).removeView(oldLayout);
                }

                Loan loan = (Loan) loansSpinner.getSelectedItem();
                Factories factories = new Factories();
                oldLayout = factories.createView(Integer.parseInt(loan.id), activity);
                ((LinearLayout) view).addView(oldLayout);

                KeyBoard.hide(oldLayout, getActivity());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button searchButton = (Button) view.findViewById(R.id.search);

        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final List<NameValuePair> pairs = oldLayout.getAllViews();

                if (pairs != null) {
                    new AsyncTask<Void, Void, Integer>() {

                        @Override
                        protected Integer doInBackground(Void... params) {
                            int code = 0;

                            try {
                                code = HttpUtil.sendGetRequest(activity, pairs,
                                        ((Loan) loansSpinner.getSelectedItem()).value);
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
                                Fragment newFragment =
                                        Factories.createFragment(Integer.valueOf(loan.id), activity);
                                getFragmentManager().
                                        beginTransaction().
                                        replace(R.id.content_frame, newFragment).commit();
                            } else {
                                Toast.makeText(activity, getResources().getString(R.string.no_internet),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }.execute();
                } else {
                    Toast.makeText(activity, getResources().getString(R.string.empty_fields),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}