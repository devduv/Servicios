package duvanfisi.fisiunmsm.Activities;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.FirebaseConexion.UserFirebase;
import duvanfisi.fisiunmsm.Interfaces.ILoadMore;
import duvanfisi.fisiunmsm.Model.CUsuario;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Recyclers.MyAdapter;

public class MapsActivity extends AppCompatActivity {

    private HashMap<Integer, CUsuario> cUsuarioHashMap;
    private ImageView back;
    private RecyclerView recyclerView;
    public static LinearLayout carga;

    MyAdapter adapter;
    private UserFirebase userFirebase;
    public static boolean loading;

    @SuppressLint("UseSparseArrays")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        this.iniciliazarViews();

        this.setImage();

        this.onClickButton();


        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(this);
        userFirebase = new UserFirebase(firebaseDatabase);
        userFirebase.setUsuarios(recyclerView, cUsuarioHashMap, 0, 10);

        adapter();
    }


    public void iniciliazarViews(){
        this. cUsuarioHashMap = new HashMap<>();
        back = findViewById(R.id.btnback);
        recyclerView = findViewById(R.id.recyclerViewUsuarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        carga = findViewById(R.id.cargausuarios);
        carga.setVisibility(ViewVisible.INVISIBLE);
        adapter = new MyAdapter(recyclerView, this, cUsuarioHashMap);
        recyclerView.setAdapter(adapter);

    }

    public void setImage(){
        ImagePicasso.setImageCenterCop(MapsActivity.this, R.drawable.ic_back, back);
    }

    public void onClickButton(){
        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void adapter(){
        adapter.setLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                if(cUsuarioHashMap.size() <= 100){
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
                            userFirebase.setUsuarios(recyclerView, cUsuarioHashMap, index, end);
                            adapter.notifyDataSetChanged();
                            adapter.setLoaded();
                        }
                    }, 2000);
                }
            }
        });
    }
}
