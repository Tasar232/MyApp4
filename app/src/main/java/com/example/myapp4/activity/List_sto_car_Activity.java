package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp4.App;
import com.example.myapp4.R;
import com.example.myapp4.logic.cars.Car;

public class List_sto_car_Activity extends AppCompatActivity {
    private TextView mark, model, mileage, year, gos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sto_car);
        mark = findViewById(R.id.tvMark2);
        model = findViewById(R.id.tvModel2);
        mileage = findViewById(R.id.tvMileage2);
        year = findViewById(R.id.tvYear2);
        gos = findViewById(R.id.tvGos2);

        Intent intent = getIntent();

        int id =  intent.getIntExtra("id", 0);
        Car car = App.getCarForID(id);

        mark.setText(car.getMark());
        model.setText(car.getModel());
        if(car.getMileageCar() == 0){
            mileage.setText("Не указано");
        }
        else {
            mileage.setText(String.valueOf(car.getMileageCar()));
        }
        if(car.getYearCar() == 0){
            year.setText("Не указано");
        }
        else {
            year.setText(String.valueOf(car.getYearCar()));
        }
        gos.setText(car.getGosNumber());

    }
}