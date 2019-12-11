$().ready(function() {

	/*转换时间格式*/
    var data = $("#sqlData").val();
    var c=todate(data, "-", false);
    $('#gtmCreate').val(c);

	/*富文本编辑器*/
    var content = $("#content").val();
    UE.getEditor('content_sn');
    UE.getEditor('content_sn').ready(function() {
        this.setHeight(300);
        this.setContent(content);  //赋值给UEditor
    });
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
            //地址栏协议：window.location.protocol
            // 地址栏的主机部分window.location.host;
            return window.location.protocol + "//" + window.location.host + '/imgUpload';
            //'http://localhost:8080/imgUpload';为方法imgUpload的访问地址
        }else if (action == 'uploadvideo'){
            return window.location.protocol + "//" + window.location.host + '/videoUpload';
        } else if (action == 'uploadfile'){
            return window.location.protocol + "//" + window.location.host + '/fileUpload';
        }
        else {
            return this._bkGetActionUrl.call(this, action);
        }
    };

	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
    var ue = UE.getEditor('content_sn');
    var content_sn = ue.getContent();
    if (content_sn == null || content_sn == ""){
        parent.layer.alert("内容不能为空");
        return false;
    }
	$.ajax({
		cache : true,
		type : "POST",
		url : "/xypjtx/xygg/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            title : {
                required : true
                // regex:"^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$"
            }
        },
        messages : {
            title : {
                required : icon + "请输入标题"
                // regex:"密码必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间"
            }
        }
    })
}
function todate(inputstr, showsplit, showweek) {
    //Wed Mar 22 13:38:37 CST 2017
    inputstr = inputstr + ""; //末尾加一个空格
    var date = "";
    var month = new Array();
    var week = new Array();

    month["Jan"] = 1; month["Feb"] = 2; month["Mar"] = 3; month["Apr"] = 4; month["May"] = 5; month["Jan"] = 6;
    month["Jul"] = 7; month["Aug"] = 8; month["Sep"] = 9; month["Oct"] = 10; month["Nov"] = 11; month["Dec"] = 12;
    week["Mon"] = "一"; week["Tue"] = "二"; week["Wed"] = "三"; week["Thu"] = "四"; week["Fri"] = "五"; week["Sat"] = "六"; week["Sun"] = "日";

    str = inputstr.split(" ");

    date = str[5];
    date += showsplit + month[str[1]] + showsplit + str[2] + " " + str[3] ;
    if(showweek){
        date += "    " + " 星期" + week[str[0]];
    }

    return date;
}