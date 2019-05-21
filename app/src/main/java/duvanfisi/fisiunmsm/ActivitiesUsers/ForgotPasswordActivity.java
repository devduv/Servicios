package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseAccount;
import duvanfisi.fisiunmsm.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button buttonForgot;
    private EditText email;
    private String email_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        this.inicializarViews();
        this.onClickButtons();

    }

    public void onClickButtons(){
        this.buttonForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarEmail();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public void inicializarViews(){
        this.buttonForgot = findViewById(R.id.email_forgot_button);
        this.email = findViewById(R.id.email_f);

        email.setFocusableInTouchMode(false);
        email.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                email.setFocusableInTouchMode(true);

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

    }
    public void verificarEmail(){
        this.email_t = email.getText().toString();

        if (TextUtils.isEmpty(email_t)) {
            email.setError(getString(R.string.error_field_required));
            email.requestFocus();
            return;
        }
        if (!isEmailValid(email_t)) {
            email.setError(getString(R.string.error_invalid_email));
            email.requestFocus();
            return;
        }

        sendEmailChangePass();
    }

    public void sendEmailChangePass(){
        FirebaseAccount firebaseAccount = new FirebaseAccount(this);
        firebaseAccount.forgotPassword(email_t);
    }


    private boolean isEmailValid(String email) {
        return email.contains("@");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
