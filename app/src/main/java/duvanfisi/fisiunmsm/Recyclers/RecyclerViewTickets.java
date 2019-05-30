package duvanfisi.fisiunmsm.Recyclers;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.ActivitiesUsers.TicketActivity;
import duvanfisi.fisiunmsm.Model.CTicket;
import duvanfisi.fisiunmsm.R;

public class RecyclerViewTickets extends RecyclerView.Adapter<RecyclerViewTickets.ViewHolder>{
    ;

    private HashMap<Integer, CTicket> cTicketHashMap;
    private Context mContext;

    public RecyclerViewTickets(Context context, HashMap<Integer, CTicket> cTicketHashMap) {
      this.cTicketHashMap = cTicketHashMap;
        mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla_ticket, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public Intent getIntent(int seleccionado){
        Intent intent = new Intent(mContext, TicketActivity.class);
        intent.putExtra(Utilidades.TICKETKEY, cTicketHashMap.get(seleccionado));
        return intent;
    }
    public void activarTicket(Context mContext, int position) {
        StartActivity.startActivity(mContext, getIntent(position));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.ticket.setText(cTicketHashMap.get(position).get_id());
        holder.fecha.setText(cTicketHashMap.get(position).getFecha_retiro());
        holder.comida.setText(codigoComida(cTicketHashMap.get(position).get_id_comida()));
        holder.sede.setText(codigoSede(cTicketHashMap.get(position).get_id_sede()));

        if(!cTicketHashMap.get(position).isConsumido()){
            holder.consumido.setText("No Consumido");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.consumido.setCompoundDrawableTintList(ColorStateList.valueOf(mContext.getResources().getColor(android.R.color.holo_red_light)));
            }
        }else{
            holder.consumido.setText("Consumido");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.consumido.setCompoundDrawableTintList(ColorStateList.valueOf(mContext.getResources().getColor(android.R.color.holo_green_light

                )));
            }
        }
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activarTicket(mContext, position);
            }
        });
        holder.imgticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activarTicket(mContext, position);
            }
        });
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activarTicket(mContext, position);
            }
        });
    }

    public String codigoSede(int idsede){
        String sede = "";
        switch(idsede){
            case 1:
                sede ="Ciudad Universitaria";
                break;
            case 2:
                sede = "San Fernando";
                break;
            case 3:
                sede = "SJL";
                break;
        }
        return sede;
    }
    public String codigoComida(int idcomida){
        String comida = "";
        switch(idcomida){
            case 1:
                comida ="Almuerzo";
                break;
            case 2:
                comida = "Cena";
                break;
        }
        return comida;
    }
    @Override
    public int getItemCount() {
        return cTicketHashMap.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView ticket;
        ImageView imgticket;
        TextView comida;
        TextView sede;
        TextView fecha;
        Button btn;
        TextView consumido;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ticket = itemView.findViewById(R.id.ticket);
            comida = itemView.findViewById(R.id.comida);
            sede = itemView.findViewById(R.id.sede);
            fecha = itemView.findViewById(R.id.fecha);
            imgticket = itemView.findViewById(R.id.icon_ticket);
            btn = itemView.findViewById(R.id.detalles_t);
            consumido = itemView.findViewById(R.id.consumido);
            relativeLayout = itemView.findViewById(R.id.relaticket);
        }
    }
}