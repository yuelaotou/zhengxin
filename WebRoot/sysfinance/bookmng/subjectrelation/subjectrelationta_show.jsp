<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action.SubjectrelationTaShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			SubjectrelationTaShowAC.PAGINATION_KEY);			
	request.setAttribute("pagination", pagination);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>财务核算</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script language="javascript"></script>
<script language="javascript" type="text/javascript">
function showlist() {
  document.Form1.submit();
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function seachSujectCode(){

	gotoSubjectpop('0','<%=path%>','0');
}
function executeAjax()
{
	if(document.all.subjectCode.value.trim()==""){
	   alert("录入科目代码!");
	   return false;
	}	 
    var subjectCode=document.all.subjectCode.value;      
    var queryString = "subjectrelationTaFindAAC.do?";  
    queryString = queryString + "subjectCode="+subjectCode;
     findInfo(queryString);
}
function displays(){
   showlist();
}
function displays1(subjectCode,subjectName,balanceDirection,balance,firstSubjectCode) {
	document.all.subjectCode.value=subjectCode;
	document.all.subjectName.value=subjectName;
	document.all.balanceDirection.value=balanceDirection;
	document.all.balance.value=balance;	
	document.all.firstSubjectCode.value=firstSubjectCode;	
	showlist();
}
function searchOrgnization(){	
	var vd=document.all.r;
	var subjectCode=document.all.subjectCode.value.trim();
	
	var firstSubjectCode=document.all.firstSubjectCode.value.trim();	
	if(subjectCode.trim()==""){
	alert("请输入科目代码！");
	return false;
	}
	if(firstSubjectCode.trim()==""){
	alert("请输入一级科目代码！");
	return false;
	}
	var position=subjectCode.indexOf(firstSubjectCode);
	if(position!=0){
	alert("不允许对该科目代码进行设置科目关系！");
	return false;
	}
	var calculRelaType="5";
	for(i=0;i<vd.length;i++){
       if(vd[i].checked){
       	calculRelaType = vd[i].value;        
       	window.open("<%=path%>/sysfinance/subjectrelationTaPopCheckAC.do?calculRelaType="+calculRelaType+"&firstSubjectCode="+firstSubjectCode,"window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes");       
       }
    }if(calculRelaType=="5"){
    alert('请选择核算类型！');
    }
}
function backErrors(id){
document.all.subjectCode.value="";
    alert(id);
}
function executeAjaxIn(){
	var firstSubjectCode=document.all.firstSubjectCode.value.trim();
		if(firstSubjectCode.length!=0){
		    var queryString = "subjectRelationTaCheckAAC.do?";
		    queryString = queryString + "firstSubjectCode="+firstSubjectCode;
		    findInfo(queryString);
		}
}
function display(message,flag){
	if(message.length!=0){
		alert(message);
		document.all.firstSubjectCode.value="";
		document.all.firstSubjectCode.focus();
	}

}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">	
		<table width="95%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="7">
								<img src="<%=path%>/img/table_left.gif" width="7" height="37">
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" width="55">
								&nbsp;
							</td>
							<td width="235" background="<%=path%>/img/table_bg_line.gif">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="112" height="37"
											background="<%=path%>/img/buttonbl.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											科目关系设置
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">											
											<a href=<%=path%>/sysfinance/subjectrelationTbForwardURLAC.do class=a2>科目关系维护</a>
										</td>
									</tr>
								</table>
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<p>
												<font color="00B5DB">&#36134;&#22871;&#31649;&#29702;&gt;&#31185;&#30446;&#20851;&#31995;&#35774;&#32622;</font>
											</p>
										</td>
										<td width="74" class=font14>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td width="9">
								<img src="<%=path%>/img/table_right.gif" width="9" height="37">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class=td3>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="176">
											<b class="font14">科 目 关 系 设 置</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="682">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:form action="/subjectrelationTaSave1AC.do" style="margin: 0">
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							<tr>
								<td width="15%" class="td1">
									科目代码
									<font color="#FF0000">*</font>
									<br>
								</td>
								<td width="20%">
									<html:text name="subjectrelationTaAF" property="subjectCode"
										styleClass="input3" 
										  onkeydown="gotoEnter();"/>
								</td>
								<td width="15%">
									<input type="button" name="Submit42" value="..."
										class="buttona" onclick="seachSujectCode();" >
								</td>
								<td width="15%" class="td1">
									科目名称
								</td>
								<td width="35%">
									<html:text name="subjectrelationTaAF" property="subjectName"
										styleClass="input3" readonly="true"></html:text>
									<br>
								</td>
							</tr>
							<tr>
								<td width="15%" class="td1" height="18">
									发生额方向
								</td>
								<td width="35%" height="18" colspan="2" align="center">
									<html:text name="subjectrelationTaAF"
										property="balanceDirection" styleClass="input3"
										readonly="true"></html:text>
									<br>
								</td>
								<td width="15%" class="td1" height="18">
									科目余额
								</td>
								<td width="35%" height="18">
									<html:text name="subjectrelationTaAF" property="balance"
										styleClass="input3" readonly="true"></html:text>
									<br>
								</td>
							</tr>
							<tr>
								<td width="15%" class="td1" height="18">&#19978;&#32423;&#31185;&#30446; 
								</td>
								<logic:notEqual name="subjectrelationTaAF" property="calculRelaType" value="">	
								<td width="35%" height="18" colspan="2" align="center">
									<html:text name="subjectrelationTaAF"
										property="firstSubjectCode" styleClass="input3"
										readonly="true"></html:text>
									<br>
								</td>
								</logic:notEqual>
								<logic:equal name="subjectrelationTaAF" property="calculRelaType" value="">	
								<td width="35%" height="18" colspan="2" align="center">
									<html:text name="subjectrelationTaAF"
										property="firstSubjectCode" styleClass="input3"  
										onblur="executeAjaxIn();"></html:text>
									<br>
								</td>
								</logic:equal>
								<td width="15%" class="td1" height="18">
									核算类别
									<font color="#FF0000">*</font>
								</td><logic:notEqual name="subjectrelationTaAF" property="calculRelaType" value="">	
							  			<td width="35%" height="18" class="td7">
									<input type="radio" name="r" value="0" disabled="true" <logic:equal name="subjectrelationTaAF" property="calculRelaType" value="0">	
							   checked						
							</logic:equal>>
									办事处
									<input type="radio" name="r" value="1" disabled="true" <logic:equal name="subjectrelationTaAF" property="calculRelaType" value="1">	
							   checked						
							</logic:equal>>
									银行
									<input type="radio" name="r" value="2" disabled="true" <logic:equal name="subjectrelationTaAF" property="calculRelaType" value="2">	
							   checked						
							</logic:equal>>
									单位
									<input type="radio" name="r" value="3" disabled="true" <logic:equal name="subjectrelationTaAF" property="calculRelaType" value="3">	
							   checked						
							</logic:equal>>
									其它
									<img src="<%=path%>/img/8.png" width="16" height="16" onClick="searchOrgnization();">
								</td>	
							</logic:notEqual>
							 <logic:equal name="subjectrelationTaAF" property="calculRelaType" value="">	
							   	<td width="35%" height="18" class="td7">
									<input type="radio" name="r" value="0" <logic:equal name="subjectrelationTaAF" property="calculRelaType" value="0">	
							   checked						
							</logic:equal>>
									办事处
									<input type="radio" name="r" value="1"<logic:equal name="subjectrelationTaAF" property="calculRelaType" value="1">	
							   checked						
							</logic:equal>>
									银行
									<input type="radio" name="r" value="2"<logic:equal name="subjectrelationTaAF" property="calculRelaType" value="2">	
							   checked						
							</logic:equal>>
									单位
									<input type="radio" name="r" value="3"<logic:equal name="subjectrelationTaAF" property="calculRelaType" value="3">	
							   checked						
							</logic:equal>>
									其它
									<img src="<%=path%>/img/8.png" width="16" height="16" onClick="return searchOrgnization();">
								</td>			
							</logic:equal>
							
							
								
							</tr>
						</table>
				
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="177">
											<b class="font14">科目关系设置列表</b>
										</td>
										<td width="681" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
						<tr align="center" bgcolor="C4F0FF">
							<td class="td2">
							<logic:equal name="subjectrelationTaAF" property="calculRelaType" value="2">	
							   单位代码							
							</logic:equal>
							<logic:equal name="subjectrelationTaAF" property="calculRelaType" value="1">
								银行代码
							</logic:equal>
							<logic:equal name="subjectrelationTaAF" property="calculRelaType" value="0">
								办事处代码
							</logic:equal>
							</td>
							<td class="td2">
							<logic:equal name="subjectrelationTaAF" property="calculRelaType" value="2">
								单位名称
							</logic:equal>
							<logic:equal name="subjectrelationTaAF" property="calculRelaType" value="1">
								银行名称
							</logic:equal>
							<logic:equal name="subjectrelationTaAF" property="calculRelaType" value="0">
								办事处名称
							</logic:equal>								
							</td>
						</tr>
						<logic:notEmpty name="subjectrelationTaAF" property="list">
						<%
							int j=0;
							String strClass="";
						 %>
							<logic:iterate id="element" name="subjectrelationTaAF" property="list"
								indexId="i">
								<%
								j++;
								if (j%2==0) {strClass = "td8";
								}
							    else {strClass = "td9";
							    }
								 %>
								<tr class="<%=strClass %>">									
									<td valign="top">
										<p>
											<bean:write name="element" property="calculRelaValue"
												format="000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="calculName" />
										</p>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="subjectrelationTaAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合的结果！
									<br>
								</td>
							</tr>
						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="subjectrelationTaShowAC.do" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>				
					</html:form>
					<form action="subjectrelationTaShowAC.do" method="POST" name="Form1"
						id="Form1">
					</form>
				</td>
			</tr>
		</table>
</body>
</html:html>
