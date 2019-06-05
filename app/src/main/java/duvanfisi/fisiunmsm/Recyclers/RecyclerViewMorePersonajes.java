package duvanfisi.fisiunmsm.Recyclers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Activities.PersonajeActivity;
import duvanfisi.fisiunmsm.Model.CPersonaje;
import duvanfisi.fisiunmsm.R;

public class RecyclerViewMorePersonajes extends RecyclerView.Adapter<RecyclerViewMorePersonajes.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private HashMap<Integer, CPersonaje> cPersonajeHashMap;
    private Context mContext;
    private int layout;

    public RecyclerViewMorePersonajes(Context context, HashMap<Integer, CPersonaje> cPersonajeHashMap) {
       this.cPersonajeHashMap = cPersonajeHashMap;
        mContext = context;
        this.layout = layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personaje_more_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activarPersonaje(position);
                    }
        });
      Picasso.with(mContext)
                .load(cPersonajeHashMap.get(position).getPhoto())
                .fit()
                .centerInside()
                .into(holder.image);


      if(cPersonajeHashMap.get(position).getNames().length()>17){
          String aux_nombre[] = cPersonajeHashMap.get(position).getNames().split(" ");
          String nombre = aux_nombre[0] +" " +aux_nombre[1].substring(0,1)+".";
          holder.imageName.setText(nombre);
      }else{
          holder.imageName.setText(cPersonajeHashMap.get(position).getNames());
      }

        Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_love_selected);
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
        TextView imageName;
        CardView parentLayout;


        public ViewHolder(View itemView) {

            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.name);
            parentLayout = itemView.findViewById(R.id.rela_more_person);
        }
    }
}