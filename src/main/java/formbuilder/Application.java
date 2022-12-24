package formbuilder;

import com.google.gson.Gson;
import formbuilder.dao.UserDAO;
import formbuilder.repositories.UserRepository;
import formbuilder.utility.JDBCConnection;
import formbuilder.utility.StandardResponse;
import formbuilder.utility.StatusResponse;

import java.sql.*;

import static spark.Spark.*;

public class Application {

    private static UserRepository repository;
    private static Connection con;


    public static void main(String[] args) throws Exception {

        initDB();
        //TODO: Create JUnit test cases rather than creating this testDB method.
        //Test DB
        //testDB();

        //getUser
        get("/user/:name", (req,res) -> new Gson().toJson(repository.getUser(req.params(":name"))));

        //addUser
        post("/user", (req,res) -> {
            res.type("application/json");
            UserDAO userDAO = new Gson().fromJson(req.body(),UserDAO.class);
            if(repository.addUser(userDAO)) {
                return new Gson()
                        .toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJson(userDAO)));
            }
            return new Gson()
                    .toJson(new StandardResponse(StatusResponse.ERROR));
        });

        //updateUser
        put("/user/:name",(req,res) -> {
            res.type("application/json");
            UserDAO userDAO = new Gson().fromJson(req.body(),UserDAO.class);
            String name = req.params(":name");
            if(repository.updateUser(name, userDAO)) {
                UserDAO updatedUser = repository.getUser(userDAO.getName());
                return new Gson()
                        .toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJson(updatedUser)));
            }
            return new Gson()
                    .toJson(new StandardResponse(StatusResponse.ERROR));
        });

        //deleteUser
        //END_POINT: http://localhost:4567/user/Rohan%20Tekade
        delete("/user/:name", (req,res) -> {
            res.type("application/json");
            if(repository.deleteUser(req.params(":name"))) {
                return new Gson()
                        .toJson(new StandardResponse(StatusResponse.SUCCESS));
            }
            return new Gson()
                    .toJson(new StandardResponse(StatusResponse.ERROR));
        });

    }

    private static void initDB() throws SQLException, ClassNotFoundException {
        con = JDBCConnection.connect();
        repository = new UserRepository(con);
    }

    private static void testDB() {
        //Add User:
        UserDAO userDAO = new UserDAO("Tushar", 26);

        repository.addUser(userDAO);

        //Update User:
        UserDAO userDAO2 = new UserDAO("Tushar", 27);
        repository.updateUser("Tushar",userDAO2);

        //Get User
        UserDAO userDAO1 = repository.getUser("Tushar");
        System.out.println(userDAO1);

        //Delete User
        //repository.deleteUser("Tushar");
    }


}




