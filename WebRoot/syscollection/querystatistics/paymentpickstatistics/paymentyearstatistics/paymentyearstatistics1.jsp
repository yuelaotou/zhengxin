<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.action.PaymentyearstatisticsShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			PaymentyearstatisticsShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>统计查询--缴存提取统计--公积金缴存情况年报表--市本级</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>

<script language="javascript" type="text/javascript">
function checkdate(){
    var date=document.all.bizDate.value.trim();
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

<body bgcolor="#FFFFFF" text="#000000" onload="loads();">
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
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">统计查询&gt;公积金缴存情况年报表</font>
									</td>
									<td width="115" class=font14>
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
				<html:form action="/paymentyearstatisticsFindAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="174">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/img/bg2.gif"
											align="center" width="746">
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
							<td width="17%" class="td1">
								办事处
							</td>
							<td>
								<html:select property="officeCode" styleClass="input4">
									<option value=""></option>
									<html:options collection="officeList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="5%"></td>
							<td width="17%" class="td1">
								日期
								<font color="red">*</font>
							</td>
							<td width="33%">
								<html:text property="bizDate" maxlength="4" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return checkdate();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="19%" height="29" align="center" bgcolor="#CCCCCC">
										<p align="center">
											<strong>公积金缴存情况年报表</strong>
										</p>
									</td>
									<td width="17%" align="center" bgcolor="#CCCCCC">
										<p align="center"></p>
									</td>
									<td width="34%" align="center" bgcolor="#CCCCCC">
										<strong></strong>
									</td>
									<td width="30%" align="center" bgcolor="#CCCCCC">
										<div align="right">
											<strong></strong>
										</div>
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
				<html:form action="/paymentyearstatisticsPrintAC.do">
					<table width="95%" border="0" cellspacing="1" cellpadding="0"
						align="center" bgcolor="1BA5FF">
						<tr align="center">
							<td rowspan="2" class="td1">
								&nbsp;
							</td>
							<td colspan="3" class="td1">
								农行
							</td>
							<td colspan="3" class="td1">
								中行
							</td>
							<td colspan="3" class="td1">
								工行
							</td>
							<td colspan="3" class="td1">
								建行
							</td>
							<td rowspan="2" class="td1">
								合计
							</td>
						</tr>
						<tr id="tr1" align="center" valign="middle" bgcolor="#FFFFFF">
							<td height="22">
								户数
							</td>
							<td height="22">
								人数
							</td>
							<td>
								金额
							</td>
							<td height="22">
								户数
							</td>
							<td height="22">
								人数
							</td>
							<td height="22">
								金额
							</td>
							<td height="22">
								户数
							</td>
							<td height="22">
								人数
							</td>
							<td>
								金额
							</td>
							<td height="22">
								户数
							</td>
							<td>
								人数
							</td>
							<td>
								金额
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" align="center">
							<td height="22">
								一月
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="nh_hs1" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_rs1" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_je1"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_hs1" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_rs1" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_je1"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="gh_hs1" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_rs1" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_je1"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="jh_hs1" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_rs1" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_je1"
									format="0.00" />
							</td>
							<td>
								<bean:write name="paymentyearstatisticsDTO" property="sum_1"
									format="0.00" />
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" align="center">
							<td height="22">
								二月
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="nh_hs2" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_rs2" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_je2"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_hs2" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_rs2" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_je2"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="gh_hs2" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_rs2" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_je2"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="jh_hs2" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_rs2" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_je2"
									format="0.00" />
							</td>
							<td>
								<bean:write name="paymentyearstatisticsDTO" property="sum_1"
									format="0.00" />
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" align="center">
							<td height="22">
								三月
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="nh_hs3" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_rs3" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_je3"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_hs3" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_rs3" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_je3"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="gh_hs3" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_rs3" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_je3"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="jh_hs3" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_rs3" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_je3"
									format="0.00" />
							</td>
							<td>
								<bean:write name="paymentyearstatisticsDTO" property="sum_3"
									format="0.00" />
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" align="center">
							<td height="22">
								四月
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="nh_hs4" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_rs4" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_je4"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_hs4" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_rs4" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_je4"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="gh_hs4" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_rs4" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_je4"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="jh_hs4" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_rs4" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_je4"
									format="0.00" />
							</td>
							<td>
								<bean:write name="paymentyearstatisticsDTO" property="sum_4"
									format="0.00" />
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" align="center">
							<td height="22">
								五月
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="nh_hs5" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_rs5" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_je5"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_hs5" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_rs5" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_je5"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="gh_hs5" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_rs5" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_je5"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="jh_hs5" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_rs5" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_je5"
									format="0.00" />
							</td>
							<td>
								<bean:write name="paymentyearstatisticsDTO" property="sum_5"
									format="0.00" />
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" align="center">
							<td height="22">
								六月
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="nh_hs6" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_rs6" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_je6"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_hs6" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_rs6" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_je6"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="gh_hs6" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_rs6" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_je6"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="jh_hs6" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_rs6" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_je6"
									format="0.00" />
							</td>
							<td>
								<bean:write name="paymentyearstatisticsDTO" property="sum_6"
									format="0.00" />
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" align="center">
							<td height="22">
								七月
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="nh_hs7" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_rs7" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_je7"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_hs7" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_rs7" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_je7"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="gh_hs7" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_rs7" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_je7"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="jh_hs7" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_rs7" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_je7"
									format="0.00" />
							</td>
							<td>
								<bean:write name="paymentyearstatisticsDTO" property="sum_7"
									format="0.00" />
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" align="center">
							<td height="22">
								八月
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="nh_hs8" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_rs8" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_je8"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_hs8" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_rs8" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_je8"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="gh_hs8" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_rs8" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_je8"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="jh_hs8" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_rs8" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_je8"
									format="0.00" />
							</td>
							<td>
								<bean:write name="paymentyearstatisticsDTO" property="sum_8"
									format="0.00" />
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" align="center">
							<td height="22">
								九月
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="nh_hs9" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_rs9" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_je9"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_hs9" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_rs9" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_je9"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="gh_hs9" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_rs9" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_je9"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="jh_hs9" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_rs9" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_je9"
									format="0.00" />
							</td>
							<td>
								<bean:write name="paymentyearstatisticsDTO" property="sum_9"
									format="0.00" />
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" align="center">
							<td height="22">
								十月
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="nh_hs10" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_rs10" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_je10"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_hs10" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_rs10" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_je10"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="gh_hs10" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_rs10" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_je10"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="jh_hs10" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_rs10" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_je10"
									format="0.00" />
							</td>
							<td>
								<bean:write name="paymentyearstatisticsDTO" property="sum_10"
									format="0.00" />
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" align="center">
							<td height="22">
								十一月
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="nh_hs11" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_rs11" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_je11"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_hs11" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_rs11" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_je11"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="gh_hs11" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_rs11" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_je11"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="jh_hs11" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_rs11" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_je11"
									format="0.00" />
							</td>
							<td>
								<bean:write name="paymentyearstatisticsDTO" property="sum_11"
									format="0.00" />
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" align="center">
							<td height="22">
								十二月
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="nh_hs12" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_rs12" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="nh_je12"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_hs12" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_rs12" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="zh_je12"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="gh_hs12" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_rs12" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="gh_je12"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="jh_hs12" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_rs12" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="jh_je12"
									format="0.00" />
							</td>
							<td>
								<bean:write name="paymentyearstatisticsDTO" property="sum_12"
									format="0.00" />
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" align="center">
							<td height="22">
								合计
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="sum_a" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="sum_b" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="sum_c"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="sum_d" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="sum_e" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="sum_f"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="sum_g" />
							</td>
							<td height="22" valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="sum_h" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="sum_i"
									format="0.00" />
							</td>
							<td height="22">
								<bean:write name="paymentyearstatisticsDTO" property="sum_j" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="sum_k" />
							</td>
							<td valign="middle">
								<bean:write name="paymentyearstatisticsDTO" property="sum_l"
									format="0.00" />
							</td>
							<td>
								<bean:write name="paymentyearstatisticsDTO" property="sum_m"
									format="0.00" />
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="10" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>

										<logic:equal name="paymentyearstatisticsDTO" property="key"
											value="key">
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</logic:equal>
										<logic:notEqual name="paymentyearstatisticsDTO" property="key"
											value="key">
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</logic:notEqual>

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
