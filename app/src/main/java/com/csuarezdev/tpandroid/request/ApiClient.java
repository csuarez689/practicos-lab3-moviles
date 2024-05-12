package com.csuarezdev.tpandroid.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.csuarezdev.tpandroid.models.Usuario;

public class ApiClient {
    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context) {
        if (sp == null)
            sp = context.getSharedPreferences("data", 0);
        return sp;
    }

    public static void guardar(Context context, Usuario usuario) {
        SharedPreferences.Editor editor = conectar(context).edit();
        editor.putLong("dni",usuario.getDni());
        editor.putString("apellido",usuario.getApellido());
        editor.putString("nombre",usuario.getNombre());
        editor.putString("email",usuario.getEmail());
        editor.putString("clave",usuario.getPassword());
        editor.commit();
    }

    public static Usuario leer(Context context) {
        Usuario usuario = null;
        SharedPreferences sp = conectar(context);
        Long dni = sp.getLong("dni", -1);
        String apellido = sp.getString("apellido", "-1");
        String nombre = sp.getString("nombre", "-1");
        String email = sp.getString("email", "-1");
        String password = sp.getString("password", "-1");
        usuario = new Usuario(dni, apellido, nombre, email, password);
        return usuario;
    }

    public static Usuario login(Context context, String email, String password) {
        Usuario usuaro=null;
        SharedPreferences sp=conectar(context);
        Long dni=sp.getLong("dni", -1);
        String apellido=sp.getString("apellido", "-1");
        String nombre=sp.getString("nombre", "-1");
        String email2=sp.getString("email", "-1");
        String password2=sp.getString("password", "-1");
        if(email.equals(email2) && password.equals(password2)){
        usuaro=new Usuario(dni, apellido, nombre, email, password);
        }
        return usuaro;

    }
}
