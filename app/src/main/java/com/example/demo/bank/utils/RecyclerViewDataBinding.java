package com.example.demo.bank.utils;

import android.databinding.BindingAdapter;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.demo.bank.model.models.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Custom binding class that have binding adapter method. That method allows to use feature described inside her through the xml file.
 */

public class RecyclerViewDataBinding {

    @BindingAdapter(value = {"adapter", "data"}, requireAll = false)
    public static void bind(RecyclerView recyclerView, CustomAdapter adapter, HashMap data) {
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean requestChildRectangleOnScreen(RecyclerView parent, View child, Rect rect, boolean immediate, boolean focusedChildVisible) {  // disable autoscrolling when view is on focus
                    return false;
                }
            });
        }
        if (adapter != null) {
            adapter.updateData(data);
        }


    }
}
