package com.csuarezdev.tpandroid.ui.login;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.csuarezdev.tpandroid.models.Usuario;
import com.csuarezdev.tpandroid.request.ApiClient;
import com.csuarezdev.tpandroid.ui.register.RegisterActivity;

public class MainActivityViewModel extends AndroidViewModel {
    private final Context context;
    private final ApiClient apiClient;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        apiClient=new ApiClient();
    }


    public void login(String email, String password) {
        if (email.length() > 0 && password.length() > 0) {
            Usuario usuario = null;
                usuario = apiClient.login(context, email, password);

            if (usuario != null) {
                Intent intent=new Intent(context, RegisterActivity.class);
                intent.putExtra("logged", true);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Credenciales incorrectas...", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Ingrese sus credenciales...", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToRegister(){
        Intent intent=new Intent(context, RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
