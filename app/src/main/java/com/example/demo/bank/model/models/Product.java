package com.example.demo.bank.model.models;

import java.util.ArrayList;

public class Product {

    private String name;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
}
