package com.example.courseworkapp;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Integer> orderList;

    public Cart() {
        this.orderList = new ArrayList<>();
    }

    public ArrayList<Integer> getOrderList() { return this.orderList; }

    public int add(int book_id) {
        if (!this.orderList.contains(book_id)) {
            this.orderList.add(book_id);
            return book_id;
        }
        return -1;
    }

    public void clear() {
        this.orderList = new ArrayList<>();
    }

    public int remove(int book_id) {
        if (this.orderList.contains(book_id)) {
            this.orderList.remove(
                    this.orderList.indexOf(book_id)
            );
            return book_id;
        }
        return -1;
    }

}
