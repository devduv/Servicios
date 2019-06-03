package duvanfisi.fisiunmsm.Recyclers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

import duvanfisi.fisiunmsm.ActivitiesUsers.FacultyActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.RegisterEscuelaActivity;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Model.CFaculty;
import duvanfisi.fisiunmsm.R;


public class RecyclerViewFac extends RecyclerView.Adapter<RecyclerViewFac.ViewHolder>{

    private HashMap<Integer, CFaculty> faculties;
    private ArrayList<RadioButton> radioButtons;
    private Context mContext;

    public RecyclerViewFac(Context context, HashMap<Integer, CFaculty> faculties) {
       this.faculties = faculties;
        mContext = context;
        radioButtons = new ArrayList<>();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_faculty,parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        String name_fac = faculties.get(position).getNombre();
        String fac = Integer.toString(faculties.get(position).get_id());

        holder.nombre_esc.setText(name_fac);
        holder.cod_esc.setText(fac);

        ImagePicasso.setImageCenterInside(mContext,faculties.get(position).getPhoto(), holder.image_esc);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


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