package lambda.seniorCollect.exercise;

import lambda.vo.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LongestName {

    private static Comparator<User> byNameLength =
            Comparator.comparing(user -> user.getName().length());

    public static User byReduce(List<User> users){
        return users.stream()
                .reduce((acc, user) -> {
                    return (byNameLength.compare
                            (acc, user) >=0) ? acc : user;
                })
                .orElseThrow(RuntimeException::new);
    }

    public static User byCollecting(List<User> users){
        return users.stream()
                .collect(Collectors.maxBy(byNameLength))
                .orElseThrow(RuntimeException::new);
    }

    public static void main(String[] args) {
        List<User> userList = Arrays.asList(
                new User("kuang"),
                new User("jun"),
                new User("lin"));
        System.out.println(byCollecting(userList).getName());

        System.out.println(byReduce(userList).getName());

    }
}
