package com.julioespadas.myapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.julioespadas.myapp.utils.OkHttpUtils;

public class WebExActivity extends AppCompatActivity {

    MonAT monAT;
    private TextView tv;
    private EditText et;
    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_ex);
        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);
        wv = findViewById(R.id.wv);

        // Réglages

        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);
    }

    public void onClick(View view) {
        // Je donne l'url a la webview
        wv.loadUrl(et.getText().toString());
        // Lancer l'asynctask
        if (monAT == null || monAT.getStatus() == AsyncTask.Status.FINISHED) {
            monAT = new MonAT();
            monAT.execute();
        }
    }

    public class MonAT extends AsyncTask {

        String resultat;
        Exception exception;

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                resultat = OkHttpUtils.sendGetOkHttpRequest(et.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (exception != null) {
                Toast.makeText(WebExActivity.this, "We have a problem :" + exception.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                tv.setText(resultat);
            }
        }
    }
}
