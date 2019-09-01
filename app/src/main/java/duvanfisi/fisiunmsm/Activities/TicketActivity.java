package duvanfisi.fisiunmsm.Activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

import java.util.Date;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Model.CTicket;
import duvanfisi.fisiunmsm.R;

public class TicketActivity extends AppCompatActivity {

    private ImageView imagen_barras;
    private ImageView imagen_qr;
    private String textoBarras;



    private CTicket ticket;

    private View view_content;

    private TextView id_fecha;
    private TextView id_comida;
    private TextView id_sede;
    private TextView id_piso;
    private ImageView id_photo;
    private TextView id_nickname;
    private TextView id_names;
    private TextView id_codigo;
    private TextView id_turno;
    private TextView id_horario;
    private TextView id_ticket;

    private TextView entrada;
    private TextView segundo;
    private TextView fruta;

    private Date date;
    private String fecha;
    private String hora_ini;


    private String idpiso;
    private String idturno;
    private String horario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        this.inicializarViews();

        this.obtenerDatosUsuarioTicket();
        this.generarCodigo();
    }
    public void inicializarViews(){
        this.imagen_barras=findViewById(R.id.imagen_barras);
        this.imagen_qr=findViewById(R.id.imagen_qr);

        this.id_comida = findViewById(R.id.id_comida);
        this.id_fecha = findViewById(R.id.id_fecha);
        this.id_sede = findViewById(R.id.id_sede);
        this.id_piso = findViewById(R.id.id_piso);
        this.id_photo = findViewById(R.id.id_photo);
        this.id_nickname = findViewById(R.id.id_nickname);
        this.id_names = findViewById(R.id.id_names);
        this.id_codigo = findViewById(R.id.id_codigo);
        this.id_turno = findViewById(R.id.id_turno);
        this.id_horario =findViewById(R.id.id_horario);
        this.id_ticket = findViewById(R.id.id_ticket);

        this.entrada =findViewById(R.id.id_entrada);
        this.segundo = findViewById(R.id.id_segundo);
        this.fruta = findViewById(R.id.id_fruta);


        ticket = getIntent().getExtras().getParcelable(Utilidades.TICKETKEY);

    }

    public void obtenerDatosUsuarioTicket(){

        this.id_comida.setText(codigoComida(ticket.get_id_comida()));
        this.id_fecha.setText(ticket.getFecha_retiro());
        this.id_sede.setText(codigoSede(ticket.get_id_sede())+", San Marcos");
        this.id_piso.setText(codigoPiso(ticket.get_id_piso()));
        Picasso.with(this).load(ticket.getUser().getPhoto()).into(this.id_photo);
        this.id_nickname.setText(ticket.getUser().getNick());
        this.id_names.setText(ticket.getUser().getNames() + " " +ticket.getUser().getLastname_p()+
                " "+ ticket.getUser().getLastname_p());
        this.id_codigo.setText(ticket.getUser().get_id());
        this.id_turno.setText("Turno "+ticket.get_id_turno());
        this.id_horario.setText(ticket.getHorario_ini() +" hasta " + ticket.getHorario_fin());


    }


    public String codigoSede(int idsede){
        String sede = "";
        switch(idsede){
            case 1:
                sede ="Ciudad Universitaria";
                break;
            case 2:
                sede = "San Fernando";
                break;
            case 3:
                sede = "SJL";
                break;
        }
        return sede;
    }
    public String codigoComida(int idcomida){
        String comida = "";
        switch(idcomida){
            case 1:
                comida ="Almuerzo";
                break;
            case 2:
                comida = "Cena";
                break;
        }
        return comida;
    }
    public String codigoPiso(int idpiso){
        String piso = "";
        switch(idpiso){
            case 1:
                piso ="1er piso";
                break;
            case 2:
                piso = "2do piso";
                break;
        }
        return piso;
    }

    public void generarCodigo(){

        this.textoBarras = ticket.get_id();
        this.id_ticket.setText("Ticket No: "+ticket.get_id());
        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try{
            BitMatrix bitMatrix=multiFormatWriter.encode(textoBarras, BarcodeFormat.CODABAR,500,200);
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
            imagen_barras.setImageBitmap(bitmap);

            BitMatrix bitMatrix2=multiFormatWriter.encode(textoBarras,BarcodeFormat.QR_CODE,210,210);
            BarcodeEncoder barcodeEncoder2=new BarcodeEncoder();
            Bitmap bitmap2=barcodeEncoder2.createBitmap(bitMatrix2);
            imagen_qr.setImageBitmap(bitmap2);
        }catch (WriterException e){
            e.printStackTrace();
        }
    }
}
