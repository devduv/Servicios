package duvanfisi.fisiunmsm.Activities;

import android.annotation.SuppressLint;
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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import duvanfisi.fisiunmsm.Extras.CloseKeyboard;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.Model.Users.CStudent;
import duvanfisi.fisiunmsm.Model.Users.CTeacher;
import duvanfisi.fisiunmsm.Model.Users.CUser;
import duvanfisi.fisiunmsm.Templates.TemplateCode;
import duvanfisi.fisiunmsm.Templates.TemplateMessage;
import duvanfisi.fisiunmsm.Extras.DisplayMetric;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.FirebaseConexion.UserFirebase;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Actions.Utilidades;

public class DatesRegisterActivity extends AppCompatActivity {

    private String USER_TYPE = Utilidades.TYPE_STUDENT;
    private Intent intent_user;
    private FirebaseUser firebaseUser;
    private final Context context_this = DatesRegisterActivity.this;
    private TextView titulo_codigo;
    private TextView r_cod;
    private EditText ap_pat;private EditText ap_mat;
    private EditText nomb;
    private ImageButton btnCodigo_alumno;
    private Button button_registrar_datos;
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_dates);

        this.initViews();
        this.touchListener();
        this.displayView();
        this.setImage();
        this.getFirebaseuser();
        this.message_welcome();
        this.onClickButtons();
    }

    public void initViews(){
        this.intent_user = getIntent();
        this.ap_pat = findViewById(R.id.ap_pat);
        this.ap_mat = findViewById(R.id.ap_mat);
        this.nomb = findViewById(R.id.names);
        this.titulo_codigo = findViewById(R.id.text_codigo);
        this.r_cod = findViewById(R.id.r_codigo);
        this.btnCodigo_alumno = findViewById(R.id.btnCod);
        this.button_registrar_datos = findViewById(R.id.button_registrar_datos);
        this.back = findViewById(R.id.btnback);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void touchListener(){
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
    }
    public void onClickButtons(){
        this.btnCodigo_alumno.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                TemplateCode codigo = new TemplateCode(context_this, r_cod);
                codigo.begin();
            }
        });

        this.button_registrar_datos.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v){
                register_document_user();
            }
        });

        this.ap_mat.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if(r_cod.getText().toString().length()==0){
                    if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                        CloseKeyboard.closeKeyboardStart(context_this, ap_mat);
                        TemplateCode codigo = new TemplateCode(context_this, r_cod);
                        codigo.begin();
                        return true;
                    }
                }

                return false;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void displayView(){
        final View activityRootView = findViewById(R.id.id_constraint_register_datos);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                if(heightDiff >  DisplayMetric.dpToPx(DatesRegisterActivity.this, 200)){
                    button_registrar_datos.setVisibility(ViewVisible.INVISIBLE);
                    titulo_codigo.setVisibility(ViewVisible.INVISIBLE);
                    r_cod.setVisibility(ViewVisible.INVISIBLE);
                    btnCodigo_alumno.setVisibility(ViewVisible.INVISIBLE);
                }else{
                    button_registrar_datos.setVisibility(ViewVisible.VISIBLE);
                    titulo_codigo.setVisibility(ViewVisible.VISIBLE);
                    r_cod.setVisibility(ViewVisible.VISIBLE);
                    btnCodigo_alumno.setVisibility(ViewVisible.VISIBLE);
                }
            }
        });
    }
    public void setImage(){
        ImagePicasso.setImageCenterCop(this, R.drawable.ic_back, back);
        ImagePicasso.setImageCenterCop(DatesRegisterActivity.this, R.drawable.ic_carnet, btnCodigo_alumno);
    }
    public void getFirebaseuser(){
        this.firebaseUser = (FirebaseUser) Objects.requireNonNull(intent_user.getExtras()).get(Utilidades.FIREBASEUSER);
    }

    public void message_welcome(){
        if(firebaseUser.getDisplayName()==null) {
            TemplateMessage mensaje = new TemplateMessage(this);
            mensaje.setMensaje(Utilidades.WELCOME, firebaseUser.getEmail() + Utilidades.PROCED);
        }
    }


    public void register_document_user() {
        USER_TYPE = Utilidades.TYPE_STUDENT;
        String lastname_p = this.ap_pat.getText().toString().trim().toUpperCase();
        String lastname_m = this.ap_mat.getText().toString().trim().toUpperCase();
        String names = this.nomb.getText().toString().trim().toUpperCase();
        String code = this.r_cod.getText().toString();

        if (TextUtils.isEmpty(names)) {
            this.nomb.setError("Ingrese sus nombres");
            return;
        }

        if (TextUtils.isEmpty(lastname_p)) {
            this.ap_pat.setError("Ingrese su apellido");
            return;
        }
        if (TextUtils.isEmpty(lastname_p)) {
            lastname_m = "-";
        }

        if (TextUtils.isEmpty(code)) {
            this.r_cod.setError("Es importante su código de alumno.");
            return;
        }

        if (code.length() > 8 || code.length() < 8) {
            this.r_cod.setError("Ingresa tu código de alumno" + names);
            return;
        }

        create_document_user(code, firebaseUser.getEmail(), names, lastname_p, lastname_m);
    }

    public void create_document_user(String code, String email, String names,
                                     String lastname_p, String lastname_m){

        if(USER_TYPE.equalsIgnoreCase(Utilidades.TYPE_STUDENT)){
            CStudent user = new CStudent(code, email, names, lastname_p,
                                        lastname_m, Utilidades.TYPE_STUDENT);

            FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
            UserFirebase userFirebase = new UserFirebase(firebaseDatabase);
            userFirebase.register_user(user, firebaseUser);
        }else{
            CUser user = new CTeacher(code, email, names, lastname_p,
                                        lastname_m, Utilidades.TYPE_TEACHER, "");
        }
    }

}


