package cn.pay.form;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Map;

public class AlipayPayForm {

    //商户订单号
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    //产品编号
    @JSONField(name = "product_code")
    private String productCode;

    //订单金额
    @JSONField(name = "total_amount")
    private BigDecimal totalAmout;

    //订单标题
    private String subject;

    //商品描述
    private String body;

    //额外参数
    @JSONField(name = "extend_params")
    private Map extendParams;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getTotalAmout() {
        return totalAmout;
    }

    public void setTotalAmout(BigDecimal totalAmout) {
        this.totalAmout = totalAmout;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map getExtendParams() {
        return extendParams;
    }

    public void setExtendParams(Map extendParams) {
        this.extendParams = extendParams;
    }
}
