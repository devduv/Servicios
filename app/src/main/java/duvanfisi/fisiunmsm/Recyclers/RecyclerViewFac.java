package duvanfisi.fisiunmsm.Recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Activities.ProfessionalSchoolActivity;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Model.CFaculty;
import duvanfisi.fisiunmsm.Model.Users.CStudent;
import duvanfisi.fisiunmsm.R;


public class RecyclerViewFac extends RecyclerView.Adapter<RecyclerViewFac.ViewHolder>{

    private HashMap<Integer, CFaculty> faculties;
    private FirebaseUser firebaseUser;
    private CStudent student;
    private Context mContext;

    public RecyclerViewFac(Context context, HashMap<Integer, CFaculty> faculties,
                           FirebaseUser firebaseUser, CStudent student) {
       this.faculties = faculties;
       this.mContext = context;
       this.firebaseUser = firebaseUser;
       this.student = student;
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
        String fac = faculties.get(position).get_id();
        String sobre_fac = faculties.get(position).getSobrenombre();
        String cantidad = Integer.toString(faculties.get(position).getCantidadesc());
        holder.nombre_esc.setText(name_fac);
        holder.cod_esc.setText(fac);
        holder.cant_esc_fac.setText(cantidad);
        holder.sobre_fac.setText(sobre_fac);

        ImagePicasso.setImageCenterInside(mContext,faculties.get(position).getPhoto(), holder.image_esc);


        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFaculty(position);
            }
        });


    }

    public void setFaculty(int position){
        String faculty_selected = faculties.get(position).get_id();
        student.setFaculty(faculty_selected);
        StartActivity.startActivity(mContext, new ProfessionalSchoolActivity(), firebaseUser, student);
    }

    @Override
    public int getItemCount() {
        return faculties.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image_esc;
        TextView nombre_esc;
        TextView cod_esc;
        TextView sobre_fac;
        TextView cant_esc_fac;
        Button radioButton;


        public ViewHolder(View itemView) {
            super(itemView);
            image_esc = itemView.findViewById(R.id.image_fac);
            nombre_esc = itemView.findViewById(R.id.nombre_fac);
            cod_esc = itemView.findViewById(R.id.cod_fac);
            sobre_fac = itemView.findViewById(R.id.sobre_fac);
            radioButton = itemView.findViewById(R.id.radioButton);
            cant_esc_fac = itemView.findViewById(R.id.cant_esc_fac);

        }
    }
}