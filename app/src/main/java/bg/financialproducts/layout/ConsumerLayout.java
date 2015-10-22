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

public class ConsumerLayout implements Layout, TextWatcher {

    private LinearLayout root;
    private EditText loanAmountText;
    private Activity activity;

    public ConsumerLayout(Activity activity) {
        this.activity = activity;

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.root = (LinearLayout) inflater.inflate(R.layout.main_layout, null);

        new ParseInformation(activity).execute();
    }

    private class ParseInformation extends AsyncTask<Void, Void, Void> {

        private Activity activity;
        private Resources resources;

        private List<Loan> typeOfTheLoans, currency, loanTermInMonths;

        public ParseInformation(Activity activity) {
            this.activity = activity;
            this.resources = activity.getResources();
        }

        @Override
        protected Void doInBackground(Void... params) {
            typeOfTheLoans = XMLParser.parse(resources, resources.getString(R.string.loan_type), R.raw.consumer_loans_sp_type_of_the_loan);
            currency = XMLParser.parse(resources, resources.getString(R.string.currency), R.raw.consumer_sp_currency);
            loanTermInMonths = XMLParser.parse(resources, resources.getString(R.string.loan_term), R.raw.consumer_loans_sp_loan_term);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Spinner typeOfLoanSpinner = CreateView.spinner(activity, "SP_LoanType", root, typeOfTheLoans);
            Spinner currencySpinner = CreateView.spinner(activity, "SP_Currency", root, currency);
            Spinner loanTermInMonthsSpinner = CreateView.spinner(activity, "SP_LoanTerm", root, loanTermInMonths);

            loanAmountText = CreateView.editText(activity, "SP_SelfParticipationAmount", resources.getString(R.string.loan_amount), root, ConsumerLayout.this);

            addViews(loanAmountText, typeOfLoanSpinner, currencySpinner, loanTermInMonthsSpinner);

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
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        CreateView.editTextValidation(loanAmountText, activity.getResources(), 100, 10000);
    }
}