package com.example.yxs.titlebar;

/**
 * Created by yxs on 16/3/19.
 */
public class Diary {

    private Integer id;
    private String title;
    private String content;
    private String datetime;

    public Diary(String title, String content, String datetime) {
        this.title = title;
        this.content = content;
        this.datetime = datetime;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }


    @Override
    public String toString() {
        return "Diary [title=" + title + ", content=" + content + ", datetime="
                + datetime + "]";
    }

}
