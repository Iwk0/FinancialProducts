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
import bg.financialproducts.util.KeyBoard;
import bg.financialproducts.util.XMLParser;

public class MortgageLayout implements Layout, TextWatcher {

    private Activity activity;
    private LinearLayout root;
    private EditText loanAmountText, propertyValueText;

    public MortgageLayout(Activity activity) {
        this.activity = activity;

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.root = (LinearLayout) inflater.inflate(R.layout.main_layout, null);

        new ParseInformation(activity).execute();
    }

    private class ParseInformation extends AsyncTask<Void, Void, Void> {

        private Activity activity;
        private Resources resources;

        private List<Loan> loanPurposes, currency, loanTerm, loanType;

        private ParseInformation(Activity activity) {
            this.activity = activity;
            this.resources = activity.getResources();
        }

        @Override
        protected Void doInBackground(Void... params) {
            loanPurposes = XMLParser.parse(resources, resources.getString(R.string.loan_purpose), R.raw.mortgage_loans_loan_purpose);
            currency = XMLParser.parse(resources, resources.getString(R.string.currency), R.raw.consumer_loans_sp_currency);
            loanTerm = XMLParser.parse(resources, resources.getString(R.string.loan_term), R.raw.mortgage_loans_loan_term);
            loanType = XMLParser.parse(resources, resources.getString(R.string.loan_type), R.raw.mortgage_loans_loan_type);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Spinner loanPurposesSpinner = CreateView.spinner(activity, "SP_LoanPurpose", root, loanPurposes);
            Spinner currencySpinner = CreateView.spinner(activity, "SP_Currency", root, currency);
            Spinner loanTermSpinner = CreateView.spinner(activity, "SP_LoanTerm", root, loanTerm);
            Spinner loanTypeSpinner = CreateView.spinner(activity, "SP_MLLoanType", root, loanType);

            loanAmountText = CreateView.editText(activity, "SP_LoanAmount", resources.getString(R.string.loan_amount), root, MortgageLayout.this);
            propertyValueText = CreateView.editText(activity, "SP_PropertyValue", resources.getString(R.string.property_value), root, MortgageLayout.this);

            addViews(loanAmountText, propertyValueText, loanPurposesSpinner, currencySpinner, loanTermSpinner, loanTypeSpinner);

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
        loanAmountText.setTag("SP_LoanAmount");

        propertyValueText.setTextColor(Color.BLACK);
        propertyValueText.setTag("SP_PropertyValue");
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Resources resources = activity.getResources();
        CreateView.editTextValidation(loanAmountText, resources, 1000, 100000000);
        CreateView.editTextValidation(propertyValueText, resources, 1000, 100000000);
    }
}