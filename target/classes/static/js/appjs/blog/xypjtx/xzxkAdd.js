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
    $('#xksxq').val(currentdate);
    $('#xkjzq').val(currentdate);
    $('#sjbssj').val(currentdate);

	/*文件上传*/
    layui.use('upload', function () {
        var upload = layui.upload;

        //文件上传执行实例
        var uploadFile = upload.render({
            elem: '#uploadFile', //绑定元素
            // url: '/blog/xinxi/uploadFile', //上传接口
            url: '/xypjtx/xzxk/uploadFile', //上传接口
            //            size: 1000,
            size: 1048576,
            accept: 'file',
            done: function (r) {
                var url = r.fileUrl;
                $('#xycns').val(url);

            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
    })


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
		url : "/xypjtx/open/xzxkSave",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
                window.location.href = "/xypjtx/open/qiyexinyongpingjia";
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
            xksxq :{
				required : true,
				isDate : true
			},
            xkjzq :{
				required : true,
				isDate : true
			},
            sjbssj :{
				isDate : true
			}
		},
		messages : {
			ztmc : {
				required : icon + "请输入主体名称"
			},
            xksxq : {
                required : icon + "请输入日期",
                isDate : icon + "请输入正确的日期,如：2008-08-08"
            },
            xkjzq : {
                required : icon + "请输入日期",
                isDate : icon + "请输入正确的日期,如：2008-08-08"
            },
            sjbssj : {
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