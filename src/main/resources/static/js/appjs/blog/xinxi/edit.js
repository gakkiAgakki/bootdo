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
    /*文件上传*/
    layui.use('upload', function () {
        var upload = layui.upload;
//                                        if (checkImgType()){
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
	validateRule();
});
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
    $("#content").val(content_sn);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/blog/xinxi/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
                // parent.location.reload(true);//刷新父级页面
                var index = parent.layer.getFrameIndex(window.name);//获取当前弹窗的Id
                parent.layer.close(index);
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
            mokuai : {
                required : icon + "请选择模块"
            },
            fenlei : {
                required : icon + "请选择分类"
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
