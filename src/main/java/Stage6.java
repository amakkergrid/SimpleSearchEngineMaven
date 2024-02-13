import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Stage6{

    public static String readFile(String path) throws IOException{
        return new String(Files.readAllBytes(Paths.get(path)));
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = args[1];
        String txt = readFile(fileName);
        String[] inputText = txt.split("\n");
        List<String> listOfPeople = Arrays.stream(inputText).toList();
        Map<String,ArrayList<Integer>> mapOfIndex = new HashMap<>();
        for(int i=0;i<listOfPeople.size();i++){
            String eachLine = listOfPeople.get(i);
            String[] words = eachLine.split(" ");
            for(String word : words){
                word = word.toLowerCase();
                mapOfIndex.putIfAbsent(word,new ArrayList<>());
                mapOfIndex.get(word).add(i);
            }

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
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    String strategy = scanner.nextLine();
                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = scanner.nextLine();
                    String[] qWord = query.split(" ");
                    Set<Integer> ansSet = new HashSet<>();
                    if(strategy.equals("ANY") || strategy.equals("NONE")){
                        Set<Integer> tempAns = new HashSet<>();
                        for(String each:qWord){
                            each = each.toLowerCase();
                            if(mapOfIndex.get(each)!=null){
                                tempAns.addAll(mapOfIndex.get(each));
                            }
                        }
                        if(strategy.equals("NONE")){
                            for(int i = 0; i < listOfPeople.size(); i++){
                                if(!tempAns.contains(i)){
                                    ansSet.add(i);
                                }
                            }
                        }
                        else{
                            ansSet = tempAns;
                        }
                    }
                    else{
                        boolean firstMove = true;
                        for(String w : qWord){
                            w = w.toLowerCase();
                            if(mapOfIndex.get(w) == null) continue;
                            if(firstMove){
                                firstMove = false;
                                ansSet.addAll(mapOfIndex.get(w));
                            }
                            else{
                                Set<Integer> f = new HashSet<>();
                                for(int val : mapOfIndex.get(w)){
                                    if(ansSet.contains(val)){
                                        f.add(val);
                                    }
                                }
                                ansSet = f;
                            }
                        }
                    }
                    if(!ansSet.isEmpty()){
                        ansSet.stream().map(listOfPeople::get).forEach(System.out::println);
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
