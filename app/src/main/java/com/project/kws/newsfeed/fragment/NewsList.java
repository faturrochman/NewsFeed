package com.project.kws.newsfeed.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.project.kws.newsfeed.MainDrawer;
import com.project.kws.newsfeed.R;
import com.project.kws.newsfeed.adapter.NewsListAdapter;
import com.project.kws.newsfeed.factory.FragmentTag;

import java.util.ArrayList;

import beans.MaterialBean;
import helper.DialogHelper;
import network.KotakwarnaHttpRequest;
import parser.MaterialParser;

/**
 * Created by Fajar on 7/19/2014.
 */
public class NewsList extends Fragment {

    private View viewHierarchy;
    private ListView lv_listnews;
    private DialogHelper dialogHelper;
    private ProgressDialog progressDialog;
    private String URL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewHierarchy = inflater.inflate(R.layout.f_newslist, container, false);
        URL = getResources().getString(R.string.api_material_all);

        lv_listnews = (ListView) viewHierarchy.findViewById(R.id.lv_listnews);
        dialogHelper = new DialogHelper();
        progressDialog = dialogHelper.buildProgressDialog(getActivity(), "Loading...");
        new DownloadTextTask().execute();

        return viewHierarchy;
    }

    private class DownloadTextTask extends AsyncTask<String, Void, String> {

        String jSonResponse;

        @Override
        protected String doInBackground(String... params) {
            KotakwarnaHttpRequest httpRequest = new KotakwarnaHttpRequest(URL);
            jSonResponse = httpRequest.requestGet();
            return jSonResponse;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {

            progressDialog.dismiss();

            MaterialParser materialParser = new MaterialParser();
            ArrayList<MaterialBean> materialBeans = materialParser.parse(jSonResponse);
            initListView(materialBeans);
        }
    }

    private void initListView(ArrayList<MaterialBean> materialBeans){
        NewsListAdapter adapter = new NewsListAdapter(getActivity(), materialBeans);
        lv_listnews.setAdapter(adapter);
        lv_listnews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = (TextView) view.findViewById(R.id.id_news);
                String view_id = t.getText().toString();
                MainDrawer mainDrawer = (MainDrawer) getActivity();
                mainDrawer.onChangeFragment(FragmentTag.NEWS_DETAIL, view_id);
            }
        });
    }

}
