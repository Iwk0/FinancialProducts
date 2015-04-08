package bg.financialproducts.layout;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import org.apache.http.NameValuePair;

import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.model.Loan;
import bg.financialproducts.util.CreateView;
import bg.financialproducts.util.KeyBoard;
import bg.financialproducts.util.XMLParser;

public class CreditCardLayout extends Layout {

    public CreditCardLayout(Context context) {
        super(context);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 0);

        setOrientation(VERTICAL);
        setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        new ParseInformation(context, getResources(), layoutParams).execute();
    }

    private class ParseInformation extends AsyncTask<Void, Void, Void> {

        private Context context;
        private LayoutParams layoutParams;
        private Resources resources;

        private List<Loan> currency, cardProviders, cardTypes;

        private ParseInformation(Context context, Resources resources, LayoutParams layoutParams) {
            this.context = context;
            this.resources = resources;
            this.layoutParams = layoutParams;
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

            Spinner currencySpinner = CreateView.spinner(context, "SP_Currency", layoutParams, currency);
            Spinner cardProvidersSpinner = CreateView.spinner(context, "SP_CardTypeByProvider", layoutParams, cardProviders);
            Spinner cardTypeSpinner = CreateView.spinner(context, "SP_CardTypePremium", layoutParams, cardTypes);

            addViews(currencySpinner, cardProvidersSpinner, cardTypeSpinner);

            KeyBoard.hide(CreditCardLayout.this, (Activity) context);
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
}
