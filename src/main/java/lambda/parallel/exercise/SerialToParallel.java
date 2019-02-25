package lambda.parallel.exercise;

import java.util.stream.IntStream;

public class SerialToParallel {
    //顺序求列表中数字的平方和
    public static int sequentialSumOfSquares(IntStream range){
        return range.map(x -> x*x)
                .sum();
    }

    //并行处理⬇️
    public static int sumOfSquares(IntStream range){
        return range.parallel()
                .map(x -> x * x)
                .sum();
    }

    public static void main(String[] args) {
        IntStream range = IntStream.range(0, 4);
        System.out.println(sumOfSquares(range));
    }
}
