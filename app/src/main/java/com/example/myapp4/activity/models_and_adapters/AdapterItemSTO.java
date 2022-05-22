package com.example.myapp4.activity.models_and_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp4.R;
import com.example.myapp4.logic.sto.ItemStoCar;
import com.example.myapp4.logic.sto.StoCar;

import java.util.ArrayList;

public class AdapterItemSTO extends RecyclerView.Adapter<AdapterItemSTO.ViewHolder>{
    private LayoutInflater inflater;
    private ArrayList<ItemStoCar> items;

    private AdapterItemSTO.ItemOnClickListenerItemSTO listener;
    private AdapterItemSTO.ItemOnLongClickListenerItemSTO longListener;

    public AdapterItemSTO(Context context, ArrayList<ItemStoCar> items, AdapterItemSTO.ItemOnClickListenerItemSTO listener, AdapterItemSTO.ItemOnLongClickListenerItemSTO longListener){
        this.items = items;
        this.inflater = LayoutInflater.from(context);

        this.listener = listener;
        this.longListener = longListener;
    }

    @Override
    public AdapterItemSTO.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_item_sto, parent, false);
        return new AdapterItemSTO.ViewHolder(view, listener, longListener);
    }

    @Override
    public void onBindViewHolder(AdapterItemSTO.ViewHolder holder, int position) {
        ItemStoCar itemCarSTO = items.get(position);
        holder.tvName.setText(itemCarSTO.getName());
        holder.tvCount.setText(String.valueOf(itemCarSTO.getCount()));
        holder.tvPrice.setText(String.valueOf(itemCarSTO.getPrice()));
        holder.imShestr.setImageResource(R.drawable.ic_shestr);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tvName, tvCount, tvPrice;
        ImageView imShestr;

        AdapterItemSTO.ItemOnClickListenerItemSTO itemOnClickListener;
        AdapterItemSTO.ItemOnLongClickListenerItemSTO itemOnLongClickListener;

        public ViewHolder(View itemView, AdapterItemSTO.ItemOnClickListenerItemSTO itemOnClickListener, AdapterItemSTO.ItemOnLongClickListenerItemSTO itemOnLongClickListener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameItemStoRVEdit);
            tvCount = itemView.findViewById(R.id.tvCountItemStoRVEdit);
            tvPrice = itemView.findViewById(R.id.tvPriceItemStoRVEdit);
            imShestr = itemView.findViewById(R.id.imShesterItemStoRV);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            this.itemOnClickListener = itemOnClickListener;
            this.itemOnLongClickListener = itemOnLongClickListener;
        }

        @Override
        public void onClick(View view) {
            itemOnClickListener.onClickListenerRecyclerViewMore_Item_STO(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            itemOnLongClickListener.onLongClickListenerRecyclerViewMore_Item_STO(getAdapterPosition(), view);
            return false;
        }
    }
    public interface ItemOnClickListenerItemSTO{
        void onClickListenerRecyclerViewMore_Item_STO(int position);
    }

    public interface ItemOnLongClickListenerItemSTO{
        void onLongClickListenerRecyclerViewMore_Item_STO(int position, View view);
    }

}
