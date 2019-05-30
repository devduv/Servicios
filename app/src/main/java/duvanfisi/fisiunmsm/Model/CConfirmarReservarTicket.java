package duvanfisi.fisiunmsm.Model;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import duvanfisi.fisiunmsm.Actions.InternetConecction;
import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Extras.ADialogs;
import duvanfisi.fisiunmsm.Extras.ViewFloat;
import duvanfisi.fisiunmsm.FirebaseConexion.TurnoFirebase;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Templates.TemplateLoading;

public class CConfirmarReservarTicket {

    private Context context;
    private View view;

    private TextView idpiso;
    private TextView idhorario;
    private TextView idsede;

    private Button btnconfirmarreserv;
    private Button btncancelar;

    private AlertDialog dialog;

    private int id_piso;
    private int id_comida;
    private int id_turno;
    private DatabaseReference databaseReference;


    public CConfirmarReservarTicket(Context context, int id_piso, int id_turno, DatabaseReference databaseReference){
        this.context = context;
        this.id_piso = id_piso;
        this.id_comida = id_comida;
        this.databaseReference = databaseReference;
        this.id_turno = id_turno;
        this.view = ViewFloat.floatview(this.context, R.layout.conf_turno_selected);

        this.btnconfirmarreserv = this.view.findViewById(R.id.btnconfirmarreservt);
        this.btncancelar = this.view.findViewById(R.id.btncancelarreservt);
       // this.idpiso = this.view.findViewById(R.id.idconpiso);
        this.idsede = this.view.findViewById(R.id.idconfirmsede);
        this.idhorario = this.view.findViewById(R.id.idconfirhorario);

        onClick();
    }

    public void mostrarDialogMensaje(){
        dialog = ADialogs.mensajeTurno(context, view);
        dialog.show();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public TextView getIdpiso() {
        return idpiso;
    }

    public void setIdpiso(int idpiso) {
        String namepiso = getPiso(idpiso) + Utilidades.NAMEPISO;
        this.idpiso.setText(namepiso);
    }

    public String getPiso(int idpiso){
        String piso = "";
        switch (idpiso){
            case 1:
                piso = "1er";
                break;
            case 2:
                piso = "2do";
                break;
        }
        return piso;
    }

    public TextView getIdsede() {
        return idsede;
    }

    public void setIdsede(String sede) {
        this.idsede.setText(sede);
    }

    public TextView getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(String horario) {
        this.idhorario.setText(horario);
    }

    public Button getBtnconfirmarreserv() {
        return btnconfirmarreserv;
    }

    public void setBtnconfirmarreserv(Button btnconfirmarreserv) {
        this.btnconfirmarreserv = btnconfirmarreserv;
    }

    public View getViewButtonTicket(){
        return this.view;
    }


    public void onClick(){
        this.btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup) view.getParent()).removeAllViews();
                dialog.dismiss();
            }
        });

        this.btnconfirmarreserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmar();
            }
        });
    }

    public void confirmar(){
        if(InternetConecction.isOnline(getContext())){
            TemplateLoading loading = new TemplateLoading(context);
            loading.setTextLoading("Reservando...");
            AlertDialog dialog_loading = loading.loading();
            dialog_loading.show();
            ((ViewGroup) view.getParent()).removeAllViews();
            dialog.dismiss();
            TurnoFirebase.ticketOnTransaction(dialog_loading, context, databaseReference,
                    Integer.toString(id_piso), Integer.toString(id_turno));
        }else{
            InternetConecction.notInternet(getContext());
        }

    }


}
