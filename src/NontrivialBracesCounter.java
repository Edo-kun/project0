import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * NontrivialBracesCounter.java
 * Counts nontrivial left braces
 * Ed Zhou, Jacob Adamson, CP Majgaard
 */
public class NontrivialBracesCounter {
    public static int getNumNontrivialLeftBraces(String program) {
        //number of left braces
        int numberOfLeftBraces = 0;

        //before char pointer
        char previousChar;

        //currentChar char pointer
        char currentChar;

        int i = 1;

        //Until the last character of the program
        while (i < program.length()) {
            previousChar = program.charAt(i - 1);
            currentChar = program.charAt(i);

            //Catching Single line comment beginnings
            if (previousChar == '/' && currentChar == '/') {
                //Until the line ends
                while (currentChar != '\n' && ++i < program.length()) {
                    currentChar = program.charAt(i);
                }
            }
            //Catching Multiline comments beginnings
            else if (previousChar == '/' && currentChar == '*') {
                //Incrementing by 2 indices to avoid seeing /*/ as a complete multiline
                if (i + 2 >= program.length()) break;
                i += 2;
                previousChar = program.charAt(i - 1);
                currentChar = program.charAt(i);

                //Until we see the terminator
                while (!(previousChar == '*' && currentChar == '/')
                        && ++i < program.length()) {
                    previousChar = program.charAt(i - 1);
                    currentChar = program.charAt(i);
                }
            }
            //Catching single quotes
            else if (currentChar == '\'') {
                if (++i >= program.length()) break;
                //Incrementing once to avoid looking at the same character twice
                currentChar = program.charAt(i);
                //Until we see the matching quote
                while (currentChar != '\'' && ++i < program.length()) {
                    currentChar = program.charAt(i);
                }
            }
            //Catching double quotes
            else if (currentChar == '\"') {
                if (++i >= program.length()) break;
                //Incrementing once to avoid looking at the same character twice
                currentChar = program.charAt(i);
                //Until we see the matching quote
                while (currentChar != '\"' && ++i < program.length()) {
                    currentChar = program.charAt(i);
                }
            }
            //If we find a nontrivial brace
            else if (currentChar == '{') {
                numberOfLeftBraces++;
            }

            i++;
        }

        return numberOfLeftBraces;
    }

    public static void main(String args[]) {
        String contents1;
        String contents2;
        try {
            contents1 = new Scanner(new File("src/NontrivialBracesCounter.java"))
                    .useDelimiter("\\Z").next();
            contents2 = new Scanner(new File("src/NontrivialBracesCounterTest.java"))
                    .useDelimiter("\\Z").next();
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        NontrivialBracesCounter bracesCounter = new NontrivialBracesCounter();
        System.out.println("Left braces in this file: " +
                bracesCounter.getNumNontrivialLeftBraces(contents1));
        System.out.println("Left braces in Test file: " +
                bracesCounter.getNumNontrivialLeftBraces(contents2));
    }
}
