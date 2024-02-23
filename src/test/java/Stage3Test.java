import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class Stage3Test {

    @Test
    void testChoice1() {
        String numberOfPeople = "6";
        String inputPeople = """
                Dwight Joseph djo@gmail.com
                Rene Webb webb@gmail.com
                Katie Jacobs
                Erick Harrington harrington@gmail.com
                Myrtle Medina
                Erick Burgess""";
        String choice = "1";
        String query = "ERICK";
        String choice2 = "0";
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            String inputWithQuery = numberOfPeople + "\n" + inputPeople + "\n" + choice + "\n" + query + "\n" + choice2;
            System.setIn(new ByteArrayInputStream(inputWithQuery.getBytes()));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Stage3.main(new String[]{});
            String stringList = outputStream.toString().trim();
            String expectedOutput = """
                    Erick Harrington harrington@gmail.com
                    Erick Burgess
                    Bye""";
            assertEquals(expectedOutput, stringList);
        }
        finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
    @Test
    void testChoice2() {
        String numberOfPeople = "6";
        String inputPeople = """
                Dwight Joseph djo@gmail.com
                Rene Webb webb@gmail.com
                Katie Jacobs
                Erick Harrington harrington@gmail.com
                Myrtle Medina
                Erick Burgess""";
        String choice = "2";
        String choice2 = "0";
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            String inputWithQuery = numberOfPeople + "\n" + inputPeople + "\n" + choice + "\n" + choice2;
            System.setIn(new ByteArrayInputStream(inputWithQuery.getBytes()));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Stage3.main(new String[]{});
            String stringList = outputStream.toString().trim();
            String expectedOutput = """
                    === List of people ===
                    Dwight Joseph djo@gmail.com
                    Rene Webb webb@gmail.com
                    Katie Jacobs
                    Erick Harrington harrington@gmail.com
                    Myrtle Medina
                    Erick Burgess
                    Bye""";
            assertEquals(expectedOutput, stringList);
        }
        finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
    @Test
    void testChoice0() {
        String numberOfPeople = "6";
        String inputPeople = """
                Dwight Joseph djo@gmail.com
                Rene Webb webb@gmail.com
                Katie Jacobs
                Erick Harrington harrington@gmail.com
                Myrtle Medina
                Erick Burgess""";
        String choice = "0";
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            String inputWithQuery = numberOfPeople + "\n" + inputPeople + "\n" + choice;
            System.setIn(new ByteArrayInputStream(inputWithQuery.getBytes()));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Stage3.main(new String[]{});
            String stringList = outputStream.toString().trim();
            String expectedOutput = """
                    Bye""";
            assertEquals(expectedOutput, stringList);
        }
        finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
    @Test
    void testChoiceIncorrect() {
        String numberOfPeople = "6";
        String inputPeople = """
                Dwight Joseph djo@gmail.com
                Rene Webb webb@gmail.com
                Katie Jacobs
                Erick Harrington harrington@gmail.com
                Myrtle Medina
                Erick Burgess""";
        String choice = "4";
        String choice2 = "0";
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            String inputWithQuery = numberOfPeople + "\n" + inputPeople + "\n" + choice + "\n" + choice2;
            System.setIn(new ByteArrayInputStream(inputWithQuery.getBytes()));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Stage3.main(new String[]{});
            String stringList = outputStream.toString().trim();
            String expectedOutput = "Incorrect option! Try again.\n"+"Bye";
            assertEquals(expectedOutput, stringList);
        }
        finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}