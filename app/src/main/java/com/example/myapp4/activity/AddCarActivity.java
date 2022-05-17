package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapp4.App;
import com.example.myapp4.R;

public class AddCarActivity extends AppCompatActivity {
    EditText edMark;
    EditText edModel;
    EditText edMil;
    EditText edYear;
    EditText edSer;
    EditText edNum;
    EditText edGosNum;
    Button btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car_activity);
        edMark = findViewById(R.id.tvMark);
        edModel = findViewById(R.id.tvModel);
        edMil = findViewById(R.id.tvMileage);
        edYear = findViewById(R.id.tvYear);
        edSer = findViewById(R.id.tvSerSTS);
        edNum = findViewById(R.id.tvNumSTS);
        edGosNum = findViewById(R.id.tvGosNum);
        btAdd = findViewById(R.id.btAdd);



        btAdd.setOnClickListener(new View.OnClickListener() {

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