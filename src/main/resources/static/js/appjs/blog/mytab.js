function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

// 触发 id="defaultOpen" click 事件
// document.getElementById("defaultOpen").click();

document.getElementById("defaultOpen").className = 'tablinks active';
document.getElementById("zhengfuxinxi").style.display='block';

function tongzhi(){
    document.getElementById("zhaotoubiao").className = 'before';
    document.getElementById("zhaotoubiao").style.color="#146C9C";
    document.getElementById("tongzhi").style.color="white";
    document.getElementById("tongzhi").className = 'after';
    document.getElementById("tongzhitab").style.display = 'block';
    document.getElementById("zhaotoubiaotab").style.display = 'none';
    document.getElementById("ztbOrTz").href = "/xContent/open/listByMk?mokuai=通知公告";

}
function zhaotoubiao(){
    document.getElementById("tongzhi").className = 'before';
    document.getElementById("tongzhi").style.color="#146C9C";
    document.getElementById("zhaotoubiao").style.color="white";
    document.getElementById("zhaotoubiao").className = 'after';
    document.getElementById("zhaotoubiaotab").style.display = 'block';
    document.getElementById("tongzhitab").style.display = 'none';
    document.getElementById("ztbOrTz").href = "/xContent/open/listByMk?mokuai=招标投标";
}

function xinwen(){
    document.getElementById("yuanqu").className = 'before';
    document.getElementById("yuanqu").style.color="#146C9C";
    document.getElementById("xinwen").style.color="white";
    document.getElementById("xinwen").className = 'after';
    document.getElementById("xinwentab").style.display = 'block';
    document.getElementById("yuanqutab").style.display = 'none';
}
function yuanqu(){
    document.getElementById("xinwen").className = 'before';
    document.getElementById("xinwen").style.color="#146C9C";
    document.getElementById("yuanqu").style.color="white";
    document.getElementById("yuanqu").className = 'after';
    document.getElementById("yuanqutab").style.display = 'block';
    document.getElementById("xinwentab").style.display = 'none';
}
