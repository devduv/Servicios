package duvanfisi.fisiunmsm.Templates;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Extras.ADialogs;
import duvanfisi.fisiunmsm.Extras.ViewFloat;
import duvanfisi.fisiunmsm.Model.CTicket;
import duvanfisi.fisiunmsm.R;

public class TemplateMessage {

    private Context context;
    private View mensaje;
    private TextView title_mensaje;
    private TextView content_mensaje;

    public TemplateMessage(Context context){
        this.context = context;
        this.mensaje = ViewFloat.floatview(this.context, R.layout.template_message);
        this.title_mensaje = mensaje.findViewById(R.id.title_mensaje);
        this.content_mensaje = mensaje.findViewById(R.id.content_mensaje);
    }

    public void setMensaje(String title, String cont){
        this.title_mensaje.setText(title);
        this.content_mensaje.setText(cont);

        mostrarDialogMensaje();
    }

    public void setBackgroundColor(int color){
        this.title_mensaje.setBackgroundColor(color);
    }

    private void mostrarDialogMensaje(){
        AlertDialog dialog = ADialogs.mensaje(context, mensaje);
        dialog.show();
    }

    public void setMensaje(String title, String cont, int close){
        this.title_mensaje.setText(title);
        this.content_mensaje.setText(cont);

        mostrarDialogMensaje(close);
    }
    public void setMensaje(String title, String cont, FirebaseUser firebaseUser){
        this.title_mensaje.setText(title);
        this.content_mensaje.setText(cont);

        mostrarDialogMensaje(firebaseUser);
    }

    public void setMensajeTicket(String title, String cont, CTicket ticket){
        this.title_mensaje.setText(title);
        this.content_mensaje.setText(cont);

        mostrarDialogMensajeTicket(ticket);
    }

    private void mostrarDialogMensaje(int close){
        AlertDialog dialog = ADialogs.mensaje(context, mensaje, close);
        dialog.show();
    }

    private void mostrarDialogMensaje(FirebaseUser firebaseUser){
        AlertDialog dialog = ADialogs.mensaje(context, mensaje, firebaseUser);
        dialog.show();
    }
    private void mostrarDialogMensajeTicket(CTicket ticket){

        Bundle bundle = new Bundle();
        bundle.putParcelable(Utilidades.TICKETKEY, ticket);
        AlertDialog dialog = ADialogs.mensajeTicket(context, mensaje, bundle);
        dialog.show();
    }
}
