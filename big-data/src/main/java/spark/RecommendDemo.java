package spark;

import model.Rating;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.recommendation.ALS;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class RecommendDemo {

    public static void main(String[] args){

        String input = args[0];

        SparkSession spark = SparkSession.builder().appName("recommend").getOrCreate();

        JavaRDD<Rating> ratingJavaRDD = spark.read().textFile(input).javaRDD().map(Rating::parseRating);

        Dataset<Row> ratings = spark.createDataFrame(ratingJavaRDD,Rating.class);
        Dataset<Row>[] splits = ratings.randomSplit(new double[]{0.8,0.2});
        Dataset<Row> training = splits[0];
        Dataset<Row> test = splits[1];

        ALS als = new ALS()
                .setMaxIter(5)
                .setRegParam(0.01)
                .setUserCol("userId")
                .setItemCol("movieId")
                .setRatingCol("rating");

        ALSModel model = als.fit(training);
        model.setColdStartStrategy("drop");

        Dataset<Row> predictions = model.transform(test);
        RegressionEvaluator evaluator = new RegressionEvaluator()
                .setMetricName("rmse")
                .setLabelCol("rating")
                .setPredictionCol("prediction");

        Double rmse = evaluator.evaluate(predictions);
        System.out.println("Root-mean-square error = " + rmse);

        Dataset<Row> userRecs = model.recommendForAllUsers(10);
        Dataset<Row> movieRecs = model.recommendForAllItems(10);

        userRecs.show();
        movieRecs.show();

        spark.stop();
    }
}
