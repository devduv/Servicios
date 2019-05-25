package duvanfisi.fisiunmsm.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import duvanfisi.fisiunmsm.Actions.InternetConecction;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.ActivitiesUsers.MainActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;


public class FSeleccionarComida extends Fragment {


    private View view;


    private Button btnalmuerzo;
    private Button btncena;

    //private CUsuario usuario;
    private int idsede;
    private int idcomida;

    private TextView title_sede;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_seleccomida, container, false);
        this.inicializarViews();
        this.OnClickButtons();
        return this.view;
    }

    public void inicializarViews(){

        this.btnalmuerzo = view.findViewById(R.id.btnalmuerzo);
        this.btncena = view.findViewById(R.id.btncena);

        this.title_sede = view.findViewById(R.id.title_sede);


        setBundle();
        setSede();

    }

    public void OnClickButtons(){
        this.btnalmuerzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irComidaSeleccionada(idsede, 1);
                     }
        });

        this.btncena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irComidaSeleccionada(idsede, 2);
            }
        });
    }

    public void irComidaSeleccionada(int idsede, int idcomida){
        if(InternetConecction.isOnline(getContext())){
            this.idcomida = idcomida;
            MainActivity.startFragment("tickets", new FReservarTicket(), 3,  getBundle(idsede, this.idcomida));
        }else{
            InternetConecction.notInternet(getContext());
        }



    }
    public void setBundle(){
        //usuario = getArguments().getParcelable(Utilidades.KEY_MODEL_USER);
        idsede = getArguments().getInt(Utilidades.IDSEDE);
    }

    public void setSede(){
        switch (idsede){
            case 1:
                this.title_sede.setText("Ciudad Universitaria");
                break;
            case 2:
                this.title_sede.setText("San Fernando");
                break;
            case 3:
                this.title_sede.setText("San Juan de Lurigancho");
                break;
        }

    }

    public Bundle getBundle(int _idsede, int comida){
        Bundle bundle = new Bundle();
        //bundle.putParcelable(Utilidades.KEY_MODEL_USER, usuario);
        bundle.putInt(Utilidades.IDSEDE, _idsede);
        bundle.putInt(Utilidades.IDCOMIDA, comida);
        return bundle;
    }

}
