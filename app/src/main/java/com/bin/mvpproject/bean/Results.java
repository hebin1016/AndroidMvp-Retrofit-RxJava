package com.bin.mvpproject.bean;

import java.util.List;

/**
 * author：hebin on 2016/11/15 09:57
 * email：hb494974108@gmail.com
 */
public class Results {
    private String currentCity;

    private String pm25;

    private List<Index> index ;

    private List<Weather_data> weather_data ;

    public void setCurrentCity(String currentCity){
        this.currentCity = currentCity;
    }
    public String getCurrentCity(){
        return this.currentCity;
    }
    public void setPm25(String pm25){
        this.pm25 = pm25;
    }
    public String getPm25(){
        return this.pm25;
    }
    public void setIndex(List<Index> index){
        this.index = index;
    }
    public List<Index> getIndex(){
        return this.index;
    }
    public void setWeather_data(List<Weather_data> weather_data){
        this.weather_data = weather_data;
    }
    public List<Weather_data> getWeather_data(){
        return this.weather_data;
    }

    @Override
    public String toString() {
        return "Results{" +
                "currentCity='" + currentCity + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", index=" + index +
                ", weather_data=" + weather_data +
                '}';
    }
}
