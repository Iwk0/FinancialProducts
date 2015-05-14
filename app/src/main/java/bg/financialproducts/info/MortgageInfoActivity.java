package bg.financialproducts.info;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import bg.financialproducts.R;
import bg.financialproducts.model.Mortgage;
import bg.financialproducts.util.Constants;

public class MortgageInfoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_info);

        View linearLayout = View.inflate(this, R.layout.abs_layout, null);
        TextView header = (TextView) linearLayout.findViewById(R.id.header);
        ImageView icon = (ImageView) linearLayout.findViewById(R.id.icon);

        icon.setImageResource(R.mipmap.info);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(linearLayout);
        }

        TextView aprView = (TextView) findViewById(R.id.apr);
        TextView currencyView = (TextView) findViewById(R.id.currency);
        TextView monthlyPaymentView = (TextView) findViewById(R.id.monthlyPayment);
        TextView totalPayedView = (TextView) findViewById(R.id.totalPayed);
        TextView downPaymentView = (TextView) findViewById(R.id.downPayment);
        TextView interestRateTypeView = (TextView) findViewById(R.id.interestRateType);
        Button back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Mortgage mortgage = bundle.getParcelable(Constants.MORTGAGE_ARRAY);
            aprView.setText(mortgage.apr);
            currencyView.setText(mortgage.currency);
            monthlyPaymentView.setText(mortgage.monthlyPayment);
            totalPayedView.setText(mortgage.totalPayed);
            downPaymentView.setText(mortgage.downPayment);
            interestRateTypeView.setText(mortgage.interestType);

            String title = mortgage.product;
            if (title.length() > 19) {
                title = title.substring(0, 16) + "...";
            }

            header.setText(getResources().getString(R.string.title_activity_mortgage_info) + "\n" + title);
        }
    }
}