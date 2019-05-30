package duvanfisi.fisiunmsm.Extras;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import duvanfisi.fisiunmsm.Actions.Preferences;
import duvanfisi.fisiunmsm.Actions.StartActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.FacultyActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.LoginActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.MainActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.RegisterActivity;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseAccount;
import duvanfisi.fisiunmsm.Fragments.TicketFragment;
import duvanfisi.fisiunmsm.Model.CServicio;
import duvanfisi.fisiunmsm.R;

public class ADialogs {

    public static AlertDialog mensajeTicket(Context context, final View view, final Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);

        builder.setPositiveButton("Ticket", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ((ViewGroup) view.getParent()).removeAllViews();
                MainActivity.startFragment("ticket", new TicketFragment(), 2, bundle);
            }
        });
        //builder.setCancelable(false);
        return builder.create();
    }


    public static AlertDialog mensaje(Context context, final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ((ViewGroup) view.getParent()).removeAllViews();
            }
        });
        //builder.setCancelable(false);
        return builder.create();
    }

    public static AlertDialog mensaje(Context context, final View view, final FirebaseUser firebaseUser) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                firebaseUser.sendEmailVerification();
                ((ViewGroup) view.getParent()).removeAllViews();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((ViewGroup) view.getParent()).removeAllViews();
            }
        });
        return builder.create();
    }

    public static AlertDialog mensaje(final Context context, final View view, final int aux) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);

        switch (aux){
            case 0:
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FirebaseAccount firebaseAccount = new FirebaseAccount(context);
                        firebaseAccount.deleteCuenta(MainActivity.firebaseUser);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((ViewGroup) view.getParent()).removeAllViews();
                    }
                });
                break;
            case 1:
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((Activity) context).finish();
                    }
                });
                break;
            case 2:
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /*FirebaseDatabase firebaseDatabase = new FirebaseDatabase(context);
                        UsuarioFirebase usuarioFirebase = new UsuarioFirebase(firebaseDatabase);
                        usuarioFirebase.setUltimaConexion(MainActivity.usuario.getEmail(), TicketsFirebase.getHoraMedium());*/
                        Preferences.cerrarSesion(context);
                        FirebaseAuth.getInstance().signOut();
                        StartActivity.startActivity(context, new LoginActivity());
                        ((Activity) context).finish();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((ViewGroup) view.getParent()).removeAllViews();
                    }
                });
                break;
            case 3:
                builder.setPositiveButton("Iniciar Sesión", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        StartActivity.startActivity(context, intent);
                    }
                });
                builder.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((ViewGroup) view.getParent()).removeAllViews();
                    }
                });
                break;
            case 5:
                builder.setPositiveButton("Aceptar términos", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RegisterActivity.checkBox.setChecked(true);
                    }
                });
                builder.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((ViewGroup) view.getParent()).removeAllViews();
                    }
                });
                break;
            case 6:
                builder.setPositiveButton("Sí, es mi facultad", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((ViewGroup) view.getParent()).removeAllViews();
                    }
                });
                builder.setNegativeButton("Buscar mi facultad", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //. . .
                        StartActivity.startActivity(context, new FacultyActivity());
                    }
                });
                break;


        }

        //builder.setCancelable(false);
        return builder.create();
    }
    public static AlertDialog info_servicio(final Context context, Bundle bundle) {
        CServicio servicio = bundle.getParcelable("servicio");
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View view_content = ViewFloat.floatview(context, R.layout.mensaje_info);
        TextView titulo_servicio = view_content.findViewById(R.id.titulo_servicio);
        ImageView img_servicio = view_content.findViewById(R.id.img_servicio);
        TextView desc = view_content.findViewById(R.id.descripcion_servicio);
        TextView frecuencia = view_content.findViewById(R.id.frecuencia_servicio);

        titulo_servicio.setText(servicio.getNombre());
        ImagePicasso.setImageCenterCop(context, servicio.getPhoto(), img_servicio);
        desc.setText(servicio.getInformacion());
        frecuencia.setText(servicio.getAdicional());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setView(view_content);
            builder.setPositiveButton("ir a servicios", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    ((ViewGroup)view_content.getParent()).removeAllViews();
                    MainActivity.touch = 3;
                    MainActivity.startFragment("servicios", MainActivity.fservicios, 3);
                    MainActivity.navigation.getMenu().getItem(3).setChecked(true);

                }

            });
            builder.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    ((ViewGroup)view_content.getParent()).removeAllViews();
                }

            });

            return builder.create();
        }else{
            return null;
        }
    }


    public static AlertDialog loading(Context context, final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setCancelable(false);
        return builder.create();
    }

    public static AlertDialog mensajeTurno(Context context, final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
       //builder.setCancelable(false);
        return builder.create();
    }
}
