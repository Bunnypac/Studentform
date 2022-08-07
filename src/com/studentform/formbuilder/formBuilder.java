package com.studentform.formbuilder;

import java.sql.*;

public class formBuilder {


        //this is a test comment
        public static void main(String[] args) throws Exception {

            userDao dao = new userDao();
            dao.connect();
            user s1 = dao.getUser(2);
            System.out.println(s1.name);


            user s2 =new user();
            s2.id=4;
            s2.name="Tushar";

            dao.connect();
            dao.addUser(s2);

        }

}




