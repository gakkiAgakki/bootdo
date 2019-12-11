$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/xContent/open/zaixianzixunSave",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				window.location.href = "/xContent";
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
			},
            xingming : {
				required : true
			},
            danweiname : {
				required : true
			},
            dianhua : {
				required : true,
				isPhone : true
			},
            youxiang : {
				required : true,
                isZipCode : true
			},
            lianxidizhi : {
				required : true
			},
            zixunzhuti : {
				required : true
			}
		},
		messages : {
			title : {
				required : icon + "请填写咨询标题"
			},
            xingming : {
				required : icon + "请填写咨询人姓名"
			},
            danweiname : {
				required : icon + "请填写单位名称"
			},
            dianhua : {
				required : icon + "请填写手机号码",
				isPhone : icon + "请正确填写手机号码"
			},
            youxiang : {
				required : icon + "请填写邮箱",
                isZipCode : icon + "请填写正确的电子邮箱"
			},
            lianxidizhi : {
				required : icon + "请填写联系地址"
			},
            zixunzhuti : {
				required : icon + "请填写咨询主体内容"
			}
		}
	})
}

//手机号码验证
jQuery.validator.addMethod("isPhone", function(value, element) {
    var length = value.length;
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写手机号码");
jQuery.validator.addMethod("isZipCode", function(value, element) {
    var tel =  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的邮箱编码");