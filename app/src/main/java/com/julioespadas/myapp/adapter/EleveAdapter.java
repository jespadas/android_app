package com.julioespadas.myapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.julioespadas.myapp.R;
import com.julioespadas.myapp.model.EleveBean;

import java.util.ArrayList;

public class EleveAdapter extends RecyclerView.Adapter<EleveAdapter.ViewHolder> {

    private ArrayList<EleveBean> data;
    private OnEleveAdapterListener onEleveAdapterListener;

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
                if (onEleveAdapterListener != null) {
                    onEleveAdapterListener.onEleveAdapterClickListener(datum, viewHolder.getAdapterPosition());
                }
                //Toast.makeText(v.getContext(), datum.getPrenom() + " " + datum.getNom(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onEleveAdapterListener != null) {
                    onEleveAdapterListener.onEleveAdapterLongClickListener(datum, viewHolder.getAdapterPosition());
                }
                return true;
            }
        });
        Glide.with(viewHolder.tv1.getContext()).load("https://i2.wp.com/www.letopdelhumour.fr/wp-content/uploads/2017/03/i282600889608361774._szw480h1280_.jpg?w=382").into(viewHolder.iv);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnEleveAdapterListener(OnEleveAdapterListener onEleveAdapterListener) {
        this.onEleveAdapterListener = onEleveAdapterListener;
    }

    public interface OnEleveAdapterListener {
        void onEleveAdapterClickListener(EleveBean eleveBean, int position);

        void onEleveAdapterLongClickListener(EleveBean eleveBean, int position);
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
