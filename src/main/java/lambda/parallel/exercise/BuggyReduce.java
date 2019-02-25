package lambda.parallel.exercise;

import java.util.Arrays;
import java.util.List;

public class BuggyReduce {
    //把列表中的数字相乘， 再将所得结果乘以5
    public static int multiplyThrough(List<Integer> linkedListOfNumbers){
        return linkedListOfNumbers.stream()
                .reduce(5, (acc, x) -> acc * x);
    }

    //并行化处理⬇️
    public static int multiplyThroughParallel(List<Integer> linkedListOfNumber){
        return 5 * linkedListOfNumber.parallelStream()
                .reduce(1, (acc, x) -> acc * x);
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        System.out.println(multiplyThrough(numbers));

        System.out.println(multiplyThroughParallel(numbers));
    }
}
