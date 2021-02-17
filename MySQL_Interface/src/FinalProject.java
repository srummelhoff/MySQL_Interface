import java.sql.*;
import java.util.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.text.Position;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;


public class FinalProject extends Application {
    private HBox hbox = new HBox(22);
    private BorderPane bPane = new BorderPane();
    private Insets in = new Insets(10);
    private ComboBox<String> select = new ComboBox();
    private ComboBox<String> view = new ComboBox();
    private ComboBox<String> delete = new ComboBox();
    private ComboBox<String> insert = new ComboBox();
    private GridPane gpane = new GridPane();
    private String[] ATables = new String[] {"Product", "Stores", "Suppliers", "Consumer", "Preferences"};
    private String url = "jdbc:mysql://cs.neiu.edu:3306/cs315sum19_srummelhoff"
                + "?serverTimezone=UTC&user=srummelhoff&password=srummelhoff652047";
    private Connection con;
    private Statement stmt;
    private VBox vbox = new VBox();
    
    
    @Override
    public void start(Stage primaryStage)  {
        
        select.setOnAction(e -> {
            setUpSelectOptions(select.getValue());
        });
        view.setOnAction(e -> {
            setUpViewOptions(view.getValue());
        });
        insert.setOnAction(e -> {
            setUpInsertOptions(insert.getValue());
        });
        delete.setOnAction(e -> {
            setUpDeleteOptions(delete.getValue());
        });
        
        
        setUpGPane();
        setUpComboBoxes();
        
        bPane.setPadding(in);
        bPane.setStyle("-fx-background-color: beige; ");
        
        hbox.setPadding(in);
        hbox.getChildren().addAll(select, view, delete, insert);
        
        
        bPane.setTop(hbox);
        bPane.setCenter(gpane);
        
        
        Scene scene = new Scene(bPane, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BorderPane Example");
        primaryStage.show();
    }
    
    public void setUpComboBoxes(){
        ArrayList<String> aLTables = new ArrayList<>(Arrays.asList(ATables));
        ObservableList<String> oLTables = FXCollections.observableArrayList(aLTables);
        
        select.setItems(oLTables);
        select.setPromptText("Select");
        view.setItems(oLTables);
        view.setPromptText("View");
        insert.setItems(oLTables);
        insert.setPromptText("Insert");
        delete.setItems(oLTables);
        delete.setPromptText("Delete");
        
    }
    
    public void setUpSelectOptions(String e){
        gpane.getChildren().clear();
        ArrayList<Text> text = new ArrayList();
        ArrayList<VBox> vboxList = new ArrayList();
        int i = 0;
        int j = 0;
        int k = 0;
        if("Product".equals(e)){
            try{
                
                String query = "SELECT * FROM " + e;
                con = DriverManager.getConnection(url);
                stmt = con.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);
                
                while(resultSet.next()){
                    
                    j=0;
                    int productNo = resultSet.getInt("productNo");
                    text.add(new Text(String.valueOf(productNo)));
                    String name = resultSet.getString("name");
                    text.add(new Text(name));
                    String color = resultSet.getString("color");
                    text.add(new Text(color));
                    String material = resultSet.getString("material");
                    text.add(new Text(material));
                    String texture = resultSet.getString("texture");
                    text.add(new Text(texture));
                    int storeNo = resultSet.getInt("storeNo");
                    text.add(new Text(String.valueOf(storeNo)));
                    int supplierNo = resultSet.getInt("supplierNo");
                    text.add(new Text(String.valueOf(supplierNo)));
                    String country = resultSet.getString("country");
                    text.add(new Text(country));
                    
                    while(j<8){
                        VBox temp = new VBox();
                        temp.setPadding(in);
                        temp.getChildren().add(text.get(k));
                        vboxList.add(temp);
                        gpane.add(vboxList.get(k), j, i);
                        k++;
                        j++;
                    }
          
                    i++;
                }
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        if("Consumer".equals(e)){
            try{
                String query = "SELECT * FROM " + e;
                con = DriverManager.getConnection(url);
                stmt = con.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);
                while(resultSet.next()){
                    j=0;
                    int creditCard = resultSet.getInt("creditCard");
                    text.add(new Text(String.valueOf(creditCard)));
                    String name = resultSet.getString("name");
                    text.add(new Text(name));
                    String address = resultSet.getString("address");
                    text.add(new Text(address));
                    String city = resultSet.getString("city");
                    text.add(new Text(city));
                    String state = resultSet.getString("state");
                    text.add(new Text(state));
                    String country = resultSet.getString("country");
                    text.add(new Text(country));
                    int prefNo = resultSet.getInt("prefNo");
                    text.add(new Text(String.valueOf(prefNo)));
                    
                    while(j<7){
                        VBox temp = new VBox();
                        temp.setPadding(in);
                        temp.getChildren().add(text.get(k));
                        vboxList.add(temp);
                        gpane.add(vboxList.get(k), j, i);
                        k++;
                        j++;
                    }
          
                    i++;
                }
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        if("Suppliers".equals(e)){
            try{
                String query = "SELECT * FROM " + e;
                con = DriverManager.getConnection(url);
                stmt = con.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);
                
                while(resultSet.next()){
                    j=0;
                    int supplierNo = resultSet.getInt("supplierNo");
                    text.add(new Text(String.valueOf(supplierNo)));
                    String name = resultSet.getString("name");
                    text.add(new Text(name));
                    String country = resultSet.getString("country");
                    text.add(new Text(country));
                    int moq = resultSet.getInt("moq");
                    text.add(new Text(String.valueOf(moq)));
                    
                    while(j<4){
                        VBox temp = new VBox();
                        temp.setPadding(in);
                        temp.getChildren().add(text.get(k));
                        vboxList.add(temp);
                        gpane.add(vboxList.get(k), j, i);
                        k++;
                        j++;
                    }
          
                    i++;
                }
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        if("Stores".equals(e)){
            try{
                String query = "SELECT * FROM " + e;
                con = DriverManager.getConnection(url);
                stmt = con.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);
                while(resultSet.next()){
                    j=0;
                    int storeNo = resultSet.getInt("storeNo");
                    text.add(new Text(String.valueOf(storeNo)));
                    String name = resultSet.getString("name");
                    text.add(new Text(name));
                    String country = resultSet.getString("country");
                    text.add(new Text(country));
                    String url = resultSet.getString("url");
                    text.add(new Text(url));
                    
                    while(j<4){
                        VBox temp = new VBox();
                        temp.setPadding(in);
                        temp.getChildren().add(text.get(k));
                        vboxList.add(temp);
                        gpane.add(vboxList.get(k), j, i);
                        k++;
                        j++;
                    }
          
                    i++;
                    
                }
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        if("Preferences".equals(e)){
            try{
                String query = "SELECT * FROM " + e;
                con = DriverManager.getConnection(url);
                stmt = con.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);
                while(resultSet.next()){
                    j=0;
                    int prefNo = resultSet.getInt("prefNo");
                    text.add(new Text(String.valueOf(prefNo)));
                    String color = resultSet.getString("color");
                    text.add(new Text(color));
                    String material = resultSet.getString("material");
                    text.add(new Text(material));
                    String texture = resultSet.getString("texture");
                    text.add(new Text(texture));
                    String country = resultSet.getString("country");
                    text.add(new Text(country));
                    
                    while(j<5){
                        VBox temp = new VBox();
                        temp.setPadding(in);
                        temp.getChildren().add(text.get(k));
                        vboxList.add(temp);
                        gpane.add(vboxList.get(k), j, i);
                        k++;
                        j++;
                    }
          
                    i++;
                }
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    public void setUpViewOptions(String e){
        
    }
    public void setUpInsertOptions(String e){
        
    }
    public void setUpDeleteOptions(String e){
        
    }
    public void setUpGPane(){
        gpane.setPadding(in);
        
        gpane.setGridLinesVisible(true);
        gpane.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(in);
    }

    public static void main(String[] args){
        launch(args);
    }
}
