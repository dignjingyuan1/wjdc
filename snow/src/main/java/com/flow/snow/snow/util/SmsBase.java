package com.flow.snow.snow.util;


import com.flow.snow.snow.util.httpclient.HTTPClient;
import com.flow.snow.snow.util.httpclient.HTTPException;
import com.flow.snow.snow.util.httpclient.HTTPResponse;

public class SmsBase {

    protected int appid;
    protected String appkey;
    protected HTTPClient httpclient;

    /**
     * SmsBase constructor
     *
     * @param appid   sdk appid
     * @param appkey  sdk appkey
     * @param httpclient  http client
     */
    public SmsBase(int appid, String appkey, HTTPClient httpclient) {
        this.appid = appid;
        this.appkey = appkey;
        this.httpclient = httpclient;
    }

    /**
     * Handle http status error
     *
     * @param response   raw http response
     * @return response  raw http response
     * @throws HTTPException  http status exception
     */
    public HTTPResponse handleError(HTTPResponse response) throws HTTPException {
        if (response.statusCode < 200 || response.statusCode >= 300) {
            throw new HTTPException(response.statusCode, response.reason);
        }
        return response;
    }
}
