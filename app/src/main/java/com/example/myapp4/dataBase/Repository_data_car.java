package com.example.myapp4.dataBase;

import com.example.myapp4.logic.cars.Car;
import com.example.myapp4.logic.sto.ItemServiceMaintenanceCar;
import com.example.myapp4.logic.sto.ServiceMaintenanceCar;

import java.util.ArrayList;

public interface Repository_data_car {

    public ArrayList<Car> getAllCarsData();

    public void addCar(Car car);
    public void addServiceMaintenance(int id_car, ServiceMaintenanceCar serviceMaintenanceCar);
    public void addSparePartAndWorkItem(int id_service_maintenance, ItemServiceMaintenanceCar itemServiceMaintenanceCar);
    public void addPolicy();

    public void updateCar(Car car);
    public void updateServiceMaintenance(ServiceMaintenanceCar serviceMaintenanceCar);
    public void updateSparePartAndWorkItem(ItemServiceMaintenanceCar itemServiceMaintenanceCar);
    public void updatePolicy();

    public void deleteCar(int id_car);
    public void deleteServiceMaintenance(int id_service_maintenance);
    public void deleteSparePartAndWorkItem(int id_item);
    public void deletePolicy();

    public String getTypeWorkName(int id_type_name_work);
    public ArrayList<String> getAllTypeWorkName();

    public String getTypeItemName(int id_type_name_item);
    public ArrayList<String> getAllTypeItemName();

    public void addColumn();
}
