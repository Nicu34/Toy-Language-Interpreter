package interpretor;

import interpretor.view.MainWindow;
import interpretor.view.ProgramListWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by nicu on 1/24/2017.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ProgramListWindow.display(1100, 500);
        MainWindow.display(1900, 800);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
