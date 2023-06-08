package com.example.demopaserjson.objectdemo;

public class Item {

    private String _id;
    private String url;
    private String prompt;

    private  String img;


    public Item(String _id, String url, String prompt, String img) {
        this._id = _id;
        this.url = url;
        this.prompt = prompt;
        this.img = img;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Item{" +
                "_id='" + _id + '\'' +
                ", url='" + url + '\'' +
                ", prompt='" + prompt + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

}
