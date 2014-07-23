package com.project.kws.newsfeed.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.kws.newsfeed.R;

/**
 * Created by Fajar on 7/14/2014.
 */
public class FragmentTabsCategoryNews extends Fragment {

    private FragmentTabHost mTabhost;
    private View viewHierarchy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewHierarchy = inflater.inflate(R.layout.f_tabs_categorynews, container, false);

        mTabhost = (FragmentTabHost) viewHierarchy.findViewById(android.R.id.tabhost);
        mTabhost.setup(getActivity(), getChildFragmentManager(), R.id.tabFrameLayout);

        mTabhost.addTab(mTabhost.newTabSpec("fragmentA").setIndicator("Fragment A"), FragmentA.class, null );
        mTabhost.addTab(mTabhost.newTabSpec("fragmentB").setIndicator("Fragment B"), FragmentB.class, null );
        mTabhost.addTab(mTabhost.newTabSpec("fragmentA").setIndicator("Fragment A"), FragmentA.class, null );
        mTabhost.addTab(mTabhost.newTabSpec("fragmentB").setIndicator("Fragment B"), FragmentB.class, null );
        mTabhost.addTab(mTabhost.newTabSpec("fragmentA").setIndicator("Fragment A"), FragmentA.class, null );
        mTabhost.addTab(mTabhost.newTabSpec("fragmentB").setIndicator("Fragment B"), FragmentB.class, null );
        mTabhost.addTab(mTabhost.newTabSpec("fragmentA").setIndicator("Fragment A"), FragmentA.class, null );
        mTabhost.addTab(mTabhost.newTabSpec("fragmentB").setIndicator("Fragment B"), FragmentB.class, null );


        /*Set Style Start*/
        //Devider
        mTabhost.getTabWidget().setDividerDrawable(null);

        //Text Color
        for (int i = 0; i < mTabhost.getTabWidget().getChildCount(); i++) {
            final TextView tv = (TextView) mTabhost.getTabWidget().getChildAt(i)
                    .findViewById(android.R.id.title);
            if (tv == null)
                continue;
            else
                tv.setTextColor(getResources().getColor(R.color.white));

        }
        /*Set Style End*/


        return viewHierarchy;
    }



}
