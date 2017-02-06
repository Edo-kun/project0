import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Counts nontrivial left braces
 */
public class NontrivialBracesCounter {
    public int getNumNontrivialLeftBraces(String program)  {
        //number of left braces
        int count = 0;

        //before char pointer
        char before = 0;

        //current char pointer
        char current = 0;

        // toggle if reading for braces
        boolean reading = true;

        // ending string to flag reading
        String ending = "";

        for(int i = 0; i<program.length(); i++) {
            current = program.charAt(i);

            // check for comments and strings
            if (reading) {
                // line comment
                if (current == '/' && before =='/') {
                    reading = false;
                    ending = "\n";
                }

                // "String" and 'char'
                else if (current == '\"') {
                    reading = false;
                    ending = "\"";
                }
                else if (current == '\'') {
                    reading = false;
                    ending = "'";
                }

                //multiline
                else if (current == '*' && before =='/') {
                    reading = false;
                    ending = "*/";
                }
            }
            // find terminating characters
            else {
                // skip escape characters
                if (current == '\\') i++;
                    switch (ending.length()) {
                        case 1: // strings and char
                            if (current == ending.charAt(0)) {
                                reading = true;
                            }
                            break;
                        case 2: // comments
                            if (before == ending.charAt(0) && current == ending.charAt(1)) {
                                reading = true;
                            }
                            break;
                    }
            }

            // add brace if looking for one
            if (reading && current=='{' ) {
                count++;
            }
            before = current;
        }
        return count;
    }

    public static void main(String args[]) {
        String contents1;
        String contents2;
        try {
            contents1 = new Scanner(new File("src/NontrivialBracesCounter.java")).useDelimiter("\\Z").next();
            contents2 = new Scanner(new File("src/HelloWorld.java")).useDelimiter("\\Z").next();
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        NontrivialBracesCounter bracesCounter = new NontrivialBracesCounter();
        System.out.println("Left braces in this file: " + bracesCounter.getNumNontrivialLeftBraces(contents1));
        System.out.println("Left braces in HelloWorld file: " + bracesCounter.getNumNontrivialLeftBraces(contents2));
    }
}
