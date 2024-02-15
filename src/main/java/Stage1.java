import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class Stage1{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] listOfWords = input.split(" ");
        String query = scanner.next();
        Optional<Integer> op = Stream.iterate(0,stringIndex->stringIndex+1).limit(listOfWords.length).filter(stringIndex->query.equals(listOfWords[stringIndex])).findFirst();
        op.ifPresentOrElse((stringIndex->System.out.println(stringIndex+1)),()->System.out.println("Not Found"));

    }
}
