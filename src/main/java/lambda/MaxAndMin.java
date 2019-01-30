package lambda;

import com.google.gson.Gson;
import lambda.vo.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxAndMin {
    public static void main(String[] args) {
        List<User> userList = Arrays.asList(
                new User(12, "zhang"),
                new User(18, "li"),
                new User(22, "wang"));

        //min
//        User shortAgeUser = userList.stream()
//                .min(Comparator.comparing(user -> user.getAge()))
//                .get();
        //max
        User bigAgeUser = userList.stream()
                .max(Comparator.comparing(user -> user.getAge()))
                .get();
        //通用模式
        User shortAgeUser = userList.get(0);
        for (User user : userList){
            if (user.getAge() < shortAgeUser.getAge()){
                shortAgeUser = user;
            }
        }

        Gson gson = new Gson();
        System.out.println(gson.toJson(shortAgeUser));
        System.out.println(gson.toJson(bigAgeUser));


    }
}
