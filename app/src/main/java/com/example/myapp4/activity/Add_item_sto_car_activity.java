package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp4.App;
import com.example.myapp4.R;

public class Add_item_sto_car_activity extends AppCompatActivity {
    EditText name, code, count, price;
    Button btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_sto_car);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setAddSTOCarButton();
    }

    private void setAddSTOCarButton(){
        name = findViewById(R.id.etAddNameItem);
        code = findViewById(R.id.etAddCodeItem);
        price = findViewById(R.id.etAddPriceItem);
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

                int iprice = 0;
                try {
                    iprice = Integer.parseInt(price.getText().toString());
                } catch (NumberFormatException e) {
                    Toast toast = Toast.makeText(Add_item_sto_car_activity.this, "Введите цену числом", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                final int iiprice = iprice;
                final int iicount = icount;

                Intent intent = getIntent();
                int id_car = intent.getIntExtra("id_car", 0);
                int id_sto = intent.getIntExtra("id_sto", 0);
                App.addItem(
                        id_sto,
                        scode,
                        sname,
                        iicount,
                        iiprice
                );
                App.readData();
                finish();

            }
        });
    }
}