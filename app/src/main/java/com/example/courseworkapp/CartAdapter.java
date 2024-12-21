package com.example.courseworkapp;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<Book> orderList;

    public CartAdapter(ArrayList<Book> orderList) {
        this.orderList = orderList;
    }

    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
        CartAdapter.ViewHolder viewHolder = new CartAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.image.setImageResource(R.drawable.book);
        holder.title.setText(orderList.get(position).getName());
        holder.cost.setText(Integer.toString(orderList.get(position).getCost()) + "₽");
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cost;
        TextView title;
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = itemView.findViewById(R.id.cartImage);
            title = itemView.findViewById(R.id.cartTitle);
            cost = itemView.findViewById(R.id.cartCost);
        }
    }
}
