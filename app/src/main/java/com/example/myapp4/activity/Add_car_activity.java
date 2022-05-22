package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapp4.App;
import com.example.myapp4.R;

public class Add_car_activity extends AppCompatActivity {
    EditText edMark, edModel, edMil, edYear, edSer, edNum, edGosNum;
    Button btAddCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        setAddCarButton();
    }
    private void setAddCarButton(){
        edMark = findViewById(R.id.etMarkAddCar);
        edModel = findViewById(R.id.etModelAddCar);
        edMil = findViewById(R.id.etMileageAddCar);
        edYear = findViewById(R.id.etYearAddCar);
        edSer = findViewById(R.id.etSerSTS);
        edNum = findViewById(R.id.etNumSTSAddCar);
        edGosNum = findViewById(R.id.etGosNumAddCar);
        btAddCar = findViewById(R.id.btAddCar);



        btAddCar.setOnClickListener(new View.OnClickListener() {

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

                App.addCar(mark,
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