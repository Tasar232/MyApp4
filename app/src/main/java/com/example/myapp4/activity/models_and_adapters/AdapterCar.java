package com.example.myapp4.activity.models_and_adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp4.R;
import com.example.myapp4.logic.cars.Car;

import java.util.List;

public class AdapterCar extends RecyclerView.Adapter<AdapterCar.ViewHolder>{
    private LayoutInflater inflater;
    private List<Car> cars;
    private Uri uri;
    private int Pick_image = 1;
    private ItemOnClickListenerCar listener;
    private ItemOnLongClickListenerCar longListener;

    public AdapterCar(Context context, List<Car> cars, ItemOnClickListenerCar listener, ItemOnLongClickListenerCar longListener){
        this.cars = cars;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.longListener = longListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_cars, parent, false);
        return new ViewHolder(view, listener, longListener);
    }

    @Override
    public void onBindViewHolder(AdapterCar.ViewHolder holder, int position) {

        Car car = cars.get(position);
        holder.markView.setText(car.getMark());
        holder.modelView.setText(car.getModel());
//        holder.buttonView.setText("Подробнее");
        holder.imCar.setImageResource(R.drawable.car);

    }

    @Override
    public int getItemCount() {
        return cars.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView markView, modelView;
        //public Button buttonView;
        public ImageView imCar;
        //private final int Pick_image = 1;
        ItemOnClickListenerCar itemOnClickListener;
        ItemOnLongClickListenerCar itemOnLongClickListenerCar;

        protected ViewHolder(View view, ItemOnClickListenerCar itemOnClickListener, ItemOnLongClickListenerCar itemOnLongClickListenerCar){
            super(view);
            markView = view.findViewById(R.id.markLoc);
            modelView = view.findViewById(R.id.modelLoc);
            //buttonView = view.findViewById(R.id.button);
            imCar = view.findViewById(R.id.iVCar);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            this.itemOnClickListener = itemOnClickListener;
            this.itemOnLongClickListenerCar = itemOnLongClickListenerCar;




        }

        @Override
        public void onClick(View view) {
            itemOnClickListener.onClickListenerRecyclerViewMore(getAdapterPosition());
        }


        @Override
        public boolean onLongClick(View view) {
            itemOnLongClickListenerCar.onLongClickListenerRecyclerViewMore(getAdapterPosition(), view);
            return false;
        }
    }

    public interface ItemOnClickListenerCar{
        void onClickListenerRecyclerViewMore(int position);
    }

    public interface ItemOnLongClickListenerCar{
        void onLongClickListenerRecyclerViewMore(int position, View view);
    }



}
