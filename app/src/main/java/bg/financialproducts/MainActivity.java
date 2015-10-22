package bg.financialproducts;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import bg.financialproducts.fragment.AutoFragment;
import bg.financialproducts.fragment.ConsumerFragment;
import bg.financialproducts.fragment.CreditCardFragment;
import bg.financialproducts.fragment.DepositsFragment;
import bg.financialproducts.fragment.HomeFragment;
import bg.financialproducts.fragment.MortgageFragment;
import bg.financialproducts.fragment.SearchFragment;
import bg.financialproducts.util.DataHolderClass;
import bg.financialproducts.util.Database;

public class MainActivity extends ActionBarActivity {

    private ActionBar actionBar;
    private String[] barTitle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        barTitle = getResources().getStringArray(R.array.loans);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.logo));

        if (savedInstanceState == null) {
            selectItem(-1, 1);
        }

/*        if (Internet.isConnected(this)) {
            AsyncTask<Void, Void, List<BannerSet>> asyncTask = BannerUtil.available(this, getWindow().getDecorView(), "Home", Constants.BOTTOM);
            asyncTask.execute();
        }

        startService(new Intent(this, BannerService.class));*/

        Resources resources = this.getResources();
        new Database(this).insertSettings(
                resources.getString(R.string.settings_url),
                resources.getString(R.string.settings_id),
                resources.getString(R.string.settings_username));
    }

    @Override
    public void setTitle(CharSequence title) {
        actionBar.setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void selectItem(int position, int layoutId) {
        Fragment fragment;

        switch (position) {
            case 1:
                fragment = new AutoFragment();
                break;
            case 2:
                fragment = new ConsumerFragment();
                break;
            case 3:
                fragment = new CreditCardFragment();
                break;
            case 4:
                fragment = new DepositsFragment();
                break;
            case 5:
                fragment = new MortgageFragment();
                break;
            case 6:
                fragment = new SearchFragment();
                break;
            default:
                fragment = new HomeFragment();
                break;
        }

        Log.i("Position", String.valueOf(position) + " " + fragment.toString());
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        DataHolderClass<Integer> dataHolderClass = DataHolderClass.getInstance();
        dataHolderClass.setItem(layoutId);

        setTitle(barTitle[0]);
    }
}