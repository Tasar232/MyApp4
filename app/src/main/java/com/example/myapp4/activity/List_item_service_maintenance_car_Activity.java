package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp4.App;
import com.example.myapp4.R;
import com.example.myapp4.activity.models_and_adapters.AdapterItemActServiceMaintenance;
import com.example.myapp4.logic.sto.ItemServiceMaintenanceCar;
import com.example.myapp4.logic.sto.ServiceMaintenanceCar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class List_item_service_maintenance_car_Activity extends AppCompatActivity implements AdapterItemActServiceMaintenance.ItemOnClickListenerItemActServiceMaintenance, AdapterItemActServiceMaintenance.ItemOnLongClickListenerItemActServiceMaintenance {
    private TextView workInf, companyInf, priceInf, descripInf, dateInf, mileageNow;
    private AdapterItemActServiceMaintenance adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_act_maintenance_car);
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
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = getIntent();
        int id_act_service_maintenance =  intent.getIntExtra("id_act_service_maintenance", -1);
        int id_car =  intent.getIntExtra("id_car", -1);
        int id = item.getItemId();
        switch(id){
            case R.id.deleteContext:
                App.deleteServiceMaintenance(id_act_service_maintenance);
                finish();
                break;
            case R.id.editContext:
                Intent intentEditSto = new Intent(getApplicationContext(), Edit_act_service_maintenance_car_activity.class);
                intentEditSto.putExtra("id_act_service_maintenance", id_act_service_maintenance);
                intentEditSto.putExtra("id_car", id_car);
                startActivity(intentEditSto);
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
        initializeInfActServiceMaintenance();
        initializeAdapterItemActServiceMaintenance();
        setOnClickFAB();
    }


    private void setOnClickFAB(){
        FloatingActionButton fab = findViewById(R.id.fabAddItemActServiceMaintenance);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                int id_act_service_maintenance =  intent.getIntExtra("id_act_service_maintenance", -1);
                int id_car =  intent.getIntExtra("id_car", -1);

                Intent intentAdd = new Intent(view.getContext(), Add_item_act_service_maintenance_car_activity.class);
                intentAdd.putExtra("id_car", id_car);
                intentAdd.putExtra("id_act_service_maintenance", id_act_service_maintenance);
                startActivity(intentAdd);
            }
        });
    }

    private void initializeAdapterItemActServiceMaintenance(){
        Intent intent = getIntent();
        int id_act_service_maintenance = intent.getIntExtra("id_act_service_maintenance", -1);
        int id_car = intent.getIntExtra("id_car", -1);

        RecyclerView recyclerView = findViewById(R.id.rvList_item_act_service_maintenance);
        adapter = new AdapterItemActServiceMaintenance(this, App.getListItemActServiceMaintenanceCar(id_car, id_act_service_maintenance), this, this);
        recyclerView.setAdapter(adapter);
    }

    private void initializeInfActServiceMaintenance(){
        workInf = findViewById(R.id.tvWorkInfActServiceMaintenanceEdit);
        companyInf = findViewById(R.id.tvCompanyInfActServiceMaintenanceEdit);
        priceInf = findViewById(R.id.tvPriceInfActServiceMaintenanceEdit);
        descripInf = findViewById(R.id.tvDescriptionInfActServiceMaintenanceEdit);
        dateInf = findViewById(R.id.tvDateInfActServiceMaintenanceEdit);
        mileageNow = findViewById(R.id.tvMileageNowInfActServiceMaintenanceEdit);

        Intent intent = getIntent();
        int id_car =  intent.getIntExtra("id_car", -1);
        int id_act_service_maintenance =  intent.getIntExtra("id_act_service_maintenance", -1);
        ServiceMaintenanceCar serviceMaintenanceCar = App.get_serviceMeintenance_for_IDcar_And_IDserviceMaintenance(id_car, id_act_service_maintenance);
        workInf.setText(App.getTypeWorkName(serviceMaintenanceCar.getIDTypeOfWork()));

        companyInf.setText(serviceMaintenanceCar.getNameCompany());
        descripInf.setText(serviceMaintenanceCar.getTextDescription());
        dateInf.setText(serviceMaintenanceCar.getDate());

        if(serviceMaintenanceCar.getTotalPrice() == 0){
            priceInf.setText("Не указано");
        }
        else {
            priceInf.setText(String.valueOf(serviceMaintenanceCar.getTotalPrice()) + " руб.");
        }

        if(serviceMaintenanceCar.getMileageNow() == -1){
            mileageNow.setText("Не указано");
        }
        else {
            mileageNow.setText(String.valueOf(serviceMaintenanceCar.getMileageNow()));
        }
    }

    @Override
    public void onClickListenerRecyclerViewMore_Item_ActServiceMaintenance(int position) {
        String dialogStr = "";
        String dialogTill = "";
        Intent intent = getIntent();
        int id_car = intent.getIntExtra("id_car", -1);
        int id_act_service_maintenance = intent.getIntExtra("id_act_service_maintenance", -1);
        ItemServiceMaintenanceCar itemServiceMaintenanceCar = App.getListItemActServiceMaintenanceCar(id_car, id_act_service_maintenance).get(position);
        switch (itemServiceMaintenanceCar.getId_type_item()){
            case 1:
                dialogTill = " о детале";
                dialogStr = "Название: " + itemServiceMaintenanceCar.getName() + "\n" +
                        "Код детали: " + itemServiceMaintenanceCar.getCodeItem() + "\n" +
                        "Количество: " + itemServiceMaintenanceCar.getCount() + "\n" +
                        "Цена детали: " + itemServiceMaintenanceCar.getPriceItem() + " руб." + "\n" +
                        "Цена работы: " + itemServiceMaintenanceCar.getPriceWork() + " руб.";
                break;
            case 2:
                dialogTill = " о работе";
                dialogStr = "Название: " + itemServiceMaintenanceCar.getName() + "\n" +
                        "Количество: " + itemServiceMaintenanceCar.getCount() + "\n" +
                        "Цена работы: " + itemServiceMaintenanceCar.getPriceWork() + " руб.";
                break;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(List_item_service_maintenance_car_Activity.this);
        builder.setTitle("Подробнее" + dialogTill)
                .setMessage(dialogStr)
                .setCancelable(true)
                .setNegativeButton("Ок",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onLongClickListenerRecyclerViewMore_Item_ActServiceMaintenance(int position, View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.inflate(R.menu.context_recycler_menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = getIntent();
                int id_car = intent.getIntExtra("id_car", -1);
                int id_act_service_maintenance = intent.getIntExtra("id_act_service_maintenance", -1);
                int id_item = App.getListItemActServiceMaintenanceCar(id_car, id_act_service_maintenance).get(position).getId_item();
                switch (menuItem.getItemId()) {
                    case R.id.deleteContext:
                        String title = "Удаление!!!";
                        String message = "Вы действительно хотите удалить Запчасть(работу)?";
                        String button1String = "Да";
                        String button2String = "Отмена";

                        AlertDialog.Builder builder = new AlertDialog.Builder(List_item_service_maintenance_car_Activity.this);
                        builder.setTitle(title);  // заголовок
                        builder.setMessage(message); // сообщение
                        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                App.deleteItem(id_item);
                                initializeAdapterItemActServiceMaintenance();
                                initializeInfActServiceMaintenance();
                                Toast.makeText(List_item_service_maintenance_car_Activity.this, "Запчасть(работа) удалена!", Toast.LENGTH_LONG)
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
                        Intent intentEditItem = new Intent(view.getContext(), Edit_item_act_service_maintenance_car_activity.class);
                        intentEditItem.putExtra("id_car", id_car);
                        intentEditItem.putExtra("id_act_service_maintenance", id_act_service_maintenance);
                        intentEditItem.putExtra("id_item", id_item);
                        startActivity(intentEditItem);
                        break;
                }
                return false;
            }
        });
        popup.show();
    }
}