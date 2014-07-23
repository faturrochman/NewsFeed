package com.project.kws.newsfeed.news;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.kws.newsfeed.R;
import com.project.kws.newsfeed.factory.Font;

import java.util.ArrayList;

import helper.ImageURLValidation;
import lazylist.ImageLoader;

/**
 * Created by Fajar on 7/19/2014.
 */
public class NewsListAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<NewsBean> newsBeans;
    private static LayoutInflater inflater = null;
    public ImageLoader imageLoader;

    public NewsListAdapter(Activity activity, ArrayList<NewsBean> newsBeans) {
        this.activity = activity;
        this.newsBeans = newsBeans;
        this.imageLoader = new ImageLoader(activity.getApplicationContext());
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return newsBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if(convertView==null){
            view = inflater.inflate(R.layout.listrow_news_item, null);
            holder = new ViewHolder();
            holder.id_news = (TextView) view.findViewById(R.id.id_news);
            holder.iv_news_img = (ImageView) view.findViewById(R.id.iv_news_img);
            holder.tv_news_title = (TextView) view.findViewById(R.id.tv_news_title);
            holder.tv_news_time = (TextView) view.findViewById(R.id.tv_news_time);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.id_news.setText(newsBeans.get(position).getNews_id());

        imageLoader.DisplayImage(ImageURLValidation.validate(newsBeans.get(position).getNews_picture()), holder.iv_news_img);

        holder.tv_news_title.setText(newsBeans.get(position).getNews_title());
        holder.tv_news_title.setTypeface(Font.getFont(activity, Font.ROBOTOSLAB_REG));
        holder.tv_news_title.setMaxLines(2);
        holder.tv_news_title.setEllipsize(TextUtils.TruncateAt.END);

        holder.tv_news_time.setText(newsBeans.get(position).getNews_date());
        holder.tv_news_time.setTypeface(Font.getFont(activity, Font.ROBOTOSLAB_THIN));
        return view;
    }

    public static class ViewHolder{
        public ImageView iv_news_img;
        public TextView id_news, tv_news_title, tv_news_time;
    }
}
