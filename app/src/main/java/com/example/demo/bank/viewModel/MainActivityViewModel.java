package com.example.demo.bank.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.example.demo.bank.adapters.MainAdapter;
import com.example.demo.bank.model.Repository;
import com.example.demo.bank.model.models.Rate;
import com.example.demo.bank.model.models.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivityViewModel extends ViewModel {
    private ObservableField<Boolean> isLoading = new ObservableField<>(false);
    private ObservableField<MainAdapter> adapter;
    private MediatorLiveData<HashMap<String, ArrayList<Transaction>>> data = new MediatorLiveData<>();
    private Repository repository = Repository.getInstance();


    public MainActivityViewModel() {
        MainAdapter mainAdapter = new MainAdapter();
        adapter = new ObservableField<>(mainAdapter);
        isLoading.set(true);
        repository.getRates();
        data.addSource(repository.getTransactions(), serverResponse -> {
            data.setValue(serverResponse);
            isLoading.set(false);
        });
    }


    public ObservableField<Boolean> getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(ObservableField<Boolean> isLoading) {
        this.isLoading = isLoading;
    }

    public ObservableField<MainAdapter> getAdapter() {
        return adapter;
    }

    public void setAdapter(ObservableField<MainAdapter> adapter) {
        this.adapter = adapter;
    }

    public LiveData<HashMap<String, ArrayList<Transaction>>> getData() {
        return data;
    }

    public void setData(MediatorLiveData<HashMap<String, ArrayList<Transaction>>> data) {
        this.data = data;
    }
}
