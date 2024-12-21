package com.example.courseworkapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    ArrayList<Book> books;
    int user_id;
    public Adapter(Context ctx, int user_id, ArrayList<Book> books) {
        this.context = ctx;
        this.books = books;
        this.user_id = user_id;
    }

    public Context getContext() { return context; }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        Adapter.ViewHolder viewHolder = new Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.image.setImageResource(R.drawable.book);
        int id = books.get(position).getId();
        holder.text.setText(books.get(position).getName());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bookPage = new Intent(getContext(), BookActivity.class);
                bookPage.putExtra("bookId", id);
                bookPage.putExtra("user_id", user_id);
                getContext().startActivity(bookPage);
            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setList(ArrayList<Book> filtered) {
        books = filtered;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        Integer id;

        public ViewHolder(View view) {
            super(view);
            image = itemView.findViewById(R.id.listImage);
            text = itemView.findViewById(R.id.listTitle);
            id = null;
        }
    }
}
