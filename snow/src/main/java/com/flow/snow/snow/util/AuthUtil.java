package com.flow.snow.snow.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class AuthUtil {
    public static final String APPID = "wx45c1428e5584fcdb";
    public static final String APPSECRET = "98174450eb706ada330f37e646be85d5";

    public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
        JSONObject obj = null;
        //首先初始化HttpClient对象
        DefaultHttpClient client = new DefaultHttpClient();
        //通过get方式进行提交
        HttpGet httpGet = new HttpGet(url);
        //通过HTTPclient的execute方法进行发送请求
        HttpResponse response = client.execute(httpGet);
        //从response里面拿自己想要的结果
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            obj = new JSONObject(result);
        }
        //把链接释放掉
        httpGet.releaseConnection();
        return obj;
    }
}
