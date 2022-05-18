package com.example.myapp4.logic.cars;

import com.example.myapp4.logic.OSAGO.PolicyOSAGO;
import com.example.myapp4.logic.sto.CarSTO;

import java.util.ArrayList;


public class Car {
    private int id;
    private String mark;
    private String model;
    private int yearCar;
    private int mileageCar;
    private String seriesSTS;
    private int numberSTS;
    private String gosNumber;
    private ArrayList<PolicyOSAGO> listPolicy;
    private ArrayList<CarSTO> listSto;

    public Car(int id, String mark, String model, int mileage, int year, String seriesSTS, int numberSTS, String gosNumber) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.yearCar = year;
        this.mileageCar = mileage;
        this.seriesSTS = seriesSTS;
        this.numberSTS = numberSTS;
        this.gosNumber = gosNumber;
        this.listPolicy =  new ArrayList<>();
        this.listSto = new ArrayList<>();
    }

    //Что касается машины
    public int getId(){return id;}

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public String getGosNumber(){return gosNumber;}

    public int getYearCar(){return yearCar;}

    public int getMileageCar(){return mileageCar;}

    public String getSeriesSTS(){return seriesSTS; }

    public int getNumberSTS(){return numberSTS;}

    ///Что касается списка полисов ОСАГО
    public ArrayList<PolicyOSAGO> getListPolicy(){
        return listPolicy;
    }

    public PolicyOSAGO getPolicy(int index){
        return listPolicy.get(index);
    }

    public void addPolicy(PolicyOSAGO policyOSAGO){
        listPolicy.add(policyOSAGO);
    }

    public void remotePolicy(int index){
        listPolicy.remove(index);
    }

    public void editPolicy(PolicyOSAGO policyOSAGO, int index){
        listPolicy.set(index, policyOSAGO);
    }

    public int getSizePolicy(){
        return listPolicy.size();
    }


    //Что касается списка СТО
    public ArrayList<CarSTO> getListSto(){return listSto;}

    public CarSTO getSTO(int index){return listSto.get(index);}

    public void addSTO(CarSTO carsto){listSto.add(carsto);}

    public void remoteSTO(int index){listSto.remove(index);}

    public void editSTO(CarSTO carsto, int index){
        listSto.set(index, carsto);
    }

    public int getSizeSTOs(){return listPolicy.size();}

}

