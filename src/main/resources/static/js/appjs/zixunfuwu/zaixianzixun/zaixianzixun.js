
var prefix = "/zixunfuwu/zaixianzixun"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
					           title:$('#searchName').val(),
                                random : Math.random()
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
																{
									field : 'id', 
									title : '编号' 
								},
																{
									field : 'title', 
									title : '标题',
									formatter : function (title) {
                                        if (title.length == "" || title == null){
                                            return " ";
                                        }
										if (title.length > 10){
											return '<span title=" ' + title + ' ">' + title.substr(0,10) + "..." + '</span>';
										}else {
											return '<span>' + title + '</span>';
										}
									}
								},
																{
									field : 'xingming', 
									title : '姓名' 
								},
																{
									field : 'danweiname', 
									title : '单位名称',
									formatter : function (danweiname) {
										if (danweiname.length == "" || danweiname == null){
											return " ";
										}
										if (danweiname.length > 10){
											return '<span title=" ' + danweiname + ' ">' + danweiname.substr(0,10) + "..." + '</span>';
										}else {
											return '<span>' + danweiname + '</span>';
										}
									}
								},
																{
									field : 'dianhua', 
									title : '联系电话' 
								},
																{
									field : 'youxiang', 
									title : '邮箱' 
								},
																{
									field : 'lianxidizhi', 
									title : '联系地址' ,
									formatter : function (lianxidizhi) {
                                        if (lianxidizhi.length == "" || lianxidizhi == null){
                                            return " ";
                                        }
										if (lianxidizhi.length > 10){
											return '<span title=" ' + lianxidizhi + ' ">' + lianxidizhi.substr(0,10) + "..." + '</span>';
										}else {
											return '<span>' + lianxidizhi + '</span>';
										}
									}

								},
																{
									field : 'zixunzhuti', 
									title : '内容',
									formatter : function (zixunzhuti) {
                                        if (zixunzhuti.length == "" || zixunzhuti == null){
                                            return " ";
                                        }
										if (zixunzhuti.length > 10){
											return '<span title=" ' + zixunzhuti + ' ">' + zixunzhuti.substr(0,10) + "..." + '</span>';
										}else {
											return '<span>' + zixunzhuti + '</span>';
										}
									}
								},
																{
									field : 'huifu', 
									title : '回复' ,
									formatter : function (huifu) {
                                        if (huifu == null){
                                            return " ";
                                        }
										if (huifu.length > 10){
											return '<span title=" ' + huifu + ' ">' + huifu.substr(0,10) + "..." + '</span>';
										}else {
											return '<span>' + huifu + '</span>';
										}
									}

								},
																{
									field : 'shenhe', 
									title : '审核' ,
									formatter : function (shenhe) {
										if (shenhe == 1){
											return '<span>已审核</span>';
										}else {
											return '<span>未审核</span>';
										}
                                    }
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	var editPage = layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
    layer.full(editPage);
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}
function shenhe() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要审核通过的数据");
		return;
	}
	layer.confirm("确认要审核通过选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchShenhe',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}
function quxiaoshenhe() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要取消审核的数据");
		return;
	}
	layer.confirm("确认要取消审核选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchQuxiaoshenhe',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}