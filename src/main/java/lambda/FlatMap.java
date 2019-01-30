package lambda;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMap {
    public static void main(String[] args) {
        //多个List集合组合到一起

        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());

        List<User> list1 = new ArrayList<>();
        list1.add(new User("张三"));
        list1.add(new User("王二"));

        List<User> list2 = new ArrayList<>();
        list2.add(new User("seven"));
        list2.add(new User("one"));
        List<User> list = Stream.of(list1, list2)
                .flatMap(users -> users.stream())
                .collect(Collectors.toList());

        Gson gson = new Gson();
        System.out.println(gson.toJson(list));

        System.out.println(together);
    }
}
