package com.ugb.home_meic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapterMueble extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataClass> dataList;
    public DetailAdapterMueble(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recProducto.setText(dataList.get(position).getDataproducto());
        holder.recPrecio.setText(dataList.get(position).getDataprecio());
        holder.recMarca.setText(dataList.get(position).getDataMarca());
        holder.recDesc.setText(dataList.get(position).getDataDesc());
        // holder.recEspecs.setText(dataList.get(position).getDataespecs());
        //holder.recDisp.setText(dataList.get(position).getDatadisp());


        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, detailMuebleActivity.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Producto", dataList.get(holder.getAdapterPosition()).getDataproducto());
                intent.putExtra("Precio", dataList.get(holder.getAdapterPosition()).getDataprecio());
                intent.putExtra("Marca", dataList.get(holder.getAdapterPosition()).getDataMarca());
                intent.putExtra("Descripcion", dataList.get(holder.getAdapterPosition()).getDataDesc());
                intent.putExtra("Especificaciones", dataList.get(holder.getAdapterPosition()).getDataespecs());
                intent.putExtra("Disponibilidad", dataList.get(holder.getAdapterPosition()).getDatadisp());

                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());

                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }


}
class MyViewHolderAdapterM extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recProducto, recDesc, recPrecio, recMarca, recDisp, recEspecs;
    CardView recCard;
    public MyViewHolderAdapterM(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recDesc = itemView.findViewById(R.id.recDesc);
        recPrecio = itemView.findViewById(R.id.recPrecio);
        recProducto = itemView.findViewById(R.id.recProducto);
        recMarca = itemView.findViewById(R.id.recMarca);
    }
}

