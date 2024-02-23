import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class Stage6Test {

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
            String result = Stage6.readFile(fileName);
            assertEquals(content, result);
            assertTrue(Files.deleteIfExists(path) , "Not deleted");
        }
        catch (IOException e) {
            System.out.println("IOException Occurred:" + e.getMessage());
        }

    }

    @Test
    void readFileNotFound() {
        assertThrows(IOException.class , ()-> Stage6.readFile("non_existent.txt"));
    }
    @Test
    void testChoice1_ANY(){
        String strategy = "ANY";
        String query = "Dwight";
        String expectedOutput = """
                Dwight Joseph djo@gmail.com
                Bye!""";
        testChoice("1" , "0" ,strategy,query,expectedOutput);
    }
    @Test
    void testChoice1_NONE(){
        String strategy = "NONE";
        String query = """
                Rene Jacobs Erick Myrtle Medina Erick Burgess""";
        String expectedOutput = """
                Dwight Joseph djo@gmail.com
                Bye!""";
        testChoice("1" , "0" ,strategy,query,expectedOutput);
    }
    @Test
    void testChoice1_ALL(){
        String strategy = "ALL";
        String query = "Harrington Erick";
        String expectedOutput = """
                Erick Harrington harrington@gmail.com
                Bye!""";
        testChoice("1" , "0" ,strategy,query,expectedOutput);
    }
    @Test
    void testChoice1_NotFound() {
        String expectedOutput = """
                        No matching people found.
                        Bye!""";
        testChoice("1","0","ANY","Ansh",expectedOutput);
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
        testChoice("2","0", "","", expectedOutput);

    }
    @Test
    void testChoice0() {
        String expectedOutput = "Bye!";
        testChoice("0","", "" ,"",expectedOutput);
    }
    @Test
    void testChoiceIncorrect() {
        String expectedOutput = "Incorrect option! Try again." + "\n" + "Bye!";
        testChoice("4","0","","",expectedOutput);
    }
    private void testChoice(String choice1 , String choice2 , String strategy , String query , String expectedOutput) {
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
            String result = Stage6.readFile(fileName);
            assertEquals(content, result);
            InputStream originalIn = System.in;
            PrintStream originalOut = System.out;
            try {
                String inputWithQuery = choice1 + "\n" + strategy + "\n" + query + "\n" + choice2 + "\n";
                System.setIn(new ByteArrayInputStream(inputWithQuery.getBytes()));
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                System.setOut(new PrintStream(outputStream));
                Stage6.main(new String[]{"", fileName});
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