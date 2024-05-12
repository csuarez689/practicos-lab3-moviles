package com.csuarezdev.tpandroid.ui.register;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.csuarezdev.tpandroid.R;
import com.csuarezdev.tpandroid.databinding.ActivityRegisterBinding;
import com.csuarezdev.tpandroid.ui.login.MainActivityViewModel;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    private RegisterActivityViewModel vModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vModel = new ViewModelProvider(this).get(RegisterActivityViewModel.class);

        vModel.getMutableUsuario().observe(this, usuario -> {
            binding.etNombre.setText(usuario.getNombre());
            binding.etApellido.setText(usuario.getApellido());
            binding.etDni.setText(String.valueOf(usuario.getDni()));
            binding.etEmailReg.setText(usuario.getEmail());
            binding.etPasswordReg.setText(usuario.getPassword());
        });

        vModel.leerUsuario(getIntent());

        binding.btnGuardarUsuario.setOnClickListener(v ->
                vModel.register(binding.etDni.getText().toString(),
                        binding.etApellido.getText().toString(),
                        binding.etNombre.getText().toString(),
                        binding.etEmailReg.getText().toString(),
                        binding.etPasswordReg.getText().toString()));
    }
}
