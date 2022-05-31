package com.example.myapp4.activity.models_and_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp4.R;
import com.example.myapp4.logic.sto.ItemServiceMaintenanceCar;

import java.util.ArrayList;

public class AdapterItemActServiceMaintenance extends RecyclerView.Adapter<AdapterItemActServiceMaintenance.ViewHolder>{
    private LayoutInflater inflater;
    private ArrayList<ItemServiceMaintenanceCar> items;

    private ItemOnClickListenerItemActServiceMaintenance listener;
    private ItemOnLongClickListenerItemActServiceMaintenance longListener;

    public AdapterItemActServiceMaintenance(Context context, ArrayList<ItemServiceMaintenanceCar> items, ItemOnClickListenerItemActServiceMaintenance listener, ItemOnLongClickListenerItemActServiceMaintenance longListener){
        this.items = items;
        this.inflater = LayoutInflater.from(context);

        this.listener = listener;
        this.longListener = longListener;
    }

    @Override
    public AdapterItemActServiceMaintenance.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_rv_item_act_service_maintenance, parent, false);
        return new AdapterItemActServiceMaintenance.ViewHolder(view, listener, longListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemServiceMaintenanceCar itemServiceMaintenanceCar = items.get(position);
        holder.tvName.setText(itemServiceMaintenanceCar.getName());
        holder.tvCount.setText(String.valueOf(itemServiceMaintenanceCar.getCount()));
        int price = itemServiceMaintenanceCar.getPriceItem() + itemServiceMaintenanceCar.getPriceWork();
        holder.tvPrice.setText(String.valueOf(price)+ " руб.");
        switch (itemServiceMaintenanceCar.getId_type_item()){
            case 1:
                holder.imShestr.setImageResource(R.drawable.ic_type_spare);
                break;
            case 2:
                holder.imShestr.setImageResource(R.drawable.ic_type_work);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tvName, tvCount, tvPrice;
        ImageView imShestr;

        ItemOnClickListenerItemActServiceMaintenance itemOnClickListener;
        ItemOnLongClickListenerItemActServiceMaintenance itemOnLongClickListener;

        public ViewHolder(View itemView, ItemOnClickListenerItemActServiceMaintenance itemOnClickListener, ItemOnLongClickListenerItemActServiceMaintenance itemOnLongClickListener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameItemActServiceMaintenanceRVEdit);
            tvCount = itemView.findViewById(R.id.tvCountItemActServiceMaintenanceRVEdit);
            tvPrice = itemView.findViewById(R.id.tvPriceItemActServiceMaintenanceRVEdit);
            imShestr = itemView.findViewById(R.id.imShesterItemActServiceMaintenanceRV);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            this.itemOnClickListener = itemOnClickListener;
            this.itemOnLongClickListener = itemOnLongClickListener;
        }

        @Override
        public void onClick(View view) {
            itemOnClickListener.onClickListenerRecyclerViewMore_Item_ActServiceMaintenance(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            itemOnLongClickListener.onLongClickListenerRecyclerViewMore_Item_ActServiceMaintenance(getAdapterPosition(), view);
            return false;
        }
    }
    public interface ItemOnClickListenerItemActServiceMaintenance {
        void onClickListenerRecyclerViewMore_Item_ActServiceMaintenance(int position);
    }

    public interface ItemOnLongClickListenerItemActServiceMaintenance {
        void onLongClickListenerRecyclerViewMore_Item_ActServiceMaintenance(int position, View view);
    }

}
