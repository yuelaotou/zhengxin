<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.action.EmployeeInfoListShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			EmployeeInfoListShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
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
function check(from){
	var orgId = document.employeeInfoSearchAF.elements["dto.orgId"].value.trim();
	var orgName = document.employeeInfoSearchAF.elements["dto.orgName"].value.trim();
	var empId = document.employeeInfoSearchAF.elements["dto.empId"].value.trim();
	var empName = document.employeeInfoSearchAF.elements["dto.empName"].value.trim();
	var identityCard = document.employeeInfoSearchAF.elements["dto.identityCard"].value.trim();
	var salaryStart = document.employeeInfoSearchAF.elements["dto.salaryStart"].value.trim();
	var salaryEnd = document.employeeInfoSearchAF.elements["dto.salaryEnd"].value.trim();
	var orgPayStart = document.employeeInfoSearchAF.elements["dto.orgPayStart"].value.trim();
	var orgPayEnd = document.employeeInfoSearchAF.elements["dto.orgPayEnd"].value.trim();
	var empPayStart = document.employeeInfoSearchAF.elements["dto.empPayStart"].value.trim();
	var empPayEnd = document.employeeInfoSearchAF.elements["dto.empPayEnd"].value.trim();
	var payState = document.employeeInfoSearchAF.elements["dto.payState"].value.trim();
	var sex = document.employeeInfoSearchAF.elements["dto.sex"].value.trim();
	
	if(salaryStart.trim()!="" && salaryEnd.trim()==""){alert("查询工资基数的时候请你输入两个工资基数");return false;}
	if(salaryStart.trim()=="" && salaryEnd.trim()!=""){alert("查询工资基数日期的时候请你输入工资基数");return false;}
	if(orgPayStart.trim()!="" && orgPayEnd.trim()==""){alert("查询单位缴额的时候请你输入两个单位缴额");return false;}
	if(orgPayStart.trim()=="" && orgPayEnd.trim()!=""){alert("查询单位缴额的时候请你输入两个单位缴额");return false;}
	if(empPayStart.trim()!="" && empPayEnd.trim()==""){alert("查询职工缴额的时候请你输入两个职工缴额");return false;}
	if(empPayStart.trim()=="" && empPayEnd.trim()!=""){alert("查询职工缴额的时候请你输入两个职工缴额");return false;}
<%--	if(orgId==""&& orgName==""&& empId==""--%>
<%--		&& empName==""&& identityCard==""&&salaryStart==""--%>
<%--		&& salaryEnd==""&& orgPayStart==""--%>
<%--		&& orgPayEnd==""&& empPayStart==""--%>
<%--		&& empPayEnd==""&& payState==""&& sex==""){--%>
<%--		alert("最少要输入一个查询条件");--%>
<%--		return false;--%>
<%--	}--%>
	if(orgId.trim()!=""){//这个地方必须用这样的方法来取值
		if(isNaN(orgId.trim())){alert("请你输入正确的单位编号");return false;}
		if(orgId.trim().indexOf(".")!=-1){alert("单位编号不能有小数点");return false;}
		if(orgId.trim()<=0){alert("单位编号必须是正整数");return false;}
	}
	if(empId.trim()!=""){//这个地方必须用这样的方法来取值
		if(isNaN(empId.trim())){alert("请你输入正确的职工编号");return false;}
		if(empId.trim().indexOf(".")!=-1){alert("职工编号不能有小数点");return false;}
		if(empId.trim()<=0){alert("职工编号必须是正整数");return false;}
	}
	//if(identityCard!=""){
		//return isIdCardNo(identityCard);
	//}
	if(salaryStart!=""){
		return checkMoney(salaryStart);
	}
	if(salaryEnd!=""){
		return checkMoney(salaryEnd);
	}
	if(orgPayStart!=""){
		return checkMoney(orgPayStart);
	}
	if(orgPayEnd!=""){
		return checkMoney(orgPayEnd);
	}
	if(empPayStart!=""){
		return checkMoney(empPayStart);
	}
	if(empPayEnd!=""){
		return checkMoney(empPayEnd);
	}
	return true;
}
var orgid;
var tr='tr0';
function gettr(trindex) {
	tr=trindex;
  	orgid=document.getElementById(tr).childNodes[1].innerHTML;
}
function skip(){
	var orgId;
	var empId = getCheckId();
	if(tr=='tr0')
	{
		orgId=document.getElementById(tr).childNodes[1].innerHTML;
	}
	else{
		orgId=orgid;
	}
	window.open('<%=path%>/syscollection/querystatistics/operationflow/empoperationflow/empOperationFlowTaForwardURLAC.do?empId='+empId+'&orgId='+orgId,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',resizable,scrollbars=yes');
}
</script>
	<title>统计查询职工信息列表</title>
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="employeeInfoListShowAC.do" />
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
										<font color="00B5DB">统计查询&gt;职工信息</font>
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
				<html:form action="/employeeInfoSearchAC.do"
					onsubmit="return check()">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="13%" class="td1">
								单位编号
							</td>
							<td width="20%">
								<html:text property="dto.orgId" styleClass="input3"
									name="infoList" onkeydown="enterNextFocus1();"></html:text>
							<td width="13%" class="td1">
								单位名称
							</td>
							<td colspan="3">
								<html:text property="dto.orgName" styleClass="input3"
									name="infoList" onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								职工编号
							</td>
							<td width="20%">
								<html:text property="dto.empId" styleClass="input3"
									name="infoList" onkeydown="enterNextFocus1();"></html:text>
							<td width="13%" class="td1">
								职工姓名
							</td>
							<td width="21%">
								<html:text property="dto.empName" styleClass="input3"
									name="infoList" onkeydown="enterNextFocus1();"></html:text>
							<td width="13%" class="td1">
								身份证号
							</td>
							<td width="20%">
								<html:text property="dto.identityCard" styleClass="input3"
									name="infoList" onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr id="gjtr" style="display:none">
							<td width="63%">
								<table width="100%" border="0" align="center" cellpadding=0
									cellspacing=1 id="table1">
									<tr>
										<td width="13%" class="td1">
											工资基数
										</td>
										<td width="8%" align="center">
											<html:text property="dto.salaryStart" styleClass="input3"
												name="infoList"></html:text>
										</td>
										<td width="4%" align="center">
											至
										</td>
										<td width="8%" align="center">
											<html:text property="dto.salaryEnd" styleClass="input3"
												name="infoList"></html:text>
										</td>
										<td width="13%" class="td1">
											单位缴额
										</td>
										<td width="8%" align="center">
											<html:text property="dto.orgPayStart" styleClass="input3"
												name="infoList"></html:text>
										</td>
										<td width="5%" align="center">
											至
										</td>
										<td width="8%" align="center">
											<html:text property="dto.orgPayEnd" styleClass="input3"
												name="infoList"></html:text>
										</td>
										<td width="13%" class="td1">
											职工缴额
										</td>
										<td width="8%" align="center">
											<html:text property="dto.empPayStart" styleClass="input3"
												name="infoList"></html:text>
										</td>
										<td width="4%" align="center">
											至
										</td>
										<td width="8%" align="center">
											<html:text property="dto.empPayEnd" styleClass="input3"
												name="infoList"></html:text>
										</td>
									</tr>
									<tr>
										<td width="13%" class="td1">
											性别
										</td>
										<td width="20%" colspan="3" align="center">
											<html:select style="input4" property="dto.sex"
												styleClass="input4" name="infoList">
												<option value=""></option>
												<html:optionsCollection property="sex" name="infoList"
													label="value" value="key" />
											</html:select>
										</td>
										<td width="13%" class="td1">
											职工状态
										</td>
										<td width="21%" colspan="3">
											<html:select property="dto.payState" name="infoList"
												styleClass="input4" style="input4">
												<option></option>
												<html:optionsCollection property="payState" name="infoList"
													label="value" value="key" />
											</html:select>
										</td>
										<td width="13%" class="td1">
											职工余额
										</td>
										<td width="21%" colspan="3">
											<html:select property="dto.empBalance" name="infoList"
												styleClass="input4" style="input4">
												<html:option value=""></html:option>
												<html:option value="morethan">余额大于0</html:option>
											</html:select>
										</td>
										<%--
                  <td width="13%" class="td1" >是否存在贷款</td>
                  <td width="20%" colspan="3" >			
                  <html:select property="dto.loanYesNo" name="infoList" styleClass="input4">
	                <html:option value=""></html:option>
					<html:option value="0">是</html:option>
					<html:option value="1">否</html:option>
				  </html:select></td>
                --%>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<input type="button" class=buttona name="submit1" value="高级>>"
									onClick="gotoGaJi();" />
								<html:submit styleClass="buttona">查询</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td class=h4>
							总人数合计
							<u>: <bean:write name="infoList" property="sumPerson"
									format="#.###" /> </u> 工资基数合计
							<u>: <bean:write name="infoList" property="sumSalaryBase"
									format="#.###" /> </u> 单位缴额合计
							<u>: <bean:write name="infoList" property="sumOrgPay"
									format="#.###" /> </u> 职工缴额合计
							<u>: <bean:write name="infoList" property="sumEmpPay"
									format="#.###" /> </u> 总缴额合计
							<u>: <bean:write name="infoList" property="sumMoney"
									format="#.###" /> </u>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="117">
										<b class="font14">职 工 列 表 </b>
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
				<html:form action="/empBaseInfoTaMaintainAC.do">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('e.org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="e.org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('e.empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="e.empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('e.empInfo.name')">职工姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="e.empInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								证件号码
							</td>
							<td align="center" class=td2>
								工资基数
							</td>
							<td align="center" class=td2>
								单位缴额
							</td>
							<td align="center" class=td2>
								职工缴额
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('e.empPayCount')">缴额合计</a>
								<logic:equal name="pagination" property="orderBy"
									value="e.empPayCount">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('e.payStatus')">职工状态</a>
								<logic:equal name="pagination" property="orderBy"
									value="e.payStatus">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('e.orgPayMonth')">单位缴至年月</a>
								<logic:equal name="pagination" property="orderBy"
									value="e.orgPayMonth">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('e.empPayMonth')">个人缴至年月</a>
								<logic:equal name="pagination" property="orderBy"
									value="e.empPayMonth">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								职工余额
							</td>
							<td align="center" class=td2>
								年提取次数
							</td>
							<td align="center" class=td2>
								年提取金额
							</td>
							<td align="center" class=td2>
								贷款状态
							</td>
							<td align="center" class=td2>
								最大支取金额
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
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>",idAF);gettr("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
									<td>
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="e" property="empid"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										<input id="s<%=i%>" type="hidden" name="iid"
											value="<bean:write name="e" property="orgid"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td>
										<bean:write name="e" property="orgid" format="0000000000" />
									</td>
									<td>
										<bean:write name="e" property="empid" format="000000" />
									</td>
									<td>
										<a href="#"
											onclick="window.open('statEmployeeInfoAC.do?empId=<bean:write name="e" property="empid"/>&orgId=<bean:write name="e" property="orgid"/>','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return false;">
											<bean:write name="e" property="empname" /> </a>
									</td>
									<td>
										<bean:write name="e" property="cardnum" />
									</td>
									<td>
										<bean:write name="e" property="salarybase" />
									</td>
									<td>
										<bean:write name="e" property="orgpay" />
									</td>
									<td>
										<bean:write name="e" property="emppay" />
									</td>
									<td>
										<bean:write name="e" property="paycount" />
									</td>
									<td>
										<bean:write name="e" property="paystatus" />
									</td>
									<td>
										<bean:write name="e" property="orgpaymonth" />
									</td>
									<td>
										<bean:write name="e" property="emppaymonth" />
									</td>
									<td>
										<bean:write name="e" property="balance" />
									</td>
									<td>
										<bean:write name="e" property="pickcount" />
									</td>
									<td>
										<bean:write name="e" property="pickmoney" />
									</td>
									<td>
										<bean:write name="e" property="loanYesNo" />
									</td>
									<td>
										<bean:write name="e" property="maxPickMoney" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="infoList" property="list">
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
											<jsp:include page="../../../../inc/pagination.jsp">
												<jsp:param name="url" value="employeeInfoListShowAC.do" />
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
												<input type="button" class="buttona" value="职工流水"
													onclick="return skip()">
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
												<input type="button" class="buttona" value="职工流水"
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
