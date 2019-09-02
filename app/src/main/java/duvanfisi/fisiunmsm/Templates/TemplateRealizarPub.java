package duvanfisi.fisiunmsm.Templates;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import duvanfisi.fisiunmsm.Activities.MainActivity;
import duvanfisi.fisiunmsm.Extras.DisplayMetric;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.R;

public class TemplateRealizarPub {
    private Context context;

    private View view_realizar_pub;

    private ImageView photo;
    private EditText input_pub;

    private CardView container_pub;

    public TemplateRealizarPub(Context context, View view_realizar_pub){
        this.context = context;

        this.view_realizar_pub = view_realizar_pub;

        this.photo = this.view_realizar_pub.findViewById(R.id.image_user);
        this.input_pub = this.view_realizar_pub.findViewById(R.id.input_pub);
        this.container_pub = this.view_realizar_pub.findViewById(R.id.card_realizar_pub);

        //this.container_pub.setVisibility(ViewVisible.INVISIBLE);

        this.touchListener();

    }


    public void setPhoto(String photo){
        ImagePicasso.setImageCenterInside(this.context, photo, this.photo);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void touchListener(){
        input_pub.setFocusableInTouchMode(false);

        input_pub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                input_pub.setFocusableInTouchMode(true);
                return false;
            }
        });




    }
}
