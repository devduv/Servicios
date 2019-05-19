package duvanfisi.fisiunmsm.Extras;

import android.view.View;

public class ViewVisible {

    public static final int INVISIBLE = View.GONE;
    public static final int VISIBLE = View.VISIBLE;

    public static void viewListener(final View view, long time){
        view.animate()
                .alpha(1f)
                .setDuration(time)
                .setListener(null);
    }
    public static void viewVisible(View view){
        view.setAlpha(0f);
        view.setVisibility(VISIBLE);
    }
}
