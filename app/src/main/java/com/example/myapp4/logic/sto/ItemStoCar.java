package com.example.myapp4.logic.sto;

public class ItemStoCar {
    private int id_item;
    private String code;
    private String name;
    private int count;
    private int price;

    public ItemStoCar(int id_item, String code_item, String name, int count, int price){
        this.id_item = id_item;
        this.code = code_item;
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public int getId_item(){return id_item;}
    public String getCodeItem(){return code;}
    public String getName(){return name;}
    public int getCount(){return count;}
    public int getPrice(){return price;}
}
