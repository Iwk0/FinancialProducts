package bg.financialproducts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import bg.financialproducts.R;
import bg.financialproducts.model.Mortgage;
import bg.financialproducts.util.AutoResizeTextView;

public class MortgageAdapter extends ArrayAdapter<Mortgage> {

    private Context context;

    public static class ViewHolder {
        AutoResizeTextView product;
    }

    public MortgageAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Mortgage getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.product = (AutoResizeTextView) convertView.findViewById(R.id.product);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return super.getView(position, convertView, parent);
    }
}