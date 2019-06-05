package duvanfisi.fisiunmsm.FirebaseConexion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Activities.MainActivity;
import duvanfisi.fisiunmsm.Activities.MiRegistroActivity;
import duvanfisi.fisiunmsm.Extras.ViewVisible;
import duvanfisi.fisiunmsm.Model.CTicket;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewFunction;
import duvanfisi.fisiunmsm.Recyclers.RecyclerViewTickets;

public class TicketsFirebase {

    private Context context;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase fd;
    private UserFirebase userFirebase;


    public TicketsFirebase(Context context){
        this.firebaseDatabase = FirebaseDatabase.getInstance();
        this.databaseReference = firebaseDatabase.getReference();
        this.context = context;
        fd = new duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase(this.context);
        this.userFirebase = new UserFirebase(fd);
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
        //this.idsede = idsede;
        DatabaseReference databaseReference = null;
        switch (idsede){
            case 1:
                switch (idcomida){
                    case 1:
                        databaseReference = getDatabaseReference()
                                .child(Utilidades.ID_ONE)
                                .child(Utilidades.ALMUERZO)
                                .child(Utilidades.REGISTRO);
                        break;
                    case 2:
                        databaseReference = getDatabaseReference()
                                .child(Utilidades.ID_ONE)
                                .child(Utilidades.CENA)
                                .child(Utilidades.REGISTRO);
                        break;
                }
                break;
            case 2:
                switch (idcomida){
                    case 1:
                        databaseReference = getDatabaseReference()
                                .child(Utilidades.ID_TWO)
                                .child(Utilidades.ALMUERZO)
                                .child(Utilidades.REGISTRO);
                        break;
                    case 2:
                        databaseReference = getDatabaseReference()
                                .child(Utilidades.ID_TWO)
                                .child(Utilidades.CENA)
                                .child(Utilidades.REGISTRO);
                        break;
                }
                break;
            case 3:
                switch (idcomida){
                    case 1:
                        databaseReference = getDatabaseReference()
                                .child(Utilidades.ID_THREE)
                                .child(Utilidades.ALMUERZO)
                                .child(Utilidades.REGISTRO);
                        break;
                    case 2:
                        databaseReference = getDatabaseReference()
                                .child(Utilidades.ID_THREE)
                                .child(Utilidades.CENA)
                                .child(Utilidades.REGISTRO);
                        break;
                }
                break;
        }

        return databaseReference;

    }

    public void registrarTicket(CTicket ticket){

        ticket.setFecha_retiro(getFechaFull());
        ticket.setHora_retiro(getHoraMedium());
        /*ticket.setNombre_usuario(MainActivity.usuario.getNombre());
        ticket.setAp_paterno_usuario(MainActivity.usuario.getAp_paterno());
        ticket.setAp_materno_usuario(MainActivity.usuario.getAp_materno());*/

        ticket.key_ticket(getFechaShort());
        String id = ticket.get_id();
        this.databaseReference
                .child(key_ticket())
                .child(id)
                .setValue(ticket);
        this.userFirebase.registrarTicket(ticket.getEmail(), ticket);

    }

    public String getFechaFull() {
        Date date = new Date();
        return DateFormat.getDateInstance(DateFormat.FULL).format(date);
    }

    public static String getHoraMedium() {
        Date date = new Date();
        return DateFormat.getTimeInstance(DateFormat.MEDIUM).format(date);
    }

    public String getFechaShort() {
        Date date = new Date();
        String datetoday = DateFormat.getDateInstance(DateFormat.SHORT).format(date);
        return  datetoday;
    }

    public String key_ticket(){
        String fecha_six[] = this.getFechaShort().split("/");

        String six = fecha_six[0] + "_"+ fecha_six[1] + "_"+fecha_six[2];

        return six;

    }

    public void setTicketUser(final RecyclerView recyclerView, final HashMap<Integer, CTicket> cTicketHashMap, String email) {
        CollectionReference collectionReference = userFirebase.getRegistroUserTicket(email);

        collectionReference.orderBy("cod", Query.Direction.DESCENDING).limit(2)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot querySnapshot,
                                        @Nullable FirebaseFirestoreException e) {
                        /*if (e != null) {
                            return;
                        }

                        if (querySnapshot != null) {
                            int i = 0;
                            for (QueryDocumentSnapshot change : querySnapshot) {
                                if (change != null && change.exists()) {
                                    cTicketHashMap.put(i, change.toObject(CTicket.class));
                                    i++;
                                }
                            }
                            duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase firebaseDatabase = new duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase(context);
                            MainActivity.usuario.setT_retirados(cTicketHashMap.size());
                            UserFirebase userFirebase = new UserFirebase(firebaseDatabase);
                            userFirebase.setTicketRetirado(MainActivity.usuario.getEmail(), MainActivity.usuario.getT_retirados());

                            if(MainActivity.usuario.getT_retirados()>1){
                                MiRegistroActivity.titlet.setText("Tickets");
                                MiRegistroActivity.titler.setText("Retirados");
                            }else{
                                if(MainActivity.usuario.getT_retirados()==0){
                                    MiRegistroActivity.titlet.setText("Tickets");
                                    MiRegistroActivity.titler.setText("Retirados");
                                    //this.btnvermast.setVisibility(ViewVisible.INVISIBLE);
                                    MiRegistroActivity.auxticket.setVisibility(ViewVisible.VISIBLE);
                                    MiRegistroActivity.auxticket.setText("No tiene tickets registrados.\nReserva un ticket ahora mismo.");

                                }else{
                                    MiRegistroActivity.titlet.setText("Ticket");
                                    MiRegistroActivity.titler.setText("Retirado");
                                    //this.btnvermast.setVisibility(ViewVisible.INVISIBLE);
                                }
                            }
                            MiRegistroActivity.plantillacarga.setVisibility(View.INVISIBLE);
                            //RecyclerPersonaje . . .
                            RecyclerViewTickets recyclerViewTicket
                                    = new RecyclerViewTickets
                                    (context, cTicketHashMap);
                            RecyclerViewFunction.recyclerview
                                    (recyclerView, context, RecyclerViewFunction.VERTICAL, recyclerViewTicket);
                        }*/
                    }
                });
    }
}
