package sample;

public class categoria {
    String nombre;
    int precio, id;
    public categoria(int id, String nombre, int precio){
        this.id= id;
        this.nombre = nombre;
        this.precio = precio;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio=precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre=nombre;
    }
}
