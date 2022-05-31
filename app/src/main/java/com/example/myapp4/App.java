package com.example.myapp4;

import android.app.Application;

import com.example.myapp4.dataBase.SQLiteDBCar;
import com.example.myapp4.dataBase.Repository_data_car;
import com.example.myapp4.logic.cars.Car;
import com.example.myapp4.logic.sto.ItemServiceMaintenanceCar;
import com.example.myapp4.logic.sto.ServiceMaintenanceCar;

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
    public static ArrayList<ServiceMaintenanceCar> getListServiceMaintenanceCarForID(int id_car){
        ArrayList<ServiceMaintenanceCar> list_service_maintenance = getCarForID(id_car).getListServiceMaintenance();
        return list_service_maintenance;
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
    public static ArrayList<ItemServiceMaintenanceCar> getListItemActServiceMaintenanceCar(int id_car, int id_service_maintenance){
        ArrayList<ItemServiceMaintenanceCar> list_spare_part_and_work_item = get_serviceMeintenance_for_IDcar_And_IDserviceMaintenance(id_car, id_service_maintenance).getListItemServiceMaintenance();
        return  list_spare_part_and_work_item;
    }
    public static ServiceMaintenanceCar get_serviceMeintenance_for_IDcar_And_IDserviceMaintenance(int id_car, int id_service_maintenance){
        Car car = getCarForID(id_car);
        int pos = 0;
        for (int i = 0; i < car.getListServiceMaintenance().size(); i++){
            if (car.getListServiceMaintenance().get(i).getIdServiceMaintenance() == id_service_maintenance){
                pos = i;
            }
        }
        return car.getListServiceMaintenance().get(pos);
    }
    public static ItemServiceMaintenanceCar get_workPartItem_for_IDcar_and_IDserviceMaintenance_and_IDItem(int id_car, int id_service_maintenance, int id_item){
        ServiceMaintenanceCar serviceMaintenanceCar = get_serviceMeintenance_for_IDcar_And_IDserviceMaintenance(id_car, id_service_maintenance);
        int pos = 0;
        for (int i = 0; i < serviceMaintenanceCar.getListItemServiceMaintenance().size(); i++){
            if (serviceMaintenanceCar.getListItemServiceMaintenance().get(i).getId_item() == id_item){
                pos = i;
            }
        }
        return serviceMaintenanceCar.getListItemServiceMaintenance().get(pos);
    }



    public static void addCar(String mark, String model, int mileage, int year, String reg_certificate, String gosNumber){
        Car car = new Car(0, mark, model, mileage, year, reg_certificate, gosNumber);
        db_car.addCar(car);
        readData();
    }
    public static void deleteCar(int id){
        db_car.deleteCar(id);
        readData();
    }

    public static void updateCar(int id_car, String mark, String model, int mileage, int year, String reg_certificate, String gosNumber) {
        Car car = new Car(id_car, mark, model, mileage, year, reg_certificate, gosNumber);
        db_car.updateCar(car);
        readData();
    }


    public static void addServiceMaintenance(int id_car, int id_work, String date, int mileage_now, String name_company, String description){
        ServiceMaintenanceCar serviceMaintenanceCar = new ServiceMaintenanceCar(0, id_work, date, mileage_now,name_company, description);
        db_car.addServiceMaintenance(id_car, serviceMaintenanceCar);
        readData();
    }
    public static void deleteServiceMaintenance(int id_service_maintenance){
        db_car.deleteServiceMaintenance(id_service_maintenance);
        readData();
    }
    public static void updateServiceMaintenance(int id_service_maintenance, int id_work, String date, int mileage_now, String name_company, String description){
        ServiceMaintenanceCar serviceMaintenanceCar = new ServiceMaintenanceCar(id_service_maintenance, id_work, date, mileage_now,name_company, description);
        db_car.updateServiceMaintenance(serviceMaintenanceCar);
        readData();
    }




    public static void addItem(int id_sto, int id_type_item, String code, String name, int count, int priceItem, int priceWork){
        ItemServiceMaintenanceCar itemServiceMaintenanceCar =
                new ItemServiceMaintenanceCar(
                        0,
                        id_type_item,
                        code,
                        name,
                        count,
                        priceItem,
                        priceWork
                );
        db_car.addSparePartAndWorkItem(id_sto, itemServiceMaintenanceCar);
        readData();
    }
    public static void deleteItem(int id_item){
        db_car.deleteSparePartAndWorkItem(id_item);
        readData();
    }
    public static void updateItem(int id_item, int id_type_item, String code, String name, int count, int priceItem, int priceWork){
        ItemServiceMaintenanceCar itemServiceMaintenanceCar =
                new ItemServiceMaintenanceCar(
                        id_item,
                        id_type_item,
                        code,
                        name,
                        count,
                        priceItem,
                        priceWork
                );
        db_car.updateSparePartAndWorkItem(itemServiceMaintenanceCar);
        readData();
    }

}
