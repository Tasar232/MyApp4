package com.example.myapp4.dataBase;

import com.example.myapp4.logic.cars.Car;

import java.net.PortUnreachableException;
import java.util.ArrayList;

public interface Repository_data_car {

    public ArrayList<Car> getData();

    public void addCar(String mark, String model, int mileage, int year, String seriesSTS, int numberSTS, String gosNumber);
    public void addSTO(int id_car, int id_work, String date, String name_company, String description, int price);
    public void addItem();
    public void addPolicy();

    public void updateCar();
    public void updateSTO();
    public void updateItem();
    public void updatePolicy();

    public void deleteCar(int id_car);
    public void deleteSTO(int id_sto);
    public void deleteItem();
    public void deletePolicy();
}
