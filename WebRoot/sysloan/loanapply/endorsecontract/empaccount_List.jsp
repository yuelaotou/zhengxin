 <%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action.ShowEmpAccountListAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ShowEmpAccountListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>

<html:html>
<head>
	<title>tranin</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	
	<script language="javascript" src="js/common.js">
</head>
<script language="javascript" ></script>
	<script language="javascript" type="text/javascript">
var tr="tr0"; 
function loads(){
var	orgId=document.empAccountAF.orgIdaa101.value.trim();
if(orgId!=null&&orgId!=''){
	var startDate=document.empAccountAF.startDate.value.trim();
	var lastDate = document.empAccountAF.lastDate.value.trim();
document.empAccountAF.orgIdaa101.value=(format(orgId)+""+orgId);
}
}
function checkNumber(thisNumber){
var salarybase = thisNumber.match(/^([0-9]{1,10})?$/)
if (salarybase==null)
			{	
        		alert("请正确录入编号！格式如：123456");
				return false;
			}else{
				return true;
			}

}
function checkData(){

	var orgId=document.empAccountAF.orgIdaa101.value.trim();
	var empId=document.empAccountAF.empIdaa102.value.trim();
	var startDate=document.empAccountAF.startDate.value.trim();
	var lastDate = document.empAccountAF.lastDate.value.trim();
	if(orgId!=null&&orgId!=""){
	   if(!checkNumber(orgId)){
	  document.empAccountAF.orgIdaa101.focus();
	  return false;
	  }
	}
	if(empId!=null&&empId!=""){
	   if(!checkNumber(empId)){
	  document.empAccountAF.empIdaa102.focus();
	  return false;
	  }
	}
	if(orgId.length==0&&empId.length==0){
		alert("单位编号和职工编号，请至少录入一个条件进行查询!!");
		return false;
	}
	if(startDate=='null'||startDate==""){
	alert("请填写日期");
	document.empAccountAF.startDate.focus();
	return false;
	}
	if(lastDate=='null'||lastDate==""){
	alert("请填写截止日期");
	document.empAccountAF.lastDate.focus();
	return false;
	}
	if(startDate!='null'&&startDate!=""){
		if(!checkDate(document.empAccountAF.startDate)){
			document.empAccountAF.startDate.focus();
			return false;
		}   
	}
	if(lastDate!=null&&lastDate!=""){
		if(!checkDate(document.empAccountAF.lastDate)){
			document.empAccountAF.lastDate.focus();
			return false;
		}   
	}
    if(startDate!=null&&startDate!=""&&lastDate!=null&&lastDate!=""){
		checktimenow(document.empAccountAF.startDate,document.empAccountAF.lastDate);	
		}
	if(true){
			return true;
	}
}
/*---用来判断起始年月---*/
function checktimenow(time1,time2)
{
var tempMonth1=time1;
var tempMonth2=time2;
 var strMonth1 = time1.value.trim();
 var strMonth2 = time2.value.trim();
  if(strMonth1.substring(4,6)>12 || strMonth1.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    tempMonth1.focus();
    return false;
  }
  if(strMonth2.substring(4,6)>12 || strMonth2.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    tempMonth2.focus();
    return false;
  }
  if(strMonth1>strMonth2)
  {
    alert("起始年月应该不大于终止年月！");
    tempMonth1.focus();
    return false;
  }else{
  return true;
 }
} 
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function checkexprot(){
	var x=confirm("确定导处记录?");
		if(x){ 
		  	return true;
		}else{
		  return false;
		}
}
function executeAjax() {
var orgId=document.empAccountAF.orgIdaa101.value.trim();
var empId=document.empAccountAF.empIdaa102.value.trim();
var nameba001=document.empAccountAF.nameba001.value.trim();
var nameba002=document.empAccountAF.nameba002.value.trim();
var startDate=document.empAccountAF.startDate.value.trim();
var lastDate = document.empAccountAF.lastDate.value.trim();
        var queryString = "<%=request.getContextPath()%>/syscollection/querystatistics.accountinfo.empaccountinfo/emoAccountFindInforAAC.do?";
        queryString = queryString + "orgId="+orgId+ "empId="+empId+ "orgId="+orgId+ "nameba001="+nameba001+ "nameba002="+nameba002+ "startDate="+startDate+ "lastDate="+lastDate; 	     
	    findInfo(queryString);
}
function gettr(trindex) {
 tr=trindex; 
   var orgIdaa101=document.getElementById(tr).childNodes[1].childNodes[0].innerHTML.trim();
   var temp_empid=document.getElementById(tr).childNodes[3].childNodes[0].innerHTML.trim();
   var startAndlastDateDate=document.getElementById(tr).childNodes[11].innerHTML.trim();
   var queryString = "<%=request.getContextPath()%>/syscollection/querystatistics.accountinfo.empaccountinfo/showEmpAccountMonthListAC.do?";
        queryString = queryString + "temp_empid="+temp_empid+"&orgIdaa101="+orgIdaa101+"&startAndlastDateDate="+startAndlastDateDate; 	
        window.open(queryString,'_self','');     
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="loads(); reportErrors();"
	onContextmenu="return false">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="<%=request.getContextPath()%>/syscollection/querystatistics.accountinfo.empaccountinfo/showEmpAccountListAC.do" />
	</jsp:include>
	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
									<td width="350" background="<%=path%>/img/table_bg_line.gif">
									<td background="<%=path%>/img/table_bg_line.gif" align="right">
										<table width="300" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="37">
													<img src="<%=path%>/img/title_banner.gif" width="37"
														height="24">
												</td>
												<td width="189" class=font14 bgcolor="#FFFFFF"
													align="center" valign="bottom">
													<font color="00B5DB">统计查询</font><font color="00B5DB">&gt;&gt;</font><font color="00B5DB">职工账</font>
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
													<b class="font14">查询条件</b>
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
							<html:form action="/<%=request.getContextPath()%>/syscollection/querystatistics.accountinfo.empaccountinfo/findEmpAccountListAC.do">
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=3 align="center">
									<tr>
										<td colspan="2" width="100%" algin="center">
											<table width="100%" border="0" cellspacing=0 cellpadding=0
												align="center">
												<tr>
													<td width="17%" class="td1" algin="center">
														发生日期
													</td>
													<td width="14%">
														<html:text name="empAccountAF" property="startDate"
															styleClass="input3" styleId="txtsearch"
															onkeydown="goEnter();"></html:text>
													</td>
													<td width="4%" algin="center">
														至
													</td>
													<td width="15%" algin="center">
														<html:text name="empAccountAF" property="lastDate"
															styleClass="input3" styleId="txtsearch"
															onkeydown="goEnter();"></html:text>
													</td>

												</tr>
											</table>
										</td>
										<td class="td1" width="17%" algin="center">

										</td>
										<td width="33%">
											<html:hidden name="empAccountAF" property="loadsMassage"
												styleClass="input3" onkeydown="goEnter();" styleId="txtsearch"/>
										</td>
									</tr>
									<tr>
										<td class="td1" width="17%" algin="center">
											单位编号
										</td>
										<td width="33%">
											<html:text name="empAccountAF" property="orgIdaa101"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch"></html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											单位姓名
										</td>
										<td width="33%">
											<html:text name="empAccountAF" property="nameba001"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td class="td1" width="17%" algin="center">
											职工编号
										</td>
										<td width="33%">
											<html:text name="empAccountAF" property="empIdaa102"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch"></html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											职工姓名
										</td>
										<td width="33%">
											<html:text name="empAccountAF" property="nameba002"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td align="right">
											<html:submit property="method" styleClass="buttona" onclick="return checkData()">
												<bean:message key="button.search" />
											</html:submit>
										</td>
									</tr>
								</table>
								<logic:notEmpty name="empAccountAF" property="list">
								<table border="0" width="95%" id="table1" align="center"
									border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class=h4>
											人数合计
											<u>：<bean:write name="pagination" property="nrOfElements" /> </u> 
											本期贷方发生总额
											<u>：<bean:write name="empAccountAF"
													property="temp_credit" /> </u>
											本期借方发生总额
											<u>：<bean:write name="empAccountAF"
													property="temp_debit" /> </u>  
											利息合计
											<u>：<bean:write name="empAccountAF"
													property="curInterest" /> </u> 
										</td>
									</tr>
								</table>
								</logic:notEmpty>
								<logic:empty name="empAccountAF" property="list">
								<table border="0" width="95%" id="table1" align="center"
									border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class=td6>
											人数合计
											<u>:0</u> 
											本期贷方发生总额
											<u>:0</u> 
											本期借方发生总额
											<u>:0</u> 
											利息合计
											<u>:0</u> 
										</td>
									</tr>
								</table>
								</logic:empty>
							</html:form>
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="154">
													<b class="font14">总账查询</b>
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
							<html:form action="/<%=request.getContextPath()%>/syscollection/querystatistics.accountinfo.empaccountinfo/empAccountMaintainAC.do" style="margin: 0">
								
								<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
										<td align="center" height="23" bgcolor="C4F0FF">
											单位编号
										</td>
										<td align="center" height="23" bgcolor="C4F0FF">
											单位名称
										</td>
										<td align="center" class=td2>
											职工编号
										</td>
										<td align="center" class=td2>
											职工姓名
										</td>
										<td align="center" class=td2>
											期初余额
										</td>
										<td align="center" class=td2>
											本期贷方发生额
										</td>
										<td align="center" class=td2>
											本期借方发生额
										</td>
										<td align="center" class=td2>
											本期贷方笔数
										</td>
										<td align="center" class=td2>
											本期借方笔数
										</td>
										<td align="center" class=td2>
											期末余额
										</td>
										<td align="center" class=td2 >查询时间</td>
									</tr>
                          		<logic:notEmpty name="empAccountAF" property="list">
                          		
<% int j=0;
			String strClass="";
		%>
										<logic:iterate name="empAccountAF" property="list"
											id="element" indexId="i">
<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
											<tr id="tr<%=i%>"
												
onclick='gotoClickpp("<%=i %>", idAF);' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>" onDblClick='gettr("tr<%=i%>");'>
												<td valign="top">
													<p align="left">
														<input id="s<%=i%>" type="radio" name="id"
															value="<bean:write name="element" property="id"/>"
															<%if(new Integer(0).equals(i)) out.print("checked"); %>>
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="org.id" format="000000"/>
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="org.orgInfo.name"/>
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="empId" format="000000"/>
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="empName" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="prebalance" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="temp_credit" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="temp_debit" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="countCredit" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="countDebit" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="curbalance" />
													</p>
												</td>
												<td valign="top">
													<bean:write name="element" property="displayTme" />
												</td>
											</tr>
											
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="empAccountAF" property="list">
										<tr>
											<td colspan="4" height="30" style="color:red">
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
															<jsp:param name="url" value="<%=request.getContextPath()%>/syscollection/querystatistics.accountinfo.empaccountinfo/showEmpAccountListAC.do" />
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
													<td>
													<logic:empty name="empAccountAF" property="list">
													<html:submit property="method" styleClass="buttona" disabled="true">
															<bean:message key="button.print" />
														</html:submit>
													</logic:empty>
													<logic:notEmpty name="empAccountAF" property="list">
													<html:submit property="method" styleClass="buttona">
															<bean:message key="button.print" />
														</html:submit>
													</logic:notEmpty>	
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
