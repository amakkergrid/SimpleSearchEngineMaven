import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * ClassName Stage5 for 5th stage of the project.
 */
public class Stage5 {

    /**
     * @param path path of the file to extract the string
     * @return String which contains the content of the file of the pathName path.
     *@throws IOException for handling checked errors.
     */
    public static String readFile(final String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
    /**
     * @param args Array of string
     * @throws IOException for handling checked errors.
     * In this method,we get a file from which we have to extract the string
     * and then make a menu driven program to find a person from the file or
     * print the list of people in the file.We have to create an inverted index for all
     * the words present in the file.
     *
     */
    public static void main(final String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = args[1];
        String fileInText = readFile(fileName);
        String[] inputText = fileInText.split("\n");
        List<String> listOfPeople = Arrays.stream(inputText).toList();
        Map<String, ArrayList<Integer>> mapOfIndex = new HashMap<>();
        for (int i = 0; i < listOfPeople.size(); i++) {
            String eachLine = listOfPeople.get(i);
            String[] words = eachLine.split(" ");
            for (String eachWord : words) {
                mapOfIndex.putIfAbsent(eachWord, new ArrayList<>());
                mapOfIndex.get(eachWord).add(i);
            }

        }
        while (true) {
            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = scanner.nextLine();
                    if (mapOfIndex.get(query) != null) {
                        List<Integer> tempAns = mapOfIndex.get(query);
                        List<String> actualAns = new ArrayList<>();
                        for (int ind:tempAns) {
                            actualAns.add(listOfPeople.get(ind));
                        }
                        actualAns.forEach(System.out::println);

                    }
                    else {
                        System.out.println("No matching people found.");
                    }

                    break;
                case 2:
                    System.out.println("=== List of people ===");
                    listOfPeople.forEach(System.out::println);
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
            }

        }
    }
}
