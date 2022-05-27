package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_recycler_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = getIntent();
        int id_car =  intent.getIntExtra("id_car", -1);
        switch (item.getItemId()) {
            case R.id.deleteContext:

                String title = "Удаление!!!";
                String message = "Вы действительно хотите удалить машину?";
                String yesString = "Да";
                String cancelString = "Отмена";

                AlertDialog.Builder builder = new AlertDialog.Builder(List_sto_car_Activity.this);
                builder.setTitle(title);  // заголовок
                builder.setMessage(message); // сообщение
                builder.setPositiveButton(yesString, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        App.deleteCar(id_car);
                        finish();
                    }
                });
                builder.setNegativeButton(cancelString, new DialogInterface.OnClickListener() {
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
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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
        if(car.getMileageCar() == -1){
            mileage.setText("Не указано");
        }
        else {
            mileage.setText(String.valueOf(car.getMileageCar()));
        }
        if(car.getYearCar() == -1){
            year.setText("Не указано");
        }
        else {
            year.setText(String.valueOf(car.getYearCar()));
        }
        gos.setText(car.getGosNumber());
    }
    private void initializeAdapterSTO(){
        Intent intent = getIntent();
        int id_car =  intent.getIntExtra("id_car", -1);

        RecyclerView recyclerView = findViewById(R.id.rvList_sto);
        adapter = new AdapterSTO(this, App.getListStoCarForID(id_car), this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickListenerRecyclerViewMore_STO(int position) {
        Intent intent = getIntent();
        int id_car = intent.getIntExtra("id_car", -1);
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
                Intent intent = getIntent();
                int id_car = intent.getIntExtra("id_car", -1);
                int id_sto = App.getCarForID(id_car).getListSto().get(position).getIdSTO();
                switch (menuItem.getItemId()) {
                    case R.id.deleteContext:
                        String title = "Удаление!!!";
                        String message = "Вы действительно хотите удалить СТО?";
                        String button1String = "Да";
                        String button2String = "Отмена";

                        AlertDialog.Builder builder = new AlertDialog.Builder(List_sto_car_Activity.this);
                        builder.setTitle(title);  // заголовок
                        builder.setMessage(message); // сообщение
                        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                App.deleteSTO(id_sto);
                                initializeAdapterSTO();
                                Toast.makeText(List_sto_car_Activity.this, "СТО удалено!", Toast.LENGTH_LONG)
                                        .show();
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
                        Intent intentEditSto = new Intent(getApplicationContext(), Edit_sto_car_activity.class);
                        intentEditSto.putExtra("id_sto", id_sto);
                        intentEditSto.putExtra("id_car", id_car);
                        startActivity(intentEditSto);
                        break;

                }
                return false;
            }
        });

        popup.inflate(R.menu.context_recycler_menu);
        popup.show();
    }

}