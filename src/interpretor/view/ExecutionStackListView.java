package interpretor.view;

import interpretor.model.statement.IStatement;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

import java.util.Collections;
import java.util.List;

/**
 * Created by nicu on 1/24/2017.
 */
class ExecutionStackListView {
    static ListView<IStatement> listView = new ListView<>();

    static void setOutputListView() {
        List<IStatement> list = ProgramStateIdentifiersListView.programState.getExecutionStack().toJavaUtilList();

        Collections.reverse(list);
        listView.setItems(FXCollections.observableArrayList(list));
    }
}
