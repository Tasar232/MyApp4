package com.example.myapp4.logic.sto;

public class CarSTO {
    private int id;
    private String date;
    private String nameCompany;
    private String typeOfWork;
    private String text;
    private int totalPrice;

    public CarSTO(String date, String nameCompany, String typeOfWork, String text, int totalPrice){
        this.date = date;
        this.nameCompany = nameCompany;
        this.typeOfWork = typeOfWork;
        this.text = text;
        this.totalPrice = totalPrice;
    }

    public String getDate(){return date;}
    public String getNameCompany(){return nameCompany;}
    public String getTypeOfWork(){return typeOfWork;}
    public String getText(){return text;}
    public int getTotalPrice(){return totalPrice;}
}
