package duvanfisi.fisiunmsm.Activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import duvanfisi.fisiunmsm.Extras.CloseKeyboard;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseAccount;
import duvanfisi.fisiunmsm.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button buttonForgot;
    private EditText email;
    private String email_t;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        this.initViews();
        this.setImages();
        this.touchlisneter();
        this.onClickButtons();

    }

    public void onClickButtons(){
        this.buttonForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify_email();
            }
        });

        email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    CloseKeyboard.closeKeyboardStart(ForgotPasswordActivity.this, email);
                    verify_email();
                    return true;
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


    public void initViews(){
        this.buttonForgot = findViewById(R.id.email_forgot_button);
        this.email = findViewById(R.id.email_f);
        this.back = findViewById(R.id.btnback);
    }

    public void setImages(){
        ImagePicasso.setImageCenterCop(this, R.drawable.ic_back, back);
    }
    @SuppressLint("ClickableViewAccessibility")
    public void touchlisneter(){
        email.setFocusableInTouchMode(false);
        email.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                email.setFocusableInTouchMode(true);

                return false;
            }
        });
    }

    public void verify_email(){
        this.email_t = email.getText().toString().trim();

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
