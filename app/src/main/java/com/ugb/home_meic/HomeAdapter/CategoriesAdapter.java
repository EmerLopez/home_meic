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

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.AdapterAllCategoriesViewHolder> {

    ArrayList<CategoriesHelperClass> mostViewedLocations;

    public CategoriesAdapter(ArrayList<CategoriesHelperClass> mostViewedLocations) {
        this.mostViewedLocations = mostViewedLocations;
    }

    @NonNull
    @Override
    public AdapterAllCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_ofertas, parent, false);
        AdapterAllCategoriesViewHolder lvh = new AdapterAllCategoriesViewHolder(view);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAllCategoriesViewHolder holder, int position) {

        CategoriesHelperClass helperClass = mostViewedLocations.get(position);

        holder.imagen1.setImageResource(helperClass.getImagen());
        holder.logo.setImageResource(helperClass.getImagenlogo());
        holder.titulo.setText(helperClass.getTitulo());
        holder.producto.setText(helperClass.getProducto());
        holder.oferta.setText(helperClass.getPrecio());
        holder.exclusivo.setText(helperClass.getExclusivo());
        holder.nombre.setText(helperClass.getNombre());

    }

    @Override
    public int getItemCount() {
        return mostViewedLocations.size();
    }

    public static class AdapterAllCategoriesViewHolder extends RecyclerView.ViewHolder {

        ImageView imagen1, logo;
        TextView titulo, producto, oferta, exclusivo, nombre;

        public AdapterAllCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            imagen1 = itemView.findViewById(R.id.ms_mueble);
            logo = itemView.findViewById(R.id.ms_logo);
            titulo = itemView.findViewById(R.id.ms_texto);
            producto = itemView.findViewById(R.id.ms_texto1);
            oferta = itemView.findViewById(R.id.ms_oferta);
            exclusivo = itemView.findViewById(R.id.ms_exclusivo);
            nombre = itemView.findViewById(R.id.ms_nombre);

        }
    }
}