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
import duvanfisi.fisiunmsm.Model.CUsuario;
import duvanfisi.fisiunmsm.R;

public class RecyclerViewUsuarios extends RecyclerView.Adapter<RecyclerViewUsuarios.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private HashMap<Integer, CUsuario> cUsuarioHashMap;
    private Context mContext;

    public RecyclerViewUsuarios(Context context, HashMap<Integer,CUsuario> cUsuarioHashMap) {
       this.cUsuarioHashMap = cUsuarioHashMap;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla_usuarios, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String punto = ".";
        int aux_position = position+1;
        String puesto = (Integer.toString(aux_position))+punto;

        holder.puesto.setText(puesto);
        holder.nombres.setText(cUsuarioHashMap.get(position).getNombre());
        if(cUsuarioHashMap.get(position).getNickname().equalsIgnoreCase("null")){
            holder.nickname.setText("no-nickname");
            ImagePicasso.setImageCenterCop(mContext,R.drawable.ic_nickname, holder.image);
        }else {
            ImagePicasso.setImageCenterCop(mContext,cUsuarioHashMap.get(position).getPhoto(), holder.image);
            holder.nickname.setText(cUsuarioHashMap.get(position).getNickname());
        }
        holder.escuela.setText(cUsuarioHashMap.get(position).getEscuela());
        holder.facultad.setText(cUsuarioHashMap.get(position).getFacultad());

    }


    @Override
    public int getItemCount() {
        return cUsuarioHashMap.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView nombres;
        TextView puesto;
        TextView nickname;
        TextView escuela;
        TextView facultad;
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            nombres = itemView.findViewById(R.id.name_usuario);
            puesto = itemView.findViewById(R.id.puesto);
            nickname = itemView.findViewById(R.id.nickname);
            facultad = itemView.findViewById(R.id.facultad);
            escuela = itemView.findViewById(R.id.escuela);
        }
    }
}