package Interface;

import Domain.Books;
import File.BooksFile;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainInterface extends Application {

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        root.getChildren().add(menuBar());
        root.setTop(menuBar());

        Scene scene = new Scene(root, 600, 600);

        primaryStage.setTitle("Bibliotech");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(450);
        primaryStage.show();
    }

    public VBox menuBar() {
        
        InterfaceModules im= new InterfaceModules();
        HBox hbx_window= new HBox();
        VBox vbx_upperSide = new VBox();
        
        hbx_window.setAlignment(Pos.CENTER);
        hbx_window.setSpacing(10);

        //Menu que despliega categorias principales
        Menu menuStudents = new Menu("Estudiantes");
        Menu menuMaterials = new Menu("Materiales");
        Menu menuLoans = new Menu("Prestamos");
        
        //Menu Items
        MenuItem addStudents= new MenuItem("Agregar Estudiante");
        addStudents.setOnAction((event) -> {
            hbx_window.getChildren().clear();
            hbx_window.getChildren().addAll(im.studentRegister(), im.showTableView());
        });
                 
        MenuBar mainMenu = new MenuBar();
        menuStudents.getItems().add(addStudents);
        mainMenu.getMenus().addAll(menuStudents,menuMaterials,menuLoans);
        
        //Despliegue del logo en el programa
        Image img_logo = new Image(MainInterface.class.getResourceAsStream("/Images/logoBiblioT.png"));
        ImageView imv_logo = new ImageView();
        imv_logo.setImage(img_logo);
        imv_logo.setFitHeight(170);
        imv_logo.setFitWidth(300);
        
        //vbox captura los nodos
        vbx_upperSide.getChildren().addAll(imv_logo,mainMenu, hbx_window);

        return vbx_upperSide;
    }
   
    public static void main(String[] args) throws IOException {
        launch(args);
        
        
    }

}
