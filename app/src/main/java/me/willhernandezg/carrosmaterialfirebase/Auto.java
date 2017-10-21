package me.willhernandezg.carrosmaterialfirebase;

/**
 * Created by WillHernandezG on 21/10/2017.
 */

public class Auto {
    private String id;
    private int foto;
    private String placa;
    private int marca;
    private int modelo;
    private int color;
    private String precio;


    public Auto(String id){
        this.id=id;
    }

    public Auto(){

    }

    public Auto(String id, int foto, String placa, int marca, int modelo, int color, String precio) {
        this.id = id;
        this.foto = foto;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
    }

    public Auto(int foto, String placa, int marca, int modelo, int color, String precio) {
        this.foto = foto;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void guardar(){
        Datos.guardarAuto(this);
    }

    public void editar(){
        Datos.editarAuto(this);
    }

    public void eliminar(){
        Datos.eliminarAuto(this);
    }

}
