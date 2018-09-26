package demo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args){

        //String rate = computerRate(100d,119.87d);


/*        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(new Date());
        System.out.println(time.replaceAll("-","").replaceAll(" ","").replaceAll(":",""));*/
/*        BigDecimal bigDecimal = new BigDecimal(0.00);
        System.out.println(bigDecimal.doubleValue() == 0.00);*/

/*        String url = "https://detailskip.taobao.com/service/getData/1/p1/item/detail/sib.htm?itemId=566721477572&sellerId=57220545&modules=dynStock,qrcode,viewer,price,duty,xmpPromotion,delivery,upp,activity,fqg,zjys,couponActivity,soldQuantity,originalPrice,tradeContract&callback=onSibRequestSuccess";
        String referer = "https://item.taobao.com/item.htm?spm=a230r.1.14.30.48501b1eA7idsA&id=566721477572&ns=1&abbucket=7";

        String reponse = getResponse(url,referer);
        if (reponse.indexOf("rgv587_flag") != -1){
            System.out.println("输入：");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()){
                String check = scanner.next();
                ErrorLimitIpResponse errorLimitIpResponse = new Gson().fromJson(reponse,new TypeToken<ErrorLimitIpResponse>(){}.getType());
                String checkUrl = errorLimitIpResponse.getUrl();
                if (checkUrl != null){

                }

            }
        }else {
            System.out.println(reponse);
        }*/
    }

    private  static String computerRate(Double ywMile , Double iotMile){
        if (ywMile.equals(0d)){
            return "-";
        }

        Double c = ywMile - iotMile >= 0?ywMile - iotMile : iotMile - ywMile;

        BigDecimal bigDecimal = new BigDecimal(c/ywMile).setScale(2,BigDecimal.ROUND_DOWN);

        return bigDecimal.doubleValue() * 100 + "%";
    }

    public static String getResponse(String url,String referer){
        CloseableHttpClient httpClient = HttpClients.custom().build();
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("referer", referer);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int code = response.getStatusLine().getStatusCode();
            String resStr = EntityUtils.toString(response.getEntity());
            resStr = resStr.substring(resStr.indexOf("(")+1,resStr.indexOf(")"));
            return resStr;
        } catch (Exception e) {

        }
        return null;
    }

}
