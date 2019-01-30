package lambda;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filter {
    public static void main(String[] args) {
//        List<String> beginningWithNumbers = new ArrayList<>();
//        for (String value : Arrays.asList("a", "1abc", "abc1")){
//            if (value.charAt(0)>='0'&&value.charAt(0)<='9'){
//                beginningWithNumbers.add(value);
//            }
//        }

        //函数式风格⬇️
        List<String> beginningWithNumbers
                = Stream.of("a", "1abc", "abc1")
                .filter(value -> (value.charAt(0)>='0'&&value.charAt(0)<='9'))
                .collect(Collectors.toList());

        //找出List集合中符合条件的对象
        List<User> userList
                = Stream.of(new User("张三"), new User("王二"), new User("李四"))
                .filter(user -> user.getName().equals("王二"))
                .collect(Collectors.toList());
        Gson gson = new Gson();

        System.out.println(gson.toJson(userList));

        System.out.println(beginningWithNumbers.toString());
    }
}
