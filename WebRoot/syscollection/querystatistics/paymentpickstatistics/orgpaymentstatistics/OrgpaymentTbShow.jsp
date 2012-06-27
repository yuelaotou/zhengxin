<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.action.*"%>
<%@ include file="/checkUrl.jsp"%>


<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			EmppaymentstatisticsShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>

</head>

<script language="javascript">
var s1="";
function loads(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="打印"){
				s1=i;
			}
		}
	}
	 var list = document.all.listCount.value;
  
    if(list.length==2){
  		document.all.item(s1).disabled="true";
    }else{
		document.all.item(s1).disabled="";
    }
}

function gotocheck(){
   var id = document.all.empId.value;
   var name = document.all.empName.value;
   var month = document.all.pay_month.value;
   
   if(month == ""){
     alert("＂查询年度＂为必添查询条件！");
     return false;
   }
   if(id == "" && name == ""){
     alert("请输入含＂查询年度＂在内的两项查询条件！");
     return false;
   }
   if(month.length !=4 ){
     alert("请输入四位的年度，格式为1835");
     return false;
   }
}




</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="to_showTbAC.do" />
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
										<a href="orgpaymentShowAC.do" class=a2>单位缴存情况</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										职工缴存情况
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
									<td class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<a href="#" class=a1>统计查询</a><font color="00B5DB">&gt;<a
											href="#" class=a1>职工缴存情况</a> </font>
									</td>
									<td width="15" class=font14>
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
				<html:form action="/emppaymentstatisticsFindAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td class="td1" width="17%">
								职工编号
							</td>
							<td width="22%">
								<html:text name="orgpaymentAF" property="empId"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								职工名称
							</td>
							<td width="33%">
								<html:text name="orgpaymentAF" property="empName"
									styleClass="input3" maxlength="20" />
							</td>
						</tr>
						<tr>
							<td class="td1" width="17%">
								查询年度
							</td>
							<td width="33%">
								<html:text name="orgpaymentAF" property="pay_month"
									styleClass="input3" maxlength="4" />
							</td>
							<td width="17%" class="td1">
								&nbsp;
							</td>
							<td width="33%" colspan="3">
								&nbsp;
							</td>
							<td>
								<html:submit property="method" styleClass="buttona"
									onclick=" return gotocheck();">
									<bean:message key="button.search" />
								</html:submit>

							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：单位缴存
								<u>：<bean:write name="orgpaymentAF" property="orgPayment" />
								</u>职工缴存
								<u>：<bean:write name="orgpaymentAF" property="empPayment" />
								</u>

							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="32" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font10">职工缴存情况列表</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/syscollection/tranmng/tranout/<%=path%>/img/bg2.gif"
											align="center">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr bgcolor="1BA5FF">
						<td align="center" height="6" colspan="1"></td>
					</tr>
				</table>

				<input type="hidden" name="listCount"
					value="<bean:write name="orgpaymentAF" property="list"/>">
				<html:form action="/emppaymentstatisticsPrintAC.do"
					style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" bgcolor="C4F0FF">
								&nbsp;
							</td>

							<td align="center" class=td2>
								<a href="javascript:sort('paymonth')">年月</a>
								<logic:equal name="pagination" property="orderBy"
									value="paymonth">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								单位缴额
							</td>

							<td align="center" class=td2>
								职工缴额
							</td>
						</tr>
						<logic:notEmpty name="orgpaymentAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="orgpaymentAF" property="list" id="element"
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
									onclick='gotoClickpp("<%=i%>", idAF);gettr("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>", idAF);' class="<%=strClass %>">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>

									<td>
										<bean:write name="element" property="pay_month" />
									</td>
									<td>
										<bean:write name="element" property="orgPay" />
									</td>
									<td>
										<bean:write name="element" property="empPay" />
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="orgpaymentAF" property="list">
							<tr>
								<td colspan="9" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
						</logic:empty>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr>
						</tr>
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
												<jsp:param name="url" value="to_showTbAC.do" />
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
									<td width="70">
										<html:submit property="method" styleClass="buttonb">
											<bean:message key="button.print" />
										</html:submit>
										&nbsp;&nbsp;
									</td>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
</body>
</html:html>

