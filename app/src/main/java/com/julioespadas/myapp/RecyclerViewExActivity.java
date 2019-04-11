package com.julioespadas.myapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.julioespadas.myapp.adapter.EleveAdapter;
import com.julioespadas.myapp.model.EleveBean;
import com.julioespadas.myapp.model.WSUtils;

import java.util.ArrayList;

public class RecyclerViewExActivity extends AppCompatActivity implements EleveAdapter.OnEleveAdapterListener {

    MonAT monAt;
    //Composant Graphique
    private RecyclerView rv;
    private ProgressBar pb;
    //Données
    private ArrayList<EleveBean> eleves;
    //Outils
    private EleveAdapter eleveAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_ex);
        rv = findViewById(R.id.rv);
        pb = findViewById(R.id.pb);

        eleves = new ArrayList<>();
        eleveAdapter = new EleveAdapter(eleves);
        eleveAdapter.setOnEleveAdapterListener(this);
        rv.setAdapter(eleveAdapter);

        rv.setLayoutManager(new LinearLayoutManager(this));

        //rv.setAdapter(eleveAdapter = new EleveAdapter(eleves = new ArrayList<>()));
    }

    public void onBtClick(View view) {
        eleves.add(new EleveBean("Eleve_" + eleves.size(), "Eleve"));
        eleveAdapter.notifyItemInserted(0);
    }

    public void onBtChargerClick(View view) {
        if (monAt == null || monAt.getStatus() == AsyncTask.Status.FINISHED) {
            monAt = new MonAT();
            monAt.execute();
        } else {
            Toast.makeText(this, "You have to wait...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setOnEleveAdapterListener(EleveBean eleveBean, int position) {
        eleves.remove(position);
        eleves.add(0, eleveBean);
        eleveAdapter.notifyItemMoved(position, 0);
    }

    @Override
    public void onEleveAdapterLongClickListener(EleveBean eleveBean, int position) {
        eleves.remove(position);
        eleveAdapter.notifyItemRemoved(position);
    }

    public class MonAT extends AsyncTask {
        EleveBean resultat;
        Exception exception;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                resultat = WSUtils.loadEleveFromWeb();
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            pb.setVisibility(View.GONE);
            if (exception != null) {
                Toast.makeText(RecyclerViewExActivity.this, "We have a problem : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                eleves.add(0, resultat);
                eleveAdapter.notifyItemInserted(0);
            }
        }
    }
}

