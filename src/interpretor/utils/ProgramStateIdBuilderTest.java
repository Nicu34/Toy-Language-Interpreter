package interpretor.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by MuresanN on 12/14/2016.
 */
public class ProgramStateIdBuilderTest {

    @Test
    public void test() {
        ProgramStateIdBuilder.freeProgramStateId(2);
        ProgramStateIdBuilder.freeProgramStateId(1);
        assertEquals(((Integer) 1), ProgramStateIdBuilder.getProgramStateId());
        assertEquals(((Integer) 2), ProgramStateIdBuilder.getProgramStateId());
        ProgramStateIdBuilder.freeProgramStateId(1);
        assertEquals(((Integer) 1), ProgramStateIdBuilder.getProgramStateId());
    }

}