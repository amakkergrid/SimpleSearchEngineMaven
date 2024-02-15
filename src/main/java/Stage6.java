import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * ClassName Stage6 for 6th stage of the project.
 */
public class Stage6 {

    /**
     * @param path path of the file to extract the string
     * @return String which contains the content of the file of the pathName path.
     * @throws IOException for handling checked errors.
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
     * We also have to implement different strategies for findAll,findAny and findNone.
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
            for (String word : words) {
                word = word.toLowerCase();
                mapOfIndex.putIfAbsent(word, new ArrayList<>());
                mapOfIndex.get(word).add(i);
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
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    String strategy = scanner.nextLine();
                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = scanner.nextLine();
                    String[] queryWord = query.split(" ");
                    Set<Integer> ansSet = new HashSet<>();
                    if (strategy.equals("ANY") || strategy.equals("NONE")) {
                        Set<Integer> tempAns = new HashSet<>();
                        for (String eachQuery:queryWord) {
                            eachQuery = eachQuery.toLowerCase();
                            if (mapOfIndex.get(eachQuery) != null) {
                                tempAns.addAll(mapOfIndex.get(eachQuery));
                            }
                        }
                        if (strategy.equals("NONE")) {
                            for (int i = 0; i < listOfPeople.size(); i++) {
                                if (!tempAns.contains(i)) {
                                    ansSet.add(i);
                                }
                            }
                        }
                        else {
                            ansSet = tempAns;
                        }
                    }
                    else {
                        boolean firstMove = true;
                        for (String eachQuery : queryWord) {
                            eachQuery = eachQuery.toLowerCase();
                            if (mapOfIndex.get(eachQuery) == null) continue;
                            if (firstMove) {
                                firstMove = false;
                                ansSet.addAll(mapOfIndex.get(eachQuery));
                            }
                            else {
                                Set<Integer> f = new HashSet<>();
                                for (int val : mapOfIndex.get(eachQuery)) {
                                    if (ansSet.contains(val)) {
                                        f.add(val);
                                    }
                                }
                                ansSet = f;
                            }
                        }
                    }
                    if (!ansSet.isEmpty()) {
                        ansSet.stream().map(listOfPeople::get).forEach(System.out::println);
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
