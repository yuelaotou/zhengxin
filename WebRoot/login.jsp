<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%
	HttpSession hsession=request.getSession(false);
	hsession.setAttribute("SecurityInfo",null);
	hsession.setAttribute("systemId",null);
%>
<html:html>
<head>
<title>金软科技住房公积金管理系统V7.0</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="JavaScript">
<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
// -->
var falg=0;
function on_submit(){
 	if(falg==0){
  		executeAjax();
 		falg++;
 	}
}
var CalendarData=new Array(20); 
var madd=new Array(12); 
var TheDate=new Date(); 
var tgString="甲乙丙丁戊己庚辛壬癸"; 
var dzString="子丑寅卯辰巳午未申酉戌亥"; 
var numString="一二三四五六七八九十"; 
var monString="正二三四五六七八九十冬腊"; 
var weekString="日一二三四五六"; 
var sx="鼠牛虎兔龙蛇马羊猴鸡狗猪"; 
var cYear; 
var cMonth; 
var cDay; 
var cHour; 
var cDateString; 
var DateString; 
var Browser=navigator.appName; 
function init() 
{ 
CalendarData[0]=0x41A95; 
CalendarData[1]=0xD4A; 
CalendarData[2]=0xDA5; 
CalendarData[3]=0x20B55; 
CalendarData[4]=0x56A; 
CalendarData[5]=0x7155B; 
CalendarData[6]=0x25D; 
CalendarData[7]=0x92D; 
CalendarData[8]=0x5192B; 
CalendarData[9]=0xA95; 
CalendarData[10]=0xB4A; 
CalendarData[11]=0x416AA; 
CalendarData[12]=0xAD5; 
CalendarData[13]=0x90AB5; 
CalendarData[14]=0x4BA; 
CalendarData[15]=0xA5B; 
CalendarData[16]=0x60A57; 
CalendarData[17]=0x52B; 
CalendarData[18]=0xA93; 
CalendarData[19]=0x40E95; 
madd[0]=0; 
madd[1]=31; 
madd[2]=59; 
madd[3]=90; 
madd[4]=120; 
madd[5]=151; 
madd[6]=181; 
madd[7]=212; 
madd[8]=243; 
madd[9]=273; 
madd[10]=304; 
madd[11]=334; 
} 
function GetBit(m,n) 
{ 
return (m>>n)&1; 
} 
function e2c() 
{ 
var total,m,n,k; 
var isEnd=false; 
var tmp=TheDate.getYear(); 
if (tmp<1900) tmp+=1900; 

total=(tmp-2001)*365 
+Math.floor((tmp-2001)/4) 
+madd[TheDate.getMonth()] 
+TheDate.getDate() 
-23; 
if (TheDate.getYear()%4==0&&TheDate.getMonth()>1) 
total++; 
for(m=0;;m++) 
{ 
k=(CalendarData[m]<0xfff)?11:12; 
for(n=k;n>=0;n--) 
{ 
if(total<=29+GetBit(CalendarData[m],n)) 
{ 
isEnd=true; 
break; 
} 
total=total-29-GetBit(CalendarData[m],n); 
} 
if(isEnd)break; 
} 
cYear=2001 + m; 
cMonth=k-n+1; 
cDay=total; 
if(k==12) 
{ 
if(cMonth==Math.floor(CalendarData[m]/0x10000)+1) 
cMonth=1-cMonth; 
if(cMonth>Math.floor(CalendarData[m]/0x10000)+1) 
cMonth--; 
} 
cHour=Math.floor((TheDate.getHours()+3)/2); 
} 
function GetcDateString() 
{ 
var tmp=""; 
tmp+=tgString.charAt((cYear-4)%10); //年干 
tmp+=dzString.charAt((cYear-4)%12); //年支 
tmp+="年("; 
tmp+=sx.charAt((cYear-4)%12); 
tmp+=")"; 
if(cMonth<1) 
{ 
tmp+="闰"; 
tmp+=monString.charAt(-cMonth-1); 
} 
else 
tmp+=monString.charAt(cMonth-1); 
tmp+="月"; 
tmp+=(cDay<11)?"初":((cDay<20)?"十":((cDay<30)?"廿":"卅")); 
if(cDay%10!=0||cDay==10) 
tmp+=numString.charAt((cDay-1)%10); 
if(cHour==13)tmp+="夜"; 
tmp+=dzString.charAt((cHour-1)%12); 
tmp+="时"; 
cDateString=tmp; 
return tmp; 
} 
function GetDateString() 
{ 
var tmp=""; 
var t1=TheDate.getYear(); 
if (t1<1900)t1+=1900; 
tmp+=t1 
+"-" 
+(TheDate.getMonth()+1)+"-" 
+TheDate.getDate()+" " 
+TheDate.getHours()+":" 
+((TheDate.getMinutes()<10)?"0":"") 
+TheDate.getMinutes() 
+" 星期"+weekString.charAt(TheDate.getDay()); 
DateString=tmp; 
return tmp; 
} 
init(); 
e2c(); 
function time_wsh(){
 document.write("<table border='0' style='font-size: 8pt; font-family:Tahoma' cellspacing='0' width='90' bordercolor='#cccccc' height='110' cellpadding='0'");
document.write("<tr><td align='center' ><b><font color=#3366cc>"+bsDate+"</font><br><span style='font-family: Arial Black;font-size:18pt;color:#FF0000'>"+bsDate2+"</span><br><span style='FONT-SIZE: 10.5pt;color:#ffffff'>");
document.write(bsWeek+"</span><br>"+"<hr width='60' ></b><font color=#ffffff>");
document.write(GetcDateString()+"</td></tr></table>");
}
</script>
<style type="text/css">
<!--
td {  font-size: 12px}
.input1 {  background-color: D5EFFA; border-color: black black #3F9DCB; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 1px; border-left-width: 0px}
-->
</style>
</head>
<script language="javascript" src="./js/login.js">
</script>
<body bgcolor="23D5F5" text="#000000" leftmargin="0" topmargin="0" onLoad=init()>

<table width="1001" border="0" cellspacing="0" cellpadding="0" align="center" height="50">
  <tr>
    <td>
      <table id="__01" width="1001" border="0" cellpadding="0" cellspacing="0" align="center">
        <tr> 
          <td colspan="5"> <img src="img/main_01.jpg" width="1000" height="24" alt=""></td>
          <td width="1" height="24"></td>
        </tr>
        <tr> 
          <td colspan="3"> <img src="img/main_02.jpg" width="794" height="170" alt=""></td>
          <td rowspan="2" background="img/main_03.jpg" width="184" height="238" valign="top"> 
            <table width="79%" border="0" cellspacing="0" cellpadding="0" align="center">
              <tr> 
                <td height="60">&nbsp;</td>
              </tr>
              <tr> 
                <td align="center" height="120"> 
<script>time_wsh()</script>
                </td>
              </tr>
              <tr>
                <td align="center">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr> 
                      <td width="17%">&nbsp; </td>
                      <td width="72%">
                        <div id=liveclock  style="position:absolute; width:110px; height:16px; z-index:1"></div>
                      </td>
                      <td width="11%">&nbsp;</td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
            </td>
          <td rowspan="4"> <img src="img/main_04.jpg" width="22" height="683" alt=""></td>
          <td> <img src="img/分隔符.gif" width="1" height="170" alt=""></td>
        </tr>
        <tr> 
          <td rowspan="3"> <img src="img/main_05.jpg" width="286" height="513" alt=""></td>
          <td rowspan="2" background="img/main_06.jpg" width="430" height="296" align="center"> 
            <table width="60%" border="0" cellspacing="0" cellpadding="0">
             <html:form action="toChangeBizDateAction.do">
									<tr>
										<td height="22" width="31%" align="right">
											用户名：
										</td>
										<td height="22" width="69%">
											<html:text property="userName" maxlength="10"
												styleClass="input1" onkeydown="enterToTab()">
											</html:text>
										</td>
									</tr>
									<tr>
										<td height="27" width="31%" align="right">
											密码：
										</td>
										<td height="27" width="69%">
											<html:password property="userPassword" styleClass="input1" onkeydown="enterToSubmit()">
											</html:password>
										</td>
									</tr>
									<!--  <tr>
										<td height="27" width="31%" align="right">
											验证码：
										</td>
										<td height="27" width="69%">
											<html:text property="validateCode" styleClass="input1" onkeydown="enterToSubmit()"/>
										</td>
									</tr>
									<tr>
										<td height="27" width="31%" align="right"></td>
										<td height="27" width="69%">
											<img src="./generateimage" style="width:166px; height:20px" />
										</td>
									</tr>-->
								</html:form>
								<tr>

									<td colspan="2" height="58" align="center">
										<input type="image" border="0" name="imageField"
											src="img/button_logon.gif" width="69" height="20"
											onclick="return on_submit();">
										<input type="image" border="0" name="imageField2"
											src="img/button_reset.gif" width="69" height="20"
											onclick="init();">
									</td>
								</tr>
            </table>
          </td>
          <td rowspan="3"> <img src="img/main_07.jpg" width="78" height="513" alt=""></td>
          <td> <img src="img/分隔符.gif" width="1" height="68" alt=""></td>
        </tr>
        <tr> 
          <td rowspan="2"> <img src="img/main_08.jpg" width="184" height="445" alt=""></td>
          <td> <img src="img/分隔符.gif" width="1" height="228" alt=""></td>
        </tr>
        <tr> 
          <td> <img src="img/main_09.jpg" width="430" height="217" alt=""></td>
          <td> <img src="img/分隔符.gif" width="1" height="217" alt=""></td>
        </tr>
        <tr> 
          <td colspan="5">&nbsp; </td>
          <td> <img src="images/分隔符.gif" width="1" height="10" alt=""></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html:html>
