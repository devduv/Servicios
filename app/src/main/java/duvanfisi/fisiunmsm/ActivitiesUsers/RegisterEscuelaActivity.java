package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import java.util.Objects;

import duvanfisi.fisiunmsm.ViewLayouts.PlantillaLoading;
import duvanfisi.fisiunmsm.ViewLayouts.PlantillaMensaje;
import duvanfisi.fisiunmsm.Modelo.CUsuario;

import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.FirebaseConexion.EscuelaFirebase;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseAccount;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.FirebaseConexion.UsuarioFirebase;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Actions.Utilidades;

public class RegisterEscuelaActivity extends AppCompatActivity {

    private FirebaseUser firebaseUser;

    private TextView title_facultad;

    private ImageView img_esc;

    private CUsuario usuario;

    private Button btn_reg_esc;
    public static String facultad_selected;
    public static String escuela_selected;

    private PlantillaLoading loading;

    public static AlertDialog dialog_loading;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_escuela);

        inicializarViews();
        onClickButton();
    }


    public void inicializarViews(){
        ImageView back = findViewById(R.id.btnback);
        ImagePicasso.setImageCenterCop(this, R.drawable.ic_back, back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent intent_user = getIntent();
        this.firebaseUser = (FirebaseUser) Objects.requireNonNull(intent_user.getExtras()).get(Utilidades.FIREBASEUSER);


        this.loading = new PlantillaLoading(this);
        setLoading();
        this.recyclerView = findViewById(R.id.recyclerViewEscuelas);

        this.title_facultad = findViewById(R.id.title_facultad);
        this.img_esc = findViewById(R.id.img_esc);
        this.btn_reg_esc = findViewById(R.id.btn_reg_esc);

        getCodeUser();

    }

    public void setLoading(){
        loading.setTextLoading("Buscando su facultad...");
        dialog_loading = loading.loading();
        dialog_loading.show();
    }


    public void onClickButton(){
        this.btn_reg_esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarEscuela();
            }
        });

    }


    public void registrarEscuela(){

        PlantillaMensaje mensaje = new PlantillaMensaje(this);
        if(escuela_selected.length()==0){
            mensaje.setMensaje("Escuela Académico Profesional", "No hay seleccionado ninguna escuela");
        }else{
            FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
            UsuarioFirebase usuarioFirebase = new UsuarioFirebase(firebaseDatabase);
            usuarioFirebase.setEscuela(facultad_selected, escuela_selected, firebaseUser);
            FirebaseAccount firebaseAccount = new FirebaseAccount(this);
            firebaseAccount.verificarPhone(usuario, firebaseUser);
            finish();
        }
    }


    public void getFacultades(String cod){
        EscuelaFirebase escuelaFirebase = new EscuelaFirebase(this);
        escuelaFirebase.setCollectionFacultades(cod, title_facultad, img_esc,recyclerView);
    }

    public void getCodeUser(){
        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
        UsuarioFirebase firebase = new UsuarioFirebase(firebaseDatabase);
        DocumentReference documentReference = firebase.getDocumentUsuario(firebaseUser.getEmail());
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    usuario = documentSnapshot.toObject(CUsuario.class);
                    if (usuario != null) {
                        String cod_aux = "";
                        String cod = Integer.toString(usuario.getCodigo());
                        if(cod.length()!=8){
                            if(cod.length()==7){
                                cod_aux = "0" +cod;
                            }
                            if(cod.length()==6){
                                cod_aux = "00"+cod;
                            }
                        }else{
                            cod_aux = cod;
                        }
                        String c = cod_aux.substring(2,4);
                        getFacultades(c);
                    }


                }
        });
    }
}
