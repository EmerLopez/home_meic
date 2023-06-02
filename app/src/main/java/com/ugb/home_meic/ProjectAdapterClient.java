package com.ugb.home_meic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProjectAdapterClient extends RecyclerView.Adapter<ProjectAdapterClient.ViewHolder>{

    ArrayList<ProjectModelClient> list;
    Context context;
    public ProjectAdapterClient(ArrayList<ProjectModelClient> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProjectModelClient model = list.get(position);

        Picasso.get().load(model.getImagenPructo()).placeholder(R.drawable.audio1).into(holder.itemImagen);

        holder.itemEncabezado.setText(model.getNombreProducto());
        holder.itemDescripcion.setText(model.getDescripcion());
        holder.itemPrecio.setText(model.getPrecio());
        holder.itemMarca.setText(model.getMarca());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleClient.class);
                intent.putExtra( "solImagen", model.getImagenPructo());
                intent.putExtra( "solNombreProducto", model.getNombreProducto());
                intent.putExtra( "solPrecio", model.getPrecio());
                intent.putExtra( "solMarca", model.getMarca());
                intent.putExtra( "solDescripcion", model.getDescripcion());
                intent.putExtra( "solEspecificaciones", model.getEspecificaciones());
                intent.putExtra( "solDisponibilidad", model.getDisponibilidad());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemEncabezado, itemDescripcion, itemPrecio, itemMarca;
        ImageView itemImagen;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemEncabezado = itemView.findViewById(R.id.itemEncabezado);
            itemDescripcion = itemView.findViewById(R.id.itemDescripcion);
            itemPrecio = itemView.findViewById(R.id.itemPrecio);
            itemMarca = itemView.findViewById(R.id.itemMarca);

            itemImagen = itemView.findViewById(R.id.itemImagen);
        }
    }
}