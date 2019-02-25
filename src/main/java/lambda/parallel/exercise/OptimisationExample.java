package lambda.parallel.exercise;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OptimisationExample {

    private List<Integer> arrayListOfNumbers;
    private List<Integer> linkedListOfNumbers;


    public void init() {
        arrayListOfNumbers= new ArrayList<>();

        linkedListOfNumbers = new LinkedList<>();
    }


    //求列表元素的平方和
    public int slowSumOfSquares(){
        return linkedListOfNumbers.parallelStream()
                .map(x -> x * x)
                .reduce(0, (acc, x) -> acc + x);
    }

    //改善性能⬇️
    public int fastSumOfSquares() {
        return arrayListOfNumbers.parallelStream()
                .mapToInt(x -> x * x)
                .sum();
    }

    public int serialFastSumOfSquares() {
        return arrayListOfNumbers.stream()
                .mapToInt(x -> x * x)
                .sum();
    }

}
