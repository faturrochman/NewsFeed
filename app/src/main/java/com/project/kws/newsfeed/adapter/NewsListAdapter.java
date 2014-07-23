package com.project.kws.newsfeed.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.kws.newsfeed.R;

import java.util.ArrayList;

import beans.MaterialBean;
import lazylist.ImageLoader;

/**
 * Created by Fajar on 7/19/2014.
 */
public class NewsListAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<String> news;
    private ArrayList<MaterialBean> materialBeans;
    private static LayoutInflater inflater = null;
    public ImageLoader imageLoader;
    private TypedArray imgs;
    private String text_news[];

    public NewsListAdapter(Activity activity, ArrayList<MaterialBean> materialBeans) {
        this.activity = activity;
        this.materialBeans = materialBeans;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imageLoader = new ImageLoader(activity.getApplicationContext());
        imgs = activity.getResources().obtainTypedArray(R.array.news_list_img);
        text_news = activity.getResources().getStringArray(R.array.news_list_txt);
    }

    @Override
    public int getCount() {
        return materialBeans.size();
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
            holder.iv_img = (ImageView) view.findViewById(R.id.iv_news_img);
            holder.id = (TextView) view.findViewById(R.id.id_news);
            holder.tv_title = (TextView) view.findViewById(R.id.tv_news_title);
            holder.tv_time = (TextView) view.findViewById(R.id.tv_news_time);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.id.setText(materialBeans.get(position).getMaterial_id());

        holder.tv_title.setText(text_news[(position%4)]+" ("+materialBeans.get(position).getMaterial_name()+")");
        Typeface tf = Typeface.createFromAsset(activity.getAssets(), "fonts/Arvo-Regular.ttf");
        holder.tv_title.setTypeface(tf);
        String hours_ago = Integer.toString(position) + " hour(s) ago. ";
        holder.tv_time.setText(hours_ago+materialBeans.get(position).getMaterial_code());
        holder.iv_img.setImageResource(imgs.getResourceId((position%4), -1));
//        ImageView imageView = holder.iv_img;
//        imageLoader.DisplayImage("", imageView);
        return view;
    }

    public static class ViewHolder{
        public ImageView iv_img;
        public TextView id, tv_title, tv_time;
    }
}
