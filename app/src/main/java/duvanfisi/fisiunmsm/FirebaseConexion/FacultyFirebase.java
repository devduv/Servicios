package duvanfisi.fisiunmsm.FirebaseConexion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Model.CFaculty;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewFac;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewFunction;

public class FacultyFirebase {

    private Context context;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private HashMap<Integer, CFaculty> faculties;


    public FacultyFirebase(Context context){
        this.context = context;

        this.firebaseDatabase = FirebaseDatabase.getInstance();
        this.databaseReference = firebaseDatabase.getReference();
    }

    private DatabaseReference getFacultadReference  (){
        return this.databaseReference
                .child(Utilidades.BD)
                .child(Utilidades.FACULTADES);
    }

    public void setCollectionFaculties(final RecyclerView recyclerView){

        databaseReference = getFacultadReference();
        faculties = new HashMap<Integer, CFaculty>();

        databaseReference.addListenerForSingleValueEvent
                (new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for(DataSnapshot aux : dataSnapshot.getChildren()) {
                    faculties.put(i, aux.getValue(CFaculty.class));
                    i++;
                }

                RecyclerViewFac adapter = new RecyclerViewFac(context, faculties);
                RecyclerViewFunction.recyclerview(recyclerView, context, RecyclerViewFunction.VERTICAL, adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //not found
            }
        });
    }
}
