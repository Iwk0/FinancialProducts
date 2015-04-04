package bg.financialproducts.fragment;

import android.app.Activity;
import android.app.Fragment;
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
import bg.financialproducts.model.BaseLoan;
import bg.financialproducts.util.Constants;
import bg.financialproducts.util.Database;

public class ConsumerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Activity activity = getActivity();

        List<BaseLoan> consumerList = new Gson().
                fromJson(new Database(activity).findLoanByType(Constants.TABLE_NAME_LOAN, Constants.CONSUMER),
                        new TypeToken<List<BaseLoan>>() {}.getType());

        if (consumerList != null && !consumerList.isEmpty()) {
            ListView listView = (ListView) view.findViewById(R.id.list);
            listView.setVisibility(View.VISIBLE);
            listView.addHeaderView(View.inflate(activity, R.layout.header, null), null, false);
            listView.setAdapter(new MainAdapter(activity, R.layout.item, consumerList));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
/*                Intent intent = new Intent(activity, LogActivity.class);
                intent.putExtra(Constants.LOG, (Log) adapterView.getItemAtPosition(i));

                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);*/
                }
            });
        } else {
            TextView noResultsView = (TextView) view.findViewById(R.id.noResult);
            noResultsView.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
