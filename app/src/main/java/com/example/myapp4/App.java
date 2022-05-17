package com.example.myapp4;

import android.app.Application;

import com.example.myapp4.logic.OSAGO.PolicyOSAGO;
import com.example.myapp4.logic.cars.Car;

import java.util.ArrayList;

public class App extends Application {
    private static App app;
    private static ArrayList<Car> listCars = new ArrayList<Car>();

    @Override
    public void onCreate(){
        super.onCreate();
        app=this;
        //Connect DB
        setInitialData();
    }
    public static ArrayList<Car> getListCars(){
        return listCars;
    }

    public static void addCar(String model, String mark, int year, int mileage, String seriesSTS, int numberSTS){
        listCars.add(new Car (mark, model, year, mileage, seriesSTS, numberSTS ));
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
