import java.util.*;

public class Stage2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of people:");
        int numberOfPeople = sc.nextInt();
        sc.nextLine();
        List<String> listOfPeople = new ArrayList<>();
        System.out.println("Enter all people:");
        while(numberOfPeople!=0){
            String s = sc.nextLine();
            listOfPeople.add(s);
            numberOfPeople--;
        }
        System.out.println("Enter the number of search queries:");
        int numberOfQueries = sc.nextInt();
        sc.nextLine();
        while(numberOfQueries!=0){
            List<String>ans = new ArrayList<>();
            System.out.println("Enter data to search people:");
            String k = sc.nextLine();
            for(String ele : listOfPeople){
                String low = ele.toLowerCase();
                String kLow = k.toLowerCase();
                int len = kLow.length();
                for(int i=0;i<low.length()-len;i++){
                    if(low.substring(i,i+len).equals(kLow)){
                        ans.add(ele);
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
