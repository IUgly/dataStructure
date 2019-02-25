package lambda.parallel;

import com.google.gson.Gson;
import lambda.vo.Comment;
import lambda.vo.WeiBo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

public class ParallelDemo {


    private static final int N = 100000000;



    List<WeiBo> weiBos = new ArrayList<>();

    //串行化，获得评论的点赞数
    public int serialArraySum(){
        return weiBos.stream()
                .flatMap(WeiBo::getCommentList)
                .mapToInt(Comment::getUpVote)
                .sum();
    }

    //并行化⬇️
    public int parallelArraySum(){
        return weiBos.parallelStream()
                .flatMap(WeiBo::getCommentList)
                .mapToInt(Comment::getUpVote)
                .sum();
    }


    private static IntFunction<Integer> twoDiceThrows() {
        return i -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int firstThrow = random.nextInt(1, 7);
            int secondThrow = random.nextInt(1, 7);
            return firstThrow + secondThrow;
        };
    }


    //使用蒙特卡洛模拟法并行化模拟掷骰子
    public Map<Integer, Double> parallelDiceRolls(){
        double fraction = 1.0 / N;
        return IntStream.range(0, N)
                .parallel()
                .mapToObj(twoDiceThrows())
                .collect(groupingBy(side -> side, //得到了需要合并的所有结果的流
                        summingDouble(n ->fraction)));
    }

    public static void main(String[] args) {
        Map<Integer, Double> map = new ParallelDemo().parallelDiceRolls();
        Gson gson = new Gson();
        System.out.println(gson.toJson(map));
    }
}
