package com.csuarezdev.tpandroid.ui.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.csuarezdev.tpandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel vModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        binding.btnLogin.setOnClickListener(v ->
                vModel.login(
                        binding.etEmail.getText().toString(),
                        binding.etPassword.getText().toString()
                )
        );

        binding.btnRegistrar.setOnClickListener(v -> {
            vModel.goToRegister();
        });


    }


}