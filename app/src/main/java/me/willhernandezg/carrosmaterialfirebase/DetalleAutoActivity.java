package me.willhernandezg.carrosmaterialfirebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleAutoActivity extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Auto a;
    private String id, placa, precio;
    private int fot, marca, modelo, color;
    private Bundle bundle;
    private Intent i;
    private ImageView foto;
    private Resources res;
    private TextView placaD, marcaD, modeloD, colorD, precioD;
    private String[] opcMarca, opcModelo, opcColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_auto);

        placaD = (TextView) findViewById(R.id.lblPlacaD);
        marcaD = (TextView) findViewById(R.id.lblMarcaD);
        modeloD = (TextView) findViewById(R.id.lblModeloD);
        colorD = (TextView) findViewById(R.id.lblColorD);
        precioD = (TextView) findViewById(R.id.lblPrecioD);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        foto = (ImageView) findViewById(R.id.fotoAuto);

        res = this.getResources();

        i = getIntent();
        bundle = i.getBundleExtra("datos");
        id = bundle.getString("id");
        fot = bundle.getInt("foto");
        placa = bundle.getString("placa");
        marca = bundle.getInt("marca");
        modelo = bundle.getInt("modelo");
        color = bundle.getInt("color");
        precio = bundle.getString("precio");

        opcMarca = res.getStringArray(R.array.marca);
        opcModelo = res.getStringArray(R.array.modelo);
        opcColor = res.getStringArray(R.array.color);

        collapsingToolbarLayout.setTitle(opcMarca[marca]+" "+opcModelo[modelo]);
        foto.setImageDrawable(ResourcesCompat.getDrawable(res,fot,null));

        placaD.setText(placa);
        marcaD.setText(opcMarca[marca]);
        modeloD.setText(opcModelo[modelo]);
        colorD.setText(opcColor[color]);
        precioD.setText(precio);
        //Log.i("datos",a.getPlaca());
    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(res.getString(R.string.titulo_eliminar_mensaje));
        builder.setMessage(res.getString(R.string.eliminar_mensaje));
        positivo = res.getString(R.string.si_eliminar_mensaje);
        negativo = res.getString(R.string.no_eliminar_mensaje);
        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Auto a = new Auto(id);
                Datos.eliminarAuto(a);
                onBackPressed();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void editar(View v){

        Intent i = new Intent(DetalleAutoActivity.this, EditarAutoActivity.class);
        Bundle b2 = new Bundle();
        //Auto a = new Auto();
        b2.putString("id",id);
        b2.putInt("foto",fot);
        b2.putString("placa",placa);
        b2.putInt("marca",marca);
        b2.putInt("modelo",modelo);
        b2.putInt("color",color);
        b2.putString("precio",precio);

        i.putExtra("datos",b2);
        startActivity(i);
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent (DetalleAutoActivity.this,PrincipalActivity.class);
        startActivity(i);
    }

}