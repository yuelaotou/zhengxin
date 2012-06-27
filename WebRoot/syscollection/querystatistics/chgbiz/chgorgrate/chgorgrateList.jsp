<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.action.ShowChgorgrateListAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			ShowChgorgrateListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>统计查询>>变更信息>>汇缴比例调整</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>
<script>

var s1="";

function checkDate2(date1,date2){
   var tempDate1;
   var tempDate2;
   eval("tempDate1=document.all."+date1);
   eval("tempDate2=document.all."+date2);
   var strDate1 = tempDate1.value;
   var strDate2 = tempDate2.value;
   if(strDate1.length != 8)
   {
     alert("请输入八位的日期，格式为20070707！");
     tempDate1.value="";
     tempDate1.focus();
     return false;
   }
   if(strDate2.length != 8)
   {
     alert("请输入八位的日期，格式为20070707！");
     tempDate2.value="";
     tempDate2.focus();
     return false;
   }
    if(strDate1.substring(0,4)<1900)
   {
     alert("年份应该大于1900!");
     tempDate1.value="";
     tempDate1.focus();
     return false;
   }
   if(strDate2.substring(0,4)<1900)
   {
     alert("年份应该大于1900!");
     tempDate2.value="";
     tempDate2.focus();
     return false;
   }
   if(strDate1.substring(4,6)>12 || strDate1.substring(4,6)<1)
   {
     alert("月份应该在1-12月之间！");
     tempDate1.value="";
     tempDate1.focus();
     return false;
   }
   if(strDate2.substring(4,6)>12 || strDate2.substring(4,6)<1)
   {
     alert("月份应该在1-12月之间！");
     tempDate2.value="";
     tempDate2.focus();
     return false;
   }
   if((strDate1.substring(4,6)==01 || strDate1.substring(4,6)==03 ||
   strDate1.substring(4,6)==05 || strDate1.substring(4,6)==07 ||
   strDate1.substring(4,6)==08 || strDate1.substring(4,6)==10 ||
   strDate1.substring(4,6)==12)&&strDate1.substring(6,8)>31){
   alert("大月日期应该在1-31之间");
   tempDate1.value="";
   tempDate1.focus();
   return false;
   }
   if((strDate1.substring(4,6)==04 || 
   strDate1.substring(4,6)==05 || strDate1.substring(4,6)==09 || 
   strDate1.substring(4,6)==11)&&strDate1.substring(6,8)>30){
   alert("小月日期应该在1-30之间");
   tempDate1.value="";
   tempDate1.focus();
   return false;
   }
   if((strDate2.substring(4,6)==01 || strDate2.substring(4,6)==03 ||
   strDate2.substring(4,6)==05 || strDate2.substring(4,6)==07 ||
   strDate2.substring(4,6)==08 || strDate2.substring(4,6)==10 ||
   strDate2.substring(4,6)==12)&&strDate2.substring(6,8)>31){
   alert("大月日期应该在1-31之间");
   tempDate2.value="";
   tempDate2.focus();
   return false;
   }
   if((strDate2.substring(4,6)==04 || 
   strDate2.substring(4,6)==05 || strDate2.substring(4,6)==09 || 
   strDate2.substring(4,6)==11)&&strDate2.substring(6,8)>30){
   alert("小月日期应该在1-30之间");
   tempDate2.value="";
   tempDate2.focus();
   return false;
   }
   if(strDate1.substring(4,6)==02 && strDate1.substring(6,8)>29){
   alert("二月份日期应该在1-29之间");
   tempDate1.value="";
   tempDate1.focus();
   return false;
   }
   if((strDate2.substring(4,6)==02)&&strDate2.substring(6,8)>29){
   alert("二月份日期应该在1-29之间");
   tempDate2.value="";
   tempDate2.focus();
   return false;
   }
   if(strDate1>strDate2)
   {
    alert("起始日期应该不大于终止日期!");
    return false;
   }
   return true;
}
function gotocheck(){
   var office=document.all.officeCode.value;
   var bank=document.all.collectionBank.value;
   var orgid=document.all.orgId.value;
   var orgName=document.all.orgName.value;
   var monthst=document.all.chgMonthStart.value;
   var monthed=document.all.chgMonthEnd.value;
   var datest=document.all.chgDateStart.value;
   var dateed=document.all.chgDateEnd.value;
   var type=document.all.chgStatus.value;
   
   if(office == "" && bank == "" && orgid == "" && orgName == "" && monthst == "" && monthed == "" && datest =="" && dateed ==""&& type==""){
     alert("请至少输入一条查询条件!");
     return false;
   }
   
   
   if(orgid != ""){
     if(isNaN(orgid)){
        alert("请输入正确格式的单位编号！");
        return false;
     }
   }
   if(monthst != ""){
     if(isNaN(monthst)){
       alert("请输入正确格式的年月!");
       return false;
     }
   }
   if(monthed != ""){
     if(isNaN(monthed)){
       alert("请输入正确格式的年月!");
       return false;
     }
   }
    if(monthst != "" && monthed != ""){
    var str = checkTimes('chgMonthStart','chgMonthEnd');
      if(!str){
        return false;
      }
   }
 
   if(datest != ""){
    if(isNaN(datest)){
      alert("请输入正确格式的日期!");
      return false;
    }
   }
   if(dateed != ""){
    if(isNaN(dateed)){
      alert("请输入正确格式的日期!");
      return false;
    }
   }
     if(datest != "" && dateed != "" ){
     var strr = checkDate2('chgDateStart','chgDateEnd');
     if(!strr){
        return false;
     }
   }
}

function loads(){
   document.chgorgrateAF.elements["officeCode"].value="";
   document.chgorgrateAF.elements["collectionBank"].value="";
   document.chgorgrateAF.elements["orgId"].value="";
   document.chgorgrateAF.elements["orgName"].value="";
   document.chgorgrateAF.elements["chgMonthStart"].value="";
   document.chgorgrateAF.elements["chgMonthEnd"].value="";
   document.chgorgrateAF.elements["chgDateStart"].value="";
   document.chgorgrateAF.elements["chgDateEnd"].value="";
   document.chgorgrateAF.elements["chgStatus"].value="";
  
         
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="打印"){
				s1=i;
			}
		}
	}
	 var list = document.all.listCount.value;
  
    if(list.length==2){
  		document.all.item(s1).disabled="true";
    }else{
		document.all.item(s1).disabled="";
    }
    
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads()"
	onContextmenu="return false">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="to_showChgorgrateListAC.do" />
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
									<td class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">统计查询</font><font color="00B5DB">&gt;</font><font
											color="00B5DB">汇缴比例调整</font>
									</td>
									<td class=font14>
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
				<html:form action="/findChgorgrateListAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/img/bg2.gif"
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
							<td width="33%" colspan="3">
								<html:select property="officeCode" styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="officeList"
										name="thechgorgrateAF" label="officeName" value="officeCode" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								归集银行
							</td>
							<td width="33%" colspan="3">
								<html:select property="collectionBank" styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="collBankList"
										name="thechgorgrateAF" label="collBankName" value="collBankId" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="chgorgrateAF" property="orgId"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="33%" colspan="3">
								<html:text name="chgorgrateAF" property="orgName"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								调整年月
							</td>
							<td width="15%">
								<html:text name="chgorgrateAF" property="chgMonthStart"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="4%">
								至
							</td>
							<td width="14%">
								<html:text name="chgorgrateAF" property="chgMonthEnd"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								调整日期
							</td>
							<td width="14%">
								<html:text name="chgorgrateAF" property="chgDateStart"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="4%">
								至
							</td>
							<td width="15%">
								<html:text name="chgorgrateAF" property="chgDateEnd"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								状态
							</td>
							<td width="33%" colspan="3">
								<html:select property="chgStatus" styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="map" name="thechgorgrateAF"
										label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								&nbsp;
							</td>
							<td width="33%" colspan="3">
								&nbsp;
							</td>
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
						<td class=h4>
							合计：调整单位数
							<u>: <bean:write name="thechgorgrateAF" property="orgCount" />
							</u> 调整前应缴总额
							<u>: <bean:write name="thechgorgrateAF" property="sumPre" />
							</u> 调整后应缴总额
							<u>: <bean:write name="thechgorgrateAF" property="sumSith" />
							</u> 变更笔数
							<u>: <bean:write name="thechgorgrateAF" property="counts" />
							</u>
						</td>
					</tr>
				</table>
				<html:form action="/printChgorgrateListAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="180">
											<b class="font14">汇缴比例调整列表</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/img/bg2.gif"
											align="center" width="724">
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
								<a href="javascript:sort('org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy" value="org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								人数
							</td>
							<td align="center" class=td2>
								调整前单位比例
							</td>
							<td align="center" class=td2>
								调整后单位比例
							</td>
							<td align="center" class=td2>
								调整前职工缴率
							</td>
							<td align="center" class=td2>
								调整后职工缴率
							</td>
							<td align="center" class=td2>
								调整前汇缴额
							</td>
							<td align="center" class=td2>
								调整后汇缴额
							</td>
							<td align="center" class=td2>
								汇缴额差额
							</td>
							<td align="center" class=td2>
								缴至年月
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgOrgRate.chgMonth')"> 调整年月</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgOrgRate.chgMonth">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								调整日期
							</td>

						</tr>
						<input type="hidden" name="listCount"
							value="<bean:write name="thechgorgrateAF" property="list"/>">
						<logic:notEmpty name="thechgorgrateAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="thechgorgrateAF"
								property="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>", idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>", idAF);' class="<%=strClass %>">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>

									<td>
										<bean:write name="element" property="org.id"
											format="0000000000" />
									</td>
									<td>
										<bean:write name="element" property="org.orgInfo.name" />
									</td>
									<td>
										<bean:write name="element" property="empCount" />
									</td>
									<td>
										<bean:write name="element" property="preOrgRate" />
									</td>
									<td>
										<bean:write name="element" property="orgRate" />
									</td>
									<td>
										<bean:write name="element" property="preEmpRate" />
									</td>
									<td>
										<bean:write name="element" property="empRate" />
									</td>
									<td>
										<bean:write name="element" property="preSumPay" />
									</td>
									<td>
										<bean:write name="element" property="sumPay" />
									</td>
									<td>
										<bean:write name="element" property="pay" />
									</td>
									<td>
										<bean:write name="element" property="month" />
									</td>
									<td>
										<bean:write name="element" property="chgMonth" />
									</td>
									<td>
										<bean:write name="element" property="bizDate" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>

						<logic:empty name="thechgorgrateAF" property="list">
							<tr>
								<td colspan="20" height="30" style="color:red">
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
												<jsp:param name="url" value="to_showChgorgrateListAC.do" />
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
												<bean:message key="button.print" />
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
