package bg.financialproducts.util;

import android.content.Context;

import bg.financialproducts.layout.ConsumerLayout;
import bg.financialproducts.layout.Layout;

public class ViewFactory {

    public final static int AUTO = 1;
    public final static int CONSUMER = 2;
    public final static int CREDIT_CARD = 3;
    public final static int DEPOSITS = 4;
    public final static int MORTGAGE = 5;

    public Layout createView(int layoutId, Context context) {
        Layout layout;

        switch (layoutId) {
            case AUTO : layout = new ConsumerLayout(context);
                break;
            case CONSUMER : layout = new ConsumerLayout(context);
                break;
            case CREDIT_CARD : layout = new ConsumerLayout(context);
                break;
            case DEPOSITS : layout = new ConsumerLayout(context);
                break;
            case MORTGAGE : layout = new ConsumerLayout(context);
                break;
            default: layout = null;
                break;
        }

        return layout;
    }
}