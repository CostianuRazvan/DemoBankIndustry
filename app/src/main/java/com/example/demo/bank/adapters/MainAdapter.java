package com.example.demo.bank.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.bank.R;
import com.example.demo.bank.adapters.viewHolder.MainViewHolder;
import com.example.demo.bank.model.models.Product;
import com.example.demo.bank.model.models.Transaction;
import com.example.demo.bank.utils.CustomAdapter;
import com.example.demo.bank.viewModel.MainItemViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainAdapter extends CustomAdapter {

    HashMap<String, ArrayList<Transaction>> data;

    public MainAdapter() {
        data = new HashMap<>();
    }

    @Override
    public void updateData(HashMap list) {
        if (list != null) {
            data.putAll(list);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        View itemView;
        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_transaction_item,
                viewGroup, false);
        viewHolder = new MainViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int count = 0;
        Product product = new Product();
        for (Map.Entry<String, ArrayList<Transaction>> entry : data.entrySet()) {
            if (count == position) {
                product.setName(entry.getKey());
                product.setTransactions(entry.getValue());
                break;
            }
            count++;
        }
        if (viewHolder instanceof MainViewHolder) {
            ((MainViewHolder) viewHolder).setViewModel(new MainItemViewModel(product));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if(holder instanceof MainViewHolder){
            ((MainViewHolder) holder).bind();
        }
    }



    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if(holder instanceof MainViewHolder){
            ((MainViewHolder) holder).unbind();
        }
    }
}
