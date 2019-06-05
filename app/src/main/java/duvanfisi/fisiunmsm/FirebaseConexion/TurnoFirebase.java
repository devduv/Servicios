package duvanfisi.fisiunmsm.FirebaseConexion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;


import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import java.util.Objects;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Activities.MainActivity;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.Fragments.FReservarTicket;

import duvanfisi.fisiunmsm.Templates.TemplateMessage;

import duvanfisi.fisiunmsm.Templates.PlantillaReservarTicket;
import duvanfisi.fisiunmsm.Model.CTicket;
import duvanfisi.fisiunmsm.Model.CTurno;

import duvanfisi.fisiunmsm.R;

public class TurnoFirebase {

    private Context context;
    private DatabaseReference databaseReference;
    private LinearLayout linearLayout;
    private TextView totalTickets;


    private HashMap<Integer,TextView> textViews;
    private HashMap<Integer, CTurno> cantidad;
    private HashMap<Integer, Button> buttons;
    private int contador;
    private int suma;
    private ProgressBar progressBar;

    private static int idsede;
    private static int idcomida;
    private static String hora_ini;
    private static String hora_fin;

    @SuppressLint("UseSparseArrays")
    public TurnoFirebase(Context context, LinearLayout linearLayout, TextView totalTickets, ProgressBar progressBar){
        this.context = context;

        textViews = new HashMap<>();
        cantidad = new HashMap<>();
        buttons = new HashMap<>();
        this.linearLayout = linearLayout;
        this.totalTickets = totalTickets;
        this.progressBar = progressBar;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        this.databaseReference = firebaseDatabase.getReference();
    }

    private DatabaseReference getDatabaseReference(){
        return this.databaseReference
                .child(Utilidades.BD)
                .child(Utilidades.COMEDOR)
                .child(Utilidades.SEDES);
    }

    public void setDatabaseReference(DatabaseReference databaseReference){
        this.databaseReference = databaseReference;
    }

    public DatabaseReference getTicketsComidaSede(int idsede, int idcomida){
        TurnoFirebase.idsede = idsede;
        TurnoFirebase.idcomida = idcomida;
        DatabaseReference databaseReference = null;
      switch (idsede){
          case 1:
              switch (idcomida){
                  case 1:

                      databaseReference = getDatabaseReference()
                              .child(Utilidades.ID_ONE)
                              .child(Utilidades.ALMUERZO)
                              .child(Utilidades.PISOS);
                      break;
                  case 2:
                      databaseReference = getDatabaseReference()
                              .child(Utilidades.ID_ONE)
                              .child(Utilidades.CENA)
                              .child(Utilidades.PISOS);
                      break;
              }
              break;
          case 2:
              switch (idcomida){
                  case 1:
                      databaseReference = getDatabaseReference()
                              .child(Utilidades.ID_TWO)
                              .child(Utilidades.ALMUERZO)
                              .child(Utilidades.PISOS);
                      break;
                  case 2:
                      databaseReference = getDatabaseReference()
                              .child(Utilidades.ID_TWO)
                              .child(Utilidades.CENA)
                              .child(Utilidades.PISOS);
                      break;
              }
              break;
          case 3:
              switch (idcomida){
                  case 1:
                      databaseReference = getDatabaseReference()
                              .child(Utilidades.ID_THREE)
                              .child(Utilidades.ALMUERZO)
                              .child(Utilidades.PISOS);
                      break;
                  case 2:
                      databaseReference = getDatabaseReference()
                              .child(Utilidades.ID_THREE)
                              .child(Utilidades.CENA)
                              .child(Utilidades.PISOS);
                      break;
              }
              break;
      }

        return databaseReference;
    }

    public void getPisosTurnos() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contador = 0;
                for(DataSnapshot ignored : dataSnapshot.getChildren()){
                    setPisos(contador);
                    contador++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setPisos(final int idpiso){
            databaseReference
                    .child(Integer.toString(idpiso+1))
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        setTotalPisos(idpiso);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }

    private void setTotalPisos(int idpiso){
        LinearLayout linearLayout = new LinearLayout(context);
        setLinearLayout(linearLayout, 10, 10, 0);
        TextView titulo_piso = new TextView(context);
        linearLayout.addView(titulo_piso);

        //add buttons
        setTurnos(linearLayout, idpiso);

        this.linearLayout.addView(linearLayout);
    }

    private void setTurnos(final LinearLayout linearLayout, final int idpiso){
            databaseReference
                    .child(Integer.toString(idpiso+1))
                    .child(Utilidades.TURNOS).addListenerForSingleValueEvent(new ValueEventListener() {
                @SuppressLint("NewApi")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int idturno = 0;
                    for(DataSnapshot aux : dataSnapshot.getChildren()){
                        CTurno  turno = aux.getValue(CTurno.class);
                        String key = (idpiso + 1) + "" + (idturno + 1);

                        PlantillaReservarTicket reservarTicket = new PlantillaReservarTicket(context ,databaseReference);
                        reservarTicket.setIdpiso(idpiso+1);
                        reservarTicket.setIdturno(idturno+1);

                        if (turno != null) {
                            reservarTicket.setIdhorario(turno.getHorario_ini() + " hasta " + turno.getHorario_fin());
                            hora_ini = turno.getHorario_ini();
                            hora_fin = turno.getHorario_fin();
                        }
                        reservarTicket.setIdsede(idsede);
                        cantidad.put(Integer.parseInt(key), turno);
                        textViews.put(Integer.parseInt(key), reservarTicket.getTickets());
                        buttons.put(Integer.parseInt(key), reservarTicket.getBtnreservar());

                        linearLayout.addView(reservarTicket.getViewButtonTicket());
                        idturno++;



                    }

                    FReservarTicket.p_t.setVisibility(ViewVisible.VISIBLE);
                    progressBar.setVisibility(ViewVisible.INVISIBLE);

                    onChangeListener(idpiso+1);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }



    public void onChangeListener(final int _idpiso){
        databaseReference
                .child(Integer.toString(_idpiso))
                .child(Utilidades.TURNOS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for(DataSnapshot aux : dataSnapshot.getChildren()){
                    CTurno turno = aux.getValue(CTurno.class);
                    String key = Integer.toString(_idpiso) + "" + Integer.toString(i+1);

                    if (turno != null) {
                        if(turno.getCantidad()==0){
                            Objects.requireNonNull(buttons.get(Integer.parseInt(key))).setBackgroundTintList(ColorStateList.valueOf(context.getResources()
                            .getColor(R.color.color_hints)));
                        }else{
                            Objects.requireNonNull(buttons.get(Integer.parseInt(key))).setBackgroundTintList(ColorStateList.valueOf(context.getResources()
                                    .getColor(R.color.colorPrimary)));
                        }
                        suma = suma + turno.getCantidad();
                        String cantidad_turno = Integer.toString(turno.getCantidad());
                        textViews.get(Integer.parseInt(key))
                                .setText(cantidad_turno);

                        cantidad.remove(Integer.parseInt(key));
                        cantidad.put(Integer.parseInt(key), turno);
                        i++;
                    }

                }

                if(FReservarTicket.dialog_loading.isShowing()){
                    FReservarTicket.dialog_loading.dismiss();
                }

                suma = 0;
                for(Integer key : cantidad.keySet()){
                    suma = suma + cantidad.get(key).getCantidad();
                }

                String total_Tickets = Integer.toString(suma);
                totalTickets.setText(total_Tickets);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public String getNombrePiso(int idpiso){
        switch (idpiso){
            case 0:
                return "Error aqui";
            case 1:
                return "Primer piso";
            case 2:
                return "Segundo piso";
            case 3:
                return "Tercer piso";
            default:
                return "No hay";
        }
    }

    public void setTexColorView(TextView textView, int size, int color){

        textView.setTextColor(color);
        textView.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setButtonSize(Button button){
        LinearLayout.LayoutParams params =  new LinearLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, 225);
        button.setLayoutParams(params);
        button.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        button.setTypeface(Typeface.create("sans-serif-smallcaps", Typeface.NORMAL));
    }
    public void setLinearLayout(LinearLayout linearLayout, int marginStart, int marginEnd, int marginTop){
        LinearLayout.LayoutParams params =  new LinearLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = 1;
        params.weight = 1;
        params.leftMargin = marginStart;
        params.rightMargin = marginEnd;
        params.topMargin = marginTop;
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
    }

    public void setRelativeLayout(TextView textView){
        LinearLayout.LayoutParams params =  new LinearLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = 1;
        textView.setLayoutParams(params);

    }

    public void setRelativeLayoutC(TextView textView){
        LinearLayout.LayoutParams params =  new LinearLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        textView.setLayoutParams(params);

    }
    public static void ticketOnTransaction(final AlertDialog dialog, final Context context,
                                           DatabaseReference databaseReference,
                                           final String idpiso, final String idturno){

        databaseReference
                .child(idpiso)
                .child(Utilidades.TURNOS)
                .child(idturno)
                .runTransaction(new Transaction.Handler() {
                    @NotNull
                    @Override
                    public Transaction.Result doTransaction(@NotNull MutableData mutableData) {

                        CTurno turno = mutableData.getValue(CTurno.class);

                        if (turno != null) {
                            if(turno.getCantidad()>0){
                                turno.setCantidad(turno.getCantidad() - 1);
                                mutableData.setValue(turno);
                                return Transaction.success(mutableData);
                            }else{
                                return Transaction.abort();
                            }
                        }else{
                            return Transaction.abort();
                        }
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b,
                                           DataSnapshot dataSnapshot) {

                        if(b){
                            /*duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase firebaseDatabase
                                    = new duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase(context);
                            UserFirebase userFirebase = new UserFirebase(firebaseDatabase);
                            //MainActivity.usuario.setT_retirados(MainActivity.usuario.getT_retirados()+1);
                           // userFirebase.setTicketRetirado(MainActivity.usuario.getEmail(), MainActivity.usuario.getT_retirados());
                            CTicket ticket = new CTicket(idcomida, idsede, Integer.parseInt(idpiso),
                                    Integer.parseInt(idturno),
                                    hora_ini, hora_fin, MainActivity.usuario.getCodigo(),
                                    MainActivity.usuario.getEmail(), MainActivity.usuario.getT_retirados());

                            TicketsFirebase ticketsFirebase = new TicketsFirebase(context);
                            DatabaseReference ticketdatabasef = ticketsFirebase.getTicketsComidaSede(idsede, idcomida);
                            ticketsFirebase.setDatabaseReference(ticketdatabasef);
                            ticketsFirebase.registrarTicket(ticket);

                            dialog.dismiss();

                            TemplateMessage mensaje = new TemplateMessage(context);
                            mensaje.setMensajeTicket("Reservar Ticket",
                                    "Ha reservado su ticket satisfactoriamente.",
                                    ticket);*/

                        }else{
                            dialog.dismiss();
                            TemplateMessage mensaje = new TemplateMessage(context);
                            mensaje.setMensaje("Reservar Ticket", "Se acabaron los tickets.");

                        }
                    }
                });
    }
}
