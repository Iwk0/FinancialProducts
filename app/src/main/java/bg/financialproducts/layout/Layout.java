package bg.financialproducts.layout;

import android.view.View;

import org.apache.http.NameValuePair;

import java.util.List;

public interface Layout {

    public View getRootView();
    public List<NameValuePair> getAllViews();
}