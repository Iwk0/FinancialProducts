package bg.financialproducts.fragment;

import android.app.Activity;
import android.app.Fragment;
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

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.layout.Layout;
import bg.financialproducts.model.Loan;
import bg.financialproducts.util.HttpUtil;
import bg.financialproducts.util.ViewFactory;

public class SearchFragment extends Fragment {

    private Activity activity;
    private View view;
    private Spinner loansSpinner;
    private Layout oldLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        List<Loan> searchValues = new ArrayList<>();
        searchValues.add(new Loan(1, "Auto"));
        searchValues.add(new Loan(2, "Consumer"));
        searchValues.add(new Loan(3, "Credit card"));
        searchValues.add(new Loan(4, "Deposits"));
        searchValues.add(new Loan(5, "Mortgage"));

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
                ViewFactory viewFactory = new ViewFactory();
                Layout layout = viewFactory.createView(/*loan.id*/2, activity);
                oldLayout = layout;
                ((LinearLayout) view).addView(oldLayout);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button searchButton = (Button) view.findViewById(R.id.search);

        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                List<NameValuePair> pairs = oldLayout.getAllViews();
                if (!pairs.isEmpty()) {
                    try {
                        HttpUtil.sendGetRequest(pairs);
                    } catch (IOException e) {
                        Log.e("IOException", e.getMessage());
                    }
                } else {
                    /*empty fields message*/
                }


                /*
                * create build factory fragment
                * open some fragment
                *
                * */
            }
        });

        return view;
    }
}