package duvanfisi.fisiunmsm.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Animation.AnimationLogin;
import duvanfisi.fisiunmsm.Templates.TemplateMessage;
import duvanfisi.fisiunmsm.Extras.CloseKeyboard;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseAccount;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Extras.ViewVisible;


public class LoginActivity extends AppCompatActivity {


    private final Context context = LoginActivity.this;

    private Button BUTTON_SHOW_HIDDEN_PASSWORD;
    private boolean PASS_SHOW = false;
    public static boolean ANIMATION_ENDED = false;


    @SuppressLint("StaticFieldLeak") public static ImageView imglogo;
    @SuppressLint("StaticFieldLeak") public static EditText emailt;
    @SuppressLint("StaticFieldLeak") public static EditText pass;
    @SuppressLint("StaticFieldLeak") public static Button buttonLogin;
    @SuppressLint("StaticFieldLeak") public static Button buttnRegister;
    @SuppressLint("StaticFieldLeak") public static Button buttnForgot;
    @SuppressLint("StaticFieldLeak") public static TextInputLayout inputpass;
    @SuppressLint("StaticFieldLeak") public static LinearLayout linearlogin;
    @SuppressLint("StaticFieldLeak") public static ImageView splash;
    @SuppressLint("StaticFieldLeak") public static View activityRootView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initViews();
        this.touchListener();
        this.visibility(ViewVisible.INVISIBLE);
        this.animation();
        this.onClickButtons();
        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
        firebaseDatabase.settingsPersistence();
        }
    public void initViews(){
        activityRootView = findViewById(R.id.id_constraint_login);
        emailt = findViewById(R.id.email);
        pass =  findViewById(R.id.password);
        inputpass = findViewById(R.id.inputpassw);
        buttonLogin = findViewById(R.id.email_sign_in_button);
        buttnRegister = findViewById(R.id.email_register_in_button);
        buttnForgot = findViewById(R.id.email_forgot_in_button);
        splash = findViewById(R.id.splash);
        imglogo = findViewById(R.id.imglogo);
        linearlogin = findViewById(R.id.linearlogin);
        BUTTON_SHOW_HIDDEN_PASSWORD = findViewById(R.id.btn_m_o_p);


        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(pass.length()>0){
                    eyepassword();
                    BUTTON_SHOW_HIDDEN_PASSWORD.setVisibility(ViewVisible.VISIBLE);

                }else{
                    BUTTON_SHOW_HIDDEN_PASSWORD.setVisibility(ViewVisible.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @SuppressLint("ClickableViewAccessibility") public void touchListener(){
        emailt.setFocusableInTouchMode(false);

        emailt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                emailt.setFocusableInTouchMode(true);
                pass.setFocusableInTouchMode(true);
                return false;
            }
        });
        pass.setFocusableInTouchMode(false);
        pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pass.setFocusableInTouchMode(true);
                emailt.setFocusableInTouchMode(true);
                return false;
            }
        });
    }
    public void visibility(int v){
        buttnForgot.setVisibility(v); buttnRegister.setVisibility(v); buttonLogin.setVisibility(v);
        emailt.setVisibility(v); pass.setVisibility(v);
        inputpass.setVisibility(v); linearlogin.setVisibility(v); imglogo.setVisibility(v);
        BUTTON_SHOW_HIDDEN_PASSWORD.setVisibility(v);
    }
    public void animation(){
        AnimationLogin animationLogin = new AnimationLogin(context);
        animationLogin.splash();
    }
    @Override public void onBackPressed() {
        super.onBackPressed();
    }
    public void onClickButtons(){
        pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                    if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                        CloseKeyboard.closeKeyboardStart(context, pass);
                        attemptLogin();
                        return true;
                    }
                    return false;
                }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CloseKeyboard.closeKeyboardStart(context, pass);
                    attemptLogin();

                }
        });

        buttnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CloseKeyboard.closeKeyboardStart(context, pass);
                    StartActivity.startActivity(context, new SigninActivity());
                }
        });

        buttnForgot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StartActivity.startActivity(context, new ForgotPasswordActivity());
                }
        });


    }
    public void eyepassword(){
            BUTTON_SHOW_HIDDEN_PASSWORD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!PASS_SHOW) {
                        inputpass.setPasswordVisibilityToggleDrawable(null);
                        inputpass.passwordVisibilityToggleRequested(true);
                        BUTTON_SHOW_HIDDEN_PASSWORD.setText("OCULTAR");
                        PASS_SHOW = true;
                    }else {
                        BUTTON_SHOW_HIDDEN_PASSWORD.setText("MOSTRAR");
                        inputpass.passwordVisibilityToggleRequested(false);
                        PASS_SHOW = false;
                    }

                }




            });
    }
    private void attemptLogin() {
        String email = emailt.getText().toString().trim();
        String password = pass.getText().toString();

        TemplateMessage mensaje = new TemplateMessage(context);
        if(email.length()==0){
            mensaje.setMensaje(Utilidades.LOGIN, Utilidades.EMAIL_REQUERED);
            return;
        }
        if(password.length()==0){
            mensaje.setMensaje(Utilidades.LOGIN, Utilidades.WRITE_PASS);
            return;
        }
        if(!email.endsWith(Utilidades.EMAIL_DOMIN)){
            mensaje.setMensaje(Utilidades.LOGIN, Utilidades.EMAIL_REQUERED);
            return;
        }

        verify_user(email, password);
    }
    public void verify_user(String email, String password){
        FirebaseAccount firebaseAccount = new FirebaseAccount(this);
        firebaseAccount.login(email,password);
    }


}