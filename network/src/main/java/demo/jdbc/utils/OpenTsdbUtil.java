package demo.jdbc.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import demo.jdbc.model.PointMetric;
import demo.jdbc.tsdb.Queries;
import demo.jdbc.tsdb.TsdbAggregation;
import demo.jdbc.tsdb.TsdbConditions;
import demo.jdbc.tsdb.TsdbResult;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OpenTsdbUtil {

    private static List<String> pointFullName(String carId, List<String> points) {
        List<String> res = new ArrayList<String>();
        points.forEach(pointName -> {
            String key = String.format("%s.%s", carId, pointName);
            res.add(key);
        });
        return res;
    }


    public static Map<String, List<PointMetric>> listMetrics(String plateNumber,String carId, List<String> points, int begin, int end) {

            List<String> realpoints = pointFullName(carId, points);

            TsdbConditions conditions = new TsdbConditions();
            List<Queries> queries = realpoints.stream().map(point -> {
                Queries q = new Queries();
                q.setMetric(point);
                q.setAggregator(TsdbAggregation.AVG.getName());
                q.setDownsample(null);
                return q;
            }).collect(Collectors.toList());
            conditions.setStart(begin + "");
            conditions.setEnd(end + "");
            conditions.setQueries(queries);

            Map<String, List<PointMetric>> resultMap = new HashMap<>();
            String uri = "http://192.168.5.43:4242/api/query/";

            try {
                List<TsdbResult> tsdbResults = queryTSDB(uri, conditions, new TypeToken<List<TsdbResult>>() {
                }.getType());

                tsdbResults.forEach(tsdbResult -> {
                    String name = tsdbResult.getMetric();
                    String key = name.substring(name.lastIndexOf('.') + 1);
                    Map<String, Double> dps = tsdbResult.getDps();
                    List<PointMetric> metrics = dps.keySet().stream().map(k -> {
                        PointMetric me = new PointMetric();
                        me.setModelName(key);
                        me.setTs(Long.valueOf(k));
                        try {
                            me.setValue(dps.get(k));
                        } catch (NumberFormatException e) {
                            me.setBinVal(ByteBuffer.wrap(dps.get(k).toString().getBytes()));
                        }
                        return me;
                    }).collect(Collectors.toList());
                    resultMap.put(key, metrics);
                });

            } catch (IOException e) {
                System.out.println(plateNumber + " error : "+e.getMessage());
                return null;
            }
            return resultMap;
    }


    private static <T> T queryTSDB(String uri, TsdbConditions conditions, Type type) throws IOException {

        CloseableHttpClient closeableHttpClient = HttpClients.custom().build() ;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000*60*10)
                .setConnectionRequestTimeout(1000*60*10)
                .setSocketTimeout(1000*60*10).build();
        try{
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setConfig(config);
            Gson gson = new Gson();
            httpPost.setEntity(new StringEntity(gson.toJson(conditions)));

            String result;
            try {
                CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
                int code = response.getStatusLine().getStatusCode();
                if (HttpStatus.SC_OK != code) {
                    if (HttpStatus.SC_NO_CONTENT != code) {
                        result = EntityUtils.toString(response.getEntity());
                    }
                }
                result = EntityUtils.toString(response.getEntity());
            }catch (Exception e){
                throw new IOException(e);
            }
            return gson.fromJson(result, type);
        } catch (Exception e) {
            throw e;
        }
    }
}
