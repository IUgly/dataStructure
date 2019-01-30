package lambda;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class Reduce {
    public static void main(String[] args) {
        //acc:累加器       element：传入Stream中的当前元素
        int count = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);
        System.out.println(count);

        //实际环境中这样写⬇️
        BinaryOperator<Integer> accumulator = (acc, element) -> acc + element;
        int count2 = accumulator.apply(
                accumulator.apply(
                        accumulator.apply(0, 1),
                        2),
        3);
    }
}
