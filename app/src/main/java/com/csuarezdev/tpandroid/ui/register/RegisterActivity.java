package com.csuarezdev.tpandroid.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.csuarezdev.tpandroid.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private static int REQUEST_IMAGE_CAPTURE = 1;
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
                        binding.etPasswordReg.getText().toString())
                );

        binding.btnTomarFoto.setOnClickListener(v -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            //}
        });

        vModel.getMutableFoto().observe(this, bitmap -> binding.ivFoto.setImageBitmap(bitmap));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        vModel.setUserPhoto(requestCode, resultCode, data, 1);
    }
}
