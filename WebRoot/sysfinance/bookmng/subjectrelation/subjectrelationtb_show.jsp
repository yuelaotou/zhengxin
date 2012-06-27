<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action.SubjectrelationTbShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			SubjectrelationTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>财务核算</title>

	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script language="javascript"></script>
<script language="javascript" type="text/javascript">
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function popOrg(){
	gotoOrgpop('2','<%=path%>','3');
}

function seachSujectCode(){

	gotoSubjectpop('0','<%=path%>','0')
}


function search(){
var count1=0;
var count2=0;
if(document.all.calculRelaType.value.trim()==""){
count1=count1+1;
}
if(document.all.orgid.value.trim()!=""){
count2=count2+1;
}
if(document.all.office.value.trim()!=""){
count2=count2+1;
}
if(document.all.bankid.value.trim()!=""){
count2=count2+1;
}
if(count1==0){
   if(count2>1){
    alert("选择核算类型后，只能选择一种核算关系值！");
    document.all.office.value="";
    document.all.orgid.value="";
    document.all.bankid.value="";
     return false;
   }
}
if(count1==1)
{
   if(count2==0){
    return true;
   }
    
}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function delConfirm(){
return confirm("是否删除该记录?");
}
function delAllConfirm(){
return confirm("是否删除全部记录?");
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
function backErrors(id){
document.all.subjectCode.value="";
    alert(id);
}function displays1(subjectCode,subjectName,balanceDirection,balance,firstSubjectCode) {
}
function displays(){
   
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();" onContextmenu="return false">

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
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href=<%=path%>/sysfinance/subjectrelationTaForwardURLAC.do
											class=a2>科目关系设置</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										科目关系维护
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="178">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="680">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/subjectRelationTbFindAC" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="20%" class="td1">
								科目代码
							</td>
							<td width="20%">
								<html:text name="subjectrelationTbAF" property="subjectCode"
									styleClass="input3" onkeydown="gotoEnter();" />
							</td>
							<td width="10%">
								<input type="button" name="Submit2" value="..." class="buttona"
									onclick="seachSujectCode();">
							</td>
							<td width="20%" class="td1">
								科目名称
							</td>
							<td width="30%">
								<html:text name="subjectrelationTbAF" property="subjectName"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="20%" class="td1" height="18">
								核算类别
							</td>
							<td width="30%" height="18" colspan="2" align="left">
								<html:select name="subjectrelationTbAF"
									property="calculRelaType" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="calculRelaTypeMap"
										name="subjectrelationTbAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="20%" class="td1">
								办事处（核算关系值）
							</td>
							<td width="30%" class="in">
								<html:select property="office" styleClass="input4"
									name="subjectrelationTbAF" onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="20%" class="td1" height="20">
								单位（核算关系值）
							</td>
							<td width="20%" height="20" align="left">
								<html:text name="subjectrelationTbAF" property="orgid"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="10%" height="20" align="left">
								<input type="button" name="Submit22" value="..." class="buttona"
									onclick="popOrg();">
							</td>
							<td width="20%" class="td1" height="20">
								银行（核算关系值）
							</td>
							<td width="30%" class="in" height="20">
								<html:select property="bankid" styleClass="input4"
									name="subjectrelationTbAF" onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="loanbankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" align="right" cellpadding="0" cellspacing="0">
									<tr>
										<td width="70">
											<html:submit styleClass="buttona" onclick="return search();">
												<bean:message key="button.search" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/subjectRelationTbMaintainAC"  style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="181">
											<b class="font14">科目关系设置列表</b>
										</td>
										<td width="677" height="22" align="center"
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
						<tr align="center">
							<td height="19" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td class="td2" height="19">
								科目代码
							</td>
							<td class="td2" height="19">
								科目名称
							</td>
							<td class="td2" height="19">&#19978;&#32423;&#31185;&#30446;&#20195;&#30721; 
							</td>
							<td class="td2" height="19">
								核算类别
							</td>
							<td class="td2" height="19">
								核算关系值
							</td>
							<td class="td2" height="19">
								核算关系值名称
							</td>
						</tr>
						<logic:notEmpty name="subjectrelationTbAF" property="list">
						<%
							int j=0;
							String strClass="";
						 %>
							<logic:iterate id="element" name="subjectrelationTbAF"
								property="list" indexId="i">
								<%	j++;
								if (j%2==0) {strClass = "td8";
								}
							    else {strClass = "td9";
							    }%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>",idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass %>">
									<td>
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="element" property="subjectreleid"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="subjectCode" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="subjectName" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="firstSubjectCode" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="calculRelaType" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="calculRelaValue" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="calculRelaName" />
										</p>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="subjectrelationTbAF" property="list">
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
												<jsp:param name="url" value="subjectrelationTbShowAC.do" />
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
									<logic:empty name="subjectrelationTbAF" property="list">

										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona"
												disabled="true">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona"
												disabled="true">
												<bean:message key="button.deleteall" />
											</html:submit>
										</td>

									</logic:empty>
									<logic:notEmpty name="subjectrelationTbAF" property="list">

										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona"
												onclick="return delConfirm();">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona"
												onclick="return delAllConfirm();">
												<bean:message key="button.deleteall" />
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
</body>

</html:html>

