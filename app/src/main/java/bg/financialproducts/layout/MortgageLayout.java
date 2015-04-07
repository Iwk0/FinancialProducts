package bg.financialproducts.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
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
import bg.financialproducts.util.XMLParser;

public class MortgageLayout extends Layout implements TextWatcher {

    private EditText loanAmountText, propertyValueText;

    public MortgageLayout(Context context) {
        super(context);

        Resources resources = getResources();
        List<Loan> loanPurposes = XMLParser.parse(resources, resources.getString(R.string.loan_purpose), R.raw.mortgage_loans_loan_purpose);
        List<Loan> currency = XMLParser.parse(resources, resources.getString(R.string.currency), R.raw.consumer_loans_sp_currency);
        List<Loan> loanTerm = XMLParser.parse(resources, resources.getString(R.string.loan_term), R.raw.mortgage_loans_loan_term);
        List<Loan> loanType = XMLParser.parse(resources, resources.getString(R.string.loan_type), R.raw.mortage_loans_loan_type);

        LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 10, 20, 0);

        setOrientation(VERTICAL);
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        loanAmountText = CreateView.editText(context, "SP_LoanAmount", resources.getString(R.string.loan_amount), layoutParams, this);
        propertyValueText = CreateView.editText(context, "SP_PropertyValue", resources.getString(R.string.property_value), layoutParams, this);

        Spinner loanPurposesSpinner = CreateView.spinner(context, "SP_LoanPurpose", layoutParams, loanPurposes);
        Spinner currencySpinner = CreateView.spinner(context, "SP_Currency", layoutParams, currency);
        Spinner loanTermSpinner = CreateView.spinner(context, "SP_LoanTerm", layoutParams, loanTerm);
        Spinner loanTypeSpinner = CreateView.spinner(context, "SP_MLLoanType", layoutParams, loanType);

        addViews(loanAmountText, propertyValueText, loanPurposesSpinner,
                currencySpinner, loanTermSpinner, loanTypeSpinner);
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