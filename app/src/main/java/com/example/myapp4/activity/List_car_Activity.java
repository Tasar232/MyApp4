package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.myapp4.App;
import com.example.myapp4.R;
import com.example.myapp4.activity.models_and_adapters.AdapterCar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class List_car_Activity extends AppCompatActivity implements AdapterCar.ItemOnClickListenerCar, AdapterCar.ItemOnLongClickListenerCar {
    private AdapterCar adapter;
    private int mCurrentItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_car);
        setOnClickFAB();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeAdapterCar();
    }


    private void setOnClickFAB(){
        FloatingActionButton fab = findViewById(R.id.fabAddCar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Add_car_activity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeAdapterCar(){
        RecyclerView recyclerView = findViewById(R.id.rvList_car);
        adapter = new AdapterCar(this, App.getListCars(), this, this);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onClickListenerRecyclerViewMore_Car(int position) {
        Intent intent = new Intent(this, List_act_service_maintenance_car_Activity.class);
        int id_car = App.getListCars().get(position).getId();
        intent.putExtra("id_car", id_car);
        startActivity(intent);
    }

    @Override
    public void onLongClickListenerRecyclerViewMore_Car(int position, View view) {
        PopupMenu popup = new PopupMenu(this, view);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id_car = App.getListCars().get(position).getId();
                switch (menuItem.getItemId()) {
                    case R.id.deleteContext:
                        String title = "????????????????!!!";
                        String message = "???? ?????????????????????????? ???????????? ?????????????? ?????????????";
                        String button1String = "????";
                        String button2String = "????????????";

                        AlertDialog.Builder builder = new AlertDialog.Builder(List_car_Activity.this);
                        builder.setTitle(title);  // ??????????????????
                        builder.setMessage(message); // ??????????????????
                        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                App.deleteCar(id_car);
                                Toast.makeText(List_car_Activity.this, "???????????? ??????????????!", Toast.LENGTH_LONG)
                                        .show();
                                initializeAdapterCar();
                            }
                        });
                        builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                        builder.setCancelable(true);
                        builder.create();
                        builder.show();

                        break;
                    case R.id.editContext:
                        Intent intentEditCar = new Intent(getApplicationContext(), Edit_car_activity.class);
                        intentEditCar.putExtra("id_car", id_car);
                        startActivity(intentEditCar);
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