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

            // line comment
            if (current == '/' && before =='/' && reading) {
                reading = false;
                ending = "\n";
            }

            // "String"
            if (current == '\"' && reading) {
                reading = false;
                ending = "\"";
            }

            //multiline
            if (current == '*' && before =='/' && reading) {
                reading = false;
                ending = "*/";
            }
            if (!reading){
                switch (ending.length()) {
                    case 1:
                        if (current == ending.charAt(0)) {reading = true;}
                        break;
                    case 2:
                        if (before == ending.charAt(0) && current == ending.charAt(1)) {
                            reading = true;
                        }
                        break;
                }
            }

            if (current=='{' && reading){
                count++;
            }
            before = current;
        }
        return count;
    }

    public static void main(String args[]) {
        String contents;
        try {
            contents = new Scanner(new File("src/NontrivialBracesCounter.java")).useDelimiter("\\Z").next();
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        NontrivialBracesCounter bracesCounter = new NontrivialBracesCounter();
        System.out.println("Left braces in this file: " + bracesCounter.getNumNontrivialLeftBraces(contents));
    }
}
