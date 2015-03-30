package bg.financialproducts.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import bg.financialproducts.R;
import bg.financialproducts.adapter.MortgageAdapter;

public class MortgageFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mortgage, container, false);

        Activity activity = getActivity();

        ListView listView = (ListView) view.findViewById(R.id.mortageList);
        listView.addHeaderView(View.inflate(activity, R.layout.header_mortage, null), null, false);
        listView.setAdapter(new MortgageAdapter(activity, R.layout.item));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               /* Intent intent = new Intent(activity, LogActivity.class);
                intent.putExtra(Constants.LOG, (Log) adapterView.getItemAtPosition(i));

                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);*/
            }
        });

        return view;
    }
}