package com.example.demo.bank.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface APIInterface {

    @GET("transactions.json")
    Call<Object> getTransactions(@Header("Accept") String MIMEtype);

    @GET("rates.json")
    Call<Object> getRates(@Header("Accept") String MIMEtype);
}
