package bg.financialproducts.layout;

import android.view.View;

import org.apache.http.NameValuePair;

import java.util.List;

public interface Layout {

    View getRootView();
    List<NameValuePair> getAllViews();
}