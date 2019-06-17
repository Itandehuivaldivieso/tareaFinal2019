package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.w3c.dom.events.MouseEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class agregarUsuario extends Scene {

    public agregarUsuario(GridPane pantallaCliente, double v, double v1) {
        super(pantallaCliente, v, v1);
        conexion nn = new conexion();
        pantallaCliente.setStyle("-fx-background-image: url('/sample/fondoAj.jpg')");

        pantallaCliente.setHgap(10);
        pantallaCliente.setVgap(10);

        pantallaCliente.add(new TextPers(24, "Datos de los jugadores",Color.WHITE), 9,0,4, 1);
        pantallaCliente.add(new TextPers(13, "Nombre(s)",Color.WHITE), 2,2);

        TextField nombreT = new TextField();
        pantallaCliente.add(nombreT, 4, 2,4, 1);

        pantallaCliente.add(new TextPers(13, "Apellidos",Color.WHITE), 9,2);

        TextField apellidosT = new TextField();
        pantallaCliente.add(apellidosT, 11, 2,6,1);

        pantallaCliente.add(new TextPers(13, "Rating",Color.WHITE), 2,4);
        Slider rating = new Slider();
        rating.setMin(0);
        rating.setMax(2875);
        rating.setValue(1000);
        rating.setShowTickLabels(false);
        rating.setShowTickMarks(false);
        rating.setBlockIncrement(100);
        Text infoLabel = new TextPers(13, "1000",Color.WHITE);
        pantallaCliente.add(infoLabel, 5,4, 4, 1);
        rating.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                                Number oldValue, Number newValue) {

                infoLabel.setText(""+ newValue.intValue());
            }
        });
        pantallaCliente.add(rating, 4, 4);

        MenuItem menuItemVI = new MenuItem("Mexico", getIcon("/Paises/mexico.jpg"));
        MenuItem china = new MenuItem("China", getIcon("/Paises/china.png"));
        MenuItem cuba = new MenuItem("Cuba", getIcon("/Paises/cuba.jpg"));
        MenuItem argentina = new MenuItem("Argentina", getIcon("/Paises/argentina.jpg"));
        MenuItem espania = new MenuItem("España", getIcon("/Paises/españa.png"));
        MenuButton menuButton = new MenuButton("Menu", getIcon("/Paises/paises.png"), menuItemVI, china, cuba, argentina,espania);
        Text n1 = new TextPers(13, "-",Color.WHITE);
        pantallaCliente.add(n1, 11, 4);
        pantallaCliente.add(menuButton, 9,4);
        class evento implements EventHandler<ActionEvent> {
            String pais1;

            public evento() {

            }

            public String getPais1() {
                return pais1;
            }
            public void setPais1(){

            }

            @Override
            public void handle(ActionEvent actionEvent) {
                n1.setText("Selecciono: " + ((MenuItem)actionEvent.getSource()).getText() );
                pais1= ((MenuItem)actionEvent.getSource()).getText();
            }
        }

        pantallaCliente.add(new TextPers(13, "Edad",Color.WHITE), 2, 6);
        final Spinner<Integer> spinner = new Spinner<Integer>();

        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 100, 20);

        spinner.setValueFactory(valueFactory);
        pantallaCliente.add(spinner, 4, 6);
        CheckBox bye = new CheckBox("Bye");
        bye.setStyle("-fx-font-family: 'Century Gothic'");
        bye.setTextFill(Color.WHITE);
        pantallaCliente.add(bye, 9,6);
        EventHandler evenn = new evento();
        Button agregar = new Button("Agregar");
        pantallaCliente.add(agregar, 11, 10);

        pantallaCliente.add(new TextPers(13, "Seccion", Color.WHITE), 2, 8);
        ComboBox categoria = new ComboBox();
        categoria.getItems().addAll("Magistral", "Yucatan", "Merida", "Infantil");
        menuItemVI.setOnAction(evenn);
        china.setOnAction(evenn);
        cuba.setOnAction(evenn);
        argentina.setOnAction(evenn);
        espania.setOnAction(evenn);

        pantallaCliente.add(categoria,4, 8);

        TableView nuevo = new tablas().tab();
        ArrayList<persona> listap= new ArrayList<persona>();
        pantallaCliente.add(nuevo, 2, 12, 18, 1);

        agregar.setOnMouseClicked(mouseEvent -> {

                    String bye11="";
                    if (nombreT.getText().isEmpty() || apellidosT.getText().isEmpty() || ((evento) evenn).getPais1() == null || categoria.getValue() == null) {
                        Alert alert=new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Datos incompletos");
                        alert.setHeaderText(null);
                        alert.setContentText("Verifique sus campos, alguno lo tiene vacio o no seleccionado");
                        alert.showAndWait();
                    } else {
                        if(bye.isSelected()){
                            bye11="Si";
                        }
                        else {
                            bye11="no";
                        }
                        listap.add(new persona(nombreT.getText(), apellidosT.getText(), (int) rating.getValue(), ((evento) evenn).getPais1(), spinner.getValue(),bye11, (String) categoria.getValue()));
                        ObservableList<persona> list=FXCollections.observableArrayList(listap);
                        nuevo.setItems(list);
                        nombreT.setText("");
                        apellidosT.setText("");
                        rating.setValue(1000);
                        menuButton.setId("");
                        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 100, 20));
                        bye.setSelected(false);
                        categoria.setValue("");
                    }
                }
        );

        Button eliminar = new Button("Eliminar");
        pantallaCliente.add(eliminar, 17, 15);
        eliminar.setOnMouseClicked(mouseEvent ->{
            listap.remove((persona) nuevo.getSelectionModel().getSelectedItem());
            persona selece =(persona) nuevo.getSelectionModel().getSelectedItem();
            nuevo.getItems().remove(selece);
        } );
        Button editar = new Button("Editar");
        pantallaCliente.add(editar,14, 15);
        editar.setOnMouseClicked(mouseEvent -> {
            persona edit = (persona) nuevo.getSelectionModel().getSelectedItem();
            listap.remove((persona) nuevo.getSelectionModel().getSelectedItem());
            nombreT.setText(edit.getNombre());
            apellidosT.setText(edit.getApellidos());
            rating.setValue(edit.getRating());
            spinner.getValueFactory().setValue(edit.getEdad());
            if(edit.getBye() == "Si") {
                bye.setSelected(true);
            }else {
                bye.setSelected(false);
            }
            categoria.setValue(edit.getSeccion());


        });
        Button aceptar = new Button("Aceptar");
        aceptar.setOnMouseClicked(mouseEvent -> {
           int id1=  nn.getidTot();
           int id2= id1 +1;
           int sec = 0;
           int total =0;
            Date n = new Date();
            DateFormat fechahora = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            System.out.println("fecha" +fechahora.format(n));
           id1++;
            for (persona x:
                 listap) {
                if(x.getSeccion() =="Magistral"){
                    sec =0;
                    total+=1500;
                }
                if (x.getSeccion() == "Yucatan"){
                    sec = 1;
                    total +=1150;
                }
                if (x.getSeccion() == "Infantil"){
                    sec =2;
                    total += 1150;
                }
                if (x.getSeccion()== "Merida"){
                    sec =3;
                    total += 850;
                }
                nn.setBoleto(id1,sec);
                id1++;
            }
            nn.insertarUser(listap);
            for (persona x2: listap){
                nn.setCompra(id2,id2, 1,fechahora.format(n));
                id2++;
            }

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Compra");
            alert.setHeaderText(null);
            alert.setContentText("El total de su compra es: " + total +"\n" + "Fecha y hora de compra" +fechahora.format(n));
            alert.showAndWait();
        });
        pantallaCliente.add(aceptar, 19, 15);

    }

    private ImageView getIcon(String resourcePath) {
        ImageView imMex =new ImageView(new Image(getClass().getResourceAsStream(resourcePath)));
        imMex.setFitWidth(20);
        imMex.setFitHeight(20);
        return imMex;
    }
}
