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
import bg.financialproducts.info.AutoInfoActivity;
import bg.financialproducts.model.Auto;
import bg.financialproducts.model.BaseLoan;
import bg.financialproducts.util.Constants;
import bg.financialproducts.util.Database;

public class AutoFragment extends Fragment {

    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        activity = getActivity();
        Database database = new Database(activity);

        List<BaseLoan> autoList = new Gson().
                fromJson(database.findLoanByType(Constants.TABLE_NAME_LOAN, Constants.AUTO),
                        new TypeToken<List<Auto>>() {}.getType());

        if (autoList != null && !autoList.isEmpty()) {
            TextView textView = (TextView) view.findViewById(R.id.header);
            textView.setVisibility(View.VISIBLE);
            textView.setText(textView.getText() + "\n" + database.getCreatedAtDate(Constants.AUTO));

            ListView listView = (ListView) view.findViewById(R.id.list);
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(new MainAdapter(activity, R.layout.item, autoList));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(activity, AutoInfoActivity.class);
                intent.putExtra(Constants.AUTO_ARRAY, (Auto) adapterView.getItemAtPosition(i));

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