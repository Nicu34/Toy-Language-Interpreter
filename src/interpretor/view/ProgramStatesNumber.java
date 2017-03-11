package interpretor.view;

import interpretor.controller.ProgramStateController;
import javafx.scene.control.TextField;

/**
 * Created by nicu on 1/24/2017.
 */
public class ProgramStatesNumber {
    private static final String PROGRAM_STATES_NUMBER = "Program states number: ";

    static TextField textField = new TextField(PROGRAM_STATES_NUMBER + "1");

    public static void setNewProgramStatesNumber(ProgramStateController programStateController) {
        textField.setText(PROGRAM_STATES_NUMBER + programStateController.getProgramStatesNumber());
    }
}
