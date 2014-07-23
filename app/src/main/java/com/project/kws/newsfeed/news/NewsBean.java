package com.project.kws.newsfeed.news;

/**
 * Created by Fajar on 7/23/2014.
 */
public class NewsBean {

    public static final String NEWS_ID = "news_id";
    public static final String NEWS_TITLE = "news_title";
    public static final String NEWS_CAT = "news_category";
    public static final String NEWS_DATE = "news_date";
    public static final String NEWS_PICT = "news_picture";
    public static final String NEWS_AUTH = "news_author";
    public static final String NEWS_CONTENT = "news_content";
    public static final String NEWS_EXCERPT = "news_excerpt";
    public static final String NEWS_TIME = "news_timestamp";
    public static final String DEL = "deleted";

    private String news_id;
    private String news_title;
    private String news_category;
    private String news_date;
    private String news_picture;
    private String news_author;
    private String news_content;
    private String news_excerpt;
    private String news_timestamp;
    private String deleted;

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_category() {
        return news_category;
    }

    public void setNews_category(String news_category) {
        this.news_category = news_category;
    }

    public String getNews_date() {
        return news_date;
    }

    public void setNews_date(String news_date) {
        this.news_date = news_date;
    }

    public String getNews_picture() {
        return news_picture;
    }

    public void setNews_picture(String news_picture) {
        this.news_picture = news_picture;
    }

    public String getNews_author() {
        return news_author;
    }

    public void setNews_author(String news_author) {
        this.news_author = news_author;
    }

    public String getNews_content() {
        return news_content;
    }

    public void setNews_content(String news_content) {
        this.news_content = news_content;
    }

    public String getNews_excerpt() {
        return news_excerpt;
    }

    public void setNews_excerpt(String news_excerpt) {
        this.news_excerpt = news_excerpt;
    }

    public String getNews_timestamp() {
        return news_timestamp;
    }

    public void setNews_timestamp(String news_timestamp) {
        this.news_timestamp = news_timestamp;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}
