package duvanfisi.fisiunmsm.FirebaseConexion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

import duvanfisi.fisiunmsm.ActivitiesUsers.MainActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.MapsActivity;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.ViewLayouts.PlantillaLoading;
import duvanfisi.fisiunmsm.Modelo.CTicket;
import duvanfisi.fisiunmsm.Modelo.CUsuario;
import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewFunction;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewUsuarios;

public class UsuarioFirebase {

    private Context context;
    private CUsuario usuario;

    private FirebaseDatabase firebaseDatabase;
    private  UserProfileChangeRequest profileUpdates;

    private PlantillaLoading loading;
    private AlertDialog dialog_loading;

    public UsuarioFirebase(FirebaseDatabase firebaseDatabase){

        this.firebaseDatabase = firebaseDatabase;
        this.context = firebaseDatabase.getContext();
        this.loading = new PlantillaLoading(context);


    }

    public void setUsuario(CUsuario usuario){
        this.usuario = usuario;
    }

    public CUsuario getUsuario(){
        return this.usuario;
    }

    public DocumentReference getDocumentUsuario(String id){
        return this.firebaseDatabase.getDocument(Utilidades.USERS, id);
    }

    public void registrarTicket(String email, CTicket ticket){
        DocumentReference documentReference =
                getRegistroUserTicket(email)
                .document(Integer.toString(MainActivity.usuario.getT_retirados()));

        documentReference.set(ticket);

    }

    public CollectionReference getRegistroUserTicket(String email){
        return getDocumentUsuario(email).collection(Utilidades.REGISTRO);
    }

    public void registrarDatosUser(final CUsuario usuario, final FirebaseUser firebaseUser){
        final FirebaseAccount firebaseAccount = new FirebaseAccount(context);
        loading.setTextLoading(Utilidades.REGISTRING);
        dialog_loading = loading.loading();
        dialog_loading.show();

        final DocumentReference documentReference = getDocumentUsuario(usuario.getEmail());

        documentReference.set(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(usuario.getAp_paterno() + " " + usuario.getAp_materno() + " " + usuario.getNombre())
                        .build();

                firebaseUser.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            CUsuario usuariodocument = documentSnapshot.toObject(CUsuario.class);
                                            dialog_loading.dismiss();
                                            firebaseAccount.verificarEscuela(usuariodocument, firebaseUser);
                                        }
                                    });
                                }
                            }
                        });
            }
        });

    }


    public void setUsuarios(final RecyclerView recyclerView, final HashMap<Integer, CUsuario> cUsuarioHashMap,
                            int start, int end){
        CollectionReference collectionReference = this.firebaseDatabase.getCollection(Utilidades.USERS);


        collectionReference.orderBy("nombre").limit(10)
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
                                    CUsuario usuario = change.toObject(CUsuario.class);
                                    cUsuarioHashMap.put(i, usuario);
                                    i++;
                                }
                            }

                           /* MapsActivity.carga.setVisibility(ViewVisible.INVISIBLE);
                            //RecyclerPersonaje . . .
                            RecyclerViewUsuarios recyclerViewUsuario
                                    = new RecyclerViewUsuarios
                                    (context, cUsuarioHashMap);
                            //recyclerViewUsuario.notifyDataSetChanged();
                            RecyclerViewFunction.recyclerview
                                    (recyclerView, context, RecyclerViewFunction.VERTICAL,recyclerViewUsuario);*/

                        }
                    }
                });
    }

    public void setNickname(String nickname, String photo_link, String selected, FirebaseUser firebaseUser) {
        DocumentReference documentReference = getDocumentUsuario(firebaseUser.getEmail());
        documentReference.update(Utilidades.NICKNAME, nickname);
        documentReference.update(Utilidades.COLOR, selected);
        documentReference.update(Utilidades.PHOTO, photo_link);
    }

    public void setPhone(String phone, final FirebaseUser firebaseUser) {
        DocumentReference documentReference = getDocumentUsuario(firebaseUser.getEmail());
        documentReference.update(Utilidades.PHONE, phone);
    }

    public void setEscuela(String facultad_selected, String escuela_selected, FirebaseUser firebaseUser) {
        DocumentReference documentReference = getDocumentUsuario(firebaseUser.getEmail());
        documentReference.update(Utilidades.ESCUELA, escuela_selected);
        documentReference.update(Utilidades.FACULTAD, facultad_selected);
    }

    public void setTicketRetirado(String email, int cantidad){
        DocumentReference documentReference = getDocumentUsuario(email);
        documentReference.update(Utilidades.T_RETIRADOS,cantidad);
    }
    public void setUltimaConexion(String email, String ultconexion){
        DocumentReference documentReference = getDocumentUsuario(email);
        documentReference.update(Utilidades.ULT_CONEXION, ultconexion);
    }
}
