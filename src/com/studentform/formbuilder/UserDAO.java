public class UserDAO {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public UserDAO(String name, int age) {
        this.name = name;
        this.age = age;
    }
}