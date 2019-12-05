// var prefix = "/blog/xinxi";
var limit = 5;
/**
 * Created by HASEE on 2019/10/21.
 */
// bindList(0);

$(function(){
    findYuanqudongtai("yuanqugaikuang","新闻信息",1,0);
    findNews("djyw","党建要闻",6,0);
    findByFenlei("xinwentab","新闻动态",6,0);
    findByFenlei("yuanqutab","园区动态",6,0);
    findByPicMk("yqdt","新闻信息",5,0);
    findByPic("yxqy","优秀企业",10,0);
    findByFenlei("tongzhitab","通知公告",6,0);
    //findByFenlei("zhaotoubiaotab","招投标公告",6,0);
    findNews("zhaotoubiaotab","招标投标",6,0);
    tongzhi();
    findByFenlei("yuanquzfwj","园区政府文件",6,0);

    findFenlei("zfxxgongkai","政府信息公开");
    findFenlei("qyfwanquanjianguan","企业服务安全监管");
    findFenlei("zsfuwu","招商服务");
    findFenlei("dqgongzuo","党群工作");
    findFenlei("ztbiao","招标投标");
    findFenlei("cyjidi","产业基地");
    findFenlei("zcfagui","政策法规");
    slider3ready();

});

function findYuanqudongtai(mid,mokuai,limit,offset) {
    $.ajax({
        // url : '/xContent/open/list?mokuai='+mokuai+'&limit='+limit+'&offset=' + offset,
        url : '/xContent/open/list',
        method : 'post',
        data : {
            "mokuai" : mokuai,
            "limit" : limit,
            "offset" : offset
        },
        dataType : 'json',
        success : function(data) {
            var rows = data.rows;
            total = data.total;
            var htmlText = "";
            var htmlText1 = "";
            for (i = 0; i < rows.length; i++) {
                var time = rows[i].gtmCreate;
                time = time.split(" ")[0];
                var title = rows[i].title;

                if(i == 0){
                    var content = rows[i].content;
                    $('.summernote').summernote({
                        lang: 'zh-CN'
                    });
                    content = /*[[${xContent.content}]]*/

                        $('#contentText').code(content);
                    $('.summernote').destroy();

                    var str = $('#contentText').text();
                    str = str.substring(0,42);

                    htmlText1 += '<li>';
                    htmlText1 += '<a href="/xContent/open/post/' + rows[i].cid + '" title="'+title+'" style="float: left;width: 100%;font-size: 21px;color: #0a6aa1;padding-bottom: 15px;">';
                    htmlText1 += title.substring(0,30);
                    htmlText1 += '</a>';
                    htmlText1 += '<li><span style="font-size: 14px;">'+str;
                    htmlText1 += '......</span></li>';
                    htmlText1 += '</li>';
                    $("#yuanqugaikuang1").append(htmlText1);
                }else {
                    htmlText += '<li>';
                    htmlText +=  time;
                    htmlText += '<a href="/xContent/open/post/' + rows[i].cid + '" title="'+title+'" style="float: left">';
                    htmlText += '>'+title.substring(0,15);
                    htmlText += '</a>';
                    htmlText += '</li>';
                }
            }
            $("#"+mid+"").append(htmlText);
        }
    });
}
function findNews(mid,mokuai,limit,offset) {
    $.ajax({
            // url : '/xContent/open/list?mokuai='+mokuai+'&limit='+limit+'&offset=' + offset,
            url : '/xContent/open/list',
            method : 'post',
            data : {
                "mokuai" : mokuai,
                "limit" : limit,
                "offset" : offset
            },
            dataType : 'json',
            success : function(data) {
                var rows = data.rows;
                total = data.total;
                var htmlText = "";
                for (i = 0; i < rows.length; i++) {
                    var time = rows[i].gtmCreate;
                    time = time.split(" ")[0];
                    var title = rows[i].title;
                    htmlText += '<li>';
                    htmlText += '<span style="line-height: 25px;">'+time+'</span>';
                    htmlText += '<a href="/xContent/open/post/' + rows[i].cid + '" title="'+title+'" style="float: left">';
                    htmlText += '>'+title.substring(0,14);
                    htmlText += '</a>';
                    htmlText += '</li>';
                }
                $("#"+mid+"").append(htmlText);
            }
    });
}
function findByFenlei(fid,fenlei,limit,offset) {
    $.ajax({
            // url : '/xContent/open/list?fenlei='+fenlei+'&limit='+limit+'&offset=' + offset,
            url : '/xContent/open/list',
            method : 'post',
            dataType : 'json',
            data :{
                "fenlei" : fenlei,
                "limit" : limit,
                "offset" : offset
            },
            success : function(data) {
                var rows = data.rows;

                total = data.total;

                var htmlText = "";
                for (i = 0; i < rows.length; i++) {
                    var time = rows[i].gtmCreate;
                    time = time.split(" ")[0];
                    htmlText += '<li>';
                    htmlText += '<span style="line-height: 25px;">'+time+'</span>';
                    htmlText += '<a href="/xContent/open/post/' + rows[i].cid + '" title="'+rows[i].title+'" style="float: left">';
                    htmlText += '>'+ rows[i].title.substring(0,14);
                    htmlText += '</a>';
                    htmlText += '</li>';
                }
                $("#"+fid+"").append(htmlText);
            }
    });
}
function findByPicMk(pid,fenlei,limit,offset) {
    $.ajax({
            //url : '/xContent/open/findPic?mokuai='+fenlei+'&limit='+limit+'&offset=' + offset,
            url : '/xContent/open/findPic',
            method : 'get',
            data : {
                "mokuai" : fenlei,
                "limit" : limit,
                "offset" : offset
            },
            dataType : 'json',
            success : function(data) {
                var rows = data.rows;
                total = data.total;
                var htmlText1 = "";
                for (i = 0; i < rows.length; i++) {
                    htmlText1 += '<div class="slide">';
                    htmlText1 += '<a href="/xContent/open/post/' + rows[i].cid + '" title="'+rows[i].title+'">';
                    htmlText1 += '<img src="'+ rows[i].shouyetupian +'" style="width: 100%;height: 100%;" />';
                    htmlText1 += '</a>';
                    htmlText1 += '</div>';
                }
                $("#"+pid+"").append(htmlText1);
                if (rows == "" || rows == null){
                    return;
                }
                if (pid == "yqdt") {
                    slider2ready();
                }else {
                    slider9ready();
                }
            }
    });
}
function findByPic(pid,fenlei,limit,offset) {
    $.ajax({
            // url : '/xContent/open/findPic?fenlei='+fenlei+'&limit='+limit+'&offset=' + offset,
            url : '/xContent/open/findPic',
            method : 'get',
            data : {
                "fenlei" : fenlei,
                "limit" : limit,
                "offset" : offset
            },
            dataType : 'json',
            success : function(data) {
                var rows = data.rows;
                total = data.total;
                var htmlText1 = "";
                for (i = 0; i < rows.length; i++) {
                    htmlText1 += '<div class="slide">';
                    htmlText1 += '<a href="/xContent/open/post/' + rows[i].cid + '" title="'+rows[i].title+'">';
                    htmlText1 += '<img src="'+ rows[i].shouyetupian +'" style="width: 100%;height: 100%;" />';
                    htmlText1 += '</a>';
                    htmlText1 += '</div>';
                }
                $("#"+pid+"").append(htmlText1);
                if (rows == "" || rows == null){
                    return;
                }
                if (pid == "yqdt") {
                    slider2ready();
                }else {
                    slider9ready();
                }
            }
    });
}
function findFenlei(mid,mokuai) {
    $.ajax({
        method:'POST',
        url: "/xContent/open/fenleiList",
        data: {
            "mokuai" : mokuai
        },
        async: true,
        dataType: "json",
        success: function (data,htmlData) {

            var htmls="";
            htmls += "<div>";
            for(var i=0;i<data.length;i++){
                var name = data[i].name;
                var id = data[i].id;
                if (i%3 == 2){

                    if (name == "入园申请"){
                        htmls += "<div><a style='color: white' href='/xContent/open/toRysq'>"+ name +"</a></div></div><div>";
                    }else {

                        htmls += "<div><a style='color: white' href='/xContent/open/listByMk?fenlei="+name+"'>"+ name +"</a></div></div><div>";
                    }
                }else {
                    if (name == "入园申请"){
                        htmls += "<div><a style='color: white' href='/xContent/open/toRysq'>"+ name +"</a></div>";
                    }else {
                        htmls += "<div><a style='color: white' href='/xContent/open/listByMk?fenlei="+name+"'>"+ name +"</a></div>";
                    }
                }
            }
            htmls += "</div>";
            $('#' +mid+ ' ').append(htmls);
        },
        error: function (data) {
            $.gridUnLoading({message: "分类数据加载失败"});
        }
    });
}
function findList(mokuai) {
    window.location.href = "/xContent/open/listByMk?mokuai="+mokuai;
}
function slider2ready() {
    $('.slider2').bxSlider({
        slideWidth: 350,
        auto: true,
        autoControls: true,
        minSlides: 1,
        maxSlides: 1,
        slideMargin: 10,
        controls: false
    });
}
function slider3ready() {
    $('.slider3').bxSlider({
        slideWidth: 200,
        minSlides: 5,
        maxSlides: 5,
        moveSlides: 1,
        slideMargin: 10,
        hideControlOnEnd: true,
        // infiniteLoop: false,
        pager: false
    });
}
function slider9ready() {
    $('.slider9').bxSlider({
        slideWidth: 300,
        minSlides: 5,
        maxSlides: 5,
        tickerHover:true,
        // useCSS:false,
        ticker: true,
        speed: 36000,
        startSlides: 0,
        slideMargin: 10
    });
}
function tolistByFL(mokuai) {
    window.location.href = "/xContent/open/listByMk?fenlei="+mokuai;
}
function toZaixianzixun() {
    window.location.href = "/xContent/open/toZaixianzixun";
}
function toRuyuanshenqing() {
    window.location.href = "/xContent/open/toRysq";
}