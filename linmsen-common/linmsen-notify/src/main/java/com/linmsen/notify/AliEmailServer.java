package com.linmsen.notify;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.linmsen.notify.model.AliEmail;

public class AliEmailServer {
    //    private static Logger logger=LogManagerUtils.getPlatformLogger();
    private final static String ACCESS_KEY_ID = "LTAI4FihwFqzUYvsx5tgUavi";
    private final static String ACCESS_KEY_SECRET = "XyoshfhXhdE9WcrhzXQhQEX21tjxI3";

    public static void main(String[] args) {
        AliEmail aliEmail = new AliEmail();
        aliEmail.setAccountName("linmsen@email.linmsen.com");
        aliEmail.setAddressType(1);
        aliEmail.setFromAlias("shuaishuai");
        String code = "123456";
        aliEmail.setHtmlBody("亲爱的会员：您好！\r 您正在注册平台用户，请在验证码输入框输入：" + code + ",以完成操作");
        aliEmail.setReplyToAddress(false);
        aliEmail.setSubject("林木森工作室");
        aliEmail.setTagName("hello");
        aliEmail.setToAddress("819747263@qq.com");
        singleSendEmail(aliEmail);
    }

    public static String singleSendEmail(AliEmail aliEmail) {

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);


        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName(aliEmail.getAccountName());
            request.setFromAlias(aliEmail.getFromAlias());
            request.setAddressType(aliEmail.getAddressType());
            request.setTagName(aliEmail.getTagName());
            request.setReplyToAddress(aliEmail.isReplyToAddress());
            request.setToAddress(aliEmail.getToAddress());
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress("邮箱1,邮箱2");
            request.setSubject(aliEmail.getSubject());
            request.setHtmlBody(aliEmail.getHtmlBody());
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
//            System.out.println(JSON.toJSON(httpResponse));
            return "success";
        } catch (ClientException e) {
//            logger.error(e.getMessage(), e);
            return "error";
        }
    }


    {

    }
}
