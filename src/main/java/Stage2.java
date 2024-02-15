import java.util.*;

/**
 * ClassName Stage2 for 2nd stage of the project.
 */
public class Stage2 {
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
        System.out.println("Enter the number of search queries:");
        int numberOfQueries = scanner.nextInt();
        scanner.nextLine();
        while (numberOfQueries != 0) {
            List<String> ans = new ArrayList<>();
            System.out.println("Enter data to search people:");
            String queryToSearch = scanner.nextLine();
            for (String person : listOfPeople) {
                String lowerCasePerson = person.toLowerCase();
                String lowerCaseQuery = queryToSearch.toLowerCase();
                int len = lowerCaseQuery.length();
                for (int i = 0; i < lowerCasePerson.length() - len; i++) {
                    if (lowerCasePerson.substring(i, i + len).equals(lowerCaseQuery)) {
                        ans.add(person);
                        break;
                    }
                }

            }
            if (ans.isEmpty()) {
                System.out.println("No matching people found.");
            }
            else {
                System.out.println("Found people:");
                ans.forEach(System.out::println);
            }
            numberOfQueries--;
            ans.clear();
        }





    }
}
