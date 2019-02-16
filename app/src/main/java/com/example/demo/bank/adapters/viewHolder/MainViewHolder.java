package com.example.demo.bank.adapters.viewHolder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.demo.bank.databinding.MainTransactionItemBinding;
import com.example.demo.bank.viewModel.MainItemViewModel;

public class MainViewHolder extends RecyclerView.ViewHolder {

    MainTransactionItemBinding binding;

    public MainViewHolder(View itemView) {
        super(itemView);
        bind();
    }

    public void bind() {
        if (binding == null) {
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public void unbind() {
        if (binding != null) {
            binding.unbind();
        }
    }

    public void setViewModel(MainItemViewModel viewModel) {
        if (binding != null) {
            binding.setViewModel(viewModel);
        }
    }
}
