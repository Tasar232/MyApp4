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
import com.example.myapp4.activity.models_and_adapters.AdapterItemSTO;
import com.example.myapp4.activity.models_and_adapters.AdapterSTO;
import com.example.myapp4.logic.cars.Car;
import com.example.myapp4.logic.sto.ItemStoCar;
import com.example.myapp4.logic.sto.StoCar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class List_item_sto_car_Activity extends AppCompatActivity implements AdapterItemSTO.ItemOnClickListenerItemSTO, AdapterItemSTO.ItemOnLongClickListenerItemSTO{
    private TextView workInf, companyInf, priceInf, descripInf, dateInf, mileageNow;
    private AdapterItemSTO adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_sto_car);
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
        int id_sto =  intent.getIntExtra("id_sto", -1);
        int id_car =  intent.getIntExtra("id_car", -1);
        int id = item.getItemId();
        switch(id){
            case R.id.deleteContext:
                App.deleteSTO(id_sto);
                finish();
                break;
            case R.id.editContext:
                Intent intentEditSto = new Intent(getApplicationContext(), Edit_sto_car_activity.class);
                intentEditSto.putExtra("id_sto", id_sto);
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
        initializeInfSTO();
        initializeAdapterItemSTO();
        setOnClickFAB();
    }


    private void setOnClickFAB(){
        FloatingActionButton fab = findViewById(R.id.fabAddItemSTO);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                int id_sto =  intent.getIntExtra("id_sto", -1);
                int id_car =  intent.getIntExtra("id_car", -1);

                Intent intentAdd = new Intent(view.getContext(), Add_item_sto_car_activity.class);
                intentAdd.putExtra("id_car", id_car);
                intentAdd.putExtra("id_sto", id_sto);
                startActivity(intentAdd);
            }
        });
    }

    private void initializeAdapterItemSTO(){
        Intent intent = getIntent();
        int id_sto =  intent.getIntExtra("id_sto", -1);
        int id_car =  intent.getIntExtra("id_car", -1);

        RecyclerView recyclerView = findViewById(R.id.rvList_item);
        adapter = new AdapterItemSTO(this, App.getListItemStoCar(id_car, id_sto), this, this);
        recyclerView.setAdapter(adapter);
    }

    private void initializeInfSTO(){
        workInf = findViewById(R.id.tvWorkInfStoEdit);
        companyInf = findViewById(R.id.tvCompanyInfStoEdit);
        priceInf = findViewById(R.id.tvPriceInfStoEdit);
        descripInf = findViewById(R.id.tvDescriptionInfStoEdit);
        dateInf = findViewById(R.id.tvDateInfStoEdit);
        mileageNow = findViewById(R.id.tvMileageNowInfStoEdit);

        Intent intent = getIntent();
        int id_car =  intent.getIntExtra("id_car", -1);
        int id_sto =  intent.getIntExtra("id_sto", -1);
        StoCar stoCar = App.getStoForIDcarAndIDsto(id_car, id_sto);
        workInf.setText(App.getTypeWorkName(stoCar.getTypeOfWork()));

        companyInf.setText(stoCar.getNameCompany());
        descripInf.setText(stoCar.getText());
        dateInf.setText(stoCar.getDate());

        if(stoCar.getTotalPrice() == 0){
            priceInf.setText("Не указано");
        }
        else {
            priceInf.setText(String.valueOf(stoCar.getTotalPrice()));
        }

        if(stoCar.getMileageNow() == -1){
            mileageNow.setText("Не указано");
        }
        else {
            mileageNow.setText(String.valueOf(stoCar.getMileageNow()));
        }
    }

    @Override
    public void onClickListenerRecyclerViewMore_Item_STO(int position) {
        String dialogStr = "";
        String dialogTill = "";
        Intent intent = getIntent();
        int id_car = intent.getIntExtra("id_car", -1);
        int id_sto = intent.getIntExtra("id_sto", -1);
        ItemStoCar itemStoCar = App.getListItemStoCar(id_car, id_sto).get(position);
        switch (itemStoCar.getId_type_item()){
            case 1:
                dialogTill = " о детале";
                dialogStr = "Название: " + itemStoCar.getName() + "\n" +
                        "Код детали: " + itemStoCar.getCodeItem() + "\n" +
                        "Количество: " + itemStoCar.getCount() + "\n" +
                        "Цена детали: " + itemStoCar.getPriceItem() + "\n" +
                        "Цена работы: " + itemStoCar.getPriceWork();
                break;
            case 2:
                dialogTill = " о работе";
                dialogStr = "Название: " + itemStoCar.getName() + "\n" +
                        "Количество: " + itemStoCar.getCount() + "\n" +
                        "Цена работы: " + itemStoCar.getPriceWork();
                break;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(List_item_sto_car_Activity.this);
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
    public void onLongClickListenerRecyclerViewMore_Item_STO(int position, View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.inflate(R.menu.context_recycler_menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = getIntent();
                int id_car = intent.getIntExtra("id_car", -1);
                int id_sto = intent.getIntExtra("id_sto", -1);
                int id_item = App.getListItemStoCar(id_car, id_sto).get(position).getId_item();
                switch (menuItem.getItemId()) {
                    case R.id.deleteContext:
                        String title = "Удаление!!!";
                        String message = "Вы действительно хотите удалить Запчасть(работу)?";
                        String button1String = "Да";
                        String button2String = "Отмена";

                        AlertDialog.Builder builder = new AlertDialog.Builder(List_item_sto_car_Activity.this);
                        builder.setTitle(title);  // заголовок
                        builder.setMessage(message); // сообщение
                        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                App.deleteItem(id_item);
                                initializeAdapterItemSTO();
                                initializeInfSTO();
                                Toast.makeText(List_item_sto_car_Activity.this, "Запчасть(работа) удалена!", Toast.LENGTH_LONG)
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
                        Intent intentEditItem = new Intent(view.getContext(), Edit_item_sto_car_activity.class);
                        intentEditItem.putExtra("id_car", id_car);
                        intentEditItem.putExtra("id_sto", id_sto);
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