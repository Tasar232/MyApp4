package com.example.myapp4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.myapp4.App;
import com.example.myapp4.R;
import com.example.myapp4.activity.models_and_adapters.AdapterItemSTO;
import com.example.myapp4.activity.models_and_adapters.AdapterSTO;
import com.example.myapp4.logic.cars.Car;
import com.example.myapp4.logic.sto.StoCar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class List_item_sto_car_Activity extends AppCompatActivity implements AdapterItemSTO.ItemOnClickListenerItemSTO, AdapterItemSTO.ItemOnLongClickListenerItemSTO{
    TextView workInf, companyInf, priceInf, descripInf, dateInf, mileageNow;
    private AdapterItemSTO adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_sto_car);
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
                int id_sto =  intent.getIntExtra("id_sto", 0);
                int id_car =  intent.getIntExtra("id_car", 0);

                Intent intentAdd = new Intent(view.getContext(), Add_item_sto_car_activity.class);
                intentAdd.putExtra("id_car", id_car);
                intentAdd.putExtra("id_sto", id_sto);
                startActivity(intentAdd);
            }
        });
    }

    private void initializeAdapterItemSTO(){
        Intent intent = getIntent();
        int id_sto =  intent.getIntExtra("id_sto", 0);
        int id_car =  intent.getIntExtra("id_car", 0);

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
        int id_car =  intent.getIntExtra("id_car", 0);
        int id_sto =  intent.getIntExtra("id_sto", 0);
        StoCar stoCar = App.getStoForIDcarAndIDsto(id_car, id_sto);
        workInf.setText(App.getTypeWorkString(stoCar.getTypeOfWork()));

        companyInf.setText(stoCar.getNameCompany());
        descripInf.setText(stoCar.getText());
        dateInf.setText(stoCar.getDate());

        if(stoCar.getTotalPrice() == 0){
            priceInf.setText("Не указано");
        }
        else {
            priceInf.setText(String.valueOf(stoCar.getTotalPrice()));
        }

        if(stoCar.getMileageNow() == 0){
            mileageNow.setText("Не указано");
        }
        else {
            mileageNow.setText(String.valueOf(stoCar.getMileageNow()));
        }
    }

    @Override
    public void onClickListenerRecyclerViewMore_Item_STO(int position) {
        //TODO
        //Intent intent = new Intent(this, List_sto_car_Activity.class);
        //int id = App.getListCars().get(position).getId();
        //intent.putExtra("id", id);
        //startActivity(intent);
    }

    @Override
    public void onLongClickListenerRecyclerViewMore_Item_STO(int position, View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.inflate(R.menu.context_recycler_menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.deleteContext:
                        Intent intent = getIntent();
                        int id_car = intent.getIntExtra("id_item", 0);
                        int id_sto = intent.getIntExtra("id_sto", 0);
                        int id_item = App.getListItemStoCar(id_car, id_sto).get(position).getId_item();
                        App.deleteItem(id_item);
                        initializeAdapterItemSTO();
                        break;
                }
                return false;
            }
        });
        popup.show();
    }
}