package bg.financialproducts.info;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import bg.financialproducts.R;
import bg.financialproducts.model.Auto;
import bg.financialproducts.util.Constants;

public class AutoInfoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_info);

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

            String title = auto.product;
            if (title.length() > 19) {
                title = title.substring(0, 16) + "...";
            }
            header.setText(getResources().getString(R.string.title_activity_auto_info) + "\n" + title);
        }
    }
}