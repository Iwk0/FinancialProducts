package bg.financialproducts.info;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import bg.financialproducts.R;
import bg.financialproducts.model.Consumer;
import bg.financialproducts.util.Constants;

public class ConsumerInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_info);

        View linearLayout = View.inflate(this, R.layout.abs_layout, null);
        TextView header = (TextView) linearLayout.findViewById(R.id.header);
        ImageView icon = (ImageView) linearLayout.findViewById(R.id.icon);

        icon.setImageResource(R.mipmap.info);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(linearLayout);
        }

        TextView aprView = (TextView) findViewById(R.id.apr);
        TextView currencyView = (TextView) findViewById(R.id.currency);
        TextView monthlyPaymentView = (TextView) findViewById(R.id.monthlyPayment);
        TextView totalPayedView = (TextView) findViewById(R.id.totalPayed);
        TextView interestRateType = (TextView) findViewById(R.id.interestRateType);
        Button back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Consumer consumer = bundle.getParcelable(Constants.CONSUMER_ARRAY);
            aprView.setText(consumer.apr);
            currencyView.setText(consumer.currency);
            monthlyPaymentView.setText(consumer.monthlyPayment);
            totalPayedView.setText(consumer.totalPayed);
            interestRateType.setText(consumer.interestRateType);

            header.setText(getResources().getString(R.string.title_activity_consumer_info) + "\n" + consumer.product);
        }
    }
}
