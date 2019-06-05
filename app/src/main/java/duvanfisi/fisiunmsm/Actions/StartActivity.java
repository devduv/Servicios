package duvanfisi.fisiunmsm.Actions;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

import duvanfisi.fisiunmsm.Model.CUsuario;
import duvanfisi.fisiunmsm.Model.Users.CStudent;
import duvanfisi.fisiunmsm.Model.Users.CUser;


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

    public static void startActivity(Context context, AppCompatActivity activity,
                                     FirebaseUser firebaseuser, CStudent student){
        Intent intent = new Intent(context, activity.getClass());
        intent.putExtra(Utilidades.FIREBASEUSER, firebaseuser);
        intent.putExtra(Utilidades.KEY_MODEL_USER, student);
        context.startActivity(intent);
    }



    public static void startActivity(Context context, Intent intent){
        context.startActivity(intent);
    }

}
