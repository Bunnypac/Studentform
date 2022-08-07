package com.studentform.formbuilder;

import java.sql.*;

public class userDao {
    Connection con=null;

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/StudentInfo","postgres","qwerty");

        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public user getUser(int id)
    {
        try {
        user s = new user();
        s.id=id;
        String query="select name from userdata where id="+id;

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


    public void addUser(user s) {
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
