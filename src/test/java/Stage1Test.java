import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class Stage1Test {

    @Test
    void testMainFound() {
        String input = "apple banana mango";
        String query = "banana";
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            String inputWithQuery = input + "\n" + query;
            System.setIn(new ByteArrayInputStream(inputWithQuery.getBytes()));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Stage1.main(new String[]{});
            String actualOutput = outputStream.toString().trim();
            assertEquals("2", actualOutput);
        }
        finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    @Test
    void testMainNotFound() {
        String input = "apple banana mango";
        String query = "orange";
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            String inputWithQuery = input + "\n" + query;
            System.setIn(new ByteArrayInputStream(inputWithQuery.getBytes()));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Stage1.main(new String[]{});
            String actualOutput = outputStream.toString().trim();
            assertEquals("Not Found", actualOutput);
        }
        finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}