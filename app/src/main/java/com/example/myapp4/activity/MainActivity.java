package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapp4.App;
import com.example.myapp4.R;
import com.example.myapp4.activity.models_and_adapters.AdapterCar;
import com.example.myapp4.logic.OSAGO.PolicyOSAGO;
import com.example.myapp4.logic.cars.Car;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterCar.ItemOnClickListenerCar{
    private AdapterCar adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeAdapter();

    }

    private void initializeAdapter(){
        RecyclerView recyclerView = findViewById(R.id.list_cars);
        adapter = new AdapterCar(this, App.getListCars(), this);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onClickListenerRecyclerViewMore(int position) {
        Intent intent = new Intent(this, List_sto_car_Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
    }
}