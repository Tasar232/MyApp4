package com.example.myapp4.logic.sto;

import java.util.ArrayList;

public class StoCar {
    private int id_sto;
    private String date;
    private int mileage_now;
    private String nameCompany;
    private int typeOfWork;
    private String textDescription;
    private int totalPrice;
    private ArrayList<ItemStoCar> list_item;

    public StoCar(int id_sto, int typeOfWork, String date, int mileage_now, String nameCompany, String textDescription, int totalPrice){
        this.id_sto = id_sto;
        this.typeOfWork = typeOfWork;
        this.date = date;
        this.mileage_now = mileage_now;
        this.nameCompany = nameCompany;
        this.textDescription = textDescription;
        this.totalPrice = totalPrice;
        this.list_item = new ArrayList<>();
    }

    public int getIdSTO(){return id_sto;}
    public String getDate(){return date;}
    public int getMileageNow(){return mileage_now;}
    public String getNameCompany(){return nameCompany;}
    public int getTypeOfWork(){return typeOfWork;}
    public String getText(){return textDescription;}
    public int getTotalPrice(){return totalPrice;}

    public ArrayList<ItemStoCar> getListItemSTO(){return list_item;}
    public ItemStoCar getItem(int index){return list_item.get(index);}
    public void addItemSTO(ItemStoCar itemStoCar){list_item.add(itemStoCar);}
    public void remoteItemSTO(int index){list_item.remove(index);}
    public void editItemSTO(ItemStoCar itemCarSTO, int index){
        list_item.set(index, itemCarSTO);
    }
    public int getSizeItemsSTO(){return list_item.size();}
}
