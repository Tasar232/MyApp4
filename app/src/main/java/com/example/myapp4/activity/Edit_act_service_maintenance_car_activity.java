package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.myapp4.logic.sto.ServiceMaintenanceCar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Edit_act_service_maintenance_car_activity extends AppCompatActivity {
    private Spinner spinner;
    private EditText date, company, mileage_now;
    private MultiAutoCompleteTextView descrip;
    private Button btEditServiceMaintenance;
    private Calendar dateAndTime = Calendar.getInstance();

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
        setAddServiceMaintenanceCarButton();
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

    private void setAddServiceMaintenanceCarButton(){
        Intent intent = getIntent();
        int id_car =  intent.getIntExtra("id_car", -1);
        int id_act_service_maintenance =  intent.getIntExtra("id_act_service_maintenance", -1);
        ServiceMaintenanceCar serviceMaintenanceCar = App.get_serviceMeintenance_for_IDcar_And_IDserviceMaintenance(id_car, id_act_service_maintenance);

        spinner.setSelection(serviceMaintenanceCar.getIDTypeOfWork());
        date = findViewById(R.id.etDateAddActServiceMaintenance);
        date.setText(serviceMaintenanceCar.getDate());
        company = findViewById(R.id.etCompanyAddActServiceMaintenance);
        company.setText(serviceMaintenanceCar.getNameCompany());
        descrip = findViewById(R.id.mtvDescAddActServiceMaintenance);
        descrip.setText(serviceMaintenanceCar.getTextDescription());
        mileage_now = findViewById(R.id.etMileageNowAddActServiceMaintenance);
        mileage_now.setText(String.valueOf(serviceMaintenanceCar.getMileageNow()));
        btEditServiceMaintenance = findViewById(R.id.btAddActServiceMaintenance);
        btEditServiceMaintenance.setText("Сохранить");
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
        btEditServiceMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner.getSelectedItemPosition() == 0){
                    Toast toast = Toast.makeText(Edit_act_service_maintenance_car_activity.this, "Выберете вид работы", Toast.LENGTH_LONG);
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
                    App.updateServiceMaintenance(
                            id_act_service_maintenance,
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
        new DatePickerDialog(Edit_act_service_maintenance_car_activity.this, d,
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