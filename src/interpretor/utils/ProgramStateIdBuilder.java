package interpretor.utils;

import interpretor.collection.stack.IStack;
import interpretor.collection.stack.Stack;

/**
 * Created by MuresanN on 12/14/2016.
 */
public abstract class ProgramStateIdBuilder {
    private static Integer id = 1;

    private static IStack<Integer> freeProgramStateIds = new Stack<>();

    public static Integer getProgramStateId() {
        return freeProgramStateIds.isEmpty() ? id++ : freeProgramStateIds.pop();
    }

    public static void freeProgramStateId(Integer fd) {
        if (fd < ProgramStateIdBuilder.id) {
            freeProgramStateIds.push(fd);
        }
    }
}
