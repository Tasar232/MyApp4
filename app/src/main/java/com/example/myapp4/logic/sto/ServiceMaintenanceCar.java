package com.example.myapp4.logic.sto;

import java.util.ArrayList;

public class ServiceMaintenanceCar {
    private int id_service_maintenance;
    private String date;
    private int mileage_now;
    private String nameCompany;
    private int idTypeOfWork;
    private String textDescription;
    private int totalPrice;
    private ArrayList<ItemServiceMaintenanceCar> list_item;

    public ServiceMaintenanceCar(int id_service_maintenance, int idTypeOfWork, String date, int mileage_now, String nameCompany, String textDescription){
        this.id_service_maintenance = id_service_maintenance;
        this.idTypeOfWork = idTypeOfWork;
        this.date = date;
        this.mileage_now = mileage_now;
        this.nameCompany = nameCompany;
        this.textDescription = textDescription;
        this.totalPrice = 0;
        this.list_item = new ArrayList<>();
    }

    public int getIdServiceMaintenance(){return id_service_maintenance;}
    public String getDate(){return date;}
    public int getMileageNow(){return mileage_now;}
    public String getNameCompany(){return nameCompany;}
    public int getIDTypeOfWork(){return idTypeOfWork;}
    public String getTextDescription(){return textDescription;}
    public int getTotalPrice(){
        int price = 0;
        for (int i = 0; i < list_item.size(); i++){
            price += (list_item.get(i).getPriceItem() + list_item.get(i).getPriceWork())* list_item.get(i).getCount();
        }
        return price;
    }
    public void addTotalPrice(){

    }

    public ArrayList<ItemServiceMaintenanceCar> getListItemServiceMaintenance(){return list_item;}
    public ItemServiceMaintenanceCar getItem(int index){return list_item.get(index);}
    public void addItemServiceMaintenance(ItemServiceMaintenanceCar itemStoCar){list_item.add(itemStoCar);}
    public void remoteItemServiceMaintenance(int index){list_item.remove(index);}
    public void editItemServiceMaintenance(ItemServiceMaintenanceCar itemCarSTO, int index){
        list_item.set(index, itemCarSTO);
    }
    public int getSizeItemsServiceMaintenance(){return list_item.size();}
}
