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
	$('#huifuTime').val(currentdate);
	/*添加时间end*/
	/*转换时间格式*/
	var data = $("#sqlData").val();
	var c=todate(data, "-", false);
	$('#zixunTime').val(c);
	/*转换时间格式end*/

	validateRule();
},
	$('#zixunzhuti').val($('#zixunzhutitext').val()),
    $('#huifu').val($('#huifutext').val())
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
		url : "/zixunfuwu/zaixianzixun/update",
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
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
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