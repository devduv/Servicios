package duvanfisi.fisiunmsm.ViewLayouts;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Extras.ViewFloat;
import duvanfisi.fisiunmsm.Modelo.CConfirmarReservarTicket;
import duvanfisi.fisiunmsm.R;

public class PlantillaReservarTicket {

    private Context context;
    private View view;

    private TextView idpiso;
    private TextView idturno;
    private TextView tickets;
    private TextView idsede;
    private TextView idhorario;
    private Button btnreservar;

    private int id_piso;
    private int id_turno;

    private String horario;
    private DatabaseReference databaseReference;
    public PlantillaReservarTicket(Context context, DatabaseReference databaseReference){
        this.context = context;
        this.databaseReference = databaseReference;
        this.view = ViewFloat.floatview(this.context, R.layout.button_reservar_ticket);

        this.btnreservar = this.view.findViewById(R.id.btnreservart);
        this.idpiso = this.view.findViewById(R.id.idbtnpiso);
        this.idturno = this.view.findViewById(R.id.idbtnturno);
        this.tickets = this.view.findViewById(R.id.cbtntickets);
        this.idsede = this.view.findViewById(R.id.idbtnsede);
        this.idhorario = this.view.findViewById(R.id.idbtnhorario);
        this.setHorario();
        OnClick();
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
        this.id_piso = idpiso;
        String namepiso = getPiso(idpiso) + Utilidades.NAMEPISO;
        this.idpiso.setText(namepiso);
    }

    public void setHorario(){
        this.horario = this.idhorario.getText().toString();
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

    public TextView getIdturno() {
        return idturno;
    }

    public void setIdturno(int turno) {
        this.id_turno = turno;
        String nameturno = Utilidades.NAMETURNO+turno;
        this.idturno.setText(nameturno);
    }

    public TextView getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets.setText(tickets);
    }

    public TextView getIdsede() {
        return idsede;
    }

    public void setIdsede(int idsede) {
        String sede = getSede(idsede);
        this.idsede.setText(sede);
    }

    public TextView getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(String horario) {
        this.idhorario.setText(horario);
    }

    public Button getBtnreservar() {
        return btnreservar;
    }

    public void setBtnreservar(Button btnreservar) {
        this.btnreservar = btnreservar;
    }

    public View getViewButtonTicket(){
        return this.view;
    }

    public String getSede(int idsede){
        String sede = "";
        switch (idsede){
            case 1:
                sede = Utilidades.CU;
                break;
            case 2:
                sede = Utilidades.SF;
            case 3:
                sede = Utilidades.SJL;
        }
        return  sede;
    }

    public void OnClick(){
        this.btnreservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarConfirmar();
            }
        });
    }

    public void mostrarConfirmar(){
        CConfirmarReservarTicket cConfirmarReservarTicket =
                new CConfirmarReservarTicket(context, id_piso, id_turno ,databaseReference);
        cConfirmarReservarTicket.setIdhorario(this.getIdhorario().getText().toString());
        cConfirmarReservarTicket.setIdsede(this.getIdsede().getText().toString());
        cConfirmarReservarTicket.mostrarDialogMensaje();
    }
}
