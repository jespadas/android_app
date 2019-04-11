package com.julioespadas.myapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.julioespadas.myapp.R;
import com.julioespadas.myapp.model.EleveBean;

import java.util.ArrayList;

public class EleveAdapter extends RecyclerView.Adapter<EleveAdapter.ViewHolder> {

    private ArrayList<EleveBean> data;

    public EleveAdapter(ArrayList<EleveBean> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_eleve, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        EleveBean datum = data.get(position);

        viewHolder.tv1.setText(datum.getPrenom());
        viewHolder.tv2.setText(datum.getNom());
        viewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), datum.getPrenom() + " " + datum.getNom(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2;
        ImageView iv;
        View root;

        public ViewHolder(View v) {
            super(v);
            root = v.findViewById(R.id.root);
            tv1 = v.findViewById(R.id.tv1);
            tv2 = v.findViewById(R.id.tv2);
            iv = v.findViewById(R.id.iv);
        }

    }
}
