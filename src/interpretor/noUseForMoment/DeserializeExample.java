//package interpretor.noUseForMoment;
//
//import interpretor.controller.ProgramStateController;
//import interpretor.exceptions.controller.ControllerExecutionException;
//import interpretor.exceptions.controller.ControllerIOException;
//import interpretor.exceptions.controller.ControllerProgramStateException;
//import interpretor.view.Command;
//
//import java.io.Serializable;
//
///**
// * Created by nicu on 12/9/2016.
// */
//public class DeserializeExample extends Command {
//    private ProgramStateController ctr;
//
//    public DeserializeExample(String key, String desc, ProgramStateController ctr) {
//        super(key, desc);
//        this.ctr = ctr;
//    }
//
//    @Override
//    public void execute() {
//        try {
//            ctr.deserializeProgramState();
//        }
//        catch (ControllerIOException e) {
//            System.out.println(e.getMessage());
//        }
//        catch (ControllerProgramStateException e) {
//            System.out.println(e.getMessage());
//        }
//        catch (ControllerExecutionException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
