package sample;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PantallaCliente  {
    GridPane pantallaCliente;
    public void PantallaCliente(){

    }
    public void setPantallaCliente(GridPane pantallaCliente){
        this.pantallaCliente= pantallaCliente;
    }
    public GridPane getPantallaCliente() {
        return pantallaCliente;
    }

    public void datos(){
        Text nombre = new Text("Nombre(s)");
        pantallaCliente.add(nombre, 0,2);
    }
}
