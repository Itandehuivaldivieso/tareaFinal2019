package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.sql.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class conexion {
    Connection con;
    String url="jdbc:mysql://localhost:3306/bdboletaje" + "?useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true" + "&useLegacyDatetimeCode=false"
            + "&serverTimezone=UTC";

    public conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(" conectado");
            con=DriverManager.getConnection(url, "root", "");
            System.out.println(" conectado");
        } catch (SQLException e) {
            e.printStackTrace();
            con=null;
            e.printStackTrace();
            System.out.println(" SQLException : " + e.getMessage());
            System.out.println(" SQLState : " + e.getSQLState());
            System.out.println(" VendorError : " + e.getErrorCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void modificarUsuario(persona per){
        String query="UPDATE cliente SET nombre='"+per.getNombre()+"', " +
                "apellidos='"+per.getApellidos()+"', rating='"+per.getRating()+"', pais='" + per.getPais() +"', edad='" + per.getEdad() + "', bye= '" + per.getBye() + "'" +" WHERE clienteID="+per.getId();

        try (Statement stmt=con.createStatement()) {
            stmt.executeUpdate(query, Statement.
                    RETURN_GENERATED_KEYS);
            ResultSet rs=stmt.getGeneratedKeys();
            int rowId;
            if (rs.next()) {
                rowId=rs.getInt(1);
            } else {
                // Esto no deber  ́ıa ocurrir ...
                rowId=-1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public ObservableList<categoria> getCat(){
        ArrayList<categoria> catt = new ArrayList<categoria>();
        ResultSet reg = null;
        try {
            PreparedStatement stConsulta=con.prepareStatement(" SELECT seccionId, descripcion , precio " + " FROM seccion ");
            reg=stConsulta.executeQuery();
            while(reg.next()) {
                int id = reg.getInt("seccionId");
                String des = reg.getString("descripcion");
                int precio = (int) reg.getDouble("precio");
                catt.add(new categoria(id,des,precio));
            }
            ObservableList<categoria> list=FXCollections.observableArrayList(catt);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public int getidTot(){
        int id =0;
        ResultSet reg = null;
        try {
            PreparedStatement stConsulta=con.prepareStatement(" SELECT MAX(clienteID)" + " FROM cliente ");
            reg=stConsulta.executeQuery();
            while(reg.next()) {
                id = reg.getInt(1);
                System.out.println("id" + id);
                }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public void setCompra(int clienteid, int numBoleto, int pag, String dia){
        String query=" INSERT INTO compra " + "( clienteId, numBoleto, pagado, fechaHora) " + " VALUES ('" + clienteid + "', '" + numBoleto + "','" + pag + "','" + dia + "') " ;
        try (Statement stmt=con.createStatement()) {
            stmt.executeUpdate(query, Statement.
                    RETURN_GENERATED_KEYS);
            ResultSet rs=stmt.getGeneratedKeys();
            int rowId;
            if (rs.next()) {
                rowId=rs.getInt(1);
            } else {
                // Esto no deber  ́ıa ocurrir ...
                rowId=-1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setBoleto(int boleto, int seccion){
            String query=" INSERT INTO boleto " + "( numBoleto, seccionId) " + " VALUES ('" + boleto + "', '" + seccion + "') ";
            try (Statement stmt=con.createStatement()) {
                stmt.executeUpdate(query, Statement.
                        RETURN_GENERATED_KEYS);
                ResultSet rs=stmt.getGeneratedKeys();
                int rowId;
                if (rs.next()) {
                    rowId=rs.getInt(1);
                } else {
                    // Esto no deber  ́ıa ocurrir ...
                    rowId=-1;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public ResultSet getuserbyid(int id){
        ResultSet registros=null;

        try {
            PreparedStatement stConsulta=con.prepareStatement(" SELECT clienteID, nombre , apellidos, direccion " + " FROM cliente "+"WHERE clienteID ="+ id);
            registros=stConsulta.executeQuery();
            return registros;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void insertarUser(ArrayList per){
        for (int x=0; x<per.size(); x++) {
            persona pers = (persona) per.get(x);

            String query=" INSERT INTO cliente " + "(nombre , apellidos , rating , pais , edad , bye ) " + " VALUES ('" + pers.getNombre() + "', '" + pers.getApellidos() + "', '" + pers.getRating() + "', '" + pers.getPais() + "" + "', '" + pers.getEdad() + "', '" + pers.getBye() + "') ";
            try (Statement stmt=con.createStatement()) {
                stmt.executeUpdate(query, Statement.
                        RETURN_GENERATED_KEYS);
                ResultSet rs=stmt.getGeneratedKeys();
                int rowId;
                if (rs.next()) {
                    rowId=rs.getInt(1);
                } else {
                    // Esto no deber  ́ıa ocurrir ...
                    rowId=-1;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ObservableList<persona> getuserboletoMagistral(){
        ArrayList<persona> lis = new ArrayList<persona>();
        ResultSet registros=null;

        try {
            PreparedStatement stConsulta=con.prepareStatement(" SELECT cliente.clienteID, cliente.nombre , cliente.apellidos, cliente.rating, cliente.pais, cliente.edad" +
                    ", cliente.bye, seccion.descripcion " + " FROM cliente, boleto, compra, seccion " + "WHERE cliente.clienteID = compra.clienteID AND boleto.numBoleto = compra.numBoleto AND boleto.seccionId = seccion.seccionId AND seccion.descripcion = 'Magistral' ");
            registros=stConsulta.executeQuery();
            while (registros.next()){
                int id = registros.getInt("cliente.clienteID");
                String nombre = registros.getString("cliente.nombre");
                String apellido = registros.getString("cliente.apellidos");
                int rating = registros.getInt("cliente.rating");
                int edad = registros.getInt("cliente.edad");
                String pais = registros.getString("cliente.pais");
                String bye = registros.getString("cliente.bye");
                String seccion = registros.getString("seccion.descripcion");
                lis.add(new persona(id, nombre,apellido,rating,pais,edad,bye,seccion));
            }
            ObservableList<persona> list=FXCollections.observableArrayList(lis);
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    public ObservableList<persona> getuserboletoyucatan(){
        ArrayList<persona> lis = new ArrayList<persona>();
        ResultSet registros=null;

        try {
            PreparedStatement stConsulta=con.prepareStatement(" SELECT cliente.clienteID, cliente.nombre , cliente.apellidos, cliente.rating, cliente.pais, cliente.edad" +
                    ", cliente.bye, seccion.descripcion " + " FROM cliente, boleto, compra, seccion " + "WHERE cliente.clienteID = compra.clienteID AND boleto.numBoleto = compra.numBoleto AND boleto.seccionId = seccion.seccionId AND seccion.descripcion = 'yucatan' ");
            registros=stConsulta.executeQuery();
            while (registros.next()){
                int id = registros.getInt("cliente.clienteID");
                String nombre = registros.getString("cliente.nombre");
                String apellido = registros.getString("cliente.apellidos");
                int rating = registros.getInt("cliente.rating");
                int edad = registros.getInt("cliente.edad");
                String pais = registros.getString("cliente.pais");
                String bye = registros.getString("cliente.bye");
                String seccion = registros.getString("seccion.descripcion");
                lis.add(new persona(id, nombre,apellido,rating,pais,edad,bye,seccion));
            }
            ObservableList<persona> list=FXCollections.observableArrayList(lis);
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    public ObservableList <persona> getUserByName(String nombre){
        ArrayList<persona> lis = new ArrayList<persona>();
        ResultSet registros=null;
        try {
            PreparedStatement stConsulta=con.prepareStatement(" SELECT cliente.nombre, cliente.apellidos , cliente.rating, cliente.pais, cliente.edad, cliente.bye, seccion.descripcion " +
                    " FROM cliente, boleto, compra, seccion WHERE cliente.clienteID = compra.clienteID AND boleto.numBoleto = compra.numBoleto AND boleto.seccionId = seccion.seccionId AND cliente.nombre = "+"'" + nombre+ "'");
            registros=stConsulta.executeQuery();
            while (registros.next()){
                String nombre1 = registros.getString("cliente.nombre");
                String apellido1 = registros.getString("cliente.apellidos");
                int rating = registros.getInt("cliente.rating");
                int edad = registros.getInt("cliente.edad");
                String pais1 = registros.getString("cliente.pais");
                String bye = registros.getString("cliente.bye");
                String seccion = registros.getString("seccion.descripcion");
                lis.add(new persona(nombre1,apellido1,rating,pais1,edad,bye,seccion));
            }
            ObservableList<persona> list=FXCollections.observableArrayList(lis);
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ObservableList <persona> getUserByLastName(String apellidos){
        ArrayList<persona> lis = new ArrayList<persona>();
        ResultSet registros=null;
        try {
            PreparedStatement stConsulta=con.prepareStatement(" SELECT cliente.nombre, cliente.apellidos , cliente.rating, cliente.pais, cliente.edad, cliente.bye, seccion.descripcion " +
                    " FROM cliente, boleto, compra, seccion WHERE cliente.clienteID = compra.clienteID AND boleto.numBoleto = compra.numBoleto AND boleto.seccionId = seccion.seccionId AND cliente.apellidos = "+"'" + apellidos+ "'");
            registros=stConsulta.executeQuery();
            while (registros.next()){
                String nombre1 = registros.getString("cliente.nombre");
                String apellido1 = registros.getString("cliente.apellidos");
                int rating = registros.getInt("cliente.rating");
                int edad = registros.getInt("cliente.edad");
                String pais1 = registros.getString("cliente.pais");
                String bye = registros.getString("cliente.bye");
                String seccion = registros.getString("seccion.descripcion");
                lis.add(new persona(nombre1,apellido1,rating,pais1,edad,bye,seccion));
            }
            ObservableList<persona> list=FXCollections.observableArrayList(lis);
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ObservableList <persona> getUserByCounty(String pais){
        ArrayList<persona> lis = new ArrayList<persona>();
        ResultSet registros=null;
        try {
            PreparedStatement stConsulta=con.prepareStatement(" SELECT cliente.nombre, cliente.apellidos , cliente.rating, cliente.pais, cliente.edad, cliente.bye, seccion.descripcion " +
                    " FROM cliente, boleto, compra, seccion WHERE cliente.clienteID = compra.clienteID AND boleto.numBoleto = compra.numBoleto AND boleto.seccionId = seccion.seccionId AND cliente.pais = "+"'" + pais+ "'");
            registros=stConsulta.executeQuery();
            while (registros.next()){
                String nombre1 = registros.getString("cliente.nombre");
                String apellido1 = registros.getString("cliente.apellidos");
                int rating = registros.getInt("cliente.rating");
                int edad = registros.getInt("cliente.edad");
                String pais1 = registros.getString("cliente.pais");
                String bye = registros.getString("cliente.bye");
                String seccion = registros.getString("seccion.descripcion");
                lis.add(new persona(nombre1,apellido1,rating,pais1,edad,bye,seccion));
            }
            ObservableList<persona> list=FXCollections.observableArrayList(lis);
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ObservableList<persona> getuserboletoinfantil(){
        ArrayList<persona> lis = new ArrayList<persona>();
        ResultSet registros=null;

        try {
            PreparedStatement stConsulta=con.prepareStatement(" SELECT cliente.clienteID, cliente.nombre , cliente.apellidos, cliente.rating, cliente.pais, cliente.edad" +
                    ", cliente.bye, seccion.descripcion " + " FROM cliente, boleto, compra, seccion " + "WHERE cliente.clienteID = compra.clienteID AND boleto.numBoleto = compra.numBoleto AND boleto.seccionId = seccion.seccionId AND seccion.descripcion = 'infantil' ");
            registros=stConsulta.executeQuery();
            while (registros.next()){
                int id = registros.getInt("cliente.clienteID");
                String nombre = registros.getString("cliente.nombre");
                String apellido = registros.getString("cliente.apellidos");
                int rating = registros.getInt("cliente.rating");
                int edad = registros.getInt("cliente.edad");
                String pais = registros.getString("cliente.pais");
                String bye = registros.getString("cliente.bye");
                String seccion = registros.getString("seccion.descripcion");
                lis.add(new persona(id, nombre,apellido,rating,pais,edad,bye,seccion));
            }
            ObservableList<persona> list=FXCollections.observableArrayList(lis);
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    public ObservableList<persona> getuserboletoMerida(){
        ArrayList<persona> lis = new ArrayList<persona>();
        ResultSet registros=null;

        try {
            PreparedStatement stConsulta=con.prepareStatement(" SELECT cliente.clienteID, cliente.nombre , cliente.apellidos, cliente.rating, cliente.pais, cliente.edad" +
                    ", cliente.bye, seccion.descripcion " + " FROM cliente, boleto, compra, seccion " + "WHERE cliente.clienteID = compra.clienteID AND boleto.numBoleto = compra.numBoleto AND boleto.seccionId = seccion.seccionId AND seccion.descripcion = 'merida' ");
            registros=stConsulta.executeQuery();
            while (registros.next()){
                int id = registros.getInt("cliente.clienteID");
                String nombre = registros.getString("cliente.nombre");
                String apellido = registros.getString("cliente.apellidos");
                int rating = registros.getInt("cliente.rating");
                int edad = registros.getInt("cliente.edad");
                String pais = registros.getString("cliente.pais");
                String bye = registros.getString("cliente.bye");
                String seccion = registros.getString("seccion.descripcion");
                lis.add(new persona(id, nombre,apellido,rating,pais,edad,bye,seccion));
            }
            ObservableList<persona> list=FXCollections.observableArrayList(lis);
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void eliminarUser(int id){
        int clienteId = id + 4;
        System.out.println("sdsd" + clienteId);
        String query = "delete from compra where compraId = " + clienteId;
        int numRegs = 0;
        try {
            Statement stmt = con.createStatement();
            numRegs = stmt.executeUpdate(query);
            elimar2(id);
            elimar3(id);
            System.out.println("Cantidad de registros afectados: " + numRegs);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());
        }

    }
    public void elimar2(int id){
        String query = "delete from boleto where numBoleto = " + id;

        int numRegs = 0;
        try {
            Statement stmt = con.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());
        }

    }
    public void elimar3(int id){
        String query = "delete from cliente where clienteID = " + id;

        int numRegs = 0;
        try {
            Statement stmt = con.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());
        }

    }

}
