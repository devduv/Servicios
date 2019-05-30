package duvanfisi.fisiunmsm.FirebaseConexion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.Model.CNoticia;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewFunction;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewNoticias;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewTopNoticias;

public class NoticiaFirebase {
    private Context context;


    private FirebaseDatabase firebaseDatabase;

    public NoticiaFirebase(FirebaseDatabase firebaseDatabase){

        this.firebaseDatabase = firebaseDatabase;
        this.context = firebaseDatabase.getContext();
    }

    public CollectionReference getDocumentNoticia(){
        return this.firebaseDatabase.getCollection(Utilidades.NOTICIAS);
    }


    public void setNoticiasHome(final RecyclerView recyclerView,
                                final HashMap<Integer, CNoticia> cNoticiaHashMap,
                                final RelativeLayout relativeLayout, final View pantallacarga){
        CollectionReference collectionReference = getDocumentNoticia();

        collectionReference
                .orderBy("id", Query.Direction.DESCENDING)
                .limit(4)
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
                    home_news(cNoticiaHashMap, recyclerView, relativeLayout, pantallacarga);

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

    private void home_news(HashMap<Integer, CNoticia> cNoticiaHashMap,
                           RecyclerView recyclerView, RelativeLayout relativeLayout,
                           View pantallacarga){

        RecyclerViewNoticias recyclerViewNoticias
                = new RecyclerViewNoticias
                (context, cNoticiaHashMap);
        RecyclerViewFunction.recyclerview
                (recyclerView, context, RecyclerViewFunction.VERTICAL,recyclerViewNoticias);

        relativeLayout.setVisibility(ViewVisible.VISIBLE);
        pantallacarga.setVisibility(ViewVisible.INVISIBLE);
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
