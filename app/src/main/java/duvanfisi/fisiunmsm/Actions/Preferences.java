package duvanfisi.fisiunmsm.Actions;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Preferences {

    static boolean isLogin(Context context){
        SharedPreferences preferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);

        return preferences.getBoolean("saved", false);

    }

    static String getUserCurrent(Context context){
        SharedPreferences preferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        return preferences.getString("user", null);

    }
    public static void guardarPreferencias(Context context, FirebaseUser firebaseUser){
        SharedPreferences preferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("saved", true);
        editor.putString("user", firebaseUser.getUid());
        editor.apply();
    }

    public static void cerrarSesion(Context context){
        SharedPreferences preferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("saved", false);
        editor.apply();
    }

    public static void fisrtTime(Context context, boolean bool){
        SharedPreferences preferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("fisrt", bool);
        editor.apply();
    }
    public static boolean getFirstTime(Context context){
        SharedPreferences preferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        return preferences.getBoolean("fisrt", true);

    }
}
