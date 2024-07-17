package tobyspring.hellospring;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
    public static void main(String[] args) {
//        List<Integer> scores = Arrays.asList(5,7,1,3,9,8);
        List<String> scores = Arrays.asList("X", "y", "JAVA", "appepl");
        Collections.sort(scores, (o1, o2) -> o1.length() - o2.length());
        scores.forEach(System.out::println);
    }
}
