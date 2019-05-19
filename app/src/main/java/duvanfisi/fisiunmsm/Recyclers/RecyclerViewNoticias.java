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
import duvanfisi.fisiunmsm.ActivitiesUsers.NoticiaActivity;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Modelo.CNoticia;
import duvanfisi.fisiunmsm.R;

public class RecyclerViewNoticias extends RecyclerView.Adapter<RecyclerViewNoticias.ViewHolder>{
    ;

    private HashMap<Integer, CNoticia> noticias;
    private Context mContext;

    public RecyclerViewNoticias(Context context, HashMap<Integer, CNoticia> cPublicacionHashMap) {
      this.noticias = cPublicacionHashMap;
        mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla_news, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public Intent getIntent(int seleccionado){
        Intent intent = new Intent(mContext, NoticiaActivity.class);
        intent.putExtra(Utilidades.NOTICIAS, noticias.get(seleccionado));
        return intent;
    }
    public void activarNoticia(Context mContext, int position) {
        StartActivity.startActivity(mContext, getIntent(position));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        ImagePicasso.setImageCenterInside(mContext, noticias.get(position).getLink_foto(), holder.image_new);
        ImagePicasso.setImageCenterCop(mContext, R.drawable.ic_calendar, holder.img_calender);

        holder.tit_new.setText(noticias.get(position).getTitulo());
        holder.fecha_new.setText(noticias.get(position).getFecha());
        holder.desc_new.setText(noticias.get(position).getDescripcion_1());

        holder.tit_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activarNoticia(mContext, position);
            }
        });
        holder.image_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activarNoticia(mContext, position);
            }
        });

        holder.button_noticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activarNoticia(mContext, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return noticias.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image_new;
        TextView tit_new;
        TextView fecha_new;
        TextView desc_new;
        ImageView img_calender;
        Button button_noticia;

        public ViewHolder(View itemView) {
            super(itemView);
            img_calender = itemView.findViewById(R.id.img_calender);
            image_new = itemView.findViewById(R.id.image_new);
            tit_new = itemView.findViewById(R.id.tit_new);
            fecha_new = itemView.findViewById(R.id.fecha_new);
            desc_new = itemView.findViewById(R.id.desc_new);
            button_noticia = itemView.findViewById(R.id.button_vernoticia);
        }
    }
}