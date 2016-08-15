package com.example.mcmor.simpleui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mcmor on 2016-08-09.
 */
public class OrderAdapter extends BaseAdapter {

    List<Order> orders;
    LayoutInflater layoutInflater;

    public OrderAdapter(Context context, List<Order> orderList){
        this.orders = orderList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;

        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.listview_order_item, null);

            TextView noteTextView = (TextView)convertView.findViewById(R.id.noteTextView);
            TextView storeInfoTextView = (TextView)convertView.findViewById(R.id.storeInfoTextView);
            TextView drinkTextView = (TextView)convertView.findViewById(R.id.drinkTextView);

            holder = new Holder();

            holder.noteTextView = noteTextView;
            holder.storeInfoTextView = storeInfoTextView;
            holder.drinkTextView = drinkTextView;

            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder)convertView.getTag();
        }

        Order order = orders.get(position);
        holder.noteTextView.setText(order.note);
        holder.storeInfoTextView.setText(order.storeInfo);
        holder.drinkTextView.setText(String.valueOf(order.getTotal()));

        return convertView;
    }

    class Holder {
        TextView noteTextView, storeInfoTextView, drinkTextView;
    }
}
