<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action.OrgChgCheckShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			OrgChgCheckShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>汇缴比例调整-变更维护</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>

<script language="javascript" type="text/javascript">
var s1="";
var s2="";
var s3="";
function loads(){
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="审核通过"){    
				s1=i;
			}
			if(document.all.item(i).value=="撤销审核"){
				s2=i;
			}
			if(document.all.item(i).value=="打印"){
				s3=i;
			}
		}
	} 

  	var count = "<bean:write name="pagination" property="nrOfElements" />";
	if(count!=0){
		update();
	}else{
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
	}
}
var tr="tr0"; 
function gettr(trindex) {
  	tr=trindex;
  	update();
}
function update() {
  	var status=document.getElementById(tr).childNodes[15].childNodes[0].innerHTML.trim();
  	var print=document.getElementById(tr).childNodes[16].childNodes[0].innerHTML.trim();
  	if(print=="审核通过"){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
  	}else{
  		if(status=="未提交"){
			document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
			document.all.item(s3).disabled="true";
	  	}else{
			document.all.item(s1).disabled="";
			document.all.item(s2).disabled="true";
			document.all.item(s3).disabled="true";
	  	}
  	}
}

function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);    
	</logic:messagesPresent>
}

function checkID(){
	var id=document.orgChgAF.orgid.value.trim();
	if(isNaN(id)){
		alert("请录入正确的单位编号,如0104000002");
		document.orgChgAF.orgid.focus();
		return false;	
	}else{
		return true;
	}
}

</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="reportErrors(); loads();">
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
										变更审核
									</td>
								</tr>
							</table>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">变更审核</font>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="117">
										<b class="font14">查 询 信 息</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/orgChgCheckFindAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1" algin="center">
								单位编号
							</td>
							<td width="22%">
								<html:text name="orgChgAF" property="orgid" styleClass="input3"
									styleId="txtsearch">
								</html:text>
							</td>
							<td width="17%" class="td1" algin="center">
								提交状态
							</td>
							<td width="22%">
								<html:select name="orgChgAF" property="orgChg.status" styleClass="input4">
									<html:option value=""></html:option>
									<html:option value="0">未提交</html:option>
									<html:option value="1">已提交</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								审核状态
							</td>
							<td width="22%">
								<html:select name="orgChgAF" property="orgChg.print" styleClass="input4">
									<html:option value=""></html:option>
									<html:option value="0">审核未通过</html:option>
									<html:option value="1">审核通过</html:option>
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return checkID();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="154">
										<b class="font14">审核列表</b>
									</td>
									<td width="750" height="22" align="center"
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
				<html:form action="/orgChgCheckMainTainAC.do" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
								单选
							</td>
							<td align="center" class=td2>
								单位编号
							</td>
							<td align="center" class=td2>
								单位名称
							</td>
							<td align="center" class=td2>
								调整前月缴存比例
							</td>
							<td align="center" class=td2>
								调整后月缴存比例
							</td>
							<td align="center" class=td2>
								调整后月缴存差额
							</td>
							<td align="center" class=td2>
								调整前应缴额
							</td>
							<td align="center" class=td2>
								调整后应缴额
							</td>
							<td align="center" class=td2>
								调整后月缴存差额
							</td>
							<td align="center" class=td2>
								补缴时间
							</td>
							<td align="center" class=td2>
								补缴起止月份
							</td>
							<td align="center" class=td2>
								补缴人数
							</td>
							<td align="center" class=td2>
								补缴金额合计
							</td>
							<td align="center" class=td2>
								补缴金额个人
							</td>
							<td align="center" class=td2>
								补缴金额单位
							</td>
							<td align="center" class=td2>
								是否提交
							</td>
							<td align="center" class=td2>
								审核状态
							</td>
						</tr>
						<logic:notEmpty name="orgChgAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="orgChgAF" property="list" id="element"
								indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>",idAF);;gettr("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="org.id"
												format="0000000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="org.orgInfo.name" />
										</p>
									</td>
									<td valign="top">
										<p>
											<logic:notEqual name="element" property="newRate" value="0">
												<bean:write name="element" property="preRate" />
											</logic:notEqual>
										</p>
									</td>
									<td valign="top">
										<p>
											<logic:notEqual name="element" property="newRate" value="0">
												<bean:write name="element" property="newRate" />
											</logic:notEqual>
										</p>
									</td>
									<td valign="top">
										<p>
											<logic:notEqual name="element" property="newRate" value="0">
												<bean:write name="element" property="payRate" />
											</logic:notEqual>
										</p>
									</td>
									<td valign="top">
										<p>
											<logic:notEqual name="element" property="newPay" value="0">
												<bean:write name="element" property="prePay" />
											</logic:notEqual>
										</p>
									</td>
									<td valign="top">
										<p>
											<logic:notEqual name="element" property="newPay" value="0">
												<bean:write name="element" property="newPay" />
											</logic:notEqual>
										</p>
									</td>
									<td valign="top">
										<p>
											<logic:notEqual name="element" property="newPay" value="0">
												<bean:write name="element" property="paySalary" />
											</logic:notEqual>
										</p>
									</td>
									<td valign="top">
										<p>
											<logic:notEqual name="element" property="addCount" value="0">
												<bean:write name="element" property="addMonth" />
											</logic:notEqual>
										</p>
									</td>
									<td valign="top">
										<p>
											<logic:notEqual name="element" property="addCount" value="0">
												<bean:write name="element" property="addStEndMonth" />
											</logic:notEqual>
										</p>
									</td>
									<td valign="top">
										<p>
											<logic:notEqual name="element" property="addCount" value="0">
												<bean:write name="element" property="addCount" />
											</logic:notEqual>
										</p>
									</td>
									<td valign="top">
										<p>
											<logic:notEqual name="element" property="addCount" value="0">
												<bean:write name="element" property="addSum" />
											</logic:notEqual>
										</p>
									</td>
									<td valign="top">
										<p>
											<logic:notEqual name="element" property="addCount" value="0">
												<bean:write name="element" property="addEmp" />
											</logic:notEqual>
										</p>
									</td>
									<td valign="top">
										<p>
											<logic:notEqual name="element" property="addCount" value="0">
												<bean:write name="element" property="addOrg" />
											</logic:notEqual>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="status" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="print" />
										</p>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="orgChgAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合结果！
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
												<jsp:param name="url" value="orgChgCheckShowAC.do" />
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
									<tr>
										<td>
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.auditing.pass" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return checkDetele();">
												<bean:message key="button.auditing.delete" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.print" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
</html:html>
