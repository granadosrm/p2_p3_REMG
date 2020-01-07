package mx.edu.tesoem.p2_p3_remg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtnombre;
    TextView lblcontenido;


    private final String nomarch = "datos.txt";
    private ArrayList<String> TextoCompleto = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombre = findViewById(R.id.txtnombre);
        lblcontenido = findViewById(R.id.lblcontenido);
        llamadatos();


    }
    public void recargar (View v)
    {
        ManejoArchivo objmanar = new ManejoArchivo();

        objmanar.agrega(txtnombre.getText().toString(),TextoCompleto);
        TextoCompleto = objmanar.getContenido();

        if (objmanar.grabar(this,TextoCompleto,nomarch))
        {
            Toast.makeText(this, "grabado correctamente", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "no se grabo la informacion", Toast.LENGTH_SHORT).show();
        }
    }

    public void recargadatos (View v)
    {
    llamadatos();
    }

    public void llamadatos ()
    {
        ManejoArchivo informacion = new ManejoArchivo();
        if (informacion.VerificaArch(this, nomarch))
        {
            Toast.makeText(this, "si existe", Toast.LENGTH_LONG).show();
            if (informacion.leer(this,nomarch))
            {

                TextoCompleto = informacion.getContenido();
                String temp="";
                for (String cadena : TextoCompleto) temp+=cadena;
                lblcontenido.setText(temp);
            }

        }
        else

        {
            Toast.makeText(this, "no existe", Toast.LENGTH_LONG).show();
        }

    }
}
