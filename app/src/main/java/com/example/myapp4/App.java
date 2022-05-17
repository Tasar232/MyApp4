package com.example.myapp4;

import android.app.Application;

import com.example.myapp4.dataBase.DBhelper;
import com.example.myapp4.dataBase.Repository_data_car;
import com.example.myapp4.logic.OSAGO.PolicyOSAGO;
import com.example.myapp4.logic.cars.Car;

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
        listCars = db_car.getData();
        //Connect DB
        //setInitialData();
    }
    public static ArrayList<Car> getListCars(){
        return listCars;
    }



    public static void addCar( String mark, String model, int mileage, int year, String seriesSTS, int numberSTS){
        /*int id = 1;
        if (listCars.size() != 0){
            id = listCars.get(listCars.size()-1).getId() + 1;
        }
        //listCars.add(new Car(id, mark, model, mileage, year, seriesSTS, numberSTS));*/
        db_car.addCar(mark, model, mileage, year, seriesSTS, numberSTS);
        //listCars = db_car.getData();
    }

    private void setInitialData(){
        /*listCars.add(new Car ("Toyota", "Mark2"));
        listCars.add(new Car ("Nissan", "Levin"));
        listCars.add(new Car ("Toyota", "LandCruiser"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        listCars.add(new Car ("Mercedes", "E63MG"));
        */
        //listCars.get(0).getListPolicy().addPolicy(new PolicyOSAGO("salo", "nety", "Da", "segodny"));
    }
}
