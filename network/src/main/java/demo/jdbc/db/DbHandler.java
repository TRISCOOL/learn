package demo.jdbc.db;

import java.sql.*;

public class DbHandler {
    public String url;
    public String name;
    public String user;
    public String password;

    public Connection conn = null;

    public static DbHandler instance;

    public static DbHandler getInstance() {
        if (instance != null) return instance;
        synchronized (DbHandler.class) {
            if (instance == null) {
                instance = new DbHandler();
                instance.init();
            }
        }
        return instance;
    }

    private void init() {
        DbConfig dbConfig = DbConfig.getInstance();
        url = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=Asia/Shanghai",
                dbConfig.getDBHost(), dbConfig.getDBPort(), dbConfig.getDBDatabase());
        name = dbConfig.getDBDriver();
        user = dbConfig.getDBUser();
        password = dbConfig.getDBPassword();
    }

    public void connect() {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String sql) {
        try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
