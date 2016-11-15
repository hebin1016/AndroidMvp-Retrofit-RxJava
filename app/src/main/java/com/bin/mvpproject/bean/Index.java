package com.bin.mvpproject.bean;

/**
 * author：hebin on 2016/11/15 09:56
 * email：hb494974108@gmail.com
 */
public class Index {
    private String title;

    private String zs;

    private String tipt;

    private String des;

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setZs(String zs){
        this.zs = zs;
    }
    public String getZs(){
        return this.zs;
    }
    public void setTipt(String tipt){
        this.tipt = tipt;
    }
    public String getTipt(){
        return this.tipt;
    }
    public void setDes(String des){
        this.des = des;
    }
    public String getDes(){
        return this.des;
    }

    @Override
    public String toString() {
        return "Index{" +
                "title='" + title + '\'' +
                ", zs='" + zs + '\'' +
                ", tipt='" + tipt + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
