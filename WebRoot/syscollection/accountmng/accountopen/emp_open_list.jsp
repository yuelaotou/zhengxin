<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@page
	import="org.xpup.hafmis.syscollection.accountmng.accountopen.action.EmpOpenShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = session
			.getAttribute(EmpOpenShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String openstatus = (String) request.getSession().getAttribute(
			"openstatus");
	Integer count = (Integer) request.getAttribute("count");
	String temp_flag = "0";
%>
<script type="text/javascript">
	function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	}
</script>
<html:html>
<head>
	<title>开户销户&gt;&gt;职工开户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js" />


</head>
<script language="javascript" src="js/common.js">
</script>
<script>
var s1="";
var s2="";
var s3="";
var s4="";
var s5="";
var s6="";
var s7="";
function loads(){
	var isorgOrCenter="<%=request.getSession().getAttribute("isorgOrCenter")%>";
	var ispickuptype=document.all.isType.value;
	var count = "<bean:write name="pagination" property="nrOfElements"/>";
	if(isorgOrCenter==2){
		var flag_=<%=temp_flag%>;
		if(flag_=='1'){
			if(count==0){
				document.all.disp.disabled="";
			}else{
				document.all.disp.disabled="true";
			}
	}
	}
	for(i=0;i<document.all.length;i++){//固定写法
		if(document.all.item(i).type=="submit"){//固定写法
			if(document.all.item(i).value=="批量导出"){//判断此按钮的值
				s1=i;
			}
			if(document.all.item(i).value=="批量导入"){
				s2=i;
			}
			if(document.all.item(i).value=="添加"){
				s3=i;
			}
			if(document.all.item(i).value=="修改"){
				s4=i;
			}
			if(document.all.item(i).value=="删除"){
				s5=i;
			}
			if(document.all.item(i).value=="全部删除"){
				s6=i;
			}
			if(document.all.item(i).value=="返回"){
				s7=i;
			}
            if(isorgOrCenter==1){
				if(document.all.item(i).value=="提交数据"){
					s8=i;
				}
				if(document.all.item(i).value=="撤销提交数据"){
					s9=i;
				}
				if(document.all.item(i).value=="修改职工编号"){
					s10=i;
				}
			}
		}
	}    
	if(isorgOrCenter==1){
	  	if(ispickuptype==2){
	  		document.all.item(s8).disabled="";
	  		document.all.item(s9).disabled="true";
	  	}
  		if(ispickuptype==0){
	  		document.all.item(s8).disabled="true";
	  		document.all.item(s9).disabled="";
	  	}
	  	if(ispickuptype==1){
	  		document.all.item(s8).disabled="true";
	  		document.all.item(s9).disabled="true";
	  	}
	  	if(<%=count%>==0){
  		 	document.all.item(s8).disabled="true";
	   		document.all.item(s9).disabled="true";
	   		document.all.item(s10).disabled="true";
	  	}
	}
	
	if(<%=openstatus%>==1&&count==0){
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="true";
		document.all.item(s7).disabled="";
	}else if(count!=0&&<%=openstatus%>==1){
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="";
		document.all.item(s5).disabled="";
		document.all.item(s6).disabled="";
		document.all.item(s7).disabled="";
		if(<%=count%>==0){
			document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
			document.all.item(s3).disabled="";
			document.all.item(s4).disabled="true";
			document.all.item(s5).disabled="true";
			document.all.item(s6).disabled="true";
			document.all.item(s7).disabled="";
		}
	}
	update();
}

function checkDetele(){
	var x=confirm("确定删除记录?");
		if(x){ 
		  	return true;
		}else{
		  return false;
		}
}

function checkDeteleAll(){
	var x=confirm("确定全部删除记录?");
		if(x){ 
		  	return true;	
		}else{
		  return false;
		}
}
function update(){
	if(<%=openstatus%>==2){
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="true";
		document.all.item(s7).disabled="";
		if(<%=count%>==0){
			document.all.item(s4).disabled="true";
		}
	}
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();reportErrors();"
	　onContextmenu="return false">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="employee_kh_show.do" />
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
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="171" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">开户销户&gt;单位开户</font>
									</td>
									<td width="92" class=font14>
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

				<html:form action="/employee_kh_find" styleClass="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="13%" class="td1">
								单位编号
							</td>
							<td width="18%">
								<input name="orgId" type="text" id="orgId" class="input3"
									readonly="readonly"
									value='<bean:write name="org.id" format="000000"/>'>
							</td>
							<td width="11%" class="td1">
								单位名称
							</td>
							<td width="21%" colspan="3">
								<input name="orgName" type="text" id="orgName" class="input3"
									readonly="readonly" value='<bean:write name="org.name"/>'>

							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								职工编号
							</td>
							<td width="18%">
								<html:text name="empkhAF" property="empid" styleClass="input3"
									onkeydown="enterNextFocus1();" styleId="txtsearch"></html:text>

							</td>
							<td width="11%" class="td1">
								职工姓名
							</td>
							<td width="21%">
								<html:text name="empkhAF" property="name" styleClass="input3"
									onkeydown="enterNextFocus1();" styleId="txtsearch"></html:text>
							</td>
							<td width="12%" class="td1">
								身份证号
							</td>
							<td width="25%">
								<html:text name="empkhAF" property="cardNumber"
									styleClass="input3" onkeydown="enterNextFocus1();"
									styleId="txtsearch"></html:text>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit styleClass="buttona">查询</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td class=h4>
							人数
							<u>：<bean:write name="pagination" property="nrOfElements" /> </u> 工资基数
							<u>：<bean:write name="empkhAF" property="salaryBaseCount" />
							</u> 单位缴额
							<u>：<bean:write name="empkhAF" property="orgpaySumCount" />
							</u> 职工缴额
							<u>：<bean:write name="empkhAF" property="emppaySumCount" />
							</u> 总缴额
							<u>：<bean:write name="empkhAF" property="sumCount" /> </u>
						</td>
					</tr>
				</table>

				<html:form action="/employee_kh_maintain" style="margin: 0">
					<html:hidden name="empkhAF" property="isType"></html:hidden>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">

								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">职 工 列 表</b>
										</td>
										<td width="826" height="22" align="center"
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

					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy" value="empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('empInfo.name')">职工姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="empInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								证件号码
							</td>

							<td align="center" class=td2>
								<a href="javascript:sort('emp.salaryBase')">工资基数</a>
								<logic:equal name="pagination" property="orderBy"
									value="emp.salaryBase">
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
							<td align="center" class=td2>
								缴额合计
							</td>

						</tr>
						<logic:notEmpty name="employees">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="employees" name="employees" indexId="i">
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
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="employees" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="employees" property="empId" format="000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="employees" property="empInfo.name" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="employees" property="empInfo.cardNum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="employees" property="salaryBase"
												format="0.00" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="employees" property="orgPay" format="0.00" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="employees" property="empPay" format="0.00" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="employees" property="empPayCount"
												format="0.00" />
										</p>
									</td>


								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="employees">
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
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="employee_kh_show.do" />
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
										<security:orghave>
											<%
												temp_flag = "1";
											%>
											<security:orgcannot>
												<td width="110">
													<html:submit property="method" styleClass="buttonc"
														styleId="disp">
														<bean:message key="button.pickup.data" />
													</html:submit>
												</td>
											</security:orgcannot>
											<security:orgcan>
												<td width="90">
													<html:submit property="method" styleClass="buttonb">
														<bean:message key="button.change.empid" />
													</html:submit>
												</td>
											</security:orgcan>
										</security:orghave>
										<logic:notEmpty name="employees">
											<security:orgcannot>
												<td width="90">
													<html:submit property="method" styleClass="buttonb">
														<bean:message key="button.empAgent.export" />
													</html:submit>
												</td>
												<td width="90">
													<html:submit property="method" styleClass="buttonb">
														<bean:message key="button.empAgent.import" />
													</html:submit>
												</td>
											</security:orgcannot>
										</logic:notEmpty>
										<logic:empty name="employees">
											<security:orgcannot>
												<td width="90">
													<html:submit property="method" styleClass="buttonb"
														disabled="true">
														<bean:message key="button.empAgent.export" />
													</html:submit>
												</td>
												<td width="90">
													<html:submit property="method" styleClass="buttonb"
														disabled="true">
														<bean:message key="button.empAgent.import" />
													</html:submit>
												</td>
											</security:orgcannot>
										</logic:empty>
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.exports" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.imports" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.add" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.update" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkDetele();">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkDeteleAll();">
												<bean:message key="button.deleteall" />
											</html:submit>
										<td width="90">
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.print.list.open" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.back" />
											</html:submit>
											<security:orghave>
												<security:orgcan>
													<td width="70">
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.referring.data" />
														</html:submit>
													</td>
													<td width="70">
														<html:submit property="method" styleClass="buttonb">
															<bean:message key="button.pproval.data" />
														</html:submit>
													</td>
												</security:orgcan>
											</security:orghave>
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
