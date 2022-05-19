package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;

import com.example.myapp4.App;
import com.example.myapp4.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Add_sto_car_activity extends AppCompatActivity {
    Spinner spinner;
    EditText date, company, price;
    MultiAutoCompleteTextView descrip;
    Button btAddSto;
    Calendar dateAndTime=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sto_car);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setSpinnerItem();
        setAddSTOCarButton();
        setInitialDateTime();
    }

    private void setSpinnerItem(){
        String[] itemWork = {
                "Вид работы",
                "Расходники",
                "Ремонт",
                "Ремонт своими рукми"
        };

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, itemWork);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setAddSTOCarButton(){
        date = findViewById(R.id.etDate);
        company = findViewById(R.id.etCompany);
        price = findViewById(R.id.etPrice);
        descrip = findViewById(R.id.mtvDesc);
        btAddSto = findViewById(R.id.btAddSTO);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate(view);
            }
        });
        btAddSto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sdate = date.getText().toString();
                String scompany = company.getText().toString();
                String sdescrip = descrip.getText().toString();
                int iprice = 0;
                try{
                    iprice = Integer.parseInt(price.getText().toString());
                }
                catch(NumberFormatException e){
                }

                final int iiprice = iprice;
                final int id_work = spinner.getSelectedItemPosition();
                Intent intent = getIntent();
                int id_car = intent.getIntExtra("id_car", 0);
                App.addSTO(
                        id_car,
                        id_work,
                        sdate,
                        scompany,
                        sdescrip,
                        iiprice
                );
                App.readData();
                finish();
            }
        });
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(Add_sto_car_activity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }


    // установка начальных даты и времени
    private void setInitialDateTime() {
        date = findViewById(R.id.etDate);
        Date dateStr = dateAndTime.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = formatter.format(dateStr);

        date.setText(strDate);

    }

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
}