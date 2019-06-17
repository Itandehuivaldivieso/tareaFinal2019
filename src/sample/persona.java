package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class persona {
    private int id;
    private final SimpleStringProperty nombre, apellidos, pais, bye, seccion;
    private final SimpleIntegerProperty rating, edad;
    public persona(String nombre, String apellidos, int rating,  String pais, int edad,  String bye, String seccion){
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.rating = new SimpleIntegerProperty(rating);
        this.pais = new SimpleStringProperty(pais);
        this.edad = new SimpleIntegerProperty(edad);
        this.bye = new SimpleStringProperty(bye);
        this.seccion = new SimpleStringProperty(seccion);

    }
    public persona(int id,String nombre, String apellidos, int rating,  String pais, int edad,  String bye, String seccion){
        this.id = id;
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.rating = new SimpleIntegerProperty(rating);
        this.pais = new SimpleStringProperty(pais);
        this.edad = new SimpleIntegerProperty(edad);
        this.bye = new SimpleStringProperty(bye);
        this.seccion = new SimpleStringProperty(seccion);

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public SimpleStringProperty apellidosProperty() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public int getEdad() {
        return edad.get();
    }

    public SimpleIntegerProperty edadProperty() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad.set(edad);
    }

    public int getRating() {
        return rating.get();
    }

    public SimpleIntegerProperty ratingProperty() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    public String getSeccion() {
        return seccion.get();
    }

    public SimpleStringProperty seccionProperty() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion.set(seccion);
    }

    public String getBye() {
        return bye.get();
    }

    public SimpleStringProperty byeProperty() {
        return bye;
    }

    public void setBye(String bye) {
        this.bye.set(bye);
    }

    public String getPais() {
        return pais.get();
    }

    public SimpleStringProperty paisProperty() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais.set(pais);
    }
}
