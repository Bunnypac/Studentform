package formbuilder;

import java.sql.*;

public class Application {


        //this is a test comment
        public static void main(String[] args) throws Exception {

            Connection con = JDBCConnection.connect();
            //Add User:
            UserDAO userDAO = new UserDAO("Tushar", 26);
            UserRepository repository = new UserRepository(con);
          //  repository.addUser(userDAO);

            //Update User:
            UserDAO userDAO2 = new UserDAO("Rohan", 27);
            repository.updateUser(userDAO2);
            //Get User
            UserDAO userDAO1 = repository.getUser("Rohan");
            System.out.println(userDAO1);
            con.close();
        }

}




