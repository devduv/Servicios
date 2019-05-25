package duvanfisi.fisiunmsm.Recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.Modelo.CPublicacion;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Templates.PlantillaPublicacion;

public class RecyclerViewPublicacion extends RecyclerView.Adapter<RecyclerViewPublicacion.ViewHolder>{
    ;

    private HashMap<Integer, CPublicacion> cPublicacionHashMap;
    private Context mContext;

    public RecyclerViewPublicacion(Context context, HashMap<Integer, CPublicacion> cPublicacionHashMap) {
      this.cPublicacionHashMap = cPublicacionHashMap;
        mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla_publicacion, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /*public Intent getIntent(int seleccionado){
        Intent intent = new Intent(mContext, NoticiaActivity.class);
        intent.putExtra(Utilidades.NOTICIAS, cPublicacionHashMap.get(seleccionado));
        return intent;
    }*/
    /*public void activarNoticia(Context mContext, int position) {
        StartActivity.startActivity(mContext, getIntent(position));

    }
*/
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String tipo = cPublicacionHashMap.get(position).getTipo();
        if(tipo.equalsIgnoreCase("news")){
            ImagePicasso.setImageCenterCop(mContext, R.drawable.icon_news, holder.img);
            holder.tit_new_pub.setText(cPublicacionHashMap.get(position).getTitulo());
            ImagePicasso.setImageCenterInsideWrap(mContext, cPublicacionHashMap.get(position).getPhoto_pub(), holder.imgpub);
            holder.button.setVisibility(ViewVisible.INVISIBLE);
        }
        if(tipo.equalsIgnoreCase("pub")){
            holder.tit_new_pub.setVisibility(ViewVisible.INVISIBLE);
            ImagePicasso.setImageCenterCop(mContext, cPublicacionHashMap.get(position).getPhoto_user(), holder.img);
           // ImagePicasso.setImageCenterInsideWrap(mContext, R.drawable.icon_new_user, holder.imgpub);
        }
        holder.nombre.setText(cPublicacionHashMap.get(position).getNombre_user());
        holder.fecha_pub.setText(cPublicacionHashMap.get(position).getFecha());
        holder.date.setText(cPublicacionHashMap.get(position).getDate_user());
        holder.descrp.setText(cPublicacionHashMap.get(position).getDescription());


    }


    @Override
    public int getItemCount() {
        return cPublicacionHashMap.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private PlantillaPublicacion plantillaPublicacion;

        private TextView nombre;
        private ImageView img;
        private TextView descrp;
        private ImageView imgpub;
        private TextView fecha_pub;
        private TextView date;
        private TextView tit_new_pub;
        private Button button;
        public ViewHolder(View view) {
            super(view);

            this.img = view.findViewById(R.id.image_user);
            this.nombre = view.findViewById(R.id.name_user);
            this.fecha_pub = view.findViewById(R.id.fecha_pub);
            this.descrp = view.findViewById(R.id.descripcion_pub);
            this.imgpub = view.findViewById(R.id.image_pub);
            this.date = view.findViewById(R.id.fac_user);
            this.tit_new_pub = view.findViewById(R.id.tit_new_pub);

            this.button = view.findViewById(R.id.button_add);

        }
    }
}