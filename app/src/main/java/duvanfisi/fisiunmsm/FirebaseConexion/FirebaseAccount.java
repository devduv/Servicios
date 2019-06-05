package duvanfisi.fisiunmsm.FirebaseConexion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import duvanfisi.fisiunmsm.Actions.Preferences;
import duvanfisi.fisiunmsm.Activities.LoginActivity;
import duvanfisi.fisiunmsm.Model.Users.CStudent;
import duvanfisi.fisiunmsm.Templates.TemplateLoading;
import duvanfisi.fisiunmsm.Templates.TemplateMessage;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Activities.MainActivity;
import duvanfisi.fisiunmsm.Activities.NicknameActivity;
import duvanfisi.fisiunmsm.Activities.PhoneActivity;
import duvanfisi.fisiunmsm.Activities.DatesRegisterActivity;
import duvanfisi.fisiunmsm.Activities.ProfessionalSchoolActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;

public class FirebaseAccount {

    private Context context;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private AlertDialog dialog_loading;
    private TemplateLoading loading;
    private TemplateMessage mensaje;

    public FirebaseAccount(Context context) {
        this.context = context;
        this.loading = new TemplateLoading(context);
        this.mAuth = FirebaseAuth.getInstance();
        this.mensaje = new TemplateMessage(context);
        this.dialog_loading = loading.loading();
    }

    public void changePasswod(String newPassword){
        setTextLoading("Cambiando...");
        dialog_loading.show();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        firebaseUser.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            dialog_loading.dismiss();
                            TemplateMessage mensaje  = new TemplateMessage(context);
                            mensaje.setMensaje("Cambiar contraseña", "Se ha cambiado su contraseña satisfactoriamente",1);
                        }
                    }
                });
    }

    public void setTextLoading(String text){
        loading.setTextLoading(text);

    }
    public void login(final String email, final String password){
        setTextLoading(Utilidades.SIGN_UP);
        dialog_loading.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            firebaseUser = mAuth.getCurrentUser();
                            if (!verifyEmail()) {
                                dialog_loading.dismiss();
                                setMensaje(
                                        context.getString(R.string.titulo_iniciarsesion),
                                        context.getString(R.string.no_verify_email),
                                        firebaseUser);
                            }else {
                                signup();
                            }
                        }else{
                            dialog_loading.dismiss();
                            setMensaje(
                                    context.getString(R.string.titulo_iniciarsesion),
                                    context.getString(R.string.user_pass_inc));
                        }
                    }
                });
    }
    private boolean verifyEmail(){
        if(firebaseUser !=null){
            if(!firebaseUser.isEmailVerified()) {
                mAuth.signOut();
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
    public static void loginToken(String iud, final Context context){
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCustomToken(iud)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isComplete()) {
                    FirebaseUser firebaseUser =  firebaseAuth.getCurrentUser();
                    if (firebaseUser != null) {
                        signup(firebaseUser, context);
                    }else{
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    }
                }else{
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }

            }
        });
    }
    private static void signup(final FirebaseUser firebaseUser, final Context context){
        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(context);
        final UserFirebase userFirebase = new UserFirebase(firebaseDatabase);
        DocumentReference documentReference = userFirebase.getDocumentUsuario(firebaseUser.getEmail());

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                userFirebase.setUser(documentSnapshot.toObject(CStudent.class));
                CStudent user = userFirebase.getUser();
                if(user!=null){
                    gotoMain(context,firebaseUser, user);
                }else{
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            }
        });
    }
    private void signup(){

        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(context);
        final UserFirebase userFirebase = new UserFirebase(firebaseDatabase);
        DocumentReference documentReference = userFirebase.getDocumentUsuario(firebaseUser.getEmail());

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
           @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
               userFirebase.setUser(documentSnapshot.toObject(CStudent.class));
                CStudent user = userFirebase.getUser();
                if(user!=null){
                    dialog_loading.dismiss();
                    verify_professional_school(firebaseUser, user);
                }else{
                    dialog_loading.dismiss();
                    goto_dates_register(firebaseUser);
                }
            }
        });
    }

    public void verify_professional_school(FirebaseUser firebaseUser, CStudent user){
        if(user.getProfessional_school().equalsIgnoreCase(Utilidades.NO_DATES)){
            gotoSelectProfessionalSchool(firebaseUser, user);
        }else{
            verify_phonenumber(user, firebaseUser);
        }
    }

    public void forgotPassword(String email){
        TemplateLoading templateLoading = new TemplateLoading(context);
        templateLoading.setTextLoading("Verificando email...");
        final AlertDialog alertDialog = templateLoading.loading();
        alertDialog.show();

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            alertDialog.dismiss();
                            TemplateMessage mensaje = new TemplateMessage(context);
                            mensaje.setMensaje("Olvidé mi contraseña", "Se ha enviado un link a su email.");

                        }else{
                            alertDialog.dismiss();
                            TemplateMessage mensaje = new TemplateMessage(context);
                            mensaje.setMensaje("Olvidé mi contraseña", "El email no ha sido registrado.");
                        }
                    }
                });

    }

    public void deleteCuenta(final FirebaseUser firebaseUser){
        this.setTextLoading("Vuelve pronto...");
        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(context);
        firebaseDatabase.deleteDocument(Utilidades.DOCUMENT_USERS, firebaseUser.getEmail());
        dialog_loading.show();
        mAuth.signInWithCustomToken(firebaseUser.getUid())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            dialog_loading.dismiss();
                            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                       Preferences.cerrarSesion(context);

                                        StartActivity.startActivity(context, new LoginActivity());
                                        ((Activity) context).finish();
                                    }else{
                                        TemplateMessage mensaje = new TemplateMessage(context);
                                        mensaje.setMensaje("Eliminar", "No se pudo completar");
                                    }
                                }
                            });
                        }

                    }
                });

    }
    public void verify_phonenumber(CStudent user, FirebaseUser firebaseUser){
        if(user.getPhonenumber().equalsIgnoreCase(Utilidades.NO_DATES)){
           gotoPhoneNumber(firebaseUser, user);
        }else{
            verify_nick(user);
        }
    }

    public void verify_nick(CStudent user){
        if(user.getNick().equalsIgnoreCase(Utilidades.NO_DATES)) {
            if(dialog_loading!=null){
                dialog_loading.dismiss();
            }
            gotoNick(firebaseUser);
        }else{
            if(dialog_loading!=null){
                dialog_loading.dismiss();
            }
            gotoMain(context, firebaseUser, user);
        }
    }

    public void goto_dates_register(FirebaseUser firebaseUser){
        StartActivity.startActivity(context, new DatesRegisterActivity(), firebaseUser);
    }
    public void gotoSelectProfessionalSchool(FirebaseUser firebaseUser, CStudent user){
        if(dialog_loading!=null){
            dialog_loading.dismiss();
        }
        StartActivity.startActivity(context, new ProfessionalSchoolActivity(), firebaseUser, user);
    }
    public void gotoPhoneNumber(FirebaseUser firebaseUser, CStudent student){
        StartActivity.startActivity(context, new PhoneActivity(), firebaseUser, student);
    }
    public void gotoNick(FirebaseUser firebaseUser){
        StartActivity.startActivity(context, new NicknameActivity(), firebaseUser);
    }

    public static void gotoMain(Context context, FirebaseUser firebaseUser, CStudent user){
        Preferences.guardarPreferencias(context, firebaseUser);
        if(Preferences.getFirstTime(context)){
            Preferences.fisrtTime(context, true);
        }
        StartActivity.startActivity(context, new MainActivity(), firebaseUser, user);
        ((Activity) context).finish();
    }
    public void registerUser(String email, String password){
        setTextLoading(Utilidades.SIGN_IN);
        dialog_loading.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendEmailVerify();
                        } else {
                            userRepited();
                        }
                    }
                });
    }

    private void sendEmailVerify(){
        firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification();
            dialog_loading.dismiss();
            int CLOSED_ACTIVITY = 1;
            setMensaje(
                    context.getString(R.string.ver_email),
                    context.getString(R.string.verificacion_email),
                    CLOSED_ACTIVITY);


        }
    }

    private void userRepited(){
        dialog_loading.dismiss();
        setMensaje(
                context.getString(R.string.men_registrar_usuario),
                context.getString(R.string.men_user_rep));
    }

    private void setMensaje(String title, String desc){
        mensaje.setMensaje(
                title, desc);
    }

    private void setMensaje(String title, String desc, int action){
        mensaje.setMensaje(
                title, desc, action);
    }

    private void setMensaje(String title, String desc, FirebaseUser firebaseUser){
        mensaje.setMensaje(
                title, desc, firebaseUser);
    }
}
