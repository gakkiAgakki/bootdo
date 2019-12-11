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
    var currentdate=year+'-'+month+'-'+day+'  '+ hour + ':'+minute+':'+second;
    //获取当前时间 yyyy-MM-dd HH:mm:ss
    // var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate + " " +date.getHours() + seperator2 + date.getMinutes() + seperator2 + date.getSeconds();
    $('#huifuTime').val(currentdate);
    $('#zixunTime').val(currentdate);
	/*添加时间end*/

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
		url : "/zixunfuwu/zaixianzixun/save",
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
			}
		},
		messages : {
			title : {
				required : icon + "请输入标题"
			}
		}
	})
}