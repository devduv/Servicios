package duvanfisi.fisiunmsm.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.HashMap;

import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.ActivitiesUsers.NoticiaActivity;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.FirebaseConexion.NoticiaFirebase;
import duvanfisi.fisiunmsm.Modelo.CNoticia;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewFunction;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewTopNoticias;


public class FNoticias extends Fragment {

    private View view;
    private View itemView;
    private FirebaseDatabase firebaseDatabase;

    private HashMap<Integer, CNoticia> cNoticiaHashMap;
    private RecyclerView rvnewstop;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_noticias, container, false);
        inicializarViews();

        return view;
    }

    @SuppressLint("UseSparseArrays")
    public void inicializarViews(){
       this.itemView = view.findViewById(R.id.destacadonew);
        this.rvnewstop = view.findViewById(R.id.rvTopNews);
        firebaseDatabase = new FirebaseDatabase(getContext());
        cNoticiaHashMap = new HashMap<>();
        destacado();
    }

    public void destacado(){
        ImageView image_new;
        TextView tit_new;
        TextView fecha_new;
        TextView desc_new;
        ImageView img_calender;
        Button button_noticia;

        img_calender = itemView.findViewById(R.id.img_calender);
        image_new = itemView.findViewById(R.id.image_new);
        tit_new = itemView.findViewById(R.id.tit_new);
        fecha_new = itemView.findViewById(R.id.fecha_new);
        desc_new = itemView.findViewById(R.id.desc_new);
        button_noticia = itemView.findViewById(R.id.button_vernoticia);

        if(FHome.noticiaHashMap!=null) {
            ImagePicasso.setImageCenterCop(getContext(), R.drawable.ic_calendar, img_calender);
            ImagePicasso.setImageCenterInside(getContext(), FHome.noticiaHashMap.get(0).getLink_foto(), image_new);
            tit_new.setText(FHome.noticiaHashMap.get(0).getTitulo());
            fecha_new.setText(FHome.noticiaHashMap.get(0).getFecha());
            desc_new.setText(FHome.noticiaHashMap.get(0).getDescripcion_1());
            final Intent intent = new Intent(getContext(), NoticiaActivity.class);
            intent.putExtra(Utilidades.NOTICIAS, FHome.noticiaHashMap.get(0));
            button_noticia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StartActivity.startActivity(getContext(), intent);
                }
            });
            image_new.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StartActivity.startActivity(getContext(), intent);
                }
            });
            tit_new.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StartActivity.startActivity(getContext(), intent);
                }
            });
            downloadNoticias();
        }
    }

    public void downloadNoticias(){

        NoticiaFirebase noticiaFirebase = new NoticiaFirebase(firebaseDatabase);
        noticiaFirebase.setNoticiasNews(rvnewstop, cNoticiaHashMap);
        /*RecyclerViewTopNoticias adapter_top = new RecyclerViewTopNoticias(getContext(), FHome.noticiaHashMap);
        recyclerview(RecyclerViewFunction.VERTICAL, adapter_top, rvnewstop);*/

    }

    /*public void recyclerview(int orientation, RecyclerView.Adapter adapter, RecyclerView recyclerView){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), orientation, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
    }*/
}
