package bg.financialproducts;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import bg.financialproducts.adapter.MenuAdapter;
import bg.financialproducts.fragment.AutoFragment;
import bg.financialproducts.fragment.ConsumerFragment;
import bg.financialproducts.fragment.CreditCardFragment;
import bg.financialproducts.fragment.DepositsFragment;
import bg.financialproducts.fragment.MortgageFragment;
import bg.financialproducts.fragment.SearchFragment;
import bg.financialproducts.model.BannerSet;
import bg.financialproducts.util.BannerService;
import bg.financialproducts.util.BannerUtil;
import bg.financialproducts.util.Constants;
import bg.financialproducts.util.Database;
import bg.financialproducts.util.Internet;

public class MainActivity extends ActionBarActivity {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private ActionBar actionBar;

    private AsyncTask<Void, Void, List<BannerSet>> asyncTask;

    private CharSequence drawerTitle;
    private CharSequence fragmentTitle;
    private String[] barTitle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
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
        drawerList.setBackgroundResource(R.mipmap.background_menu);
        drawerList.setAdapter(new MenuAdapter(this, R.layout.menu_item));
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectItem(position);
            }
        });

        if (savedInstanceState == null) {
            selectItem(5);
        }

        if (Internet.isConnected(this)) {
            asyncTask = BannerUtil.available(this, getWindow().getDecorView(), "Home", Constants.BOTTOM);
            asyncTask.execute();
        }

        startService(new Intent(this, BannerService.class));

        Resources resources = this.getResources();
        new Database(this).insertSettings(
                resources.getString(R.string.settings_url),
                resources.getString(R.string.settings_id),
                resources.getString(R.string.settings_username));
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
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(drawerList)) {
            drawerLayout.closeDrawer(drawerList);
        } else {
            finish();
        }
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

    public void selectItem(int position) {
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

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();

        drawerList.setItemChecked(position, true);
        setTitle(barTitle[position]);
        drawerLayout.closeDrawer(drawerList);
    }
}