package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.FirebaseConexion.FacultyFirebase;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Templates.TemplateLoading;
import duvanfisi.fisiunmsm.Templates.TemplateMessage;

public class FacultyActivity extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private RecyclerView recyclerView;

    public static String faculty_selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);


        inicializarViews();
        onClickButton();
    }

    public void inicializarViews(){
        ImageView back = findViewById(R.id.btnback);
        ImagePicasso.setImageCenterCop(this, R.drawable.ic_back, back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent intent_user = getIntent();
        //this.firebaseUser = (FirebaseUser) Objects.requireNonNull(intent_user.getExtras()).get(Utilidades.FIREBASEUSER);


        this.recyclerView = findViewById(R.id.recyclerViewFaculty);

        getFaculties();
    }

    public void onClickButton(){
       /* this.btn_reg_esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarEscuela();
            }
        });

        this.btn_found_fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TemplateMessage templateMessage = new TemplateMessage(RegisterEscuelaActivity.this);
                templateMessage.setMensaje("Registrar Escuela", "Está seguro que la facultad que se le mostró no es una a la que pertenece?", 6);
            }
        });**/

    }

    public void getFaculties(){
        FacultyFirebase facultyFirebase = new FacultyFirebase(this);
        facultyFirebase.setCollectionFaculties(recyclerView);
    }

}
