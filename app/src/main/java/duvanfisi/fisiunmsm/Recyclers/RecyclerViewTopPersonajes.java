package duvanfisi.fisiunmsm.Recyclers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Activities.PersonajeActivity;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Model.CPersonaje;
import duvanfisi.fisiunmsm.R;

public class RecyclerViewTopPersonajes extends RecyclerView.Adapter<RecyclerViewTopPersonajes.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private HashMap<Integer, CPersonaje> cPersonajeHashMap;
    private Context mContext;

    public RecyclerViewTopPersonajes(Context context, HashMap<Integer,CPersonaje> cPersonajeHashMap) {
       this.cPersonajeHashMap = cPersonajeHashMap;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personajes_top_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String punto = ".";
        int aux_position = position+1;
        String puesto = (Integer.toString(aux_position))+punto;
        ImagePicasso.setImageCenterCop(mContext,cPersonajeHashMap.get(position).getPhoto(), holder.image);
        holder.puesto.setText(puesto);
        holder.nombres.setText(cPersonajeHashMap.get(position).getNames());
        holder.desc.setText(cPersonajeHashMap.get(position).getDescripcion());
        holder.ciudad_natal.setText(cPersonajeHashMap.get(position).getCiudad_natal());
        holder.fecha_nac.setText(cPersonajeHashMap.get(position).getFecha_nac());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activarPersonaje(position);
            }
        });

        holder.nombres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activarPersonaje(position);
            }
        });
        holder.desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activarPersonaje(position);
            }
        });
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activarPersonaje(position);
            }
        });
    }

    private void activarPersonaje(int position){
        StartActivity.startActivity(mContext, getIntent(position));

    }

    private Intent getIntent(int seleccionado){
        Intent intent = new Intent(mContext, PersonajeActivity.class);
        intent.putExtra(Utilidades.PERSONAJES, cPersonajeHashMap.get(seleccionado));
        return intent;
    }

    @Override
    public int getItemCount() {
        return cPersonajeHashMap.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView nombres;
        TextView puesto;
        TextView desc;
        TextView ciudad_natal;
        TextView fecha_nac;
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            nombres = itemView.findViewById(R.id.name_personaje);
            puesto = itemView.findViewById(R.id.puesto);
            desc = itemView.findViewById(R.id.desc_personaje);
            fecha_nac = itemView.findViewById(R.id.fecha_nac);
            ciudad_natal = itemView.findViewById(R.id.ciudad_natal);
            button = itemView.findViewById(R.id.button_vernoticia);
        }
    }
}