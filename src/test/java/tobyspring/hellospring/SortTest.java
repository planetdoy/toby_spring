package tobyspring.hellospring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class SortTest {
    Sort sort;

    @BeforeEach
    void beforeEach() {
        // 준비 (given)
        sort = new Sort();
        // System.out.println(this); // 매번 새로운 인스턴스를 생성한다. 이전 테스트에 영향을 받지 않게 하기 위함.
    }

    @Test
    void sort () {


        // 실행 (when)
        List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

        // 검증 (then)
        assertThat(list).isEqualTo(List.of("b", "aa"));
    }

    @Test
    void sort3Items () {

        // 실행 (when)
        List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

        // 검증 (then)
        assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }

    @Test
    void sortAlreadySorted () {

        // 실행 (when)
        List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

        // 검증 (then)
        assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }
}