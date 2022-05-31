package com.example.myapp4.logic.cars;

import com.example.myapp4.logic.OSAGO.PolicyOSAGO;
import com.example.myapp4.logic.sto.ServiceMaintenanceCar;

import java.util.ArrayList;


public class Car {
    private int id;
    private String mark;
    private String model;
    private int yearCar;
    private int mileageCar;
    private String reg_certificate;
    private String gosNumber;
    private ArrayList<PolicyOSAGO> listPolicy;
    private ArrayList<ServiceMaintenanceCar> listSto;

    public Car(int id, String mark, String model, int mileage, int year, String reg_certificate, String gosNumber) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.yearCar = year;
        this.mileageCar = mileage;
        this.reg_certificate = reg_certificate;
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
    public String getRegCertificate(){return reg_certificate;}

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
    public ArrayList<ServiceMaintenanceCar> getListServiceMaintenance(){return listSto;}
    public ServiceMaintenanceCar getServiceMaintenance(int index){return listSto.get(index);}
    public void addServiceMaintenance(ServiceMaintenanceCar serviceMaintenanceCar){listSto.add(serviceMaintenanceCar);}
    public void remoteServiceMaintenance(int index){listSto.remove(index);}
    public void editServiceMaintenance(ServiceMaintenanceCar serviceMaintenanceCar, int index){
        listSto.set(index, serviceMaintenanceCar);
    }
    public int getSizeServiceMaintenances(){return listSto.size();}

}

