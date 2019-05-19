package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import duvanfisi.fisiunmsm.Animation.AnimationLogin;
import duvanfisi.fisiunmsm.ViewLayouts.PlantillaLoading;
import duvanfisi.fisiunmsm.ViewLayouts.PlantillaMensaje;
import duvanfisi.fisiunmsm.Extras.CloseKeyboard;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseAccount;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Extras.ViewVisible;


public class LoginActivity extends AppCompatActivity {

        private boolean canExitApp = false;
        private final Context context_this = LoginActivity.this;

        public static boolean animation_ended = false;

        public static EditText emailt; public static EditText pass;

        public static ImageView logo;

        public static Button buttonLogin;public static Button buttnRegister;
        public static Button buttnForgot;public static Button buttnInvited;


        public static TextView id_app; public static TextInputLayout inputpass;
        public static TextView text_titulo; public static ImageView splash;

        public static View activityRootView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            this.inicializarViews();
            this.visibility(ViewVisible.INVISIBLE);

            AnimationLogin animationLogin = new AnimationLogin(context_this);
            animationLogin.splash();
            this.onClickButtons();

            FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
            firebaseDatabase.settingsPersistence();
        }


        public void visibility(int v){
            buttnInvited.setVisibility(v); buttnForgot.setVisibility(v);
            buttnRegister.setVisibility(v); buttonLogin.setVisibility(v);
            emailt.setVisibility(v); pass.setVisibility(v); inputpass.setVisibility(v);
            logo.setVisibility(v); text_titulo.setVisibility(v);
            id_app.setVisibility(v);
        }

       @Override
        public void onBackPressed() {
           if (!canExitApp) {
               canExitApp = true;
               Toast.makeText(this, getString(R.string.close_app), Toast.LENGTH_SHORT).show();

               new Handler().postDelayed(new Runnable() {

                   @Override
                   public void run() {
                       canExitApp = false;
                   }
               }, 2000);
           } else {
               super.onBackPressed();
           }
        }

        public void onClickButtons(){
            pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                    if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                        CloseKeyboard.closeKeyboardStart(context_this, pass);
                        attemptLogin();
                        return true;
                    }
                    return false;
                }
            });

            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CloseKeyboard.closeKeyboardStart(context_this, pass);
                    attemptLogin();


                }
            });

            buttnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CloseKeyboard.closeKeyboardStart(context_this, pass);
                    StartActivity.startActivity(context_this, new RegisterActivity());
                }
            });

            buttnForgot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StartActivity.startActivity(context_this, new ForgotPasswordActivity());
                }
            });

            buttnInvited.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PlantillaLoading loading = new PlantillaLoading(LoginActivity.this);
                    loading.setTextLoading("Bienvenido...");
                    final AlertDialog dialog_l = loading.loading();
                    dialog_l.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog_l.dismiss();
                            StartActivity.startActivity(context_this, new MainActivity(), null);
                        }
                    }, 1500);
                }
            });
        }

        @SuppressLint("ClickableViewAccessibility")
        public void inicializarViews(){

            activityRootView = findViewById(R.id.id_constraint_login);
            emailt = findViewById(R.id.email);
            pass=  findViewById(R.id.password);
            inputpass = findViewById(R.id.inputpassw);
            text_titulo = findViewById(R.id.text_r_titulo);
            id_app = findViewById(R.id.id_app);
            buttonLogin = findViewById(R.id.email_sign_in_button);
            buttnRegister = findViewById(R.id.email_register_in_button);
            buttnForgot = findViewById(R.id.email_forgot_in_button);
            buttnInvited = findViewById(R.id.email_invited_in_button);
            splash = findViewById(R.id.splash);

            logo = findViewById(R.id.icon_login_sm);
            ImagePicasso.setImageCenterCop(context_this, R.drawable.icon_logo_sm, logo);

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

        private void attemptLogin() {
            String email = emailt.getText().toString().trim();
            String password = pass.getText().toString();

            PlantillaMensaje mensaje = new PlantillaMensaje(context_this);

            if(email.length()==0){
                mensaje.setMensaje("Iniciar sesión", "Ingrese un correo electrónico,");
                return;
            }
            if(password.length()==0){
                mensaje.setMensaje("Iniciar sesión", "Escriba su contraseña.");
                return;
            }
            if(!email.endsWith("@unmsm.edu.pe") && !email.endsWith("@gmail.com")){
                mensaje.setMensaje("Iniciar sesión", "Ingrese un correo electrónico.");
                return;
            }

            FirebaseAccount firebaseAccount = new FirebaseAccount(this);
            firebaseAccount.login(email,password);

        }

}