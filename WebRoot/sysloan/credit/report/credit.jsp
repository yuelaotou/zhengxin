<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.credit.report.action.CreditShowAC"%>

<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(CreditShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>征信系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">

</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<script language="javascript">
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function checkFind(){
	var shujutiquriqi = document.creditAF.shujutiquriqi.value;
	if(shujutiquriqi ==null || shujutiquriqi==""){
		alert("数据提取日期不能为空");
		document.creditAF.shujutiquriqi.focus();
		return false;
	}
	return true;
}
function checkDelete(){
	var shujutiquriqi = document.creditAF.shujutiquriqi.value;
	if(shujutiquriqi ==null || shujutiquriqi==""){
		alert("数据提取日期不能为空");
		document.creditAF.shujutiquriqi.focus();
		return false;
	}else{
		return confirm("确认删除本月份数据吗？");
	}
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="creditShowAC.do" />
	</jsp:include>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="3%" align="right" valign="middle">
				<table width="21" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
				</table>
			</td>
			<td width="97%" align="left" valign="top">
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=path%>/img/table_left.gif" width="7" height="37">
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" width="10">
							&nbsp;
						</td>
						<td width="695" background="<%=path%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37" align="center" valign="top"
										style="PADDING-top: 7px"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
								</tr>
							</table>

						</td>
						<td width="255">
							<table width="255" border="0" cellspacing="0" cellpadding="0"
								align="right">
								<tr>
									<td width="56">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24" align="right">
									</td>
									<td width="169" class=font14 bgcolor="#FFFFFF" align="center"
										valign="middle">
										<font color="00B5DB">贷款征信系统&gt;上报查询</font>
									</td>
									<td width="30" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="18">
							<img src="<%=path%>/img/table_bg_line.gif" width="18" height="37">
						</td>
						<td width="9">
							<img src="<%=path%>/img/table_right.gif" width="9" height="37">
						</td>
					</tr>
				</table>
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" valign="top" class=td3>
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="207">
													<b class="font14">查 询 条 件</b>
												</td>
												<td width="716" height="22" align="center"
													background="<%=path%>/img/bg2.gif">
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<html:form action="/creditFindAC.do" method="post"
								style="margin: 0">
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=0 align="center">
									<tr>
										<td width="11%" class="td1">
											办事处
										</td>
										<td width="21%">
											<html:select name="creditAF" property="officeCode"
												styleClass="input3">
												<html:option value=""></html:option>
												<html:options collection="officeList" property="value"
													labelProperty="label" />
											</html:select>
										</td>
										<td width="11%" class="td1">
											放款银行
										</td>
										<td width="21%">
											<html:select name="creditAF" property="loanBankName"
												styleClass="input3">
												<html:option value=""></html:option>
												<html:options collection="loanBankNameList" property="value"
													labelProperty="label" />
											</html:select>
										</td>
										<td width="11%" class="td1">
											数据提取月份
											<font color="#FF0000">*</font>
										</td>
										<td width="21%">
											<html:text name="creditAF" property="shujutiquriqi"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
									</tr>
									<tr>
										<td width="11%" class="td1">
											数据状态
										</td>
										<td width="21%">
											<html:select name="creditAF" property="jiluzhuangtai"
												styleClass="input3">
												<html:option value=""></html:option>
												<html:option value="0">正常</html:option>
												<html:option value="1">删除</html:option>
											</html:select>
										</td>
										<td width="11%" class="td1">
											合同账号
										</td>
										<td width="21%">
											<html:text name="creditAF" property="yewuhao"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="11%" class="td1">
											报文生成日期
										</td>
										<td width="21%">
											<html:text name="creditAF" property="baowenshengchengriqi"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
									</tr>
								</table>
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="right">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkFind();">
												<bean:message key="button.search" />
											</html:submit>
											<html:submit property="method" styleClass="buttonc"
												onclick="return checkFind();">
												<bean:message key="button.credit.shengchengshuju" />
											</html:submit>
											<html:submit property="method" styleClass="buttonc"
												onclick="return checkDelete();">
												<bean:message key="button.credit.shanchubaowenshuju" />
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
												<td height="22" bgcolor="#CCCCCC" align="center" width="213">
													<b class="font14">征信信息列表</b>
												</td>
												<td width="710" height="22" align="center"
													background="<%=path%>/img/bg2.gif">
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>

							<html:form action="/creditMaintainAC.do" method="post"
								style="margin: 0;">
								<div
									style="overflow-x: auto; overflow-y: auto; height: 280px; width: 820;">
									<table width="2750" border="0" cellspacing="0" cellpadding="3"
										align="center">
										<tr bgcolor="1BA5FF">
											<td align="center" height="6" colspan="1"></td>
										</tr>
									</table>
									<table width="2750" border="0" bgcolor="#76BEE9"
										cellspacing="1" cellpadding="3" align="center">
										<tr align="center" bgcolor="C4F0FF">
											<td height="23" bgcolor="C4F0FF">
												&nbsp;
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.yewuhao')">合同编号</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.yewuhao">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.office')">办事处</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.office">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.loan_bank_id')">放款银行</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.loan_bank_id">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.kaihuriqi')">开户日期</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.kaihuriqi">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.daoqiriqi')">到期日期</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.daoqiriqi">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.daikuanjine')">贷款金额</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.daikuanjine">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.huankuanyueshu')">还款月数</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.huankuanyueshu">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.shengyuqixian')">剩余期限</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.shengyuqixian">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.yinghuankuanriqi')">应还款日期</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.yinghuankuanriqi">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.shijihuankuanriqi')">实际还款日期</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.shijihuankuanriqi">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.yinghuankuanjine')">应还款金额</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.yinghuankuanjine">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.shijihuankuanjine')">实际还款金额</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.shijihuankuanjine">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.daikuanyue')">贷款余额</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.daikuanyue">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.dangqianyuqiqishu')">当前逾期期数</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.dangqianyuqiqishu">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.dangqianyuqizonge')">当前逾期总额</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.dangqianyuqizonge">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.yuqiyigeyue')">逾期31-60天未归还贷款本金</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.yuqiyigeyue">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.yuqilianggeyue')">逾期61-90天未归还贷款本金</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.yuqilianggeyue">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.yuqisangeyue')">逾期91-180天未归还贷款本金</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.yuqisangeyue">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.yuqiliugeyue')">逾期180天以上未归还贷款本金</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.yuqiliugeyue">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.weiyuecishu')">违约次数</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.weiyuecishu">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.zuigaoyuqiqishu')">最高逾期期数</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.zuigaoyuqiqishu">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.zhanghuzhuangtai')">账户状态</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.zhanghuzhuangtai">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.ershisigeyue')">24个月（账户）还款状态</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.ershisigeyue">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.zhanghuxinxitishi')">账户拥有者信息提示</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.zhanghuxinxitishi">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.xingming')">姓名</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.xingming">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.zhengjianleixing')">证件类型</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.zhengjianleixing">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.zhengjianhaoma')">证件号码</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.zhengjianhaoma">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.shujutiquriqi')">数据提取月份</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.shujutiquriqi">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.baowenshengchengriqi')">报文生成日期</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.baowenshengchengriqi">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
											<td align="center" class=td2>
												<a href="javascript:sort('credit.jiluzhuangtai')">记录状态</a>
												<logic:equal name="pagination" property="orderBy"
													value="credit.jiluzhuangtai">
													<logic:equal name="pagination" property="orderother"
														value="ASC">▲</logic:equal>
													<logic:equal name="pagination" property="orderother"
														value="DESC">▼</logic:equal>
												</logic:equal>
											</td>
										</tr>
										<logic:notEmpty name="creditAF" property="list">
											<%
													int j = 0;
													String strClass = "";
											%>
											<logic:iterate name="creditAF" property="list" id="credit"
												indexId="i">
												<%
															j++;
															if (j % 2 == 0) {
																strClass = "td8";
															} else {
																strClass = "td9";
															}
												%>
												<tr align="left" id="tr<%=i%>" class="<%=strClass%>">
													<td>
														<html:multibox property="rowArray">
															<bean:write name="credit" property="yewuhao" />
														</html:multibox>
													</td>
													<td>
														<bean:write name="credit" property="yewuhao" />
													</td>
													<td>
														<bean:write name="credit" property="office" />
													</td>
													<td>
														<bean:write name="credit" property="loan_bank_id" />
													</td>
													<td>
														<bean:write name="credit" property="kaihuriqi" />
													</td>
													<td>
														<bean:write name="credit" property="daoqiriqi" />
													</td>
													<td>
														<bean:write name="credit" property="daikuanjine" />
													</td>
													<td>
														<bean:write name="credit" property="huankuanyueshu" />
													</td>
													<td>
														<bean:write name="credit" property="shengyuqixian" />
													</td>
													<td>
														<bean:write name="credit" property="yinghuankuanriqi" />
													</td>
													<td>
														<bean:write name="credit" property="shijihuankuanriqi" />
													</td>
													<td>
														<bean:write name="credit" property="yinghuankuanjine" />
													</td>
													<td>
														<bean:write name="credit" property="shijihuankuanjine" />
													</td>
													<td>
														<bean:write name="credit" property="daikuanyue" />
													</td>
													<td>
														<bean:write name="credit" property="dangqianyuqiqishu" />
													</td>
													<td>
														<bean:write name="credit" property="dangqianyuqizonge" />
													</td>
													<td>
														<bean:write name="credit" property="yuqiyigeyue" />
													</td>
													<td>
														<bean:write name="credit" property="yuqilianggeyue" />
													</td>
													<td>
														<bean:write name="credit" property="yuqisangeyue" />
													</td>
													<td>
														<bean:write name="credit" property="yuqiliugeyue" />
													</td>
													<td>
														<bean:write name="credit" property="weiyuecishu" />
													</td>
													<td>
														<bean:write name="credit" property="zuigaoyuqiqishu" />
													</td>
													<td>
														<bean:write name="credit" property="zhanghuzhuangtai" />
													</td>
													<td>
														<bean:write name="credit" property="ershisigeyue" />
													</td>
													<td>
														<bean:write name="credit" property="zhanghuxinxitishi" />
													</td>
													<td>
														<bean:write name="credit" property="xingming" />
													</td>
													<td>
														<bean:write name="credit" property="zhengjianleixing" />
													</td>
													<td>
														<bean:write name="credit" property="zhengjianhaoma" />
													</td>
													<td>
														<bean:write name="credit" property="shujutiquriqi" />
													</td>
													<td>
														<bean:write name="credit" property="baowenshengchengriqi" />
													</td>
													<td>
														<bean:write name="credit" property="jiluzhuangtai" />
													</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
										<logic:empty name="creditAF" property="list">
											<tr>
												<td colspan="29" height="30" style="color:red">
													没有找到与条件相符合的结果！
												</td>
											</tr>
										</logic:empty>
									</table>
								</div>
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr class="td1">
										<td align="center">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr align="center">
													<td align="left">
														共
														<bean:write name="pagination" property="nrOfElements" />
														项
													</td>
													<td align="right">
														<jsp:include page="/inc/pagination.jsp">
															<jsp:param name="url" value="creditShowAC.do" />
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
													<td width="120">
														<html:submit property="method" styleClass="buttonc">
															<bean:message key="button.credit.zhengchangshuju" />
														</html:submit>
													</td>
													<td width="120">
														<html:submit property="method" styleClass="buttonc">
															<bean:message key="button.credit.shanchushuju" />
														</html:submit>
													</td>
													<td width="120">
														<html:submit property="method" styleClass="buttonc">
															<bean:message key="button.credit.jiandujiancha" />
														</html:submit>
													</td>
													<td width="100">
														<html:submit property="method" styleClass="buttonb">
															<bean:message key="button.credit.shanchuhetong" />
														</html:submit>
													</td>
													<td width="100">
														<html:submit property="method" styleClass="buttonb">
															<bean:message key="button.credit.huifuhetong" />
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
			</td>
		</tr>
	</table>
</body>
</html:html>

