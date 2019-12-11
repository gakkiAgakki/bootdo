$().ready(function() {
	validateRule();

	/*添加时间*/
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    var currentdate=year+'-'+month+'-'+day;
    //获取当前时间 yyyy-MM-dd HH:mm:ss
    // var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate + " " +date.getHours() + seperator2 + date.getMinutes() + seperator2 + date.getSeconds();
    $('#dbtsrq').val(currentdate);
    $('#xxbsrq').val(currentdate);
    $('#zhxgrq').val(currentdate);

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
		url : "/xypjtx/sxorsx/save",
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
			ztmc : {
				required : true
			},
            ycrq :{
				isDate : true
			},
            dbtsrq :{
                isDate : true
            },
            xxbsrq :{
                isDate : true
            },
            zhxgrq :{
                isDate : true
            }
		},
		messages : {
			ztmc : {
				required : icon + "请输入主题名称"
			},
            ycrq : {
                isDate : icon + "请输入正确的日期,如：2008-08-08"
            },
			dbtsrq : {
                isDate : icon + "请输入正确的日期,如：2008-08-08"
            },
			xxbsrq : {
                isDate : icon + "请输入正确的日期,如：2008-08-08"
            },
			zhxgrq : {
                isDate : icon + "请输入正确的日期,如：2008-08-08"
            }
		}
	})
}
jQuery.validator.addMethod("isDate", function(value, element){
    // var ereg = /^(\d{1,4})(-|\/)(\d{1,2})(-|\/)(\d{1,2})$/;
    var ereg = /^(\d{1,4})(-)(\d{1,2})(-)(\d{1,2})$/;
    var r = value.match(ereg);
    if (r == null) {
        return false;
    }
    var d = new Date(r[1], r[3] - 1, r[5]);
    var result = (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[5]);

    return this.optional(element) || (result);
}, "请输入正确的日期");
function shou() {
	var shixly = $("#shixly");
    console.log($("#shouxly").val().length);
	if ($("#shouxly").val().length > 0){
		shixly.val("");
		shixly.attr("readOnly","true");
    }
    if ($("#shouxly").val().length <= 0){
		console.log($("#shouxly").val().length);
        shixly.attr("readOnly",false);
	}
}
function shi() {
    var shouxly = $("#shouxly");
    console.log($("#shixly").val().length);
    if($("#shixly").val().length > 0) {
        shouxly.val("");
        shouxly.attr("readOnly", "true");
    }
    if ($("#shixly").val().length <= 0){
        shouxly.attr("readOnly",false);
    }
}