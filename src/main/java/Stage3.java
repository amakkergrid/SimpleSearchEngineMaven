import java.util.*;

import static java.lang.System.exit;

public class Stage3{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of people:");
        int numberOfPeople = scanner.nextInt();
        scanner.nextLine();
        List<String>listOfPeople = new ArrayList<>();
        System.out.println("Enter all people:");
        while(numberOfPeople!=0){
            String person = scanner.nextLine();
            listOfPeople.add(person);
            numberOfPeople--;
        }
        while(true) {
            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
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

    private static List<String> getStrings(List<String> listOfPeople, String query) {
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
