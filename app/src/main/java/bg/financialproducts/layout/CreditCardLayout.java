package bg.financialproducts.layout;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;

import org.apache.http.NameValuePair;

import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.model.Loan;
import bg.financialproducts.util.CreateView;
import bg.financialproducts.util.KeyBoard;
import bg.financialproducts.util.XMLParser;

public class CreditCardLayout implements Layout {

    private LinearLayout root;

    public CreditCardLayout(Activity activity) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.root = (LinearLayout) inflater.inflate(R.layout.main_layout, null);

        new ParseInformation(activity).execute();
    }

    private class ParseInformation extends AsyncTask<Void, Void, Void> {

        private Activity activity;
        private Resources resources;

        private List<Loan> currency, cardProviders, cardTypes;

        private ParseInformation(Activity activity) {
            this.activity = activity;
            this.resources = activity.getResources();
        }

        @Override
        protected Void doInBackground(Void... params) {
            currency = XMLParser.parse(resources, resources.getString(R.string.currency), R.raw.credit_cards_sp_currency);
            cardProviders = XMLParser.parse(resources, resources.getString(R.string.card_providers), R.raw.auto_loan_sp_car_type);
            cardTypes = XMLParser.parse(resources, resources.getString(R.string.card_type), R.raw.auto_loan_sp_alloan_type);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Spinner currencySpinner = CreateView.spinner(activity, "SP_Currency", root, currency);
            Spinner cardProvidersSpinner = CreateView.spinner(activity, "SP_CardTypeByProvider", root, cardProviders);
            Spinner cardTypeSpinner = CreateView.spinner(activity, "SP_CardTypePremium", root, cardTypes);

            addViews(currencySpinner, cardProvidersSpinner, cardTypeSpinner);

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
}