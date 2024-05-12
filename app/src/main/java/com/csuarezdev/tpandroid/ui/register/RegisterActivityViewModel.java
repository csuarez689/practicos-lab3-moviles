package com.csuarezdev.tpandroid.ui.register;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.csuarezdev.tpandroid.models.Usuario;
import com.csuarezdev.tpandroid.request.ApiClient;
import com.csuarezdev.tpandroid.ui.login.MainActivity;

public class RegisterActivityViewModel extends AndroidViewModel {
    private Context context;
    private ApiClient apiClient;
    private MutableLiveData<Usuario> mutableUsuario;

    public RegisterActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiClient = new ApiClient();
    }

    public LiveData<Usuario> getMutableUsuario() {
        if (mutableUsuario == null) {
            mutableUsuario = new MutableLiveData<>();
        }
        return mutableUsuario;
    }

    public void leerUsuario(Intent intent) {
        boolean logged = (boolean) intent.getBooleanExtra("logged", false);
        if (logged) {
            Usuario usuario = apiClient.leer(context);
            mutableUsuario.setValue(usuario);
        } else {
            mutableUsuario.setValue(new Usuario(0, "", "", "", ""));
        }
    }

    public void register(String dni, String apellido, String nombre, String email, String password) {
        try {
            Long dniLong = Long.parseLong(dni);
            Usuario usuario = new Usuario(dniLong, apellido, nombre, email, password);
            apiClient.guardar(context, usuario);
            Toast.makeText(context, "Usuario registrado...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
        catch (NumberFormatException e){
            Toast.makeText(context, "Ingrese un DNI valido...", Toast.LENGTH_SHORT).show();
        }
    }
}
