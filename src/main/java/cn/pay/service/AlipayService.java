package cn.pay.service;

import cn.pay.form.AlipayCloseForm;
import cn.pay.form.AlipayPayForm;
import cn.pay.form.AlipayRefundForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AlipayService {

    /**
     *
     * @param returnUrl 同步通知Url
     * @param notifyUrl 异步通知Url
     */
    void alipay(HttpServletRequest request, HttpServletResponse response, String returnUrl, String notifyUrl, AlipayPayForm payForm);

    //退款操作
    void refund(AlipayRefundForm refundForm);

    /**
     *  交易退款查询
     * @param params  支付宝交易号 tradeNo,    (trade_no)支付宝调用时格式
     *                商户订单号 outTradeNo,    out_trade_no
     *                商户请求编号 outRequestNo   out_request_no
     */
    void refundQuery(Map params);

    //交易关闭
    void close(AlipayCloseForm closeForm);
}
