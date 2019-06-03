package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseUser;


import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.FirebaseConexion.FacultyFirebase;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Templates.TemplateLoading;
import duvanfisi.fisiunmsm.Templates.TemplateMessage;

public class FacultyActivity extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private RecyclerView recyclerView;

    private Button btn_reg_fac;
    private ImageView back;
    public static String faculty_selected;

    private TemplateLoading templateLoading;
    private AlertDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);


        inicializarViews();
        onClickButton();
    }

    public void inicializarViews(){
        this.btn_reg_fac = findViewById(R.id.btn_reg_fac);
        back = findViewById(R.id.btnback);



        Intent intent_user = getIntent();
        //this.firebaseUser = (FirebaseUser) Objects.requireNonNull(intent_user.getExtras()).get(Utilidades.FIREBASEUSER);


        this.recyclerView = findViewById(R.id.recyclerViewFaculty);

        templateLoading = new TemplateLoading(this);

        setInvisible();
        setImage();
        loadingON();
        getFaculties();
    }

    public void setImage(){
        ImagePicasso.setImageCenterCop(this, R.drawable.ic_back, back);
    }

    public void setInvisible(){
        this.btn_reg_fac.setVisibility(ViewVisible.INVISIBLE);
    }
    public void loadingON(){
        templateLoading.setTextLoading(Utilidades.FOUNDING_FACULTIES);
        loading = templateLoading.loading();
        loading.show();
    }
    public void onClickButton(){
       this.btn_reg_fac.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               irRegEsc();
           }
       });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void getFaculties(){
        FacultyFirebase facultyFirebase = new FacultyFirebase(this);
        facultyFirebase.setCollectionFaculties(recyclerView, loading, btn_reg_fac);
    }


    public void irRegEsc(){
        StartActivity.startActivity(this, new RegisterEscuelaActivity());
    }
}
