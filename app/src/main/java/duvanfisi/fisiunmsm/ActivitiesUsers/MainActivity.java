package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;


import java.util.ArrayList;

import duvanfisi.fisiunmsm.Actions.Preferences;
import duvanfisi.fisiunmsm.Fragments.FNoticias;
import duvanfisi.fisiunmsm.Templates.TemplateLoading;
import duvanfisi.fisiunmsm.Templates.TemplateMessage;
import duvanfisi.fisiunmsm.Model.CUsuario;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.FirebaseConexion.UsuarioFirebase;
import duvanfisi.fisiunmsm.Fragments.FHome;
import duvanfisi.fisiunmsm.Fragments.FPerfil;
import duvanfisi.fisiunmsm.Fragments.FPersonajes;
import duvanfisi.fisiunmsm.Fragments.FServicios;
import duvanfisi.fisiunmsm.Actions.StartFragment;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;


public class MainActivity extends AppCompatActivity {


    public static FirebaseUser firebaseUser;
    private boolean canExitApp = false;

    @SuppressLint("StaticFieldLeak")
    public static BottomNavigationView navigation;

    @SuppressLint("StaticFieldLeak")
    public static Toolbar toolbar;

    public static  FragmentManager fragmentManager;
    private FHome fhome;
    private FPersonajes fpersonajes;
    @SuppressLint("StaticFieldLeak")
    public static FServicios fservicios;
    private FPerfil fperfil;
    private FNoticias fnews;

    public static CUsuario usuario;

    private AlertDialog dialog_loading;

    private FirebaseDatabase firebaseDatabase;
    public static Fragment currentFragment;

    public static int touch;
    public static ArrayList<Integer> item;


   private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
           new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_noticias:
                        if(touch!=0){
                            touch=0;
                            startFragment("news", fnews, 0);
                            return true;
                        }else{
                            return false;
                        }
                    case R.id.navigation_personajes:
                        if(touch!=1){
                            touch=1;
                            startFragment("personajes", fpersonajes, 1);
                            return true;
                        }else{
                            return false;
                        }
                    case R.id.navigation_home:
                        if(touch!=2){
                            touch = 2;
                            startFragment("home", fhome, 2);
                            return true;
                        }else{
                            return false;
                        }
                    case R.id.navigation_services:
                        if (touch != 3) {
                            touch = 3;
                            startFragment("servicios", fservicios, 3);
                            return true;
                        }else{
                            return false;
                        }
                    case R.id.navigation_perfil:
                       if(touch!=4){
                           if(firebaseUser!=null) {
                               touch=4;
                               startFragment("mi perfil", fperfil, 4);
                               return true;
                           }else{
                               TemplateMessage mensaje = new TemplateMessage(MainActivity.this);
                               mensaje.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                               mensaje.setMensaje("Iniciar Sesión", "No ha iniciado sesión", 3);
                               return false;
                           }
                       }else{
                           return false;
                       }
                }
                return false;
            }
    };

    @Override
    public void onBackPressed() {
        if(item.size()>1) {
            item.remove(item.size() - 1);
            int id = item.get(item.size() - 1);
            switch (id) {
                case 0:
                    touch=0;
                    startFragmentBack("news", MainActivity.this, fnews);
                    navigation.getMenu().getItem(0).setChecked(true);
                    break;
                case 1:
                    touch=1;
                    startFragmentBack("personajes", MainActivity.this, fpersonajes);
                    navigation.getMenu().getItem(1).setChecked(true);
                    break;
                case 2:
                    touch = 2;
                    startFragmentBack("home", MainActivity.this,fhome);
                    navigation.getMenu().getItem(2).setChecked(true);
                    break;
                case 3:
                    touch = 3;
                    startFragmentBack("servicios", MainActivity.this, fservicios);
                    navigation.getMenu().getItem(3).setChecked(true);
                    break;
                case 4:
                    touch = 4;
                    startFragmentBack("mi perfil", MainActivity.this, fperfil);
                    navigation.getMenu().getItem(4).setChecked(true);
                    break;
                default:

                    break;
            }
        }else{
            if (!canExitApp) {
                canExitApp = true;
                Toast.makeText(this, getString(R.string.close_app), Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        canExitApp = false;
                    }
                }, 2000);
            } else {
                finish();
            }
        }
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.tools_main);


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.mi_registro:
                        if(firebaseUser!=null){
                            StartActivity.startActivity(MainActivity.this, new MiRegistroActivity());
                            return true;
                        }else{
                            TemplateMessage mensaje = new TemplateMessage(MainActivity.this);
                            mensaje.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                            mensaje.setMensaje("Iniciar Sesión", "No ha iniciado sesión", 3);
                            return false;
                        }
                    case R.id.action_arrive:
                        /*TemplateMessage mensaje_mapa = new TemplateMessage(MainActivity.this);
                        mensaje_mapa.setMensaje("Mapa", "Próximamente!");*/
                        StartActivity.startActivity(MainActivity.this, new LocationActivity()   );
                       // Intent intent = new Intent(MainActivity.this, LocationActivity.ExtraData.class);
                        return true;
                    case R.id.action_info:
                        StartActivity.startActivity(MainActivity.this, new WelcomeActivity());
                        return true;
                    case R.id.action_salir:
                        if(firebaseUser!=null) {
                            TemplateMessage mensaje = new TemplateMessage(MainActivity.this);
                            mensaje.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                            mensaje.setMensaje("Cerrar sesión", "¿Desea cerrar sesión?", 2);
                            return true;
                        }else{
                            startLogin();
                        }
                }
                return false;
            }
        });
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        touch = 2;
        startFragment("home", fhome, 2);
        navigation.getMenu().getItem(2).setChecked(true);

        inicializarViews();
    }

    public void startLogin(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        StartActivity.startActivity(MainActivity.this, intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        /*FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
        UsuarioFirebase usuarioFirebase = new UsuarioFirebase(firebaseDatabase);
        usuarioFirebase.setUltimaConexion(MainActivity.usuario.getEmail(), TicketsFirebase.getHoraMedium());*/
    }

    public void inicializarViews() {

        firebaseDatabase = new FirebaseDatabase(this);
        firebaseDatabase.settingsPersistence();
        TemplateLoading loading = new TemplateLoading(this);
        loading.setTextLoading("Cargando datos...");
        this.dialog_loading = loading.loading();

        Intent intent = getIntent();
        firebaseUser = intent.getExtras().getParcelable(Utilidades.FIREBASEUSER);

        if (firebaseUser != null) {
            toolbar.getMenu().getItem(3).setTitle("Cerrar sesión");
            setUsuario();
        }else{
            toolbar.getMenu().getItem(3).setTitle("Iniciar sesión");
        }

        TemplateMessage mensaje = new TemplateMessage(this);
        mensaje.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        if (Preferences.getFirstTime(this)) {
            mensaje.setMensaje("Servicios San Marcos",
                    "Esta aplicación está en desarrollo aún y no es oficial de la universidad.\n ¿Te gustaría que lo fuera?");
            Preferences.fisrtTime(this, false);
        }
    }

    public void setUsuario(){
        dialog_loading.show();
        UsuarioFirebase usuarioFirebase = new UsuarioFirebase(firebaseDatabase);
        DocumentReference documentReference = usuarioFirebase.getDocumentUsuario(firebaseUser.getEmail());
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                usuario = documentSnapshot.toObject(CUsuario.class);
                dialog_loading.dismiss();
            }
        });
    }
    public static void startFragment(String name, Fragment fragment, int id){

            StartFragment.startFragment(name, fragment, id);
    }

    public static void startFragmentBack(String name, AppCompatActivity appCompatActivity, Fragment fragment){

        StartFragment.startFragmentBack(name, fragment);
    }

    public static void startFragment(String name, Fragment fragment, int id, Bundle bundle){
        StartFragment.startFragment(name, fragment, bundle, id);
    }


    public MainActivity(){
        this.fhome = new FHome();
        this.fpersonajes = new FPersonajes();
        fservicios = new FServicios();
        this.fperfil = new FPerfil();
        this.fnews = new FNoticias();


        item = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
    }

    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putParcelable(Utilidades.KEY_MODEL_USER, usuario);
        return bundle;
    }


}
