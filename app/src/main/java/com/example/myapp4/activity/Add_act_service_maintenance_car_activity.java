package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DateKeyListener;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapp4.App;
import com.example.myapp4.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Add_act_service_maintenance_car_activity extends AppCompatActivity {
    private Spinner spinner;
    private EditText date, company, price, mileage_now;
    private MultiAutoCompleteTextView descrip;
    private Button btAddSto;
    private Calendar dateAndTime=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_act_service_maintenance_car);
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
        setInitialDateTime();
    }

    private void setSpinnerItem(){
        ArrayList<String> arrItemWork  = new ArrayList<>();
        arrItemWork.add("Вид работы");
        arrItemWork.addAll(App.getAllTypeWorkName());

        spinner = findViewById(R.id.spinnerAddActServiceMaintenance);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrItemWork);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setAddSTOCarButton(){
        date = findViewById(R.id.etDateAddActServiceMaintenance);
        date.setFocusable(View.NOT_FOCUSABLE);

        company = findViewById(R.id.etCompanyAddActServiceMaintenance);
        descrip = findViewById(R.id.mtvDescAddActServiceMaintenance);
        mileage_now = findViewById(R.id.etMileageNowAddActServiceMaintenance);
        btAddSto = findViewById(R.id.btAddActServiceMaintenance);
        ConstraintLayout constraintLayout = findViewById(R.id.clAddActServiceMaintenance);
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

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate(view);
            }
        });
        btAddSto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                if (spinner.getSelectedItemPosition() == 0){
                    toast = Toast.makeText(Add_act_service_maintenance_car_activity.this, "Выберете вид работы", Toast.LENGTH_LONG);
                    toast.show();

                }
                else {
                    String sdate = date.getText().toString();
                    if (sdate.equals("")){
                        setInitialDateTime();
                        sdate = date.getText().toString();
                    }
                    String scompany = company.getText().toString();
                    if (scompany.equals("")){
                        scompany = "Не указано";
                    }
                    String sdescrip = descrip.getText().toString();
                    if (sdescrip.equals("")){
                        sdescrip = "Нет описания";
                    }
                    int imileagenow = -1;
                    try {
                        imileagenow = Integer.parseInt(mileage_now.getText().toString());
                    } catch (NumberFormatException e) { }


                    final int iimileagenow = imileagenow;
                    final int id_work = spinner.getSelectedItemPosition();
                    Intent intent = getIntent();
                    int id_car = intent.getIntExtra("id_car", 0);
                    App.addServiceMaintenance(
                            id_car,
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
        new DatePickerDialog(Add_act_service_maintenance_car_activity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }


    // установка начальных даты и времени
    private void setInitialDateTime() {
        date = findViewById(R.id.etDateAddActServiceMaintenance);
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