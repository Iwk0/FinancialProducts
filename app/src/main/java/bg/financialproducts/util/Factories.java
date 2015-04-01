package bg.financialproducts.util;

import android.app.Fragment;
import android.content.Context;

import bg.financialproducts.fragment.AutoFragment;
import bg.financialproducts.fragment.ConsumerFragment;
import bg.financialproducts.fragment.CreditCardFragment;
import bg.financialproducts.fragment.DepositsFragment;
import bg.financialproducts.fragment.MortgageFragment;
import bg.financialproducts.layout.AutoLayout;
import bg.financialproducts.layout.ConsumerLayout;
import bg.financialproducts.layout.CreditCardLayout;
import bg.financialproducts.layout.DepositsLayout;
import bg.financialproducts.layout.Layout;
import bg.financialproducts.layout.MortgageLayout;

public class Factories {

    public Layout createView(int layoutId, Context context) {
        Layout layout;

        switch (layoutId) {
            case LoansValue.AUTO : layout = new AutoLayout(context);
                break;
            case LoansValue.CONSUMER : layout = new ConsumerLayout(context);
                break;
            case LoansValue.CREDIT_CARD : layout = new CreditCardLayout(context);
                break;
            case LoansValue.DEPOSITS : layout = new DepositsLayout(context);
                break;
            case LoansValue.MORTGAGE : layout = new MortgageLayout(context);
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