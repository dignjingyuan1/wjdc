//package com.flow.snow.snow.controller;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Random;
//
//@RestController
//public class BussinessController {
//
//    /**
//     * 发送短信验证码
//     * @param number接收手机号码
//     */
//    @RequestMapping("/sendSms")
//    @ResponseBody
//    public Object sendSms(HttpServletRequest request, String number) {
//        try {
//            //生成6位验证码
//            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
//            //发送短信
//            ZhenziSmsClient client = new ZhenziSmsClient("你的appId", "你的appSecret");
//            String result = client.send(number, "您的验证码为:" + verifyCode + "，该码有效期为5分钟，该码只能使用一次！【短信签名】");
//            // 将认证码存入SESSION
//            request.getSession().setAttribute("verifyCode", json);
//            return "success";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
