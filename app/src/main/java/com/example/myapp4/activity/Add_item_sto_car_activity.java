package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapp4.App;
import com.example.myapp4.R;

import java.util.ArrayList;

public class Add_item_sto_car_activity extends AppCompatActivity {
    EditText name, code, count, priceItem,  priceWork;
    Button btAdd;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_sto_car);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setSpinnerItem();
        setAddSTOCarButton();
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
                        priceItem.setText("");
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

    private void setAddSTOCarButton(){
        name = findViewById(R.id.etAddNameItem);
        code = findViewById(R.id.etAddCodeItem);
        priceItem = findViewById(R.id.etAddItemPriceItem);
        priceWork = findViewById(R.id.etAddItemPriceWork);
        count = findViewById(R.id.etAddCountItem);
        btAdd = findViewById(R.id.btAddItemSto);



        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sname = name.getText().toString();
                String scode = code.getText().toString();
                int icount = 0;
                try {
                    icount = Integer.parseInt(count.getText().toString());
                }
                catch (NumberFormatException e){
                    Toast toast = Toast.makeText(Add_item_sto_car_activity.this, "Введите количество числом", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                int ipriceWork = 0;
                int ipriceItem = 0;
                try {
                    ipriceWork = Integer.parseInt(priceWork.getText().toString());
                    ipriceItem = Integer.parseInt(priceItem.getText().toString());
                } catch (NumberFormatException e) {
                    Toast toast = Toast.makeText(Add_item_sto_car_activity.this, "Введите цену числом", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                int iid_type_work = spinner.getSelectedItemPosition()+1;

                final int iipriceWork = ipriceWork;
                final int iipriceItem = ipriceItem;
                final int iicount = icount;

                Intent intent = getIntent();
                int id_car = intent.getIntExtra("id_car", 0);
                int id_sto = intent.getIntExtra("id_sto", 0);
                App.addItem(
                        id_sto,
                        iid_type_work,
                        scode,
                        sname,
                        iicount,
                        iipriceItem,
                        iipriceWork
                );
                App.readData();
                finish();

            }
        });
    }
}