import java.util.*;

public class Stage2{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of people:");
        int numberOfPeople = scanner.nextInt();
        scanner.nextLine();
        List<String> listOfPeople = new ArrayList<>();
        System.out.println("Enter all people:");
        while(numberOfPeople!=0){
            String person = scanner.nextLine();
            listOfPeople.add(person);
            numberOfPeople--;
        }
        System.out.println("Enter the number of search queries:");
        int numberOfQueries = scanner.nextInt();
        scanner.nextLine();
        while(numberOfQueries!=0){
            List<String>ans = new ArrayList<>();
            System.out.println("Enter data to search people:");
            String numberOfSearch = scanner.nextLine();
            for(String person : listOfPeople){
                String lowerCasePerson = person.toLowerCase();
                String lowerCaseQuery = numberOfSearch.toLowerCase();
                int length = lowerCaseQuery.length();
                for(int i=0;i<lowerCasePerson.length()-length;i++){
                    if(lowerCasePerson.substring(i,i+length).equals(lowerCaseQuery)){
                        ans.add(person);
                        break;
                    }
                }

            }
            if(ans.isEmpty()){
                System.out.println("No matching people found.");
            }
            else{
                System.out.println("Found people:");
                ans.forEach(System.out::println);
            }
            numberOfQueries--;
            ans.clear();
        }





    }
}
