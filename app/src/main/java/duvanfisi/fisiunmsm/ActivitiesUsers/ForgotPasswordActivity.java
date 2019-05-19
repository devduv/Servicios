package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    public void inicializarViews(){
        this.buttonForgot = findViewById(R.id.email_forgot_button);
        this.email = findViewById(R.id.email_f);
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
