package bg.financialproducts.layout;

import android.content.Context;
import android.widget.LinearLayout;

import org.apache.http.NameValuePair;

import java.util.List;

public abstract class Layout extends LinearLayout {

    public Layout(Context context) {
        super(context);
    }

    public abstract List<NameValuePair> getAllViews();
}