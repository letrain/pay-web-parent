package cn.pay.controller;

import cn.pay.config.AlipayConfig;
import cn.pay.form.AlipayCloseForm;
import cn.pay.form.AlipayPayForm;
import cn.pay.form.AlipayRefundForm;
import cn.pay.service.AlipayService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("alipay")
public class AliPayController {
    private Logger logger = LoggerFactory.getLogger(AliPayController.class);

    @Value("${ALIPAY.NOTIFY_URL}")
    private String NOTIFY_URL;

    @Value("${ALIPAY.RETURN_URL}")
    private String RETURN_URL;

    @Value("${ALIPAY.SELLER_ID}")
    private String SELLER_ID;

    private final String charset = "utf-8";

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private AlipayService alipayService;


    /**
     * 发起支付:  支付宝支付
     * @param request
     * @param response
     */
    @PostMapping("/pay")
    public void alipay(HttpServletRequest request, HttpServletResponse response, @RequestBody AlipayPayForm form) {
        System.out.println(RETURN_URL);
        alipayService.alipay(request,response,RETURN_URL,NOTIFY_URL,form);
    }

    /**
     * 支付宝： 退款操作
     * @param refundForm
     */
    @GetMapping("/refund")
    public void refund(@Validated AlipayRefundForm refundForm) {
        //todo 校验参数 refundForm
        alipayService.refund(refundForm);
    }

    /**
     * 支付宝：退款查询
     * @param mapParas
     */
    @GetMapping("/refundQuery")
    public void refundQuery(@RequestBody Map mapParas) {
        //todo 校验参数 mapParas
        alipayService.refundQuery(mapParas);
    }

    /**
     *
     */
    public void close(@RequestBody AlipayCloseForm closeForm) {
        //todo 校验参数
        alipayService.close(closeForm);

    }


    /**
     * alipay 同步通知
     * @return
     */
    @GetMapping("/alipay_return_url")
    public String alipay_return_url() {
        return "alipay/return_url";
    }

    /**
     * alipay  支付的异步通知
     * @return
     */
    @PostMapping("/alipay_notify_url")
    public String alipay_notify_url(HttpServletRequest request) {
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        Boolean signVerified = null;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getALIPAY_PUBLIC_KEY(), charset, alipayConfig.getSIGN_TYPE()); //调用SDK验证签名

        } catch (AlipayApiException e) {
            logger.warn("【异步通知】支付宝：校验失败,msg={}",e.getErrMsg());

        }
        if(signVerified) {
            /* 实际验证过程建议商户务必添加以下校验：
	          1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	          2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	          3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	          4、验证app_id是否为该商户本身。*/
            String out_trade_no = params.get("out_trade_no");
            String total_amout = params.get("total_amout");
            String seller_id = params.get("seller_id"); //卖家支付宝用户号
            String appId = params.get("app_id");

            //通过查询数据库的 orderId
            //todo
            if (SELLER_ID.equals(seller_id) && alipayConfig.getAPPID().equals(appId)) {
                return "alipay/notify_url";
            } else {
                return "fail";
            }
        } else {
            return "fail";
        }
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }


}
