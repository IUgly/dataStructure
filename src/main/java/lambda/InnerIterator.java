package lambda;

import com.google.gson.Gson;
import lambda.vo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InnerIterator {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User(17, "张三"));
        userList.add(new User(20, "牛二"));
        userList.add(new User(16, "王五"));
        Gson gson = new Gson();

        long count = userList.stream().filter(user -> user.isAdult()).count();
        System.out.println(count);

        //集合中符合条件的元素的个数
        long count2 = userList.stream()
                .filter(user -> {
//                    System.out.println(user.getName());
                    return user.isAdult();
                })
                .count();
        System.out.println(count2);

        List<String> collected = Stream.of("a", "b", "c")
                .collect(Collectors.toList());
//        System.out.println(collected.toString());

        //对象装入list集合
        List<User> userList1 = Stream.of(new User("zhang"), new User("li"), new User("zhao"))
                .collect(Collectors.toList());
        System.out.println(gson.toJson(userList1));;

//        //使用map操作将字符串转换为大写形式
//        List<String> collected = Stream.of("a", "b", "hello")
//                .map(s -> s.toUpperCase())
//                .collect(Collectors.toList());
//        System.out.println(collected.toString());

    }
}
