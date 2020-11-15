package com.linmsen.user.controller;

import com.linmsen.core.vo.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/failure")
    public CommonResult failure(){
        return CommonResult.error(1,"登录失败");
    }
}
