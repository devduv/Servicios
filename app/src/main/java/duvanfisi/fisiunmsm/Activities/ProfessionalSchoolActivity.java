package duvanfisi.fisiunmsm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.FirebaseConexion.UserFirebase;
import duvanfisi.fisiunmsm.Model.Users.CStudent;
import duvanfisi.fisiunmsm.Templates.TemplateLoading;
import duvanfisi.fisiunmsm.Templates.TemplateMessage;

import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.FirebaseConexion.ProfesionalSchoolFirebase;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseAccount;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Actions.Utilidades;

public class ProfessionalSchoolActivity extends AppCompatActivity {

    public static String FACULTY_SELECTED;
    public static String SCHOOL_SELECTED;

    private Intent intent_user;

    public static CStudent user;
    public static FirebaseUser firebaseUser;

    private Button btn_reg_esc;
    private Button btn_found_fac;
    private ImageView back;


    private TextView title_faculty;
    private ImageView img_esc;

    private TemplateLoading loading;

    public static AlertDialog dialog_loading;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_reg);

        initViews();
        setVisibility();
        setFirebaseUser();
        setUser();
        setLoading();
        setImage();
        onClickButton();
        getCodeUser();
    }


    public void initViews() {
        this.back = findViewById(R.id.btnback);
        this.intent_user = getIntent();
        this.loading = new TemplateLoading(this);
        this.recyclerView = findViewById(R.id.recyclerViewEscuelas);
        this.title_faculty = findViewById(R.id.title_facultad);
        this.img_esc = findViewById(R.id.img_esc);
        this.btn_reg_esc = findViewById(R.id.btn_reg_esc);
        this.btn_found_fac = findViewById(R.id.btn_found_fac);
    }

    public void setVisibility(){
        this.btn_reg_esc.setVisibility(ViewVisible.INVISIBLE);
        this.btn_found_fac.setVisibility(ViewVisible.INVISIBLE);
        SCHOOL_SELECTED="";
    }

    public void setFirebaseUser(){
        firebaseUser = (FirebaseUser)
                Objects.requireNonNull(intent_user.getExtras()).get(Utilidades.FIREBASEUSER);
    }

    public void setUser(){
        if(intent_user.getExtras()!=null){
            user = (CStudent) intent_user.getExtras().get(Utilidades.KEY_MODEL_USER);
        }
    }
    public void setLoading(){
        loading.setTextLoading("Buscando escuelas...");
        dialog_loading = loading.loading();
        dialog_loading.show();
    }

    public void setImage(){
        ImagePicasso.setImageCenterCop(this, R.drawable.ic_back, back);
    }

    public void onClickButton(){
        this.btn_reg_esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_profesional_school();
            }
        });

        this.btn_found_fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TemplateMessage templateMessage = new TemplateMessage(ProfessionalSchoolActivity.this);
                templateMessage.setMensaje(Utilidades.SELECT_PROFESIONAL_SCHOOL,
                        Utilidades.NO_FACULTY_CURRENT, Utilidades.ACTION_FOUND_FACULTY);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    public void select_profesional_school(){

        TemplateMessage mensaje = new TemplateMessage(this);
        if(SCHOOL_SELECTED.length()==0){
            mensaje.setMensaje(Utilidades.SELECT_PROFESIONAL_SCHOOL, "No hay seleccionado ninguna escuela");
        }else{

            setProfessionalSchoolModel();
            setProfessionalSchoolFirebase();

            gotoNextActivty();
        }
    }

    public void setProfessionalSchoolModel() {
        user.setFaculty(FACULTY_SELECTED);
        user.setProfessional_school(SCHOOL_SELECTED);

    }
    public void setProfessionalSchoolFirebase(){
        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
        UserFirebase userFirebase = new UserFirebase(firebaseDatabase);
        userFirebase.setProfessionalSchool(FACULTY_SELECTED, SCHOOL_SELECTED, firebaseUser);
    }

    public void gotoNextActivty(){
        FirebaseAccount firebaseAccount = new FirebaseAccount(this);
        firebaseAccount.verify_phonenumber(user, firebaseUser);
        //finish();
    }
    public void getCodeUser(){
        if (user != null) {
            if(!user.getFaculty().equalsIgnoreCase(Utilidades.NO_DATES)){
                getFacultades(user.getFaculty());
            }else{
                System.out.println(user.getEmail());
                String cod = user.get_id();
                String c = cod.substring(2,4);
                getFacultades(c);
            }
        }else{
            TemplateMessage templateMessage = new TemplateMessage(this);
            templateMessage.setMensaje(Utilidades.SELECT_PROFESIONAL_SCHOOL, Utilidades.ERROR);
        }
    }

    public void getFacultades(String cod){
        ProfesionalSchoolFirebase schoolFirebase = new ProfesionalSchoolFirebase(this);
        schoolFirebase.setCollectionFacultades(cod, title_faculty, img_esc,recyclerView,
                btn_reg_esc, btn_found_fac);
    }

}
