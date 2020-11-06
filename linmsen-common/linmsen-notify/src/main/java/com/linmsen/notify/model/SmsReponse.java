package com.linmsen.notify.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsReponse {
    private String message;
    private String requestId;
    private String code;
}
