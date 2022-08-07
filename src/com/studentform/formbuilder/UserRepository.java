package com.studentform.formbuilder;

import java.sql.*;

public class UserRepository {
    Connection con=null;

   

    public User getUser(int id)
    {
        try {
        User s = new User();
        s.id=id;
        String query="select name from userdata where id="+;

            Statement st =con.createStatement();
            ResultSet rs=st.executeQuery(query);
            rs.next();
            String username=rs.getString(1);
            s.name=username;



            return s;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }


    public void addUser(User s) {
        String query="insert into userdata values (?,?)";
        try {
            PreparedStatement ps =con.prepareStatement(query);
            ps.setInt(1,s.id);
            ps.setString(2,s.name);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
