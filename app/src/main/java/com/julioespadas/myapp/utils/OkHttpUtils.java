package com.julioespadas.myapp.utils;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {

    public static String sendGetOkHttpRequest(String url) throws Exception {
        Log.w("TAG", "url: " + url);
        OkHttpClient client = new OkHttpClient();
        // Création de la requete
        Request request = new Request.Builder().url(url).build();
        // Execution de la requete
        Response response = client.newCall(request).execute();
        // Analyse du code retour
        if (response.code() < 200 || response.code() >= 300) {
            throw new Exception("Message from serveur : " + response.code());
        } else {
            // Résultat de la requete
            // .string() ne peut être appelée qu'une seule fois
            return response.body().string();
        }
    }

}
