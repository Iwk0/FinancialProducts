package bg.financialproducts.util;

import android.app.Fragment;
import android.content.Context;

import bg.financialproducts.adapter.CreditCardFragment;
import bg.financialproducts.fragment.AutoFragment;
import bg.financialproducts.fragment.ConsumerFragment;
import bg.financialproducts.fragment.DepositsFragment;
import bg.financialproducts.fragment.MortgageFragment;
import bg.financialproducts.layout.ConsumerLayout;
import bg.financialproducts.layout.Layout;

public class Factories {

    public Layout createView(int layoutId, Context context) {
        Layout layout;

        switch (layoutId) {
            case LoansValue.AUTO : layout = new ConsumerLayout(context);
                break;
            case LoansValue.CONSUMER : layout = new ConsumerLayout(context);
                break;
            case LoansValue.CREDIT_CARD : layout = new ConsumerLayout(context);
                break;
            case LoansValue.DEPOSITS : layout = new ConsumerLayout(context);
                break;
            case LoansValue.MORTGAGE : layout = new ConsumerLayout(context);
                break;
            default: layout = null;
                break;
        }

        return layout;
    }

    public static Fragment createFragment(int fragmentId, Context context) {
        Fragment fragment;

        switch (fragmentId) {
            case LoansValue.AUTO : fragment = new AutoFragment();
                break;
            case LoansValue.CONSUMER : fragment = new ConsumerFragment();
                break;
            case LoansValue.CREDIT_CARD : fragment = new CreditCardFragment();
                break;
            case LoansValue.DEPOSITS : fragment = new DepositsFragment();
                break;
            case LoansValue.MORTGAGE : fragment = new MortgageFragment();
                break;
            default: fragment = null;
                break;
        }

        return fragment;
    }
}