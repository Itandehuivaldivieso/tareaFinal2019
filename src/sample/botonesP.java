package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class botonesP extends Button {
    public botonesP(String nombre, String url, int tamLet){
        super(nombre);
        ImageView imMex =new ImageView(new Image(getClass().getResourceAsStream(url)));
        imMex.setFitWidth(200);
        imMex.setFitHeight(200);
        this.setGraphic(imMex);
        this.setStyle("-fx-font-family: 'Century Gothic'");
        this.setContentDisplay(ContentDisplay.BOTTOM);
        String n = "-fx-font-size: "+ tamLet;
        this.setStyle(n);
    }
}
