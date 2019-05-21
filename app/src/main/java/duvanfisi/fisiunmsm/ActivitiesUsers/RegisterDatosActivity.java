package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import java.util.Random;

import duvanfisi.fisiunmsm.Extras.CloseKeyboard;
import duvanfisi.fisiunmsm.ViewLayouts.PlantillaCodigo;
import duvanfisi.fisiunmsm.ViewLayouts.PlantillaMensaje;
import duvanfisi.fisiunmsm.Modelo.CUsuario;
import duvanfisi.fisiunmsm.Extras.DisplayMetric;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Extras.ViewFloat;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.FirebaseConexion.UsuarioFirebase;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Actions.Utilidades;

public class RegisterDatosActivity extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private static final int INVISIBLE = View.GONE;
    private static final int VISIBLE = View.VISIBLE;
    private final Context context_this = RegisterDatosActivity.this;

    private String cod_in_tec;

    private TextView titulo_codigo;

    private TextView r_cod;

    private AlertDialog dialog_cod;

    private EditText ap_pat;private EditText ap_mat;
    private EditText nomb;

    private ImageButton btnCodigo_alumno;
    private Button button_registrar_datos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_datos);

        this.inicializar();
        this.onClickButtons();
    }

    public void onClickButtons(){
        this.btnCodigo_alumno.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                PlantillaCodigo codigo = new PlantillaCodigo(context_this, cod_in_tec, r_cod);
                codigo.begin();
            }
        });

        this.button_registrar_datos.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v){
                registrarDatosCompletos();
            }
        });

        this.ap_mat.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    CloseKeyboard.closeKeyboardStart(context_this, ap_mat);
                    PlantillaCodigo codigo = new PlantillaCodigo(context_this, cod_in_tec, r_cod);
                    codigo.begin();
                    return true;
                }
                return false;
            }
        });
    }



public void mensaje(){
    PlantillaMensaje mensaje = new PlantillaMensaje(this);
    mensaje.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    mensaje.setMensaje(Utilidades.WELCOME, firebaseUser.getEmail() + Utilidades.PROCED);

}
    @SuppressLint("ClickableViewAccessibility")
    public void inicializar(){


        Intent intent_user = getIntent();
        this.firebaseUser = (FirebaseUser) Objects.requireNonNull(intent_user.getExtras()).get(Utilidades.FIREBASEUSER);
        if(firebaseUser.getDisplayName()==null){
            this.mensaje();
        }
        this.ap_pat = findViewById(R.id.ap_pat);
        this.ap_mat = findViewById(R.id.ap_mat);
        this.nomb = findViewById(R.id.names);

        this.titulo_codigo = findViewById(R.id.text_codigo);
        this.r_cod = findViewById(R.id.r_codigo);
        this.btnCodigo_alumno = findViewById(R.id.btnCod);
        this.button_registrar_datos = findViewById(R.id.button_registrar_datos);

        this.ap_pat.setFocusableInTouchMode(false);
        this.ap_mat.setFocusableInTouchMode(false);
        this.nomb.setFocusableInTouchMode(false);

        this.nomb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                nomb.setFocusableInTouchMode(true);
                ap_pat.setFocusableInTouchMode(true);
                ap_mat.setFocusableInTouchMode(true);
                return false;
            }
        });

        this.ap_pat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                nomb.setFocusableInTouchMode(true);
                ap_pat.setFocusableInTouchMode(true);
                ap_mat.setFocusableInTouchMode(true);
                return false;
            }
        });

        this.ap_mat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                nomb.setFocusableInTouchMode(true);
                ap_pat.setFocusableInTouchMode(true);
                ap_mat.setFocusableInTouchMode(true);
                return false;
            }
        });

        ImageView back = findViewById(R.id.btnback);
        ImagePicasso.setImageCenterCop(this, R.drawable.ic_back, back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImagePicasso.setImageCenterCop(RegisterDatosActivity.this, R.drawable.ic_carnet, btnCodigo_alumno);


        final View activityRootView = findViewById(R.id.id_constraint_register_datos);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                    int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                    if(heightDiff >  DisplayMetric.dpToPx(RegisterDatosActivity.this, 200)){
                        button_registrar_datos.setVisibility(INVISIBLE);
                        titulo_codigo.setVisibility(INVISIBLE);
                        r_cod.setVisibility(INVISIBLE);
                        btnCodigo_alumno.setVisibility(INVISIBLE);
                    }else{
                        button_registrar_datos.setVisibility(VISIBLE);
                        titulo_codigo.setVisibility(VISIBLE);
                        r_cod.setVisibility(VISIBLE);
                        btnCodigo_alumno.setVisibility(VISIBLE);
                    }
            }
        });


    }



    public void registrarDatosCompletos() {

        String ap_pat = this.ap_pat.getText().toString().trim();
        String ap_mat = this.ap_mat.getText().toString().trim();
        String nombres = this.nomb.getText().toString().trim();
        String code = this.r_cod.getText().toString();

        if (TextUtils.isEmpty(nombres)) {
            this.nomb.setError("Ingrese sus nombres");
            return;
        }

        if (TextUtils.isEmpty(ap_pat)) {
            this.ap_pat.setError("Ingrese su apellido");
            return;
        }
        if (TextUtils.isEmpty(ap_pat)) {
            ap_mat = "-";
        }

        if (TextUtils.isEmpty(code)) {
            this.r_cod.setError("Es importante su código de alumno.");
            return;
        }

        if (code.length() > 8 || code.length() < 8) {
            this.r_cod.setError("Ingresa tu código de alumno" + nombres);
            return;
        }
        if (Integer.parseInt(code.substring(0,2))>19) {
            this.r_cod.setError("Fuera de rango.");
            return;
        }

        if(!codfac()){
            this.r_cod.setError("No existe");
            return;
        }
        CUsuario usuario = new CUsuario(firebaseUser.getEmail(), Integer.parseInt(code), ap_pat, ap_mat, nombres);
        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
        UsuarioFirebase usuarioFirebase = new UsuarioFirebase(firebaseDatabase);
        usuarioFirebase.registrarDatosUser(usuario, firebaseUser);

    }

    public boolean codfac(){
        int cod_fac = Integer.parseInt(r_cod.getText().toString().substring(2,4));
        return cod_fac <= 20 && cod_fac != 0;
    }

}


