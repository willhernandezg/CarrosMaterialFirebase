package me.willhernandezg.carrosmaterialfirebase;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class EditarAutoActivity extends AppCompatActivity {
    private TextInputLayout cajaPlacaE, cajaPrecioE;
    private EditText txtPlacaE, txtPrecioE;
    private Spinner cmbMarcaE, cmbModeloE, cmbColorE;
    private Auto a;
    private ArrayList<Integer> fotos;
    private Resources res;
    private ArrayAdapter<String> adapterMarca, adapterModelo, adapterColor;
    private String[] opcMarcaE, opcModeloE, opcColorE;
    private Intent i;
    private Bundle bundle;
    private String id, placa, precio;
    private int foto, marca, modelo, color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_auto);

        cajaPlacaE = (TextInputLayout) findViewById(R.id.cajaPlacaE);
        txtPlacaE = (EditText) findViewById(R.id.txtPlacaE);

        cmbMarcaE = (Spinner) findViewById(R.id.cmbMarcaE);

        cmbModeloE = (Spinner) findViewById(R.id.cmbModeloE);

        cmbColorE = (Spinner) findViewById(R.id.cmbColorE);

        cajaPrecioE = (TextInputLayout) findViewById(R.id.cajaPrecioE);
        txtPrecioE = (EditText) findViewById(R.id.txtPrecioE);

        res = this.getResources();

        opcMarcaE = res.getStringArray(R.array.marca);

        opcModeloE = res.getStringArray(R.array.modelo);

        opcColorE = res.getStringArray(R.array.color);

        adapterMarca = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opcMarcaE);
        cmbMarcaE.setAdapter(adapterMarca);

        adapterModelo = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opcModeloE);
        cmbModeloE.setAdapter(adapterModelo);

        adapterColor = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opcColorE);
        cmbColorE.setAdapter(adapterColor);

        i = getIntent();
        bundle = i.getBundleExtra("datos");
        id = bundle.getString("id");
        foto = bundle.getInt("foto");
        placa = bundle.getString("placa");
        marca = bundle.getInt("marca");
        modelo = bundle.getInt("modelo");
        color = bundle.getInt("color");
        precio = bundle.getString("precio");

        txtPlacaE.setText(placa);
        cmbMarcaE.setSelection(marca);
        cmbModeloE.setSelection(modelo);
        cmbColorE.setSelection(color);
        txtPrecioE.setText(precio);
        //Log.i("datos",a.getPlaca());
    }

    public void editar(View v){
        if (validarE()){
            Auto a = new Auto(id ,foto, txtPlacaE.getText().toString(), cmbMarcaE.getSelectedItemPosition(),cmbModeloE.getSelectedItemPosition(),cmbColorE.getSelectedItemPosition(), txtPrecioE.getText().toString());
            a.editar();
            Snackbar.make(v, res.getString(R.string.mensaje_guardado), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            onBackPressedE();
        }
    }

    public boolean validarE(){
        if (validar_auxE(txtPlacaE, cajaPlacaE)) return false;
        else if (validar_auxE(txtPrecioE, cajaPrecioE)) return false;
        else if (Metodos.existencia_auto_editar(Datos.obtenerAutos(),txtPlacaE.getText().toString(),placa)) {
            txtPlacaE.setError(res.getString(R.string.auto_existente_error));
            txtPlacaE.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validar_auxE(TextView t, TextInputLayout ct){
        if (t.getText().toString().isEmpty()){
            t.requestFocus();
            t.setError("No puede ser vacio");
            return true;
        }
        return false;
    }

    public void onBackPressedE(){
        Intent i = new Intent(this, DetalleAutoActivity.class);
        Bundle b3 = new Bundle();
        b3.putString("id",id);
        b3.putInt("foto",foto);
        b3.putString("placa",txtPlacaE.getText().toString());
        b3.putInt("marca",cmbMarcaE.getSelectedItemPosition());
        b3.putInt("modelo",cmbModeloE.getSelectedItemPosition());
        b3.putInt("color",cmbColorE.getSelectedItemPosition());
        b3.putString("precio",txtPrecioE.getText().toString());

        i.putExtra("datos",b3);
        startActivity(i);
    }

}
