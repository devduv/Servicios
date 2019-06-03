package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

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

        private boolean canExitApp = false;
        private final Context context_this = LoginActivity.this;
        public static ImageView imglogo;

        private Button btn_m_o_p;

        public static boolean animation_ended = false;

        @SuppressLint("StaticFieldLeak")
        public static EditText emailt; @SuppressLint("StaticFieldLeak")
    public static EditText pass;

        @SuppressLint("StaticFieldLeak")
        public static Button buttonLogin;
    @SuppressLint("StaticFieldLeak")
    public static Button buttnRegister;
        @SuppressLint("StaticFieldLeak")
        public static Button buttnForgot;

        @SuppressLint("StaticFieldLeak")
        public static TextInputLayout inputpass; //@SuppressLint("StaticFieldLeak")
    //public static FrameLayout view;
        //@SuppressLint("StaticFieldLeak")
        public static LinearLayout linearlogin;
        @SuppressLint("StaticFieldLeak")
        public static ImageView splash;

        @SuppressLint("StaticFieldLeak")
        public static View activityRootView;

        private boolean pass_on = false;

       // private ViewFlipper viewFlipper;

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
            buttnForgot.setVisibility(v);
            buttnRegister.setVisibility(v); buttonLogin.setVisibility(v);
            emailt.setVisibility(v); pass.setVisibility(v); inputpass.setVisibility(v);
           // view.setVisibility(v);
            linearlogin.setVisibility(v);
            imglogo.setVisibility(v);
            btn_m_o_p.setVisibility(v);
        }

       @Override
        public void onBackPressed() {
          /* if (!canExitApp) {
               canExitApp = true;
               Toast.makeText(this, getString(R.string.close_app), Toast.LENGTH_SHORT).show();

               new Handler().postDelayed(new Runnable() {

                   @Override
                   public void run() {
                       canExitApp = false;
                   }
               }, 2000);
           } else {*/
               //finish();
               super.onBackPressed();
           //}
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



        }

        /*public void flipper(int image){

            ImageView imageView = new ImageView(this);
            ImagePicasso.setImageCenterCop(context_this, image, imageView);

            viewFlipper.addView(imageView);
            viewFlipper.setFlipInterval(5000);
            viewFlipper.setAutoStart(true);
            viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
            viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        }*/
        /*public void setImages(){
            int[] images = {R.drawable.portada, R.drawable.portada2};
            for (int image : images) {
                flipper(image);
            }

        }*/
        @SuppressLint("ClickableViewAccessibility")
        public void inicializarViews(){
            //viewFlipper = findViewById(R.id.viewflipper);
            activityRootView = findViewById(R.id.id_constraint_login);
            emailt = findViewById(R.id.email);
            pass=  findViewById(R.id.password);
            inputpass = findViewById(R.id.inputpassw);
            buttonLogin = findViewById(R.id.email_sign_in_button);
            buttnRegister = findViewById(R.id.email_register_in_button);
            buttnForgot = findViewById(R.id.email_forgot_in_button);
            splash = findViewById(R.id.splash);
            //view = findViewById(R.id.view);
            imglogo = findViewById(R.id.imglogo);
            linearlogin = findViewById(R.id.linearlogin);
            btn_m_o_p = findViewById(R.id.btn_m_o_p);

            ImageView back = findViewById(R.id.btnback);
            ImagePicasso.setImageCenterCop(LoginActivity.this, R.drawable.ic_back, back);


            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
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

            pass.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(pass.length()>0){
                        eyepassword();
                        btn_m_o_p.setVisibility(ViewVisible.VISIBLE);

                    }else{
                        btn_m_o_p.setVisibility(ViewVisible.INVISIBLE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
           // setImages();
        }

        void eyepassword(){
            btn_m_o_p.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!pass_on) {
                        inputpass.setPasswordVisibilityToggleDrawable(null);
                        inputpass.passwordVisibilityToggleRequested(true);
                        btn_m_o_p.setText("OCULTAR");
                        pass_on = true;
                    }else {
                        btn_m_o_p.setText("MOSTRAR");
                        inputpass.passwordVisibilityToggleRequested(false);
                        pass_on = false;
                    }

                }




            });
        }
        private void attemptLogin() {
            String email = emailt.getText().toString().trim();
            String password = pass.getText().toString();

            TemplateMessage mensaje = new TemplateMessage(context_this);

            if(email.length()==0){
                mensaje.setMensaje("Iniciar sesión", "Ingrese un correo electrónico.");
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