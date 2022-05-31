package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp4.App;
import com.example.myapp4.R;
import com.example.myapp4.logic.cars.Car;

public class Edit_car_activity extends AppCompatActivity {
    EditText edMark, edModel, edMil, edYear, reg_certificate, edGosNum;
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
        if (car.getMileageCar() != -1) {
            edMil.setText(String.valueOf(car.getMileageCar()));
        }
        edYear = findViewById(R.id.etYearAddCar);
        if (car.getYearCar() != -1) {
            edYear.setText(String.valueOf(car.getYearCar()));
        }
        reg_certificate = findViewById(R.id.etRegCertificateAddCar);
        if (!car.getRegCertificate().equals("Не указано")) {
            reg_certificate.setText(car.getRegCertificate());
        }

        edGosNum = findViewById(R.id.etGosNumAddCar);
        if (!car.getGosNumber().equals("Не указано")) {
            edGosNum.setText(car.getGosNumber());
        }
        btSetCar = findViewById(R.id.btAddCar);
        btSetCar.setText("Сохранить");



        btSetCar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast toast;
                String mark = edMark.getText().toString();
                if (mark.equals("")){
                    toast = Toast.makeText(Edit_car_activity.this, "Введите марку автомобиля", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                String model = edModel.getText().toString();
                if (model.equals("")){
                    toast = Toast.makeText(Edit_car_activity.this, "Введите модель автомобиля", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                int mileage = -1;
                try {
                    mileage = Integer.parseInt(edMil.getText().toString());
                }
                catch (NumberFormatException e){
                }
                int year = -1;
                try {
                    year = Integer.parseInt(edYear.getText().toString());
                }
                catch (NumberFormatException e){
                }

                String regCertificate = reg_certificate.getText().toString();
                if (regCertificate.equals("")){
                    regCertificate = "Не указано";
                }


                String gos = edGosNum.getText().toString();
                if (gos.equals("")){
                    gos = "Не указано";
                }

                final int mil = mileage;
                final int yea = year;

                App.updateCar(
                        id_car,
                        mark,
                        model,
                        mil,
                        yea,
                        regCertificate,
                        gos
                );
                App.readData();
                finish();
            }
        });
    }

}