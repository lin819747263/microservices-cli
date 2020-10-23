package com.linmsen.core.vo;

public class CommonResult<T> {
    private Integer code;

    private T data;

    private String message;

    /**
     * 错误明细，内部调试错误
     */
    private String detailMessage;


    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getCode(), result.getMessage(), result.detailMessage);
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        return error(code, message, null);
    }
//
    public static <T> CommonResult<T> error(Integer code, String message, String detailMessage) {
//        Assert.isTrue(!GlobalErrorCodeConstants.SUCCESS.getCode().equals(code), "code 必须是错误的！");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.message = message;
        result.detailMessage = detailMessage;
        return result;
    }
//
//    public static <T> CommonResult<T> success(T data) {
//        CommonResult<T> result = new CommonResult<>();
//        result.code = GlobalErrorCodeConstants.SUCCESS.getCode();
//        result.data = data;
//        result.message = "";
//        return result;
//    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
