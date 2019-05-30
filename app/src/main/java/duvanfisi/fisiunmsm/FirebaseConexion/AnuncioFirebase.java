package duvanfisi.fisiunmsm.FirebaseConexion;

import android.content.Context;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Model.CAnuncio;

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
