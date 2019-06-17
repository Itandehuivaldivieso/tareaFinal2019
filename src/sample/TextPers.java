package sample;

import javafx.scene.control.TableView;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TextPers extends Text {
    public TextPers(int tamLet, String s, Color col){
        super(s);
        this.setStyle("-fx-font-family: 'Century Gothic'");
        this.setEffect(new Glow(5.0));
        this.setFill(col);
        String n = "-fx-font-size: "+ tamLet;
        this.setStyle(n);
    }
}
