package demo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        String url = "https://detailskip.taobao.com/service/getData/1/p1/item/detail/sib.htm?itemId=566721477572&sellerId=57220545&modules=dynStock,qrcode,viewer,price,duty,xmpPromotion,delivery,upp,activity,fqg,zjys,couponActivity,soldQuantity,originalPrice,tradeContract&callback=onSibRequestSuccess";
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
        }
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
