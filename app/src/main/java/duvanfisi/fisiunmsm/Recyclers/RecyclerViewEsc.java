package duvanfisi.fisiunmsm.Recyclers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import duvanfisi.fisiunmsm.Modelo.CEscuela;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.ActivitiesUsers.RegisterEscuelaActivity;


public class RecyclerViewEsc extends RecyclerView.Adapter<RecyclerViewEsc.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<CEscuela> escuelas;
    private ArrayList<RadioButton> radioButtons;
    private Context mContext;
    private int selected;

    public RecyclerViewEsc(Context context, ArrayList<CEscuela> escuelas) {
       this.escuelas = escuelas;
        mContext = context;
        radioButtons = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.escuela_list,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.nombre_esc.setText(escuelas.get(position).getNombre());
        holder.cod_esc.setText(escuelas.get(position).get_id());
        holder.image_esc.setText(escuelas.get(position).getPhoto());
        radioButtons.add(holder.radioButton);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(position);
            }
        });

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!radioButtons.get(position).isSelected()) {
                    setSelected(position);
                }
            }
        });


    }

    public void setSelected(int position){
        selected = position;
        RegisterEscuelaActivity.escuela_selected = escuelas.get(selected).getNombre();
        if(radioButtons.size()!=0){
            int i = 0;
            for(RadioButton aux : radioButtons){
                if(i==position){
                        aux.setChecked(true);


                }else{
                    aux.setChecked(false);
                }
                i++;
            }

        }

    }
    @Override
    public int getItemCount() {
        return escuelas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView image_esc;
        TextView nombre_esc;
        TextView cod_esc;
        RadioButton  radioButton;
        CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);
            image_esc = itemView.findViewById(R.id.image_esc);
            nombre_esc = itemView.findViewById(R.id.nombre_esc);
            cod_esc = itemView.findViewById(R.id.cod_esc);
            cardView = itemView.findViewById(R.id.card_esc);
            radioButton = itemView.findViewById(R.id.radioButton);

        }
    }
}