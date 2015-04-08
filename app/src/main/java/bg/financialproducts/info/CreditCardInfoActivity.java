package bg.financialproducts.info;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import bg.financialproducts.R;
import bg.financialproducts.model.CreditCard;
import bg.financialproducts.util.Constants;

public class CreditCardInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        View linearLayout = View.inflate(this, R.layout.abs_layout, null);
        TextView header = (TextView) linearLayout.findViewById(R.id.header);
        ImageView icon = (ImageView) linearLayout.findViewById(R.id.icon);

        icon.setImageResource(R.mipmap.info);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(linearLayout);
        }

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

            header.setText(getResources().getString(R.string.title_activity_credit_card) + "\n" + creditCard.product);
        }
    }
}