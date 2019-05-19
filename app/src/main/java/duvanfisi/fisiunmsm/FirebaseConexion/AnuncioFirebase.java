package duvanfisi.fisiunmsm.FirebaseConexion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Modelo.CAnuncio;
import duvanfisi.fisiunmsm.Modelo.CServicio;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewFunction;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewServices;

public class AnuncioFirebase {
    private Context context;


    private FirebaseDatabase firebaseDatabase;


    public AnuncioFirebase(FirebaseDatabase firebaseDatabase){

        this.firebaseDatabase = firebaseDatabase;
        this.context = firebaseDatabase.getContext();
    }

    public DocumentReference getDocumentAnuncio(){
        return this.firebaseDatabase.getCollection(Utilidades.ADS).document(Utilidades.ADS);
    }


    public void setAnuncio(final ImageView imageView){
        getDocumentAnuncio().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                CAnuncio anuncio = documentSnapshot.toObject(CAnuncio.class);
                ImagePicasso.setImageCenterInsideWrap(context, anuncio.getLink(), imageView);
            }
        });

    }
}
