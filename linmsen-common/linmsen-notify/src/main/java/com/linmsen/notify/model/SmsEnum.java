package com.linmsen.notify.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SmsEnum {

    /**
     * 注册时使用
     */
    REGISTER("SMS_184815522"),
    /**
     * 修改密码时使用
     */
    PASSWORD("SMS_184830495"),
    /**
     * 修改信息时使用
     */
    MODIFY("SMS_184830495");



    private String type;
}
