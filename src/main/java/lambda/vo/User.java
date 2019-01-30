package lambda.vo;

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
}
