import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Stage2Test {

    @Test
    void testMainFound() {
        String numberOfPeople = "6";
        String inputPeople = """
                Dwight Joseph djo@gmail.com
                Rene Webb webb@gmail.com
                Katie Jacobs
                Erick Harrington harrington@gmail.com
                Myrtle Medina
                Erick Burgess\
                """;
        String numberOfQueries = "1";
        String query = "ERICK";
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            String inputWithQuery = numberOfPeople + "\n" + inputPeople + "\n" + numberOfQueries + "\n" + query;
            System.setIn(new ByteArrayInputStream(inputWithQuery.getBytes()));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Stage2.main(new String[]{});
            String stringList = outputStream.toString().trim();
            String actualOutput = stringList.substring(stringList.indexOf("Found"));
            String expectedOutput = """
                    Found people:
                    Erick Harrington harrington@gmail.com
                    Erick Burgess""";
            assertEquals(expectedOutput, actualOutput);
        }
        finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
    @Test
    void testMainNotFound() {
        String numberOfPeople = "6";
        String inputPeople = """
                Dwight Joseph djo@gmail.com
                Rene Webb webb@gmail.com
                Katie Jacobs
                Erick Harrington harrington@gmail.com
                Myrtle Medina
                Erick Burgess\
                """;
        String numberOfQueries = "1";
        String query = "Ansh";
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            String inputWithQuery = numberOfPeople + "\n" + inputPeople + "\n" + numberOfQueries + "\n" + query;
            System.setIn(new ByteArrayInputStream(inputWithQuery.getBytes()));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Stage2.main(new String[]{});
            String stringList = outputStream.toString().trim();
            String actualOutput = stringList.substring(stringList.indexOf("No"));
            String expectedOutput = "No matching people found.";
            assertEquals(expectedOutput, actualOutput);
        }
        finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}