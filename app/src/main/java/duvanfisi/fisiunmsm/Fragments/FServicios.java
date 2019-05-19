package duvanfisi.fisiunmsm.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import duvanfisi.fisiunmsm.Extras.ADialogs;
import duvanfisi.fisiunmsm.ViewLayouts.PlantillaMensaje;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Extras.ViewFloat;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.ActivitiesUsers.MainActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;


public class FServicios extends Fragment {

    private View view;

    private Button btn_info_services;
    private Button btn_formato_services;

    private View info;

   // private CUsuario usuario;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_servicios, container, false);
        this.inicializarViews();
        this.onClicks();
        return this.view;
    }

    public void inicializarViews(){

        if (getArguments() != null) {
          //  usuario = getArguments().getParcelable(Utilidades.KEY_MODEL_USER);
        }

        View view_s_comedor = view.findViewById(R.id.card_service_comedor);
        View view_s_transporte = view.findViewById(R.id.card_service_transporte);


        this.btn_info_services = view.findViewById(R.id.btn_info_service);
        this.btn_formato_services = view.findViewById(R.id.btn_formato_service);

        info = ViewFloat.floatview(getContext(), R.layout.info_servicio);

        setViewsCard(view_s_comedor, new FComedor(), 3, R.id.card_service_comedor);
        setViewsCard(view_s_transporte, null, 0, R.id.card_service_transporte);
    }

    public void setViewsCard(View view, Fragment fragment, int id, int service_selected){
        CardView btns = view.findViewById(R.id.card_service);
        Button btnserv = view.findViewById(R.id.btnirserv);
        ImageView img_services = view.findViewById(R.id.img_serv);
        TextView title_service = view.findViewById(R.id.title_serv);
        ImageView info_service = view.findViewById(R.id.info_serv);
        TextView subtitle_service = view.findViewById(R.id.subtitle_serv);
        TextView subsubtitle_service = view.findViewById(R.id.subsubtitle_serv);

        if (service_selected == R.id.card_service_comedor) {
            ImagePicasso.setImageCenterCop(getContext(), R.drawable.ic_comedor, img_services);
            title_service.setText(Utilidades.TITLE_SERV_COM);
            subtitle_service.setText(Utilidades.SUBTITLE_SERV_COM);
            subsubtitle_service.setText(Utilidades.SUBSUBTITLE_SERV_COM);

            onClickButtonsViewCard(service_selected, btnserv, btns, info_service, fragment, id);
        }
        if(service_selected == R.id.card_service_transporte){
            ImagePicasso.setImageCenterCop(getContext(), R.drawable.icon_transporte, img_services);
            title_service.setText(Utilidades.TITLE_SERV_TRANSPORTE);
            subtitle_service.setText(Utilidades.SUBTITLE_SERV_TRANSPORTE);
            subsubtitle_service.setText(Utilidades.SUBSUBTITLE_SERV_TRANSPORTE);
            onClickButtonsViewCard(service_selected, btnserv, btns, info_service, fragment, id);
        }


    }

    public void onClickButtonsViewCard(final int selected, final Button btnirserv, final CardView btns, ImageView info_service,
                                       final Fragment fragment, final int id){

        btnirserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (selected){
                    case R.id.card_service_comedor:
                        if (fragment != null) {
                            if(MainActivity.firebaseUser!=null) {
                                MainActivity.startFragment("Servicio elegido", fragment, id, new Bundle());
                            }else{
                                PlantillaMensaje mensaje = new PlantillaMensaje(getContext());
                                mensaje.setMensaje("Iniciar Sesión", "No ha iniciado sesión");
                            }
                        }
                        break;
                    case R.id.card_service_transporte:
                        PlantillaMensaje mensaje = new PlantillaMensaje(getContext());
                        mensaje.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
                        mensaje.setMensaje("Transporte", "Próximamente");
                        break;
                }

            }
        });
        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (selected){
                    case R.id.card_service_comedor:
                        if (fragment != null) {
                            if(MainActivity.firebaseUser!=null) {
                                MainActivity.startFragment("Servicio elegido", fragment, id, new Bundle());
                            }else{
                                PlantillaMensaje mensaje = new PlantillaMensaje(getContext());
                                mensaje.setMensaje("Iniciar Sesión", "No ha iniciado sesión");
                            }
                        }
                        break;
                    case R.id.card_service_transporte:
                        PlantillaMensaje mensaje = new PlantillaMensaje(getContext());
                        mensaje.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
                        mensaje.setMensaje("Transporte", "Próximamente");
                        break;
                }

            }
        });

        info_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogInfoService();
            }
        });
    }

    public void onClicks(){


        this.btn_formato_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogInfoService();
            }
        });

        this.btn_info_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogInfoService();
            }
        });
    }


    public void mostrarDialogInfoService(){
        AlertDialog dialog = ADialogs.mensaje(getContext(), info);
        dialog.show();
    }

    /*public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putParcelable(Utilidades.KEY_MODEL_USER, usuario);
        return bundle;
    }*/


}
