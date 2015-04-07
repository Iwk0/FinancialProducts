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

public class ConsumerLayout extends Layout implements TextWatcher {

    private EditText loanAmountText;

    public ConsumerLayout(Context context) {
        super(context);

        Resources resources = getResources();
        List<Loan> typeOfTheLoans = XMLParser.parse(resources, resources.getString(R.string.loan_type), R.raw.consumer_loans_type_of_the_loan);
        List<Loan> currency = XMLParser.parse(resources, resources.getString(R.string.currency), R.raw.consumer_loans_sp_currency);
        List<Loan> loanTermInMonths = XMLParser.parse(resources, resources.getString(R.string.loan_term), R.raw.consumer_loans_loan_term);

        LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 10, 20, 0);

        setOrientation(VERTICAL);
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        loanAmountText = CreateView.editText(context, "SP_LoanAmount", resources.getString(R.string.loan_amount), layoutParams, this);

        Spinner typeOfLoanSpinner = CreateView.spinner(context, "SP_LoanType", layoutParams, typeOfTheLoans);
        Spinner currencySpinner = CreateView.spinner(context, "SP_Currency", layoutParams, currency);
        Spinner loanTermInMonthsSpinner = CreateView.spinner(context, "SP_LoanTerm", layoutParams, loanTermInMonths);

        addViews(loanAmountText, typeOfLoanSpinner, currencySpinner, loanTermInMonthsSpinner);
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
        CreateView.editTextValidation(loanAmountText, getResources(), 100, 1000);
    }
}