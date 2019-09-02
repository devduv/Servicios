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


import duvanfisi.fisiunmsm.Actions.Preferences;
import duvanfisi.fisiunmsm.Extras.CloseKeyboard;
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
    private boolean GET_BACK_HOME = false;

    //User recurrent
    private FirebaseUser firebaseUser;
    private CStudent user;

    //Navigation & Toolbar
    private BottomNavigationView navigation;
    private Toolbar toolbar;

    public static  FragmentManager fragmentManager;

    //This is Menu Main
    private FHome nav_home;
    private FPersonajes fpersonajes;
    private FServicios nav_services;
    private FPerfil nav_profile;
    private FNoticias nav_news;

    private FirebaseDatabase firebaseDatabase;
    public static Fragment currentFragment;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        set_persistence();
        setmOnNavigationItemSelectedListener();
        initViews();
        get_user_current_firebase();
        is_firebaseuser_on();
        first_time_welcome();
        setToolbar();
        gotoHome();

    }


   private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;


    public void setmOnNavigationItemSelectedListener(){
        mOnNavigationItemSelectedListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_news:
                        GET_BACK_HOME = true;
                        gotofragment(Utilidades.NAV_NEWS, nav_news);
                        return true;
                    case R.id.navigation_social_red:
                        GET_BACK_HOME = true;
                        gotofragment(Utilidades.NAV_SOCIAL_RED, fpersonajes);
                        return true;
                    case R.id.navigation_home:
                        GET_BACK_HOME = false;
                        gotofragment(Utilidades.NAV_HOME, nav_home);
                        return true;
                    case R.id.navigation_services:
                        GET_BACK_HOME = true;
                        gotofragment(Utilidades.NAV_SERVICES, nav_services);
                        return true;
                    case R.id.navigation_profile:
                        GET_BACK_HOME = true;
                        if(firebaseUser!=null) {
                            gotofragment(Utilidades.NAV_PROFILE, nav_profile);
                            return true;
                        }else{
                            TemplateMessage mensaje = new TemplateMessage(MainActivity.this);
                            mensaje.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                            mensaje.setMensaje("Iniciar Sesión", "No ha iniciado sesión", 3);
                            return false;
                        }
                }
                return false;
            }
        };
    }

    public void gotofragment(String tag, Fragment fragment){
        startFragment(tag, fragment, getBundle());
    }

    @Override
    public void onBackPressed() {
        if(GET_BACK_HOME){
            GET_BACK_HOME = false;
            gotofragment(Utilidades.NAV_HOME, nav_home);
            this.navigation.setSelectedItemId(R.id.navigation_home);
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

    public void gotoHome(){
        gotofragment(Utilidades.NAV_HOME, nav_home);
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
                            StartActivity.startActivity(MainActivity.this, new MiRegistroActivity(), firebaseUser, user);
                            return true;
                        }else{
                            TemplateMessage mensaje = new TemplateMessage(MainActivity.this);
                            mensaje.setMensaje("Iniciar Sesión", "No ha iniciado sesión", 3);
                            return false;
                        }
                    case R.id.action_arrive:
                        StartActivity.startActivity(MainActivity.this, new LocationActivity()   );
                        return true;
                    case R.id.action_info:
                        //StartActivity.startActivity(MainActivity.this, new WelcomeActivity());
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

    @Override protected void onStop() {
        super.onStop();
        /*FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
        UserFirebase usuarioFirebase = new UserFirebase(firebaseDatabase);
        usuarioFirebase.setUltimaConexion(MainActivity.usuario.getEmail(), TicketsFirebase.getHoraMedium());*/
    }

    public void initViews() {
        this.toolbar = findViewById(R.id.toolbar);
        this.toolbar.inflateMenu(R.menu.tools_main);
        this.navigation = findViewById(R.id.navigation);
        this.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void set_persistence(){
        this.firebaseDatabase = new FirebaseDatabase(this);
        this.firebaseDatabase.settingsPersistence();
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
            get_user_current_model();
        }else{
            toolbar.getMenu().getItem(3).setTitle("Iniciar sesión");
        }
    }
    public void get_user_current_firebase(){
        if(getIntent().getExtras()!=null){
            this.firebaseUser = getIntent().getExtras().getParcelable(Utilidades.FIREBASEUSER);
        }
    }
    public void get_user_current_model(){
        if(getIntent().getExtras()!=null){
            this.user = getIntent().getExtras().getParcelable(Utilidades.KEY_MODEL_USER);
        }
    }
    public void startFragment(String name, Fragment fragment, Bundle bundle){
        StartFragment.startFragment(name, fragment, bundle);
    }
    public MainActivity(){
        this.nav_home = new FHome();
        this.fpersonajes = new FPersonajes();
        this.nav_services = new FServicios();
        this.nav_profile = new FPerfil();
        this.nav_news = new FNoticias();
        fragmentManager = getSupportFragmentManager();
    }
    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putParcelable(Utilidades.FIREBASEUSER, firebaseUser);
        bundle.putParcelable(Utilidades.KEY_MODEL_USER, user);
        return bundle;
    }


}
