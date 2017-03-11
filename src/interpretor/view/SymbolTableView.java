package interpretor.view;

import interpretor.collection.dictionary.IDictionary;
import interpretor.model.ProgramState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by nicu on 1/24/2017.
 */
public class SymbolTableView {
    static TableView<IDictionary.IEntry<String, Integer>> tableView;

    public static void initTableView(int width) {
        tableView = new TableView<>();

        TableColumn<IDictionary.IEntry<String, Integer>, String> tableColumn0 = new TableColumn<>("Variable");
        TableColumn<IDictionary.IEntry<String, Integer>, String> tableColumn1 = new TableColumn<>("Value");

        tableColumn0.setMinWidth(width);
        tableColumn1.setMinWidth(width);
        tableColumn0.setCellValueFactory(new PropertyValueFactory<>("key"));
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("value"));

        tableView.getColumns().addAll(tableColumn0, tableColumn1);
    }

    public static void setTableView() {
        ObservableList<IDictionary.IEntry<String, Integer>> observableList =
                FXCollections.observableArrayList(ProgramStateIdentifiersListView.programState.getSymbolTable().entrySet());
        tableView.setItems(observableList);
    }
}
