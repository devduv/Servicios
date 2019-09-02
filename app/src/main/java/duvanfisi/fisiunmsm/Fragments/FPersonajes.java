package duvanfisi.fisiunmsm.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import duvanfisi.fisiunmsm.Model.CPersonaje;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewFunction;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewMorePersonajes;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewPersonaje;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewTopPersonajes;


public class FPersonajes extends Fragment {


    private RecyclerView rvpersonajes;
    private RecyclerView rvpersonajestop;
    private RecyclerView rvpersonajesmore;
    private View view;

    private HashMap<Integer, CPersonaje> partone;
    private HashMap<Integer, CPersonaje> parttwo;

    private HashMap<Integer, CPersonaje> top;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_personajes, container, false);

        inicializarViews();
        setPersonajes();
        setPersonajesTop();
        setPersonajesMore();
        return view;
    }

    public void inicializarViews() {
      this.rvpersonajes = view.findViewById(R.id.rvpersonajes);
      this.rvpersonajesmore = view.findViewById(R.id.rvpersonajesmore);
      this.rvpersonajestop = view.findViewById(R.id.rvpersonajes_top);

      partone = new HashMap<>();
      parttwo = new HashMap<>();
      top = new HashMap<>();

      dividid();
    }

    public void dividid(){
        int i=0;
        int contparteone = 0;
        int contparttwo = 0;
        int ctop = 0;

        /*if(FHome.personajeHashMap!=null) {
            HashMap<Integer, CPersonaje> aux = FHome.personajeHashMap;
            int cantidad = FHome.personajeHashMap.size() / 2;
            for (Integer key : FHome.personajeHashMap.keySet()) {
                if (i < cantidad && ctop>=5) {
                    partone.put(contparteone, FHome.personajeHashMap.get(key));
                    contparteone++;
                } else {
                    if (ctop >= 5) {
                        parttwo.put(contparttwo, FHome.personajeHashMap.get(key));
                        contparttwo++;
                    }
                }
                if (ctop < 5) {
                    top.put(ctop, FHome.personajeHashMap.get(key));
                    ctop++;
                }

                i++;
            }

        }*/
    }

    public void setPersonajesMore(){

        RecyclerViewMorePersonajes adapter = new RecyclerViewMorePersonajes(getContext(), partone);
        RecyclerViewFunction.recyclerview(rvpersonajes, getContext(),
                RecyclerViewFunction.HORIZONTAL, adapter);
    }

    public void setPersonajes(){
        RecyclerViewPersonaje recyclerViewPersonaje
                = new RecyclerViewPersonaje
                (getContext(), parttwo, R.layout.plantilla_personaje);
        RecyclerViewFunction.recyclerview
                (rvpersonajesmore, getContext(), RecyclerViewFunction.HORIZONTAL,recyclerViewPersonaje);


    }

    public void setPersonajesTop(){
        RecyclerViewTopPersonajes adapter = new RecyclerViewTopPersonajes(getContext(), top);
        RecyclerViewFunction.recyclerview(rvpersonajestop,
                getContext(),
                RecyclerViewFunction.VERTICAL, adapter);

    }
}
