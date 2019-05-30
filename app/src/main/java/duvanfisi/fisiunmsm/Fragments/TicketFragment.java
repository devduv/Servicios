package duvanfisi.fisiunmsm.Fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import duvanfisi.fisiunmsm.ActivitiesUsers.MainActivity;
import duvanfisi.fisiunmsm.Model.CTicket;
import duvanfisi.fisiunmsm.R;


public class TicketFragment extends Fragment {

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

    public TicketFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view_content = inflater.inflate(R.layout.fragment_ticket, container, false);

        this.inicializarViews();

        this.obtenerDatosUsuarioTicket();
        this.generarCodigo();
        return view_content;
    }

    public void inicializarViews(){
        this.imagen_barras=view_content.findViewById(R.id.imagen_barras);
        this.imagen_qr=view_content.findViewById(R.id.imagen_qr);

        this.id_comida = view_content.findViewById(R.id.id_comida);
        this.id_fecha = view_content.findViewById(R.id.id_fecha);
        this.id_sede = view_content.findViewById(R.id.id_sede);
        this.id_piso = view_content.findViewById(R.id.id_piso);
        this.id_photo = view_content.findViewById(R.id.id_photo);
        this.id_nickname = view_content.findViewById(R.id.id_nickname);
        this.id_names = view_content.findViewById(R.id.id_names);
        this.id_codigo = view_content.findViewById(R.id.id_codigo);
        this.id_turno = view_content.findViewById(R.id.id_turno);
        this.id_horario = view_content.findViewById(R.id.id_horario);
        this.id_ticket = view_content.findViewById(R.id.id_ticket);

        this.entrada =view_content.findViewById(R.id.id_entrada);
        this.segundo = view_content.findViewById(R.id.id_segundo);
        this.fruta = view_content.findViewById(R.id.id_fruta);

        ticket = getArguments().getParcelable(Utilidades.TICKETKEY);

    }

    public void obtenerDatosUsuarioTicket(){

        this.id_comida.setText(codigoComida(ticket.get_id_comida()));
        this.id_fecha.setText(ticket.getFecha_retiro());
        this.id_sede.setText(codigoSede(ticket.get_id_sede())+", San Marcos");
        this.id_piso.setText(codigoPiso(ticket.get_id_piso()));
        Picasso.with(getContext()).load(MainActivity.usuario.getPhoto()).into(this.id_photo);
        this.id_nickname.setText(MainActivity.usuario.getNickname());
        this.id_names.setText(ticket.getNombre_usuario() + " " +ticket.getAp_paterno_usuario()+ " "+ ticket.getAp_materno_usuario());
        this.id_codigo.setText(Integer.toString(ticket.getCod_usuario()));
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
