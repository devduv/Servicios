package duvanfisi.fisiunmsm.FirebaseConexion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.Model.CNoticia;
import duvanfisi.fisiunmsm.Model.CPublicacion;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewFunction;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewPublicacion;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewTopNoticias;

public class PublicacionFirebase {
    private Context context;


    private FirebaseDatabase firebaseDatabase;

    public PublicacionFirebase(FirebaseDatabase firebaseDatabase){

        this.firebaseDatabase = firebaseDatabase;
        this.context = firebaseDatabase.getContext();
    }

    public CollectionReference getDocumentNoticia(){
        return this.firebaseDatabase.getCollection("publications");
    }


    public void setNoticiasHome(final RecyclerView recyclerView,
                                final HashMap<Integer, CPublicacion> cPublicacionHashMap,
                                final RelativeLayout relativeLayout, final View pantallacarga){
        CollectionReference collectionReference = getDocumentNoticia();

        collectionReference
                .orderBy("_id", Query.Direction.DESCENDING)
        //        .limit(4)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot querySnapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                if (querySnapshot != null) {
                    int i = 0;
                    for (QueryDocumentSnapshot change : querySnapshot) {
                        if(change!=null && change.exists()) {
                            cPublicacionHashMap.put(i, change.toObject(CPublicacion.class));
                            i++;
                        }
                    }
                    home_news(cPublicacionHashMap, recyclerView, relativeLayout, pantallacarga);

                }
            }
        });

    }

    public void setPublicacionesOnHome(final RecyclerView publicaciones,
                                       final HashMap<Integer, CPublicacion> publicacionHashMap){
        CollectionReference collectionReference = getDocumentNoticia();
        collectionReference.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            int i = 0;
                            for(QueryDocumentSnapshot document : task.getResult()){
                                publicacionHashMap.put(i, document.toObject(CPublicacion.class));
                                i++;
                            }
                            init_pub(publicacionHashMap, publicaciones);
                        }
                    }
                });
    }

    public void setNoticiasNews(final RecyclerView recyclerView,
                                final HashMap<Integer, CNoticia> cNoticiaHashMap){
        CollectionReference collectionReference = getDocumentNoticia();

        collectionReference
                .orderBy("id", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot querySnapshot,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            return;
                        }

                        if (querySnapshot != null) {
                            int i = 0;
                            for (QueryDocumentSnapshot change : querySnapshot) {
                                if(change!=null && change.exists()) {
                                    cNoticiaHashMap.put(i, change.toObject(CNoticia.class));
                                    i++;
                                }
                            }
                            news_news(cNoticiaHashMap, recyclerView);

                        }
                    }
                });

    }

    private void home_news(HashMap<Integer, CPublicacion> cPublicacionHashMap,
                           RecyclerView recyclerView, RelativeLayout relativeLayout,
                           View pantallacarga){

        RecyclerViewPublicacion recyclerViewNoticias
                = new RecyclerViewPublicacion
                (context, cPublicacionHashMap);
        RecyclerViewFunction.recyclerview
                (recyclerView, context, RecyclerViewFunction.VERTICAL,recyclerViewNoticias);

        relativeLayout.setVisibility(ViewVisible.VISIBLE);
        pantallacarga.setVisibility(ViewVisible.INVISIBLE);
    }

    private void init_pub(HashMap<Integer, CPublicacion> cPublicacionHashMap,
                           RecyclerView recyclerView){

        RecyclerViewPublicacion recyclerViewNoticias
                = new RecyclerViewPublicacion
                (context, cPublicacionHashMap);
        RecyclerViewFunction.recyclerview
                (recyclerView, context, RecyclerViewFunction.VERTICAL,recyclerViewNoticias);
    }

    private void news_news(HashMap<Integer, CNoticia> cNoticiaHashMap,
                           RecyclerView recyclerView){

        RecyclerViewTopNoticias recyclerViewNoticias
                = new RecyclerViewTopNoticias
                (context, cNoticiaHashMap);
        RecyclerViewFunction.recyclerview
                (recyclerView, context, RecyclerViewFunction.VERTICAL,recyclerViewNoticias);

        //pantallacarga.setVisibility(ViewVisible.INVISIBLE);
    }


}
