package com.linmsen.notify.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsMessage {

    /**
     * 电话号
     */
    private String phoneNumbers;
    /**
     * 模板编码
     */
    private SmsEnum type;
    /**
     * 验证码
     */
    private int code;
    /**
     * 国际区号
     */
    private String globalRoaming;


}