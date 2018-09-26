package demo.jdbc.db;

import java.util.Map;

public class DbConfig {
    private String DBHost = "172.16.200.208";
    private String DBPort = "3306";
    private String DBUser = "root";
    private String DBPassword = "tongfang";
    private String DBDatabase = "cybermonkey";
    private String DBDriver = "com.mysql.cj.jdbc.Driver";

    private static DbConfig dbConfigl;

    public static DbConfig getInstance(){
        if (dbConfigl != null)
            return dbConfigl;
        synchronized (DbConfig.class){
            if (dbConfigl != null)
                return dbConfigl;

            dbConfigl = new DbConfig();
            dbConfigl.init();
            return dbConfigl;
        }
    }

    public String getDBHost() {
        return DBHost;
    }

    public void setDBHost(String DBHost) {
        this.DBHost = DBHost;
    }

    public String getDBPort() {
        return DBPort;
    }

    public void setDBPort(String DBPort) {
        this.DBPort = DBPort;
    }

    public String getDBUser() {
        return DBUser;
    }

    public void setDBUser(String DBUser) {
        this.DBUser = DBUser;
    }

    public String getDBPassword() {
        return DBPassword;
    }

    public void setDBPassword(String DBPassword) {
        this.DBPassword = DBPassword;
    }

    public String getDBDatabase() {
        return DBDatabase;
    }

    public void setDBDatabase(String DBDatabase) {
        this.DBDatabase = DBDatabase;
    }

    public String getDBDriver() {
        return DBDriver;
    }

    public void setDBDriver(String DBDriver) {
        this.DBDriver = DBDriver;
    }

    private void init() {
        Map<String, String> env = System.getenv();
        if (env.containsKey("DB_HOST")) {
            DBHost = env.get("DB_HOST");
        }
        if (env.containsKey("DB_PORT")) {
            DBPort = env.get("DB_PORT");
        }
        if (env.containsKey("DB_USER")) {
            DBUser = env.get("DB_USER");
        }
        if (env.containsKey("DB_PASSWORD")) {
            DBPassword = env.get("DB_PASSWORD");
        }
        if (env.containsKey("DB_DATABASE")) {
            DBDatabase = env.get("DB_DATABASE");
        }
        if (env.containsKey("DB_DRIVER")) {
            DBDriver = env.get("DB_DRIVER");
        }
    }
}
