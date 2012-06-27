<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.modifyaccount.action.ShowOpenMondifyListAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			ShowOpenMondifyListAC.SHOWOPENMONDIFY_KEY);
	request.setAttribute("pagination", pagination);
%>
<script type="text/javascript">
<!--
	function check(form){
		var orgId = form.elements["orgId"].value;
		var name = form.elements["orgName"].value;
		var start = form.elements["start"].value;
		var end = form.elements["end"].value;
		if(orgId.trim()=="" && name.trim()=="" && start.trim()=="" && end.trim()==""){alert("最少要有一个查询条件");return false;}
		if(orgId.trim()!=""){//这个地方必须用这样的方法来取值
			if(isNaN(orgId.trim())){alert("请你输入正确的单位编号");return false;}
			if(orgId.trim().indexOf(".")!=-1){alert("单位编号不能有小数点");return false;}
			if(orgId.trim()<=0){alert("单位编号必须是正整数");return false;}
		}
		if(start.trim()!="" && end.trim()==""){alert("查询修改日期的时候请你输入两个日期");return false;}
		if(start.trim()=="" && end.trim()!=""){alert("查询修改的日期时候请你输入两个日期");return false;}
		if(start.trim()!=""){
			return checkDate(form.elements["start"]);
		}
		if(end.trim()!=""){
			return checkDate(form.elements["end"]);
		}
			return true;
}
var s1="";
function loads1(){//初始化页面按钮的方法
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="打印"){//必须对应按钮的顺序
				s1=i;
			}
		}
	}
	var initiaButton = document.getElementById("buttonState").value;
	if(initiaButton == '显示打印'){
		document.all.item(s1).disabled="";
		 
	}else {
		document.all.item(s1).disabled="true";
	}
}
//-->
</script>
<html:html>
<head>
	<html:base />
	<title>开户修改信息</title>
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="loads1();">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="showOpenMondifyListAC.do" />
	</jsp:include>
	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<html:form action="/openMondifyInfoSearchAC.do">
					<html:hidden name="updateInfo" property="buttonState" />
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="7">
								<img src="<%=path%>/img/table_left.gif" width="7" height="37">
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" width="55">
								&nbsp;
							</td>
							<td width="235" background="<%=path%>/img/table_bg_line.gif">
								&nbsp;
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">统计查询&gt;开户修改信息</font>
										</td>
										<td class=font14>
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
										<b class="font14">查 询 条 件</b>
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
				<table border="0" width="95%" id="table1" cellspacing=1
					cellpadding=3 align="center">
					<tr>
						<td width="17%%" class="td1">
							单位编号
						</td>
						<td width="33%" colspan="3">
							<html:text property="orgId" name="updateInfo" styleClass="input3"
								onkeydown="enterNextFocus1();"></html:text>
						</td>
						<td width="17%%" class="td1">
							单位名称
						</td>
						<td width="33%">
							<html:text property="orgName" name="updateInfo"
								styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
						</td>
					</tr>
					<tr>
						<td width="17%%" class="td1">
							修改日期
						</td>
						<td width="14%">
							<html:text property="start" name="updateInfo" styleClass="input3"
								onkeydown="enterNextFocus1();"></html:text>
						</td>
						<td width="4%">
							至
						</td>
						<td width="15%">
							<html:text property="end" name="updateInfo" styleClass="input3"
								onkeydown="enterNextFocus1();"></html:text>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td align="right">
							<html:submit styleClass="buttona" onclick="return check(this);">查询</html:submit>
						</td>
					</tr>
					</html:form>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="212">
										<b class="font14">开户修改信息列表 </b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="692">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<html:form action="/openMondifyPrintAC.do">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
				</table>
				<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
					cellpadding="3" align="center">
					<tr>
						<td align="center" height="23" bgcolor="C4F0FF">
						</td>
						<td align="center" class=td2>
							单位编号
						</td>
						<td align="center" class=td2>
							单位名称
						</td>
						<td align="center" class=td2>
							修改内容
						</td>
						<td align="center" class=td2>
							修改前内容
						</td>
						<td align="center" class=td2>
							修改后内容
						</td>
						<td align="center" class=td2>
							修改时间
						</td>
					</tr>
					<logic:notEmpty name="updateInfo" property="list">
						<%
								int j = 0;
								String strClass = "";
						%>
						<logic:iterate id="e" property="list" name="updateInfo"
							indexId="i">
							<%
										j++;
										if (j % 2 == 0) {
											strClass = "td8";
										} else {
											strClass = "td9";
										}
							%>
							<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);'
								onMouseOver='this.style.background="#eaeaea"'
								onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
								<td>
									<input id="s<%=i%>" type="radio" name="id"
										value="<bean:write name="e" property="id"/>"
										<%if(new Integer(0).equals(i)) out.print("checked"); %>>
								</td>
								<td>
									<bean:write name="e" property="org.id" format="0000000000" />
								</td>
								<td>
									<bean:write name="e" property="org.orgInfo.name" />
								</td>
								<td>
									<bean:write name="e" property="chgColumn" />
								</td>
								<td>
									<bean:write name="e" property="temp_chgBefInfo" />
								</td>
								<td>
									<bean:write name="e" property="temp_chgEndInfo" />
								</td>
								<td>
									<bean:write name="e" property="opTime"
										format="yyyy-MM-dd hh:mm:ss" />
								</td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="updateInfo" property="list">
						<tr>
							<td colspan="11" height="30" style="color:red">
								没有找到与条件相符合的结果!
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
								<jsp:include page="../../../../inc/pagination.jsp">
									<jsp:param name="url" value="showOpenMondifyListAC.do" />
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
				<td colspan="10" bgcolor="#FFFFFF" align="center" height="30">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="70">
								<html:submit value="打印" styleClass="buttona"></html:submit>
							</td>
					</table>
				</td>
			</tr>
			</html:form>
		</table>
		</td>
		</tr>
	</table>
</body>
</html:html>
