package sample;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class tablas {
    public TableView<persona> tab(){
        TableView <persona> nn = new TableView<persona>();
        nn.setEditable(true);
        TableColumn nombre = new TableColumn("Nombre");
        nombre.setMinWidth(100);
        nombre.setCellValueFactory(
                new PropertyValueFactory<persona, String>("nombre"));

        TableColumn apellidos = new TableColumn("Apellidos");
        apellidos.setMinWidth(100);
        apellidos.setCellValueFactory(
                new PropertyValueFactory<persona, String>("apellidos"));

        TableColumn rating = new TableColumn("rating");
        rating.setMinWidth(100);
        rating.setCellValueFactory(
                new PropertyValueFactory<persona, Integer>("rating"));

        TableColumn pais = new TableColumn("pais");
        pais.setMinWidth(100);
        pais.setCellValueFactory(
                new PropertyValueFactory<persona, String>("pais"));

        TableColumn edad = new TableColumn("edad");
        edad.setMinWidth(100);
        edad.setCellValueFactory(
                new PropertyValueFactory<persona, Integer>("edad"));

        TableColumn bye = new TableColumn("bye");
        bye.setMinWidth(100);
        bye.setCellValueFactory(
                new PropertyValueFactory<persona, String>("bye"));

        TableColumn seccion = new TableColumn("seccion");
        seccion.setMinWidth(100);
        seccion.setCellValueFactory(
                new PropertyValueFactory<persona, String>("seccion"));

        //nn.setItems(list);
        nn.getColumns().addAll(nombre,apellidos,rating,pais,edad,bye,seccion);
        return  nn;
    }
    public tablas() {

    }

}
