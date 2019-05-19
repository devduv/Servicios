package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.FirebaseConexion.UsuarioFirebase;
import duvanfisi.fisiunmsm.Modelo.CUsuario;
import duvanfisi.fisiunmsm.R;

public class MapsActivity extends AppCompatActivity {

    private HashMap<Integer, CUsuario> cUsuarioHashMap;
    private ImageView back;
    private RecyclerView recyclerView;
    public static LinearLayout carga;

    public static boolean loading;

    @SuppressLint("UseSparseArrays")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        this.iniciliazarViews();

        this.setImage();

        this.onClickButton();

        this. cUsuarioHashMap = new HashMap<>();
        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
        UsuarioFirebase usuarioFirebase = new UsuarioFirebase(firebaseDatabase);
        usuarioFirebase.setUsuarios(recyclerView, cUsuarioHashMap);

    }


    public void iniciliazarViews(){
        back = findViewById(R.id.btnback);
        recyclerView = findViewById(R.id.recyclerViewUsuarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        carga = findViewById(R.id.cargausuarios);

    }

    public void setImage(){
        ImagePicasso.setImageCenterCop(MapsActivity.this, R.drawable.ic_back, back);
        //sdsdsd
    }

    public void onClickButton(){
        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
/*
    public void adapter(){
        final MyAdapter adapter = new MyAdapter(recyclerView, this, cUsuarioHashMap);
        recyclerView.setAdapter(adapter);
        adapter.setLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                if(cUsuarioHashMap.size() <= 20){
                    cUsuarioHashMap.put(null,null);
                    adapter.notifyItemInserted(cUsuarioHashMap.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cUsuarioHashMap.remove(cUsuarioHashMap.size()-1);
                            adapter.notifyItemRemoved(cUsuarioHashMap.size());
                            int index =  cUsuarioHashMap.size();
                            int end = index+10;

                            //agregar
                            adapter.notifyDataSetChanged();
                            adapter.setLoaded();
                        }
                    }, 4000);
                }
            }
        });
    }*/
}
