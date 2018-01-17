package spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class WordCount {

    public static void main(String[] args){
        if (args.length < 2){
            System.out.println("please input inputUrl outputUrl");
        }

        String input = args[0];
        String output = args[1];

        SparkConf sparkConf = new SparkConf().setAppName("WordCount");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> row = sparkContext.textFile(input);
        JavaPairRDD<String,Integer> words = row.flatMap(line -> {
            return Arrays.asList(line.split(" ")).iterator();
        }).mapToPair(x -> new Tuple2<>(x,1));

        JavaPairRDD<String,Integer> count = words.reduceByKey((a,b) -> (a+b));

        count.saveAsTextFile(output);
    }
}
