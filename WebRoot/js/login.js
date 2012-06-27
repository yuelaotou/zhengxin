function enterToTab(){
  if(event.srcElement.type != 'button' && event.keyCode == 13){
    event.keyCode = 9;
  }
}
function enterToSubmit(){
  if(event.keyCode == 13){
   executeAjax();
  }
}
String.prototype.trim = function() 
{
return this.replace(/(^\s*)|(\s*$)/g, "");
}
var xmlHttp;
function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
} 

function findInfo(queryString) {
 createXMLHttpRequest();       
	    xmlHttp.onreadystatechange = handleStateChange;
	    xmlHttp.open("GET", queryString, true);
	    xmlHttp.send(null);    
}

function handleStateChange() {
  if(xmlHttp.readyState == 4) {
      if(xmlHttp.status == 200) {
         var x=xmlHttp.responseText;
         eval(x);
      }
   }
}

function executeAjax() {
       var userName=document.all["userName"].value.trim();
       var userPassword=document.all["userPassword"].value.trim();
       //var validateCode=document.all["validateCode"].value.trim();
       var queryString = "loginAction.do?userName="+userName+"&userPassword="+userPassword;//+"&validateCode="+validateCode; 
	   findInfo(queryString);
}

function toSuccess(){
   document.loginActionForm.submit();
}

function toFail(errors){
//alert("------------------------------------");
   falg=0;
 if(errors=="1"){
    alert("请输入用户名！");
    document.all["userName"].select();
 }else if(errors=="2"){
    alert("用户名有误，请重新输入！");
    document.all["userName"].select();
 }else if(errors=="3"){
    alert("请重新输入密码！");
    document.all["userPassword"].select();
}else if(errors=="4"){
    alert("用户名和密码不匹配，请重新输入！");
    document.all["userPassword"].select();
}else if(errors=="5"){
    alert("请重新输入验证码！");
    document.all["validateCode"].select();
}else if(errors=="6"){
    alert("验证码不匹配，请重新输入！");
    document.all["validateCode"].select();
}
  
}
function init(){
       document.all["userName"].value="";
       document.all["userPassword"].value="";
       //document.all["validateCode"].value="";
       document.all["userName"].focus();
       falg=0;
       show5();
}

function show5()
{
if(!document.layers&&!document.all)
return
var Digital=new Date()
var hours=Digital.getHours()
var minutes=Digital.getMinutes()
var seconds=Digital.getSeconds()
var dn="AM"
if(hours>12){dn="PM"
hours=hours-12
}if(hours==0)
hours=12
if(minutes<=9)
minutes="0"+minutes
if(seconds<=9)
seconds="0"+seconds
//change font size here to your desire
myclock="<span style='font-family: Arial Black;font-size:12pt;color:#FFffff'>"+hours+":"+minutes+":"+seconds+" "+dn+"</span>";
if(document.layers){
document.layers.liveclock.document.write(myclock)
document.layers.liveclock.document.close()
}else if(document.all)
liveclock.innerHTML=myclock
setTimeout("show5()",1000)
}

var bsYear;
var bsDate;
var bsWeek;
var arrLen=8;    //数组长度
var sValue=0;    //当年的秒数
var dayiy=0;    //当年第几天
var miy=0;    //月份的下标
var iyear=0;    //年份标记
var dayim=0;    //当月第几天
var spd=86400;    //每天的秒数

var year1999="30;29;29;30;29;29;30;29;30;30;30;29";    //354
var year2000="30;30;29;29;30;29;29;30;29;30;30;29";    //354
var year2001="30;30;29;30;29;30;29;29;30;29;30;29;30";    //384
var year2002="30;30;29;30;29;30;29;29;30;29;30;29";    //354
var year2003="30;30;29;30;30;29;30;29;29;30;29;30";    //355
var year2004="29;30;29;30;30;29;30;29;30;29;30;29;30";    //384
var year2005="29;30;29;30;29;30;30;29;30;29;30;29";    //354
var year2006="30;29;30;29;30;30;29;29;30;30;29;29;30";

var month1999="正月;二月;三月;四月;五月;六月;七月;八月;九月;十月;十一月;十二月"
var month2001="正月;二月;三月;四月;闰四月;五月;六月;七月;八月;九月;十月;十一月;十二月"
var month2004="正月;二月;闰二月;三月;四月;五月;六月;七月;八月;九月;十月;十一月;十二月"
var month2006="正月;二月;三月;四月;五月;六月;七月;闰七月;八月;九月;十月;十一月;十二月"
var Dn="初一;初二;初三;初四;初五;初六;初七;初八;初九;初十;十一;十二;十三;十四;十五;十六;十七;十八;十九;二十;廿一;廿二;廿三;廿四;廿五;廿六;廿七;廿八;廿九;三十";

var Ys=new Array(arrLen);
Ys[0]=919094400;Ys[1]=949680000;Ys[2]=980265600;
Ys[3]=1013443200;Ys[4]=1044028800;Ys[5]=1074700800;
Ys[6]=1107878400;Ys[7]=1138464000;

var Yn=new Array(arrLen);  //农历年的名称
Yn[0]="己卯年";Yn[1]="庚辰年";Yn[2]="辛巳年";
Yn[3]="壬午年";Yn[4]="癸未年";Yn[5]="甲申年";
Yn[6]="乙酉年";Yn[7]="丙戌年";
var D=new Date();
var yy=D.getYear();
var mm=D.getMonth()+1;
var dd=D.getDate();
var ww=D.getDay();
if (ww==0) ww="<font color=RED>星期日</font>";
if (ww==1) ww="星期一";
if (ww==2) ww="星期二";
if (ww==3) ww="星期三";
if (ww==4) ww="星期四";
if (ww==5) ww="星期五";
if (ww==6) ww="<font color=green>星期六</font>";
ww=ww;
var ss=parseInt(D.getTime() / 1000);
if (yy<100) yy="19"+yy;

for (i=0;i<arrLen;i++)
if (ss>=Ys[i]){
iyear=i;
sValue=ss-Ys[i];  //当年的秒数
}
dayiy=parseInt(sValue/spd)+1;  //当年的天数

var dpm=year1999;
if (iyear==1) dpm=year2000;
if (iyear==2) dpm=year2001;
if (iyear==3) dpm=year2002;
if (iyear==4) dpm=year2003;
if (iyear==5) dpm=year2004;
if (iyear==6) dpm=year2005;
if (iyear==7) dpm=year2006;
dpm=dpm.split(";");

var Mn=month1999;
if (iyear==2) Mn=month2001;
if (iyear==5) Mn=month2004;
if (iyear==7) Mn=month2006;
Mn=Mn.split(";");

var Dn="初一;初二;初三;初四;初五;初六;初七;初八;初九;初十;十一;十二;十三;十四;十五;十六;十七;十八;十九;二十;廿一;廿二;廿三;廿四;廿五;廿六;廿七;廿八;廿九;三十";
Dn=Dn.split(";");

dayim=dayiy;

var total=new Array(13);
total[0]=parseInt(dpm[0]);
for (i=1;i<dpm.length-1;i++) total[i]=parseInt(dpm[i])+total[i-1];
for (i=dpm.length-1;i>0;i--)
if (dayim>total[i-1]){
dayim=dayim-total[i-1];
miy=i;
}
bsWeek=ww;
bsDate=yy+"年"+mm+"月";
bsDate2=dd;
bsYear="农历"+Yn[iyear];
bsYear2=Mn[miy]+Dn[dayim-1];
if (ss>=Ys[7]||ss<Ys[0]) bsYear=Yn[7];


function time(){
document.write("<table border='0' style='font-size: 8pt; font-family:Tahoma' cellspacing='0' width='90' bordercolor='#cccccc' height='110' cellpadding='0'");
document.write("<tr><td align='center' ><b><font color=#3366cc>"+bsDate+"</font><br><span style='font-family: Arial Black;font-size:18pt;color:#FF0000'>"+bsDate2+"</span><br><span style='FONT-SIZE: 10.5pt;color:#ffffff'>");
document.write(bsWeek+"</span><br>"+"<hr width='60' ></b><font color=#ffffff>");
document.write(bsYear+"<br>"+bsYear2+"</td></tr></table>");
}
