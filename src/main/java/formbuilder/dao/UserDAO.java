package formbuilder.dao;

import formbuilder.pojo.User;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDAO userDAO = (UserDAO) o;
        return age == userDAO.age && Objects.equals(name, userDAO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "UserDAO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static UserDAO of(User user) {
        UserDAO userDAO = new UserDAO(user.getName(), user.getAge());
        return userDAO;
    }
}