package bg.financialproducts.info;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import bg.financialproducts.R;
import bg.financialproducts.model.CreditCard;
import bg.financialproducts.util.Constants;

public class CreditCardInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        TextView cashRate = (TextView) findViewById(R.id.cashRate);
        TextView purchaseRate = (TextView) findViewById(R.id.purchaseRate);
        TextView cashAPR = (TextView) findViewById(R.id.cashAPR);
        TextView creditCardLimit = (TextView) findViewById(R.id.creditCardLimit);
        TextView annualFeeMain = (TextView) findViewById(R.id.annualFeeMain);
        Button back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            CreditCard creditCard = bundle.getParcelable(Constants.CREDIT_CARDS_ARRAY);
            cashRate.setText(creditCard.cashAPR);
            purchaseRate.setText(creditCard.purchaseRate);
            cashAPR.setText(creditCard.cashAPR);
            creditCardLimit.setText(creditCard.creditCardLimit);
            annualFeeMain.setText(creditCard.annualFeeMain);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_credit_card, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
/*        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}