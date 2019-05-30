package duvanfisi.fisiunmsm.Actions;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

import duvanfisi.fisiunmsm.Model.CUsuario;


public class StartActivity {



    public static void startActivity(Context context, AppCompatActivity activity){
        Intent intent = new Intent(context, activity.getClass());
        context.startActivity(intent);
    }


    public static void startActivity(Context context, FragmentActivity activity){
        Intent intent = new Intent(context, activity.getClass());
        context.startActivity(intent);
    }


    public static void startActivity(Context context, AppCompatActivity activity, FirebaseUser user){
        Intent intent = new Intent(context, activity.getClass());
        intent.putExtra(Utilidades.FIREBASEUSER, user);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, AppCompatActivity activity, FirebaseUser user, CUsuario usuario){
        Intent intent = new Intent(context, activity.getClass());
        intent.putExtra(Utilidades.FIREBASEUSER, user);
        intent.putExtra(Utilidades.KEY_MODEL_USER, usuario);
        context.startActivity(intent);
    }



    public static void startActivity(Context context, Intent intent){
        context.startActivity(intent);
    }

}
