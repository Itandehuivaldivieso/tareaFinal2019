package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class busquedas extends Scene {
    public busquedas(GridPane grid, double v1, double v2){
        super(grid,v1,v2);
        //Fondo del gridpane
        grid.setStyle("-fx-background-image: url('/sample/fondo2.jpg')");
        grid.setHgap(10);
        grid.setVgap(10);

        //Combo box para seleccionar que se quiere buscar
        ComboBox res = new ComboBox();
        res.getItems().addAll("Nombre", "Apellidos", "Pais");
        grid.add(res, 1,1 );
        res.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gi(grid,(String)res.getValue());
            }
        });

    }
    public void gi (GridPane grid, String resp){

        grid.add(new TextPers(13, resp, Color.BLACK), 2, 2);
        //Textfiel para la busqueda
        TextField nombre = new TextField();
        grid.add(nombre, 3, 2);
        //tsbla de los datos obtenidos
        TableView tab = new tablas().tab();
        Button buscar = new Button("buscar");
        grid.add(buscar, 6, 4);
        grid.add(tab, 1,7, 7,1);

        //Consultar en la base de datos
        buscar.setOnMouseClicked(mouseEvent -> {
            conexion conn = new conexion();
            if(resp == "Nombre") {
                tab.setItems(conn.getUserByName(nombre.getText()));
            }
            if(resp == "Apellidos"){
                tab.setItems(conn.getUserByLastName(nombre.getText()));
            }
            if (resp == "Pais"){
                tab.setItems(conn.getUserByCounty(nombre.getText()));
            }
        });

    }

}
