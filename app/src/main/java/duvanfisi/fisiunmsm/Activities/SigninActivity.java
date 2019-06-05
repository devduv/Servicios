package duvanfisi.fisiunmsm.Activities;

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


public class SigninActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static CheckBox checkBox;
    private final Context context = SigninActivity.this;
    private final int ACTION = 5;

    private EditText emailt;
    private EditText pass;
    private EditText pass_confirmar;
    private TextView idterminos;
    private Button button_signin;
    private ImageView ic_registro;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.initViews();
        this.touchListener();
        this.setImages();
        this.setHints();
        this.onClickButtons();
        this.displayView();
    }

    public void initViews(){
        emailt = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        pass_confirmar = findViewById(R.id.password_confir);
        checkBox = findViewById(R.id.checkBox);
        idterminos = findViewById(R.id.idterminos);
        button_signin = findViewById(R.id.button_signin);
        back = findViewById(R.id.btnback);
        ic_registro = findViewById(R.id.image_registro);
    }

    public void displayView(){
        final View activityRootView = findViewById(R.id.id_constraint_register);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                if(heightDiff >  DisplayMetric.dpToPx(context, 200)){
                    ic_registro.setVisibility(ViewVisible.INVISIBLE);
                }else{
                    emailt.setFocusable(false);
                    pass.setFocusable(false);
                    pass_confirmar.setFocusable(false);
                    ic_registro.setVisibility(ViewVisible.VISIBLE);
                }
            }
        });
    }

    public void touchListener(){
        emailt.setFocusableInTouchMode(false);
        pass.setFocusableInTouchMode(false);
        pass_confirmar.setFocusableInTouchMode(false);
        focusable(emailt);
        focusable(pass);
        focusable(pass_confirmar);
    }

    public void setImages(){
        ImagePicasso.setImageCenterCop(SigninActivity.this, R.drawable.ic_registro, ic_registro);
        ImagePicasso.setImageCenterCop(SigninActivity.this, R.drawable.ic_back, back);
    }

    public void setHints(){
        emailt.setHint(getResources().getString(R.string.prompt_email));
        pass.setHint(getResources().getString(R.string.password));
        pass_confirmar.setHint(getResources().getString(R.string.password));

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
                StartActivity.startActivity(SigninActivity.this, new PoliticasActivity());
            }
        });

        pass_confirmar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if(!checkBox.isChecked()){
                    if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                        CloseKeyboard.closeKeyboardStart(context, pass);
                        TemplateMessage templateMessage = new TemplateMessage(SigninActivity.this);
                        templateMessage.setMensaje(Utilidades.SIGIN_USER, Utilidades.ACEPTEDTERM,5);
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

    public void registrarUser() {
        String email = emailt.getText().toString();
        String password = pass.getText().toString();
        String passwordconf = pass_confirmar.getText().toString();
        TemplateMessage mensaje = new TemplateMessage(this);
        if(email.length()==0){
            mensaje.setMensaje(Utilidades.SIGIN_USER, Utilidades.EMAIL_REQUERED);
            return;
        }
        if(password.length()==0 || passwordconf.length()==0){
            mensaje.setMensaje(Utilidades.SIGIN_USER, Utilidades.WRITE_NEW_PASS);
            return;
        }
        if(!email.endsWith(Utilidades.EMAIL_DOMIN)){
            mensaje.setMensaje(Utilidades.SIGIN_USER, Utilidades.USE_DOMIN);
            return;
        }
        if(password.length()<5){
            mensaje.setMensaje(Utilidades.SIGIN_USER, Utilidades.MORE_CHARACTERS);
            return;
        }
        if(!password.equalsIgnoreCase(passwordconf)){
            mensaje.setMensaje(Utilidades.SIGIN_USER, Utilidades.PASS_SAME);
            return;
        }
        if(checkBox.isChecked()){
            verifyuser(email, password);
        }else{
            mensaje.setMensaje(Utilidades.SIGIN_USER, Utilidades.ACEPTEDTERM,ACTION);
        }
    }

    public void verifyuser(String email, String password){
        FirebaseAccount firebaseDB = new FirebaseAccount(this);
        firebaseDB.registerUser(email,password);
    }

}

