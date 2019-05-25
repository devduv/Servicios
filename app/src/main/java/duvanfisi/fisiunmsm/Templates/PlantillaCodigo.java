package duvanfisi.fisiunmsm.Templates;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import duvanfisi.fisiunmsm.Extras.ViewFloat;
import duvanfisi.fisiunmsm.R;

public class PlantillaCodigo {
    private Context context;
    private int cont;

    private Button btn00; private Button btn01; private Button btn02;
    private Button btn03; private Button btn04; private Button btn05;
    private Button btn06; private Button btn07; private Button btn08;
    private Button btn09;

    private ImageButton btnnunc;
    private ImageButton btnborrar;

    private Button btnsetcod;

    private AlertDialog dialog;

    private Button button_registrar_datos;

    private LinearLayout linearLayout;


    private String cod_in_tec;
    private View view_text;
    private View view_content;


    private TextView r_cod;
    public PlantillaCodigo(Context context, String cod_in_tec, TextView r_cod){
        this.cod_in_tec = cod_in_tec;
        this.context = context;
        this.view_content = ViewFloat.floatview(context, R.layout.ingresar_cod);

        this.view_text = ViewFloat.floatview(context, R.layout.text_cod);

        btn00 = view_content.findViewById(R.id.btn_num_0);btn01 = view_content.findViewById(R.id.btn_num_1);
        btn02 = view_content.findViewById(R.id.btn_num_2);btn03 = view_content.findViewById(R.id.btn_num_3);
        btn04 = view_content.findViewById(R.id.btn_num_4);btn05 = view_content.findViewById(R.id.btn_num_5);
        btn06 = view_content.findViewById(R.id.btn_num_6);btn07 = view_content.findViewById(R.id.btn_num_7);
        btn08 = view_content.findViewById(R.id.btn_num_8);btn09 = view_content.findViewById(R.id.btn_num_9);

        this.btnborrar = view_content.findViewById(R.id.btn_num_b);

        this.btnnunc = view_content.findViewById(R.id.btn_num_c);
        btnsetcod = view_content.findViewById(R.id.btnsetcod);
        linearLayout = view_content.findViewById(R.id.linearcod);
        this.r_cod =r_cod;
        dialog = codigoAlumno();
        onClickButton();

    }

    public void begin(){
        dialog.show();
    }
    public void onClickButton(){
        btn00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCOD(btn00);
            }
        });
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCOD(btn01);
            }
        });
        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCOD(btn02);
            }
        });
        btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCOD(btn03);
            }
        });
        btn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCOD(btn04);
            }
        });
        btn05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCOD(btn05);
            }
        });
        btn06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCOD(btn06);
            }
        });
        btn07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCOD(btn07);
            }
        });
        btn08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCOD(btn08);
            }
        });
        btn09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCOD(btn09);
            }
        });
        btnnunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRandom();
            }
        });
        btnborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBorrar();
            }
        });
        btnsetcod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCodigo();
            }
        });

    }
    public AlertDialog codigoAlumno() {
        cod_in_tec = "";
        funcionRandom();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view_content);

        builder.setCancelable(false);
        return builder.create();
    }

    public void funcionRandom(){
        Random random = new Random();
        int number = random.nextInt(10);

        int aux = number;
        int[] opc = new int[10];
        String[] numbers = new String[10];
        int i=0;
        boolean hallado;
        while(i<10){
            hallado = false;
            if(aux == number){
                number = random.nextInt(10);
            }else{
                for(int j=0; j<i; j++){
                    if(opc[j] == number){
                        hallado = true;
                    }
                }
                if(!hallado){
                    opc[i] = number;
                    numbers[i] = Integer.toString(opc[i]);
                    aux = number;
                    number = random.nextInt(10);
                    i++;
                }else{
                    number = random.nextInt(10);
                }

            }
        }
        btn00.setText(numbers[0]);btn01.setText(numbers[1]);
        btn02.setText(numbers[2]);btn03.setText(numbers[3]);
        btn04.setText(numbers[4]);btn05.setText(numbers[5]);
        btn06.setText(numbers[6]);btn07.setText(numbers[7]);
        btn08.setText(numbers[8]);btn09.setText(numbers[9]);
    }

    public View setTxt(String number){
        View view_text = ViewFloat.floatview(context, R.layout.text_cod);
        TextView textView = view_text.findViewById(R.id.id_cod_input);
        textView.setText(number);
        return  view_text;
    }

    public void setCOD(Button btn){
        if(cont <8) {
            linearLayout.addView(setTxt(btn.getText().toString()));
            cod_in_tec = cod_in_tec + btn.getText().toString();
            cont++;
        }else if(cont ==7){
            setCodigo();
            }else{
            TemplateMessage mensaje = new TemplateMessage(context);
            mensaje.setMensaje("Código", "Demasiados dígitos");
            }
    }

    public void getBorrar(){
        cod_in_tec = "";
        cont = 0;
        limpiar();
    }

    public void limpiar(){
        linearLayout.removeAllViews();
    }
    public void getRandom(){
        funcionRandom();
    }


    public void setCodigo(){
        r_cod.setText(cod_in_tec);
        dialog.dismiss();
    }

}

