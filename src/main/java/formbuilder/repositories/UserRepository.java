package formbuilder.repositories;
import formbuilder.dao.UserDAO;
import formbuilder.pojo.User;

import java.sql.*;
import java.util.Optional;

public class UserRepository {

    Connection con;

    public UserRepository(Connection con) {
        this.con = con;
    }

    private Optional<User> getUserWithID(String name)
    {
        try {
            PreparedStatement st =con.prepareStatement("select * from user_data where name=?");
            st.setString(1,name);
            ResultSet rs=st.executeQuery();
            return Optional.of(User.of(rs));

        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    public UserDAO getUser(String name)
    {
        return getUserWithID(name).map(UserDAO::of).orElse(null);
    }

    public boolean addUser(UserDAO userDAO) {
        User user = User.of(userDAO);
        String query="insert into user_data values (?,?,?)";
        try {
            PreparedStatement ps =con.prepareStatement(query);
            ps.setString(1,user.getId());
            ps.setString(2,user.getName());
            ps.setInt(3,user.getAge());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteUser(String name) {
        String query="delete from user_data where name = ?";
        try {
            PreparedStatement ps =con.prepareStatement(query);
            ps.setString(1,name);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    public boolean updateUser(String name, UserDAO userDAO) {
        Optional<User> user = getUserWithID(name);
        User updatedUser = User.of(userDAO);
        String query="update user_data set age = ?, name = ?  where id = ?";
        try {
            PreparedStatement ps =con.prepareStatement(query);
            ps.setInt(1, updatedUser.getAge());
            ps.setString(2,updatedUser.getName());
            if(user.isEmpty()) {
                throw new RuntimeException("Original User not found!");
            }
            ps.setString(3, user.get().getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
