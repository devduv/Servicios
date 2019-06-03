package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Templates.TemplateMessage;
import duvanfisi.fisiunmsm.Extras.CloseKeyboard;
import duvanfisi.fisiunmsm.Extras.DisplayMetric;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseAccount;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;

import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Extras.ViewVisible;


public class RegisterActivity extends AppCompatActivity {

    private EditText emailt;
    private EditText pass;
    private EditText pass_confirmar;
    public static CheckBox checkBox;

    private TextView idterminos;
    private final Context context_this = RegisterActivity.this;

    private Button button_signin;


    private ImageView ic_registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.inicializarViews();
        this.onClickButtons();
        CloseKeyboard.closeKeyboardStart(this, emailt);

    }

    public void inicializarViews(){


        emailt = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        pass_confirmar = findViewById(R.id.password_confir);
        checkBox = findViewById(R.id.checkBox);
        idterminos = findViewById(R.id.idterminos);

        emailt.setHint(getResources().getString(R.string.prompt_email));
        pass.setHint(getResources().getString(R.string.password));
        pass_confirmar.setHint(getResources().getString(R.string.password));

        button_signin = findViewById(R.id.button_signin);

        ic_registro = findViewById(R.id.image_registro);
        ImagePicasso.setImageCenterCop(RegisterActivity.this, R.drawable.ic_registro, ic_registro);
        ImageView back = findViewById(R.id.btnback);
        ImagePicasso.setImageCenterCop(RegisterActivity.this, R.drawable.ic_back, back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final View activityRootView = findViewById(R.id.id_constraint_register);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                    int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                    if(heightDiff >  DisplayMetric.dpToPx(context_this, 200)){
                       // button_signin.setVisibility(ViewVisible.INVISIBLE);
                        ic_registro.setVisibility(ViewVisible.INVISIBLE);
                    }else{
                        emailt.setFocusable(false);
                        pass.setFocusable(false);
                        pass_confirmar.setFocusable(false);
                       // button_signin.setVisibility(ViewVisible.VISIBLE);
                        ic_registro.setVisibility(ViewVisible.VISIBLE);
                    }
            }
        });

       emailt.setFocusableInTouchMode(false);
       pass.setFocusableInTouchMode(false);
       pass_confirmar.setFocusableInTouchMode(false);

       focusable(emailt);
       focusable(pass);
       focusable(pass_confirmar);

    }

    public void focusable(View view){
        view.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                emailt.setFocusableInTouchMode(true);
                pass.setFocusableInTouchMode(true);
                pass_confirmar.setFocusableInTouchMode(true);
                return false;
            }
        });
    }
    public void onClickButtons(){
        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    registrarUser();
            }
        });

        idterminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity.startActivity(RegisterActivity.this, new PoliticasActivity());
            }
        });

        pass_confirmar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if(!checkBox.isChecked()){
                    if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                        CloseKeyboard.closeKeyboardStart(context_this, pass);
                        TemplateMessage templateMessage = new TemplateMessage(RegisterActivity.this);
                        templateMessage.setMensaje("Registro de usuario", Utilidades.ACEPTEDTERM,5);
                        return true;
                    }
                }

                return false;
            }
        });
    }
    public void registrarUser() {
        String email = emailt.getText().toString();
        String password = pass.getText().toString();
        String passwordconf = pass_confirmar.getText().toString();
        TemplateMessage mensaje = new TemplateMessage(this);
        if(email.length()==0){
            mensaje.setMensaje("Registrar usuario", "Ingrese un correo electrónico.");
            return;
        }
        if(password.length()==0 || passwordconf.length()==0){
            mensaje.setMensaje("Registrar usuario", "Escriba una contraseña.");
            return;
        }
        if(!email.endsWith("@unmsm.edu.pe") && !email.endsWith("@gmail.com")){
            mensaje.setMensaje("Registrar usuario", "Use direcciones de correo disponibles.");
            return;
        }
        if(password.length()<5){
            mensaje.setMensaje("Registrar usuario", "Contraseña debe tener más caracteres.");
            return;
        }
        if(!password.equalsIgnoreCase(passwordconf)){
            mensaje.setMensaje("Registrar usuario", "Contraseñas no son iguales");
            return;
        }
        if(checkBox.isChecked()){
            FirebaseAccount firebaseDB = new FirebaseAccount(this);
            firebaseDB.registerUser(email,password);
        }else{
            mensaje.setMensaje("Registro de usuario", Utilidades.ACEPTEDTERM,5);
        }



    }


}

