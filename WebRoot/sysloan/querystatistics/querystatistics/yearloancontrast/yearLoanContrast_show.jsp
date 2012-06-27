<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.action.YearLoanContrastShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			YearLoanContrastShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>年度贷款统计对照表</title>
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/picture.js"></script>
</head>
<script language="javascript" type="text/javascript">
function checkdate(){
    var date=document.all.year.value.trim();
    if(date!=""){
        var temp_ym = date.match(/^(([1][9]|[2][0])\d{2})$/); 
	   if(temp_ym==null){
		        	alert("请正确录入年月！格式如：2007");
				    return false;
	   }else{
		 return true;
	   }
     }else{
         alert("请录入查询年度！");
		 return false;
     }
}
function loads(){
 document.all.bizDate.value="";
 document.all.officeCode.value="";
}
</script>

<body bgcolor="#FFFFFF" text="#000000">
	<table width="1000" border="0" cellspacing="0" cellpadding="0"
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
							width="55">
							&nbsp;

						</td>
						<td width="235"
							background="<%=request.getContextPath()%>/img/table_bg_line.gif">
							&nbsp;

						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=request.getContextPath()%>/img/title_banner.gif"
											width="37" height="24">
									</td>
									<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">统计查询&gt;年度贷款统计对照表</font>
									</td>
									<td width="29" class=font14>
										&nbsp;

									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=request.getContextPath()%>/img/table_right.gif"
								width="9" height="37">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="199">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22"
										background="<%=request.getContextPath()%>/img/bg2.gif"
										align="center" width="749">
										&nbsp;

									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/yearLoanContrastFindAC.do">
					<table width="95%" border="0" align="center" cellpadding=0
						cellspacing=1 id="table1">
						<tr>
							<td width="17%" class="td1">
								年度
								<font color="#ff0000">*</font>
							</td>
							<td width="33%">
								<html:text property="year" name="yearLoanContrastAF"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="4" />
							</td>
							<td width="17%" class="td1">
								办事处
							</td>
							<td width="33%">
								<html:select name="yearLoanContrastAF" property="officeCode"
									styleClass="input4" onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:options collection="officlist" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="95%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td align="right">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkdate();">
												<bean:message key="button.search" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="3"
								align="center">
								<tr bgcolor="1BA5FF">
									<td align="center" height="6" colspan="1"></td>
								</tr>
							</table>
							<table width="100%" border="0" bgcolor="#76BEE9" cellspacing="1"
								cellpadding="3" align="center">
								<tr align="center" bgcolor="C4F0FF">

									<td rowspan="2" align="center" bgcolor="C4F0FF">
										月份
									</td>
									<td colspan="4" align="center" class=td2 bgcolor="C4F0FF">
										<bean:write name="yearLoanContrastAF" property="thisyear" />
										年
									</td>
									<td colspan="4" align="center" class=td2 bgcolor="C4F0FF">
										<bean:write name="yearLoanContrastAF" property="lastyear" />
										年
									</td>
									<td colspan="4" align="center" class=td2 bgcolor="C4F0FF">
										同比增长
									</td>
								</tr>
								<tr>
									<td align="center" class=td2>
										贷款发放户数
									</td>
									<td align="center" class=td2>
										贷款金额
									</td>
									<td align="center" class=td2>
										建筑面积
									</td>
									<td align="center" class=td2>
										房屋总价
									</td>
									<td align="center" class=td2>
										贷款发放户数
									</td>
									<td align="center" class=td2>
										贷款金额
									</td>
									<td align="center" class=td2>
										建筑面积
									</td>
									<td align="center" class=td2>
										房屋总价
									</td>
									<td align="center" class=td2>
										贷款发放户数
									</td>
									<td align="center" class=td2>
										贷款金额
									</td>
									<td align="center" class=td2>
										建筑面积
									</td>
									<td align="center" class=td2>
										房屋总价
									</td>
								</tr>
								<logic:notEmpty name="yearLoanContrastAF" property="list">
									<%
												int j = 0;
												String strClass = "";
									%>
									<logic:iterate id="element" name="yearLoanContrastAF"
										property="list" indexId="i">
										<%
												j++;
												if (j % 2 == 0) {
													strClass = "td8";
												} else {
													strClass = "td9";
												}
										%>
										<tr class="<%=strClass%>">
											<td>
												<bean:write name="element" property="month" />
											</td>
											<td>
												<bean:write name="element" property="b_ffhs" />
											</td>
											<td>
												<bean:write name="element" property="b_ffje" />
											</td>
											<td>
												<bean:write name="element" property="b_jjmj" />
											</td>
											<td>
												<bean:write name="element" property="b_ffzj" />
											</td>

											<td>
												<bean:write name="element" property="w_ffhs" />
											</td>
											<td>
												<bean:write name="element" property="w_ffje" />
											</td>
											<td>
												<bean:write name="element" property="w_jjmj" />
											</td>
											<td>
												<bean:write name="element" property="w_ffzj" />
											</td>

											<td>
												<bean:write name="element" property="t_ffhs" />
											</td>
											<td>
												<bean:write name="element" property="t_ffje" />
											</td>
											<td>
												<bean:write name="element" property="t_jjmj" />
											</td>
											<td>
												<bean:write name="element" property="t_ffzj" />
											</td>
										</tr>

									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="yearLoanContrastAF" property="list">
									<tr>
										<td colspan="16" height="10" style="color:red">
											没有找到与条件相符合的结果！
										</td>
									</tr>

								</logic:empty>
							</table>
						</td>
					</tr>
				</table>


				<html:form action="/yearLoanContrastPrintAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="110">
											<logic:notEmpty name="yearLoanContrastAF" property="list">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</logic:notEmpty>
											<logic:empty name="yearLoanContrastAF" property="list">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</logic:empty>
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


