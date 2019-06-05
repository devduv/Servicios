package duvanfisi.fisiunmsm.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Model.CNoticia;
import duvanfisi.fisiunmsm.R;

public class NoticiaActivity extends AppCompatActivity {


    private TextView titulo_new_selected;
    private ImageView image_new_selected;
    private TextView fecha_new_selected;
    private TextView desc_01;
    private TextView desc_02;
    private TextView desc_03;
    private TextView desc_04;
    private TextView desc_05;

    private CNoticia noticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
        inicializarViews();
        setNew();
    }

    public void inicializarViews(){

        noticia = getIntent().getExtras().getParcelable(Utilidades.NOTICIAS);

        titulo_new_selected = findViewById(R.id.tit_new_selected);
        image_new_selected = findViewById(R.id.image_new_selected);
        fecha_new_selected = findViewById(R.id.fecha_new_selected);
        desc_01 = findViewById(R.id.desc_new_01);
        desc_02 = findViewById(R.id.desc_new_02);
        desc_03 = findViewById(R.id.desc_new_03);
        desc_04 = findViewById(R.id.desc_new_04);
        desc_05 = findViewById(R.id.desc_new_05);
    }

    public void setNew(){
        titulo_new_selected.setText(noticia.getTitulo());
        ImagePicasso.setImageCenterInside(this, noticia.getLink_foto(), image_new_selected);
        fecha_new_selected.setText(noticia.getFecha());
        desc_01.setText(noticia.getDescripcion_1());
        desc_02.setText(noticia.getDescripcion_2());
        desc_03.setText(noticia.getDescripcion_3());
        desc_04.setText(noticia.getDescripcion_4());
        desc_05.setText(noticia.getDescripcion_5());
    }

}
