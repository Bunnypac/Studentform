package com.studentform.formbuilder;

import java.sql.*;

public class Application {


        //this is a test comment
        public static void main(String[] args) throws Exception {

            Connection con = JDBCConnection.connect();

            user s1 = dao.getUser(2);
            System.out.println(s1.name);

            user s2 =new user();
            s2.id=4;
            s2.name="Tushar";

            dao.addUser(s2);

            con.close();
        }

}




