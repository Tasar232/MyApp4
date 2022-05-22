package com.example.myapp4.dataBase;

import com.example.myapp4.logic.cars.Car;

import java.net.PortUnreachableException;
import java.util.ArrayList;

public interface Repository_data_car {

    public ArrayList<Car> getAllCarsData();

    public void addCar(String mark, String model, int mileage, int year, String seriesSTS, int numberSTS, String gosNumber);
    public void addSTO(int id_car, int id_work, String date, int mileage_now, String name_company, String description, int price);
    public void addItem(int id_sto, String code, String name, int count, int price);
    public void addPolicy();

    public void updateCar();
    public void updateSTO();
    public void updateItem();
    public void updatePolicy();

    public void deleteCar(int id_car);
    public void deleteSTO(int id_sto);
    public void deleteItem(int id_item);
    public void deletePolicy();

    public String getWorkString(int id_work);

    public void addColumn();
}
