package bg.financialproducts.layout;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import org.apache.http.NameValuePair;

import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.model.Loan;
import bg.financialproducts.util.CreateView;
import bg.financialproducts.util.DisableSpinner;
import bg.financialproducts.util.KeyBoard;
import bg.financialproducts.util.XMLParser;

public class AutoLayout implements Layout, TextWatcher {

    private Activity activity;
    private LinearLayout root;
    private EditText loanAmountText, carPriceText;

    public AutoLayout(Activity activity) {
        this.activity = activity;

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.root = (LinearLayout) inflater.inflate(R.layout.main_layout, null);

        new ParseInformation(activity).execute();
    }

    private class ParseInformation extends AsyncTask<Void, Void, Void> {

        private Activity activity;
        private Resources resources;

        private List<Loan> carType, aLLoanTypes, ageOfCars, residualValue, currency, loanTerm;

        public ParseInformation(Activity activity) {
            this.activity = activity;
            this.resources = activity.getResources();
        }

        @Override
        protected Void doInBackground(Void... params) {
            carType = XMLParser.parse(resources, resources.getString(R.string.car_type), R.raw.auto_loan_sp_car_type);
            aLLoanTypes = XMLParser.parse(resources, resources.getString(R.string.loan_or_a_leasing), R.raw.auto_loan_sp_alloan_type);
            ageOfCars = XMLParser.parse(resources, resources.getString(R.string.car_age), R.raw.auto_loan_sp_age_of_car);
            residualValue = XMLParser.parse(resources, resources.getString(R.string.residual_value), R.raw.auto_loan_sp_residual_value);
            currency = XMLParser.parse(resources, resources.getString(R.string.currency), R.raw.consumer_loans_sp_currency);
            loanTerm = XMLParser.parse(resources, resources.getString(R.string.loan_term), R.raw.auto_loan_sp_loan_term);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Spinner carTypeSpinner = CreateView.spinner(activity, "SP_LoanType", root, carType);
            Spinner currencySpinner = CreateView.spinner(activity, "SP_Currency", root, currency);
            Spinner loanTermInMonthsSpinner = CreateView.spinner(activity, "SP_LoanTerm", root, loanTerm);
            Spinner aLLoanTypeSpinner = CreateView.spinner(activity, "SP_ALLoanType", root, aLLoanTypes);
            Spinner ageOfCarsSpinner = CreateView.spinner(activity, "SP_AgeOfCar", root, ageOfCars);
            Spinner residualValueSpinner = CreateView.spinner(activity, "SP_ResidualValue_Input", root, residualValue);

            aLLoanTypeSpinner.setOnItemSelectedListener(new DisableSpinner(residualValueSpinner));
            carTypeSpinner.setOnItemSelectedListener(new DisableSpinner(ageOfCarsSpinner));

            loanAmountText = CreateView.editText(activity, "SP_SelfParticipationAmount", resources.getString(R.string.loan_amount), root, AutoLayout.this);
            carPriceText = CreateView.editText(activity, "SP_CarPrice", resources.getString(R.string.car_price), root, AutoLayout.this);

            addViews(loanAmountText, carPriceText, carTypeSpinner, currencySpinner, loanTermInMonthsSpinner,
                    aLLoanTypeSpinner, ageOfCarsSpinner, residualValueSpinner);

            KeyBoard.hide(root, activity);
        }
    }

    private void addViews(View... localViews) {
        for (View view : localViews) {
            root.addView(view);
        }
    }

    @Override
    public View getRootView() {
        return root;
    }

    @Override
    public List<NameValuePair> getAllViews() {
        return CreateView.allFieldsAreRequired(root);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        loanAmountText.setTextColor(Color.BLACK);
        loanAmountText.setTag("SP_SelfParticipationAmount");

        carPriceText.setTextColor(Color.BLACK);
        carPriceText.setTag("SP_CarPrice");
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Resources resources = activity.getResources();
        CreateView.editTextValidation(loanAmountText, resources, 500, 5000000);
        CreateView.editTextValidation(carPriceText, resources, 500, 5000000);
    }
}