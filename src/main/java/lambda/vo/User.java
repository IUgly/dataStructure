package lambda.vo;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class User {
    private Integer age;
    private String name;
    private List<User> userList;
    private String type;
    private List<BoWen> boWenList;

    public User copy() {
        List<User> users = getUserList().map(User::copy).collect(toList());
        return new User(name, users);
    }
    public User(String name, List<User> members) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(members);

        this.name = name;
        this.userList = new ArrayList<>(members);
    }

    public Stream<User> getUserList() {
        return userList.stream();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<BoWen> getBoWenList() {
        return boWenList;
    }

    public void setBoWenList(List<BoWen> boWenList) {
        this.boWenList = boWenList;
    }

    public boolean isAdult(){
        return this.age>18;
    }
    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public User(String name, List<User> userList, String type) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(userList);
        Objects.requireNonNull(type);
        this.name = name;
        this.userList = new ArrayList<>(userList);
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }


    public User(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> sameOrder = numbers.stream()
                .collect(Collectors.toList());

        Gson gson = new Gson();
        System.out.println(gson.toJson(sameOrder));

        User user = new User("kuang");
    }
}
