package com.linmsen.notify;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.linmsen.notify.model.SmsEnum;
import com.linmsen.notify.model.SmsMessage;
import com.linmsen.notify.model.SmsReponse;
import com.sun.istack.NotNull;

public class SmsServer {

//    private static Logger logger = LogManagerUtils.getBusinessLogger();

    private final static String ACTION = "SendSms";
    private final static String DOMAIN = "dysmsapi.aliyuncs.com";

    private final static String SIGN_NAME = "林木森博客";
    private final static String ACCESS_KEY_ID = "LTAI4FihwFqzUYvsx5tgUavi";
    private final static String ACCESS_KEY_SECRET = "XyoshfhXhdE9WcrhzXQhQEX21tjxI3";

    public static SmsReponse sendSingalMessage(SmsMessage message){

        DefaultProfile profile = DefaultProfile.getProfile("default",
                ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //固定参数
        request.setMethod(MethodType.POST);
        request.setVersion("2017-05-25");
        request.setDomain(DOMAIN);
        request.setAction(ACTION);
        request.putQueryParameter("SignName", SIGN_NAME);

        //自定义参数 增加手机号和短信模板参数
        //86或者不填 用国内模板，发送时不加国际区号
        request.putQueryParameter("PhoneNumbers", message.getPhoneNumbers());
        request.putQueryParameter("TemplateCode", message.getType().getType());

        //验证码填充
        request.putQueryParameter("TemplateParam", getCode(message.getCode()));


        try {
            CommonResponse response = client.getCommonResponse(request);
//            logger.info(response.getData());
            return JSONObject.parseObject(response.getData(), SmsReponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new SmsReponse();
        }
    }

    private static String getCode(int code){
        return "{\"code\":\"" + code + "\"}";
    }

    public static void main(String[] args) {
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setPhoneNumbers("18434351211");
        smsMessage.setCode(123456);
        smsMessage.setType(SmsEnum.REGISTER);
        sendSingalMessage(smsMessage);
    }

}
