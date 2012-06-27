<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.action.OrgBaseInfoShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			OrgBaseInfoShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<script language="javascript">
	var oldColor="#ffffff";                            //原来的颜色
	var newColor="#E8FCFD";                     //要变成的颜色
	function chgBGColor(oTD){
		oldColor=oTD.style.backgroundColor;//保存当前颜色
		oTD.style.backgroundColor=newColor;//改变表格颜色
		newColor=oldColor;                 //改变下次要变成的颜色
	}
	function check(){
		var officeCode = document.forms[1].elements["officeCode"].value.trim();
		var collectionBankId = document.forms[1].elements["collectionBankId"].value.trim();
		var character = document.forms[1].elements["character"].value.trim();
		//var deptInCharge = document.forms[1].elements["deptInCharge"].value.trim();
		var id = document.forms[1].elements["id"].value.trim();
		var name = document.forms[1].elements["name"].value.trim();
		var openStatus = document.forms[1].elements["openStatus"].value.trim();
<%--		VAR PAYMODE = DOCUMENT.FORMS[1].ELEMENTS["PAYMODE"].VALUE.TRIM();--%>
<%--		VAR REGION = DOCUMENT.FORMS[1].ELEMENTS["REGION"].VALUE.TRIM();--%>
<%--		VAR OLDORGID = DOCUMENT.FORMS[1].ELEMENTS["OLDORGID"].VALUE.TRIM();--%>
<%--		VAR INSPECTOR = DOCUMENT.FORMS[1].ELEMENTS["INSPECTOR"].VALUE.TRIM();--%>
<%--		VAR PAYDATE = DOCUMENT.FORMS[1].ELEMENTS["PAYDATE"].VALUE.TRIM();--%>
<%--		VAR CODE = DOCUMENT.FORMS[1].ELEMENTS["CODE"].VALUE.TRIM();--%>
		var openDateSta = document.forms[1].elements["openDateSta"].value.trim();
		var openDateEnd = document.forms[1].elements["openDateEnd"].value.trim();
<%--		if(officeCode==""&& collectionBankId==""&& character==""&& deptInCharge==""&& id==""&&name==""&& openStatus==""&& payMode==""&& region==""&& oldOrgId==""&& inspector==""&& payDate==""&& code==""&& openDateSta==""&&openDateEnd==""){--%>
<%--			alert("最少要输入一个查询条件");--%>
<%--			return false;--%>
<%--		}--%>
		if(id.trim()!=""){//这个地方必须用这样的方法来取值
			if(isNaN(id.trim())){alert("请你输入正确的单位编号");return false;}
			if(id.trim().indexOf(".")!=-1){alert("单位编号不能有小数点");return false;}
			if(id.trim()<=0){alert("单位编号必须是正整数");return false;}
		}
		if(oldOrgId.trim()!=""){//这个地方必须用这样的方法来取值
			if(isNaN(oldOrgId.trim())){alert("请你输入正确的老单位编号");return false;}
			if(oldOrgId.trim().indexOf(".")!=-1){alert("老单位编号不能有小数点");return false;}
			if(oldOrgId.trim()<=0){alert("老单位编号必须是正整数");return false;}
		}
		if(payDate!=""){
			return checkDate(document.forms[1].elements["payDate"]);
		}
		if(openDateSta!=""){
			return checkDate(document.forms[1].elements["openDateSta"]);
		}
		if(openDateEnd!=""){
			return checkDate(document.forms[1].elements["openDateEnd"]);
		}
		return true;
	}
function skiporg() {
		var orgId = getCheckId();
    	var queryString = "<%=path%>/syscollection/querystatistics/baseinfosearch/orgcollinfo/orgCollInfoSkipOrgFlowAAC.do?orgid="+orgId;
		findInfo(queryString);
	}
function displayOrgFlow(){
		window.open('<%=path%>/syscollection/querystatistics/operationflow/orgoperationflow/showOrgbusinessflowListAC.do','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',resizable,scrollbars=yes');
	}
function skipemp(){
	var orgId = getCheckId();
	window.open('<%=path%>/syscollection/querystatistics/baseinfosearch/empbaseinfo/employeeInfoSearchAC.do?dto.orgId='+orgId,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',resizable,scrollbars=yes');
}		
</script>
	<title>统计查询单位信息列表</title>
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="orgBaseInfoShowAC.do" />
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
							&nbsp;
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">统计查询&gt;单位信息</font>
									</td>
									<td width="115" class=font14>
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
				<html:form action="/orgBaseInfoFindAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">基 本 信 息</b>
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
						cellpadding=0 align="center">
						<tr>
							<td width="17%" class="td1">
								办事处
							</td>
							<td width="33%">
								<html:select property="officeCode" styleClass="input4"
									name="infoList" onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								归集银行
							</td>
							<td width="33%">
								<html:select property="collectionBankId" styleClass="input4"
									name="infoList" onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="collBankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位性质
							</td>
							<td width="33%">
								<html:select style="input4" property="character"
									styleClass="input4" name="infoList"
									onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:optionsCollection property="characterMap" name="infoList"
										label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								主管部门
							</td>
							<td width="33%">
								<html:select style="input4" property="deptInCharge"
									styleClass="input4" name="infoList"
									onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:optionsCollection property="deptInChargeMap"
										name="infoList" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td width="33%">
								<html:text property="id" styleClass="input3" name="infoList"></html:text>
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="33%">
								<html:text property="name" styleClass="input3" name="infoList"></html:text>
							</td>
						</tr>
						
						<tr id="gjtr" >
							<td colspan="4">
								<table width="100%" border="0" align="center" cellpadding=0
									cellspacing=1 id="table1">
									
									<tr>
										<td width="17%" class="td1">
											单位状态
										</td>
										<td width="33%" align="center">
											<html:select style="input4" property="openStatus"
												styleClass="input4" name="infoList"
												onkeydown="enterNextFocus1();">
												<option value=""></option>
												<html:optionsCollection property="openStatusMap"
													name="infoList" label="value" value="key" />
											</html:select>
										</td>

										<td width="17%" class="td1" height="15">
											开户日期
										</td>
										<td width="15%" height="15">
											<html:text property="openDateSta" styleClass="input3"
												name="infoList"></html:text>
										</td>
										<td width="4%" height="15" align="center">
											至
										</td>
										<td width="14%" height="15">
											<html:text property="openDateEnd" styleClass="input3"
												name="infoList"></html:text>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<br>
<%--								<input type="button" class=buttona name="submit1" value="高级>>"--%>
<%--									onClick="gotoGaJi();" />--%>
								<html:submit styleClass="buttona" onclick="return check();">查询</html:submit>
						</tr>
					</table>
				</html:form>
				<html:form action="/orgBaseInfoTaMaintainAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：单位数:
								<u><bean:write name="pagination" property="nrOfElements" />&nbsp;</u>
								总人数:
								<u><bean:write name="infoList" property="personc" />&nbsp;</u>
							</td>
						</tr>
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">单 位 列 表 </b>
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
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
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
								<a href="javascript:sort('a001.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy" value="a001.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('b001.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="b001.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('b001.officecode')">办事处名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="b001.officecode">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('b001.collection_bank_id')">归集银行</a>
								<logic:equal name="pagination" property="orderBy"
									value="b001.collection_bank_id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								单位性质
							</td>
							<td align="center" class=td2>
								主管部门
							</td>
							<td align="center" class=td2>
								发薪银行
							</td>
							
							<td align="center" class=td2>
								单位经办人
							</td>
							<td align="center" class=td2>
								经办人电话
							</td>
							<td align="center" class=td2>
								经办人移动电话
							</td>
							
							<td align="center" class=td2>
								<a href="javascript:sort('b001.open_date')">开户日期</a>
								<logic:equal name="pagination" property="orderBy"
									value="b001.open_date">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								单位状态
							</td>
						</tr>
						<logic:notEmpty name="infoList" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="e" name="infoList" property="list" indexId="i">
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
										<bean:write name="e" property="id" format="0000000000" />
									</td>
									<td>
										<a href="#"
											onclick="window.open('orgDetailInfoAC.do?orgId=<bean:write name="e" property="id"/>','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return false;">
											<bean:write name="e" property="orgName" /> </a>
									</td>
									<td>
										<bean:write name="e" property="officecode" />
									</td>
									<td>
										<bean:write name="e"
											property="collectionBankId" />
									</td>
									<td>
										<bean:write name="e" property="character" />
									</td>
									<td>
										<bean:write name="e" property="deptInCharge" />
									</td>
									<td>
										<bean:write name="e" property="paybankName" />
									</td>
									
									<td>
										<bean:write name="e" property="transactorName" />
									</td>
									<td>
										<bean:write name="e" property="transactorTel" />
									</td>
									<td>
										<bean:write name="e"
											property="transactorMobile" />
									</td>
									
									<td>
										<bean:write name="e" property="openDate" />
									</td>
									<td>
										<bean:write name="e" property="openStatus" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="infoList" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合结果！
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
											<jsp:include page="../../../../inc/pagination.jsp">
												<jsp:param name="url" value="orgBaseInfoShowAC.do" />
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
										<logic:notEmpty name="infoList" property="list">
											<td width="70">
												<input type="button" class="buttona" value="职工信息"
													onclick="return skipemp()">
											</td>
											<td width="70">
												<input type="button" class="buttona" value="单位流水"
													onclick="skiporg();">
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.printone" />
												</html:submit>
											</td>
										</logic:notEmpty>
										<logic:empty name="infoList" property="list">
											<td width="70">
												<input type="button" class="buttona" value="职工信息"
													onclick="return skipemp()" disabled="true">
											</td>
											<td width="70">
												<input type="button" class="buttona" value="单位流水"
													onclick="return skip()" disabled="true">
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.printone" />
												</html:submit>
											</td>
										</logic:empty>
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
