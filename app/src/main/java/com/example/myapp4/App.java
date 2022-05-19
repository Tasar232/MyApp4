package com.example.myapp4;

import android.app.Application;

import com.example.myapp4.dataBase.DBhelper;
import com.example.myapp4.dataBase.Repository_data_car;
import com.example.myapp4.logic.OSAGO.PolicyOSAGO;
import com.example.myapp4.logic.cars.Car;
import com.example.myapp4.logic.sto.CarSTO;

import java.util.ArrayList;

public class App extends Application {
    private static App app;
    private static ArrayList<Car> listCars = new ArrayList<Car>();
    private static Repository_data_car db_car;

    @Override
    public void onCreate(){
        super.onCreate();
        app=this;

        db_car = new DBhelper(this);
        readData();
    }
    public static ArrayList<Car> getListCars(){
        return listCars;
    }

    public static void readData(){
        listCars.clear();
        listCars.addAll(db_car.getData());
    }

    public static void addCar( String mark, String model, int mileage, int year, String seriesSTS, int numberSTS, String gosNumber){
        db_car.addCar(mark, model, mileage, year, seriesSTS, numberSTS, gosNumber);
        readData();
    }

    public static void deleteCar(int id){
        db_car.deleteCar(id);
        readData();
    }

    public static Car getCarForID(int id){
        int pos = 0;
        for (int i = 0; i < listCars.size(); i++){
            if(listCars.get(i).getId() == id){
                pos = i;
            }
        }
        return listCars.get(pos);
    }

    public static ArrayList<CarSTO> getListStoCar(int id){
        ArrayList<CarSTO> list_sto = getCarForID(id).getListSto();
        return list_sto;
    }

    public static void addSTO(int id_car, int id_work, String date, String name_company, String description, int price){
        db_car.addSTO(id_car, id_work, date, name_company, description, price);
        readData();
    }

    public static void deleteSTO(int id_sto){
        db_car.deleteSTO(id_sto);
        readData();
    }


}
