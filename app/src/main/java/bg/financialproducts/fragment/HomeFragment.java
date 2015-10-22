package bg.financialproducts.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bg.financialproducts.MainActivity;
import bg.financialproducts.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.consumer_button).setOnClickListener(this);
        view.findViewById(R.id.credit_cards_button).setOnClickListener(this);
        view.findViewById(R.id.mortgage_button).setOnClickListener(this);
        view.findViewById(R.id.deposit_button).setOnClickListener(this);
        view.findViewById(R.id.savings_accounts_button).setOnClickListener(this);
        view.findViewById(R.id.financial_news_button).setOnClickListener(this);
        view.findViewById(R.id.current_tips_button).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Integer value = Integer.parseInt((String) view.getTag());
        ((MainActivity) getActivity()).selectItem(6, value);
    }
}