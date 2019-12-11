$().ready(function() {
	validateRule();

    var fujian = $("#xycns").val();
    var htmlfujian ="";
    if (fujian != ""){
        htmlfujian += '<a href=" '+fujian+' ">下载</a>';
        console.log(fujian);
        $("#fujian").append(htmlfujian);
        // document.getElementById("fujian").style.display = "block";
    }

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
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/xypjtx/xzxk/update",
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