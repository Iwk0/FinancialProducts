package bg.financialproducts.info;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import bg.financialproducts.R;
import bg.financialproducts.model.Deposits;
import bg.financialproducts.util.Constants;

public class DepositsInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposits_info);

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
        }
    }
}
