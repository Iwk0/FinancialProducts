package bg.financialproducts.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bg.financialproducts.R;
import bg.financialproducts.model.Menu;

public class MenuAdapter extends ArrayAdapter<Menu> {

    private final List<Menu> menus = new ArrayList<>();

    private Context context;

    private static class ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public View divider;
    }

    public MenuAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;

        Resources resources = context.getResources();
        menus.add(new Menu(R.drawable.auto, resources.getString(R.string.auto)));
        menus.add(new Menu(R.drawable.consumer, resources.getString(R.string.consumer)));
        menus.add(new Menu(R.drawable.credit_cards, resources.getString(R.string.credit_cards)));
        menus.add(new Menu(R.drawable.deposits, resources.getString(R.string.deposits)));
        menus.add(new Menu(R.drawable.mortgage, resources.getString(R.string.mortgage)));
        menus.add(new Menu(R.drawable.search, resources.getString(R.string.search)));
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Menu getItem(int position) {
        return menus.get(position);
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
            convertView = inflater.inflate(R.layout.menu_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text);
            viewHolder.divider = convertView.findViewById(R.id.divider);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Menu menu = menus.get(position);
        if (menu.text.equals(context.getResources().getString(R.string.search))) {
            viewHolder.divider.setVisibility(View.VISIBLE);
            viewHolder.divider.getBackground().setAlpha(100);
        }

        viewHolder.imageView.setImageResource(menu.imageId);
        viewHolder.textView.setText(menu.text);

        return convertView;
    }
}