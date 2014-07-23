package com.project.kws.newsfeed.news;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.ArrayList;

/**
 * Created by Fajar on 7/23/2014.
 */
public class NewsParser {

    private String responseJson;

    public NewsParser(String responseJson) {
        this.responseJson = responseJson;
    }

    public ArrayList<NewsBean> parse(){
        ArrayList<NewsBean> newsBeans = new ArrayList<NewsBean>();
        NewsBean newsBean;
        JSONObject jObj;
        JSONArray jArr;

        try {
            Object json = new JSONTokener(responseJson).nextValue();
            if(json instanceof JSONObject){
                jObj = (JSONObject) json;
                newsBean = getItem(jObj);
                newsBeans.add(newsBean);
            }else if(json instanceof JSONArray){
                jArr = (JSONArray) json;
                for(int i=0; i<jArr.length(); i++){
                    jObj = jArr.getJSONObject(i);
                    newsBean = getItem(jObj);
                    newsBeans.add(newsBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsBeans;
    }

    private NewsBean getItem(JSONObject jObj){

        NewsBean newsBean = new NewsBean();

        try {
            if(jObj.getString(NewsBean.NEWS_ID)!=null)
                newsBean.setNews_id(jObj.getString(NewsBean.NEWS_ID));
            if(jObj.getString(NewsBean.NEWS_TITLE)!=null)
                newsBean.setNews_title(jObj.getString(NewsBean.NEWS_TITLE));
            if(jObj.getString(NewsBean.NEWS_CAT)!=null)
                newsBean.setNews_category(jObj.getString(NewsBean.NEWS_CAT));
            if(jObj.getString(NewsBean.NEWS_DATE)!=null)
                newsBean.setNews_date(jObj.getString(NewsBean.NEWS_DATE));
            if(jObj.getString(NewsBean.NEWS_PICT)!=null)
                newsBean.setNews_picture(jObj.getString(NewsBean.NEWS_PICT));
            if(jObj.getString(NewsBean.NEWS_AUTH)!=null)
                newsBean.setNews_author(jObj.getString(NewsBean.NEWS_AUTH));
            if(jObj.getString(NewsBean.NEWS_CONTENT)!=null)
                newsBean.setNews_content(jObj.getString(NewsBean.NEWS_CONTENT));
            if(jObj.getString(NewsBean.NEWS_EXCERPT)!=null)
                newsBean.setNews_excerpt(jObj.getString(NewsBean.NEWS_EXCERPT));
            if(jObj.getString(NewsBean.NEWS_TIME)!=null)
                newsBean.setNews_timestamp(jObj.getString(NewsBean.NEWS_TIME));
            if(jObj.getString(NewsBean.DEL)!=null)
                newsBean.setDeleted(jObj.getString(NewsBean.DEL));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsBean;
    }

}
