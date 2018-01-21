
G.setConfig({
    //请求的数据响应规范
    response: {
        //响应代码
        code: "code",
        //响应数据
        data: "data",
        //错误提示信息
        msg: "msg",
        //响应是否是成功的
        isOK: function (response) {
            return (response instanceof Object) && response['code'] == 0;
        }
    },
    //数据表格的数据响应规范
    table: {
        //分页
        paging_data: "content",
        paging_total: "total"
    }
});



function zfb_pay() {
    var productCode = document.getElementById("productCode").innerHTML;
    var body = document.getElementById("body").innerHTML;
    var totalAmout = document.getElementById("totalAmout").innerHTML;

    var AlipayPayForm = {
        productCode : productCode,
        body : body,
        totalAmout : totalAmout
    }

    $.ajax({
        type : "POST",
        contentType: "application/json; charset=utf-8",
        url : "/alipay/pay",
        data : JSON.stringify(AlipayPayForm),
        success : function (data) {
            alert(data.toString());
        }
    })


}