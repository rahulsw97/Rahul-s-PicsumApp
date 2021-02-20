package com.rahulsw.picsumapp;

public class OutputImg {
    private  int id ;
    private  String filename;
    private  String author;
    private  String post_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return post_url;
    }

    public void setUrl(String post_url) {
        this.post_url = post_url;
    }
}
