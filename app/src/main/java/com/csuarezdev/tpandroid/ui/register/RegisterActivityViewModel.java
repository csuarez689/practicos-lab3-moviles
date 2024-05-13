package com.csuarezdev.tpandroid.ui.register;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.csuarezdev.tpandroid.models.Usuario;
import com.csuarezdev.tpandroid.request.ApiClient;
import com.csuarezdev.tpandroid.ui.login.MainActivity;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterActivityViewModel extends AndroidViewModel {
    private static int REQUEST_IMAGE_CAPTURE = 1;
    private Context context;
    private ApiClient apiClient;
    private MutableLiveData<Usuario> mutableUsuario;
    private MutableLiveData<Bitmap> mutableFoto;

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

    public LiveData<Bitmap> getMutableFoto() {
        if (mutableFoto == null) {
            mutableFoto = new MutableLiveData<>();
        }
        return mutableFoto;
    }

    public void leerUsuario(Intent intent) {
        boolean logged = (boolean) intent.getBooleanExtra("logged", false);
        if (logged) {
            Usuario usuario = apiClient.leer(context);
            mutableUsuario.setValue(usuario);
            File archivo =new File(context.getFilesDir(),"foto.png");
            Bitmap imageBitmap = null;
            if(archivo.exists()) {
                 BitmapFactory.decodeFile(archivo.getAbsolutePath());
            }
            if(imageBitmap!=null) {
                mutableFoto.setValue(imageBitmap);
            }
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
        } catch (NumberFormatException e) {
            Toast.makeText(context, "Ingrese un DNI valido...", Toast.LENGTH_SHORT).show();
        }
    }


    public  void setUserPhoto(int requestCode, int resultCode, Intent data, int REQUEST_IMAGE_CAPTURE) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Recupero los datos provenientes de la camara.
            Bundle extras = data.getExtras();
            //Casteo a bitmap lo obtenido de la camara.
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            //Rutina para optimizar la foto,
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            mutableFoto.setValue(imageBitmap);

            //Rutina para convertir a un arreglo de byte los datos de la imagen
            byte [] b = baos.toByteArray();

            File archivo = new File(context.getFilesDir(), "foto.png");
            if(archivo.exists()){
                archivo.delete();
            }
            try{
                FileOutputStream fos = new FileOutputStream(archivo);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                bos.write(b);
                bos.flush();
                bos.close();
            } catch (IOException e){
                Toast.makeText(context, "No se pudo tomar la foto...", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
