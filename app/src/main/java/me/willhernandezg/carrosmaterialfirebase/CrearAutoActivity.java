package me.willhernandezg.carrosmaterialfirebase;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CrearAutoActivity extends AppCompatActivity {
    private TextInputLayout cajaPlaca, cajaPrecio;
    private EditText txtPlaca, txtPrecio;
    private Spinner cmbMarca, cmbModelo, cmbColor;
    private ArrayList<Integer> fotos;
    private Resources res;
    private ArrayAdapter<String> adapterMarca, adapterModelo, adapterColor;
    private String[] opcMarca, opcModelo, opcColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_auto);

        cajaPlaca = (TextInputLayout) findViewById(R.id.cajaPlaca);
        txtPlaca = (EditText) findViewById(R.id.txtPlaca);

        cmbMarca = (Spinner) findViewById(R.id.cmbMarca);

        cmbModelo = (Spinner) findViewById(R.id.cmbModelo);

        cmbColor = (Spinner) findViewById(R.id.cmbColor);

        cajaPrecio = (TextInputLayout) findViewById(R.id.cajaPrecio);
        txtPrecio = (EditText) findViewById(R.id.txtPrecio);

        res = this.getResources();

        opcMarca = res.getStringArray(R.array.marca);

        opcModelo = res.getStringArray(R.array.modelo);

        opcColor = res.getStringArray(R.array.color);

        adapterMarca = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opcMarca);
        cmbMarca.setAdapter(adapterMarca);

        adapterModelo = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opcModelo);
        cmbModelo.setAdapter(adapterModelo);

        adapterColor = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opcColor);
        cmbColor.setAdapter(adapterColor);

        iniciar_fotos();

    }

    public void iniciar_fotos(){
        fotos = new ArrayList<>();
        fotos.add(R.drawable.carro1);
        fotos.add(R.drawable.carro2);
        fotos.add(R.drawable.carro3);
    }

    public void guardar(View v){
        if (validar()){
            Auto a = new Auto(Metodos.fotoAleatoria(fotos), txtPlaca.getText().toString(), cmbMarca.getSelectedItemPosition(), cmbModelo.getSelectedItemPosition(), cmbColor.getSelectedItemPosition(),txtPrecio.getText().toString());
            a.guardar();
            Snackbar.make(v, res.getString(R.string.mensaje_guardado), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            limpiar();
        }
    }

    public void limpiar(View v){
        limpiar();
    }

    private void limpiar(){
        txtPlaca.setText("");
        cmbMarca.setSelection(0);
        cmbModelo.setSelection(0);
        cmbColor.setSelection(0);
        txtPrecio.setText("");
        txtPlaca.requestFocus();
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent (CrearAutoActivity.this,PrincipalActivity.class);
        startActivity(i);
    }

    public boolean validar(){
        if (validar_aux(txtPlaca, cajaPlaca)) return false;
        else if (validar_aux(txtPrecio, cajaPrecio)) return false;
        else if (Metodos.existencia_auto(Datos.obtenerAutos(),txtPlaca.getText().toString())){
            txtPlaca.setError(res.getString(R.string.auto_existente_error));
            txtPlaca.requestFocus();
            return false;
        }

        return true;
    }

    public boolean validar_aux(TextView t, TextInputLayout ct){
        if (t.getText().toString().isEmpty()){
            t.requestFocus();
            t.setError(res.getString(R.string.error_vacio));
            return true;
        }
        return false;
    }

}
