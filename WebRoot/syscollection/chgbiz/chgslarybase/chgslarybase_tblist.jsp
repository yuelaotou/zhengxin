<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action.ChgslarybaseTbShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			ChgslarybaseTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>工资基数调整</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js">




<script language="javascript"></script>
<script language="javascript" type="text/javascript">
var s1="";
var s2="";
var s3="";
var s4="";
var tr="tr0"; 
function gettr(trindex) {
  	tr=trindex;
  	update1();
}
function update1() {
  	var chgStatus=document.getElementById(tr).childNodes[10].innerHTML.trim();

  	if(chgStatus=="未启用"){
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
  	}
  	if(chgStatus=="启用"){	
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
  	}  
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	var count="<bean:write name='pagination' property='nrOfElements' />";

	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="修改"){
				s1=i;
			}
			if(document.all.item(i).value=="删除"){
				s2=i;
			}
			if(document.all.item(i).value=="启用"){
				s3=i;
			}
			if(document.all.item(i).value=="撤消启用"){
				s4=i;
			}
			if(document.all.item(i).value=="提交数据"){
				s5=i;
			}
			if(document.all.item(i).value=="撤销提交数据"){
				s6=i;
			}
		}
	}
   if(count!=0){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		update1();
    }else{
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
    }
}

function backErrors(errors){
   alert(errors);
}

function toPop(status){
	gotoOrgpop(status,'<%=path%>');
}

function remove() {
 var x=confirm("是否确定删除该记录?");
	if(!x){
	   return false;
   }
}
function removeall() {
 var x=confirm("是否确定全部删除该记录?");
	if(!x){
	   return false;
   }
}
function checkDataUse() {
   	var chgMonth=document.chgslarybaseListAF.elements["chgMonth"].value;
	document.all.chgMonth2.value=chgMonth;
 	var x=confirm("是否确定启用?");
	if(!x){	 
	   return false;
	}
}
function checkDataDeluse() {
 	var x=confirm("是否确定撤消启用?");
	if(!x){	 
	   return false;
	}
}
function checkDataReferring() {
 	var x=confirm("是否确定提交该笔业务?");
	if(!x){	 
	   return false;
	}
}
function checkDatapproval() {
 	var x=confirm("是否确定撤消提交该笔业务?");
	if(!x){	 
	   return false;
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="chgslarybaseTbShowAC.do" />
	</jsp:include>

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
										<a href="chgslarybaseTaForwardURLAC.do" class=a2>办理变更</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										变更维护
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
									<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">变更业务&gt;工资基数调整</font>
									</td>
									<td width="29" class=font14>
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
										<b class="font14">单 位 信 息</b>
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
				<html:form action="/chgslarybaseTbFindAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td width="22%" colspan="3">
								<html:text name="chgslarybaseListAF" property="org.id"
									styleClass="input3" styleId="txtsearch"></html:text>

							</td>

							<td class="td1" width="17%">
								单位名称
							</td>
							<td width="33%" colspan="2">
								<html:text name="chgslarybaseListAF" property="org.orgInfo.name"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								调整年月
							</td>


							<td width="15%">
								<html:text name="chgslarybaseListAF" property="chgMonth"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="chgslarybaseListAF" property="endChgMonth"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="26%" colspan="6">
							</td>
							<td width="17%" align="right">
								<html:submit styleClass="buttona">
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
										<b class="font14">工资基数调整列表</b>
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

				<html:form action="/chgslarybaseTbMaintainAC" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								业务编号
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('a1.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="a1.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								<a
									href="javascript:sort('b1.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="b1.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								人数
							</td>
							<td align="center" class=td2>
								原总工资基数
							</td>
							<td align="center" class=td2>
								新总工资基数
							</td>
							<td align="center" class=td2>
								原应缴总额
							</td>
							<td align="center" class=td2>
								新应缴总额
							</td>

							<td align="center" class=td2>
								<a href="javascript:sort('a2.chg_month')">调整年月</a>
								<logic:equal name="pagination" property="orderBy"
									value="2.chg_month">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								变更状态
							</td>
							<security:orgcan>
								<td width="10%" align="center" class=td2>
									提取状态
								</td>
							</security:orgcan>
						</tr>
						<logic:notEmpty name="chgslarybaseListAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="chgslarybaseListAF" property="list"
								id="element" indexId="i">
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
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="element" property="id"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td>
										<bean:write name="element" property="id" />
									</td>
									<td>
										<bean:write name="element" property="orgid"
											format="0000000000" />
									</td>
									<td>
										<a href="#"
											onClick="window.open('chgslarybaseTbWindowShow.do?paymentid=<bean:write name="element" property="id"/>&sun=sun','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return false;"><bean:write
												name="element" property="orgname" /> </a>
									</td>
									<td>
										<bean:write name="element" property="personCount" />
									</td>
									<td>
										<bean:write name="element" property="preSalary" />
									</td>
									<td>
										<bean:write name="element" property="curSalary" />
									</td>
									<td>
										<bean:write name="element" property="prePay" />
									</td>
									<td>
										<bean:write name="element" property="curPay" />
									</td>
									<td>
										<bean:write name="element" property="chgMonth" />
									</td>
									<td>
										<bean:write name="element" property="chgStatus" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="chgslarybaseListAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
							<tr>
								<td colspan="11"></td>
							</tr>
						</logic:empty>
						<tr>
							<td colspan="9"></td>
						</tr>
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
												<jsp:param name="url" value="chgslarybaseTbShowAC.do" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr>
							<td align="center" height="6" colspan="1">
								<input type="hidden" name="chgMonth2" value="">
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
												<bean:message key="button.update" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return remove()">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return checkDataUse()">
												<bean:message key="button.use" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return checkDataDeluse()">
												<bean:message key="button.deluse" />
											</html:submit>
										</td>
										<td>
											<security:orgcan>
												<html:submit property="method" styleClass="buttona"
													onclick="return checkDataReferring()">
													<bean:message key="button.referring.data" />
												</html:submit>
											</security:orgcan>
										</td>
										<td>
											<security:orgcan>
												<html:submit property="method" styleClass="buttonc"
													onclick="return checkDatapproval()">
													<bean:message key="button.pproval.data" />
												</html:submit>
											</security:orgcan>
										</td>
									</tr>
								</table>
							</td>
						</tr>

					</table>
				</html:form>
	</table>
</html:html>
