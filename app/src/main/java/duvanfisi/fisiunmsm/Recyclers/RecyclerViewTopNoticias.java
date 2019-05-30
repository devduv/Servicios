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

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.ActivitiesUsers.NoticiaActivity;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Model.CNoticia;
import duvanfisi.fisiunmsm.R;

public class RecyclerViewTopNoticias extends RecyclerView.Adapter<RecyclerViewTopNoticias.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private HashMap<Integer, CNoticia> cPublicacionHashMap;
    private Context mContext;

    public RecyclerViewTopNoticias(Context context, HashMap<Integer, CNoticia> cPublicacionHashMap) {
       this.cPublicacionHashMap = cPublicacionHashMap;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noticias_top_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, final int position) {

           int puesto = position+1;
           String puesto_aux = Integer.toString(puesto) +".";
           holder.puesto.setText(puesto_aux);
           ImagePicasso.setImageCenterCop(mContext, Objects.requireNonNull(cPublicacionHashMap.get(position + 1)).getLink_foto(), holder.image_new);
           ImagePicasso.setImageCenterCop(mContext, R.drawable.ic_calendar, holder.img_calender);

           holder.tit_new.setText(cPublicacionHashMap.get(position+1).getTitulo());
           holder.fecha_new.setText(cPublicacionHashMap.get(position+1).getFecha());

           holder.button_noticia.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   activarNoticia(mContext, position+1);
               }
           });
           holder.image_new.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   activarNoticia(mContext, position+1);
               }
           });
           holder.tit_new.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   activarNoticia(mContext, position+1);
               }
           });
    }

    @Override
    public int getItemCount() {
        return cPublicacionHashMap.size()-1;
    }

    public Intent getIntent(int seleccionado){
        Intent intent = new Intent(mContext, NoticiaActivity.class);
        intent.putExtra(Utilidades.NOTICIAS, cPublicacionHashMap.get(seleccionado));
        return intent;
    }
    public void activarNoticia(Context mContext, int position) {
        StartActivity.startActivity(mContext, getIntent(position));

    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image_new;
        TextView tit_new;
        TextView fecha_new;
        ImageView img_calender;
        Button button_noticia;
        TextView puesto;

        public ViewHolder(View itemView) {
            super(itemView);
            puesto = itemView.findViewById(R.id.puesto_top);
            img_calender = itemView.findViewById(R.id.img_calender_top);
            image_new = itemView.findViewById(R.id.image_new_top);
            tit_new = itemView.findViewById(R.id.tit_new_top);
            fecha_new = itemView.findViewById(R.id.fecha_new_top);
            button_noticia = itemView.findViewById(R.id.button_vernoticia_top);
        }
    }
}