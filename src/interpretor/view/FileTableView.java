package interpretor.view;

import interpretor.collection.dictionary.IDictionary;
import interpretor.model.ProgramState;
import interpretor.utils.FilePair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by nicu on 1/24/2017.
 */
public class FileTableView {
    static TableView<IDictionary.IEntry<Integer, FilePair>> tableView;

    public static void initTableView(int width) {
        tableView = new TableView<>();

        TableColumn<IDictionary.IEntry<Integer, FilePair>, String> tableColumn0 = new TableColumn<>("Identifier");
        TableColumn<IDictionary.IEntry<Integer, FilePair>, String> tableColumn1 = new TableColumn<>("File name");

        tableColumn0.setMinWidth(width);
        tableColumn1.setMinWidth(width);
        tableColumn0.setCellValueFactory(new PropertyValueFactory<>("key"));
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("value"));

        tableView.getColumns().addAll(tableColumn0, tableColumn1);
    }

    public static void setTableView(ProgramState programState) {
        ObservableList<IDictionary.IEntry<Integer, FilePair>> observableList =
                FXCollections.observableArrayList(programState.getFileTable().entrySet());
        tableView.setItems(observableList);
    }
}

