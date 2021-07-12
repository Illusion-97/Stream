package illu.stream;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex1 {

    public static void main(String[] args) {
        Integer[] integerList = Stream.generate(() -> new Random().nextInt(21))
                .limit(6).sorted().toArray(Integer[]::new);
        Stream.of(integerList).forEach(System.out::println);

        System.out.println();
        System.out.println("Min : " + Stream.of(integerList).findFirst().get());

        System.out.println();
        System.out.println("Sum : " + Stream.of(integerList).filter(i -> i > 3).mapToInt(Integer::intValue).sum());
    }
}
