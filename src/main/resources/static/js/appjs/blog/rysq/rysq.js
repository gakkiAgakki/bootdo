
var prefix = "/blog/rysq"
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
                                random : Math.random()
					           // name:$('#searchName').val(),
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
									field : 'cid', 
									title : '' 
								},
																{
									field : 'title', 
									title : '企业名称',
									formatter : function (title) {
										if (title == null){
											return " ";
										}
										if (title.length > 8){
											return '<span title=" ' + title + ' ">' + title.substr(0,8) + "..." + '</span>';
										}else {
											return '<span>' + title + '</span>';
										}
									}
								},
																{
									field : 'frdb', 
									title : '法人代表' 
								},
																{
									field : 'frlxfs', 
									title : '法人联系方式' 
								},
								// 								{
								// 	field : 'xmlxr',
								// 	title : '项目联系人'
								// },
								// 								{
								// 	field : 'xmlxrlxfs',
								// 	title : '项目联系人联系方式'
								// },
								// 								{
								// 	field : 'dzyx',
								// 	title : '电子邮箱'
								// },
								// 								{
								// 	field : 'wz',
								// 	title : '网址'
								// },
																{
									field : 'xmmc', 
									title : '项目名称',
									formatter : function (xmmc) {
										if (xmmc == null){
											return " ";
										}
										if (xmmc.length > 8){
											return '<span title=" ' + xmmc + ' ">' + xmmc.substr(0,8) + "..." + '</span>';
										}else {
											return '<span>' + xmmc + '</span>';
										}
									}
								},
																{
									field : 'xmztz', 
									title : '项目总投资' 
								},
								// 								{
								// 	field : 'zcdz',
								// 	title : '注册地址'
								// },
								// 								{
								// 	field : 'zyjsnr1',
								// 	title : '主要建设内容1'
								// },
								// 								{
								// 	field : 'zyjsnr2',
								// 	title : '主要建设内容2'
								// },
								// 								{
								// 	field : 'zyjsnr3',
								// 	title : '主要建设内容3'
								// },
								// 								{
								// 	field : 'rzfsZl',
								// 	title : '入驻方式_租赁'
								// },
								// 								{
								// 	field : 'rzfsXz',
								// 	title : '入驻方式_新增'
								// },
								// 								{
								// 	field : 'xyYjncz',
								// 	title : '效益_预计年产值'
								// },
								// 								{
								// 	field : 'xyNxssr',
								// 	title : '效益_年销售收入'
								// },
								// 								{
								// 	field : 'xyNlr',
								// 	title : '效益_年利润'
								// },
								// 								{
								// 	field : 'xyNss',
								// 	title : '效益_年税收'
								// },
																{
									field : 'sh', 
									title : '审核状态',
									formatter : function (sh) {
										if (sh != 1){
											return '<span>未审核</span>';
										}else {
											return '<span>已审核</span>';
										}
									}
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.cid
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.cid
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="预览"  mce_href="#" onclick="resetPwd(\''
												+ row.cid
												+ '\')"><i class="fa fa-key"></i></a> ';
										return d + f ;
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
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}

function resetPwd(id) {
	var resetPwdPage = layer.open({
		type : 2,
		title : '预览',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/resetPwd/' + id // iframe的url
	});
    layer.full(resetPwdPage);
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'cid' : id
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
			ids[i] = row['cid'];
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
/*
 * 进行审核
 *
 * */
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
            ids[i] = row['cid'];
        });
        $.ajax({
            type : 'POST',
            data : {
                "ids" : ids
            },
            url : prefix + '/batchShenHe',
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    });
}
/*
 * 取消审核
 *
 * */
function quxiaoshenhe() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要取消审核通过的数据");
        return;
    }
    layer.confirm("确认要取消审核选中的'" + rows.length + "'条数据吗?", {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            ids[i] = row['cid'];
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
    });
}