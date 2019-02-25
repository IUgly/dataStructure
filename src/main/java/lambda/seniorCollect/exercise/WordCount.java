package lambda.seniorCollect.exercise;

import com.google.gson.Gson;
import lambda.vo.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.counting;

public class WordCount {

    public static Map<String, Long> countWords(Stream<String> names){
        return names.collect(Collectors.groupingBy(name -> name, counting()));
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a","b","c","a");

        Gson gson = new Gson();
        System.out.println(gson.toJson(countWords(strings.stream())));
    }
}
