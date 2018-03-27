package Interface;

import Domain.Books;
import File.BooksFile;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainInterface extends Application {

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        root.getChildren().add(menuBar());
        root.setTop(menuBar());

        Scene scene = new Scene(root, 500, 450);

        primaryStage.setTitle("Bibliotech");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(450);
        primaryStage.show();
    }

    public VBox menuBar() {
        VBox vbx_upperSide = new VBox();

        //Menu que despliega categorias principales
        Menu menuStudents = new Menu("Estudiantes");
        Menu menuMaterials = new Menu("Materiales");
        Menu menuLoans = new Menu("Prestamos");
        
        MenuBar mainMenu = new MenuBar();
        mainMenu.getMenus().addAll(menuStudents,menuMaterials,menuLoans);
        
        //Despliegue del logo en el programa
        Image img_logo = new Image(MainInterface.class.getResourceAsStream("/Images/logoBiblioT.png"));
        ImageView imv_logo = new ImageView();
        imv_logo.setImage(img_logo);
        imv_logo.setFitHeight(170);
        imv_logo.setFitWidth(300);
        
        //vbox captura los nodos
        vbx_upperSide.getChildren().addAll(imv_logo,mainMenu);

        return vbx_upperSide;
    }
   
    public static void main(String[] args) throws IOException {
        launch(args);
        
        
    }

}
