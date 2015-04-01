package bg.financialproducts.layout;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import org.apache.http.NameValuePair;

import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.model.Loan;
import bg.financialproducts.util.CreateView;
import bg.financialproducts.util.XMLParser;

public class CreditCardLayout extends Layout {

    public CreditCardLayout(Context context) {
        super(context);

        Resources resources = getResources();
        List<Loan> currency = XMLParser.parse(resources, resources.getString(R.string.currency), R.raw.auto_loan_sp_age_of_car);
        List<Loan> cardProviders = XMLParser.parse(resources, resources.getString(R.string.card_providers), R.raw.auto_loan_sp_car_type);
        List<Loan> cardTypes = XMLParser.parse(resources, resources.getString(R.string.card_type), R.raw.auto_loan_sp_alloan_type);

        ViewGroup.LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        setOrientation(VERTICAL);
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        Spinner currencySpinner = CreateView.spinner(context, "SP_Currency", layoutParams, currency);
        Spinner cardProvidersSpinner = CreateView.spinner(context, "SP_CardTypeByProvider", layoutParams, cardProviders);
        Spinner cardTypeSpinner = CreateView.spinner(context, "SP_CardTypePremium", layoutParams, cardTypes);

        addViews(currencySpinner, cardProvidersSpinner, cardTypeSpinner);
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
