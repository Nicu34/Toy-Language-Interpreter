package interpretor.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Created by nicu on 1/24/2017.
 */
public class ProgramListWindow {

    static Command selectedCommand;
    private static ListView<Command> listView;
    static Stage stageWindow = new Stage();

    public static void display(int width, int height) {
        Scene scene;

        stageWindow.setOnCloseRequest(event -> MainWindow.stageWindow.close());
        stageWindow.setTitle("Program list");

        Label label = new Label("Please select the program you want to execute");

        listView = new ListView<>();
        listView.getItems().addAll(ViewUtils.populateListOfPrograms());
        listView.getSelectionModel().select(0);
        listView.getFocusModel().focus(0);
        selectedCommand = listView.getSelectionModel().getSelectedItem();
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> onItemSelected());
        VBox vBoxLayout = new VBox(20);

        vBoxLayout.setPadding(new Insets(20, 20, 20, 20));
        vBoxLayout.getChildren().addAll(label, listView);
        scene = new Scene(vBoxLayout, width, height);
        stageWindow.setScene(scene);
        stageWindow.show();
    }

    private static void onItemSelected() {
        selectedCommand = listView.getSelectionModel().getSelectedItem();
        MainWindow.refresh();
    }
}
