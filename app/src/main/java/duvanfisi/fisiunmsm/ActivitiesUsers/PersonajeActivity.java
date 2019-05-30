package duvanfisi.fisiunmsm.ActivitiesUsers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseDatabase;
import duvanfisi.fisiunmsm.FirebaseConexion.PersonajeFirebase;
import duvanfisi.fisiunmsm.Model.CPersonaje;
import duvanfisi.fisiunmsm.R;

public class PersonajeActivity extends AppCompatActivity {

    private ImageView button_favorite;
    private ImageView button_love;
    private ImageView button_like;
    private ImageView button_dislike;

    public static TextView favs;
    public static TextView loves;
    public static TextView likes;
    public static TextView dislikes;

    private ImageView image_personaje_selected;
    private TextView name_personaje_selected;
    private TextView fecha_nac;
    private TextView ciudad_natal;
    private TextView desc;
    private TextView bio_first;
    private TextView bio_first2;
    private TextView bio_second;
    private TextView bio_third;
    private TextView bio_four;
    private TextView bio_five;
    private TextView bio_five2;
    private TextView bio_five3;


    FirebaseDatabase firebaseDatabase;
    PersonajeFirebase personajeFirebase;

    private CPersonaje personaje_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);

        this.inicializarViews();
        this.onClickButtons();
    }

    public void inicializarViews(){
        firebaseDatabase = new FirebaseDatabase(this);
        personajeFirebase = new PersonajeFirebase(firebaseDatabase);
        image_personaje_selected = findViewById(R.id.image_personaje_selected);
        name_personaje_selected = findViewById(R.id.name_personaje_selected);
        fecha_nac = findViewById(R.id.fecha_nac);
        ciudad_natal = findViewById(R.id.ciudad_natal);
        desc = findViewById(R.id.desc_personaje);
        bio_first = findViewById(R.id.bio_first);
        bio_first2 = findViewById(R.id.bio_first_2);
        bio_second = findViewById(R.id.bio_second);
        bio_third = findViewById(R.id.bio_third);
        bio_four = findViewById(R.id.bio_four);
        bio_five = findViewById(R.id.bio_five);
        bio_five2 = findViewById(R.id.bio_five2);
        bio_five3 = findViewById(R.id.bio_five3);

        button_favorite = findViewById(R.id.button_favorite_person);
        button_love = findViewById(R.id.button_love_person);
        button_like = findViewById(R.id.button_liked_person);
        button_dislike = findViewById(R.id.button_dontliked_person);


        favs = findViewById(R.id.favs);
        likes = findViewById(R.id.likes);
        loves = findViewById(R.id.loves);
        dislikes = findViewById(R.id.dislikes);

        imagerPicasso(R.drawable.icon_favorite, button_favorite);
        imagerPicasso(R.drawable.icon_love, button_love);
        imagerPicasso(R.drawable.icon_like, button_like);
        imagerPicasso(R.drawable.icon_dislike, button_dislike);

        button_favorite.setColorFilter(getResources().getColor(R.color.color_yellow_dark));
        button_love.setColorFilter(getResources().getColor(R.color.color_rose));
        button_like.setColorFilter(getResources().getColor(R.color.color_datos));
        button_dislike.setColorFilter(getResources().getColor(R.color.color_comedor_buttons));

        getPersonajeSelected();
        setPersonajeSelected();
    }

    public void onClickButtons(){
        this.button_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonsActions(0);
            }
        });

        this.button_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonsActions(1);
            }
        });

        this.button_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonsActions(2);
            }
        });

        this.button_dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsActions(3);
            }
        });
    }

    public void buttonsActions(int action){
        personajeFirebase.setActionPersonaje(personaje_selected.getNames(), action);
    }

    public void imagerPicasso(int iddrawable, ImageView img){
        ImagePicasso.setImageCenterCop(this, iddrawable, img);
    }

    public void getPersonajeSelected(){

        personaje_selected = Objects.requireNonNull(getIntent().getExtras()).getParcelable(Utilidades.PERSONAJES);
    }
    public void setPersonajeSelected(){
        String dl = Integer.toString(personaje_selected.getDislikes());
        String lv = Integer.toString(personaje_selected.getLoves());
        String lk = Integer.toString(personaje_selected.getLikes());
        String fv = Integer.toString(personaje_selected.getFavs());
        dislikes.setText(dl);
        loves.setText(lv);
        likes.setText(lk);
        favs.setText(fv);
        ImagePicasso.setImageCenterCop(this, personaje_selected.getPhoto(), image_personaje_selected);
        name_personaje_selected.setText(personaje_selected.getNames());
        fecha_nac.setText(personaje_selected.getFecha_nac());
        ciudad_natal.setText(personaje_selected.getCiudad_natal());
        desc.setText(personaje_selected.getDescripcion());

        if(personaje_selected.getBio_first()!=null){
            String parrafo_first[] = getParrafo(personaje_selected.getBio_first(), 2);
            String aux_parrafo_first = parrafo_first[0] + ".";
            bio_first.setText(aux_parrafo_first);
            bio_first2.setText(parrafo_first[1].trim());
        }
        bio_second.setText(personaje_selected.getBio_second());
        bio_third.setText(personaje_selected.getBio_third());


        if(personaje_selected.getBio_four()!=null){
            String parrafo[] = getParrafo(personaje_selected.getBio_four(), 4);
            String aux = parrafo[0] + ".";
            bio_four.setText(aux);
            bio_five.setText((parrafo[1] + ".").trim());
            bio_five2.setText((parrafo[2]+ ".").trim());
            bio_five3.setText(parrafo[3].trim());
        }

    }

    public String[] getParrafo(String texto, int limit){
        return texto.split("\\.", limit);
    }

}
