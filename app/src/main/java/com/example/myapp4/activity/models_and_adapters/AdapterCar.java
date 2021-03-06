package com.example.myapp4.activity.models_and_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp4.R;
import com.example.myapp4.logic.cars.Car;

import java.util.ArrayList;

public class AdapterCar extends RecyclerView.Adapter<AdapterCar.ViewHolder>{
    private LayoutInflater inflater;
    private ArrayList<Car> cars;

    private ItemOnClickListenerCar listener;
    private ItemOnLongClickListenerCar longListener;

    public AdapterCar(Context context, ArrayList<Car> cars, ItemOnClickListenerCar listener, ItemOnLongClickListenerCar longListener){
        this.cars = cars;
        this.inflater = LayoutInflater.from(context);

        this.listener = listener;
        this.longListener = longListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_rv_car, parent, false);
        return new ViewHolder(view, listener, longListener);
    }

    @Override
    public void onBindViewHolder(AdapterCar.ViewHolder holder, int position) {

        Car car = cars.get(position);
        holder.markView.setText(car.getMark());
        holder.modelView.setText(car.getModel());
        holder.gosView.setText(car.getGosNumber());
        holder.stsView.setText(car.getRegCertificate());
        holder.imCar.setImageResource(R.drawable.ic_car);

    }

    @Override
    public int getItemCount() {
        return cars.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView markView, modelView, gosView, stsView;
        public ImageView imCar;

        ItemOnClickListenerCar itemOnClickListener;
        ItemOnLongClickListenerCar itemOnLongClickListenerCar;

        protected ViewHolder(View view, ItemOnClickListenerCar itemOnClickListener, ItemOnLongClickListenerCar itemOnLongClickListenerCar){
            super(view);
            markView = view.findViewById(R.id.tvMarkCarRV);
            modelView = view.findViewById(R.id.tvModelCarRV);
            gosView = view.findViewById(R.id.tvGosCarRVEdit);
            stsView = view.findViewById(R.id.tvSTSCarRVEdit);
            imCar = view.findViewById(R.id.ivCarRV);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            this.itemOnClickListener = itemOnClickListener;
            this.itemOnLongClickListenerCar = itemOnLongClickListenerCar;




        }

        @Override
        public void onClick(View view) {
            itemOnClickListener.onClickListenerRecyclerViewMore_Car(getAdapterPosition());
        }


        @Override
        public boolean onLongClick(View view) {
            itemOnLongClickListenerCar.onLongClickListenerRecyclerViewMore_Car(getAdapterPosition(), view);
            return false;
        }
    }

    public interface ItemOnClickListenerCar{
        void onClickListenerRecyclerViewMore_Car(int position);
    }

    public interface ItemOnLongClickListenerCar{
        void onLongClickListenerRecyclerViewMore_Car(int position, View view);
    }



}
