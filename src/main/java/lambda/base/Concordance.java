package lambda.base;

import com.google.gson.Gson;
import lambda.vo.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concordance {
    //找出List集合中 姓氏为张 的用户的年龄情况
    public static void main(String[] args) {
        List<User> userList = Stream.of(
                new User(20, "zhang wuji"),
                new User(40, "li"),
                new User(15, "zhao si"),
                new User(80, "zhang sanfeng"))
                .collect(Collectors.toList());

        Set<Integer> zhangs = userList.stream()
                .filter(user -> user.getName().startsWith("zhang"))
                .map(user -> user.getAge())
                .collect(Collectors.toSet());

        Gson gson = new Gson();
        System.out.println(gson.toJson(zhangs));
    }

}
