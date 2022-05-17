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
                App.addCar(edMark.getText().toString(),
                        edModel.getText().toString(),
                        Integer.parseInt(edMil.getText().toString()),
                        Integer.parseInt(edYear.getText().toString()),
                        edSer.getText().toString(),
                        Integer.parseInt(edNum.getText().toString()),
                        edGosNum.getText().toString()
                );
                finish();
            }
        });


    }
}