package bg.financialproducts.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.adapter.MainAdapter;
import bg.financialproducts.info.CreditCardInfoActivity;
import bg.financialproducts.model.BaseLoan;
import bg.financialproducts.model.CreditCard;
import bg.financialproducts.util.Constants;
import bg.financialproducts.util.Database;

public class CreditCardFragment extends Fragment {

    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        activity = getActivity();
        Database database = new Database(activity);

        List<BaseLoan> creditCardList = new Gson().
                fromJson(database.findLoanByType(Constants.TABLE_NAME_LOAN, Constants.CREDIT_CARDS),
                        new TypeToken<List<CreditCard>>() {}.getType());

        if (creditCardList != null && !creditCardList.isEmpty()) {
            View linearLayout = View.inflate(activity, R.layout.header, null);

            TextView textView = (TextView) linearLayout.findViewById(R.id.header);
            textView.setText(textView.getText() + "\n" + database.getCreatedAtDate(Constants.CREDIT_CARDS));

            ListView listView = (ListView) view.findViewById(R.id.list);
            listView.setVisibility(View.VISIBLE);
            listView.addHeaderView(linearLayout, null, false);
            listView.setAdapter(new MainAdapter(activity, R.layout.item, creditCardList));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(activity, CreditCardInfoActivity.class);
                    intent.putExtra(Constants.CREDIT_CARDS_ARRAY, (CreditCard) adapterView.getItemAtPosition(i));

                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            });
        } else {
            TextView noResultsView = (TextView) view.findViewById(R.id.noResult);
            noResultsView.setVisibility(View.VISIBLE);
        }

        return view;
    }
}