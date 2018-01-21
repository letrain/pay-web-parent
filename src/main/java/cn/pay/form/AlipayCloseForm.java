package cn.pay.form;

import com.alibaba.fastjson.annotation.JSONField;

public class AlipayCloseForm {

    //商户订单号
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    //支付宝交易号 (支付宝返回)
    @JSONField(name = "trade_no")
    private String tradeNo;

    //操作者编号
    @JSONField(name = "operator_id")
    private String operatorId;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
}
