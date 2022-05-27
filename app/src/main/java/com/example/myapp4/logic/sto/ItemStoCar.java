package com.example.myapp4.logic.sto;

public class ItemStoCar {
    private int id_item;
    private int id_type_item;
    private String code;
    private String name;
    private int count;
    private int priceItem;
    private int priceWork;

    public ItemStoCar(int id_item, int id_type_item, String code_item, String name, int count, int priceItem, int priceWork){
        this.id_item = id_item;
        this.id_type_item = id_type_item;
        this.code = code_item;
        this.name = name;
        this.count = count;
        this.priceItem = priceItem;
        this.priceWork = priceWork;
    }

    public int getId_item(){return id_item;}
    public int getId_type_item(){return id_type_item;}
    public String getCodeItem(){return code;}
    public String getName(){return name;}
    public int getCount(){return count;}
    public int getPriceWork(){return priceWork;}
    public int getPriceItem(){return  priceItem;}
}
