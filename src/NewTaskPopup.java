import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewTaskPopup extends Application {

    @Override
    public void start(Stage stage){
        int[] APP_SIZE = {200,200};
        Pane root = new Pane();
        Scene scene = new Scene(root, APP_SIZE[0], APP_SIZE[1]);
        root.setMaxSize(APP_SIZE[0], APP_SIZE[1]);
        root.setMinSize(APP_SIZE[0], APP_SIZE[1]);
        stage.setResizable(false);

        //GUI STUFF HERE


        //root.getChildren().addAll(application);

        stage.setScene(scene);
        stage.showAndWait();

    }

}
