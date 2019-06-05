package duvanfisi.fisiunmsm.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import duvanfisi.fisiunmsm.Actions.Utilidades;
import duvanfisi.fisiunmsm.Extras.ImagePicasso;
import duvanfisi.fisiunmsm.R;

public class PoliticasActivity extends AppCompatActivity {
    private ImageView back;
    private TextView textView;
    private TextView politica;
    private TextView datosusuario;
    private TextView datocuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politicas);
        inicializarViews();
        setImageViews();
        setTexts();
       onClickViews();



    }

    public void inicializarViews(){
        back = findViewById(R.id.btnback);
        textView = findViewById(R.id.cond);
        politica = findViewById(R.id.politica);
        datosusuario = findViewById(R.id.datosusuario);
        datocuenta = findViewById(R.id.datocuenta);
    }

    public void setTexts(){
        textView.setText(Utilidades.CONDICIONES);
        politica.setText(Utilidades.POLITICA);
        datosusuario.setText(Utilidades.DATOSUSUARIO);
        datocuenta.setText(Utilidades.DATOCUENTA);
    }

    public void setImageViews(){
        ImagePicasso.setImageCenterCop(PoliticasActivity.this, R.drawable.ic_back, back);
    }

    public void onClickViews(){
        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
