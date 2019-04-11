package com.julioespadas.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.julioespadas.myapp.adapter.EleveAdapter;
import com.julioespadas.myapp.model.EleveBean;

import java.util.ArrayList;

public class RecyclerViewExActivity extends AppCompatActivity {

    //Composant Graphique
    private RecyclerView rv;

    //Données
    private ArrayList<EleveBean> eleves;

    //Outils
    private EleveAdapter eleveAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_ex);
        rv = findViewById(R.id.rv);

        eleves = new ArrayList<>();
        eleveAdapter = new EleveAdapter(eleves);
        rv.setAdapter(eleveAdapter);

        rv.setLayoutManager(new LinearLayoutManager(this));

        //rv.setAdapter(eleveAdapter = new EleveAdapter(eleves = new ArrayList<>()));
    }

    public void onBtClick(View view) {
        for (int i = 0; i < 10; i++) {
            eleves.add(new EleveBean("Eleve_" + eleves.size(), "Eleve"));

        }
        eleveAdapter.notifyDataSetChanged();
    }

}
