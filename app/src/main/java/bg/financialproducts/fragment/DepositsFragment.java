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
import bg.financialproducts.info.DepositsInfoActivity;
import bg.financialproducts.model.BaseLoan;
import bg.financialproducts.model.Deposits;
import bg.financialproducts.util.Constants;
import bg.financialproducts.util.Database;

public class DepositsFragment extends Fragment {

    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        activity = getActivity();
        Database database = new Database(activity);

        List<BaseLoan> depositsList = new Gson().
                fromJson(database.findLoanByType(Constants.TABLE_NAME_LOAN, Constants.DEPOSITS),
                        new TypeToken<List<Deposits>>() {}.getType());

        if (depositsList != null && !depositsList.isEmpty()) {
            TextView textView = (TextView) view.findViewById(R.id.header);
            textView.setVisibility(View.VISIBLE);
            textView.setText(textView.getText() + "\n" + database.getCreatedAtDate(Constants.CREDIT_CARDS));

            ListView listView = (ListView) view.findViewById(R.id.list);
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(new MainAdapter(activity, R.layout.item, depositsList));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(activity, DepositsInfoActivity.class);
                    intent.putExtra(Constants.DEPOSITS_ARRAY, (Deposits) adapterView.getItemAtPosition(i));

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