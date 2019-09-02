package duvanfisi.fisiunmsm.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Activities.MainActivity;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.FirebaseConexion.AnuncioFirebase;
import duvanfisi.fisiunmsm.FirebaseConexion.ProfesionalSchoolFirebase;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.FirebaseConexion.NoticiaFirebase;
import duvanfisi.fisiunmsm.FirebaseConexion.PersonajeFirebase;
import duvanfisi.fisiunmsm.FirebaseConexion.PublicacionFirebase;
import duvanfisi.fisiunmsm.FirebaseConexion.ServicioFirebase;
import duvanfisi.fisiunmsm.Model.CPersonaje;
import duvanfisi.fisiunmsm.Model.CNoticia;
import duvanfisi.fisiunmsm.Model.CPublicacion;
import duvanfisi.fisiunmsm.Model.CServicio;
import duvanfisi.fisiunmsm.Model.Users.CStudent;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Templates.TemplateRealizarPub;


public class FHome extends Fragment {

    private View view;
    private View view_realizar_pub;

    private TemplateRealizarPub realizarPub;

    private FirebaseDatabase firebaseDatabase;

    private RecyclerView publicaciones;

    private CStudent user;
    private FirebaseUser firebaseUser;
    private Bundle bundle;

    public FHome() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        inicializarViews();
        setPublicacion();
        return view;
    }


    @SuppressLint("UseSparseArrays")
    public void inicializarViews(){
        this.getUserCurrent();
        this.publicaciones = view.findViewById(R.id.publicaciones);
        this.view_realizar_pub = view.findViewById(R.id.layout_realizar_pub);
        this.realizarPub = new TemplateRealizarPub(getContext(), this.view_realizar_pub);
        this.firebaseDatabase = new FirebaseDatabase(getContext());

        this.realizarPub.setPhoto(user.getPhoto());
    }

    public void setPublicacion(){
        HashMap<Integer, CPublicacion> cPublicacionHashMap = new HashMap<>();
        PublicacionFirebase publicacionFirebase = new PublicacionFirebase(firebaseDatabase);
        publicacionFirebase.setPublicacionesOnHome(publicaciones, cPublicacionHashMap);
    }

    public void getUserCurrent(){
        bundle = getArguments();

        firebaseUser = bundle.getParcelable(Utilidades.FIREBASEUSER);
        user = bundle.getParcelable(Utilidades.KEY_MODEL_USER);
    }



}
