package duvanfisi.fisiunmsm.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;

import duvanfisi.fisiunmsm.Actions.Preferences;
import duvanfisi.fisiunmsm.Fragments.FNoticias;
import duvanfisi.fisiunmsm.Model.Users.CStudent;
import duvanfisi.fisiunmsm.Templates.TemplateMessage;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.Fragments.FHome;
import duvanfisi.fisiunmsm.Fragments.FPerfil;
import duvanfisi.fisiunmsm.Fragments.FPersonajes;
import duvanfisi.fisiunmsm.Fragments.FServicios;
import duvanfisi.fisiunmsm.Actions.StartFragment;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;


public class MainActivity extends AppCompatActivity {

    private boolean CAN_EXIT_APP = false;

    //User recurrent
    private FirebaseUser firebaseUser;
    private CStudent user;

    public static int ON_TOUCH;

    private BottomNavigationView navigation;
    private Toolbar toolbar;

    public static  FragmentManager fragmentManager;

    //Menu Main
    private FHome fhome;
    private FPersonajes fpersonajes;
    private FServicios nav_services;
    private FPerfil nav_profile;
    private FNoticias nav_news;

    private FirebaseDatabase firebaseDatabase;
    public static Fragment currentFragment;


    public static ArrayList<Integer> item;


   private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
           new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_news:
                        if(ON_TOUCH !=0){
                            ON_TOUCH =0;
                            startFragment("news", nav_news, 0);
                            return true;
                        }else{
                            return false;
                        }
                    case R.id.navigation_social_red:
                        if(ON_TOUCH !=1){
                            ON_TOUCH =1;
                            startFragment("personajes", fpersonajes, 1);
                            return true;
                        }else{
                            return false;
                        }
                    case R.id.navigation_home:
                        if(ON_TOUCH !=2){
                            ON_TOUCH = 2;
                            startFragment("home", fhome, 2);
                            return true;
                        }else{
                            return false;
                        }
                    case R.id.navigation_services:
                        if (ON_TOUCH != 3) {
                            ON_TOUCH = 3;
                            startFragment("servicios", nav_services, 3);
                            return true;
                        }else{
                            return false;
                        }
                    case R.id.navigation_profile:
                       if(ON_TOUCH !=4){
                           if(firebaseUser!=null) {
                               ON_TOUCH =4;
                               startFragment("mi perfil", nav_profile, getBundle());
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
                    ON_TOUCH =0;
                    startFragmentBack("news", MainActivity.this, nav_news);
                    navigation.getMenu().getItem(0).setChecked(true);
                    break;
                case 1:
                    ON_TOUCH =1;
                    startFragmentBack("personajes", MainActivity.this, fpersonajes);
                    navigation.getMenu().getItem(1).setChecked(true);
                    break;
                case 2:
                    ON_TOUCH = 2;
                    startFragmentBack("home", MainActivity.this,fhome);
                    navigation.getMenu().getItem(2).setChecked(true);
                    break;
                case 3:
                    ON_TOUCH = 3;
                    startFragmentBack("servicios", MainActivity.this, nav_services);
                    navigation.getMenu().getItem(3).setChecked(true);
                    break;
                case 4:
                    ON_TOUCH = 4;
                    startFragmentBack("mi perfil", MainActivity.this, nav_profile);
                    navigation.getMenu().getItem(4).setChecked(true);
                    break;
                default:

                    break;
            }
        }else{
            if (!CAN_EXIT_APP) {
                CAN_EXIT_APP = true;
                Toast.makeText(this, getString(R.string.close_app), Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        CAN_EXIT_APP = false;
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

        initViews();
        is_firebaseuser_on();
        first_time_welcome();
        setToolbar();
        gotoHome();

    }

    public void gotoHome(){
        ON_TOUCH = 2;
        startFragment("home", fhome, 2);
        navigation.getMenu().getItem(2).setChecked(true);
    }
    public void startLogin(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        StartActivity.startActivity(MainActivity.this, intent);
    }

    public void setToolbar(){
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
                            mensaje.setMensaje("Cerrar sesión", "¿Desea cerrar sesión?", 2);
                            return true;
                        }else{
                            startLogin();
                        }
                }
                return false;
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        /*FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
        UserFirebase usuarioFirebase = new UserFirebase(firebaseDatabase);
        usuarioFirebase.setUltimaConexion(MainActivity.usuario.getEmail(), TicketsFirebase.getHoraMedium());*/
    }

    public void initViews() {
        this.firebaseDatabase = new FirebaseDatabase(this);
        this.firebaseDatabase.settingsPersistence();
        this.firebaseUser = getIntent().getExtras().getParcelable(Utilidades.FIREBASEUSER);
        this.toolbar = findViewById(R.id.toolbar);
        this.toolbar.inflateMenu(R.menu.tools_main);
        this.navigation = findViewById(R.id.navigation);
        this.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void first_time_welcome(){
        TemplateMessage mensaje = new TemplateMessage(this);
        if (Preferences.getFirstTime(this)) {
            mensaje.setMensaje("Servicios San Marcos",
                    "Te damos la bienvenida estudiante san marquino.");
            Preferences.fisrtTime(this, false);
        }
    }

    public void is_firebaseuser_on(){
        if (firebaseUser != null) {
            toolbar.getMenu().getItem(3).setTitle("Cerrar sesión");
            setUsuario();
        }else{
            toolbar.getMenu().getItem(3).setTitle("Iniciar sesión");
        }
    }
    public void setUsuario(){
        user = getIntent().getExtras().getParcelable(Utilidades.KEY_MODEL_USER);
    }

    public static void startFragment(String name, Fragment fragment, int id){

            StartFragment.startFragment(name, fragment, id);
    }

    public static void startFragment(String name, Fragment fragment, Bundle bundle){

        StartFragment.startFragment(name, fragment, bundle);
    }

    public static void startFragmentBack(String name, AppCompatActivity appCompatActivity, Fragment fragment){

        StartFragment.startFragmentBack(name, fragment);
    }

    public static void startFragment(String name, Fragment fragment, int id, Bundle bundle){
        StartFragment.startFragment(name, fragment, bundle);
    }


    public MainActivity(){
        this.fhome = new FHome();
        this.fpersonajes = new FPersonajes();
        this.nav_services = new FServicios();
        this.nav_profile = new FPerfil();
        this.nav_news = new FNoticias();

        item = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
    }

    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putParcelable(Utilidades.FIREBASEUSER, firebaseUser);
        bundle.putParcelable(Utilidades.KEY_MODEL_USER, user);
        return bundle;
    }


}
