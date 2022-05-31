package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapp4.App;
import com.example.myapp4.R;
import com.example.myapp4.logic.sto.ItemServiceMaintenanceCar;

import java.util.ArrayList;

public class Edit_item_act_service_maintenance_car_activity extends AppCompatActivity {
    private EditText name, code, count, priceItem,  priceWork;
    private Button btEdit;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_act_service_maintenance_car);
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
        setAddActServiceMaintenanceCarButton();
    }

    private void setSpinnerItem(){

        ArrayList<String> arrTypeItemName  = App.getAllTypeItemName();

        spinner = findViewById(R.id.spinnerAddItem);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrTypeItemName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (spinner.getSelectedItemPosition()){
                    case 0:
                        priceItem.setEnabled(true);
                        code.setEnabled(true);
                        break;
                    case 1:
                        priceItem.setText("0");
                        priceItem.setEnabled(false);
                        code.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setAddActServiceMaintenanceCarButton(){
        Intent intent = getIntent();
        int id_car = intent.getIntExtra("id_car", -1);
        int id_act_service_maintenance = intent.getIntExtra("id_act_service_maintenance", -1);
        int id_item = intent.getIntExtra("id_item", -1);
        ItemServiceMaintenanceCar itemServiceMaintenanceCar = App.get_workPartItem_for_IDcar_and_IDserviceMaintenance_and_IDItem(id_car, id_act_service_maintenance, id_item);

        spinner.setSelection(itemServiceMaintenanceCar.getId_type_item()-1);
        name = findViewById(R.id.etAddNameItem);
        name.setText(itemServiceMaintenanceCar.getName());
        code = findViewById(R.id.etAddCodeItem);
        code.setText(itemServiceMaintenanceCar.getCodeItem());

        priceItem = findViewById(R.id.etAddItemPriceItem);
        String pi = String.valueOf(itemServiceMaintenanceCar.getPriceItem());
        priceItem.setText(pi);
        priceWork = findViewById(R.id.etAddItemPriceWork);
        String pw = String.valueOf(itemServiceMaintenanceCar.getPriceWork());
        priceWork.setText(pw);

        count = findViewById(R.id.etAddCountItem);
        count.setText(String.valueOf(itemServiceMaintenanceCar.getCount()));
        btEdit = findViewById(R.id.btAddItemActServiceMaintenance);
        btEdit.setText("Сохранить");



        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                int iid_type_work = spinner.getSelectedItemPosition()+1;
                String toastStr = "детали";
                if (iid_type_work != 1){
                    toastStr = "работы";
                }

                String sname = name.getText().toString();
                if (sname.equals("")){
                    toast = Toast.makeText(Edit_item_act_service_maintenance_car_activity.this, "Введите название " + toastStr, Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                String scode = code.getText().toString();
                if (scode.equals("")){
                    scode = "Не указано";
                }
                int icount = 1;
                try {
                    icount = Integer.parseInt(count.getText().toString());
                }
                catch (NumberFormatException e){}

                int ipriceWork = 0;
                int ipriceItem = 0;
                try {
                    ipriceWork = Integer.parseInt(priceWork.getText().toString());
                    ipriceItem = Integer.parseInt(priceItem.getText().toString());
                } catch (NumberFormatException e) {}

                final int iipriceWork = ipriceWork;
                final int iipriceItem = ipriceItem;
                final int iicount = icount;

                App.updateItem(
                        id_item,
                        iid_type_work,
                        scode,
                        sname,
                        iicount,
                        iipriceItem,
                        iipriceWork
                );
                finish();
            }
        });
    }

}