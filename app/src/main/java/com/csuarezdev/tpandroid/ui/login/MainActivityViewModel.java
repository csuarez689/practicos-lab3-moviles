package com.csuarezdev.tpandroid.ui.login;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public void login(){

    }

    public void registrar(){

    }
}
