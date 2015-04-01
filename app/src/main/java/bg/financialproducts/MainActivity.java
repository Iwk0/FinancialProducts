package bg.financialproducts;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import bg.financialproducts.fragment.AutoFragment;
import bg.financialproducts.fragment.ConsumerFragment;
import bg.financialproducts.fragment.CreditCardFragment;
import bg.financialproducts.fragment.DepositsFragment;
import bg.financialproducts.fragment.MortgageFragment;
import bg.financialproducts.fragment.SearchFragment;

public class MainActivity extends FragmentActivity {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private ActionBar actionBar;

    private CharSequence drawerTitle;
    private CharSequence fragmentTitle;
    private String[] barTitle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getActionBar();
        fragmentTitle = drawerTitle = getTitle();
        barTitle = getResources().getStringArray(R.array.loans);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerToggle = new CustomActionBarDrawerToggle(this, drawerLayout);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawerLayout.setDrawerListener(drawerToggle);

        drawerList.setVisibility(View.VISIBLE);
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, barTitle));
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectItem(position);
            }
        });

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        fragmentTitle = title;
        actionBar.setTitle(fragmentTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private class CustomActionBarDrawerToggle extends ActionBarDrawerToggle {

        public CustomActionBarDrawerToggle(Activity mActivity, DrawerLayout mDrawerLayout) {
            super(mActivity, mDrawerLayout, new Toolbar(MainActivity.this),
                    R.string.drawer_open, R.string.drawer_close);
        }

        @Override
        public void onDrawerClosed(View view) {
            actionBar.setTitle(fragmentTitle);
            invalidateOptionsMenu();
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            actionBar.setTitle(drawerTitle);
            invalidateOptionsMenu();
        }
    }

    private void selectItem(int position) {
        Fragment fragment;

        switch (position) {
            case 0:
                fragment = new AutoFragment();
                break;
            case 1:
                fragment = new ConsumerFragment();
                break;
            case 2:
                fragment = new CreditCardFragment();
                break;
            case 3:
                fragment = new DepositsFragment();
                break;
            case 4:
                fragment = new MortgageFragment();
                break;
            default:
                fragment = new SearchFragment();
                break;
        }

        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();

        drawerList.setItemChecked(position, true);
        setTitle(barTitle[position]);
        drawerLayout.closeDrawer(drawerList);
    }
}