package demo.jdbc.db;

import java.sql.ResultSet;

public class Executor {

    public static ResultSet execute(String sql){
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.connect();
        ResultSet resultSet = dbHandler.query(sql);
        return resultSet;
    }

    public static void colse(){
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.close();
    }
}
