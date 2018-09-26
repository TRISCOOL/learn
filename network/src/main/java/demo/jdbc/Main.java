package demo.jdbc;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import demo.jdbc.db.Executor;
import demo.jdbc.model.BMLocation;
import demo.jdbc.model.BMResponse;
import demo.jdbc.model.PointMetric;
import demo.jdbc.utils.DateTimeUtil;
import demo.jdbc.utils.OpenTsdbUtil;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args){
        String sql = String.format("select c.platenumber,t.* from travelcount t left join carinfo c on t.carid = c.uuid where t.isTravelEnd =1 and c.platenumber is not null group by t.carid order by `begin` desc;");
        ResultSet resultSet = Executor.execute(sql);
        try {
            while (resultSet.next()){
                String plateNumber = resultSet.getString("platenumber");
                String content = resultSet.getString("content");

                Map<String,String> contentObject = new Gson().fromJson(content,new TypeToken<Map<String,String>>(){}.getType());
                if (contentObject != null){
                    Double endLongitude = Double.parseDouble(contentObject.get("endLongitude"));
                    Double endLatitude = Double.parseDouble(contentObject.get("endLatitude"));
                    String address = getAddress(endLongitude,endLatitude);
                    System.out.println(plateNumber+" : lng: "+endLongitude+" lat: "+endLatitude+" address: "+address);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getAddress(Double lng,Double lat){
        if (lng == null || lat == null){
            return "";
        }
        String location = lat+","+lng;
        String url = "http://api.map.baidu.com/geocoder/v2/?output=json&pois=0&ak=Xd19hVHyu656R0ggACSPv6V8MWBKU9Ll&location="+location;

        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpGet httpGet = new HttpGet(url);


        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK){
                String result = EntityUtils.toString(response.getEntity());
                BMResponse bmResponse = new Gson().fromJson(result,BMResponse.class);
                if (bmResponse != null){
                    BMLocation bmLocation = bmResponse.getResult();
                    if (bmLocation != null){
                        return bmLocation.getFormatted_address();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


}
