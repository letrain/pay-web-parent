package cn.pay.form;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

public class AlipayRefundForm {

    //商户订单号
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    //支付宝交易号 (支付宝返回)
    @JSONField(name = "trade_no")
    private String tradeNo;

    //退款金额
    @JSONField(name = "refund_amount")
    private BigDecimal refundAmount;

    //退款理由
    @JSONField(name = "refund_reason")
    private String refundReason;

    //商户请求编号
    @JSONField(name = "out_request_no")
    private String outRequestNo;

    //操作者编号
    @JSONField(name = "operator_id")
    private String operatorId;

    @JSONField(name = "store_id")
    private String storeId;

    @JSONField(name = "terminal_id")
    private String terminalId;


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

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
}
