package duvanfisi.fisiunmsm.FirebaseConexion;

import android.content.Context;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

import duvanfisi.fisiunmsm.Actions.Utilidades;

public class FirebaseDatabase {

    private Context context;
    private FirebaseFirestore firebaseFirestore;

    public FirebaseDatabase(Context context){
        this.context = context;
        firebaseFirestore =FirebaseFirestore.getInstance();
    }

    public FirebaseFirestore getFirebaseFirestore(){
        return firebaseFirestore;
    }

    public DocumentReference getDocument(String collection, String document){
        return firebaseFirestore.collection(collection).document(document);
    }

    public CollectionReference getCollection(String collection){
        return firebaseFirestore.collection(collection);
    }

    public void deleteDocument(String collection, String document){
        getDocument(collection, document).delete();

    }

    public Context getContext(){
        return this.context;
    }

    public void settingsPersistence(){
        firebaseFirestore
                .setFirestoreSettings(
                        new FirebaseFirestoreSettings.Builder()
                                .setPersistenceEnabled(true)
                                .build());
    }
}
