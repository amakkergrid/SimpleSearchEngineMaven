import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Stage5{

    public static String readFile(String path) throws IOException{
        return new String(Files.readAllBytes(Paths.get(path)));
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String fileName = args[1];
        String txt = readFile(fileName);
        String[] inputText = txt.split("\n");
        List<String> listOfPeople = Arrays.stream(inputText).toList();
        Map<String,ArrayList<Integer>> mapOfIndex = new HashMap<>();
        for(int i=0 ; i<listOfPeople.size() ; i++){
            String eachLine = listOfPeople.get(i);
            String[] words = eachLine.split(" ");
            for(String eachWord : words){
                mapOfIndex.putIfAbsent(eachWord,new ArrayList<>());
                mapOfIndex.get(eachWord).add(i);
            }

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
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = sc.nextLine();
                    if(mapOfIndex.get(query)!=null){
                        List<Integer> tempAns = mapOfIndex.get(query);
                        List<String> actualAns = new ArrayList<>();
                        for(int ind:tempAns){
                            actualAns.add(listOfPeople.get(ind));
                        }
                        actualAns.forEach(System.out::println);

                    }
                    else{
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
