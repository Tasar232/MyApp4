package com.example.myapp4;

import android.app.Application;

import com.example.myapp4.dataBase.SQLiteDBCar;
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

        db_car = new SQLiteDBCar(this);
        //db_car.addColumn();
        readData();
    }

    public static void readData(){
        listCars.clear();
        listCars.addAll(db_car.getAllCarsData());
        int i = 0;
    }



    public static String getTypeWorkName(int id_work){
        return db_car.getTypeWorkName(id_work);
    }
    public static ArrayList<String> getAllTypeWorkName(){
        return db_car.getAllTypeWorkName();
    }
    public static String getTypeItemName(int id_type_item){
        return db_car.getTypeItemName(id_type_item);
    }
    public static ArrayList<String> getAllTypeItemName(){
        return db_car.getAllTypeItemName();
    }




    public static ArrayList<Car> getListCars(){
        return listCars;
    }
    public static ArrayList<StoCar> getListStoCarForID(int id_car){
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



    public static void addCar(String mark, String model, int mileage, int year, String seriesSTS, int numberSTS, String gosNumber){
        db_car.addCar(mark, model, mileage, year, seriesSTS, numberSTS, gosNumber);
        readData();
    }
    public static void deleteCar(int id){
        db_car.deleteCar(id);
        readData();
    }

    public static void updateCar(int id_car, String mark, String model, int mileage, int year, String seriesSTS, int numberSTS, String gosNumber) {
        db_car.updateCar(id_car, mark, model,  mileage, year, seriesSTS, numberSTS, gosNumber);
        readData();
    }


    public static void addSTO(int id_car, int id_work, String date, int mileage_now, String name_company, String description){
        db_car.addSTO(id_car, id_work, date, mileage_now, name_company, description);
        readData();
    }
    public static void deleteSTO(int id_sto){
        db_car.deleteSTO(id_sto);
        readData();
    }
    public static void updateSTO(int id_sto, int id_work, String date, int mileage_now, String name_company, String description){
        db_car.updateSTO(id_sto, id_work, date, mileage_now, name_company, description);
        readData();
    }




    public static void addItem(int id_sto, int id_type_item, String code, String name, int count, int priceItem, int priceWork){
        db_car.addItem(id_sto, id_type_item, code, name, count, priceItem, priceWork);
        readData();
    }
    public static void deleteItem(int id_item){
        db_car.deleteItem(id_item);
        readData();
    }
    public static void updateItem(int id_item, int id_type_item, String code, String name, int count, int priceItem, int priceWork){
        db_car.updateItem(id_item, id_type_item, code, name, count, priceItem, priceWork);
        readData();
    }

}
