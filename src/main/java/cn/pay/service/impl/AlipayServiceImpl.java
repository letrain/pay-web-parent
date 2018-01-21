package cn.pay.service.impl;

import cn.pay.config.AlipayConfig;
import cn.pay.form.AlipayCloseForm;
import cn.pay.form.AlipayPayForm;
import cn.pay.form.AlipayRefundForm;
import cn.pay.service.AlipayService;
import cn.pay.util.UUIDUtil;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AlipayServiceImpl implements AlipayService {

    private Logger logger = LoggerFactory.getLogger(AlipayServiceImpl.class);

    @Autowired
    private AlipayClient alipayClient;

    private final String CHARSET = "UTF-8";
    @Override
    public void alipay(HttpServletRequest request, HttpServletResponse response, String returnUrl, String notifyUrl,AlipayPayForm alipayPayForm) {
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setNotifyUrl(notifyUrl);
//        AlipayPayForm payForm = (AlipayPayForm) request.getAttribute("alipayForm");
        //TODO 去数据库获取订单号
        alipayPayForm.setOutTradeNo(UUIDUtil.genUUID());
        alipayPayForm.setSubject("APPLE PURCHASE");
        Map<String,String> params = new HashMap();
        //TODO 商品详细信息根据商品ID 获取相关信息 并封装到 params
        params.put("number","1");
        params.put("color","grey");
        params.put("capacity","64G");
        alipayPayForm.setExtendParams(params);
        alipayRequest.setBizContent(JSON.toJSONString(alipayPayForm));//填充业务数据
        String form ="";//返回结果
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        } catch (AlipayApiException e) {
            //支付异常...
            e.printStackTrace();
            logger.error("【支付宝支付】 异常，msg ={}.",e.getErrMsg());
        }

        response.setContentType("text/html;charset=" + CHARSET);
        try {
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        异步通知验签：

        Map<String, String> paramsMap = ... //将异步通知中收到的所有参数都存放到map中
        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE) //调用SDK验证签名
        if(signVerfied){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
        }else{
            // TODO 验签失败则记录异常日志，并在response中返回failure.
        }
        */
    }

    @Override
    public void refund(AlipayRefundForm refundForm) {
        AlipayTradeRefundRequest refundRequest = new AlipayTradeRefundRequest();
        refundRequest.setBizContent(JSON.toJSONString(refundForm));
        try {
            AlipayTradeRefundResponse refundResponse = alipayClient.execute(refundRequest);
            if (refundResponse.isSuccess()) {
                //todo  加库存，减去交易量，数据库操作
                logger.info("【退款】成功，商户订单号={},支付宝交易号={}.",refundResponse.getOutTradeNo(),refundResponse.getTradeNo());
            } else {
                logger.warn("【退款】失败,商户订单号={},支付宝交易号={}.",refundResponse.getOutTradeNo(),refundResponse.getTradeNo());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refundQuery(Map params) {
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizContent(JSON.toJSONString(params));

        try {
            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
            if(response.isSuccess()) {

                logger.info("【退款查询】成功：{}",response.getIndustrySepcDetail());
            } else {
                logger.info("【退款查询】失败：{}",response.getIndustrySepcDetail());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(AlipayCloseForm closeForm) {
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizContent(JSON.toJSONString(closeForm));
        AlipayTradeCloseResponse response = null;
        try {
            response = alipayClient.execute(request);

            if(response.isSuccess()){
                logger.info("【关闭交易】成功,订单号={}，交易号={}",response.getOutTradeNo(),response.getTradeNo());
            } else {
                logger.warn("【关闭交易】失败,订单号={}，交易号={}\",response.getOutTradeNo(),response.getTradeNo()");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }

//    public static void main(String[] args) {
//        Map map = new HashMap();
//        map.put("tradeNo","111");
//        map.put("outTradeNo","2222");
//        System.out.println(JSON.toJSONString(map));
//
//        AlipayRefundForm refundForm = new AlipayRefundForm();
//        refundForm.setOperatorId("3333");
//        refundForm.setOutRequestNo("4444");
//        refundForm.setOutTradeNo("55555");
//        refundForm.setRefundAmount(new BigDecimal(44.55));
//        refundForm.setRefundReason("6666");
//        refundForm.setStoreId("7777");
//        System.out.println(JSON.toJSONString(refundForm));
//
//
//    }
}
