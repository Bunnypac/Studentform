package formbuilder;
import java.sql.*;

public class UserRepository {

    Connection con;

    public UserRepository(Connection con) {
        this.con = con;
    }

    public UserDAO getUser(String name)
    {
        try {
            PreparedStatement st =con.prepareStatement("select * from user_data where name=?");
            st.setString(1,name);
            ResultSet rs=st.executeQuery();
            User user = User.of(rs);
            return UserDAO.of(user);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }


    public void addUser(UserDAO userDAO) {
        User user = User.of(userDAO);
        String query="insert into user_data values (?,?,?)";
        try {
            PreparedStatement ps =con.prepareStatement(query);
            ps.setString(1,user.getId());
            ps.setString(2,user.getName());
            ps.setInt(3,user.getAge());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateUser(UserDAO userDAO) {
        User user = User.of(userDAO);
        String query="update user_data set age = ? where name = ?";
        try {
            PreparedStatement ps =con.prepareStatement(query);
            ps.setInt(1,user.getAge());
            ps.setString(2,user.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
