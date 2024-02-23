import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class Stage5Test {

    @Test
    void readFileFound() {
        try{
            String fileName = "test.txt";
            String content = """
                Dwight Joseph djo@gmail.com
                Rene Webb webb@gmail.com
                Katie Jacobs
                Erick Harrington harrington@gmail.com
                Myrtle Medina
                Erick Burgess
                """;
            Path path = Paths.get(fileName);
            Files.write(path,content.getBytes());
            String result = Stage5.readFile(fileName);
            assertEquals(content, result);
            assertTrue(Files.deleteIfExists(path) , "Not deleted");
        }
        catch (IOException e) {
            System.out.println("IOException Occurred:" + e.getMessage());
        }

    }

    @Test
    void readFileNotFound() {
        assertThrows(IOException.class , ()-> Stage5.readFile("non_existent.txt"));
    }

    @Test
    void testChoice1Found() {
        String expectedOutput = """
                        Erick Harrington harrington@gmail.com
                        Erick Burgess
                        Bye!""";
        testChoice("1","0","ERICK",expectedOutput);
    }
    @Test
    void testChoice1NotFound() {
        String expectedOutput = """
                        No matching people found.
                        Bye!""";
        testChoice("1","0","Ansh",expectedOutput);
    }
    @Test
    void testChoice2() {
        String expectedOutput = """
                    Dwight Joseph djo@gmail.com
                    Rene Webb webb@gmail.com
                    Katie Jacobs
                    Erick Harrington harrington@gmail.com
                    Myrtle Medina
                    Erick Burgess
                    Bye!""";
        testChoice("2","0","",expectedOutput);
    }

    @Test
    void testChoice0() {
        String expectedOutput = "Bye!";
        testChoice("0","","",expectedOutput);
    }
    @Test
    void testChoiceIncorrect() {
        String expectedOutput = "Incorrect option! Try again." + "\n" + "Bye!";
        testChoice("4","0","",expectedOutput);
    }

    private void testChoice(String choice1 , String choice2 , String query , String expectedOutput) {
        try {
            String fileName = "test.txt";
            String content = """
                    Dwight Joseph djo@gmail.com
                    Rene Webb webb@gmail.com
                    Katie Jacobs
                    Erick Harrington harrington@gmail.com
                    Myrtle Medina
                    Erick Burgess
                    """;
            Path path = Paths.get(fileName);
            Files.write(path, content.getBytes());
            String result = Stage5.readFile(fileName);
            assertEquals(content, result);
            InputStream originalIn = System.in;
            PrintStream originalOut = System.out;
            try {
                String inputWithQuery = choice1 + "\n" + query + "\n" + choice2 + "\n";
                System.setIn(new ByteArrayInputStream(inputWithQuery.getBytes()));
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                System.setOut(new PrintStream(outputStream));
                Stage5.main(new String[]{"", fileName});
                String stringList = outputStream.toString().trim();
                assertEquals(expectedOutput, stringList);
            } finally {
                System.setIn(originalIn);
                System.setOut(originalOut);
            }
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }
}