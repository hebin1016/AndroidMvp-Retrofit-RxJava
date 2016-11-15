package com.bin.mvpproject.bean;

import java.util.List;

/**
 * author：hebin on 2016/11/15 09:58
 * email：hb494974108@gmail.com
 */
public class Root {
    private int error;

    private String status;

    private String date;

    private List<Results> results ;

    public void setError(int error){
        this.error = error;
    }
    public int getError(){
        return this.error;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public void setResults(List<Results> results){
        this.results = results;
    }
    public List<Results> getResults(){
        return this.results;
    }

    @Override
    public String toString() {
        return "Root{" +
                "error=" + error +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", results=" + results +
                '}';
    }
}
