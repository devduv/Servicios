package duvanfisi.fisiunmsm.Actions;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import duvanfisi.fisiunmsm.ViewLayouts.PlantillaMensaje;
import duvanfisi.fisiunmsm.R;

public class InternetConecction {
    private static ConnectivityManager manager;

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    public static void nointernet(Context context){
        PlantillaMensaje mensaje = new PlantillaMensaje(context);
        mensaje.setMensaje("Reservar Ticket", "Conéctese a internet.");
        mensaje.setBackgroundColor(context.getResources().getColor(R.color.color_rose));
    }
}
