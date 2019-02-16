package com.example.demo.bank.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.support.v7.widget.RecyclerView;

import com.example.demo.bank.model.models.Transaction;

/**
 * Custom RecyclerView Adapter, creates to be extends by all adapters that needs to use data binding, in order to update data properly
 **/

public abstract class  CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public abstract void updateData(HashMap<String, ArrayList<Transaction>> list);


}
