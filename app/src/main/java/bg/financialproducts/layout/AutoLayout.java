package bg.financialproducts.layout;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.http.NameValuePair;

import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.model.Loan;
import bg.financialproducts.util.CreateView;
import bg.financialproducts.util.KeyBoard;
import bg.financialproducts.util.XMLParser;

public class AutoLayout extends Layout implements TextWatcher {

    private EditText loanAmountText, carPriceText;

    public AutoLayout(Context context) {
        super(context);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 0);

        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        ParseInformation parseInformation = new ParseInformation(context, getResources(), layoutParams);
        parseInformation.execute();
    }

    private class ParseInformation extends AsyncTask<Void, Void, Void> {

        private Context context;
        private LayoutParams layoutParams;
        private Resources resources;

        private List<Loan> carType, aLLoanTypes, ageOfCars, residualValue, currency, loanTerm;

        public ParseInformation(Context context, Resources resources, LayoutParams layoutParams) {
            this.context = context;
            this.layoutParams = layoutParams;
            this.resources = resources;
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

            Spinner carTypeSpinner = CreateView.spinner(context, "SP_LoanType", layoutParams, carType);
            Spinner currencySpinner = CreateView.spinner(context, "SP_Currency", layoutParams, currency);
            Spinner loanTermInMonthsSpinner = CreateView.spinner(context, "SP_LoanTerm", layoutParams, loanTerm);
            Spinner aLLoanTypeSpinner = CreateView.spinner(context, "SP_ALLoanType", layoutParams, aLLoanTypes);
            Spinner ageOfCarsSpinner = CreateView.spinner(context, "SP_AgeOfCar", layoutParams, ageOfCars);
            Spinner residualValueSpinner = CreateView.spinner(context, "SP_ResidualValue_Input", layoutParams, residualValue);

            aLLoanTypeSpinner.setOnItemSelectedListener(new DisableSpinner(residualValueSpinner));
            carTypeSpinner.setOnItemSelectedListener(new DisableSpinner(ageOfCarsSpinner));

            loanAmountText = CreateView.editText(context, "SP_SelfParticipationAmount", resources.getString(R.string.loan_amount), layoutParams, AutoLayout.this);
            carPriceText = CreateView.editText(context, "SP_CarPrice", resources.getString(R.string.car_price), layoutParams, AutoLayout.this);

            addViews(loanAmountText, carPriceText, carTypeSpinner, currencySpinner, loanTermInMonthsSpinner,
                    aLLoanTypeSpinner, ageOfCarsSpinner, residualValueSpinner);

            KeyBoard.hide(AutoLayout.this, (Activity) context);
        }
    }

    private class DisableSpinner implements AdapterView.OnItemSelectedListener {

        private Spinner spinner;

        public DisableSpinner(Spinner spinner) {
            this.spinner = spinner;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Loan loan = (Loan) parent.getSelectedItem();
            if (loan.id.equals("1")) {
                spinner.setEnabled(false);
                spinner.setSelection(0);
            } else {
                spinner.setEnabled(true);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void addViews(View... localViews) {
        for (View view : localViews) {
            addView(view);
        }
    }

    @Override
    public List<NameValuePair> getAllViews() {
        return CreateView.allFieldsAreRequired(this);
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
        Resources resources = getResources();
        CreateView.editTextValidation(loanAmountText, resources, 500, 5000000);
        CreateView.editTextValidation(carPriceText, resources, 500, 5000000);
    }
}