package com.flow.snow.snow.controller;

import com.alibaba.fastjson.JSON;
import com.flow.snow.snow.entity.User;
import com.flow.snow.snow.service.UserService;
import com.flow.snow.snow.util.AuthUtil;
import com.flow.snow.snow.util.SmsMultiSender;
import com.flow.snow.snow.util.SmsMultiSenderResult;
import com.flow.snow.snow.util.httpclient.HTTPException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserService.class);
    public static final String APPID = "wxd64c8fef6542b36b";
    public static final String APPSECRET = "39607c3fb8f283ad66491c04c468945e";
    @Autowired
    UserService userService;

    @RequestMapping(path = "/reg", method = RequestMethod.POST)
    public boolean reg(User user) {
        try {
            user.setOpenid(UUID.randomUUID().toString());
            userService.insertUser(user);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public User login(String phoneNumber, String password) {
        List<User> res = userService.login(phoneNumber, password);
        if (res.size() > 0) {
            return res.get(0);
        } else {
            return null;
        }
    }

    @RequestMapping(path = "/sendCode", method = RequestMethod.POST)
    public void sendValidate(String code, String phoneNumber) throws HTTPException, IOException {
        String[] phoneNumbers = new String[]{phoneNumber};
        String[] codeArr = {code, "5"};
        SmsMultiSender msender = new SmsMultiSender(1400100451, "e9e96c66f3b24e15aa62d8020bb4ec1f");
        SmsMultiSenderResult result = msender.sendWithParam("86", phoneNumbers,
                478575, codeArr, "易佰思", "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
        System.out.print(result);
    }

    @RequestMapping(path = "/getOpenid", method = RequestMethod.GET)
    public Map<String, String> getOpenid(String code) throws IOException {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID
                + "&secret=" + APPSECRET
                + "&code=" + code
                + "&grant_type=authorization_code";
        JSONObject jsonObject = AuthUtil.doGetJson(url);
        logger.info("get token api return:" + jsonObject.toString());
        String token = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
        // 根据openid查用户如果查到了直接跳过登录
        List<User> users = userService.getUserByOpenid(openid);
        logger.info("根据openid查询到的user:" + users.size());
        logger.info("——————————code:" + code);
        if (!users.isEmpty()) {
            Map<String, String> map = new HashMap<>();
            User user = users.get(0);
            map.put("openid", user.getOpenid());
            map.put("phoneNumber", user.getPhoneNumber());
            return map;
        } else {
            String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token
                    + "&openid=" + openid
                    + "&lang=zh_CN";
            //通过网络请求方法来请求上面这个接口
            JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
            logger.info("get userinfo api return:" + userInfo.toString());
            Map<String, String> map = new HashMap<>();
            map.put("openid", userInfo.getString("openid"));
            map.put("phoneNumber", "");
            return map;
        }
    }
}
