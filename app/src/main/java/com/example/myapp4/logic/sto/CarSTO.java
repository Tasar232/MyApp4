package com.example.myapp4.logic.sto;

public class CarSTO {
    private int id_sto;
    private String date;
    private String nameCompany;
    private int typeOfWork;
    private String textDescription;
    private int totalPrice;

    public CarSTO(int id_sto, int typeOfWork, String date, String nameCompany, String textDescription, int totalPrice){
        this.id_sto = id_sto;
        this.typeOfWork = typeOfWork;
        this.date = date;
        this.nameCompany = nameCompany;
        this.textDescription = textDescription;
        this.totalPrice = totalPrice;
    }

    public int getIdSTO(){return id_sto;}
    public String getDate(){return date;}
    public String getNameCompany(){return nameCompany;}
    public int getTypeOfWork(){return typeOfWork;}
    public String getText(){return textDescription;}
    public int getTotalPrice(){return totalPrice;}
}
