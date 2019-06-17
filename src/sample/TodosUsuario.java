package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Pair;

import java.util.Optional;

public class TodosUsuario extends Scene {
    public TodosUsuario(TabPane usuarios, double num, double num2){
        super(usuarios,num,num2);
        usuarios.setStyle("-fx-background-image: url('/sample/fondo2.jpg')");
        usuarios.getTabs().addAll(jugadoresMagistral(), jugadoresyucatan(), jugadoresMerida(), jugadoresInfantil());
    }
    conexion con = new conexion();

    //Tab de jugadores en la categoria magistral
    public Tab jugadoresMagistral(){
        Tab magistral = new Tab();

        GridPane nnn = new GridPane();
        nnn.setHgap(10);
        nnn.setVgap(10);

        TableView tabMag = new tablas().tab() ;
        tabMag.setEditable(true);

        magistral.setText("Magistral");
        Text n1 = new TextPers(25, "Jugadores Magistral", Color.WHITE);
        n1.setTextAlignment(TextAlignment.CENTER);
        nnn.add(n1,4,0,14,1);
        //Metemos a la tabla, la consulta a la base de datos.
        tabMag.setItems(con.getuserboletoMagistral());
        nnn.add(tabMag,2, 2, 14,7);

        magistral.setContent(botonesAvisos(nnn,tabMag));

        return magistral;
    }
    public Tab jugadoresyucatan(){
        Tab magistral = new Tab();
        GridPane nnn = new GridPane();
        nnn.setHgap(10);
        nnn.setVgap(10);
        TableView tabMag = new tablas().tab() ;
        tabMag.setEditable(true);
        magistral.setText("Yucatan");
        Text n1 = new TextPers(25, "Jugadores yucatan",Color.WHITE);
        n1.setTextAlignment(TextAlignment.CENTER);
        nnn.add(n1,4,0,14,1);
        tabMag.setItems(con.getuserboletoyucatan());
        nnn.add(tabMag,2, 2, 14,7);

        magistral.setContent(botonesAvisos(nnn,tabMag));

        return magistral;
    }
    public Tab jugadoresInfantil(){
        Tab magistral = new Tab();
        GridPane nnn = new GridPane();
        nnn.setHgap(10);
        nnn.setVgap(10);
        TableView tabMag = new tablas().tab() ;
        tabMag.setEditable(true);
        magistral.setText("infantil");
        Text n1 = new TextPers(25, "Jugadores Infantil",Color.WHITE);
        n1.setTextAlignment(TextAlignment.CENTER);
        nnn.add(n1,4,0,14,1);
        tabMag.setItems(con.getuserboletoinfantil());
        nnn.add(tabMag,2, 2, 14,7);

        magistral.setContent(botonesAvisos(nnn,tabMag));

        return magistral;
    }
    public Tab jugadoresMerida(){
        Tab magistral = new Tab();
        GridPane nnn = new GridPane();
        nnn.setHgap(10);
        nnn.setVgap(10);
        TableView tabMag = new tablas().tab() ;
        tabMag.setEditable(true);
        magistral.setText("merida");
        Text n1 = new TextPers(25, "Jugadores merida",Color.WHITE);
        n1.setTextAlignment(TextAlignment.CENTER);
        nnn.add(n1,4,0,14,1);
        tabMag.setItems(con.getuserboletoMerida());
        nnn.add(tabMag,2, 2, 14,7);

        magistral.setContent(botonesAvisos(nnn,tabMag));

        return magistral;
    }
    public GridPane botonesAvisos(GridPane bod, TableView n){
        Button eliminar = new Button("Eliminar");
        Button editar = new Button("Editar");
        bod.add(editar, 2, 9,2,1);
        bod.add(eliminar, 6, 9,2,1 );

        //Elimina en la tabla
        eliminar.setOnMouseClicked(mouseEvent -> {

            persona n2 = (persona) n.getSelectionModel().getSelectedItem();
            System.out.println("ss"+ n2.getId());
            con.eliminarUser(n2.getId());
            n.setItems(con.getuserboletoMagistral());
        });

        editar.setOnMouseClicked(mouseEvent -> {
            persona n2 = (persona) n.getSelectionModel().getSelectedItem();
            // Create the custom dialog.
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Modificar");
            dialog.setHeaderText("Datos usuario");
// Set the icon (must be included in the project).
            //dialog.setGraphic(new ImageView(this.getClass().getResource("/sample/fondoAj.jpg").toString()));

// Set the button types.
            ButtonType loginButtonType = new ButtonType("modificar", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            grid.add(new TextPers(12,"Nombre",Color.BLACK),0,0);
            TextField nombre = new TextField();
            grid.add(nombre, 1, 0);
            nombre.setText(n2.getNombre());

            grid.add(new TextPers(12, "Apellidos",Color.BLACK), 0,1);
            TextField apellidos = new TextField();
            grid.add(apellidos,1,1);
            apellidos.setText(n2.getApellidos());

            grid.add(new TextPers(12, "rating",Color.BLACK), 0,2);
            Slider rating = new Slider();
            rating.setMin(0);
            rating.setMax(2875);
            rating.setValue(1000);
            rating.setShowTickLabels(false);
            rating.setShowTickMarks(false);
            rating.setBlockIncrement(100);
            Text infoLabel = new TextPers(13, "1000",Color.BLACK);
            grid.add(infoLabel, 4,2);
            rating.valueProperty().addListener(new ChangeListener<Number>() {

                @Override
                public void changed(ObservableValue<? extends Number> observable, //
                                    Number oldValue, Number newValue) {

                    infoLabel.setText(""+ newValue.intValue());
                }
            });
            grid.add(rating,1,2);
            rating.setValue(n2.getRating());

            grid.add(new TextPers(12, "pais",Color.BLACK), 0,3);
            TextField pais = new TextField();
            grid.add(pais,1,3);
            pais.setText(n2.getPais());

            grid.add(new TextPers(12, "edad",Color.BLACK), 0,4);
            final Spinner<Integer> spinner = new Spinner<Integer>();
            // Value factory.
            SpinnerValueFactory<Integer> valueFactory = //
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 100, 20);
            spinner.setValueFactory(valueFactory);
            grid.add(spinner,1,4);
            spinner.getValueFactory().setValue(n2.getEdad());

            CheckBox bye = new CheckBox("Bye");
            bye.setStyle("-fx-font-family: 'Century Gothic'");
            bye.setTextFill(Color.BLACK);
            grid.add(bye,0, 5);
            if(n2.getBye() == "Si") {
                bye.setSelected(true);
            }else {
                bye.setSelected(false);
            }

// Enable/Disable login button depending on whether a username was entered.
            Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);


            dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
            Platform.runLater(() -> nombre.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    String sel ="";
                    if(bye.isSelected()){
                        sel="Si";
                    }
                    else{
                        sel ="no";
                    }
                    con.modificarUsuario(new persona(n2.getId(),nombre.getText(),apellidos.getText(),(int) rating.getValue(),pais.getText(),spinner.getValue(),sel,n2.getSeccion()));
                    n.setItems(con.getuserboletoMagistral());
                    return new Pair<>(nombre.getText(), apellidos.getText());
                }
                return null;
            });

            Optional<Pair<String, String>> result = dialog.showAndWait();

            result.ifPresent(usernamePassword -> {
                System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
            });
        });

        return bod;
    }

}
