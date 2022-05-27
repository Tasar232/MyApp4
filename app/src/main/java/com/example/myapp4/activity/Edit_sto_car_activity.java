package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapp4.App;
import com.example.myapp4.R;
import com.example.myapp4.logic.sto.ItemStoCar;
import com.example.myapp4.logic.sto.StoCar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Edit_sto_car_activity extends AppCompatActivity {
    private Spinner spinner;
    private EditText date, company, mileage_now;
    private MultiAutoCompleteTextView descrip;
    private Button btAddSto;
    private Calendar dateAndTime=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sto_car);
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

    @Override
    protected void onStart() {
        super.onStart();
        setSpinnerItem();
        setAddSTOCarButton();
    }

    private void setSpinnerItem(){
        ArrayList<String> arrItemWork  = new ArrayList<>();
        arrItemWork.add("Вид работы");
        arrItemWork.addAll(App.getAllTypeWorkName());

        spinner = findViewById(R.id.spinnerAddSTO);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrItemWork);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setAddSTOCarButton(){
        Intent intent = getIntent();
        int id_car =  intent.getIntExtra("id_car", 0);
        int id_sto =  intent.getIntExtra("id_sto", 0);
        StoCar stoCar = App.getStoForIDcarAndIDsto(id_car, id_sto);

        spinner.setSelection(stoCar.getTypeOfWork());
        date = findViewById(R.id.etDateAddSto);
        date.setText(stoCar.getDate());
        company = findViewById(R.id.etCompanyAddSto);
        company.setText(stoCar.getNameCompany());
        descrip = findViewById(R.id.mtvDescAddSto);
        descrip.setText(stoCar.getText());
        mileage_now = findViewById(R.id.etMileageNowAddSto);
        mileage_now.setText(String.valueOf(stoCar.getMileageNow()));
        btAddSto = findViewById(R.id.btAddSTO);
        btAddSto.setText("Сохранить");

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate(view);
            }
        });
        btAddSto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner.getSelectedItemPosition() == 0){
                    Toast toast = Toast.makeText(Edit_sto_car_activity.this, "Выберете вид работы", Toast.LENGTH_LONG);
                    toast.show();

                }
                else {
                    String sdate = date.getText().toString();
                    String scompany = company.getText().toString();
                    String sdescrip = descrip.getText().toString();

                    int imileagenow = 0;
                    try {
                        imileagenow = Integer.parseInt(mileage_now.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast toast = Toast.makeText(Edit_sto_car_activity.this, "Введите текщий пробег числом", Toast.LENGTH_LONG);
                        toast.show();
                        return;
                    }


                    final int iimileagenow = imileagenow;
                    final int id_work = spinner.getSelectedItemPosition();
                    App.updateSTO(
                            id_sto,
                            id_work,
                            sdate,
                            iimileagenow,
                            scompany,
                            sdescrip
                    );
                    finish();
                }
            }
        });
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(Edit_sto_car_activity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }


    // установка начальных даты и времени
    private void setInitialDateTime() {
        date = findViewById(R.id.etDateAddSto);
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