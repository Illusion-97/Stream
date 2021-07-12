package illu.stream;

import java.util.function.Consumer;
import java.util.stream.IntStream;

@FunctionalInterface
interface MonFiltre<E> {
    void apply(E e);
}

public class Ex2 {

    public static void main(String[] args) {
        int[] tab = {-9, 3, -8, 7, -6, 2, -1};
        System.out.println("Les positifs (MonFiltre): ");
        affichePositifInterfaced(tab);
        System.out.println("Les positifs (Consummer): ");
        affichePositifConsummed(tab);
        System.out.println("Les positifs (Lambda): ");
        affichePositif(tab);
        System.out.println("Les négatifs : ");
        afficheNegatif(tab);
        System.out.println("Les pairs : ");
        affichePair(tab);
    }

    public static void affichePositifInterfaced(int[] tab) {
        MonFiltre<int[]> filter = vars -> {
            for (int i : vars) {
                if (i > 0) System.out.println(i);
            }
        };
        filter.apply(tab);
    }

    public static void affichePositifConsummed(int[] tab) {
        Consumer<int[]> consume = vars -> {
            for (int i : vars) {
                if (i > 0) System.out.println(i);
            }
        };
        consume.accept(tab);
    }

    public static void affichePositif(int[] tab) {
        IntStream.of(tab).filter(i -> i > 0).forEach(System.out::println);
    }
    public static void afficheNegatif(int[] tab) {
        IntStream.of(tab).filter(i -> i < 0).forEach(System.out::println);
    }
    public static void affichePair(int[] tab) {
        IntStream.of(tab).filter(i -> i % 2 == 0).forEach(System.out::println);
    }
}
