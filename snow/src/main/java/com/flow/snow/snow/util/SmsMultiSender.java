package com.flow.snow.snow.util;

import com.flow.snow.snow.util.httpclient.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;


public class SmsMultiSender extends SmsBase {

    private String url = "https://yun.tim.qq.com/v5/tlssmssvr/sendmultisms2";

    public SmsMultiSender(int appid, String appkey) {
        super(appid, appkey, new DefaultHTTPClient());
    }

    public SmsMultiSender(int appid, String appkey, HTTPClient httpclient) {
        super(appid, appkey, httpclient);
    }

    /**
     * 指定模板群发
     *
     * @param nationCode 国家码，如 86 为中国
     * @param phoneNumbers 不带国家码的手机号列表
     * @param templateId 模板 id
     * @param params 模板参数列表
     * @param sign 签名，如果填空，系统会使用默认签名
     * @param extend 扩展码，可以填空
     * @param ext 服务端原样返回的参数，可以填空
     * @return {@link}SmsMultiSenderResult
     * @throws HTTPException  http status exception
     * @throws JSONException  json parse exception
     * @throws IOException    network problem
     */
    public SmsMultiSenderResult sendWithParam(String nationCode, ArrayList<String> phoneNumbers,
        int templateId, ArrayList<String> params, String sign, String extend, String ext)
            throws HTTPException, JSONException, IOException {

        long random = SmsSenderUtil.getRandom();
        long now = SmsSenderUtil.getCurrentTime();
        JSONObject body = new JSONObject()
            .put("tel", toTel(nationCode, phoneNumbers))
            .put("sign", sign)
            .put("tpl_id", templateId)
            .put("params", params)
            .put("sig", SmsSenderUtil.calculateSignature(appkey, random, now, phoneNumbers))
            .put("time", now)
            .put("extend", SmsSenderUtil.isNotEmpty(extend) ? extend : "")
            .put("ext", SmsSenderUtil.isNotEmpty(ext) ? ext : "");

        HTTPRequest req = new HTTPRequest(HTTPMethod.POST, this.url)
            .addHeader("Conetent-Type", "application/json")
            .addQueryParameter("sdkappid", this.appid)
            .addQueryParameter("random", random)
            .setConnectionTimeout(60 * 1000)
            .setRequestTimeout(60 * 1000)
            .setBody(body.toString());

        try {
            // May throw IOException and URISyntaxexception
            HTTPResponse res = httpclient.fetch(req);

            // May throw HTTPException
            handleError(res);

            // May throw JSONException
            return (new SmsMultiSenderResult()).parseFromHTTPResponse(res);
        } catch (URISyntaxException e) {
            throw new RuntimeException("API url has been modified, current url: " + url);
        }
    }

    public SmsMultiSenderResult sendWithParam(String nationCode, String[] phoneNumbers,
        int templateId, String[] params, String sign, String extend, String ext)
            throws HTTPException, JSONException, IOException {

        return sendWithParam(nationCode, new ArrayList<String>(Arrays.asList(phoneNumbers)),
                             templateId, new ArrayList<String>(Arrays.asList(params)),
                             sign, extend, ext);
    }

    private ArrayList<JSONObject> toTel(String nationCode, ArrayList<String> phoneNumbers) {
        ArrayList<JSONObject> phones = new ArrayList<JSONObject>();
        for (String phoneNumber: phoneNumbers) {
            JSONObject phone = new JSONObject();
            phone.put("nationcode", nationCode);
            phone.put("mobile", phoneNumber);
            phones.add(phone);
        }
        return phones;
    }
}
