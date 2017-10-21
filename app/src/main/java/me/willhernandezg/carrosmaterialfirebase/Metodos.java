package me.willhernandezg.carrosmaterialfirebase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by WillHernandezG on 21/10/2017.
 */

public class Metodos {

    public static int fotoAleatoria(ArrayList<Integer> fotos){
        int fotoSeleccionada;
        Random r = new Random();
        fotoSeleccionada = r.nextInt(fotos.size());
        return fotos.get(fotoSeleccionada);
    }

    public static boolean existencia_auto(ArrayList<Auto> autos, String placa){
        for (int i = 0; i < autos.size() ; i++) {
            if (autos.get(i).getPlaca().equals(placa)){
                return true;
            }
        }
        return false;
    }

    public static boolean existencia_auto_editar(ArrayList<Auto> autos, String placaE, String placaActual){

        if (!placaActual.equals(placaE)) {
            if (existencia_auto(autos, placaE)) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

}
