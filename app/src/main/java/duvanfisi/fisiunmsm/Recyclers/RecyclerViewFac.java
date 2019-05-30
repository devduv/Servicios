package duvanfisi.fisiunmsm.Recyclers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import duvanfisi.fisiunmsm.ActivitiesUsers.FacultyActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.RegisterEscuelaActivity;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Model.CFaculty;
import duvanfisi.fisiunmsm.R;


public class RecyclerViewFac extends RecyclerView.Adapter<RecyclerViewFac.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private HashMap<Integer, CFaculty> faculties;
    private ArrayList<RadioButton> radioButtons;
    private Context mContext;
    private int selected;

    public RecyclerViewFac(Context context, HashMap<Integer, CFaculty> faculties) {
       this.faculties = faculties;
        mContext = context;
        radioButtons = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_faculty,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.nombre_esc.setText(faculties.get(position).getNombre());
        holder.cod_esc.setText(Integer.toString(faculties.get(position).get_id()));
        ImagePicasso.setImageCenterInside(mContext,faculties.get(position).getPhoto(), holder.image_esc);
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
       FacultyActivity.faculty_selected = faculties.get(selected).getNombre();
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
        return faculties.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image_esc;
        TextView nombre_esc;
        TextView cod_esc;
        RadioButton  radioButton;
        CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);
            image_esc = itemView.findViewById(R.id.image_fac);
            nombre_esc = itemView.findViewById(R.id.nombre_fac);
            cod_esc = itemView.findViewById(R.id.cod_fac);
            cardView = itemView.findViewById(R.id.card_fac);
            radioButton = itemView.findViewById(R.id.radioButton);

        }
    }
}