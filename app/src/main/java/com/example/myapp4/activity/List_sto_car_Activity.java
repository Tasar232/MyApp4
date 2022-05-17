package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp4.R;
import com.example.myapp4.logic.cars.Car;

public class List_sto_car_Activity extends AppCompatActivity {
    TextView marklocal, modellocal;
    ImageView imCarLocal;
   //Repository repository ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sto_car);
        //repository = App.getRepository();
        marklocal = findViewById(R.id.markLoc);
        modellocal = findViewById(R.id.modelLoc);
        imCarLocal = findViewById(R.id.ivCarLoc);

        Intent intent = getIntent();

        Car car = (Car) intent.getParcelableExtra("car");

        marklocal.setText(car.getMark());
        modellocal.setText(car.getModel());
        imCarLocal.setImageResource(R.drawable.car);
    }
}