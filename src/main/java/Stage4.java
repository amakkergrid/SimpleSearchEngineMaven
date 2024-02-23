import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * ClassName Stage4 for 4th stage of the project.
 */
public class Stage4 {

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
     * print the list of people in the file.
     *
     */
    public static void main(final String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = args[1];
        String fileText = readFile(fileName);
        String[] fileInList = fileText.split("\n");
        List<String> listOfPeople = Arrays.stream(fileInList).toList();
        boolean flag = true;
        while (flag) {
//            System.out.println("=== Menu ===");
//            System.out.println("1. Find a person");
//            System.out.println("2. Print all people");
//            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    System.out.println("Bye!");
                    flag = false;
                    break;
                case 1:
//                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = scanner.nextLine();
                    List<String> ans = getStrings(listOfPeople, query);
                    ans.forEach(System.out::println);
                    break;
                case 2:
//                    System.out.println("=== List of people ===");
                    listOfPeople.forEach(System.out::println);
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
            }

        }
    }

    /**
     * @param listOfPeople List of input people
     * @param query input query to search
     * @return ans List of Strings which matches with the query.
     *The method takes an input of number of people
     *             and a query and performs the search operation in the list to get the result.
     *
     */
    private static List<String> getStrings(final List<String> listOfPeople, final String query) {
        List<String> ans = new ArrayList<>();
        for (String people : listOfPeople) {
            String lowerCasePerson = people.toLowerCase();
            String lowerCaseQuery = query.toLowerCase();
            int length = lowerCaseQuery.length();
            for (int i = 0; i <= lowerCasePerson.length() - length; i++) {
                if (lowerCasePerson.substring(i, i + length).equals(lowerCaseQuery)) {
                    ans.add(people);
                    break;
                }
            }

        }
        return ans;
    }
}
