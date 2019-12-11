$().ready(function() {
	validateRule();
});
$().ready(function () {
		/*类别与部门下拉框*/
        $.ajax({
            method:'POST',
            url: "/qzgs/gsqd/lbOrbm",
            // data: params,
            async: true,
            dataType: "json",
            success: function (data,htmlData) {
                for(var i=0;i<data.length;i++){
                    var leixing = data[i].leixing;
                    var name = data[i].name;
                    if (leixing == '类别'){
                        $('#zqlx').append("<option value="+name+">"+name+"</option>");
                    }
                    if (leixing == '部门'){
                        $("#xszt").append("<option value="+name+">"+name+"</option>");
                    }
                }
            },
            error: function (data) {
                $.gridUnLoading({message: "下拉框数据加载失败"});
            }
        });

    },
	/*文件上传*/
    layui.use('upload', function () {
        var upload = layui.upload;
//                                        if (checkImgType()){
        //图片上传执行实例
        var uploadBsznb = upload.render({
            elem: '#uploadBsznb', //绑定元素
            url: '/qzgs/gsqd/uploadImg', //上传接口
            //            size: 1000,
            size: 1048576,
            accept: 'file',
            done: function (r) {
                var url = r.fileUrl;
                $('#bsznb').val(url);
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
        //文件上传执行实例
        var uploadYxlct = upload.render({
            elem: '#uploadYxlct', //绑定元素
            url: '/qzgs/gsqd/uploadImg', //上传接口
            //            size: 1000,
            size: 1048576,
            accept: 'file',
            done: function (r) {
                var url = r.fileUrl;
                $('#yxlct').val(url);
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
        //文件上传执行实例
        var uploadLzfxfkt = upload.render({
            elem: '#uploadLzfxfkt', //绑定元素
            url: '/qzgs/gsqd/uploadImg', //上传接口
            //            size: 1000,
            size: 1048576,
            accept: 'file',
            done: function (r) {
                var url = r.fileUrl;
                $('#lzfxfkt').val(url);
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
}),
	$('#zqyj').val($('#zqyjtext').val()),
	$('#zrsx').val($('#zrsxtext').val()),
	$('#zrsxyj').val($('#zrsxyjtext').val()),
	$('#beizhu').val($('#beizhutext').val())
    );
$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/qzgs/gsqd/update",
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
            },
            bianma : {
                required : true
            },
            zqlx : {
                required : true
            },
            xszt : {
                required : true
            },
            zqyj : {
                required : true
            },
            zrsx : {
                required : true
            },
            zrsxyj : {
                required : true
            }
        },
        messages : {
            title : {
                required : icon + "请输入事项名称"
            },
            bianma : {
                required : icon + "请输入编码"
            },
            zqlx : {
                required : icon + "请选择职权类型"
            },
            xszt : {
                required : icon + "请选择行使主体"
            },
            zqyj : {
                required : icon + "请输入职权依据"
            },
            zrsx : {
                required : icon + "请输入责任事项"
            },
            zrsxyj : {
                required : icon + "请输入责任事项依据"
            }
        }
	})
}