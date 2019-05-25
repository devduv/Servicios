package duvanfisi.fisiunmsm.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.ChangePasswordActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.MainActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.PhoneActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.RegisterDatosActivity;
import duvanfisi.fisiunmsm.Templates.TemplateMessage;
import duvanfisi.fisiunmsm.Modelo.CUsuario;
import duvanfisi.fisiunmsm.R;


public class FPerfil extends Fragment {

    private TextView datos_name;
    private TextView dato_ap;
    private TextView dato_am;
    private TextView dato_cod;
    private TextView dato_email;
    private TextView dato_nc;
    private TextView dato_fac;
    private TextView dato_esc;
    private TextView dato_e;
    private TextView dato_sede;


    private Button button_change_pass;
    private Button button_change_phone;
    private Button button_delete;

    private View view;
    CUsuario usuario;
    public FPerfil() {

    }



    public void inicializarViews(){
        datos_name = view.findViewById(R.id.dato_names);
        dato_ap = view.findViewById(R.id.dato_ap);
        dato_am = view.findViewById(R.id.dato_am);
        dato_cod = view.findViewById(R.id.dato_cod);
        dato_email = view.findViewById(R.id.dato_email);
        dato_nc = view.findViewById(R.id.dato_nc);
        dato_fac = view.findViewById(R.id.dato_fac);
        dato_esc = view.findViewById(R.id.dato_esc);
        dato_e =view.findViewById(R.id.dato_e);
        dato_sede = view.findViewById(R.id.dato_sede);
        button_change_pass = view.findViewById(R.id.button_change_pass);
        button_change_phone = view.findViewById(R.id.button_change_phone);
        button_delete = view.findViewById(R.id.button_delete);
        setUserDates();
    }


    public void setUserDates(){
        datos_name.setText(usuario.getNombre());
        dato_ap.setText(usuario.getAp_paterno());
        dato_am.setText(usuario.getAp_materno());
        dato_cod.setText(Integer.toString(usuario.getCodigo()));
        dato_email.setText(usuario.getEmail());
        if(usuario.getPhone().equalsIgnoreCase("null")){
            dato_nc.setText("Sin registrar");
        }else{
            dato_nc.setText(usuario.getPhone());
        }

        dato_fac.setText(usuario.getFacultad());
        dato_esc.setText(usuario.getEscuela());
        dato_e.setText(usuario.getTipo_usuario());
        dato_sede.setText(usuario.getSede());
    }

    public void OnClickButtons(){
        button_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              StartActivity.startActivity(getContext(), new ChangePasswordActivity());
            }
        });
        button_change_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPhone();
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAcc();
            }
        });
    }

    public void startPhone(){
        StartActivity.startActivity(getContext(), new PhoneActivity(),MainActivity.firebaseUser);
    }

    public void startRegisterDates(){
        StartActivity.startActivity(getContext(), new RegisterDatosActivity(),MainActivity.firebaseUser);
    }

    public void deleteAcc(){

        TemplateMessage mensaje = new TemplateMessage(getContext());
        mensaje.setMensaje("Eliminar cuenta", "¿Enserio quieres eliminar esta cuenta?",0);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_miperfil, container, false);

        usuario = MainActivity.usuario;

        inicializarViews();
        OnClickButtons();
        return view;
    }


}
