package spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;

public class ConnectJDBCDemo {

    public static void main(String[] args){
        SparkSession spark = SparkSession.builder().appName("testConnectDB").getOrCreate();

        String jdbcUrl = "jdbc:mysql://192.168.101.32/tera_auto_dev?useUnicode=true&characterEncoding=UTF-8";

        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "password");

        Dataset<Row> db = spark.read().jdbc(jdbcUrl,"schema.carinfo",properties);
        db.show(3);
    }

}
