$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
var prefix = "/common/fenlei"
$(function() {
    load();
});
function load() {
    $.ajax({
        method:'get',
        url: prefix + "/mokuai",
        // data: params,
        async: true,
        dataType: "json",
        success: function (data,htmlData) {
            for(var i=0;i<data.length;i++){
                var value = data[i].name;
                var lable = data[i].id;
                $('#mokuainame').append("<option id="+ lable +" value="+value+">"+value+"</option>");

            }
        },
        error: function (data) {
            $.gridUnLoading({message: "下拉框数据加载失败"});
        }
    });
}
function mokuaiChange () {
    $('#parentId').val($('#mokuainame option:checked').attr("id"));
}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/common/fenlei/update",
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
            name : {
                required : true
            },
            value : {
                required : true
            },
            mokuainame : {
                required : true
            }
        },
        messages : {
            name : {
                required : icon + "请输入分类名"
            },
            value : {
                required : icon + "请输入数据值"
            },
            mokuainame : {
                required : icon + "请选择所属模块"
            }
        }
	})
}