package com.example.myapp4;

import android.app.Application;

import com.example.myapp4.dataBase.DBhelper;
import com.example.myapp4.dataBase.Repository_data_car;
import com.example.myapp4.logic.cars.Car;
import com.example.myapp4.logic.sto.ItemStoCar;
import com.example.myapp4.logic.sto.StoCar;

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
        //db_car.addColumn();
        readData();
    }

    public static void readData(){
        listCars.clear();
        listCars.addAll(db_car.getAllCarsData());
        int i = 0;
    }

    public static ArrayList<Car> getListCars(){
        return listCars;
    }

    public static ArrayList<StoCar> getListStoCar(int id_car){
        ArrayList<StoCar> list_sto = getCarForID(id_car).getListSto();
        return list_sto;
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

    public static ArrayList<ItemStoCar> getListItemStoCar(int id_car, int id_sto){
        ArrayList<ItemStoCar> list_item = getStoForIDcarAndIDsto(id_car, id_sto).getListItemSTO();
        return  list_item;
    }

    public static StoCar getStoForIDcarAndIDsto(int id_car, int id_sto){
        Car car = getCarForID(id_car);
        int pos = 0;
        for (int i = 0; i < car.getListSto().size(); i++){
            if (car.getListSto().get(i).getIdSTO() == id_sto){
                pos = i;
            }
        }
        return car.getListSto().get(pos);
    }


    public static ItemStoCar getItemStoForIDcarAndIDstoAndIDItem(int id_car, int id_Sto, int id_item){
        StoCar stoCar = getStoForIDcarAndIDsto(id_car, id_Sto);
        int pos = 0;
        for (int i = 0; i < stoCar.getListItemSTO().size(); i++){
            if (stoCar.getListItemSTO().get(i).getId_item() == id_item){
                pos = i;
            }
        }
        return stoCar.getListItemSTO().get(pos);
    }



    public static void addCar( String mark, String model, int mileage, int year, String seriesSTS, int numberSTS, String gosNumber){
        db_car.addCar(mark, model, mileage, year, seriesSTS, numberSTS, gosNumber);
        readData();
    }

    public static void deleteCar(int id){
        db_car.deleteCar(id);
        readData();
    }





    public static void addSTO(int id_car, int id_work, String date, String name_company, String description, int price){
        db_car.addSTO(id_car, id_work, date, name_company, description, price);
        readData();
    }

    public static void deleteSTO(int id_sto){
        db_car.deleteSTO(id_sto);
        readData();
    }


    public static void addItem(int id_sto, String code, String name, int count, int price){
        db_car.addItem(id_sto, code, name, count, price);
        readData();
    }

    public static void deleteItem(int id_item){
        db_car.deleteItem(id_item);
        readData();
    }

}
