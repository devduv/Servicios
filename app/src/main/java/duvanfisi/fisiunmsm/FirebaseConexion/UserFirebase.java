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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Activities.MainActivity;
import duvanfisi.fisiunmsm.Model.Users.CStudent;
import duvanfisi.fisiunmsm.Templates.TemplateLoading;
import duvanfisi.fisiunmsm.Model.CTicket;
import duvanfisi.fisiunmsm.Model.CUsuario;
import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Templates.TemplateMessage;

public class UserFirebase {

    private Context context;
    private CStudent user;

    private FirebaseDatabase firebaseDatabase;
    private  UserProfileChangeRequest profileUpdates;

    private TemplateLoading loading;
    private AlertDialog dialog_loading;

    public UserFirebase(FirebaseDatabase firebaseDatabase){

        this.firebaseDatabase = firebaseDatabase;
        this.context = firebaseDatabase.getContext();
        this.loading = new TemplateLoading(context);


    }

    public void setUser(CStudent user){
        this.user = user;
    }

    public CStudent getUser(){
        return this.user;
    }

    public DocumentReference getDocumentUsuario(String id){
        return this.firebaseDatabase.getDocument(Utilidades.DOCUMENT_USERS, id);
    }

    public void registrarTicket(String email, CTicket ticket){
       /* DocumentReference documentReference =
                getRegistroUserTicket(email)
                .document(Integer.toString(MainActivity.usuario.getT_retirados()));

        documentReference.set(ticket);*/

    }

    public CollectionReference getRegistroUserTicket(String email){
        return getDocumentUsuario(email).collection(Utilidades.REGISTRO);
    }

    public void register_user(final CStudent user, final FirebaseUser firebaseUser){
        final FirebaseAccount firebaseAccount = new FirebaseAccount(context);
        loading.setTextLoading(Utilidades.REGISTRING);
        dialog_loading = loading.loading();
        dialog_loading.show();

        final DocumentReference documentReference = getDocumentUsuario(user.getEmail());

        documentReference.set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(user.getLastname_p()+ " " +
                                user.getLastname_m() + " " + user.getNames())

                        .build();
                firebaseUser.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                            dialog_loading.dismiss();
                                            firebaseAccount.verify_professional_school
                                                    (firebaseUser, user);
                                }else{
                                    dialog_loading.dismiss();
                                    TemplateMessage message = new TemplateMessage(context);
                                    message.setMensaje(Utilidades.REGISTER_DATES, Utilidades.ERROR);
                                }
                            }
                        });
            }});

    }


    public void setUsuarios(final RecyclerView recyclerView, final HashMap<Integer, CUsuario> cUsuarioHashMap,
                            int start, int end){
        CollectionReference collectionReference = this.firebaseDatabase.getCollection(Utilidades.DOCUMENT_USERS);


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

    public void setNickname(String nickname, String photo_link, String color, FirebaseUser firebaseUser) {
        DocumentReference documentReference = getDocumentUsuario(firebaseUser.getEmail());
        documentReference.update(Utilidades.NICKNAME, nickname);
        documentReference.update(Utilidades.COLOR, color);
        documentReference.update(Utilidades.PHOTO, photo_link);
    }

    public void setPhone(String phone, final FirebaseUser firebaseUser) {
        DocumentReference documentReference = getDocumentUsuario(firebaseUser.getEmail());
        documentReference.update(Utilidades.PHONENUMBER, phone);
    }

    public void setProfessionalSchool(String faculty, String professional_school,
                                      FirebaseUser firebaseUser) {
        DocumentReference documentReference = getDocumentUsuario(firebaseUser.getEmail());

        documentReference.update(Utilidades.PROFESSIONAL_SCHOOL, professional_school);
        documentReference.update(Utilidades.FACULTY, faculty);
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
