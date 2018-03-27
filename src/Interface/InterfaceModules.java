package Interface;

import Domain.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class InterfaceModules {
    
    ObservableList<Student> testRecords=  FXCollections.observableArrayList();
    static String genre, idiom;
    
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
     
    public static GridPane enterBooks() {

        GridPane gpn_enterBooks = new GridPane();
        gpn_enterBooks.setPadding(new Insets(20));
        gpn_enterBooks.setPrefSize(300, 300);
        gpn_enterBooks.getColumnConstraints().add(new ColumnConstraints(200));
        gpn_enterBooks.getColumnConstraints().add(new ColumnConstraints(250));
        gpn_enterBooks.getColumnConstraints().add(new ColumnConstraints(150));
        gpn_enterBooks.getColumnConstraints().add(new ColumnConstraints(200));
        gpn_enterBooks.getColumnConstraints().add(new ColumnConstraints(250));
        gpn_enterBooks.getRowConstraints().add(new RowConstraints(60));
        gpn_enterBooks.getRowConstraints().add(new RowConstraints(60));
        gpn_enterBooks.getRowConstraints().add(new RowConstraints(60));
        gpn_enterBooks.getRowConstraints().add(new RowConstraints(60));
        gpn_enterBooks.getRowConstraints().add(new RowConstraints(60));
        gpn_enterBooks.getRowConstraints().add(new RowConstraints(60));
        gpn_enterBooks.getRowConstraints().add(new RowConstraints(60));
        gpn_enterBooks.setAlignment(Pos.CENTER);

        Label lbl_name = new Label("Título");
        lbl_name.setTextFill(Color.BLACK);
        lbl_name.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterBooks.add(lbl_name, 0, 0);

        TextField tfd_name = new TextField();
        gpn_enterBooks.add(tfd_name, 1, 0);

        Label lbl_signature = new Label("Signatura");
        lbl_signature.setTextFill(Color.BLACK);
        lbl_signature.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterBooks.add(lbl_signature, 3, 0);

        TextField tfd_signature = new TextField();
        gpn_enterBooks.add(tfd_signature, 4, 0);

        Label lbl_author = new Label("Autor");
        lbl_author.setTextFill(Color.BLACK);
        lbl_author.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterBooks.add(lbl_author, 0, 2);

        TextField tfd_author = new TextField();
        gpn_enterBooks.add(tfd_author, 1, 2);

        Label lbl_genre = new Label("Género");
        lbl_genre.setTextFill(Color.BLACK);
        lbl_genre.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterBooks.add(lbl_genre, 3, 2);

        ComboBox cbx_genre = new ComboBox();
        cbx_genre.setPrefWidth(400);
        cbx_genre.getItems().addAll("Bibliografía", "Clásicos de la Literatura", "Comics", "Ensayos",
                "Fantasía", "Ficción Literaria", "Historia", "Humor", "Infantil", "Poesía", "Romántico",
                "Académico", "Otro");

        cbx_genre.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                genre = t1;
            }
        });
        gpn_enterBooks.add(cbx_genre, 4, 2);
        
        Label lbl_idiom = new Label("Idioma");
        lbl_idiom.setTextFill(Color.BLACK);
        lbl_idiom.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterBooks.add(lbl_idiom, 0, 4);
        
        ComboBox cbx_idiom = new ComboBox();
        cbx_idiom.setPrefWidth(400);
        cbx_idiom.getItems().addAll("Inglés", "Español", "Chino", "Alemán",
                "Frances", "Portugués", "Otro");

        cbx_idiom.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                genre = t1;
            }
        });
        gpn_enterBooks.add(cbx_idiom, 1, 4);
        
        Label lbl_description = new Label("Descripción");
        lbl_description.setTextFill(Color.BLACK);
        lbl_description.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterBooks.add(lbl_description, 3, 4);
        
        TextArea txa_description = new TextArea();
        txa_description.setPrefSize(400, 500);
        gpn_enterBooks.add(txa_description, 4, 4);
        
        Button btn_enterBook = new Button("Ingresar Libro");
        gpn_enterBooks.add(btn_enterBook, 4, 6);
        
        return gpn_enterBooks;
    }
    
    public static GridPane enterAudioVisual(){
        
        GridPane gpn_enterAudioV = new GridPane();
        
        gpn_enterAudioV.getColumnConstraints().add(new ColumnConstraints(200));
        gpn_enterAudioV.getColumnConstraints().add(new ColumnConstraints(250));
        gpn_enterAudioV.getColumnConstraints().add(new ColumnConstraints(150));
        gpn_enterAudioV.getColumnConstraints().add(new ColumnConstraints(200));
        gpn_enterAudioV.getColumnConstraints().add(new ColumnConstraints(250));
        gpn_enterAudioV.getRowConstraints().add(new RowConstraints(60));
        gpn_enterAudioV.getRowConstraints().add(new RowConstraints(60));
        gpn_enterAudioV.getRowConstraints().add(new RowConstraints(60));
        gpn_enterAudioV.getRowConstraints().add(new RowConstraints(60));
        gpn_enterAudioV.getRowConstraints().add(new RowConstraints(60));
        gpn_enterAudioV.getRowConstraints().add(new RowConstraints(60));
        gpn_enterAudioV.getRowConstraints().add(new RowConstraints(60));
        gpn_enterAudioV.setAlignment(Pos.CENTER);
        gpn_enterAudioV.setPadding(new Insets(20));
        gpn_enterAudioV.setPrefSize(300, 300);
        
        Label lbl_kind = new Label("Tipo:");
        lbl_kind.setTextFill(Color.BLACK);
        lbl_kind.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterAudioV.add(lbl_kind, 0, 0);
        
        TextField tfd_kind = new TextField();
        gpn_enterAudioV.add(tfd_kind, 1, 0);
        
        Label lbl_signature = new Label("Signatura:");
        lbl_signature.setTextFill(Color.BLACK);
        lbl_signature.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterAudioV.add(lbl_signature, 3, 0);
        
        TextField tfd_signature = new TextField();
        gpn_enterAudioV.add(tfd_signature, 4, 0);
        
        Label lbl_brand = new Label("Marca:");
        lbl_brand.setTextFill(Color.BLACK);
        lbl_brand.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterAudioV.add(lbl_brand, 0, 2);
        
        TextField tfd_brand = new TextField();
        gpn_enterAudioV.add(tfd_brand, 1, 2);
        
        Label lbl_model = new Label("Modelo:");
        lbl_model.setTextFill(Color.BLACK);
        lbl_model.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterAudioV.add(lbl_model, 3, 2);
        
        TextField tfd_model = new TextField();
        gpn_enterAudioV.add(tfd_model, 4, 2);
        
        Label lbl_description = new Label("Descripción:");
        lbl_description.setTextFill(Color.BLACK);
        lbl_description.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterAudioV.add(lbl_description, 0, 4);
        
        TextArea txa_description = new TextArea();
        txa_description.setPrefSize(300, 400);
        gpn_enterAudioV.add(txa_description, 1, 4);
        
        Button btn_enterAudioV = new Button("Ingresar Artículo");
        gpn_enterAudioV.add(btn_enterAudioV, 4, 6);
            
        return gpn_enterAudioV;
    }


}
