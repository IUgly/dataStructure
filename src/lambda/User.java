package lambda;

public class User {
    private Integer age;
    private String name;

    public boolean isAdult(){
        return this.age>18;
    }
    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }
}
