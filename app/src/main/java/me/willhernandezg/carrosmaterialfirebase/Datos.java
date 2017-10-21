package me.willhernandezg.carrosmaterialfirebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by WillHernandezG on 21/10/2017.
 */

public class Datos {

    private static String db="Autos";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private static ArrayList<Auto> autos = new ArrayList();

    public static void guardarAuto(Auto a){
        a.setId(databaseReference.push().getKey());
        databaseReference.child(db).child(a.getId()).setValue(a);
        //personas.add(p);
    }

    public static void editarAuto(Auto a){
        databaseReference.child(db).child(a.getId()).setValue(a);
        //personas.set(0,p);
    }

    public static ArrayList<Auto> obtenerAutos(){
        return autos;
    }

    public static void setAutos(ArrayList<Auto> aut){
        autos=aut;
    }

    public static void eliminarAuto(Auto a){
        databaseReference.child(db).child(a.getId()).removeValue();
    }
}
