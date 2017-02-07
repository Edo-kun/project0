import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * NontrivialBracesCounterTest.java
 * Tests Counting of nontrivial left braces
 * Ed Zhou, Jacob Adamson, CP Majgaard
 */

public class NontrivialBracesCounterTest {
    @org.junit.Test

    public void getNumNontrivialLeftBraces() throws Exception {
        String testText = new Scanner(new File("src/NontrivialBracesCounter.java"))
                .useDelimiter("\\Z").next();

        final int result = NontrivialBracesCounter.getNumNontrivialLeftBraces(testText);

        assertEquals(result, 14);

        String testText2 = new Scanner(new File("src/NontrivialBracesCounterTest.java"))
                .useDelimiter("\\Z").next();

        final int result2 = NontrivialBracesCounter.getNumNontrivialLeftBraces(testText2);

        assertEquals(result2, 2);
    }
}