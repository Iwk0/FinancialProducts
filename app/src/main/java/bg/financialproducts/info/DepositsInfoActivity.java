package bg.financialproducts.info;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import bg.financialproducts.R;
import bg.financialproducts.model.Deposits;
import bg.financialproducts.util.Constants;

public class DepositsInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposits_info);

        View linearLayout = View.inflate(this, R.layout.abs_layout, null);
        TextView header = (TextView) linearLayout.findViewById(R.id.header);
        ImageView icon = (ImageView) linearLayout.findViewById(R.id.icon);

        icon.setImageResource(R.mipmap.info);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(linearLayout);
        }

        TextView aer = (TextView) findViewById(R.id.AER);
        TextView interestRateType = (TextView) findViewById(R.id.interestRateType);
        TextView afterRevenueTaxAmount = (TextView) findViewById(R.id.afterRevenueTaxAmount);
        Button back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Deposits deposits = bundle.getParcelable(Constants.DEPOSITS_ARRAY);
            aer.setText(deposits.AER);
            interestRateType.setText(deposits.interestRateType);
            afterRevenueTaxAmount.setText(deposits.afterRevenueTaxAmount);

            header.setText(getResources().getString(R.string.title_activity_deposits_info) + "\n" + deposits.product);
        }
    }
}
