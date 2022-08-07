package formbuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class User {
   private String id;
   private String name;
   private int age;

    public User(String name, int age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    private User() {}

    public static User of(ResultSet rs) throws SQLException {
        rs.next();
        User user = new User();
        user.id = rs.getString(1);
        user.name = rs.getString(2);
        user.age = rs.getInt(3);
        return user;
    }

    public static User of(UserDAO userDAO) {
        User user = new User(userDAO.getName(), userDAO.getAge());
        return user;
    }


}
