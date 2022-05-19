package com.example.myapp4.activity.models_and_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp4.R;
import com.example.myapp4.logic.cars.Car;
import com.example.myapp4.logic.sto.CarSTO;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class AdapterSTO extends RecyclerView.Adapter<AdapterSTO.ViewHolder>{
    private LayoutInflater inflater;
    private ArrayList<CarSTO> stos;

    private AdapterSTO.ItemOnClickListenerSTO listener;
    private AdapterSTO.ItemOnLongClickListenerSTO longListener;

    public AdapterSTO(Context context, ArrayList<CarSTO> stos, AdapterSTO.ItemOnClickListenerSTO listener, AdapterSTO.ItemOnLongClickListenerSTO longListener){
        this.stos = stos;
        this.inflater = LayoutInflater.from(context);

        this.listener = listener;
        this.longListener = longListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_sto, parent, false);
        return new AdapterSTO.ViewHolder(view, listener, longListener);
    }

    @Override
    public void onBindViewHolder(AdapterSTO.ViewHolder holder, int position) {
        CarSTO carSTO = stos.get(position);
        String price = String.valueOf(carSTO.getTotalPrice());
        holder.price.setText(price);
        holder.date.setText(carSTO.getDate());
        switch (carSTO.getTypeOfWork()){
            case 1:
                holder.imWork.setImageResource(R.drawable.ic_pas);
                holder.work.setText("Расходники");
                break;
            case 2:
                holder.imWork.setImageResource(R.drawable.ic_rem);
                holder.work.setText("Ремонт");
                break;
            case 3:
                holder.imWork.setImageResource(R.drawable.ic_srem);
                holder.work.setText("Ремонт своими рукми");
                break;
        }

    }

    @Override
    public int getItemCount() {
        return stos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView work, price, date;
        ImageView imWork;

        AdapterSTO.ItemOnClickListenerSTO itemOnClickListener;
        AdapterSTO.ItemOnLongClickListenerSTO itemOnLongClickListener;

        public ViewHolder(View itemView, AdapterSTO.ItemOnClickListenerSTO itemOnClickListener, AdapterSTO.ItemOnLongClickListenerSTO itemOnLongClickListener) {
            super(itemView);
            work = itemView.findViewById(R.id.tvWork);
            price = itemView.findViewById(R.id.tvPrice);
            date = itemView.findViewById(R.id.tvDate);
            imWork = itemView.findViewById(R.id.imWork);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            this.itemOnClickListener = itemOnClickListener;
            this.itemOnLongClickListener = itemOnLongClickListener;
        }

        @Override
        public void onClick(View view) {
            itemOnClickListener.onClickListenerRecyclerViewMore_STO(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            itemOnLongClickListener.onLongClickListenerRecyclerViewMore_STO(getAdapterPosition(), view);
            return false;
        }
    }

    public interface ItemOnClickListenerSTO{
        void onClickListenerRecyclerViewMore_STO(int position);
    }

    public interface ItemOnLongClickListenerSTO{
        void onLongClickListenerRecyclerViewMore_STO(int position, View view);
    }
}
