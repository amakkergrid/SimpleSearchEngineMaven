import java.util.*;

import static java.lang.System.exit;

public class Stage3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of people:");
        int numberOfPeople = sc.nextInt();
        sc.nextLine();
        List<String>listOfPeople = new ArrayList<>();
        System.out.println("Enter all people:");
        while(numberOfPeople!=0){
            String s = sc.nextLine();
            listOfPeople.add(s);
            numberOfPeople--;
        }
        while(true) {
            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 0:
                    System.out.println("Bye");
                    exit(0);
                    break;
                case 1:
                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = sc.nextLine();
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
            String low = people.toLowerCase();
            String kLow = query.toLowerCase();
            int len = kLow.length();
            for (int i = 0; i <= low.length() - len; i++) {
                if (low.substring(i, i + len).equals(kLow)) {
                    ans.add(people);
                    break;
                }
            }

        }
        return ans;
    }
}
