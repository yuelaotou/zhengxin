<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.bookmng.credencemodle.action.CredencemodleShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			CredencemodleShowAC.PAGINATION_KEY);
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
function seachSujectCode1(){
	gotoSubjectpop('0','<%=path%>','3');
}
function executeAjax()
{
	if(document.all.subjectCode.value.trim()==""){
	   alert("录入科目代码!");
	   return false;
	}	 
    var subjectCode=document.all.subjectCode.value;          
    var queryString = "credencemodleFindInfoAAC.do?";  
    queryString = queryString + "subjectCode="+subjectCode;
     findInfo(queryString);
}
function executeAjax1(){
	var bizType = document.all.bizType.value.trim();
	var url = "credencemodleFindAAC.do?bizType="+bizType;
	findInfo123(url);
}
function findInfo123(url) {
 createXMLHttpRequest();
	    xmlHttp.onreadystatechange = handleStateChange123;
	    xmlHttp.open("GET", url, true);
	    xmlHttp.send(null);   
}
function handleStateChange123() {
  if(xmlHttp.readyState == 4) {
      if(xmlHttp.status == 200) {        
        var xmlDoc = xmlHttp.responseXML;
		var values = xmlDoc.getElementsByTagName("value");
		 
		var texts  = xmlDoc.getElementsByTagName("text");		
		var selectObj = document.getElementById("childNode");
		selectObj.length = 0;
		
		for ( i=0; i < values.length; i++ ) {
			var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
			selectObj.options[selectObj.length++] = childOption;
			if(i==0){
				document.all.moneyType.value=values[i].firstChild.data;
			}
		}	
      }
   }
}
function getChildValue(){
 	var selectObj = document.getElementById("childNode");
  	document.all.moneyType.value=selectObj.options[selectObj.selectedIndex].value;
}
function displays(){
   showlist();
}
function displays1(subjectCode,subjectName) {
	document.all.subjectCode.value=subjectCode;
	document.all.subjectName.value=subjectName;	
	
}
function searchOrgnization(){	
	var vd=document.all.r;
	var calculRelaType="5";
	for(i=0;i<vd.length;i++){
       if(vd[i].checked){
       		calculRelaType = vd[i].value; 
       		window.open("<%=path%>/sysfinance/subjectrelationTaPopCheckAC.do?calculRelaType="+calculRelaType,"window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes");       
       }
    }if(calculRelaType=="5"){
    	alert('请选择核算类型！');
    }
}
function backErrors(id){
document.all.subjectCode.value="";
document.all.subjectName.value="";
document.all.subjectCode.focus();
    alert(id);
}
function checkData(){
if(document.all.subjectCode.value.trim()==""){
	   alert("请录入科目代码!");
	   return false;
	}
	var v1=document.all.subjectDirection;
	if(v1[0].checked==false&&v1[1].checked==false){
		alert('请选择科目方向!');
		return false;
	}
	if(document.all.bizType.value.trim()==""){
	   alert("请选择业务类型!");
	   return false;
	}
	if(document.all.moneyType.value.trim()==""){
	   alert("请选择金额类型!");
	   return false;
	}
	if(document.all.summray.value.trim()==""){
	   alert("请选择摘要!");
	   return false;
	}	
	return true;
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function appValue(dateMode){
	document.all.subjectDirection3.value=
	dateMode.value.trim();
}
function appValue1(dateMode1){
	document.all.subjectDirection4.value=dateMode1.value.trim();	
}
function delConfirm(){
	return confirm("是否删除该记录?");
}
function displays2(subjectCode,subjectName,subjectDirection,bizType,moneyType,summray) {
	document.all.subjectCode.value=subjectCode;
	document.all.subjectName.value=subjectName;	
	document.all.subjectDirection.value=subjectDirection;
	document.all.bizType.value=bizType;	
	document.all.moneyType.value=moneyType;
	document.all.summray.value=summray;	
    var value="";
    var direction=document.all.subjectDirection;
    if(subjectDirection.trim()==0){
      	direction[0].checked=true;
       	appValue(direction[0]);
    }else{
        direction[1].checked=true;
        appValue(direction[1]);
    }
    if("1"==moneyType){
   		value="公积金_缴存金额";
    }
    if("2"==moneyType){
       	value="公积金_提取金额";
    }
    if("3"==moneyType){
     	value="公积金_销户利息";
   	}
    if("4"==moneyType){
        value="公积金_转出金额";
    }
    if("5"==moneyType){
        value="公积金_转入金额";
    }
    if("6"==moneyType){
        value="公积金_转出利息";
    }
    if("7"==moneyType){
       	value="公积金_结息利息";
    }
     if("8"==moneyType){
       	value="公积金_挂账金额";
    }
    if("11"==moneyType){
        value="贷款_发放金额";
    }
    if("12"==moneyType){
        value="贷款_回收正常本金";
    }
    if("13"==moneyType){
        value="贷款_回收逾期本金";
    }
    if("14"==moneyType){
        value="贷款_回收利息";
    }
    if("15"==moneyType){
       	value="贷款_核销收回金额";
    }
    if("16"==moneyType){
        value="贷款_挂账金额";
    }
   	if("17"==moneyType){
        value="贷款_保证金额";
    }
    if("18"==moneyType){
      	value="贷款_保证金利息";
    }
    if("19"==moneyType){
        value="实收金额";
    }
    if("20"==moneyType){
        value="贷款_回收罚息";
    }
    if("21"==moneyType){
        value="提取金额";
    }
	var selectObj = document.getElementById("childNode");
	selectObj.length = 0;
	var childOption = new Option(value,moneyType);
	selectObj.options[selectObj.length++] = childOption;
	document.all.moneyType.value=moneyType;
}
var tr=0;
function gettr1(tr){
	var id=document.getElementById(tr).childNodes[6].innerHTML;	
	var subjectcode = document.getElementById(tr).childNodes[2].innerHTML;		
    var queryString = "credencemodleUpdate1AAC.do?";  
    queryString = queryString + "subjectCode="+subjectcode.trim()+"&id="+id.trim();     
    findInfo(queryString);
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();"
	onContextmenu="return false">
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
											<font color="00B5DB">&#36134;&#22871;&#31649;&#29702;&gt;&#20973;&#35777;&#27169;&#24335;&#35774;&#32622;</font>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="219">
										<b class="font14">凭 证 模 式 设 置</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="701">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/credencemodleSaveAC" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="15%" class="td1">
								科目代码
								<font color="#FF0000">*</font>
								<br>
							</td>
							<td width="20%">
								<html:text name="credencemodleAF" property="subjectCode"
									onkeydown="gotoEnter();" ondblclick="return executeAjax();"
									styleClass="input3" />
							</td>
							<td width="15%">
								<input type="button" name="Submit2" value="..." class="buttona"
									onclick="seachSujectCode();">
							</td>
							<td width="15%" class="td1">
								科目名称
							</td>
							<td width="35%">
								<html:text name="credencemodleAF" property="subjectName"
									styleClass="input3" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1" height="18">
								科目方向
								<font color="#FF0000">*</font>
							</td>
							<td width="35%" height="18" colspan="2" align="left" class=td7>
								<html:radio name="credencemodleAF" property="subjectDirection"
									value="0" onclick="appValue(this)" />
								借
								<html:radio name="credencemodleAF" property="subjectDirection"
									value="1" onclick="appValue(this)" />
								贷
							</td>
							<td width="15%" class="td1" height="18">
								业务类型
								<font color="#FF0000">*</font>
							</td>
							<td width="35%" height="18">
								<html:select name="credencemodleAF" property="bizType"
									styleClass="input4" onchange="executeAjax1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="bizTypeMap"
										name="credencemodleAF" label="value" value="key" />
								</html:select>

							</td>
						</tr>
						<tr>
							<td width="15%" class="td1" height="18">
								金额类型
								<font color="#FF0000">*</font>
							</td>
							<td width="35%" height="18" colspan="2" align="center">
								<html:hidden property="moneyType" name="credencemodleAF" />
								<html:hidden property="subjectDirection3" name="credencemodleAF" />

								<select class="input4" id="childNode" onChange="getChildValue()">
								</select>
							</td>
							<td width="15%" class="td1" height="18">
								摘要
								<font color="#FF0000">*</font>
							</td>
							<td width="35%" height="18">
								<html:select property="summray" styleClass="input4"
									name="credencemodleAF">
									<option value=""></option>
									<html:options collection="summrayList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkData();">
												<bean:message key="button.sure" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/credencemodleFindAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td valign="top">
											<br>
										</td>
										<td valign="top">
											<br>
										</td>
									</tr>
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="219">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="701">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="15%" class="td1">
								科目代码
							</td>
							<td width="20%">
								<html:text name="credencemodleAF" property="subjectCode1"
									styleClass="input3" />
							</td>
							<td width="15%">
								<input type="button" name="Submit22" value="..." class="buttona"
									onclick="seachSujectCode1();">
							</td>
							<td width="15%" class="td1">
								科目名称
							</td>
							<td width="35%">
								<html:text name="credencemodleAF" property="subjectName1"
									styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1" height="18">
								科目方向
							</td>
							<td width="35%" height="18" colspan="2" align="left" class=td7>
								<html:hidden property="subjectDirection4" name="credencemodleAF" />
								<html:radio name="credencemodleAF" property="subjectDirection1"
									value="0" onclick="appValue1(this)"
									onkeydown="enterNextFocus1();" />
								借
								<html:radio name="credencemodleAF" property="subjectDirection1"
									value="1" onclick="appValue1(this)"
									onkeydown="enterNextFocus1();" />
								贷
							</td>
							<td width="15%" class="td1">
								业务类型
							</td>
							<td width="35%">
								<span class="td4"> <html:select name="credencemodleAF"
										property="bizType1" styleClass="input4"
										onchange="executeAjax1();" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="bizTypeMap"
											name="credencemodleAF" label="value" value="key" />
									</html:select> </span>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="right" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.search" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

				</html:form>
				<html:form action="/credencemodleDeleteAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="203">
											<b class="font14">凭 证 模 式 设 置 列 表</b>
										</td>
										<td width="655" height="22" align="center"
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
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center">
							<td height="19" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td class="td2">
								<a href="javascript:sort('b.biz_type')">业务类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="b.biz_type">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2" height="19">
								科目代码
							</td>
							<td class="td2" height="19">
								科目名称
							</td>
							<td class="td2">
								<a href="javascript:sort('b.subject_direction')">科目方向</a>
								<logic:equal name="pagination" property="orderBy"
									value="b.subject_direction">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2" height="19">
								金额类型
							</td>
						</tr>
						<logic:notEmpty name="credencemodleAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="credencemodleAF"
								property="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>",idAF);gettr1("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
									<td>
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="element" property="id"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td valign="top">
										<bean:write name="element" property="bizType" />
									</td>
									<td valign="top">
										<bean:write name="element" property="subjectCode" />
									</td>
									<td valign="top">
										<bean:write name="element" property="subjectName" />
									</td>
									<td valign="top">
										<bean:write name="element" property="balanceDirection" />
									</td>
									<td valign="top">
										<bean:write name="element" property="moneyType" />
									</td>
									<td style="display:none">
										<bean:write name="element" property="id" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="credencemodleAF" property="list">
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
												<jsp:param name="url" value="credencemodleShowAC.do" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<logic:empty name="credencemodleAF" property="list">

										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona"
												disabled="true">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
									</logic:empty>
									<logic:notEmpty name="credencemodleAF" property="list">

										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona"
												onclick="return delConfirm();">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
					</table>
				</html:form>

			</td>
		</tr>
	</table>
	<form action="credencemodleShowAC.do" method="POST" name="Form1"
		id="Form1">
	</form>
</body>
</html:html>
