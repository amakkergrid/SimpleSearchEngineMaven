import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class Stage1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] li = input.split(" ");
        String query = sc.next();
        Optional<Integer> op = Stream.iterate(0,idx->idx+1).limit(li.length).filter(idx->query.equals(li[idx])).findFirst();
        op.ifPresentOrElse((idx->System.out.println(idx+1)),()->System.out.println("Not Found"));

    }
}
