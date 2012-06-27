<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.contractchg.assurepledgechg.action.AssurepledgechgTaShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			AssurepledgechgTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>
<script type="text/javascript">
var s1="";

//function gotoTa(){
 //var id=getCheckId();
  //window.open('endorsecontractTaShowAC.do?comeFromType="yes"&contractId='+id,'','width=600,height=400,top=200,left=300,scrollbars=yes');return gotoShow();
  //location='endorsecontractTaShowAC.do?comeFromType="yes"&contractId='+id;
//}
function checkNum(){
 var number = document.all.cardNum.value;
 if(number != ""){
   if(isNaN(number)){
     alert("请输入正确格式的证件号码！");
     return false;
    
   }
   }
 }


function loads(){
   document.all.houseType.value="";
  	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	
	 for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="修改"){
				s1=i;
			}
		}
	}
	
	
	 
	var islist = document.all.isList.value;
	if(islist == '[]'){
	  document.all.item(s1).disabled="true";
	  //document.all.item(s2).disabled="true";
	  //document.all.item(s3).disabled="true";
	  //document.forms[1].elements["Submit32"].disabled="true";
	}
} 
</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="loads();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="assurepledgechgTaShowAC.do" />
	</jsp:include>
	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=request.getContextPath()%>/img/table_left.gif"
								width="7" height="37">
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							width="10">
							&nbsp;
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							valign="top">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttonbl.gif"
										align="center" valign="top" style="PADDING-top: 7px">
										担保抵押变更维护
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										<a href="<%=path%>/sysloan/assurepledgechgTbShowAC.do" class=a2>抵押合同信息</a>
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										<a href="<%=path%>/sysloan/assurepledgechgTcShowAC.do" class=a2>质押合同信息</a>
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										<a href="<%=path%>/sysloan/assurepledgechgTdShowAC.do" class=a2>保证人信息</a>
									</td>
								</tr>
							</table>
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							align="right">
							<table width="200" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=request.getContextPath()%>/img/title_banner.gif"
											width="37" height="24">
									</td>
									<td width="228" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">合同变更&gt;基本信息变更</font>
									</td>
									<td width="35" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="10">
							<img src="images/table_right.gif" width="9" height="37">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="165">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22"
										background="<%=request.getContextPath()%>/img/bg2.gif"
										align="center" width="773">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/assurepledgechgTaFindAC.do" method="post"
					style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="theAssurepledgechgTaAF" property="contractId"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td width="33%" colspan="3">
								<html:text name="theAssurepledgechgTaAF" property="debitter"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								借款人职工编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="theAssurepledgechgTaAF" property="empId"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								证件号码
							</td>
							<td width="33%" colspan="3">
								<html:text name="theAssurepledgechgTaAF" property="cardNum"
									onkeydown="enterNextFocus1();" styleClass="input3"
									onblur="checkNum();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								购房类型
							</td>
							<td width="33%" align="center" colspan="3">
								<html:select property="houseType" onkeydown="enterNextFocus1();"
									styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="map"
										name="theAssurepledgechgTaAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1"></td>
							<td align="center" colspan="3">
							</td>
						</tr>
						<tr>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return gotocheck();">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="164">
										<b class="font14">贷款合同信息列表</b>
									</td>
									<td height="22" background="images/bg2.gif" align="center"
										width="761">
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
				<html:form action="/assurepledgechgTaMaintainAC.do" method="post"
					style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center" bgcolor="C4F0FF">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								合同编号
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('t2.borrower_name')">借款人姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="t2.borrower_name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('t1.loan_money')">借款金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="t1.loan_money">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								借款起始日期
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('t1.loan_time_limit')">借款期限（月） </a>
								<logic:equal name="pagination" property="orderBy"
									value="t1.loan_time_limit">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								借款终止日期
							</td>
							<td align="center" class=td2>
								借款每月利率
							</td>
							<td align="center" class=td2>
								月还本息
							</td>
							<td align="center" class=td2>
								放款银行
							</td>
						</tr>
						<logic:notEmpty name="theAssurepledgechgTaAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="theAssurepledgechgTaAF"
								property="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>", idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>", idAF);'
									class="<%=strClass%>" onDblClick="">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="contractId"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td>
										<bean:write name="element" property="contractId" />
									</td>
									<td>
										<bean:write name="element" property="debitter" />
									</td>
									<td>
										<bean:write name="element" property="loanMoney" />
									</td>
									<td>
										<bean:write name="element" property="startDate" />
									</td>
									<td>
										<bean:write name="element" property="loanTimeLimit" />
									</td>
									<td>
										<bean:write name="element" property="endDate" />
									</td>
									<td>
										<bean:write name="element" property="loanMonthRate" />
									</td>
									<td>
										<bean:write name="element" property="corpusInterest" />
									</td>
									<td>
										<bean:write name="element" property="bank" />
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>

						<input type="hidden" name="isList"
							value="<bean:write name="theAssurepledgechgTaAF" property="list"/>">
						<logic:empty name="theAssurepledgechgTaAF" property="list">
							<tr>
								<td colspan="16" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>

						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td align="center">
								<table width="100%" height="19" border="0" cellpadding="0"
									cellspacing="0">


									<tr>
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="assurepledgechgTaShowAC.do" />
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
										<td width="70">
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.update" />
											</html:submit>
											&nbsp;
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
</body>
</html:html>

