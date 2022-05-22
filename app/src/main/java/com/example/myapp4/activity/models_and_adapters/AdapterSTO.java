package com.example.myapp4.activity.models_and_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp4.App;
import com.example.myapp4.R;
import com.example.myapp4.logic.sto.StoCar;

import java.util.ArrayList;

public class AdapterSTO extends RecyclerView.Adapter<AdapterSTO.ViewHolder>{
    private LayoutInflater inflater;
    private ArrayList<StoCar> stos;

    private AdapterSTO.ItemOnClickListenerSTO listener;
    private AdapterSTO.ItemOnLongClickListenerSTO longListener;

    public AdapterSTO(Context context, ArrayList<StoCar> stos, AdapterSTO.ItemOnClickListenerSTO listener, AdapterSTO.ItemOnLongClickListenerSTO longListener){
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
        StoCar carSTO = stos.get(position);
        String price = String.valueOf(carSTO.getTotalPrice());
        String mileage = String.valueOf(carSTO.getMileageNow());

        holder.price.setText(price);
        holder.date.setText(carSTO.getDate());
        holder.mileage.setText(mileage);
        holder.work.setText(App.getTypeWorkString(carSTO.getTypeOfWork()));
        switch (carSTO.getTypeOfWork()){
            case 1:
                holder.imWork.setImageResource(R.drawable.ic_pas);
                break;
            case 2:
                holder.imWork.setImageResource(R.drawable.ic_rem);
                break;
            case 3:
                holder.imWork.setImageResource(R.drawable.ic_srem);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return stos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView work, price, date, mileage;
        ImageView imWork;

        AdapterSTO.ItemOnClickListenerSTO itemOnClickListener;
        AdapterSTO.ItemOnLongClickListenerSTO itemOnLongClickListener;

        public ViewHolder(View itemView, AdapterSTO.ItemOnClickListenerSTO itemOnClickListener, AdapterSTO.ItemOnLongClickListenerSTO itemOnLongClickListener) {
            super(itemView);
            work = itemView.findViewById(R.id.tvWorkRVSto);
            price = itemView.findViewById(R.id.tvPriceRVStoEdit);
            date = itemView.findViewById(R.id.tvDateTVSto);
            mileage = itemView.findViewById(R.id.tvmileadeNowRVStoEdit);
            imWork = itemView.findViewById(R.id.imWorkRVItemSto);

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
