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

public class DepositsLayout extends Layout implements TextWatcher {

    private EditText loanAmountText;

    public DepositsLayout(Context context) {
        super(context);

        Resources resources = getResources();
        List<Loan> currency = XMLParser.parse(resources, resources.getString(R.string.currency), R.raw.consumer_loans_sp_currency);
        List<Loan> depositsTerm = XMLParser.parse(resources, resources.getString(R.string.deposit_term_month), R.raw.deposits_sp_deposit_term);
        List<Loan> interestCapitalization = XMLParser.parse(resources, resources.getString(R.string.interest_capitalization), R.raw.deposits_interest_capitalization);

        LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 10, 20, 0);

        setOrientation(VERTICAL);
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        loanAmountText = CreateView.editText(context, "SP_DepositAmount", resources.getString(R.string.loan_amount), layoutParams, this);

        Spinner currencySpinner = CreateView.spinner(context, "SP_Currency", layoutParams, currency);
        Spinner depositsTermSpinner = CreateView.spinner(context, "SP_DepositTerm", layoutParams, depositsTerm);
        Spinner interestCapitalizationSpinner = CreateView.spinner(context, "SP_CapitalizationOfInterest", layoutParams, interestCapitalization);

        addViews(loanAmountText, currencySpinner, depositsTermSpinner, interestCapitalizationSpinner);
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
        loanAmountText.setTag("SP_DepositAmount");
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        CreateView.editTextValidation(loanAmountText, getResources(), 100, 100000);
    }
}