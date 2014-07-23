package com.project.kws.newsfeed.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.kws.newsfeed.R;

/**
 * Created by Fajar on 7/14/2014.
 */
public class FragmentB extends Fragment {

    View viewHieararchy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewHieararchy = inflater.inflate(R.layout.f_b, container, false);
        return viewHieararchy;
    }
}
