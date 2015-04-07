package bg.financialproducts.layout;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.http.NameValuePair;

import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.model.Loan;
import bg.financialproducts.util.CreateView;
import bg.financialproducts.util.KeyBoard;
import bg.financialproducts.util.XMLParser;

public class ConsumerLayout extends Layout implements TextWatcher {

    private EditText loanAmountText;

    public ConsumerLayout(Context context) {
        super(context);

        LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 0);

        setOrientation(VERTICAL);
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        new ParseInformation(context, getResources(), layoutParams).execute();
    }

    private class ParseInformation extends AsyncTask<Void, Void, Void> {

        private Context context;
        private LayoutParams layoutParams;
        private Resources resources;

        private List<Loan> typeOfTheLoans, currency, loanTermInMonths;

        public ParseInformation(Context context, Resources resources, LayoutParams layoutParams) {
            this.context = context;
            this.layoutParams = layoutParams;
            this.resources = resources;
        }

        @Override
        protected Void doInBackground(Void... params) {
            typeOfTheLoans = XMLParser.parse(resources, resources.getString(R.string.loan_type), R.raw.consumer_loans_type_of_the_loan);
            currency = XMLParser.parse(resources, resources.getString(R.string.currency), R.raw.consumer_loans_sp_currency);
            loanTermInMonths = XMLParser.parse(resources, resources.getString(R.string.loan_term), R.raw.consumer_loans_loan_term);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Spinner typeOfLoanSpinner = CreateView.spinner(context, "SP_LoanType", layoutParams, typeOfTheLoans);
            Spinner currencySpinner = CreateView.spinner(context, "SP_Currency", layoutParams, currency);
            Spinner loanTermInMonthsSpinner = CreateView.spinner(context, "SP_LoanTerm", layoutParams, loanTermInMonths);

            loanAmountText = CreateView.editText(context, "SP_SelfParticipationAmount", resources.getString(R.string.loan_amount), layoutParams, ConsumerLayout.this);

            addViews(loanAmountText, typeOfLoanSpinner, currencySpinner, loanTermInMonthsSpinner);

            KeyBoard.hide(ConsumerLayout.this, (Activity) context);
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
        loanAmountText.setTag("SP_LoanAmount");
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        CreateView.editTextValidation(loanAmountText, getResources(), 100, 10000);
    }
}