
var prefix = "/blog/xinxi";
$(function() {
	load();
	/*模块下拉框*/
    $.ajax({
    	/*请求类型为post*/
        method:'POST',
		/*请求路径*/
        url: "/blog/xinxi/mokuai",
		/*查询全部，不需要参数*/
        // data: params,
		/*异步请求*/
        async: true,
		/*传递参数类型为json格式*/
        dataType: "json",
		/*请求成功后执行方法*/
        success: function (data,htmlData) {
        	/*遍历向模块下拉框中添加数据*/
            for(var i=0;i<data.length;i++){
                var value = data[i].name;
                var lable = data[i].id;
                $('#searchMokuai').append("<option id="+ lable +" value="+value+">"+value+"</option>");
            }
        },
		/*请求失败后执行方法*/
        error: function (data) {
            $.gridUnLoading({message: "模块下拉框数据加载失败"});
        }
    });
});
function mokuaiChange () {
    $('#parentId').val($('#searchMokuai option:checked').attr("id"));
    var parentId = $('#parentId').val();
    $('#searchFenlei').empty();
	/*分类下拉框*/
    $.ajax({
    	/*访问类型*/
        method:'POST',
		/*访问路径*/
        url: "/blog/xinxi/fenlei",
		/*参数*/
        data: {
            "parentId" : parentId
        },
		/*异步请求*/
        async: true,
		/*传递的参数类型*/
        dataType: "json",
		/*成功后执行的方法*/
        success: function (data,htmlData) {
        	/*向分类添加一个空参，防止带参数查询*/
            $('#searchFenlei').append("<option value=''>--按模块查询--</option>");
            /*遍历向分类下拉框添加数据*/
            for(var i=0;i<data.length;i++){
                var value = data[i].name;
                var lable = data[i].id;
                $('#searchFenlei').append("<option id="+ lable +" value="+value+">"+value+"</option>");
            }
            /*调用方法重新带参查询集合*/
            $('#exampleTable').bootstrapTable('refresh');
        },
		/*加载模块失败*/
        error: function (data) {
            $.gridUnLoading({message: "分类下拉框数据加载失败"});
        }
    });
}

function fenleiChange() {
	/*分类框改变后直接带参查询*/
	$('#exampleTable').bootstrapTable('refresh');
}

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
								/*查询条数*/
								limit: params.limit,
								/*从第几条开始*/
								offset:params.offset,
								/*查询信息标题名*/
					           title:$('#searchName').val(),
								/*按照模块查询*/
								mokuai:$('#searchMokuai').val(),
								/*按照分类查询*/
								fenlei:$('#searchFenlei').val(),
								/*添加随机数，防止IE对get请求缓存数据*/
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
									field : 'cid',
									align : 'center',
									title : 'ID'
								},
																{
									field : 'title', 
									title : '标题' ,
									width : '20%',
									align : 'center',
									formatter : function (title) {
										if (title.length > 10){
											return '<span title=" ' + title + ' ">' + title.substr(0,10) + "..." + '</span>';
										}else {
											return '<span>' + title + '</span>';
										}
									}
								},
								// 								{
								// 	field : 'subtitle',
								// 	title : '副标题'
								// },
								// 								{
								// 	field : 'created',
								// 	title : '创建人id'
								// },
								// 								{
								// 	field : 'modified',
								// 	title : '最近修改人id'
								// },
								// 								{
								// 	field : 'content',
								// 	title : '内容'
								// },
																{
									field : 'mokuai',
									align : 'center',
									title : '所属模块'
								},
																{
									field : 'fenlei',
									align : 'center',
									title : '分类' 
								},

																{
									field : 'author',
									align : 'center',
									title : '作者',
									formatter : function (author) {
										if (author == null){
											return " ";
										}
										if (author.length > 8){
											return '<span title=" ' + author + ' ">' + author.substr(0,8) + "..." + '</span>';
										}else {
											return '<span>' + author + '</span>';
										}
									}
								},
																{
									field : 'gtmCreate',
									align : 'center',
									title : '创建时间' ,
									width : '10%'
								},
								// 								{
								// 	field : 'gtmModified',
								// 	title : '修改时间'
								// },
								// 								{
								// 	field : 'shouyetupian',
								// 	title : '首页图片'
								// },
																{
									field : 'status',
									align : 'center',
									title : '审核状态',
                                        formatter : function (status) {
											if (status != 1){
												return '<span>未审核</span>';
											}else {
												return '<span>已审核</span>';
											}
                                        }
								},
																{
									field : 'zhiding',
									align : 'center',
									title : '是否置顶',
									formatter : function (zhiding) {
										if (zhiding != 1){
											return '<span>未置顶</span>';
										}else {
											return '<span>已置顶</span>';
										}
									}
								},
								// 								{
								// 	field : 'fujian',
								// 	title : '附件url'
								// },
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
                                        var f = '<a class="btn btn-success btn-sm" href="#" title="预览"  mce_href="#" onclick="preview(\''
                                            + row.cid
                                            + '\')"><i class="fa fa-rocket"></i></a> ';
										return e + d + f;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	var addPage = layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
    layer.full(addPage);
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

function preview(id) {
	window.location.href = "/xContent/open/post/" + id;
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
/*
* 进行置顶
*
* */
function zhiding() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要置顶的数据");
		return;
	}
	layer.confirm("确认要置顶选中的'" + rows.length + "'条数据吗?", {
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
			url : prefix + '/batchZhiding',
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
}/*
* 取消置顶
*
* */
function quxiaozhiding() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要取消置顶的数据");
		return;
	}
	layer.confirm("确认要取消置顶选中的'" + rows.length + "'条数据吗?", {
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
			url : prefix + '/batchQuxiaoZhiding',
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