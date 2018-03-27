
package Interface;

import Domain.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class InterfaceModules {
    
    ObservableList<Student> testRecords=  FXCollections.observableArrayList();
    
    public GridPane studentRegister(){
        GridPane gridpane= new GridPane();
        
        
        Label lbl_title= new Label("Registrar Estudiantes");
        Label lbl_name= new Label("Nombre");
        Label lbl_entryYear= new Label("Año de ingreso");
        
        TextField tf_name= new TextField();
        TextField tf_entryYear= new TextField();
        
        tf_name.setPromptText("Nombre");
        tf_entryYear.setPromptText("Año");;
        
        ComboBox<String> cb_career= new ComboBox<>();
        cb_career.getItems().addAll("Informática","Administración","Turismo");
        cb_career.setPromptText("Carrera");
        cb_career.setEditable(false);
        
        Button btn_add= new Button("Agregar");
        btn_add.setOnAction((event) -> {
            testRecords.add(new Student(tf_name.getText(), tf_entryYear.getText(), 
                    cb_career.getValue(), "metodo", "metodo"));
        });
        
        gridpane.add(lbl_title,0, 0);
        gridpane.add(lbl_name, 0, 2);
        gridpane.add(tf_name, 0, 3);
        gridpane.add(lbl_entryYear, 0, 5);
        gridpane.add(tf_entryYear, 0, 6);
        gridpane.add(cb_career, 0, 8);
        gridpane.add(btn_add, 0, 10);
        gridpane.setVgap(5);
        
        return gridpane;
    }
    
    public VBox showTableView(){
        TableView<Student> table = new TableView<>();
        
        TableColumn columnId= new TableColumn("Carnet");
        columnId.setCellValueFactory(new PropertyValueFactory("id"));
        
        TableColumn columnName= new TableColumn("Nombre");
        columnName.setCellValueFactory(new PropertyValueFactory("name"));
        
        TableColumn columnYear= new TableColumn("Año Ingreso");
        columnYear.setCellValueFactory(new PropertyValueFactory("entryYear"));
        
        TableColumn columnCareer= new TableColumn("Carrera");
        columnCareer.setCellValueFactory(new PropertyValueFactory("career"));
    
        TableColumn columnLoans= new TableColumn("Préstamos");
        columnLoans.setCellValueFactory(new PropertyValueFactory("previousLoans"));    
        
        table.getColumns().addAll(columnId, columnName, columnYear, columnCareer, columnLoans);
        table.setItems(testRecords);
        table.setEditable(false);
        
        VBox vbox= new VBox();
        vbox.getChildren().addAll(table);
       
        return vbox;
    }
     
}
