package bg.financialproducts.layout;

import android.content.Context;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MortageLayout extends LinearLayout implements Layout {

    private List<TextView> views;

    public MortageLayout(Context context) {
        super(context);
        views = new ArrayList<>();

        setOrientation(VERTICAL);
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        EditText loansAmount = new EditText(context);
        loansAmount.setRawInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        loansAmount.setLayoutParams(
                new LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        views.addAll(Arrays.asList(loansAmount));
        addView(loansAmount);
    }

    @Override
    public List getAllViews() {
        return views;
    }
}