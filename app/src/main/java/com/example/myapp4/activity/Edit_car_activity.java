package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapp4.App;
import com.example.myapp4.R;
import com.example.myapp4.logic.cars.Car;

public class Edit_car_activity extends AppCompatActivity {
    EditText edMark, edModel, edMil, edYear, edSer, edNum, edGosNum;
    Button btSetCar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onStart() {
        super.onStart();
        setAddCarButton();
    }

    private void setAddCarButton(){
        Intent intent = getIntent();
        int id_car =  intent.getIntExtra("id_car", 0);
        Car car = App.getCarForID(id_car);

        edMark = findViewById(R.id.etMarkAddCar);
        edMark.setText(car.getMark());
        edModel = findViewById(R.id.etModelAddCar);
        edModel.setText(car.getModel());
        edMil = findViewById(R.id.etMileageAddCar);
        edMil.setText(String.valueOf(car.getMileageCar()));
        edYear = findViewById(R.id.etYearAddCar);
        edYear.setText(String.valueOf(car.getYearCar()));
        edSer = findViewById(R.id.etSerSTS);
        edSer.setText(car.getSeriesSTS());
        edNum = findViewById(R.id.etNumSTSAddCar);
        edNum.setText(String.valueOf(car.getNumberSTS()));
        edGosNum = findViewById(R.id.etGosNumAddCar);
        edGosNum.setText(car.getGosNumber());
        btSetCar = findViewById(R.id.btAddCar);
        btSetCar.setText("Сохранить");



        btSetCar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String mark = edMark.getText().toString();
                String model = edModel.getText().toString();
                int mileage = 0;
                try{
                    mileage = Integer.parseInt(edMil.getText().toString());
                }
                catch(NumberFormatException e){
                }
                int year = 0;
                try {
                    year = Integer.parseInt(edYear.getText().toString());
                }
                catch (NumberFormatException e){
                }
                String serial = edSer.getText().toString();
                int number = 0;
                try {
                    number = Integer.parseInt(edNum.getText().toString());
                }
                catch (NumberFormatException e){
                }
                String gos = edGosNum.getText().toString();

                final int mil = mileage;
                final int yea = year;
                final int num = number;

                App.updateCar(
                        id_car,
                        mark,
                        model,
                        mil,
                        yea,
                        serial,
                        num,
                        gos
                );
                App.readData();
                finish();
            }
        });
    }

}