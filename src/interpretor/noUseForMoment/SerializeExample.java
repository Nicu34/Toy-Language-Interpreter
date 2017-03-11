//package interpretor.noUseForMoment;
//
//import interpretor.controller.ProgramStateController;
//import interpretor.exceptions.controller.ControllerIOException;
//import interpretor.exceptions.controller.ControllerProgramStateException;
//import interpretor.view.Command;
//
//import java.io.Serializable;
//
///**
// * Created by nicu on 12/9/2016.
// */
//public class SerializeExample extends Command {
//    private ProgramStateController ctr;
//
//    public SerializeExample(String key, String desc, ProgramStateController ctr) {
//        super(key, desc);
//        this.ctr = ctr;
//    }
//
//    @Override
//    public void execute() {
//        try {
//            ctr.serializeCurrentProgramState();
//        }
//        catch (ControllerIOException e) {
//            System.out.println(e.getMessage());
//        }
//        catch (ControllerProgramStateException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
