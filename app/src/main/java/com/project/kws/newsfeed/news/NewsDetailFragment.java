package com.project.kws.newsfeed.news;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.kws.newsfeed.R;
import com.project.kws.newsfeed.factory.Font;

import java.util.ArrayList;

import helper.DialogHelper;
import helper.ImageURLValidation;
import lazylist.ImageLoader;
import network.KotakwarnaHttpRequest;

/**
 * Created by Fajar on 7/19/2014.
 */
public class NewsDetailFragment extends Fragment {

    private View viewHierarchy;
    private ImageView iv_news_img, iv_source_img;
    private TextView tv_news_title, tv_source_name, tv_source_time, tv_full_news;
    private DialogHelper dialogHelper;
    private ProgressDialog progressDialog;
    private ImageLoader imageLoader;
    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewHierarchy = inflater.inflate(R.layout.f_newsdetail, container, false);
        init();
        new DownloadTextTask().execute();
        return viewHierarchy;
    }

    private void init(){
        url = getResources().getString(R.string.news_getspec);
        url = url +getArguments().getString("id");
        dialogHelper = new DialogHelper();
        imageLoader = new ImageLoader(getActivity().getApplicationContext());
        progressDialog = dialogHelper.buildProgressDialog(getActivity(), "Loading...");
        iv_news_img = (ImageView) viewHierarchy.findViewById(R.id.iv_news_img);
        tv_source_name = (TextView) viewHierarchy.findViewById(R.id.tv_source_name);
        tv_source_time = (TextView) viewHierarchy.findViewById(R.id.tv_source_time);
        tv_news_title = (TextView) viewHierarchy.findViewById(R.id.tv_news_title);
        tv_full_news = (TextView) viewHierarchy.findViewById(R.id.tv_full_news);
    }

    private class DownloadTextTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            KotakwarnaHttpRequest httpRequest = new KotakwarnaHttpRequest(url);
            String jSonResponse = httpRequest.requestGet();
            return jSonResponse;
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
            NewsBean newsBean = newsBeans.get(0);

            imageLoader.DisplayImage(ImageURLValidation.validate(newsBean.getNews_picture()), iv_news_img);

            tv_source_name.setText(newsBean.getNews_author());
            tv_source_name.setTypeface(Font.getFont(getActivity(), Font.ROBOTOSLAB_THIN));
            tv_source_time.setText(newsBean.getNews_timestamp());
            tv_source_time.setTypeface(Font.getFont(getActivity(), Font.ROBOTOSLAB_LIGHT));
            tv_news_title.setText(newsBean.getNews_title());
            tv_news_title.setTypeface(Font.getFont(getActivity(), Font.ROBOTOSLAB_BOLD));

            /*CONTENT*/
            tv_full_news.setText(Html.fromHtml(newsBean.getNews_content()));
            tv_full_news.setTypeface(Font.getFont(getActivity(), Font.ROBOTOSLAB_REG));


        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.full_news, menu);
    }


}
