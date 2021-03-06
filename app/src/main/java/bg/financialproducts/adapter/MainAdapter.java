package bg.financialproducts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.model.BaseLoan;

public class MainAdapter extends ArrayAdapter<BaseLoan> {

    private List<BaseLoan> loans;
    private Context context;

    public static class ViewHolder {
        TextView product;
    }

    public MainAdapter(Context context, int resource, List<BaseLoan> loans) {
        super(context, resource);
        this.loans = loans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return loans.size();
    }

    @Override
    public BaseLoan getItem(int position) {
        return loans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.product = (TextView) convertView.findViewById(R.id.product);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position % 2 == 0) {
            convertView.setBackgroundResource(R.color.Blue);
        } else {
            convertView.setBackgroundResource(R.color.LightBlue);
        }

        BaseLoan baseLoan = loans.get(position);
        viewHolder.product.setText(baseLoan.product);

        return convertView;
    }
}