package com.example.myapp4.logic.sto;

public class ItemStoCar {
    private int id_item;
    private int id_type_work;
    private String code;
    private String name;
    private int count;
    private int priceItem;
    private int priceWork;

    public ItemStoCar(int id_item, int id_type_work, String code_item, String name, int count, int priceItem, int priceWork){
        this.id_item = id_item;
        this.id_type_work = id_type_work;
        this.code = code_item;
        this.name = name;
        this.count = count;
        this.priceItem = priceItem;
        this.priceWork = priceWork;
    }

    public int getId_item(){return id_item;}
    public int getId_type_work(){return id_type_work;}
    public String getCodeItem(){return code;}
    public String getName(){return name;}
    public int getCount(){return count;}
    public int getPriceWork(){return priceItem;}
    public int getPriceItem(){return  priceWork;}
}
