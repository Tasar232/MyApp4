package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.myapp4.App;
import com.example.myapp4.R;
import com.example.myapp4.activity.models_and_adapters.AdapterCar;
import com.example.myapp4.logic.OSAGO.PolicyOSAGO;
import com.example.myapp4.logic.cars.Car;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterCar.ItemOnClickListenerCar, AdapterCar.ItemOnLongClickListenerCar {
    private AdapterCar adapter;
    private int mCurrentItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeAdapter();

        FloatingActionButton fab = findViewById(R.id.floatingActionButton3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddCarActivity.class);
                startActivity(intent);
                //
                //App.readData();
                //initializeAdapter();

                //RecyclerView recyclerView = findViewById(R.id.list_cars);
                //isChangingConfigurations();
                //adapter.notifyDataSetChanged();
                //recyclerView.notify();
                //initializeAdapter();
                //onRestart();
                //onResume();
                //adapter.updateData(App.getListCars());
            }
        });

    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeAdapter();
    }


    private void initializeAdapter(){
        RecyclerView recyclerView = findViewById(R.id.list_cars);

        adapter = new AdapterCar(this, App.getListCars(), this, this);

        recyclerView.setAdapter(adapter);

        //recyclerView.refreshDrawableState();
        //recyclerView.notify();

    }



    @Override
    public void onClickListenerRecyclerViewMore_Car(int position) {
        Intent intent = new Intent(this, List_sto_car_Activity.class);
        int id = App.getListCars().get(position).getId();
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void onLongClickListenerRecyclerViewMore_Car(int position, View view) {
        PopupMenu popup = new PopupMenu(this, view);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.deleteContext:
                        int id = App.getListCars().get(position).getId();
                        App.deleteCar(id);
                        initializeAdapter();
                        //onRestart();
                        break;
                }
                return false;
            }
        });

        popup.inflate(R.menu.context_recycler_menu);
        popup.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);


    }



}