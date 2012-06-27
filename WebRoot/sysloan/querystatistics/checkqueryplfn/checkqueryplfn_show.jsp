<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action.CheckQueryPlFnShowAC"%>
<%
String path = request.getContextPath();
Object pagination= request.getSession().getAttribute(CheckQueryPlFnShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>个贷账查询</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
	<table width="1200" border="0" cellspacing="0" cellpadding="0"
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
							&nbsp;
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
										<font color="00B5DB">统计查询&gt;个贷账查询</font>
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
				<html:form action="/checkQueryPlFnFindAC.do" style="margin: 0">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="171">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="688">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table border="0" width="95%" id="table1" cellspacing=1
					cellpadding=0 align="center">
					<tr>
						<td width="17%" class="td1">
							合同编号
						</td>
						<td >
							<html:text name="checkQueryPlFnAF"
								property="checkQueryPlFnFindDTO.contractid" styleClass="input3" />
						</td>
						<td width="17%" class="td1">
							借款人姓名
						</td>
						<td width="33%">
							<html:text name="checkQueryPlFnAF"
								property="checkQueryPlFnFindDTO.borrowername"
								styleClass="input3" />
						</td>
					</tr>
					<tr>
						<td width="17%" class="td1">
							扣款账号
						</td>
						<td >
							<html:text name="checkQueryPlFnAF"
								property="checkQueryPlFnFindDTO.loankouacc" styleClass="input3" />
						</td>
						<td width="17%" class="td1">
							证件号码
						</td>
						<td align="center">
							<html:text name="checkQueryPlFnAF"
								property="checkQueryPlFnFindDTO.cardnum" styleClass="input3" />
						</td>
					</tr>
					
				</table>
				<table width="95%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="">
									<bean:message key="button.search" />
								</html:submit>
						</td>
					</tr>
				</table>
				</html:form>
				<html:form action="/checkQueryPlFnSureAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="179">
											<b class="font14">贷款信息列表</b>
										</td>
										<td width="680" height="22" align="center"
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
							<td align="center" class=td2></td>
							<td align="center" class=td2><a href="javascript:sort('p11.contract_id')">合同编号</a>
            <logic:equal name="pagination" property="orderBy" value="p11.contract_id">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
							
							<td align="center" class=td2><a href="javascript:sort('p11.loan_kou_acc')">扣款账号</a>
            <logic:equal name="pagination" property="orderBy" value="p11.loan_kou_acc">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
							<td align="center" class=td2><a href="javascript:sort('p10.borrower_name')">借款人姓名</a>
            <logic:equal name="pagination" property="orderBy" value="p10.borrower_name">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
							<td align="center" class=td2>证件号码</td>
							<td align="center" class=td2>贷款账</td>
							<td align="center" class=td2>放款银行</td>
							<td align="center" class=td2><a href="javascript:sort('p10.borrower_name')">贷款金额</a>
            <logic:equal name="pagination" property="orderBy" value="p10.borrower_name">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
							<td align="center" class=td2><a href="javascript:sort('p11.loan_time_limit')">贷款期限</a>
            <logic:equal name="pagination" property="orderBy" value="p11.loan_time_limit">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
							<td align="center" class=td2><a href="javascript:sort('p11.loan_mode')">还款方式</a>
            <logic:equal name="pagination" property="orderBy" value="p11.loan_mode">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
							<td align="center" class=td2>贷款余额</td>
							<td align="center" class=td2 style="display:none">呆账未回收金额</td>
							<td align="center" class=td2>挂账余额</td>
							<td align="center" class=td2 style="display:none">保证金余额</td>
							<td align="center" class=td2>总还本金</td>
							<td align="center" class=td2>总还利息</td>
							<td align="center" class=td2>总还罚息利息</td>
							<td align="center" class=td2>欠还本金</td>
							<td align="center" class=td2>欠还利息</td>
							<td align="center" class=td2>欠还罚息利息</td>
						</tr>
						<logic:notEmpty name="checkQueryPlFnAF" property="list">
			          <% int j=0;
						String strClass="";%>
							<logic:iterate name="checkQueryPlFnAF" property="list"
								id="elments" indexId="i"> 
					          <%j++;
								if (j%2==0) {strClass = "td8";
								}
							    else {strClass = "td9";
							    }%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i %>", idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>"
									onDblClick="">
									<td valign="top">
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="elments" property="contractid"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td valign="top">
										<bean:write name="elments" property="contractid" />
									</td>
									<td valign="top">
										<bean:write name="elments" property="loankouacc" />
									</td>
									<td valign="top">
										<bean:write name="elments" property="borrowername" />
									</td>
									<td valign="top">
										<bean:write name="elments" property="cardnum" />
									</td>
									
									<td valign="top">
										<a href="<%=path%>/sysloan/checkQueryPlFnSureAC.do?cardNum=<bean:write name="elments" property="cardnum"/>&contractid=<bean:write name="elments" property="contractid"/>&loankouacc=<bean:write name="elments" property="loankouacc"/>">贷款账</a>																					
									</td>
									
									<td valign="top">
										<bean:write name="elments" property="loanbankname" />
									</td>
									<td valign="top">
										<bean:write name="elments" property="loanmoney" />
									</td>
									<td valign="top">
										<bean:write name="elments" property="loanlimit" />
									</td>
									<td valign="top">
										<bean:write name="elments" property="temp_loanmode" />
									</td>
									<td valign="top">
										<bean:write name="elments" property="overplusloanmoney" />
									</td>
									<td valign="top" style="display:none">
										<bean:write name="elments" property="nobackmoney" />
									</td>
									<td valign="top">
										<bean:write name="elments" property="oveaerloanrepay" />
									</td>
									<td valign="top" style="display:none">
										<bean:write name="elments" property="ballbalance" />
									</td>
									<td valign="top">
										<bean:write name="elments" property="srealcorpus" />
									</td>
									<td valign="top">
										<bean:write name="elments" property="srealinterest" />
									</td>
									<td valign="top">
										<bean:write name="elments" property="srealpunishinterest" />
									</td>
									<td valign="top">
										<bean:write name="elments" property="owercorpus" />
									</td>
									<td valign="top">
										<bean:write name="elments" property="oweinterest" />
									</td>
									<td valign="top">
										<bean:write name="elments" property="owepunishinterest" />
									</td>

								</tr>
								<tr>
									<td colspan="20" class=td5></td>
								</tr>
							</logic:iterate>

						</logic:notEmpty>
						<logic:empty name="checkQueryPlFnAF" property="list">
							<tr>
								<td colspan="7" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>
							<tr>
								<td colspan="7"></td>
							</tr>
						</logic:empty>
					</table>

      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right">
														<jsp:include page="../../../inc/pagination.jsp">
															<jsp:param name="url" value="checkQueryPlFnShowAC.do" />
														</jsp:include></td>
              </tr>
            </table>
          </td>
	    </tr>
      </table>

				</html:form>
	</table>
</body>
</html:html>
