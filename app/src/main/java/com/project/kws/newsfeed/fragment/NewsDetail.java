package com.project.kws.newsfeed.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.kws.newsfeed.R;

import java.util.ArrayList;

import beans.MaterialBean;
import helper.DialogHelper;
import network.KotakwarnaHttpRequest;
import parser.MaterialParser;

/**
 * Created by Fajar on 7/19/2014.
 */
public class NewsDetail extends Fragment {

    private View viewHierarchy;
    private ImageView iv_news_img, iv_source_img;
    private TextView tv_news_title, tv_source_name, tv_full_news;
    private DialogHelper dialogHelper;
    private ProgressDialog progressDialog;
    private ArrayList<MaterialBean> materialBeans;
    private String URL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewHierarchy = inflater.inflate(R.layout.f_newsdetail, container, false);
        URL = getResources().getString(R.string.api_material_view);
        URL = URL+getArguments().getString("id");
        Log.i("ID", URL);

        dialogHelper = new DialogHelper();
        progressDialog = dialogHelper.buildProgressDialog(getActivity(), "Loading...");

        new DownloadTextTask().execute();
        return viewHierarchy;
    }

    private class DownloadTextTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            KotakwarnaHttpRequest httpRequest = new KotakwarnaHttpRequest(URL);
            String jSonResponse = httpRequest.requestGet();
            return jSonResponse;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(String result) {

            progressDialog.dismiss();
            MaterialParser materialParser = new MaterialParser();
            materialBeans = materialParser.parse(result);
            MaterialBean materialBean = materialBeans.get(0);

            tv_source_name = (TextView) viewHierarchy.findViewById(R.id.tv_source_name);
            tv_source_name.setText(materialBean.getMaterial_code());

            tv_news_title = (TextView) viewHierarchy.findViewById(R.id.tv_news_title);
            tv_news_title.setText(materialBean.getMaterial_name());

            ArrayList<String> materialDetails = new ArrayList<String>();
            materialDetails.add("Material ID = " + materialBean.getMaterial_id());
            materialDetails.add("Material Code = " + materialBean.getMaterial_code());
            materialDetails.add("Material Name = " + materialBean.getMaterial_name());
            materialDetails.add("Material Unit = " + materialBean.getMaterial_unit());
            materialDetails.add("Material Category = " + materialBean.getMaterial_category());
            materialDetails.add("Material Remark = " + materialBean.getMaterial_remark());
            materialDetails.add("Deleted = " + materialBean.getDeleted());
            materialDetails.add("Created By = " + materialBean.getCreated_by());
            materialDetails.add("Modified By = " + materialBean.getModified_by());
            materialDetails.add("Created On = " + materialBean.getCreated_on());
            materialDetails.add("Modified On = " + materialBean.getModified_on());
            materialDetails.add("Material Category ID = " + materialBean.getMaterial_category_id());
            materialDetails.add("Material Category Name = " + materialBean.getMaterial_category_name());
            materialDetails.add("Material Category Remark = " + materialBean.getMaterial_category_remark());

            tv_full_news = (TextView) viewHierarchy.findViewById(R.id.tv_full_news);
            String all = "";
            for(String detail:materialDetails){
                all = all+detail+"\n";
            }
            tv_full_news.setText(all);

        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.full_news, menu);
    }


}
