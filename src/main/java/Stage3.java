import java.util.*;

import static java.lang.System.exit;

/**
 * ClassName Stage3 for 3rd stage of the project.
 */
public class Stage3 {
    /**
     * @param args Array of string
     *The method takes an input of number of people
     *             and also takes a number of search queries
     *             and prints the names of the people who are found
     *             in the input.
     *
     */
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of people:");
        int numberOfPeople = scanner.nextInt();
        scanner.nextLine();
        List<String> listOfPeople = new ArrayList<>();
        System.out.println("Enter all people:");
        while (numberOfPeople != 0) {
            String person = scanner.nextLine();
            listOfPeople.add(person);
            numberOfPeople--;
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
                    System.out.println("Bye");
                    exit(0);
                    break;
                case 1:
                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = scanner.nextLine();
                    List<String> ans = getStrings(listOfPeople, query);
                    ans.forEach(System.out::println);
                    ans.clear();
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
