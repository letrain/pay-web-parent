<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/17
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>title</title>
    <link href="http://static.dev.zhixike.net/lib/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <link href="http://static.dev.zhixike.net/lib/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://static.dev.zhixike.net/lib/g/1.1.0/g.min.css" rel="stylesheet">
    <link href="http://static.dev.zhixike.net/lib/g/1.1.0/theme/green.min.css" rel="stylesheet">
</head>
<body>

<style>
    #btnSubmitOrder {
        background: none;
        border: 1px solid #e53e26;
        color: #e53e26;
        cursor: pointer;
        display: inline-block;
        height: 48px;
        text-align: center;
        width: 158px;
        font-size: 18px;
        margin: 0 20px 0 0;
    }
</style>
<div class="main-container">
    <div id="productCode">343255</div> <br>
    <div id="body">iphone 6S 64</div>  <br>
    <div id="totalAmout">2999.00</div> <br>

    <label class = "axis-label" >
        <span class ="split-pane"></span>
        <input type="radio" name="order" id="wx" value="wx" class="radioStyle imgInput" onclick="showWhichOrder(this.id);" checked = "checked">
        <span class="wxclick"><img src="static/img/WechatPay.jpg"></span>
    </label>
    <label class = "color-picker-label" >
        <span class ="aa"></span>
        <input type="radio" name="order" id="zfb" value="zfb" class="radioStyle imgInput" onclick="zfb_pay()">
        <span class="zfbclick"><img src="static/img/Alipay.jpg"></span>
    </label>
    <label class = "progress-label" >
        <span class ="aa"></span>
        <input type="radio" name="order" id="yl" value="yl" class="radioStyle imgInput" onclick="showWhichOrder(this.id);">
        <span class="ylclick"><img src="static/img/UnionPay.jpg">  </span>
    </label>

    <br>
    <button id="btnSubmitOrder" type="button">提交订单</button>
    <br>
    <br>
    <br>
    <div>支付宝支付</div>
    <div class="date-cell">
        <button id="bottom-text" onclick="zfb_pay()"/>
    </div>

    <div>微信支付</div>
    <div class="date-cell">
        <input type="radio" name="wx">
    </div>

    <div>银联支付</div>
    <div class="date-cell">
        <input type="radio" name="un" >
    </div>
</div>

<div>
    <button onclick="test()">点击</button>
</div>


<script>
    function test() {
        alert(document.getElementById("productCode").innerHTML);
    }
</script>

<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"/>--%>
<script src="http://static.dev.zhixike.net/lib/jquery-1.12.4/jquery.min.js"></script>
<script src="http://static.dev.zhixike.net/lib/bootstrap-3.3.7/js/bootstrap.js"></script>
<script src="http://static.dev.zhixike.net/lib/jquery-validation-1.15.0/jquery.validate.min.js"></script>
<script src="http://static.dev.zhixike.net/lib/jquery-validation-1.15.0/localization/messages_zh.min.js"></script>
<script src="http://static.dev.zhixike.net/lib/pace/pace.min.js"></script>
<script src="http://static.dev.zhixike.net/lib/g/1.1.0/g.min.js"></script>
<script src="/static/js/index.js"></script>
</body>
</html>
