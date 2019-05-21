package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseAccount;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.FirebaseConexion.UsuarioFirebase;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Actions.Utilidades;

public class NicknameActivity extends AppCompatActivity {


    private ImageView img_select;
    private ImageButton btn00;private ImageButton btn01;
    private ImageButton btn02;private ImageButton btn03;
    private ImageButton btn04;private ImageButton btn05;
    private ImageButton btn06;private ImageButton btn07;
    private ImageButton btn08;

    private CircularProgressButton btnGuardar;
    private FirebaseUser firebaseUser;

    private EditText nickname_user;
    private ImageView icnickname;

    private String selected;
    private String photo_link;
    private String nickname;

    private String link0;private String link1;
    private String link2;private String link3;
    private String link4;private String link5;
    private String link6;private String link7;
    private String link8;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);

        this.userActivo();
        this.instanciarViews();
        this.buttonOnClick();

        this.cargarFotos();

    }

    public void userActivo() {
        Intent intent = getIntent();
        firebaseUser = (FirebaseUser) Objects.requireNonNull(intent.getExtras()).get(Utilidades.FIREBASEUSER);
    }

    public void instanciarViews(){
        ImageView back = findViewById(R.id.btnback);
        ImagePicasso.setImageCenterCop(this, R.drawable.ic_back, back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        this.nickname_user = findViewById(R.id.nickname_ver_input);
        this.icnickname = findViewById(R.id.icnickname);
        this.img_select = findViewById(R.id.img_selected);
        this.btn00 = findViewById(R.id.btn_num_0);this.btn01 = findViewById(R.id.btn_num_1);
        this.btn02 = findViewById(R.id.btn_num_2);this.btn03 = findViewById(R.id.btn_num_3);
        this.btn04 = findViewById(R.id.btn_num_4);this.btn05 = findViewById(R.id.btn_num_5);
        this.btn06 = findViewById(R.id.btn_num_6);this.btn07 = findViewById(R.id.btn_num_7);
        this.btn08 = findViewById(R.id.btn_num_8);
        this.btnGuardar = findViewById(R.id.btn_g_d);

        this.photo_link = "";

        this.link0 = "https://firebasestorage.googleapis.com/v0/b/unmsm-proyecto-servicios.appspot.com/o/user_profiles%2F001-man-13.png?alt=media&token=1f4bac3b-b3c5-4d59-946d-e00baeb24f4b";
        this.link1 = "https://firebasestorage.googleapis.com/v0/b/unmsm-proyecto-servicios.appspot.com/o/user_profiles%2F002-woman-14.png?alt=media&token=88b94373-5533-4564-ab24-6252e667a261";
        this.link2 = "https://firebasestorage.googleapis.com/v0/b/unmsm-proyecto-servicios.appspot.com/o/user_profiles%2F003-woman-13.png?alt=media&token=3f672a50-904c-41b6-924f-634f59fa5738";
        this.link3 = "https://firebasestorage.googleapis.com/v0/b/unmsm-proyecto-servicios.appspot.com/o/user_profiles%2F016-man-10.png?alt=media&token=2f6ca183-4b88-417b-91c6-e61de4e9b4fe";
        this.link4 = "https://firebasestorage.googleapis.com/v0/b/unmsm-proyecto-servicios.appspot.com/o/user_profiles%2F033-boy.png?alt=media&token=fcb03843-bfc8-4e42-bed2-deb80f59aa6d";
        this.link5 = "https://firebasestorage.googleapis.com/v0/b/unmsm-proyecto-servicios.appspot.com/o/user_profiles%2F035-woman-1.png?alt=media&token=9b98fa22-caba-4320-83f4-c89228cf97d9";
        this.link6 = "https://firebasestorage.googleapis.com/v0/b/unmsm-proyecto-servicios.appspot.com/o/user_profiles%2F017-man-9.png?alt=media&token=a92e3ca4-12fe-4e4c-98e9-769b3dc651ec";
        this.link7 = "https://firebasestorage.googleapis.com/v0/b/unmsm-proyecto-servicios.appspot.com/o/user_profiles%2F031-boy-2.png?alt=media&token=d69599fc-70d6-439e-b021-0e71f4aec157";
        this.link8 = "https://firebasestorage.googleapis.com/v0/b/unmsm-proyecto-servicios.appspot.com/o/user_profiles%2F006-woman-10.png?alt=media&token=14b70824-1bdb-40e2-b1c7-30407480828b";
    }

    public void cargarFotos(){

        ImagePicasso.setImageCenterCop(NicknameActivity.this, R.drawable.ic_nickname, icnickname);
        Picasso.with(NicknameActivity.this)
                .load(link0).fit().centerCrop().into(btn00);
        Picasso.with(NicknameActivity.this)
                .load(link1).fit().centerCrop().into(btn01);
        Picasso.with(NicknameActivity.this)
                .load(link2).fit().centerCrop().into(btn02);
        Picasso.with(NicknameActivity.this)
                .load(link3).fit().centerCrop().into(btn03);
        Picasso.with(NicknameActivity.this)
                .load(link4).fit().centerCrop().into(btn04);
        Picasso.with(NicknameActivity.this)
                .load(link5).fit().centerCrop().into(btn05);
        Picasso.with(NicknameActivity.this)
                .load(link6).fit().centerCrop().into(btn06);
        Picasso.with(NicknameActivity.this)
                .load(link7).fit().centerCrop().into(btn07);
        Picasso.with(NicknameActivity.this)
                .load(link8).fit().centerCrop().into(btn08);
    }

    public void imgSelected(ImageView v, String link){
        v.setVisibility(View.VISIBLE);
        Picasso.with(NicknameActivity.this)
                .load(link).into(v);
    }
    public void buttonOnClick(){
        this.btn00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "#FF0099CC";
                photo_link = link0;
                imgSelected(img_select, link0);
            }
        });

        this.btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "#FF99CC00";
                photo_link = link1;
                imgSelected(img_select, link1);
            }
        });

        this.btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "#FFFF4444";
                photo_link = link2;
                imgSelected(img_select, link2);
            }
        });

        this.btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "#FFFF8800";
                photo_link = link3;
                imgSelected(img_select, link3);
            }
        });

        this.btn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "#FFAA66CC";
                photo_link = link4;
                imgSelected(img_select, link4);
            }
        });

        this.btn05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "#FF00DDFF";
                photo_link = link5;
                imgSelected(img_select, link5);
            }
        });

        this.btn06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "#4f4840";
                photo_link = link6;
                imgSelected(img_select, link6);
            }
        });

        this.btn07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "#FF33B5E5";
                photo_link = link7;
                imgSelected(img_select, link7);
            }
        });

        this.btn08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "#be4a47";
                photo_link = link8;
                imgSelected(img_select, link8);
            }
        });

        this.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNickName();
            }
        });
    }
    public void getNickName(){
        this.nickname = this.nickname_user.getText().toString().trim();

        if(this.nickname.isEmpty()){
            this.nickname_user.setError("Este campo es obligatorio");
            this.nickname_user.requestFocus();
            return;
        }
        if(this.nickname.length() >10){
            this.nickname_user.setError("No se exceda");
            this.nickname_user.requestFocus();
            return;
        }
        if(this.nickname.length()<2){
            this.nickname_user.setError("Muy corto");
            this.nickname_user.requestFocus();
            return;
        }
        if(this.photo_link.length() == 0){
            Toast.makeText(this, "Seleccione una foto de perfil", Toast.LENGTH_SHORT).show();
            return;
        }

        @SuppressLint("StaticFieldLeak")

        final AsyncTask<String, String, String> download = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... voids) {
                try{
                    Thread.sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                return "registrado";
            }

            @Override
            protected void onPostExecute(String s) {
                if(s.equals("registrado")){
                    btnGuardar.doneLoadingAnimation(Color.parseColor(
                            "#ffff4444"), BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
                    updateUser();
                    FirebaseAccount.irMain(firebaseUser, NicknameActivity.this);
                }
            }
        };
        btnGuardar.startAnimation();
        download.execute();
    }

    public void updateUser(){
        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
        UsuarioFirebase usuarioFirebase = new UsuarioFirebase(firebaseDatabase);

        usuarioFirebase.setNickname(nickname, photo_link, selected, firebaseUser);
    }



}
