package com.csuarezdev.tpandroid.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.csuarezdev.tpandroid.models.Usuario;

public class ApiClient {
    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context) {
        if (sp == null)
            sp = context.getSharedPreferences("datos", 0);
        return sp;
    }

    public static void guardar(Context context, Usuario usuario) {
        SharedPreferences.Editor editor = conectar(context).edit();
        editor.putLong("dni", usuario.getDni());
        editor.putString("apellido", usuario.getApellido());
        editor.putString("nombre", usuario.getNombre());
        editor.putString("email", usuario.getEmail());
        editor.putString("password", usuario.getPassword());
        editor.commit();
    }

    public static Usuario leer(Context context) {
        Usuario usuario = null;
        SharedPreferences sp = conectar(context);
        usuario = new Usuario(sp.getLong("dni", -1)
                , sp.getString("apellido", "-1"),
                sp.getString("nombre", "-1"),
                sp.getString("email", "-1"),
                sp.getString("password", "-1"));
        return usuario;
    }

    public static Usuario login(Context context, String email, String password) {
        Usuario usuario = null;
        SharedPreferences sp = conectar(context);

        String email2 = sp.getString("email", "-1");
        String password2 = sp.getString("password", "-1");

        if (email.equals(email2) && password.equals(password2)) {
            usuario = new Usuario(sp.getLong("dni", -1)
                    , sp.getString("apellido", "-1"),
                    sp.getString("nombre", "-1"),
                    email2,
                    password2);
        }
        return usuario;

    }
}
