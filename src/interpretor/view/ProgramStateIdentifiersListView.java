package interpretor.view;

import interpretor.controller.ProgramStateController;
import interpretor.model.ProgramState;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

import java.util.stream.Collectors;

/**
 * Created by nicu on 1/24/2017.
 */
public class ProgramStateIdentifiersListView {
    static ListView<Integer> listView = new ListView<>();
    static ProgramState programState;

    static void initProgramStateIdentifiersListView(ProgramStateController programStateController, int width) {
        listView.setMinWidth(width);
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> onSelected());
    }

    static void setProgramStateIdentifiersListView(ProgramStateController programStateController) {
        listView.setItems(FXCollections.observableArrayList(programStateController.getProgramStateList().stream().map(ProgramState::getId).collect(Collectors.toList())));
//        programState = programStateController.getProgramStateList().get(0);
        listView.getSelectionModel().select(0);
        listView.getFocusModel().focus(0);
    }

    private static void onSelected() {
        Integer selectedId = listView.getSelectionModel().getSelectedItem();
        if (selectedId != null) {
            programState = ProgramListWindow.selectedCommand.getProgramStateController().getProgramStateList().stream().filter(programState -> programState.getId().equals(selectedId)).findFirst().get();
        }
        else {
            programState = ProgramListWindow.selectedCommand.getProgramStateController().getProgramStateList().get(0);
        }
        SymbolTableView.setTableView();
        ExecutionStackListView.setOutputListView();
    }
}
