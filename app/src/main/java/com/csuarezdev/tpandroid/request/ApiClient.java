package com.csuarezdev.tpandroid.request;

import android.content.Context;
import android.widget.Toast;

import com.csuarezdev.tpandroid.models.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {
    public static void guardar(Context context, Usuario usuario) {
        try {
            File file = new File(context.getFilesDir(), "usuarios.dat");
            FileOutputStream fos = new FileOutputStream(file, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(usuario);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            Toast.makeText(context, "No se pudo acceder al archivo", Toast.LENGTH_SHORT).show();
        }
    }

    public static Usuario leer(Context context) {
        Usuario usuario = null;

        try {
            File archivo = new File(context.getFilesDir(), "usuarios.dat");
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            usuario = (Usuario) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException  | ClassNotFoundException e) {
            Toast.makeText(context, "No se pudo acceder al archivo", Toast.LENGTH_SHORT).show();
        }
        return usuario;
    }

    public static Usuario login(Context context, String email, String password) {
        Usuario usuario = leer(context);
        if (usuario != null && usuario.getEmail().equals(email) && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;

    }


}
