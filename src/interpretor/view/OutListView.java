package interpretor.view;

import interpretor.controller.ProgramStateController;
import interpretor.model.ProgramState;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

/**
 * Created by nicu on 1/24/2017.
 */
class OutListView {
    static ListView<Integer> listView = new ListView<>();

    static void setOutputListView(ProgramState programState) {
        listView.setItems(FXCollections.observableArrayList(programState.getOutputList().toJavaUtilList()));
    }
}