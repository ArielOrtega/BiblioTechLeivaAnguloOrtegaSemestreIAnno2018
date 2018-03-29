package Interface;

import Domain.Books;
import Domain.Loan;
import Domain.LogicalMethods;
import Domain.Student;
import File.BooksFile;
import File.StudentFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OptionalDataException;
import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import javafx.util.converter.LocalDateStringConverter;

public class InterfaceModules {
    
    static String genre, idiom;
    static int valueDelivery1, valueDelivery2, valueDelivery3;
    static LocalDate date1;
    StudentFile sft= new StudentFile();
    ObservableList<Student> observableArrayStudent= FXCollections.observableArrayList();
    ArrayList<Student> arrayListStudent= new ArrayList<>();   
    LogicalMethods methods= new LogicalMethods();

    public GridPane studentRegister() {
        GridPane gridpane = new GridPane();

        Label lbl_title = new Label("Registrar Estudiantes");
        Label lbl_name = new Label("Nombre");
        Label lbl_entryYear = new Label("Año de ingreso");

        TextField tf_name = new TextField();
        TextField tf_entryYear = new TextField();

        tf_name.setPromptText("Nombre");
        tf_entryYear.setPromptText("Año");;

        ComboBox<String> cb_career = new ComboBox<>();
        cb_career.getItems().addAll("Informática", "Administración", "Turismo");
        cb_career.setPromptText("Carrera");
        cb_career.setEditable(false);
        
        cb_career.setOnAction((event) -> {
            
            if (tf_name.getText().length()>0 && tf_entryYear.getText().length()>0 && cb_career.getValue().length()>0) {
                Student s = new Student(tf_name.getText(), tf_entryYear.getText(),
                        cb_career.getValue(), "metodo", methods.getStudentId(cb_career.getValue(), tf_entryYear.getText(), arrayListStudent.size()));

                observableArrayStudent.add(s);
                arrayListStudent.add(s);

                tf_name.clear();
                tf_entryYear.clear();
            }
            
        });

        gridpane.add(lbl_title, 0, 0);
        gridpane.add(lbl_name, 0, 2);
        gridpane.add(tf_name, 0, 3);
        gridpane.add(lbl_entryYear, 0, 5);
        gridpane.add(tf_entryYear, 0, 6);
        gridpane.add(cb_career, 0, 8);
        gridpane.setVgap(5);

        return gridpane;
    }

    public VBox showTableView() {
        TableView<Student> table = new TableView<>();

        TableColumn columnId = new TableColumn("Carnet");
        columnId.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn columnName = new TableColumn("Nombre");
        columnName.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn columnYear = new TableColumn("Año Ingreso");
        columnYear.setCellValueFactory(new PropertyValueFactory("entryYear"));

        TableColumn columnCareer = new TableColumn("Carrera");
        columnCareer.setCellValueFactory(new PropertyValueFactory("career"));

        TableColumn columnLoans = new TableColumn("Préstamos");
        columnLoans.setCellValueFactory(new PropertyValueFactory("previousLoans"));

        table.getColumns().addAll(columnId, columnName, columnYear, columnCareer, columnLoans);
        table.setItems(observableArrayStudent);
        table.setEditable(false);
        
        Button btn_addToFile= new Button("Añadir al archivo");
        btn_addToFile.setOnAction((event) -> {
            sft.writeFile(arrayListStudent);
        });
        
        Button btn_showRecords= new Button("Ver Registros");
        btn_showRecords.setOnAction((event) -> {
            try {
                table.setItems(sft.readFile());
            } catch (FileNotFoundException | ClassNotFoundException | OptionalDataException ex) {
                Logger.getLogger(InterfaceModules.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        HBox hbox_buttons= new HBox();
        hbox_buttons.getChildren().addAll(btn_addToFile, btn_showRecords);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table, hbox_buttons);

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

    public static GridPane enterAudioVisual() {

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

    public static GridPane viewMaterial() throws IOException {

        GridPane gpn_viewMaterial = new GridPane();
        gpn_viewMaterial.setAlignment(Pos.TOP_CENTER);
        gpn_viewMaterial.setPadding(new Insets(20));
        gpn_viewMaterial.setPrefSize(700, 800);

        TableView<Books> tvw_viewAudiovisual = new TableView();
        tvw_viewAudiovisual.setVisible(false);

//      Falta el observableList        
        TableColumn nameAVColumn = new TableColumn("Nombre");
        nameAVColumn.setMinWidth(150);
        nameAVColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn signatureAVColumn = new TableColumn("Signatura");
        signatureAVColumn.setMinWidth(150);
        signatureAVColumn.setCellValueFactory(new PropertyValueFactory<>("signature"));

        TableColumn brandAVColumn = new TableColumn("Marca");
        brandAVColumn.setMinWidth(150);
        brandAVColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

        TableColumn availabilityAVColumn = new TableColumn("Disponibilidad");
        availabilityAVColumn.setMinWidth(150);
        availabilityAVColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

        TableColumn descritionAVColumn = new TableColumn("Descripcion");
        descritionAVColumn.setMinWidth(150);
        descritionAVColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

        tvw_viewAudiovisual.getColumns().addAll(nameAVColumn, signatureAVColumn, brandAVColumn, availabilityAVColumn, descritionAVColumn);
        tvw_viewAudiovisual.setPrefSize(750, 475);
        tvw_viewAudiovisual.setTableMenuButtonVisible(true);
        gpn_viewMaterial.add(tvw_viewAudiovisual, 1, 1);

        TableView<Books> tvw_viewBooks = new TableView();

        BooksFile bfile = new BooksFile(new File("./Books.dat"));
        ObservableList<Books> datos = bfile.getBooks();
        tvw_viewBooks.setItems(datos);

        TableColumn nameBColumn = new TableColumn("Nombre");
        nameBColumn.setMinWidth(150);
        nameBColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn authorBColumn = new TableColumn("Autor");
        authorBColumn.setMinWidth(150);
        authorBColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn signatureBColumn = new TableColumn("Signatura");
        signatureBColumn.setMinWidth(150);
        signatureBColumn.setCellValueFactory(new PropertyValueFactory<>("signature"));

        TableColumn genreBColumn = new TableColumn("Género");
        genreBColumn.setMinWidth(150);
        genreBColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn lenguageBColumn = new TableColumn("Idioma");
        lenguageBColumn.setMinWidth(150);
        lenguageBColumn.setCellValueFactory(new PropertyValueFactory<>("lenguage"));

        TableColumn availabilityBColumn = new TableColumn("Disponibilidad");
        availabilityBColumn.setMinWidth(150);
        availabilityBColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

        TableColumn descriptionBColumn = new TableColumn("Descripción");
        descriptionBColumn.setMinWidth(150);
        descriptionBColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        tvw_viewBooks.getColumns().addAll(nameBColumn, authorBColumn, signatureBColumn, genreBColumn,
                lenguageBColumn, availabilityBColumn, descriptionBColumn);
        tvw_viewBooks.setPrefSize(1100, 475);
        gpn_viewMaterial.add(tvw_viewBooks, 1, 1);

        Button btn_viewBooks = new Button("Ver libros");
        btn_viewBooks.setOnAction((event) -> {
//            tvw_viewAudiovisual.setVisible(false);
            tvw_viewBooks.setVisible(true);
        });
        gpn_viewMaterial.add(btn_viewBooks, 0, 0);

        Button btn_viewAudiov = new Button("Ver Audiovisual");
        btn_viewAudiov.setOnAction((event) -> {
            tvw_viewBooks.setVisible(false);
//            tvw_viewAudiovisual.setVisible(true);            
        });
        gpn_viewMaterial.add(btn_viewAudiov, 1, 0);

        return gpn_viewMaterial;

    }

    public static GridPane enterBookLoan() {

        GridPane gpn_entBookL = new GridPane();
        gpn_entBookL.getColumnConstraints().add(new ColumnConstraints(200));
        gpn_entBookL.getColumnConstraints().add(new ColumnConstraints(250));
        gpn_entBookL.getColumnConstraints().add(new ColumnConstraints(150));
        gpn_entBookL.getColumnConstraints().add(new ColumnConstraints(200));
        gpn_entBookL.getColumnConstraints().add(new ColumnConstraints(250));
        gpn_entBookL.getRowConstraints().add(new RowConstraints(60));
        gpn_entBookL.getRowConstraints().add(new RowConstraints(60));
        gpn_entBookL.getRowConstraints().add(new RowConstraints(60));
        gpn_entBookL.getRowConstraints().add(new RowConstraints(60));
        gpn_entBookL.getRowConstraints().add(new RowConstraints(60));
        gpn_entBookL.getRowConstraints().add(new RowConstraints(60));
        gpn_entBookL.getRowConstraints().add(new RowConstraints(60));
        gpn_entBookL.setAlignment(Pos.CENTER);
        gpn_entBookL.setPadding(new Insets(20));
        gpn_entBookL.setPrefSize(300, 300);

        Label lbl_idStudent = new Label("Carné del Estudiante");
        lbl_idStudent.setTextFill(Color.BLACK);
        lbl_idStudent.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_entBookL.add(lbl_idStudent, 0, 0);

        TextField tfd_idStudent = new TextField();
        gpn_entBookL.add(tfd_idStudent, 1, 0);

        Label lbl_signature = new Label("Signatura del Libro");
        lbl_signature.setTextFill(Color.BLACK);
        lbl_signature.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_entBookL.add(lbl_signature, 3, 0);

        TextField tfd_signature = new TextField();
        gpn_entBookL.add(tfd_signature, 4, 0);

        Label lbl_deliveryDay = new Label("Día de Entrega");
        lbl_deliveryDay.setTextFill(Color.BLACK);
        lbl_deliveryDay.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_entBookL.add(lbl_deliveryDay, 0, 2);

        DatePicker dpk_loanDay = new DatePicker(LocalDate.now());
        dpk_loanDay.setEditable(false);

        DatePicker dpk_delivaeyDay = new DatePicker();
        dpk_delivaeyDay.setPrefWidth(250);
        dpk_delivaeyDay.setEditable(false);
        dpk_delivaeyDay.setShowWeekNumbers(true);
        dpk_delivaeyDay.setConverter(new LocalDateStringConverter(FormatStyle.FULL));
        dpk_delivaeyDay.setOnAction(new EventHandler() {
            public void handle(Event t) {

                date1 = dpk_delivaeyDay.getValue();
                valueDelivery1 = date1.getDayOfMonth();
                valueDelivery2 = date1.getMonthValue();
                valueDelivery3 = date1.getYear();
                dpk_delivaeyDay.setDisable(false);
            }
        });
        final Callback<DatePicker, DateCell> dayCellFactory
                = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(dpk_loanDay.getValue().plusDays(0))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        dpk_delivaeyDay.setDayCellFactory(dayCellFactory);
        dpk_delivaeyDay.setValue(dpk_loanDay.getValue().plusDays(0));
        gpn_entBookL.add(dpk_delivaeyDay, 1, 2);
        
        Button btn_enterLoan = new Button("Ingresar Prestamo");
        gpn_entBookL.add(btn_enterLoan, 4, 4);
        
        

        return gpn_entBookL;
    }

    public static GridPane viewLoans() {

        GridPane gpn_viewloans = new GridPane();
        gpn_viewloans.setAlignment(Pos.TOP_CENTER);
        gpn_viewloans.setPadding(new Insets(20));
        gpn_viewloans.setPrefSize(700, 800);

        TableView<Loan> tvw_viewLoan = new TableView();

//      Falta el observableList        
        TableColumn idColumn = new TableColumn("Carné");
        idColumn.setMinWidth(150);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        TableColumn signatureColumn = new TableColumn("Signatura");
        signatureColumn.setMinWidth(150);
        signatureColumn.setCellValueFactory(new PropertyValueFactory<>("signature"));

        TableColumn loanDayColumn = new TableColumn("Día de prestamo");
        loanDayColumn.setMinWidth(150);
        loanDayColumn.setCellValueFactory(new PropertyValueFactory<>("loanDay"));

        TableColumn deliveryColumn = new TableColumn("Dia de devolución");
        deliveryColumn.setMinWidth(150);
        deliveryColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryDay"));

        tvw_viewLoan.getColumns().addAll(idColumn, signatureColumn, loanDayColumn, deliveryColumn);
        tvw_viewLoan.setPrefSize(750, 500);
        tvw_viewLoan.setTableMenuButtonVisible(true);
        gpn_viewloans.add(tvw_viewLoan, 0, 0);

        return gpn_viewloans;

    }

}
