package duvanfisi.fisiunmsm.Recyclers;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Extras.ADialogs;
import duvanfisi.fisiunmsm.Model.CServicio;
import duvanfisi.fisiunmsm.R;

public class RecyclerViewServices extends RecyclerView.Adapter<RecyclerViewServices.ViewHolder>{

    private HashMap<Integer, CServicio> cServicioHashMap;
    private Context mContext;

    public RecyclerViewServices(Context context, HashMap<Integer, CServicio> cServicioHashMap) {
       this.cServicioHashMap = cServicioHashMap;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.servicios_list,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    public Bundle getBundle(int seleccionado){
        Bundle bundle = new Bundle();
        bundle.putParcelable("servicio", cServicioHashMap.get(seleccionado));
        return bundle;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

      Picasso.with(mContext)
                .load(cServicioHashMap.get(position).getPhoto())
                .fit()
                .centerCrop()
                .into(holder.image_services);
        holder.nombre.setText(cServicioHashMap.get(position).getNombre());
        holder.adicional.setText(cServicioHashMap.get(position).getAdicional());
        holder.informacion.setText(cServicioHashMap.get(position).getInformacion());

      /*  holder.image_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = AlertDialogs.info_servicio(mContext, getBundle(position));
                dialog.show();
            }
        });*/

       holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = ADialogs.info_servicio(mContext, getBundle(position));
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cServicioHashMap.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image_services;
        TextView nombre;
        TextView informacion;
        TextView adicional;
        CardView parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image_services = itemView.findViewById(R.id.image_service);
            nombre = itemView.findViewById(R.id.nombre);
            informacion = itemView.findViewById(R.id.informacion);
            adicional = itemView.findViewById(R.id.adicional);
            parentLayout = itemView.findViewById(R.id.rela);
        }
    }
}