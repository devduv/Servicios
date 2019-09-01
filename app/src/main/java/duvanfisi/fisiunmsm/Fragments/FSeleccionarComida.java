package duvanfisi.fisiunmsm.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import duvanfisi.fisiunmsm.Actions.InternetConecction;
import duvanfisi.fisiunmsm.Actions.StartFragment;
import duvanfisi.fisiunmsm.Model.Users.CStudent;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Activities.MainActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;


public class FSeleccionarComida extends Fragment {


    private View view;


    private Button btnalmuerzo;
    private Button btncena;

    private CStudent user;
    private FirebaseUser firebaseUser;
    private Bundle bundle;

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

        this.getUserCurrent();
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
            startFragment("tickets", new FReservarTicket(),  getBundle(idsede, this.idcomida));
        }else{
            InternetConecction.notInternet(getContext());
        }



    }

    public void startFragment(String name, Fragment fragment, Bundle bundle){
        StartFragment.startFragment(name, fragment, bundle);
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
        bundle.putParcelable(Utilidades.KEY_MODEL_USER, user);
        bundle.putInt(Utilidades.IDSEDE, _idsede);
        bundle.putInt(Utilidades.IDCOMIDA, comida);
        return bundle;
    }

    public void getUserCurrent(){
        bundle = getArguments();

        firebaseUser = bundle.getParcelable(Utilidades.FIREBASEUSER);
        user = bundle.getParcelable(Utilidades.KEY_MODEL_USER);
        idsede = bundle.getInt(Utilidades.IDSEDE);
    }

}
