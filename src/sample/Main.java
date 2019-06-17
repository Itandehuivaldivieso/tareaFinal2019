package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        HBox jugadores=new HBox();
        primaryStage.setTitle("Tarea Final");

        //Boton que te envia a la pantalla de agregarUsuario
        Button AgregarUsuario=new botonesP("Agregar Usuarios", "/imgbot/5.jpg", 20);
        AgregarUsuario.setOnMouseClicked(MouseEvent -> {
            GridPane gri=new GridPane();
            primaryStage.setScene(new agregarUsuario(gri, 750, 600));
        });

        //Boton que te envia a la pantalla de Consultar
        Button consultarUsuario=new botonesP("consultar usuarios", "/imgbot/2.jpg", 20);
        consultarUsuario.setOnMouseClicked(MouseEvent -> {
            primaryStage.setScene(new TodosUsuario(new TabPane(), 750, 750));
        });

        //Boton que te envia a la pantalla de Buscar por nombre, apellido o pais
        Button buscarNombreApe=new botonesP("Buscar por nombre, apellido o pais", "/imgbot/6.jpg", 20);
        buscarNombreApe.setOnMouseClicked(MouseEvent -> {
            GridPane gri=new GridPane();
            primaryStage.setScene(new busquedas(gri, 750, 600));
        });

        //datos de la scena
        jugadores.getChildren().addAll(AgregarUsuario, consultarUsuario, buscarNombreApe);
        Scene jugad = new Scene(jugadores, 800,250);
        primaryStage.setScene(jugad);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}