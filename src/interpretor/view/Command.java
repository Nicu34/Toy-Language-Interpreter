package interpretor.view;

import interpretor.controller.ProgramStateController;

/**
 * Created by MuresanN on 11/20/2016.
 */
public abstract class Command {

    private String description;
    protected ProgramStateController programStateController;

    public Command(String description, ProgramStateController programStateController) {
        this.description = description;
        this.programStateController = programStateController;
    }

    public String getDescription() {
        return description;
    }

    public ProgramStateController getProgramStateController() {
        return programStateController;
    }

    @Override
    public String toString() {
        if (programStateController != null) {
            return programStateController.getCurrentProgramState().getOriginalProgram().toString();
        }
        else {
            return "null";
        }
    }

    public abstract void execute();
}