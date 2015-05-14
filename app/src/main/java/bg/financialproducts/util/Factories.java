package bg.financialproducts.util;

import android.app.Activity;
import android.support.v4.app.Fragment;

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

    public static Layout createView(int layoutId, Activity activity) {
        Layout layout;

        switch (layoutId) {
            case Constants.AUTO:
                layout = new AutoLayout(activity);
                break;
            case Constants.CONSUMER:
                layout = new ConsumerLayout(activity);
                break;
            case Constants.CREDIT_CARDS:
                layout = new CreditCardLayout(activity);
                break;
            case Constants.DEPOSITS:
                layout = new DepositsLayout(activity);
                break;
            case Constants.MORTGAGE:
                layout = new MortgageLayout(activity);
                break;
            default: layout = null;
                break;
        }

        return layout;
    }

    public static Fragment createFragment(int fragmentId) {
        Fragment fragment;

        switch (fragmentId) {
            case Constants.AUTO:
                fragment = new AutoFragment();
                break;
            case Constants.CONSUMER:
                fragment = new ConsumerFragment();
                break;
            case Constants.CREDIT_CARDS:
                fragment = new CreditCardFragment();
                break;
            case Constants.DEPOSITS:
                fragment = new DepositsFragment();
                break;
            case Constants.MORTGAGE:
                fragment = new MortgageFragment();
                break;
            default: fragment = null;
                break;
        }

        return fragment;
    }
}