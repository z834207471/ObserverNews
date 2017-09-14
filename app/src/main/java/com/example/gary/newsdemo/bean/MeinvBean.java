package com.example.gary.newsdemo.bean;

import java.util.List;

/**
 * Created by Gary on 2017/9/12.
 */

public class MeinvBean {
    private int code;
    private String msg;
    private List<MeinvBean.MeinvListBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<MeinvBean.MeinvListBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<MeinvBean.MeinvListBean> newslist) {
        this.newslist = newslist;
    }

    public static class MeinvListBean {
        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
