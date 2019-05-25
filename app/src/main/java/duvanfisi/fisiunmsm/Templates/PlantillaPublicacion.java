package duvanfisi.fisiunmsm.Templates;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.R;

public class PlantillaPublicacion {

    private Context context;
    private View view;
    private TextView nombre;
    private ImageView img;
    private TextView descrp;
    private ImageView imgpub;
    private TextView fecha_pub;
    private TextView date;
    private TextView tit_new_pub;

    public PlantillaPublicacion(Context context, View view){
        this.context = context;
        this.view = view;
        this.img = view.findViewById(R.id.image_user);
        this.nombre = view.findViewById(R.id.name_user);
        this.fecha_pub = view.findViewById(R.id.fecha_pub);
        this.descrp = view.findViewById(R.id.descripcion_pub);
        this.imgpub = view.findViewById(R.id.image_pub);
        this.date = view.findViewById(R.id.fac_user);
        this.tit_new_pub = view.findViewById(R.id.tit_new_pub);
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

   public void setPublicacion(String photo, String nameuser, String fechapub, String date,
                              String descrippub, String img_pub, String tit){
        this.date.setText(date);
       ImagePicasso.setImageCenterCop(this.context, photo, this.img);
       this.nombre.setText(nameuser);
       this.fecha_pub.setText(fechapub);
       this.descrp.setText(descrippub);
       this.tit_new_pub.setText(tit);
       ImagePicasso.setImageCenterInsideWrap(this.context, img_pub, imgpub);
   }
}
