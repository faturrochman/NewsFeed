package com.project.kws.newsfeed;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.project.kws.newsfeed.factory.FragmentTag;
import com.project.kws.newsfeed.news.NewsDetailFragment;
import com.project.kws.newsfeed.news.NewsListFragment;


public class MainDrawerImpl extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, MainDrawer {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

        setContentView(R.layout.a_main_drawer);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onChangeFragment(String tag, Object obj) {
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_transparent));
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        if(tag.equalsIgnoreCase(FragmentTag.NEWS_DETAIL)){
            NewsDetailFragment newsDetail = new NewsDetailFragment();
            Bundle args = new Bundle();
            args.putString("id", (String) obj);
            newsDetail.setArguments(args);
            ft.replace(R.id.container, newsDetail,FragmentTag.NEWS_DETAIL).addToBackStack(FragmentTag.NEWS_DETAIL).commit();
        }

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.KWS_Green_News)));

        NewsListFragment newsList = new NewsListFragment();
        ft.replace(R.id.container, newsList, FragmentTag.NEWS_LIST).commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_drawer, menu);
        restoreActionBar();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        FragmentManager fragmentManager = getFragmentManager();
        getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.KWS_Green_News)));
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}
