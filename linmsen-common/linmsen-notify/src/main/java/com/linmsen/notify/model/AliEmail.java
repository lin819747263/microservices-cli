package com.linmsen.notify.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AliEmail {

    private String accountName;
    private String tagName;
    private String toAddress;
    private boolean replyToAddress;
    private int addressType;
    private String fromAlias;
    private String subject;
    private String htmlBody;
}
