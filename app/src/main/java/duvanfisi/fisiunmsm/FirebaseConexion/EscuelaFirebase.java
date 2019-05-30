package duvanfisi.fisiunmsm.FirebaseConexion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.Fragments.CArea;
import duvanfisi.fisiunmsm.Model.CEscuela;
import duvanfisi.fisiunmsm.Model.CFaculty;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewAreas;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewEsc;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewFunction;
import duvanfisi.fisiunmsm.ActivitiesUsers.RegisterEscuelaActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;

public class EscuelaFirebase {

    private Context context;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private CFaculty facultad;
    private ArrayList<CEscuela> escuelas;


    public EscuelaFirebase(Context context){
        this.context = context;

        this.firebaseDatabase = FirebaseDatabase.getInstance();
        this.databaseReference = firebaseDatabase.getReference();
    }

    private DatabaseReference getFacultadReference  (){
        return this.databaseReference
                .child(Utilidades.BD)
                .child(Utilidades.FACULTADES);
    }

    private DatabaseReference getAreasference  (){
        return this.databaseReference
                .child(Utilidades.BD)
                .child(Utilidades.AREAS);
    }

    public void setCollectionAreas(final RecyclerView recyclerView, final HashMap<Integer, CArea> areaHashMap){

        databaseReference = getAreasference();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot aux : dataSnapshot.getChildren()){
                    areaHashMap.put(i, aux.getValue(CArea.class));
                    i++;
                }
                RecyclerViewAreas recyclerViewAreas = new RecyclerViewAreas(context, areaHashMap);
                RecyclerViewFunction.recyclerview(recyclerView, context,
                        RecyclerViewFunction.HORIZONTAL,recyclerViewAreas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setCollectionFacultades(String cod, final TextView title_fac, final ImageView imageView,
                                        final RecyclerView recyclerView, final Button btnreg, final Button btnfound){

        databaseReference = getFacultadReference()
                .child(cod);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                facultad = dataSnapshot.getValue(CFaculty.class);

                title_fac.setText(facultad.getNombre());
                RegisterEscuelaActivity.facultad_selected = facultad.getNombre();
                ImagePicasso.setImageCenterInside(context, facultad.getPhoto(), imageView);

               databaseReference.child(Utilidades.ESCUELAS).addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       escuelas = new ArrayList<>();
                       for(DataSnapshot aux : dataSnapshot.getChildren()){
                           escuelas.add(aux.getValue(CEscuela.class));
                       }
                       RecyclerViewEsc adapter = new RecyclerViewEsc(context, escuelas);
                       RecyclerViewFunction.recyclerview(recyclerView, context, RecyclerViewFunction.VERTICAL, adapter);

                       btnreg.setVisibility(ViewVisible.VISIBLE);
                       btnfound.setVisibility(ViewVisible.VISIBLE);

                       RegisterEscuelaActivity.dialog_loading.dismiss();
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //not found
            }
        });
    }
}
