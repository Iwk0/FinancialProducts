package bg.financialproducts.info;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import bg.financialproducts.R;
import bg.financialproducts.model.Consumer;
import bg.financialproducts.util.Constants;

public class ConsumerInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_info);

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
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consumer_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
/*        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
