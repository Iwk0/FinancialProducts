package bg.financialproducts.info;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import bg.financialproducts.R;
import bg.financialproducts.model.Mortgage;
import bg.financialproducts.util.Constants;

public class MortgageInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_info);

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
        }
    }
}