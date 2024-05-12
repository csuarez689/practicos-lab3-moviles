package com.csuarezdev.tpandroid.ui.login;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.csuarezdev.tpandroid.R;
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

//        binding.btnLogin.setOnClickListener(v->{
//                vModel.login(
//                        binding.etUsuario.getText().toString(),
//                        binding.etContrasenia.getText().toString()
//                );
//        });
//
//        binding.btnRegistrar.setOnClickListener(v -> {
//            vModel.registrar();
//        });
    }


}