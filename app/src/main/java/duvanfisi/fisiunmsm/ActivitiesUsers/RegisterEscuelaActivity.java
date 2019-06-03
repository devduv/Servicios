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

import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.Templates.TemplateLoading;
import duvanfisi.fisiunmsm.Templates.TemplateMessage;
import duvanfisi.fisiunmsm.Model.CUsuario;

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
    private Button btn_found_fac;
    public static String facultad_selected;
    public static String escuela_selected;

    private TemplateLoading loading;

    public static AlertDialog dialog_loading;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_reg);

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
        this.usuario = (CUsuario) intent_user.getExtras().get(Utilidades.KEY_MODEL_USER);

        this.loading = new TemplateLoading(this);
        setLoading();
        this.recyclerView = findViewById(R.id.recyclerViewEscuelas);

        this.title_facultad = findViewById(R.id.title_facultad);
        this.img_esc = findViewById(R.id.img_esc);
        this.btn_reg_esc = findViewById(R.id.btn_reg_esc);
        this.btn_found_fac = findViewById(R.id.btn_found_fac);
        escuela_selected = "";

        this.btn_reg_esc.setVisibility(ViewVisible.INVISIBLE);
        this.btn_found_fac.setVisibility(ViewVisible.INVISIBLE);

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

        this.btn_found_fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TemplateMessage templateMessage = new TemplateMessage(RegisterEscuelaActivity.this);
                templateMessage.setMensaje("Registrar Escuela", "Está seguro que la facultad que se le mostró no es una a la que pertenece?", 6);
            }
        });

    }


    public void registrarEscuela(){

        TemplateMessage mensaje = new TemplateMessage(this);
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
        escuelaFirebase.setCollectionFacultades(cod, title_facultad, img_esc,recyclerView, btn_reg_esc, btn_found_fac);
    }

    public void getCodeUser(){
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
}
