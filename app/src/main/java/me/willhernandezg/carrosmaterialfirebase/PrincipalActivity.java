package me.willhernandezg.carrosmaterialfirebase;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity implements AdaptadorAuto.OnAutoClickListener {
    private RecyclerView listado;
    private ArrayList<Auto> autos;
    private Resources res;
    private AdaptadorAuto adapter;
    private LinearLayoutManager llm;
    private Intent i;
    private DatabaseReference databaseReference;
    private final String BD = "Autos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listado = (RecyclerView)findViewById(R.id.lstOpciones);

        res = this.getResources();
        //autos = Datos.obtenerPersonas();
        autos = new ArrayList<>();

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new AdaptadorAuto(this.getApplicationContext(), autos, this);

        listado.setLayoutManager(llm);
        listado.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child(BD).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                autos.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Auto a = snapshot.getValue(Auto.class);
                        autos.add(a);
                        //Log.i("prueba",a.getPlaca());
                    }
                }
                adapter.notifyDataSetChanged();
                Datos.setAutos(autos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(PrincipalActivity.this, CrearAutoActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void OnAutoClick(Auto a) {
        Intent i = new Intent(PrincipalActivity.this, DetalleAutoActivity.class);
        Bundle b = new Bundle();

        b.putString("id",a.getId());
        b.putInt("foto",a.getFoto());
        b.putString("placa",a.getPlaca());
        b.putInt("marca",a.getMarca());
        b.putInt("modelo",a.getModelo());
        b.putInt("color",a.getColor());
        b.putString("precio",a.getPrecio());

        i.putExtra("datos",b);
        startActivity(i);
    }



    /**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    **/


}
