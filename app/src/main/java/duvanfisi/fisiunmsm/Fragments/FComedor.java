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

import duvanfisi.fisiunmsm.Extras.ADialogs;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Extras.ViewFloat;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Activities.MainActivity;
import duvanfisi.fisiunmsm.Actions.Utilidades;


public class FComedor extends Fragment {


    private View view;

    private View info;

    private CardView btncu;
    private CardView btnsf;
    private CardView btnsjl;

    private ImageView img_serv_comedor;
    private ImageView img_sede_sf;
    private ImageView img_sede_sjl;

    private boolean selectedcu;
    private boolean selectedsf;
    private boolean selectedsjl;

    private Button info_sedes;

    private int sede_selected;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_comedor, container, false);
        this.inicializarViews();
        this.OnClickButtons();
        return this.view;
    }

    public void inicializarViews(){
        selectedcu = false;
        selectedsf = false;
        selectedsjl = false;
        info = ViewFloat.floatview(getContext(), R.layout.info_servicio);
        this.info_sedes = view.findViewById(R.id.info_sedes);
        this.btncu = view.findViewById(R.id.btncu);
        this.btnsf = view.findViewById(R.id.btnsf);
        this.btnsjl = view.findViewById(R.id.btnsjl);

        this.img_serv_comedor = view.findViewById(R.id.img_serv_comedor);
        this.img_sede_sf = view.findViewById(R.id.img_serv_comedor2);
        this.img_sede_sjl = view.findViewById(R.id.img_serv_comedor3);

    }

    public Bundle getBundle(int _idsede){
        Bundle bundle = new Bundle();
        bundle.putInt(Utilidades.IDSEDE, _idsede);
        return bundle;
    }

    public void OnClickButtons(){

        sede_univ(btncu, img_serv_comedor);
        sede_univ(btnsf, img_sede_sf);
        sede_univ(btnsjl, img_sede_sjl);
        this.info_sedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfoSedes();
            }
        });

    }

public void sede_univ(final CardView button, final ImageView imageView) {
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (button.getId()) {
                case R.id.btncu:
                    MainActivity.startFragment("elegir turno", new FSeleccionarComida(), 3, getBundle(1));
                    break;
                case R.id.btnsf:
                    MainActivity.startFragment("elegir turno", new FSeleccionarComida(), 3, getBundle(2));
                    break;
                case R.id.btnsjl:
                    MainActivity.startFragment("elegir turno", new FSeleccionarComida(), 3, getBundle(3));
                    break;
            }

        }
    });

    imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (imageView.getId()) {
                case R.id.img_serv_comedor:
                    if(selectedcu){
                        selectedcu = false;
                        ImagePicasso.setImageCenterCop(getContext(), R.drawable.icon_love, img_serv_comedor);
                    }else{
                        selectedcu = true;
                        ImagePicasso.setImageCenterCop(getContext(), R.drawable.icon_love_selected, img_serv_comedor);
                    }

                    break;
                case R.id.img_serv_comedor2:
                    if(selectedsf){
                        selectedsf = false;
                        ImagePicasso.setImageCenterCop(getContext(), R.drawable.icon_love, img_sede_sf);
                    }else{
                        selectedsf = true;
                        ImagePicasso.setImageCenterCop(getContext(), R.drawable.icon_love_selected, img_sede_sf);
                    }

                    break;
                case R.id.img_serv_comedor3:
                    if(selectedsjl){
                        selectedsjl = false;
                        ImagePicasso.setImageCenterCop(getContext(), R.drawable.icon_love, img_sede_sjl);
                    }else{
                        selectedsjl = true;
                        ImagePicasso.setImageCenterCop(getContext(), R.drawable.icon_love_selected, img_sede_sjl);
                    }

                    break;
            }
        }
    });
}

    public void dialogInfoSedes(){
        AlertDialog dialog = ADialogs.mensaje(getContext(), info);
        dialog.show();
    }


}
