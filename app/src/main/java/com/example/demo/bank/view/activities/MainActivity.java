package com.example.demo.bank.view.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.demo.bank.R;
import com.example.demo.bank.databinding.ActivityMainBinding;
import com.example.demo.bank.viewModel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {


    MainActivityViewModel loginViewModel;
    ActivityMainBinding activityLoginBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityLoginBinding.setLifecycleOwner(this);
        loginViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        activityLoginBinding.setViewModel(loginViewModel);
    }

}
