package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Objects;

import duvanfisi.fisiunmsm.Model.CUsuario;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.FirebaseConexion.UsuarioFirebase;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;

public class PhoneActivity extends AppCompatActivity {

    private EditText phonenumber;
    private Button phone;
    private Button omitir;
    private FirebaseUser firebaseUser;
    private CUsuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        inicializarViews();
        onClickButtons();
    }

    public void inicializarViews(){
        Intent intent_user = getIntent();
        this.firebaseUser = (FirebaseUser) Objects.requireNonNull(intent_user.getExtras()).get(Utilidades.FIREBASEUSER);

        this.phonenumber = findViewById(R.id.phonenumber);
        this.phone = findViewById(R.id.buttonphone);
        this.omitir = findViewById(R.id.buttonomitir);
        ImageView img_phone = findViewById(R.id.img_phone);
        ImageView back = findViewById(R.id.btnback);
        ImagePicasso.setImageCenterCop(this, R.drawable.ic_back, back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImagePicasso.setImageCenterCop(PhoneActivity.this, R.drawable.ic_phone, img_phone);
        setUsuario();
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
                registrarPhone();
            }
        });
        ;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void registrarPhone(){
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
        registrarPhone(phonenumber);


    }

    public void registrarPhone(String phone){
        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
        UsuarioFirebase usuarioFirebase = new UsuarioFirebase(firebaseDatabase);
        usuarioFirebase.setPhone(phone, firebaseUser);
        continuar();
    }

    public void setUsuario(){
        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
        UsuarioFirebase usuarioFirebase = new UsuarioFirebase(firebaseDatabase);
        DocumentReference documentReference = usuarioFirebase.getDocumentUsuario(firebaseUser.getEmail());
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                usuario = documentSnapshot.toObject(CUsuario.class);
            }
        });
    }

    public void continuar(){
        if(usuario.getNickname().equalsIgnoreCase(Utilidades.NO_DATES)) {
            StartActivity.startActivity(this, new NicknameActivity(), firebaseUser);
        }else{
            if(MainActivity.firebaseUser!=null){
                onBackPressed();
            }else{
                StartActivity.startActivity(this, new MainActivity(), firebaseUser);
                finish();
            }

        }
    }
}
