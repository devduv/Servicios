package duvanfisi.fisiunmsm.Templates;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import duvanfisi.fisiunmsm.Extras.ADialogs;
import duvanfisi.fisiunmsm.Extras.ViewFloat;
import duvanfisi.fisiunmsm.R;

public class TemplateLoading {



    private View load_view;
    private Context context;
    private TextView load;
    private LottieAnimationView lottieAnimationView;

    public TemplateLoading(Context context){
        this.context = context;
        this.load_view = ViewFloat.floatview(context, R.layout.loading);
        this.load = this.load_view.findViewById(R.id.loading);
        this.lottieAnimationView = this.load_view.findViewById(R.id.lottie);
        setLootie();
    }


    public AlertDialog loading(){
        AlertDialog dialog = ADialogs.loading(context, load_view);
        return dialog;
    }

    public void setTextLoading(String text){
        load.setText(text);
    }

    public void setLootie(){
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            this.lottieAnimationView.setAnimation("trail_loading.json");
            this.lottieAnimationView.playAnimation();
            this.lottieAnimationView.loop(true);
        }
    }
}
