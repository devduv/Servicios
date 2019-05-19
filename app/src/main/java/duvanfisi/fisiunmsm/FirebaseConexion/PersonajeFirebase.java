package duvanfisi.fisiunmsm.FirebaseConexion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.ActivitiesUsers.MainActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.PersonajeActivity;
import duvanfisi.fisiunmsm.Modelo.CPersonaje;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewFunction;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewPersonaje;

public class PersonajeFirebase {
    private Context context;


    private FirebaseDatabase firebaseDatabase;

    public PersonajeFirebase(FirebaseDatabase firebaseDatabase){

        this.firebaseDatabase = firebaseDatabase;
        this.context = firebaseDatabase.getContext();

    }

    public CollectionReference getDocumentPersonaje(){
        return this.firebaseDatabase.getCollection(Utilidades.PERSONAJES);
    }



    public void setPersonajes(final RecyclerView recyclerView, final HashMap<Integer, CPersonaje> cPersonajeHashMap){
        CollectionReference collectionReference = getDocumentPersonaje();

        collectionReference.orderBy("loves", Query.Direction.DESCENDING)
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
                            CPersonaje cPersonaje = change.toObject(CPersonaje.class);
                            cPersonajeHashMap.put(i, cPersonaje);
                            i++;
                        }
                    }


                    //RecyclerPersonaje . . .
                    RecyclerViewPersonaje recyclerViewPersonaje
                            = new RecyclerViewPersonaje
                            (context, cPersonajeHashMap, R.layout.plantilla_personaje);
                    RecyclerViewFunction.recyclerview
                            (recyclerView, context, RecyclerViewFunction.HORIZONTAL,recyclerViewPersonaje);

                }
            }
        });

    }
    public void setActionPersonaje(String name, final int action){
        final DocumentReference sfDocRef = getDocumentPersonaje().document(name);

        firebaseDatabase.getFirebaseFirestore().runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);
                long actionpersonaje;
                switch (action){
                    case 0:
                         actionpersonaje= snapshot.getLong("favs") + 1;
                        transaction.update(sfDocRef, "favs", actionpersonaje);
                        PersonajeActivity.favs.setText(Long.toString(actionpersonaje));
                        break;
                    case 1:
                        actionpersonaje = snapshot.getLong("loves") + 1;
                        transaction.update(sfDocRef, "loves", actionpersonaje);
                        PersonajeActivity.loves.setText(Long.toString(actionpersonaje));
                        break;
                    case 2:
                        actionpersonaje = snapshot.getLong("likes") + 1;
                        transaction.update(sfDocRef, "likes", actionpersonaje);
                        PersonajeActivity.likes.setText(Long.toString(actionpersonaje));
                        break;
                    case 3:
                        actionpersonaje = snapshot.getLong("dislikes") + 1;
                        transaction.update(sfDocRef, "dislikes", actionpersonaje);
                        PersonajeActivity.dislikes.setText(Long.toString(actionpersonaje));
                        break;
                }


                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }
}
