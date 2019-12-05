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
		url : "/xContent/open/rysqSave",
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
            frdb : {
				required : true
			},
            frlxfs : {
                isPhone : true
			},
            xmlxr : {
				required : true
			},
            xmlxrlxfs : {
				required : true,
				isPhone : true
			},
            dzyx : {
				required : true,
                isZipCode : true,
			},
            zcdz : {
				required : true
			},
            zyjsnr1 : {
				required : true
			}
		},
		messages : {
			title : {
				required : icon + "请填写企业名称"
			},
            frdb : {
				required : icon + "请填写法人代表"
			},
            frlxfs : {
                isPhone : icon + "请填写正确的联系方式"
			},
            xmlxr : {
				required : icon + "请填写项目联系人"
			},
            xmlxrlxfs : {
				required : icon + "请填写项目联系人的联系方式",
				isPhone : icon + "请填写正确的联系方式"
			},
            dzyx : {
				required : icon + "请填写电子邮箱",
                isZipCode : icon + "请填写正确的电子邮箱"
			},
            zcdz : {
				required : icon + "请填写注册地址"
			},
            zyjsnr1 : {
				required : icon + "请填写主要建设(经营)内容"
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