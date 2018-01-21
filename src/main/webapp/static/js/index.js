
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


//新增-----------
function showWhichOrder(type){
    var productCode = document.getElementById("productCode").innerHTML;
    var body = document.getElementById("body").innerHTML;
    var totalAmout = document.getElementById("totalAmout").innerHTML;
    switch(type) {
        case wx :
            var wxForm = {
                productCode : productCode,
                body : body,
                totalAmout : totalAmout
            };
            wx_pay(wxForm);
            break;
        case zfb:

            var AlipayPayForm = {
                productCode : productCode,
                body : body,
                totalAmout : totalAmout
            }
            zfb_pay(AlipayPayForm);
            break;

        case union:
            alert("yl代做！！！");
            break;
    }
}

function wx_pay(tpye) {
    $.ajax({
        type : "POST",
        contentType: "application/json; charset=utf-8",
        url : "/wechat/pay",
        data : JSON.stringify(tpye),
        success : function (data) {
            alert(data.toString());
        }
    })
}
function zfb_pay(tpye) {
    $.ajax({
        type : "POST",
        contentType: "application/json; charset=utf-8",
        url : "/alipay/pay",
        data : JSON.stringify(tpye),
        success : function (data) {
            alert(data.toString());
        }
    })
}

function union_pay(tpye) {
    $.ajax({
        type : "POST",
        contentType: "application/json; charset=utf-8",
        url : "/union/pay",
        data : JSON.stringify(tpye),
        success : function (data) {
            alert(data.toString());
        }
    })
}