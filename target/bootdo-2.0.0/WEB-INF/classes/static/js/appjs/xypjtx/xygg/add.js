$().ready(function() {
	validateRule();
});

$().ready(function () {
	/*添加时间*/
    var date = new Date();

    var year = date.getFullYear();

    var month = date.getMonth()+1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    var currentdate=year+'-'+month+'-'+day+'  '+ hour + ':'+minute+':'+second;
    //获取当前时间 yyyy-MM-dd HH:mm:ss
    // var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate + " " +date.getHours() + seperator2 + date.getMinutes() + seperator2 + date.getSeconds();
    $('#gtmCreate').val(currentdate);

	/*富文本编辑器*/
    UE.getEditor('content_sn');
    UE.getEditor('content_sn').ready(function() {
        this.setHeight(300);
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
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
    var ue = UE.getEditor('content_sn');
    var content_sn = ue.getContent();
    if (content_sn == null || content_sn == ""){
        parent.layer.alert("内容不能为空");
        return false;
    }
    $("#content").val(content_sn);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/xypjtx/xygg/save",
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
            },
            gtmCreate : {
                required : true
            }
        },
        messages : {
            title : {
                required : icon + "请输入标题"
                // regex:"密码必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间"
            },
            gtmCreate : {
                required : icon + "请输入时间"
            }
        }
    })
}