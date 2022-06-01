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
import com.example.myapp4.logic.sto.ServiceMaintenanceCar;

import java.util.ArrayList;

public class AdapterActServiceMaintenance extends RecyclerView.Adapter<AdapterActServiceMaintenance.ViewHolder>{
    private LayoutInflater inflater;
    private ArrayList<ServiceMaintenanceCar> list_act_service_maintenance;

    private ItemOnClickListenerActServiceMaintenance listener;
    private ItemOnLongClickListenerActServiceMaintenance longListener;

    public AdapterActServiceMaintenance(Context context, ArrayList<ServiceMaintenanceCar> list_act_service_maintenance, ItemOnClickListenerActServiceMaintenance listener, ItemOnLongClickListenerActServiceMaintenance longListener){
        this.list_act_service_maintenance = list_act_service_maintenance;
        this.inflater = LayoutInflater.from(context);

        this.listener = listener;
        this.longListener = longListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_rv_service_maintenance, parent, false);
        return new AdapterActServiceMaintenance.ViewHolder(view, listener, longListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ServiceMaintenanceCar serviceMaintenanceCar = list_act_service_maintenance.get(list_act_service_maintenance.size() - 1 - position);
        String price = String.valueOf(serviceMaintenanceCar.getTotalPrice());
        String mileage = String.valueOf(serviceMaintenanceCar.getMileageNow());

        holder.price.setText(price + " руб.");
        holder.date.setText(serviceMaintenanceCar.getDate());
        if (serviceMaintenanceCar.getMileageNow() == -1){
            holder.mileage.setText("Не указано");
        }
        else {
            holder.mileage.setText(mileage);
        }
        holder.work.setText(App.getTypeWorkName(serviceMaintenanceCar.getIDTypeOfWork()));
        switch (serviceMaintenanceCar.getIDTypeOfWork()){
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
        return list_act_service_maintenance.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView work, price, date, mileage;
        ImageView imWork;

        ItemOnClickListenerActServiceMaintenance itemOnClickListener;
        ItemOnLongClickListenerActServiceMaintenance itemOnLongClickListener;

        public ViewHolder(View itemView, ItemOnClickListenerActServiceMaintenance itemOnClickListener, ItemOnLongClickListenerActServiceMaintenance itemOnLongClickListener) {
            super(itemView);
            work = itemView.findViewById(R.id.tvWorkRVActServiceMaintenance);
            price = itemView.findViewById(R.id.tvPriceRVActServiceMaintenanceEdit);
            date = itemView.findViewById(R.id.tvDateTVActServiceMaintenance);
            mileage = itemView.findViewById(R.id.tvmileadeNowRVActServiceMaintenanceEdit);
            imWork = itemView.findViewById(R.id.imWorkRVItemActServiceMaintenance);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            this.itemOnClickListener = itemOnClickListener;
            this.itemOnLongClickListener = itemOnLongClickListener;
        }

        @Override
        public void onClick(View view) {
            itemOnClickListener.onClickListenerRecyclerViewMore_ActServiceMaintenance(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            itemOnLongClickListener.onLongClickListenerRecyclerViewMore_ActServiceMaintenance(getAdapterPosition(), view);
            return false;
        }
    }

    public interface ItemOnClickListenerActServiceMaintenance {
        void onClickListenerRecyclerViewMore_ActServiceMaintenance(int position);
    }

    public interface ItemOnLongClickListenerActServiceMaintenance {
        void onLongClickListenerRecyclerViewMore_ActServiceMaintenance(int position, View view);
    }
}
