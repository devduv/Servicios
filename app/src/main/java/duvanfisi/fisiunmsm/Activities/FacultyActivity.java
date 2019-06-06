package duvanfisi.fisiunmsm.Activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseUser;


import java.util.Objects;

import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.FirebaseConexion.FacultyFirebase;
import duvanfisi.fisiunmsm.Model.Users.CStudent;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Templates.TemplateLoading;

public class FacultyActivity extends AppCompatActivity {

    private Intent intent_user;
    public static CStudent user;
    public static FirebaseUser firebaseUser;
    private RecyclerView recyclerView;
    private ImageView back;
    private TemplateLoading templateLoading;
    private AlertDialog loading;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        inicializarViews();
        setFirebaseUser();
        setUser();
        onClickButton();
        setImage();
        loadingON();
        getFaculties();
    }
    public void inicializarViews(){
        this.back = findViewById(R.id.btnback);
        this.intent_user = getIntent();
        this.recyclerView = findViewById(R.id.recyclerViewFaculty);
        this.templateLoading = new TemplateLoading(this);
    }
    public void setFirebaseUser(){
        firebaseUser = (FirebaseUser)
                Objects.requireNonNull(intent_user.getExtras()).get(Utilidades.FIREBASEUSER);
    }
    public void setUser(){
        user = (CStudent) intent_user.getExtras().get(Utilidades.KEY_MODEL_USER);
    }
    public void setImage(){
        ImagePicasso.setImageCenterCop(this, R.drawable.ic_back, back);
    }
    public void loadingON(){
        templateLoading.setTextLoading(Utilidades.FOUNDING_FACULTIES);
        loading = templateLoading.loading();
        loading.show();
    }
    public void onClickButton(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void getFaculties(){
        FacultyFirebase facultyFirebase = new FacultyFirebase(this);
        facultyFirebase.setCollectionFaculties(recyclerView, loading, firebaseUser, user);
    }

}
