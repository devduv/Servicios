package duvanfisi.fisiunmsm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.FirebaseConexion.UserFirebase;
import duvanfisi.fisiunmsm.Model.Users.CStudent;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;

public class PhoneActivity extends AppCompatActivity {

    private Intent intent_user;

    private CStudent user;
    private FirebaseUser firebaseUser;

    private EditText phonenumber;
    private Button phone;
    private Button omitir;
    private ImageView img_phone;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        inicializarViews();
        setImages();
        setFirebaseUser();
        setUser();
        onClickButtons();
    }

    public void inicializarViews(){
        this.intent_user = getIntent();
        this.phonenumber = findViewById(R.id.phonenumber);
        this.phone = findViewById(R.id.buttonphone);
        this.omitir = findViewById(R.id.buttonomitir);
        this.img_phone = findViewById(R.id.img_phone);
        this.back = findViewById(R.id.btnback);

    }

    public void setImages(){
        ImagePicasso.setImageCenterCop(this, R.drawable.ic_back, back);
        ImagePicasso.setImageCenterCop(PhoneActivity.this, R.drawable.ic_phone, img_phone);
    }

    public void setFirebaseUser(){
        firebaseUser = (FirebaseUser)
                Objects.requireNonNull(intent_user.getExtras()).get(Utilidades.FIREBASEUSER);
    }

    public void setUser(){
        user = (CStudent) intent_user.getExtras().get(Utilidades.KEY_MODEL_USER);
    }

    public void onClickButtons(){
        this.omitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuar();
            }
        });
        this.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify_input_phonenumber();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void verify_input_phonenumber(){
        String phone = this.phonenumber.getText().toString();

        int tam = phone.length();
        if(tam>9  ||tam<9){
            this.phonenumber.setError("No es un número de celular.");
            return;
        }
        if(!phone.startsWith("9")){
            this.phonenumber.setError("No es un número de celular.");
            return;
        }
        String phonenumber = "+51 " + phone;
        setPhoneNumberModel(phonenumber);
        register_phonenumber(phonenumber);
    }

    public void register_phonenumber(String phone){
        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
        UserFirebase userFirebase = new UserFirebase(firebaseDatabase);
        userFirebase.setPhone(phone, firebaseUser);
        continuar();
    }

    public void setPhoneNumberModel(String phone){
        user.setPhonenumber(phone);
    }
    public void continuar(){
        if(user.getNick().equalsIgnoreCase(Utilidades.NO_DATES)) {
            StartActivity.startActivity(this, new NicknameActivity(), firebaseUser, user);
        }else{
                StartActivity.startActivity(this, new MainActivity(), firebaseUser, user);
                finish();
        }
    }
}
