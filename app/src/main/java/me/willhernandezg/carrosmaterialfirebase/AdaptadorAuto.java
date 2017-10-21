package me.willhernandezg.carrosmaterialfirebase;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by WillHernandezG on 21/10/2017.
 */

public class AdaptadorAuto extends RecyclerView.Adapter<AdaptadorAuto.AutoViewHolder>  {
    private ArrayList<Auto> autos;
    private Resources res;
    private OnAutoClickListener clickListener;

    public AdaptadorAuto(Context contexto, ArrayList<Auto> autos, OnAutoClickListener clickListener) {
        this.autos = autos;
        res = contexto.getResources();
        this.clickListener = clickListener;
    }

    @Override
    public AutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_autos,parent,false);
        return  new AutoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorAuto.AutoViewHolder holder, int position) {
        final Auto a = autos.get(position);
        holder.foto.setImageDrawable(ResourcesCompat.getDrawable(res, a.getFoto(),null));
        holder.placa.setText(a.getPlaca());
        holder.marca.setText(res.getStringArray(R.array.marca)[a.getMarca()]);
        holder.modelo.setText(res.getStringArray(R.array.modelo)[a.getModelo()]);
        holder.color.setText(res.getStringArray(R.array.color)[a.getColor()]);
        holder.precio.setText(a.getPrecio());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.OnAutoClick(a);
            }
        });
    }

    @Override
    public int getItemCount() {
        return autos.size();
    }


    public static class AutoViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView placa, marca, modelo, color, precio;
        private View v;

        public AutoViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            foto = (ImageView) itemView.findViewById(R.id.imgFoto);
            placa = (TextView) itemView.findViewById(R.id.lblPlaca);
            marca = (TextView) itemView.findViewById(R.id.lblMarca);
            modelo = (TextView) itemView.findViewById(R.id.lblModelo);
            color = (TextView) itemView.findViewById(R.id.lblColor);
            precio  = (TextView) itemView.findViewById(R.id.lblPrecio);

        }
    }

    public interface OnAutoClickListener{
        void OnAutoClick(Auto a);
    }

}
