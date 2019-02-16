package com.example.demo.bank.viewModel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.example.demo.bank.helpers.FindRates;
import com.example.demo.bank.model.models.Product;
import com.example.demo.bank.model.models.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class MainItemViewModel extends ViewModel {


    ObservableField<Product> product = new ObservableField<>();
    ObservableField<Double> value = new ObservableField<>();


    public MainItemViewModel(Product product) {
        this.product.set(product);
    }


    public ObservableField<Product> getProduct() {
        return product;
    }

    public void setProduct(ObservableField<Product> product) {
        this.product = product;
    }

    public ObservableField<Double> getValue() {
        return value;
    }

    public void setValue(ObservableField<Double> value) {
        this.value = value;
    }

    public void calculate(){
        double transactionValue = 0;
        for (Transaction transaction : product.get().getTransactions()) {
            ArrayList<String> currencies = new ArrayList();
            currencies.add(transaction.getCurrency());
            transactionValue += transaction.getAmount() * FindRates.getInstance().findRate(transaction.getCurrency(), currencies, 1);
        }
        BigDecimal roundedValue = new BigDecimal(transactionValue);
        roundedValue = roundedValue.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        value.set(roundedValue.doubleValue());
    }
}
