import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * ClassName Stage1 for 1st stage of the project.
 */
public class Stage1 {
    /**
     * @param args Array of string
     *The method prints the first occurance of the given query in the input
     *             and if the query is not present it prints Not Found.
     *
     */
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] listOfWords = input.split(" ");
        String query = scanner.next();
        Optional<Integer> op = Stream.iterate(0, stringIndex -> stringIndex + 1)
                .limit(listOfWords.length)
                .filter(stringIndex -> query.equals(listOfWords[stringIndex]))
                .findFirst();
        op.ifPresentOrElse(stringIndex -> System.out.println(stringIndex + 1),
                () -> System.out.println("Not Found"));

    }
}
