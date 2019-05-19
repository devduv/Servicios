package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseAccount;
import duvanfisi.fisiunmsm.R;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText pass;
    private EditText newpass;
    private Button buttoncomprobar;
    private Button buttonaceptar;
    private TextView text_new_p;
    private TextView text_pass_c;
    private TextInputLayout t;

    private String email;
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        inicializarViews();
        onClickButtons();
    }


    public void inicializarViews(){

        email = MainActivity.usuario.getEmail();
        pass = findViewById(R.id.password_comp);
        newpass = findViewById(R.id.password_new);
        buttoncomprobar = findViewById(R.id.buttoncomprobar);
        buttonaceptar = findViewById(R.id.buttonchangepass);
        text_new_p = findViewById(R.id.text_new_pass);
        text_pass_c = findViewById(R.id.text_contra_confir);
        t = findViewById(R.id.input_confir_pass);

        back = findViewById(R.id.btnback);
        ImagePicasso.setImageCenterCop(ChangePasswordActivity.this, R.drawable.ic_back, back);
        t.setVisibility(ViewVisible.INVISIBLE);
        buttonaceptar.setVisibility(ViewVisible.INVISIBLE);
        newpass.setVisibility(ViewVisible.INVISIBLE);
        text_new_p.setVisibility(ViewVisible.INVISIBLE);
        text_pass_c.setVisibility(ViewVisible.INVISIBLE);

    }
    public void onClickButtons(){
        buttoncomprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarPassword();
            }
        });

        buttonaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarPassword();
            }
        });

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void comprobarPassword(){
        String pass_c = pass.getText().toString();

        if(pass_c.length()==0){
            pass.setError("No ha ingresado nada.");
            return;
        }

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, pass_c)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            buttonaceptar.setVisibility(ViewVisible.VISIBLE);
                            newpass.setVisibility(ViewVisible.VISIBLE);
                            text_new_p.setVisibility(ViewVisible.VISIBLE);
                            t.setVisibility(ViewVisible.VISIBLE);
                            pass.setEnabled(false);
                            buttoncomprobar.setEnabled(false);
                        }else{
                            pass.setError("Contraseña incorrecta.");
                        }
                    }
                });
    }

    public void cambiarPassword(){
        String pass_n = newpass.getText().toString();
        if(pass_n.length()==0){
            newpass.setError("No ha ingresado nada.");
            return;
        }
        if(pass_n.length()<6){
            newpass.setError("Muy corto.");
            return;
        }
        if(pass_n.equalsIgnoreCase("12345") || pass_n.equalsIgnoreCase("123456")){
            newpass.setError("Contraseña muy fácil.");
            return;
        }
        FirebaseAccount firebaseAccount = new FirebaseAccount(this);
        firebaseAccount.changePasswod(pass_n);

    }
}
