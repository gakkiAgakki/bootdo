$().ready(function() {
	validateRule();
});
$().ready(function() {
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
	/*模块下拉框*/
    $.ajax({
        method:'POST',
        url: "/blog/xinxi/mokuai",
        // data: params,
        async: true,
        dataType: "json",
        success: function (data,htmlData) {
            for(var i=0;i<data.length;i++){
                var value = data[i].name;
                var lable = data[i].id;
                $('#mokuai').append("<option id="+ lable +" value="+value+">"+value+"</option>");
            }
        },
        error: function (data) {
            $.gridUnLoading({message: "模块下拉框数据加载失败"});
        }
    });

},
/*文件上传*/
layui.use('upload', function () {
    var upload = layui.upload;
//     if (checkImgType()){
    //图片上传执行实例
    var uploadInst = upload.render({
        elem: '#uploadImg', //绑定元素
        url: '/blog/xinxi/uploadImg', //上传接口
        //            size: 1000,
        size: 1048576,
        accept: 'file',
        done: function (r) {
            var url = r.fileUrl;
            $('#shouyetupian').val(url);
        },
        error: function (r) {
            layer.msg(r.msg);
        }
    });
    //文件上传执行实例
    var uploadFile = upload.render({
        elem: '#uploadFile', //绑定元素
        url: '/blog/xinxi/uploadFile', //上传接口
        //            size: 1000,
        size: 1048576,
        accept: 'file',
        done: function (r) {
            var url = r.fileUrl;
            $('#fujian').val(url);

        },
        error: function (r) {
            layer.msg(r.msg);
        }
    });
})
);
function mokuaiChange () {
    $('#parentId').val($('#mokuai option:checked').attr("id"));
	var parentId = $('#parentId').val();
	$('#fenlei').empty();
	/*分类下拉框*/
    $.ajax({
        method:'POST',
        url: "/blog/xinxi/fenlei",
        data: {
            "parentId" : parentId
        },
        async: true,
        dataType: "json",
        success: function (data,htmlData) {
            for(var i=0;i<data.length;i++){
                var value = data[i].name;
                var lable = data[i].id;
                $('#fenlei').append("<option id="+ lable +" value="+value+">"+value+"</option>");
            }
        },
        error: function (data) {
            $.gridUnLoading({message: "分类下拉框数据加载失败"});
        }
    });
}
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
		url : "/blog/xinxi/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
                parent.location.reload(true);//刷新父级页面
                // window.location.href = "/blog/xinxi/list?limit=10&offset=0";
                window.location.href = "/blog/xinxi/xinxi";
                var index = parent.layer.getFrameIndex(window.name);//获取当前弹窗的Id
                // parent.layer.close(index);
				// parent.reLoad();
				// var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				// parent.layer.close(index);

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
			},
            mokuai : {
                required : true
            },
            fenlei : {
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
            },
            mokuai : {
			    required : icon + "请选择模块"
            },
            fenlei : {
                required : icon + "请选择分类"
            }
		}
	})
}
/*
 * 判断图片类型
 */
// function checkImgType(){
//     var ths = $('#shouyetupian').get(0).files[0];
//     if (!ths) {
//         return false;
//     } else {
//         if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test($('#shouyetupian').val())) {
//             alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
//             $('#shouyetupian').val("");
//             return false;
//         }
//     }
//     return true;
// }
