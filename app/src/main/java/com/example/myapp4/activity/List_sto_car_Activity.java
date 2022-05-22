package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.myapp4.App;
import com.example.myapp4.R;
import com.example.myapp4.activity.models_and_adapters.AdapterSTO;
import com.example.myapp4.logic.cars.Car;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class List_sto_car_Activity extends AppCompatActivity implements AdapterSTO.ItemOnClickListenerSTO, AdapterSTO.ItemOnLongClickListenerSTO {
    private TextView mark, model, mileage, year, gos;
    private ImageView imCar;
    private AdapterSTO adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sto_car);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeInfCar();
        initializeAdapterSTO();
        setOnClickFAB();
    }

    private void setOnClickFAB(){
        FloatingActionButton fab = findViewById(R.id.fabAddSTO);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                int id_car = intent.getIntExtra("id_car", 0);

                Intent intentAdd = new Intent(view.getContext(), Add_sto_car_activity.class);
                intentAdd.putExtra("id_car", id_car);
                startActivity(intentAdd);
            }
        });
    }

    private void initializeInfCar(){
        mark = findViewById(R.id.tvMarkInfCarEdit);
        model = findViewById(R.id.tvModelInfCarEdit);
        mileage = findViewById(R.id.tvMileageInfCarEdit);
        year = findViewById(R.id.tvYearInfCarEdit);
        gos = findViewById(R.id.tvGosInfCarEdit);
        imCar = findViewById(R.id.ivCarSTO);

        imCar.setImageResource(R.drawable.ic_car);

        Intent intent = getIntent();
        int id_car =  intent.getIntExtra("id_car", 0);
        Car car = App.getCarForID(id_car);

        mark.setText(car.getMark());
        model.setText(car.getModel());
        if(car.getMileageCar() == 0){
            mileage.setText("Не указано");
        }
        else {
            mileage.setText(String.valueOf(car.getMileageCar()));
        }
        if(car.getYearCar() == 0){
            year.setText("Не указано");
        }
        else {
            year.setText(String.valueOf(car.getYearCar()));
        }
        gos.setText(car.getGosNumber());
    }
    private void initializeAdapterSTO(){
        Intent intent = getIntent();
        int id_car =  intent.getIntExtra("id_car", 0);

        RecyclerView recyclerView = findViewById(R.id.rvList_sto);
        adapter = new AdapterSTO(this, App.getListStoCar(id_car), this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickListenerRecyclerViewMore_STO(int position) {
        Intent intent = getIntent();
        int id_car = intent.getIntExtra("id_car", 0);
        int id_sto = App.getCarForID(id_car).getListSto().get(position).getIdSTO();

        Intent intentItemSto = new Intent(this, List_item_sto_car_Activity.class);
        intentItemSto.putExtra("id_sto", id_sto);
        intentItemSto.putExtra("id_car", id_car);
        startActivity(intentItemSto);
    }

    @Override
    public void onLongClickListenerRecyclerViewMore_STO(int position, View view) {
        PopupMenu popup = new PopupMenu(this, view);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.deleteContext:
                        Intent intent = getIntent();
                        int id_car = intent.getIntExtra("id_car", 0);
                        int id_sto = App.getCarForID(id_car).getListSto().get(position).getIdSTO();
                        App.deleteSTO(id_sto);
                        initializeAdapterSTO();
                        break;
                }
                return false;
            }
        });

        popup.inflate(R.menu.context_recycler_menu);
        popup.show();
    }
}