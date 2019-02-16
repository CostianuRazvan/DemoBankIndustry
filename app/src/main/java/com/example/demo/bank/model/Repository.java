package com.example.demo.bank.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.demo.bank.helpers.FindRates;
import com.example.demo.bank.model.models.Rate;
import com.example.demo.bank.model.models.Transaction;
import com.example.demo.bank.network.APIClient;
import com.example.demo.bank.network.APIInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static Repository INSTANCE;

    private APIInterface apiInterface;

    private Repository() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public static Repository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Repository();

        return INSTANCE;
    }

    public LiveData<HashMap<String, ArrayList<Transaction>>> getTransactions() {

        MutableLiveData<HashMap<String, ArrayList<Transaction>>> fetchedData = new MutableLiveData<>();

        Call serverCall = apiInterface.getTransactions("application/json");

        serverCall.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.println(response.toString());
                HashMap<String, ArrayList<Transaction>> transactions = new HashMap<>();
                if (response.isSuccessful()) {
                    try {
                        String resp = String.valueOf(response.body());
                        JSONArray jsonArray = new JSONArray(resp);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String product = jsonObject.getString("sku");
                            Transaction transaction = new Transaction();
                            transaction.setAmount(jsonObject.getDouble("amount"));
                            transaction.setCurrency(jsonObject.getString("currency"));
                            if (transactions.containsKey(product)) {
                                transactions.get(product).add(transaction);
                            } else {
                                ArrayList<Transaction> newList = new ArrayList<>();
                                newList.add(transaction);
                                transactions.put(product, newList);
                            }
                        }
                        fetchedData.setValue(transactions);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println(t.toString());
            }
        });

        return fetchedData;
    }

    public void getRates() {

        Call serverCall = apiInterface.getRates("application/json");

        serverCall.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ArrayList<Rate> rates = new ArrayList<>();
                if (response.isSuccessful()) {
                    try {
                        String resp = String.valueOf(response.body());
                        JSONArray jsonArray = new JSONArray(resp);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Rate rate = new Rate();
                            rate.setFrom(jsonObject.getString("from"));
                            rate.setTo(jsonObject.getString("to"));
                            rate.setRate(jsonObject.getDouble("rate"));
                            rates.add(rate);
                        }
                        FindRates.getInstance(rates);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }



}
