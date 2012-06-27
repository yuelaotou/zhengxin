<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.pickupmng.pickup.action.VindicatePickEmployeeListAction"%>
<%@ page
	import="org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickupGetCompanyIdAF"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			VindicatePickEmployeeListAction.EMPLOYEELIST);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();

	PickupGetCompanyIdAF result = (PickupGetCompanyIdAF) request
			.getSession().getAttribute("orgList");
%>
<html>
<head>
	<title>提取管理</title>
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
</head>
<script language="javascript" type="text/javascript">
var tr="tr0";
var s1="";//代表按钮 几个按钮几个变量
var s2="";
var query;
function print(){
 	query = 'printEmployeeAC.do?';
 	printreal(query);
		//document.URL='printEmployeeAC.do?';
}
function printpz(){
	var empid=update();
 	query ='printEmployeeAC.do?empid='+empid;
	printreal(query);
}
function printreal(query){
    document.URL=query;
}
function excHz1(url){
 	var l=window.screen.width ;
  	var w= window.screen.height; 
  	var al=l/2-350;
  	var aw=w/2-350;
  	var ur="../../../syscommon/picture/browse.jsp?path="+url;
  	var newwin=window.open(ur,"homeWin","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=800,height=600,top="+aw+",left="+al+"");
  	newwin.focus();
}

function gettr(trindex) {//点单选行的时候 触发这个函数
 	tr=trindex;//一个变量
 	update();
}

function loads(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			//必须对应按钮的顺序
			if(document.all.item(i).value=="凭证打印"){
				s1=i;
			}
			if(document.all.item(i).value=="扫描"){
				s2=i;
			}
		}
	}
	update();
	var initiaButton = "<%=result.getButtonState()%>"
		if(initiaButton == '1'){
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
	
	}else if(initiaButton == '2'){
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		
	}
	
	if(initiaButton==null || initiaButton==""){
	 	document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
	}
	 
}
function update() {
	var empid=document.getElementById(tr).cells[1].innerHTML;
	return empid;
}
	
</script>
	<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
		onContextmenu="return false">
		<jsp:include page="/syscommon/picture/progressbar.jsp" />
		<jsp:include page="../../../inc/sort.jsp">
			<jsp:param name="url" value="windowMaintainAC.do" />
		</jsp:include>
		<html:form action="/windowMaintainAC.do">

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
											<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
												valign="bottom">
												<font color="00B5DB">提取管理&gt;办理提取</font>
											</td>
											<td width="74" class=font14>
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
												<b class="font14">提 取 信 息</b>
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

								<td width="17%" class="td1">
									单位编号
									<span class="STYLE1"></span>
								</td>
								<td>
									<html:text property="id" name="orgList" styleClass="input3"
										readonly="true"></html:text>
									<html:hidden name="orgList" property="buttonState" />
								</td>
								<td class="td1" width="17%">
									单位名称
									<span class="STYLE1"></span>
								</td>
								<td width="33%">
									<html:text property="name" name="orgList" styleClass="input3"
										readonly="true"></html:text>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									结算号
									<span class="STYLE1"></span>
								</td>
								<td>
									<html:text property="noteNumber" styleClass="input3"
										name="orgList" readonly="true"></html:text>
								</td>
								<td width="17%" class="td1">
									凭证编号
									<span class="STYLE1"></span>
								</td>
								<td width="33%">
									<html:text property="docNumber" name="orgList"
										styleClass="input3" readonly="true"></html:text>
								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td class=h4>
									合计：部分提取人数
									<u>: <bean:write name="orgList" property="somePickupNumber" />
									</u> 销户提取人数
									<u>: <bean:write name="orgList"
											property="distoryPickupNumber" /> </u> 提取总人数
									<u>: <bean:write name="orgList" property="sumPerson" /> </u>提取金额
									<u>: <bean:write name="orgList" property="sumBalance" /> </u>
									<security:orgcannot> 销户利息<u>: <bean:write
												name="orgList" property="sumInterese" /> </u> 提取总金额<u>: <bean:write
												name="orgList" property="sumTotal" /> </u>
									</security:orgcannot>
								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="154">
												<b class="font14">办理提取列表</b>
											</td>
											<td width="750" height="22" align="center"
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
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr>
								<td width="4%" height="23" align="center" bgcolor="C4F0FF">
									&nbsp;
								</td>
								<td height="23" align="center" class=td2>
									<a href="javascript:sort('pt.empId')">职工编号</a>
									<logic:equal name="pagination" property="orderBy"
										value="pt.empId">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('pt.empName')">职工姓名</a>
									<logic:equal name="pagination" property="orderBy"
										value="pt.empName">
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
									<a href="javascript:sort('pt.pickSalary')">提取金额</a>
									<logic:equal name="pagination" property="orderBy"
										value="pt.pickSalary">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<security:orgcannot>
									<td align="center" class=td2>
										销户利息
									</td>
									<td align="center" class=td2>
										提取总额
									</td>
								</security:orgcannot>
								<td align="center" class=td2>
									提取原因
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('pt.pickType')">提取类型</a>
									<logic:equal name="pagination" property="orderBy"
										value="pt.pickType">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									房照号
								</td>
								<td align="center" class=td2>
									浏览附件
								</td>
							</tr>
							<logic:notEmpty name="orgList" property="list">
								<logic:iterate id="a" property="list" name="orgList" indexId="i">

									<tr id="tr<%=i%>"
										onclick='gotoClick("tr<%=i%>","s<%=i%>", pickGetEmployeeInfoAF);gettr("tr<%=i%>");'
										onMouseOver='this.style.background="#eaeaea"'
										onMouseOut='gotoColor("tr<%=i%>","s<%=i%>", pickGetEmployeeInfoAF);'
										class=td4 onDblClick="">
										<td>
											<input id="s<%=i%>" type="radio" name="iid"
												value="<bean:write name="a" property="empId"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>

										</td>

										<td valign="top">
											<bean:write name="a" property="empId" />
										</td>
										<td valign="top">
											<bean:write name="a" property="emp.empInfo.name" />
											<br>
										</td>
										<td valign="top">
											<bean:write name="a" property="emp.empInfo.cardNum" />
											<br>
										</td>
										<td valign="top">
											<bean:write name="a" property="pickSalary" />
											<br>
										</td>
										<security:orgcannot>
											<td valign="top">
												<bean:write name="a" property="pickInterest" />
												<br>
											</td>
											<td valign="top">
												<bean:write name="a" property="total" />
												<br>
											</td>
										</security:orgcannot>
										<td valign="top">
											<bean:write name="a" property="reason" />
											<br>
										</td>
										<td valign="top">
											<bean:write name="a" property="pickDisplayType" />
											<br>
										</td>
										<td valign="top">
											<bean:write name="a" property="reserveaA" />
											<br>
										</td>
										<td>
											<a
												href='javascript:excHz1("<bean:write name="a" property="photourl"/>");'><img
													src="<%=path%>/img/lookinfo.gif" width="37" height="24">
											</a>
										</td>
									</tr>
									<tr>
										<td colspan="11" class=td5></td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="orgList" property="list">
								<tr>
									<td colspan="11" height="30" style="color:red">
										没有找到与条件相符合结果!
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
													<jsp:param name="url" value="vindicatePickEmployeeList.do" />
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
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print.doc" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.scan" />
												</html:submit>
											</td>
											<td width="70">
												<input type="button" name="Submit22" value="关闭"
													onClick="javascript:window.close();" class="buttona">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
			</table>
		</html:form>
	</body>
</html>
