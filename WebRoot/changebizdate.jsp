<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%
String path = request.getContextPath();
%>
<html:html>

<head>
	<title>修改业务日期</title>
	<link href="<%=request.getContextPath()%>/css/index.css"
		type="text/css" rel="stylesheet" />
	<script src="<%=request.getContextPath()%>/js/common.js">
  
	
	<SCRIPT language="JAVASCRIPT"
		src="<%=request.getContextPath()%>/syscommon/calendar/oa_Popup.js"></SCRIPT>
	<script language="JAVASCRIPT">
var date="";
var date2="";
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
function init(){
   	document.all["bizdate"].focus();
   	date2= document.all("bizdate").value;
   	var isChooseBook=document.loginActionForm.elements["isChooseBook"].value;
  	if(isChooseBook==1){
      	zt.style.display="";
      	rq.style.display="none";
  	}else{
     	zt.style.display="none";
     	rq.style.display="";
  	}
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
   var systemId=document.loginActionForm.elements["systemId"].value;  
   var userName=document.loginActionForm.elements["userName"].value;  
   var queryString = "toChangeBizDateAAC.do?userName="+userName+"&systemId="+systemId; 
   findInfo(queryString);
}

function executeAjax1() {
	var userName=document.loginActionForm.elements["userName"].value; 
	var bookId=document.loginActionForm.elements["bookId"].value;        
    var queryString = "toChangeBookAAC.do?bookId="+bookId+"&userName="+userName; 
   	findInfo(queryString);
}
function displays(bizdate,opSystemType,isChooseBook){  
  	document.loginActionForm.elements["bizdate"].value=bizdate;
  	date=bizdate;
  	document.loginActionForm.elements["opSystemType"].value=opSystemType;
  	if(isChooseBook==1){
      	zt.style.display="";
      	rq.style.display="none";
  	}else{
     	zt.style.display="none";
     	rq.style.display="";
  	}
}

function displays1(bookName,bizDate){
  	document.loginActionForm.elements["bookName"].value=bookName;
}
function test(){
   	var theForm = document.loginActionForm.elements["systemId"];
   	for(var i=0;i<theForm.options.length;i++){
   		if(theForm.options[i].selected == true){
   			var s = theForm.options[i].value;
   			return s;
   		}
   	}
}

function checkDate()
{
    var state=test();
    if(state=="4028814811eaa6df0111eaacb5a60001"){
    	return true;
    }
  	var strDate = document.all("bizdate").value;
  	if(strDate==""||strDate==null){
  		alert("业务日期不能为空!");
  		return false;
  	}
  
  	if(strDate.length!=8)
 	{
    	alert("请输入八位的日期格式，例如20070101！");
    	document.all("bizdate").focus();
    	return false;
  	}
  	if(strDate.substring(0,4)<1900){
    	alert("年份应该大于1900！");
    	document.all("bizdate").focus();
    	return false;
  	}
  	if(strDate.substring(4,6)>12 || strDate.substring(4,6)<1)
  	{
    	alert("月份应该在1-12月之间！");
    	document.all("bizdate").focus();
    	return false;
  	}
  	var tempStrDate=strDate.substring(6,8);
  	var tempStrMonth=strDate.substring(4,6);
 	if(tempStrMonth==2&&tempStrDate>29)
  	{
    	alert("日期不能大于29！");
    	document.all("bizdate").focus();
    	return false;
  	}else if((tempStrMonth==1||tempStrMonth==3||tempStrMonth==5||tempStrMonth==7||tempStrMonth==8||tempStrMonth==10||tempStrMonth==12)&&tempStrDate>31){
    	alert("日期不能大于31！");
    	document.all("bizdate").focus();
    	return false;
  	}else if((tempStrMonth==2||tempStrMonth==4||tempStrMonth==6||tempStrMonth==9||tempStrMonth==11)&&tempStrDate>30){
    	alert("日期不能大于30！");
    	document.all("bizdate").focus();
    	return false;
  	}
   	if(date==""||date==null){
   		date=date2;
   	}
  	if(strDate<date){
 	}
  	return true;
}
function checkDatas(date){
	var strDate = date.value;
	var checkDate=IsValidDate(strDate);
	if(!checkDate){
		alert("日期输入有误！");
		return false;
	}
}
   

function checkData(date){
  	if(!checkDate(date)){
    	return false;
    	document.all("bizdate").focus();
  	}else{
    	return true;
  	}
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
	    xmlHttp.open("GET", queryString, false);
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
//关闭系统的是否存在应扎账的业务验证
function checkclearaccount(path){
    var queryString =path+"/syscommon/checkclearaccount/checkclearaccountAAC.do?";
    findInfo(queryString);
}
function isclosesys(flag){
   	if(flag==2){
	   	var x=confirm("尚未进入系统中，是否继续执行本次操作！！！");
	   	if(x){
	    	parent.window.location="<%=request.getContextPath()%>/closesys.jsp";
	   	}
   	}else if(flag==0){
	   	var x=confirm("您浏览的页面即将关闭，是否继续执行本次操作！！！");
	   	if(x){
	    	parent.window.location="<%=request.getContextPath()%>/closesys.jsp";
	   	}
   	}else{
   		
	   	var x=confirm("当前业务日期中存在未入账的业务，是否继续！！！");
	   	if(x){
	    	parent.window.location="<%=request.getContextPath()%>/closesys.jsp";
	   	}
   }
}
function closesys(){
   checkclearaccount('<%=path%>');
}
function logoff(){
   	var x=confirm("您即将重新登录系统，是否继续执行本次操作！！！");
   	if(x){
   		parent.window.location="<%=request.getContextPath()%>/logoff.jsp";
   	}
}
   
</script>
</head>

<body topmargin=0 leftmargin=0 onLoad="init()">
	<html:form action="changeBizDateAction.do">
		<div align="center">
			<br>
			<br>
			<br>
			<table width=641 border=0 cellpadding=0 cellspacing=0>
				<tr>
					<td colspan=4 height="32" class="font14"
						background="<%=request.getContextPath()%>/img/search_1.jpg"
						style="PADDING-top: 8px">
						<b>修改业务日期</b>
					</td>
				</tr>
				<tr>
					<td rowspan=3>
						<img src="<%=request.getContextPath()%>/img/search_2.jpg" width=26
							height=325 alt="">
					</td>
					<td colspan=3>
						<img src="<%=request.getContextPath()%>/img/search_3.jpg"
							width=615 height=39 alt="">
					</td>
				</tr>
				<tr>
					<td rowspan=2>
						<img src="<%=request.getContextPath()%>/img/search_4.jpg" width=40
							height=286 alt="">
					</td>
					<td background="<%=request.getContextPath()%>/img/search_5.jpg"
						height="225" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="center" valign="top"
									style="border-bottom-style: solid; border-bottom-width: 1;border-left-style: solid; border-left-width: 1;border-right-style: solid; border-right-width: 1"
									bordercolor="#2D7DC6">
									<table width="100%" border="0" cellspacing="0" cellpadding="2">
										<tr>
											<td height="1"
												background="<%=request.getContextPath()%>/img/bg_line.gif"
												colspan="7"></td>
										</tr>
										<tr valign=middle>
											<td class="td1" align="right">
												<strong>操作员:</strong>
											</td>
											<td class="td1" width="35%">
												<html:text name="loginActionForm" property="userName"
													styleClass="input1" readonly="true"></html:text>
											</td>
											<td class="td1" width="35%"></td>
										</tr>
										<tr valign=middle>
											<td class="td1" align="right">
												<strong>选择系统:</strong>
											</td>
											<td class="td1" width="35%">
												<html:select name="loginActionForm" property="systemId"
													onchange="executeAjax()" onkeydown="enterToTab()">
													<html:optionsCollection name="loginActionForm"
														property="systemsList" value="id" label="caption" />
												</html:select>
											</td>
											<td class="td1" width="35%">
											</td>
										</tr>
										<tr valign=middle style="display:none" id="zt">
											<td class="td1" align="right">
												<strong><font color="red">选择账套:</font> </strong>
											</td>
											<td class="td1" width="35%">
												<html:select name="loginActionForm" property="bookId"
													onchange="executeAjax1()" onkeydown="enterToTab()">
													<html:optionsCollection name="loginActionForm"
														property="userBookList" value="bookId" label="bookName" />
												</html:select>
											</td>
											<td class="td1" width="35%">
											</td>
										</tr>
										<tr valign="middle" id="rq">
											<td class="td1" align="right">
												<strong>业务日期:</strong>
											</td>
											<td class="td1" width="35%">
												<html:text name="loginActionForm" property="bizdate"
													styleClass="input1" onkeydown="enterToSubmit()"></html:text>
											</td>
											<td class="td1" width="35%"></td>
										</tr>
										<html:hidden name="loginActionForm" property="opSystemType"></html:hidden>
										<tr>
										</tr>
									</table>
									<table width="100%" border="0" cellspacing="1" cellpadding="3"
										bgcolor="66BEE3">
										<tr align="center">
											<td colspan="4" class="td1" height="24">
												<input type="submit" name="insys"
													onclick="return checkDate()" value="进入系统">
												<!--<html:submit value="进入系统"></html:submit>-->
												<input type="button" value="重新登录" onClick="logoff();">
												<input type="button" value="退出系统" onClick="closesys();">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
					<td rowspan=2>
						<img src="<%=request.getContextPath()%>/img/search_6.jpg" width=42
							height=286 alt="">
					</td>
				</tr>
				<tr>
					<td>
						<img src="<%=request.getContextPath()%>/img/search_7.jpg"
							width=533 height=61 alt="">
					</td>
				</tr>
			</table>
		</div>
		<html:hidden name="loginActionForm" property="isChooseBook" />
		<html:hidden name="loginActionForm" property="bookName" />
	</html:form>
</body>

</html:html>
