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

public class MortgageLayout extends Layout implements TextWatcher {

    private EditText loanAmountText, propertyValueText;

    public MortgageLayout(Context context) {
        super(context);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 0);

        setOrientation(VERTICAL);
        setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        new ParseInformation(context, layoutParams, getResources()).execute();
    }

    private class ParseInformation extends AsyncTask<Void, Void, Void> {

        private Context context;
        private LayoutParams layoutParams;
        private Resources resources;

        private List<Loan> loanPurposes, currency, loanTerm, loanType;

        private ParseInformation(Context context, LayoutParams layoutParams, Resources resources) {
            this.context = context;
            this.layoutParams = layoutParams;
            this.resources = resources;
        }

        @Override
        protected Void doInBackground(Void... params) {
            loanPurposes = XMLParser.parse(resources, resources.getString(R.string.loan_purpose), R.raw.mortgage_loans_loan_purpose);
            currency = XMLParser.parse(resources, resources.getString(R.string.currency), R.raw.consumer_loans_sp_currency);
            loanTerm = XMLParser.parse(resources, resources.getString(R.string.loan_term), R.raw.mortgage_loans_loan_term);
            loanType = XMLParser.parse(resources, resources.getString(R.string.loan_type), R.raw.mortage_loans_loan_type);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Spinner loanPurposesSpinner = CreateView.spinner(context, "SP_LoanPurpose", layoutParams, loanPurposes);
            Spinner currencySpinner = CreateView.spinner(context, "SP_Currency", layoutParams, currency);
            Spinner loanTermSpinner = CreateView.spinner(context, "SP_LoanTerm", layoutParams, loanTerm);
            Spinner loanTypeSpinner = CreateView.spinner(context, "SP_MLLoanType", layoutParams, loanType);

            loanAmountText = CreateView.editText(context, "SP_LoanAmount", resources.getString(R.string.loan_amount), layoutParams, MortgageLayout.this);
            propertyValueText = CreateView.editText(context, "SP_PropertyValue", resources.getString(R.string.property_value), layoutParams, MortgageLayout.this);

            addViews(loanAmountText, propertyValueText, loanPurposesSpinner, currencySpinner, loanTermSpinner, loanTypeSpinner);

            KeyBoard.hide(MortgageLayout.this, (Activity) context);
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

        propertyValueText.setTextColor(Color.BLACK);
        propertyValueText.setTag("SP_PropertyValue");
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Resources resources = getResources();
        CreateView.editTextValidation(loanAmountText, resources, 1000, 100000000);
        CreateView.editTextValidation(propertyValueText, resources, 1000, 100000000);
    }
}