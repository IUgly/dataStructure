package lambda;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User(17, "张三"));
        userList.add(new User(20, "牛二"));
        userList.add(new User(16, "王五"));

        long count = userList.stream().filter(user -> user.isAdult()).count();
        System.out.println(count);
    }
}
