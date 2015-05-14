package bg.financialproducts.info;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import bg.financialproducts.R;
import bg.financialproducts.model.Deposits;
import bg.financialproducts.util.Constants;

public class DepositsInfoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposits_info);

        View linearLayout = View.inflate(this, R.layout.abs_layout, null);
        TextView header = (TextView) linearLayout.findViewById(R.id.header);
        ImageView icon = (ImageView) linearLayout.findViewById(R.id.icon);

        icon.setImageResource(R.mipmap.info);

        ActionBar actionBar = getSupportActionBar();
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

            String title = deposits.product;
            if (title.length() > 19) {
                title = title.substring(0, 16) + "...";
            }

            header.setText(getResources().getString(R.string.title_activity_deposits_info) + "\n" + title);
        }
    }
}
