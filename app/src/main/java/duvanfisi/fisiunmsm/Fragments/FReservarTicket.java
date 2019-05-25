package duvanfisi.fisiunmsm.Fragments;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.FirebaseConexion.TurnoFirebase;
import duvanfisi.fisiunmsm.Templates.PlantillaLoading;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Actions.Utilidades;


public class FReservarTicket extends Fragment {

    private ProgressBar progressBar;

    private View view;
    public static ConstraintLayout p_t;
    private View view_ticket_acp;

    private LinearLayout turnos_ticket;

   // private CUsuario usuario;
    private int idsede;
    private int idcomida;

    private TextView title_sede_c_s;
    private TextView title_comida_selected;

    private TurnoFirebase turnoFirebase;

    public static AlertDialog dialog_loading;

    private TextView totalTickets;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_comselec, container, false);
        this.view_ticket_acp = inflater.inflate(R.layout.mensaje, container, false);
        this.inicializarViews();
        return this.view;
    }

    public void inicializarViews(){
        PlantillaLoading loading = new PlantillaLoading(getContext());
        loading.setTextLoading("Buscando tickets...");
        dialog_loading = loading.loading();
        dialog_loading.show();

        p_t = view.findViewById(R.id.contraint_p_t);
        p_t.setVisibility(ViewVisible.INVISIBLE);

        this.turnos_ticket = this.view.findViewById(R.id.turnos_ticket);
        this.title_comida_selected = this.view.findViewById(R.id.title_comida_selected);
        this.title_sede_c_s = this.view.findViewById(R.id.title_sede_c_s);
        this.totalTickets = this.view.findViewById(R.id.txtTotalT);
        this.progressBar = this.view.findViewById(R.id.progress_tickets);

        this.setBundle();
        this.setSede();
        this.setComida();


        this.turnoFirebase = new TurnoFirebase(getContext(), turnos_ticket, totalTickets, progressBar);


        setTotalTickets();

    }

    public void setTotalTickets(){
         databaseReference = this.turnoFirebase.getTicketsComidaSede(idsede,idcomida);
         this.turnoFirebase.setDatabaseReference(databaseReference);
         this.turnoFirebase.getPisosTurnos();


    }
    public void setBundle(){
        idsede = getArguments().getInt(Utilidades.IDSEDE);
        idcomida = getArguments().getInt(Utilidades.IDCOMIDA);

    }

    public void setComida(){
        switch (idcomida){
            case 1:
                this.title_comida_selected.setText("Almuerzo, seleccione un turno");
                break;
            case 2:
                this.title_comida_selected.setText("Cena, seleccione un turno");
                break;
        }
    }

    public void setSede(){
        switch (idsede){
            case 1:
                this.title_sede_c_s.setText(Utilidades.CU);
                break;
            case 2:
                this.title_sede_c_s.setText(Utilidades.SF);
                break;
            case 3:
                this.title_sede_c_s.setText(Utilidades.SJL);
                break;
        }

    }
}
