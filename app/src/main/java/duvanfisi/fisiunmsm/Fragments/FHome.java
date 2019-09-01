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
import android.widget.RelativeLayout;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Activities.MainActivity;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.FirebaseConexion.AnuncioFirebase;
import duvanfisi.fisiunmsm.FirebaseConexion.ProfesionalSchoolFirebase;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.FirebaseConexion.NoticiaFirebase;
import duvanfisi.fisiunmsm.FirebaseConexion.PersonajeFirebase;
import duvanfisi.fisiunmsm.FirebaseConexion.ServicioFirebase;
import duvanfisi.fisiunmsm.Model.CPersonaje;
import duvanfisi.fisiunmsm.Model.CNoticia;
import duvanfisi.fisiunmsm.Model.CServicio;
import duvanfisi.fisiunmsm.R;


public class FHome extends Fragment {

    private View view;
    private ImageView image_ads;
    private RecyclerView recyclerViewPersonaje;
    private RecyclerView recyclerViewNoticia;
    private RecyclerView recyclerViewServicio;
    private RecyclerView recyclerViewArea;

    private View pantallacarga;


    private RelativeLayout relativeLayout;

    private Button button_noticias;

    public static HashMap<Integer, CNoticia> noticiaHashMap;
    public static HashMap<Integer, CPersonaje> personajeHashMap;

    private FirebaseDatabase firebaseDatabase;
    public FHome() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        inicializarViews();
        return view;
    }

    public void uploadImage(int load, ImageView img){
        ImagePicasso.setImageCenterCop(getContext(), load, img);
    }
    @SuppressLint("UseSparseArrays")
    public void inicializarViews(){
        this.pantallacarga = view.findViewById(R.id.pantallacarga);
        this.relativeLayout = view.findViewById(R.id.relative);
        this.relativeLayout.setVisibility(ViewVisible.INVISIBLE);

        ImageView img_news = view.findViewById(R.id.img_news);
        ImageView img_services = view.findViewById(R.id.img_services);
        ImageView img_areas = view.findViewById(R.id.img_areas);
        this.button_noticias = view.findViewById(R.id.button_fragment_news);

        this.image_ads = view.findViewById(R.id.imgpub);

        this.uploadImage(R.drawable.ic_news, img_news);
        this.recyclerViewPersonaje = view.findViewById(R.id.recyclerView_personajes);
        this.uploadImage(R.drawable.ic_services, img_services);
        this.uploadImage(R.drawable.ic_areas, img_areas);

        this.recyclerViewNoticia = view.findViewById(R.id.recyclerViewNoticias);
        this.recyclerViewServicio = view.findViewById(R.id.recyclerViewServices);
        this.recyclerViewArea = view.findViewById(R.id.recyclerViewAreas);

        firebaseDatabase = new FirebaseDatabase(getContext());
        firebaseDatabase.settingsPersistence();
        setPersonajeDB();
        //setPublicacion();
        setNoticiaDB();
        setServicio();
        setAreas();
        setAnuncio();
        button_noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // MainActivity.ON_TOUCH = 0;
                //MainActivity.startFragment("news", new FNoticias(), 0);
                //MainActivity.navigation.getMenu().getItem(0).setChecked(true);
            }
        });
    }


    /*public void setPublicacion(){
        HashMap<Integer, CPublicacion> cPublicacionHashMap = new HashMap<>();
        PublicacionFirebase publicacionFirebase = new PublicacionFirebase(firebaseDatabase);
        publicacionFirebase.setNoticiasHome(recyclerViewPublicacion, cPublicacionHashMap, relativeLayout, pantallacarga);
    }*/
    public void setPersonajeDB(){
        personajeHashMap = new HashMap<>();
        PersonajeFirebase personajeFirebase = new PersonajeFirebase(firebaseDatabase);
        personajeFirebase.setPersonajes(recyclerViewPersonaje, personajeHashMap);
    }

    public void setNoticiaDB(){
        noticiaHashMap = new HashMap<>();
        NoticiaFirebase noticiaFirebase = new NoticiaFirebase(firebaseDatabase);
        noticiaFirebase.setNoticiasHome(recyclerViewNoticia, noticiaHashMap, relativeLayout, pantallacarga);
    }

    public void setServicio(){
        HashMap<Integer, CServicio> servicioHashMap = new HashMap<>();
        ServicioFirebase servicioFirebase = new ServicioFirebase(firebaseDatabase);
        servicioFirebase.setServicios(recyclerViewServicio, servicioHashMap);
    }

    public void setAreas(){
        HashMap<Integer, CArea> cAreaHashMap = new HashMap<>();
        ProfesionalSchoolFirebase escuelaFirebase  = new ProfesionalSchoolFirebase(getContext());
        escuelaFirebase.setCollectionAreas(recyclerViewArea, cAreaHashMap);
    }

    public void setAnuncio(){
        AnuncioFirebase anuncioFirebase = new AnuncioFirebase(firebaseDatabase);
        anuncioFirebase.setAnuncio(image_ads);
    }


    public void proximaactualizacion(){

    }


}
