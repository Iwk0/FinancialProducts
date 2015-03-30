package bg.financialproducts.layout;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import bg.financialproducts.R;
import bg.financialproducts.model.Loan;

public class ConsumerLayout extends Layout implements TextWatcher {

    private EditText loanAmountText;

    public ConsumerLayout(Context context) {
        super(context);

        List<Loan> typeOfTheLoans = getSpinnerValues("Type of the loan", R.raw.consumer_loans_type_of_the_loan);
        List<Loan> currency = getSpinnerValues("Currency", R.raw.consumer_loans_sp_currency);
        List<Loan> loanTermInMonths = getSpinnerValues("Loan Term in months", R.raw.consumer_loans_loan_term);

        ViewGroup.LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        List<View> views = new ArrayList<>();

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
                R.layout.spinner_item, typeOfTheLoans);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner typeOfLoanSpinner = new Spinner(context);
        typeOfLoanSpinner.setTag("SP_LoanType");
        typeOfLoanSpinner.setLayoutParams(layoutParams);
        typeOfLoanSpinner.setAdapter(adapter);

        adapter.clear();
        adapter.addAll(currency);

        Spinner currencySpinner = new Spinner(context);
        currencySpinner.setTag("SP_Currency");
        currencySpinner.setLayoutParams(layoutParams);
        currencySpinner.setAdapter(adapter);

        adapter.clear();
        adapter.addAll(loanTermInMonths);

        Spinner loanTermInMonthsSpinner = new Spinner(context);
        loanTermInMonthsSpinner.setTag("SP_LoanTerm");
        loanTermInMonthsSpinner.setLayoutParams(layoutParams);
        loanTermInMonthsSpinner.setAdapter(adapter);

        views.addAll(Arrays.asList(loanAmountText, typeOfLoanSpinner, currencySpinner, loanTermInMonthsSpinner));

        addViews(loanAmountText, typeOfLoanSpinner, currencySpinner, loanTermInMonthsSpinner);
    }

    private void addViews(View... localViews) {
        for (View view : localViews) {
            addView(view);
        }
    }

    private List<Loan> getSpinnerValues(String defaultValue, int fileId) {
        List<Loan> loans = new ArrayList<Loan>();
        loans.add(new Loan(-1, defaultValue));

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(getResources().openRawResource(fileId));

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("option");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    loans.add(new Loan(
                            Integer.parseInt(element.getAttribute("value")),
                            element.getTextContent()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }

        return loans;
    }

    @Override
    public List<NameValuePair> getAllViews() {
        List<NameValuePair> params = new ArrayList<>();
        final int SIZE = this.getChildCount();
        for (int i = 0; i < SIZE; i++) {
            View view = this.getChildAt(i);
            if (view instanceof Spinner) {
                Spinner spinner = (Spinner) view;
                Loan loan = (Loan) spinner.getSelectedItem();
                params.add(new BasicNameValuePair((String) spinner.getTag(), (String.valueOf(loan.id))));
            } else if (view instanceof EditText) {
                EditText text = (EditText) view;
                params.add(new BasicNameValuePair((String) text.getTag(), text.getText().toString()));
            }
        }

        return params;
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
            if (loanAmount < 100 || loanAmount > 1000) {
                loanAmountText.setBackgroundColor(Color.RED);
            }
        }
    }
}