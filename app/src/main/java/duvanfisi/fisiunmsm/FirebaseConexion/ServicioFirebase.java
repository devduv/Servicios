package duvanfisi.fisiunmsm.FirebaseConexion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Modelo.CServicio;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewFunction;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewServices;

public class ServicioFirebase {
    private Context context;


    private FirebaseDatabase firebaseDatabase;


    private RecyclerView recyclerViewNoticia;

   // private PlantillaLoading loading;
   // private AlertDialog dialog_loading;

    public ServicioFirebase(FirebaseDatabase firebaseDatabase){

        this.firebaseDatabase = firebaseDatabase;
        this.context = firebaseDatabase.getContext();

       // this.loading = new PlantillaLoading(context);
    }

    public CollectionReference getDocumentNoticia(){
        return this.firebaseDatabase.getCollection(Utilidades.SERVICIOS);
    }


    public void setServicios(final RecyclerView recyclerView, final HashMap<Integer, CServicio> cServicioHashMap){
        CollectionReference collectionReference = getDocumentNoticia();

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot querySnapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                if (querySnapshot != null) {
                    int i = 0;
                    for (QueryDocumentSnapshot change : querySnapshot) {
                        String source = change != null &&change.getMetadata().hasPendingWrites()
                                ? "Local" : "Server";
                        if(change!=null && change.exists()) {
                            cServicioHashMap.put(i, change.toObject(CServicio.class));
                            i++;
                        }
                    }
                    RecyclerViewServices recyclerViewServices
                            = new RecyclerViewServices
                            (context, cServicioHashMap);
                    RecyclerViewFunction.recyclerview
                            (recyclerView, context, RecyclerViewFunction.VERTICAL,recyclerViewServices);

                }
            }
        });

    }
}
