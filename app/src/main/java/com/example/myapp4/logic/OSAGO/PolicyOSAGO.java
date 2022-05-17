package com.example.myapp4.logic.OSAGO;

import java.util.Date;

public class PolicyOSAGO {
    private int id;
    private String nameCompany;
    private String series;
    private String number;
    private String dateStart;
    private String dateFinish;
    //private Date

    public PolicyOSAGO(String nameCompany,
                       String series,
                       String number,
                       String dateStart,
                       String dateFinish){
        this.nameCompany = nameCompany;
        this.series = series;
        this.number = number;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public String getNameCompany(){
        return nameCompany;
    }
    public String getSeries(){
        return series;
    }
    public String getNumber(){
        return number;
    }
    public String getDateStart(){
        return dateStart;
    }
    public String getDateFinish(){
        return dateFinish;
    }


}
