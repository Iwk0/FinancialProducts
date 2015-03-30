package bg.financialproducts.layout;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import org.apache.http.NameValuePair;

import java.util.Arrays;
import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.model.Loan;
import bg.financialproducts.util.XMLParser;

public class MortgageLayout extends Layout implements TextWatcher {

    private EditText loanAmountText;

    public MortgageLayout(Context context) {
        super(context);

        List<Loan> loanPurposes = XMLParser.parse(getResources(), "Type of the loan", R.raw.consumer_loans_type_of_the_loan);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        setOrientation(VERTICAL);
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        loanAmountText = new EditText(context);
        loanAmountText.setHint("Loan amount");
        loanAmountText.setTag("SP_LoanAmount");
        loanAmountText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        loanAmountText.addTextChangedListener(this);
        loanAmountText.setLayoutParams(layoutParams);

        ArrayAdapter<Loan> adapter = new ArrayAdapter<>(context,
                R.layout.spinner_item, loanPurposes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        addViews(loanAmountText);
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
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = loanAmountText.getText().toString();
        if (!text.isEmpty()) {
            double loanAmount = Double.valueOf(text);
            if (loanAmount < 1000 || loanAmount > 100000000) {
                loanAmountText.setBackgroundColor(Color.RED);
            }
        }
    }
}