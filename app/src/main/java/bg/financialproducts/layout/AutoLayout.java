package bg.financialproducts.layout;

import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.http.NameValuePair;

import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.model.Loan;
import bg.financialproducts.util.CreateView;
import bg.financialproducts.util.XMLParser;

public class AutoLayout extends Layout implements TextWatcher {

    private EditText loanAmountText, carPriceText;

    public AutoLayout(Context context) {
        super(context);

        Resources resources = getResources();
        List<Loan> carType = XMLParser.parse(resources, resources.getString(R.string.car_type), R.raw.auto_loan_sp_car_type);
        List<Loan> aLLoanTypes = XMLParser.parse(resources, resources.getString(R.string.loan_or_a_leasing), R.raw.auto_loan_sp_alloan_type);
        List<Loan> ageOfCars = XMLParser.parse(resources, resources.getString(R.string.car_age), R.raw.auto_loan_sp_age_of_car);
        List<Loan> residualValue = XMLParser.parse(resources, resources.getString(R.string.residual_value), R.raw.auto_loan_sp_residual_value);
        List<Loan> currency = XMLParser.parse(resources, resources.getString(R.string.currency), R.raw.consumer_loans_sp_currency);
        List<Loan> loanTerm = XMLParser.parse(resources, resources.getString(R.string.loan_term), R.raw.auto_loan_sp_loan_term);

        ViewGroup.LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        setOrientation(VERTICAL);
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        loanAmountText = CreateView.editText(context, "SP_SelfParticipationAmount", resources.getString(R.string.loan_amount), layoutParams, this);
        carPriceText = CreateView.editText(context, "SP_CarPrice", resources.getString(R.string.car_price), layoutParams, this);

        Spinner carTypeSpinner = CreateView.spinner(context, "SP_LoanType", layoutParams, carType);
        Spinner currencySpinner = CreateView.spinner(context, "SP_Currency", layoutParams, currency);
        Spinner loanTermInMonthsSpinner = CreateView.spinner(context, "SP_LoanTerm", layoutParams, loanTerm);
        Spinner aLLoanTypeSpinner = CreateView.spinner(context, "SP_ALLoanType", layoutParams, aLLoanTypes);
        final Spinner ageOfCarsSpinner = CreateView.spinner(context, "SP_AgeOfCar", layoutParams, ageOfCars);
        final Spinner residualValueSpinner = CreateView.spinner(context, "SP_ResidualValue_Input", layoutParams, residualValue);

        aLLoanTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Loan loan = (Loan) parent.getSelectedItem();
                if (loan.id.equals("1")) {
                    residualValueSpinner.setEnabled(false);
                    residualValueSpinner.setSelection(0);
                } else {
                    residualValueSpinner.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        carTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Loan loan = (Loan) parent.getSelectedItem();
                if (loan.id.equals("1")) {
                    ageOfCarsSpinner.setEnabled(false);
                    ageOfCarsSpinner.setSelection(0);
                } else {
                    ageOfCarsSpinner.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addViews(loanAmountText, carPriceText, carTypeSpinner, currencySpinner,
                loanTermInMonthsSpinner, aLLoanTypeSpinner, ageOfCarsSpinner, residualValueSpinner);
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
        loanAmountText.setBackgroundColor(android.R.attr.editTextColor);
        carPriceText.setBackgroundColor(android.R.attr.editTextColor);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        CreateView.editTextValidation(loanAmountText, 500, 5000000);
        CreateView.editTextValidation(carPriceText, 500, 5000000);
    }
}