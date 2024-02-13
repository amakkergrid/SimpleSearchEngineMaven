import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Stage4{

    public static String readFile(String path) throws IOException{
        return new String(Files.readAllBytes(Paths.get(path)));
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String fileName = args[1];
        String txt = readFile(fileName);
        String[] a = txt.split("\n");
        List<String> listOfPeople = Arrays.stream(a).toList();
        while(true) {
            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 0:
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = sc.nextLine();
                    List<String> ans = getStrings(listOfPeople, query);
                    ans.forEach(System.out::println);
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
        List<String>ans = new ArrayList<>();
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
