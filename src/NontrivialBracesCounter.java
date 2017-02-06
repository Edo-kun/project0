import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Counts nontrivial left braces
 */
public class NontrivialBracesCounter {
    public int getNumNontrivialLeftBraces(String program)  {
        //number of left braces
        int numberOfLeftBraces = 0;

        //before char pointer
        char previousChar = 0;

        //currentChar char pointer
        char currentChar = 0;

        // toggle if reading for braces
        boolean acceptBraces = true;

        // ending string to flag reading
        String ending = "";

        for(int i = 0; i<program.length(); i++) {
            currentChar = program.charAt(i);

            // check for comments and strings
            if (acceptBraces) {
                // line comment
                if (currentChar == '/' && previousChar =='/') {
                    acceptBraces = false;
                    ending = "\n";
                }

                // "String" and 'char'
                else if (currentChar == '\"') {
                    acceptBraces = false;
                    ending = "\"";
                }
                else if (currentChar == '\'') {
                    acceptBraces = false;
                    ending = "'";
                }

                //multiline comment
                else if (currentChar == '*' && previousChar =='/') {
                    acceptBraces = false;
                    ending = "*/";
                }
            }
            // find terminating characters
            else {
                // skip escape characters
                if (currentChar == '\\') i++;
                    switch (ending.length()) {
                        case 1: // strings and char
                            if (currentChar == ending.charAt(0)) {
                                acceptBraces = true;
                            }
                            break;
                        case 2: // comments
                            if (previousChar == ending.charAt(0) && currentChar == ending.charAt(1)) {
                                acceptBraces = true;
                            }
                            break;
                    }
            }

            // add brace if looking for one
            if (acceptBraces && currentChar=='{' ) {
                numberOfLeftBraces++;
            }
            previousChar = currentChar;
        }
        return numberOfLeftBraces;
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
