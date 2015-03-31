package bg.financialproducts.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.http.NameValuePair;

import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.model.Loan;
import bg.financialproducts.util.XMLParser;

public class AutoLayout extends Layout implements TextWatcher {

    private EditText loanAmountText, carPriceText;

    protected AutoLayout(Context context) {
        super(context);

        Resources resources = getResources();
        List<Loan> carType = XMLParser.parse(resources, "Car type", R.raw.auto_loan_sp_car_type);
        List<Loan> aLLoanTypes = XMLParser.parse(resources, "Loan or a leasing", R.raw.auto_loan_sp_alloan_type);
        List<Loan> ageOfCars = XMLParser.parse(resources, "Age of cars", R.raw.auto_loan_sp_age_of_car);
        List<Loan> residualValue = XMLParser.parse(resources, "Residual value", R.raw.auto_loan_sp_residual_value);
        List<Loan> currency = XMLParser.parse(resources, "Currency", R.raw.consumer_loans_sp_currency);
        List<Loan> loanTerm = XMLParser.parse(resources, "Loan Term in months", R.raw.auto_loan_sp_loan_term);

        ViewGroup.LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        setOrientation(VERTICAL);
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        loanAmountText = fieldInitialization(context, "SP_SelfParticipationAmount", "Loan amount", layoutParams);
        carPriceText = fieldInitialization(context, "SP_CarPrice", "Car price", layoutParams);

        //TODO да оправя всички dropdown-и
        ArrayAdapter<Loan> adapter = new ArrayAdapter<>(context,
                R.layout.spinner_item, carType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner typeOfLoanSpinner = new Spinner(context);
        typeOfLoanSpinner.setTag("SP_LoanType");
        typeOfLoanSpinner.setLayoutParams(layoutParams);
        typeOfLoanSpinner.setAdapter(adapter);

        adapter = new ArrayAdapter<>(context,
                R.layout.spinner_item, currency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner currencySpinner = new Spinner(context);
        currencySpinner.setTag("SP_Currency");
        currencySpinner.setLayoutParams(layoutParams);
        currencySpinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter = new ArrayAdapter<>(context,
                R.layout.spinner_item, currency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner loanTermInMonthsSpinner = new Spinner(context);
        loanTermInMonthsSpinner.setTag("SP_LoanTerm");
        loanTermInMonthsSpinner.setLayoutParams(layoutParams);
        loanTermInMonthsSpinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        addViews(loanAmountText, typeOfLoanSpinner, currencySpinner, loanTermInMonthsSpinner);
    }

    private Spinner spinnerInitialization(Context context, String tag, ViewGroup.LayoutParams layoutParams, ArrayAdapter<Loan> loan) {
        Spinner spinner = new Spinner(context);
        spinner.setTag(tag);
        spinner.setLayoutParams(layoutParams);
        spinner.setAdapter(loan);
        loan.notifyDataSetChanged();

        return spinner;
    }

    private EditText fieldInitialization(Context context, String tag, String hint, ViewGroup.LayoutParams layoutParams) {
        EditText editText = new EditText(context);
        editText.setHint(hint);
        editText.setTag(tag);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.addTextChangedListener(this);
        editText.setLayoutParams(layoutParams);

        return editText;
    }

    private void addViews(View... localViews) {
        for (View view : localViews) {
            addView(view);
        }
    }

    @Override
    public List<NameValuePair> getAllViews() {
        return null;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        loanAmountText.setBackgroundColor(Color.WHITE);
        carPriceText.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        fieldValidation(loanAmountText);
        fieldValidation(carPriceText);
    }

    private void fieldValidation(EditText textField) {
        String text = textField.getText().toString();
        if (!text.isEmpty()) {
            double loanAmount = Double.valueOf(text);
            if (loanAmount < 500 || loanAmount > 5000000) {
                textField.setBackgroundColor(Color.RED);
            }
        }
    }
}