package com.ugb.home_meic.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ugb.home_meic.R;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    ArrayList<FeaturedHelperClass> featuredLocations;

    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredLocations) {
        this.featuredLocations = featuredLocations;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_desing, parent, false);
        FeaturedViewHolder FeaturedViewHolder = new FeaturedViewHolder(view);

        return FeaturedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  FeaturedViewHolder holder, int position) {

        FeaturedHelperClass featuredHelperClass =  featuredLocations.get(position);

        holder.imagen.setImageResource(featuredHelperClass.getImagen());
        holder.titulo.setText(featuredHelperClass.getTitulo());
        holder.descripcion.setText(featuredHelperClass.getDescripcion());

    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        ImageView imagen;
        TextView titulo, descripcion;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.featured_imagen);
            titulo = itemView.findViewById(R.id.featured_titulo);
            descripcion = itemView.findViewById(R.id.featured_descripcion);


        }
    }

}

