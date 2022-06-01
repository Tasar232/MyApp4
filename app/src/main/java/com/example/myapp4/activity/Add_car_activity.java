package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp4.App;
import com.example.myapp4.R;

public class Add_car_activity extends AppCompatActivity {
    private EditText edMark, edModel, edMil, edYear, edRegCertificate, edGosNum;
    private Button btAddCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setAddCarButton();
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



    private void setAddCarButton(){
        edMark = findViewById(R.id.etMarkAddCar);
        edModel = findViewById(R.id.etModelAddCar);
        edMil = findViewById(R.id.etMileageAddCar);
        edYear = findViewById(R.id.etYearAddCar);
        edRegCertificate = findViewById(R.id.etRegCertificateAddCar);
        edGosNum = findViewById(R.id.etGosNumAddCar);
        btAddCar = findViewById(R.id.btAddCar);

        ConstraintLayout constraintLayout = findViewById(R.id.clAddCar);
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(view.getContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                return view.performClick();
            }
        });


        btAddCar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast toast;
                String mark = edMark.getText().toString();
                if (mark.equals("")){
                    toast = Toast.makeText(Add_car_activity.this, "Введите марку автомобиля", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                String model = edModel.getText().toString();
                if (model.equals("")){
                    toast = Toast.makeText(Add_car_activity.this, "Введите модель автомобиля", Toast.LENGTH_LONG);
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

                String reg_certificate = edRegCertificate.getText().toString();
                if (reg_certificate.equals("")){
                    reg_certificate = "Не указано";
                }

                String gos = edGosNum.getText().toString();
                if (gos.equals("")){
                    gos = "Не указано";
                }

                final int mil = mileage;
                final int yea = year;

                App.addCar(mark,
                        model,
                        mil,
                        yea,
                        reg_certificate,
                        gos
                );
                App.readData();
                finish();
            }
        });
    }

}