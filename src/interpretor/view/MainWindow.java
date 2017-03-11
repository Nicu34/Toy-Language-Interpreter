package interpretor.view;

import interpretor.controller.ProgramStateController;
import interpretor.model.ProgramState;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by nicu on 1/24/2017.
 */
public class MainWindow {
    static Stage stageWindow = new Stage();

    public static void display(int width, int height) {
        Scene scene;

        stageWindow.setTitle("Application main window");
        stageWindow.setOnCloseRequest(event -> ProgramListWindow.stageWindow.close());

        HBox hBoxLayout0 = new HBox(10);
        hBoxLayout0.setPadding(new Insets(20, 20, 20, 20));
        Label label0 = new Label("Heap Table");
        label0.setMinWidth(250);
        Label label1 = new Label("Output List");
        label1.setMinWidth(250);
        Label label2 = new Label("File Table");
        label2.setMinWidth(250);
        Label label3 = new Label("Program State Identifiers List");
        label3.setMinWidth(250);
        Label label4 = new Label("Symbol table");
        label4.setMinWidth(250);
        Label label5 = new Label("Execution Stack List");
        label5.setMinWidth(250);
        Label label6 = new Label("Latch table");
        label6.setMinWidth(250);
        hBoxLayout0.getChildren().addAll(label0, label1, label2, label3, label4, label5, label6);

        HBox hBoxLayout1 = new HBox(10);
        hBoxLayout1.setPadding(new Insets(20, 20, 20, 20));
        HeapTableView.initTableView(125);
        OutListView.listView.setMinWidth(125);
        FileTableView.initTableView(125);
        ProgramStateIdentifiersListView.initProgramStateIdentifiersListView(ProgramListWindow.selectedCommand.getProgramStateController(), 125);
        SymbolTableView.initTableView(125);
        ExecutionStackListView.listView.setMinWidth(125);
        LatchTableView.initTableView(125);
        hBoxLayout1.getChildren().addAll(HeapTableView.tableView, OutListView.listView, FileTableView.tableView, ProgramStateIdentifiersListView.listView, SymbolTableView.tableView, ExecutionStackListView.listView, LatchTableView.tableView);

        ProgramStatesNumber.textField.setMinWidth(300);
        VBox vBoxLayout = new VBox(10);
        vBoxLayout.setPadding(new Insets(20, 20, 20, 20));
        vBoxLayout.getChildren().addAll(hBoxLayout0, hBoxLayout1, ProgramStatesNumber.textField, createAllStepButton());

        scene = new Scene(vBoxLayout, width, height);
        stageWindow.setScene(scene);
        stageWindow.show();
    }

    static void refresh() {
        ProgramStateController programStateController = ProgramListWindow.selectedCommand.getProgramStateController();
        ProgramState programState = programStateController.getCurrentProgramState();

        System.out.println(programState);
        ProgramStatesNumber.setNewProgramStatesNumber(programStateController);
        HeapTableView.setTableView(programState);
        OutListView.setOutputListView(programState);
        FileTableView.setTableView(programState);
        ProgramStateIdentifiersListView.setProgramStateIdentifiersListView(programStateController);
        SymbolTableView.setTableView();
        ExecutionStackListView.setOutputListView();
        LatchTableView.setTableView();
    }

    private static Button createAllStepButton() {
        Button allStepButton = new Button("Run one step");

        allStepButton.setMinWidth(200);
        allStepButton.setOnAction(event -> {
            if (ProgramListWindow.selectedCommand != null) {
                ProgramListWindow.selectedCommand.execute();
                refresh();
            }
            else {
                AlertBox.display("Error.", "No program selected. Please select a program from the other window.");
            }
        });

        return allStepButton;
    }
}
