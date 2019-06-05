package duvanfisi.fisiunmsm.Activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.FirebaseConexion.TicketsFirebase;
import duvanfisi.fisiunmsm.Model.CTicket;
import duvanfisi.fisiunmsm.R;

public class MiRegistroActivity extends AppCompatActivity {

    public static View plantillacarga;
    private ImageView back;
    private TextView titulo;

    public static  TextView titlet;
    public static TextView titler;
    private RecyclerView rv;
    private ImageView img_ticket_icon;
    private ImageView img_personaje_icon;

    public static Button btnvermast;
    private TextView tickets_retirados;

    private HashMap<Integer, CTicket> cTicketHashMap;
    public static TextView auxticket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_registro);

        inicializarViews();
        setImageViews();
        onClickViews();

    }

    @SuppressLint("UseSparseArrays")
    public void inicializarViews(){


        this.plantillacarga = findViewById(R.id.plantillacargaregistro);
        this.cTicketHashMap = new HashMap<>();
        back = findViewById(R.id.btnback);
        titulo =findViewById(R.id.text_r_titulo);
        this.rv = findViewById(R.id.rvtickets);
        this.tickets_retirados = findViewById(R.id.t_retirados);
        this.btnvermast = findViewById(R.id.vermastickets);
        this.titlet = findViewById(R.id.titleticket);
        this.titler = findViewById(R.id.titleretirado);
        this.img_ticket_icon = findViewById(R.id.img_ticket_icon);
        this.btnvermast.setVisibility(ViewVisible.INVISIBLE);

        this.img_personaje_icon = findViewById(R.id.img_personaje_icon);
        this.auxticket = findViewById(R.id.auxticket);
        this.auxticket.setVisibility(ViewVisible.INVISIBLE);
        this.setTicketUser();
        this.setTexts();

    }


    public void setTexts(){
        /*String namettitle = MainActivity.usuario.getNombre() + " " +
                MainActivity.usuario.getAp_paterno() + " " +
                MainActivity.usuario.getAp_materno();

        String t_retirados = Integer.toString(MainActivity.usuario.getT_retirados());
        this.tickets_retirados.setText(t_retirados);
        this.titulo.setText(namettitle);*/

    }
    public void setImageViews(){
        ImagePicasso.setImageCenterCop(MiRegistroActivity.this, R.drawable.ic_back, back);
        ImagePicasso.setImageCenterCop(MiRegistroActivity.this, R.drawable.ticket, img_ticket_icon);
        ImagePicasso.setImageCenterCop(MiRegistroActivity.this, R.drawable.icon_personaje, img_personaje_icon);
    }

    public void onClickViews(){
        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        this.btnvermast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @SuppressLint("UseSparseArrays")
    public void setTicketUser(){
        cTicketHashMap = new HashMap<>();
        //TicketsFirebase ticketsFirebase= new TicketsFirebase(this);
        //ticketsFirebase.setTicketUser(rv, cTicketHashMap, MainActivity.usuario.getEmail());
    }
}
