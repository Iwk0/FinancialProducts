package bg.financialproducts.info;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import bg.financialproducts.R;
import bg.financialproducts.model.Auto;
import bg.financialproducts.util.Constants;

public class AutoInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_info);

        TextView aprView = (TextView) findViewById(R.id.apr);
        TextView currencyView = (TextView) findViewById(R.id.currency);
        TextView monthlyPaymentView = (TextView) findViewById(R.id.monthlyPayment);
        TextView totalPayedView = (TextView) findViewById(R.id.totalPayed);
        TextView minSelfParticipationView = (TextView) findViewById(R.id.minSelfParticipation);
        Button back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Auto auto = bundle.getParcelable(Constants.AUTO_ARRAY);
            aprView.setText(auto.apr);
            currencyView.setText(auto.currency);
            monthlyPaymentView.setText(auto.monthlyPayment);
            totalPayedView.setText(auto.totalPayed);
            minSelfParticipationView.setText(auto.minSelfParticipation);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more_info, menu);
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