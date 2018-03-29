package Interface;

import Domain.Books;
import Domain.Loan;
import Domain.Student;
import File.BooksFile;
import File.LoanFile;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.FormatStyle;
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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import javafx.util.converter.LocalDateStringConverter;

public class InterfaceModules {

    ObservableList<Student> testRecords = FXCollections.observableArrayList();
    static String genre, idiom;
    static int valueDelivery1, valueDelivery2, valueDelivery3;
    static LocalDate date1;
    static Label lbl_choise, lbl_signature, lbl_deliveryDay, lbl_warning, lbl_success, lbl_idStudent, lbl_info;
    static RadioButton rdb_choiceBook, rdb_choiceAV;
    static TextField tfd_signatureB, tfd_idStudent, tfd_signatureAV;
    static DatePicker dpk_delivaeyDay;
    static Button btn_enterLoan, btn_checkStudent, btn_exit;
    static Loan loan1;

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

        Button btn_add = new Button("Agregar");
        btn_add.setOnAction((event) -> {
            testRecords.add(new Student(tf_name.getText(), tf_entryYear.getText(),
                    cb_career.getValue(), "metodo", "metodo"));
        });

        gridpane.add(lbl_title, 0, 0);
        gridpane.add(lbl_name, 0, 2);
        gridpane.add(tf_name, 0, 3);
        gridpane.add(lbl_entryYear, 0, 5);
        gridpane.add(tf_entryYear, 0, 6);
        gridpane.add(cb_career, 0, 8);
        gridpane.add(btn_add, 0, 10);
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
        table.setItems(testRecords);
        table.setEditable(false);

        VBox vbox = new VBox();
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

        Label lbl_signatureB = new Label("Signatura");
        lbl_signatureB.setTextFill(Color.BLACK);
        lbl_signatureB.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterBooks.add(lbl_signatureB, 3, 0);

        TextField tfd_signatureB = new TextField();
        gpn_enterBooks.add(tfd_signatureB, 4, 0);

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

        Label lbl_signatureAV = new Label("Signatura:");
        lbl_signatureAV.setTextFill(Color.BLACK);
        lbl_signatureAV.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterAudioV.add(lbl_signatureAV, 3, 0);

        TextField tfd_signatureAV = new TextField();
        gpn_enterAudioV.add(tfd_signatureAV, 4, 0);

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
    }//end method

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
            tvw_viewAudiovisual.setVisible(false);
            tvw_viewBooks.setVisible(true);
        });
        gpn_viewMaterial.add(btn_viewBooks, 0, 0);

        Button btn_viewAudiov = new Button("Ver Audiovisual");
        btn_viewAudiov.setOnAction((event) -> {
            tvw_viewBooks.setVisible(false);
            tvw_viewAudiovisual.setVisible(true);
        });
        gpn_viewMaterial.add(btn_viewAudiov, 1, 0);

        return gpn_viewMaterial;

    }

    public static GridPane enterLoan() throws IOException {

        LoanFile lFile = new LoanFile(new File("./Loans.dat"));

        GridPane gpn_enterLoan = new GridPane();

        //Acomodar las columnas y las filas en el tamaño que sea necesario
        gpn_enterLoan.getColumnConstraints().add(new ColumnConstraints(200));
        gpn_enterLoan.getColumnConstraints().add(new ColumnConstraints(250));
        gpn_enterLoan.getColumnConstraints().add(new ColumnConstraints(150));
        gpn_enterLoan.getColumnConstraints().add(new ColumnConstraints(200));
        gpn_enterLoan.getColumnConstraints().add(new ColumnConstraints(250));
        gpn_enterLoan.getRowConstraints().add(new RowConstraints(60));
        gpn_enterLoan.getRowConstraints().add(new RowConstraints(60));
        gpn_enterLoan.getRowConstraints().add(new RowConstraints(60));
        gpn_enterLoan.getRowConstraints().add(new RowConstraints(60));
        gpn_enterLoan.getRowConstraints().add(new RowConstraints(60));
        gpn_enterLoan.getRowConstraints().add(new RowConstraints(60));
        gpn_enterLoan.getRowConstraints().add(new RowConstraints(60));
        gpn_enterLoan.setAlignment(Pos.CENTER);
        gpn_enterLoan.setPadding(new Insets(20));
        gpn_enterLoan.setPrefSize(300, 300);

        lbl_idStudent = new Label("Carné del Estudiante");
        lbl_idStudent.setTextFill(Color.BLACK);
        lbl_idStudent.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterLoan.add(lbl_idStudent, 0, 3);

        tfd_idStudent = new TextField();
        gpn_enterLoan.add(tfd_idStudent, 1, 3);

        btn_checkStudent = new Button("Ingresar");
        btn_checkStudent.setOnAction((event) -> {

            String student = tfd_idStudent.getText().replaceAll(" ", "");
            lbl_idStudent.setVisible(false);
            tfd_idStudent.setVisible(false);
            btn_checkStudent.setVisible(false);
            lbl_choise.setVisible(true);
            rdb_choiceAV.setVisible(true);
            rdb_choiceBook.setVisible(true);
            

        });//end Button
        gpn_enterLoan.add(btn_checkStudent, 3, 3);

        lbl_choise = new Label("Seleccione una opción");
        lbl_choise.setVisible(false);
        lbl_choise.setTextFill(Color.BLACK);
        lbl_choise.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterLoan.add(lbl_choise, 0, 0);

        lbl_signature = new Label("Signatura del Artículo");
        lbl_signature.setVisible(false);
        lbl_signature.setTextFill(Color.BLACK);
        lbl_signature.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterLoan.add(lbl_signature, 0, 2);

        tfd_signatureB = new TextField("ISBN-");
        tfd_signatureB.setVisible(false);
//        tfd_signatureAV.setText("ISBN");
        gpn_enterLoan.add(tfd_signatureB, 1, 2);

        lbl_deliveryDay = new Label("Día de Entrega");
        lbl_deliveryDay.setVisible(false);
        lbl_deliveryDay.setTextFill(Color.BLACK);
        lbl_deliveryDay.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterLoan.add(lbl_deliveryDay, 3, 2);

        DatePicker dpk_loanDay = new DatePicker(LocalDate.now());
        dpk_loanDay.setEditable(false);

        dpk_delivaeyDay = new DatePicker();
        dpk_delivaeyDay.setPrefWidth(250);
        dpk_delivaeyDay.setEditable(false);
        dpk_delivaeyDay.setVisible(false);
        dpk_delivaeyDay.setShowWeekNumbers(true);
        dpk_delivaeyDay.setConverter(new LocalDateStringConverter(FormatStyle.FULL)); //Fecha en un formato String

        dpk_delivaeyDay.setOnAction(new EventHandler() {
            public void handle(Event t) {
                date1 = dpk_delivaeyDay.getValue();
                valueDelivery1 = date1.getDayOfMonth();
                valueDelivery2 = date1.getMonthValue();
                valueDelivery3 = date1.getYear();
                dpk_delivaeyDay.setDisable(false);
            }
        });

        //Metodo para deshabilitar los dias anteriores al actual
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
        gpn_enterLoan.add(dpk_delivaeyDay, 4, 2);

        lbl_warning = new Label("Prestamo no registrado");
        lbl_warning.setVisible(false);
        lbl_warning.setTextFill(Color.RED);
        lbl_warning.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterLoan.add(lbl_warning, 3, 4, 4, 6);

        lbl_success = new Label("Se registro el prestamo");
        lbl_success.setVisible(false);
        lbl_success.setTextFill(Color.GREEN);
        lbl_success.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_enterLoan.add(lbl_success, 3, 4, 4, 6);

        ToggleGroup group = new ToggleGroup();

        rdb_choiceBook = new RadioButton("Libro");
        rdb_choiceBook.setVisible(false);        
        rdb_choiceBook.setToggleGroup(group);

        rdb_choiceBook.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                tfd_signatureAV.setVisible(false);
                lbl_signature.setVisible(true);
                tfd_signatureB.setVisible(true);
                lbl_deliveryDay.setVisible(true);
                dpk_delivaeyDay.setVisible(true);
                btn_enterLoan.setVisible(true);
                btn_exit.setVisible(true);
                tfd_signatureB.setText("ISBN-");
                dpk_delivaeyDay.setValue(LocalDate.now());

            }
        });
        gpn_enterLoan.add(rdb_choiceBook, 0, 1);

        tfd_signatureAV = new TextField();
        tfd_signatureAV.setVisible(false);
        //metodo para establecer un tamaño maximo de ingreso de valores en un TextField
        tfd_signatureAV.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number beforeValue, Number actualValue) {
                if (actualValue.intValue() > beforeValue.intValue()) {
                    // Revisa que la longitud del texto no sea mayor a la variable definida.
                    if (tfd_signatureAV.getText().length() >= 5) {
                        tfd_signatureAV.setText(tfd_signatureAV.getText().substring(0, 5));
                    }
                }
            }
        });
        gpn_enterLoan.add(tfd_signatureAV, 1, 2);

        rdb_choiceAV = new RadioButton("Audiovisual");
        rdb_choiceAV.setVisible(false);
        rdb_choiceAV.setToggleGroup(group);
        rdb_choiceAV.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                tfd_signatureB.setVisible(false);
                lbl_signature.setVisible(true);
                tfd_signatureAV.setVisible(true);
                lbl_deliveryDay.setVisible(true);
                dpk_delivaeyDay.setVisible(true);
                btn_enterLoan.setVisible(true);
                btn_exit.setVisible(true);
                tfd_signatureAV.setText("");
                dpk_delivaeyDay.setValue(LocalDate.now());

            }
        });
        gpn_enterLoan.add(rdb_choiceAV, 1, 1);
        
        lbl_info = new Label("Salir para realizar otro prestamo");
        lbl_info.setVisible(false);
        gpn_enterLoan.add(lbl_info, 2, 6, 3, 6);

        btn_enterLoan = new Button("Ingresar Prestamo");
        btn_enterLoan.setVisible(false);
        btn_enterLoan.setOnAction((event) -> {
            String idStudent = tfd_idStudent.getText().replaceAll(" ", "");
            String signatureB = tfd_signatureB.getText().replaceAll(" ", "");
            String signatureAV = tfd_signatureAV.getText().replaceAll(" ", "");
            String loanDay = "" + LocalDate.now();
            String deliveryDay = valueDelivery3 + "-" + valueDelivery1 + "-" + valueDelivery2;
            String kind = "";

            if (rdb_choiceAV.isSelected()) {
                kind = "Audiovisual";
                loan1 = new Loan(idStudent, signatureAV, loanDay, deliveryDay, kind);
            } else {
                kind = "Libro";
                loan1 = new Loan(idStudent, signatureB, loanDay, deliveryDay, kind);
            }

            try {

                lFile.addEndRecord(loan1);
                lFile.close();
               
                tfd_signatureB.setDisable(true); 
                tfd_signatureAV.setDisable(true);
                dpk_delivaeyDay.setDisable(true);                
                rdb_choiceBook.setDisable(true);
                rdb_choiceAV.setDisable(true);
                btn_enterLoan.setDisable(true);
                lbl_info.setVisible(true);
                
                //Se limpian los valores anteriormente ingresados 
                tfd_idStudent.setText("");
                tfd_signatureB.setText("");
                dpk_delivaeyDay.setValue(LocalDate.now()); //Se asigna el valor por defecto del DatePicker

            } catch (IOException ex) {
                Logger.getLogger(InterfaceModules.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        gpn_enterLoan.add(btn_enterLoan, 4, 4);

        btn_exit = new Button("Salir");
        btn_exit.setVisible(false);
        btn_exit.setOnAction((event) -> {
            lbl_signature.setVisible(false);
            tfd_signatureB.setVisible(false);
            lbl_deliveryDay.setVisible(false);
            dpk_delivaeyDay.setVisible(false);
            lbl_choise.setVisible(false);
            rdb_choiceBook.setVisible(false);
            rdb_choiceAV.setVisible(false);
            btn_enterLoan.setVisible(false);
            lbl_warning.setVisible(false);
            lbl_success.setVisible(false);
            lbl_info.setVisible(false);
            btn_exit.setVisible(false);
            tfd_signatureAV.setVisible(false);
            lbl_idStudent.setVisible(true);
            tfd_idStudent.setVisible(true);
            btn_checkStudent.setVisible(true);
        });
        gpn_enterLoan.add(btn_exit, 4, 6);

        return gpn_enterLoan;

    }//end method

    public static GridPane deleteLoans() throws IOException {

        LoanFile lfile = new LoanFile(new File("./Loans.dat"));

        GridPane gpn_deleteLoan = new GridPane();
        gpn_deleteLoan.getColumnConstraints().add(new ColumnConstraints(200));
        gpn_deleteLoan.getColumnConstraints().add(new ColumnConstraints(250));
        gpn_deleteLoan.getColumnConstraints().add(new ColumnConstraints(150));
        gpn_deleteLoan.getColumnConstraints().add(new ColumnConstraints(200));
        gpn_deleteLoan.getColumnConstraints().add(new ColumnConstraints(250));
        gpn_deleteLoan.getRowConstraints().add(new RowConstraints(60));
        gpn_deleteLoan.getRowConstraints().add(new RowConstraints(60));
        gpn_deleteLoan.getRowConstraints().add(new RowConstraints(60));
        gpn_deleteLoan.getRowConstraints().add(new RowConstraints(60));
        gpn_deleteLoan.getRowConstraints().add(new RowConstraints(60));
        gpn_deleteLoan.getRowConstraints().add(new RowConstraints(60));
        gpn_deleteLoan.getRowConstraints().add(new RowConstraints(60));
        gpn_deleteLoan.setAlignment(Pos.CENTER);
        gpn_deleteLoan.setPadding(new Insets(20));
        gpn_deleteLoan.setPrefSize(300, 300);

        Label lbl_signatLoan = new Label("Signatura");
        gpn_deleteLoan.add(lbl_signatLoan, 0, 0);

        TextField tfd_signatLoan = new TextField();
//        tfd_signatLoan.setOnKeyPressed((event) -> {
//           
//            String signature = tfd_signatLoan.getText();
//            
//            
//        });
        gpn_deleteLoan.add(tfd_signatLoan, 1, 0);

        Label lbl_warningL = new Label("Prestamo no resgistrado");
        lbl_warningL.setVisible(false);
        lbl_warningL.setTextFill(Color.RED);
        gpn_deleteLoan.add(lbl_warningL, 4, 0);
        
        Label lbl_successL = new Label("Prestamo registrado");
        lbl_successL.setTextFill(Color.RED);
        lbl_successL.setVisible(false);
        gpn_deleteLoan.add(lbl_successL, 4, 0);

        Button btn_search = new Button("Buscar");
        btn_search.setOnAction((event) -> {

            String signature = tfd_signatLoan.getText();

            try {
                if (lfile.searchLoan(signature) == -1) {
                    lbl_warningL.setVisible(true);
                } else {
                    lfile.deleteLoan(signature);
                    lfile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(InterfaceModules.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        gpn_deleteLoan.add(btn_search, 3, 0);

//        TableView<Loan> tvw_deleteLoan = new TableView();      
//
//        TableColumn idColumn = new TableColumn("Carné");
//        idColumn.setMinWidth(150);
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
//
//        TableColumn signatureColumn = new TableColumn("Signatura");
//        signatureColumn.setMinWidth(150);
//        signatureColumn.setCellValueFactory(new PropertyValueFactory<>("signature"));
//
//        TableColumn loanDayColumn = new TableColumn("Día de prestamo");
//        loanDayColumn.setMinWidth(150);
//        loanDayColumn.setCellValueFactory(new PropertyValueFactory<>("loanDay"));
//
//        TableColumn deliveryColumn = new TableColumn("Dia de devolución");
//        deliveryColumn.setMinWidth(150);
//        deliveryColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryDay"));
//
//        tvw_deleteLoan.getColumns().addAll(idColumn, signatureColumn, loanDayColumn, deliveryColumn);
//        tvw_deleteLoan.setPrefSize(600, 500);
//        tvw_deleteLoan.setTableMenuButtonVisible(true);
//        gpn_deleteLoan.add(tvw_deleteLoan, 0, 0);
        return gpn_deleteLoan;

    }

    public static GridPane viewLoans() throws IOException {

        GridPane gpn_viewloans = new GridPane();
        gpn_viewloans.setAlignment(Pos.TOP_CENTER);
        gpn_viewloans.setPadding(new Insets(20));
        gpn_viewloans.setPrefSize(700, 800);

        TableView<Loan> tvw_viewLoan = new TableView();

        LoanFile lfile = new LoanFile(new File("./Loans.dat"));
        ObservableList<Loan> data = lfile.getAllLoans();
        tvw_viewLoan.setItems(data);

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

        TableColumn kindColumn = new TableColumn("Artículo");
        kindColumn.setMinWidth(150);
        kindColumn.setCellValueFactory(new PropertyValueFactory<>("kind"));

        tvw_viewLoan.getColumns().addAll(idColumn, signatureColumn, loanDayColumn, deliveryColumn, kindColumn);
        tvw_viewLoan.setPrefSize(750, 500);
        tvw_viewLoan.setTableMenuButtonVisible(true);
        gpn_viewloans.add(tvw_viewLoan, 0, 0);

        return gpn_viewloans;

    }

}
