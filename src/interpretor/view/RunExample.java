package interpretor.view;

import interpretor.controller.ProgramStateController;
import interpretor.exceptions.controller.ControllerExecutionException;
import interpretor.exceptions.controller.ControllerIOException;
import interpretor.exceptions.controller.ControllerProgramStateException;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class RunExample extends Command {
    public RunExample(String desc, ProgramStateController ctr){
        super(desc, ctr);
    }

    @Override
    public void execute() {
        try {
            programStateController.allStepGUI();
        }
        catch (ControllerExecutionException e) {
            AlertBox.display("Exception occured", e.getMessage());
        }
    }
}
