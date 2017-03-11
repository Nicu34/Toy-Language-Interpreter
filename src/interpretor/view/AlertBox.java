package interpretor.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by nicu on 1/21/2017.
 */
public class AlertBox {

    public static void display(String title, String message) {
        Stage stageWindow = new Stage();

        stageWindow.initModality(Modality.APPLICATION_MODAL);
        stageWindow.setTitle(title);
        stageWindow.setWidth(500);
        stageWindow.setHeight(200);

        Label label = new Label();
        label.setText(message);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);

        stageWindow.setScene(scene);
        stageWindow.showAndWait();
    }
}
