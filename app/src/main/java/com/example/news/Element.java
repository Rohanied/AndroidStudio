package com.example.news;

public class Element {
    private String heatText;
    private String subText;
    private String imgid;

    public Element(String heatText, String subText, String imgid){
        this.heatText=heatText;
        this.subText=subText;
        this.imgid=imgid;
    }

    public String getHeatText(){
        return heatText;
    }

    public String getSubText(){
        return subText;
    }

    public String getImgid(){
        return getImgid();
    }

    public void setHeatText(String heatText) {
        this.heatText = heatText;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }
}
