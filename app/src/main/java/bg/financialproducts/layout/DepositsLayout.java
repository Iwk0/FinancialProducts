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

public class DepositsLayout implements Layout, TextWatcher {

    private Activity activity;
    private LinearLayout root;
    private EditText loanAmountText;

    public DepositsLayout(Activity activity) {
        this.activity = activity;

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.root = (LinearLayout) inflater.inflate(R.layout.main_layout, null);

        new ParseInformation(activity).execute();
    }

    private class ParseInformation extends AsyncTask<Void, Void, Void> {

        private Activity activity;
        private Resources resources;

        private List<Loan> currency, depositsTerm, interestCapitalization;

        public ParseInformation(Activity activity) {
            this.activity = activity;
            this.resources = activity.getResources();
        }

        @Override
        protected Void doInBackground(Void... params) {
            currency = XMLParser.parse(resources, resources.getString(R.string.currency), R.raw.consumer_sp_currency);
            depositsTerm = XMLParser.parse(resources, resources.getString(R.string.deposit_term_month), R.raw.deposits_sp_deposit_term);
            interestCapitalization = XMLParser.parse(resources, resources.getString(R.string.interest_capitalization), R.raw.deposits_sp_interest_capitalization);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Spinner currencySpinner = CreateView.spinner(activity, "SP_Currency", root, currency);
            Spinner depositsTermSpinner = CreateView.spinner(activity, "SP_DepositTerm", root, depositsTerm);
            Spinner interestCapitalizationSpinner = CreateView.spinner(activity, "SP_CapitalizationOfInterest", root, interestCapitalization);

            loanAmountText = CreateView.editText(activity, "SP_DepositAmount", resources.getString(R.string.loan_amount),  root, DepositsLayout.this);

            addViews(loanAmountText, currencySpinner, depositsTermSpinner, interestCapitalizationSpinner);

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
        loanAmountText.setTag("SP_DepositAmount");
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        CreateView.editTextValidation(loanAmountText, activity.getResources(), 100, 100000);
    }
}