package duvanfisi.fisiunmsm.Animation;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import duvanfisi.fisiunmsm.Extras.DisplayMetric;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.ActivitiesUsers.LoginActivity;


public class AnimationLogin implements Animation.AnimationListener {

    private Context context;
    public AnimationLogin(Context context){
        this.context = context;
    }

    public void splash(){
        Animation moveLogoAnimation =
                AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.move_logo);
        moveLogoAnimation.setFillAfter(true);
        moveLogoAnimation.setAnimationListener(this);
        LoginActivity.splash.startAnimation(moveLogoAnimation);


        LoginActivity.activityRootView.getViewTreeObserver().addOnGlobalLayoutListener
                (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(LoginActivity.animation_ended){
                    int heightDiff = LoginActivity.activityRootView.getRootView().getHeight() - LoginActivity.activityRootView.getHeight();
                    if(heightDiff >  DisplayMetric.dpToPx(context, 200)){
                        LoginActivity.splash.setVisibility(ViewVisible.INVISIBLE);
                        LoginActivity.buttnRegister.setVisibility(ViewVisible.INVISIBLE);
                        LoginActivity.buttnForgot.setVisibility(ViewVisible.INVISIBLE);
                        LoginActivity.linearlogin.setVisibility(ViewVisible.INVISIBLE);
                        LoginActivity.view.setBackgroundColor(context.getResources().getColor(R.color.color_background));
                    }else{
                        LoginActivity.emailt.setFocusable(false);
                        LoginActivity.pass.setFocusable(false);
                        LoginActivity.view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        LoginActivity.buttnRegister.setVisibility(ViewVisible.VISIBLE);
                        LoginActivity.buttnForgot.setVisibility(ViewVisible.VISIBLE);
                        LoginActivity.linearlogin.setVisibility(ViewVisible.VISIBLE);
                    }
                }
            }
        });
    }
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        LoginActivity.splash.setVisibility(ViewVisible.INVISIBLE);
        ViewVisible.viewVisible(LoginActivity.buttnRegister);
        ViewVisible.viewVisible(LoginActivity.linearlogin);
        ViewVisible.viewVisible(LoginActivity.buttnForgot);
        ViewVisible.viewVisible(LoginActivity.buttonLogin);
        ViewVisible.viewVisible(LoginActivity.emailt);
        ViewVisible.viewVisible(LoginActivity.inputpass);
        ViewVisible.viewVisible(LoginActivity.pass);
        ViewVisible.viewVisible(LoginActivity.view);


        LoginActivity.emailt.setHint("example@unmsm.edu.pe");
        LoginActivity.pass.setHint("Contraseña");

        int shortAnimationTime = context.getResources().getInteger(android.R.integer.config_shortAnimTime);
        ViewVisible.viewListener(LoginActivity.buttnRegister, shortAnimationTime);
        ViewVisible.viewListener(LoginActivity.linearlogin, shortAnimationTime);
        ViewVisible.viewListener(LoginActivity.buttnForgot, shortAnimationTime);
        ViewVisible.viewListener(LoginActivity.buttonLogin, shortAnimationTime);
        ViewVisible.viewListener(LoginActivity.inputpass, shortAnimationTime);
        ViewVisible.viewListener(LoginActivity.pass, shortAnimationTime);
        ViewVisible.viewListener(LoginActivity.emailt, shortAnimationTime);
        ViewVisible.viewListener(LoginActivity.view, shortAnimationTime);

        LoginActivity.animation_ended = true;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
