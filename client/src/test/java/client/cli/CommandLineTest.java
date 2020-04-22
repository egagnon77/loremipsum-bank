package client.cli;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CommandLineTest {

    private static final String ARGUMENT_CLEANED = "argument";
    private static final String SECOND_ARGUMENT_CLEANED = "argument2 with spaces";
    private String[] testingArgs;
    private String[] passedArgs;
    private CommandLine testedClass;


    @Before
    public void setUp() {
        testingArgs = new String[]{ARGUMENT_CLEANED, SECOND_ARGUMENT_CLEANED};
    }

    @Test
    public void whenPassingArgsWithSpaceBeforeAndAfter_theseSpacesMustBeCleaned() {
        final String ARGUMENT_WITH_SPACE_BEFORE_AND_AFTER = " argument ";
        passedArgs = new String[]{ARGUMENT_WITH_SPACE_BEFORE_AND_AFTER};
        assertEquals(testedClass.cleanArguments(passedArgs)[0], testingArgs[0]);

        final String ARGUMENT_WITH_SPACE_BEFORE = " argument";
        passedArgs = new String[]{ARGUMENT_WITH_SPACE_BEFORE};
        assertEquals(testedClass.cleanArguments(passedArgs)[0], testingArgs[0]);

        final String ARGUMENT_WITH_SPACE_AFTER = "argument ";
        passedArgs = new String[]{ARGUMENT_WITH_SPACE_AFTER};
        assertEquals(testedClass.cleanArguments(passedArgs)[0], testingArgs[0]);
    }

    @Test
    public void whenPassingArgsWithMoreThanOneConsecutiveSpace_theseSpacesMustBeAdjustedToOnlyOne() {
        final String ARGUMENT_WITH_MULTIPLE_SPACES = "argument2    with     spaces";
        passedArgs = new String[]{"", ARGUMENT_WITH_MULTIPLE_SPACES};
        assertEquals(testedClass.cleanArguments(passedArgs)[1], testingArgs[1]);
    }

    @Test
    public void whenPassingNullArgumentsThenAnEmptyListOfArgumentsIsReturned() {
        passedArgs = null;
        assertEquals(0, testedClass.cleanArguments(passedArgs).length);
    }
}
