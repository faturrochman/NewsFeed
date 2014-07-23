package com.project.kws.newsfeed.news;

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
import com.project.kws.newsfeed.factory.FragmentTag;

import java.util.ArrayList;

import helper.DialogHelper;
import network.KotakwarnaHttpRequest;

/**
 * Created by Fajar on 7/19/2014.
 */
public class NewsListFragment extends Fragment {

    private String url;
    private View viewHierarchy;
    private ListView lv_listnews;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewHierarchy = inflater.inflate(R.layout.f_newslist, container, false);
        init();
        new DownloadTextTask().execute();
        return viewHierarchy;
    }

    private void init(){
        url = getResources().getString(R.string.news_getall);
        lv_listnews = (ListView) viewHierarchy.findViewById(R.id.lv_listnews);
        DialogHelper dialogHelper = new DialogHelper();
        progressDialog = dialogHelper.buildProgressDialog(getActivity(), "Loading...");
    }

    private class DownloadTextTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            KotakwarnaHttpRequest httpRequest = new KotakwarnaHttpRequest(url);
            return httpRequest.requestGet();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String jSonResponse) {
            progressDialog.dismiss();
            NewsParser newsParser = new NewsParser(jSonResponse);
            ArrayList<NewsBean> newsBeans = newsParser.parse();
            initListView(newsBeans);
        }
    }

    private void initListView(ArrayList<NewsBean> newsBeans){
        NewsListAdapter adapter = new NewsListAdapter(getActivity(), newsBeans);
        lv_listnews.setAdapter(adapter);
        lv_listnews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView tv_id = (TextView) view.findViewById(R.id.id_news);
            String view_id = tv_id.getText().toString();
            MainDrawer mainDrawer = (MainDrawer) getActivity();
            mainDrawer.onChangeFragment(FragmentTag.NEWS_DETAIL, view_id);
            }
        });
    }

}
