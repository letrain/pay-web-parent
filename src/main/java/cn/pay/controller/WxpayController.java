package cn.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wxpay")
public class WxpayController {

    @GetMapping("/pay")
    public String wxpay() {

        return "redirect:aa";
    }
}
