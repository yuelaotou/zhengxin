<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.action.ShowCollectionstatisticsListAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			ShowCollectionstatisticsListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>统计查询>>缴存提取统计>>归集情况统计</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>
<script>
var s1="";
var s2="";

function checkDate(){
  var startdate = document.all.startDate.value;
  var enddate = document.all.endDate.value;
  if(startdate == ""&& enddate == ""){
    alert("必须输入查询日期!");
   return false;
  }
   if(startdate != ""&& enddate == ""){
    alert("请输入查询结束日期!");
   return false;
  }
   if(startdate == ""&& enddate != ""){
    alert("请输入查询开始日期!");
   return false;
  }
  if(startdate != "" && enddate != "" ){
     var strr = checkDate2('startDate','endDate');
     if(!strr){
        return false;
     }
   }
}

function loads(){
   document.collectionstatisticsAF.elements["officeCode"].value="";
   document.collectionstatisticsAF.elements["collectionBank"].value="";
   document.collectionstatisticsAF.elements["orgId"].value="";
   document.collectionstatisticsAF.elements["orgName"].value="";
   document.collectionstatisticsAF.elements["orgCharacter"].value="";
   document.collectionstatisticsAF.elements["deptInCharge"].value="";
   document.collectionstatisticsAF.elements["startDate"].value="";
   document.collectionstatisticsAF.elements["endDate"].value="";
   document.collectionstatisticsAF.elements["region"].value="";
   
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

</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads()"
	onContextmenu="return false">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="to_showAC.do" />
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
											color="00B5DB">归集情况统计</font>
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
				<html:form action="/findCollection.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1">
								办事处
							</td>
							<td colspan="3">
								<html:select property="officeCode" styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="officeList" name="theCsAF"
										label="officeName" value="officeCode" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								归集银行
							</td>
							<td width="33%">
								<html:select property="collectionBank" styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="collBankList" name="theCsAF"
										label="collBankName" value="collBankId" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" height="33" class="td1">
								单位编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="collectionstatisticsAF" property="orgId"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="33%">
								<html:text name="collectionstatisticsAF" property="orgName"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位性质
							</td>
							<td width="33%" colspan="3">
								<html:select property="orgCharacter" styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="orgCharacterMap"
										name="theCsAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								主管部门
							</td>
							<td width="33%" colspan="3">
								<html:select property="deptInCharge" styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="deptMap" name="theCsAF"
										label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								发生日期
							</td>
							<td width="15%">
								<html:text name="collectionstatisticsAF" property="startDate"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text name="collectionstatisticsAF" property="endDate"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								所在地区
							</td>
							<td width="33%" colspan="3">
								<html:select property="region" styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="ragionMap" name="theCsAF"
										label="value" value="key" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return checkDate();">
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
							合计：期初归集
							<u>: <bean:write name="theCsAF"
									property="collectionstatisticsExportDTO.sumlastMonth" /> </u>、本期正常汇缴
							<u>: <bean:write name="theCsAF"
									property="collectionstatisticsExportDTO.summonthPay" /> </u>、本期单位补缴
							<u>: <bean:write name="theCsAF"
									property="collectionstatisticsExportDTO.sumorgAddPay" /> </u>、本期个人补缴
							<u>: <bean:write name="theCsAF"
									property="collectionstatisticsExportDTO.sumpersonAddPay" /> </u>、本期单位挂账
							<u>: <bean:write name="theCsAF"
									property="collectionstatisticsExportDTO.sumorgOverPay" /> </u>、本期调缴存
							<u>: <bean:write name="theCsAF"
									property="collectionstatisticsExportDTO.sumChgPay" /> </u>、期末归集
							<u>: <bean:write name="theCsAF"
									property="collectionstatisticsExportDTO.sumthisMonth" /> </u>、比率
							<u>: <bean:write name="theCsAF"
									property="collectionstatisticsExportDTO.sumRate" /> </u>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="167">
										<b class="font14">归集情况统计列表 </b>
									</td>
									<td height="22"
										background="<%=request.getContextPath()%>/img/bg2.gif"
										align="center" width="737">
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
				<html:form action="/maintain.do" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2 rowspan="2">
								<a href="javascript:sort('ba001.officecode')">办事处</a>
								<logic:equal name="pagination" property="orderBy"
									value="ba001.officecode">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							<td align="center" class=td2 rowspan="2">
								<a href="javascript:sort('ba001.collection_bank_id')">归集银行</a>
								<logic:equal name="pagination" property="orderBy"
									value="ba001.collection_bank_id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2 rowspan="2">
								<a href="javascript:sort('aa001.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="aa001.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2 rowspan="2">
								<a href="javascript:sort('ba001.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="ba001.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2 rowspan="2">
								单位性质
							</td>
							<td align="center" class=td2 rowspan="2">
								主管部门
							</td>
							<td align="center" class=td2 rowspan="2">
								所在地区
							</td>
							<td align="center" class=td2 rowspan="2">
								期初归集
							</td>
							<td align="center" class=td2 colspan="5">
								本期
							</td>
							<td align="center" class=td2 rowspan="2">
								期末归集
							</td>
							<td align="center" class=td2>
								&nbsp;
							</td>
						</tr>
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								正常汇缴
							</td>
							<td align="center" class=td2>
								单位补缴
							</td>
							<td align="center" class=td2>
								个人补缴
							</td>
							<td align="center" class=td2>
								单位挂账
							</td>
							<td align="center" class=td2>
								调缴存
							</td>
							<td align="center" class=td2>
								比率
							</td>
						</tr>
						<logic:notEmpty name="theCsAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>

							<logic:iterate id="element" name="theCsAF" property="list"
								indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>", idAF);gettr("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>", idAF);'
									class="<%=strClass%>">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id" value=""
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td>
										<bean:write name="element" property="officeCode" />
									</td>
									<td>
										<bean:write name="element" property="collectionBank" />
									</td>
									<td>
										<bean:write name="element" property="orgId" format="000000" />
									</td>
									<td>
										<bean:write name="element" property="orgName" />
									</td>
									<td>
										<bean:write name="element" property="orgCharacter" />
									</td>
									<td>
										<bean:write name="element" property="deptInCharge" />
									</td>
									<td>
										<bean:write name="element" property="region" />
									</td>
									<td>
										<bean:write name="element" property="lastMonthCollect" />
									</td>
									<td>
										<bean:write name="element" property="monthPay" />
									</td>
									<td>
										<bean:write name="element" property="orgAddPay" />
									</td>
									<td>
										<bean:write name="element" property="orgOverPay" />
									</td>
									<td>
										<bean:write name="element" property="personAddPay" />
									</td>
									<td>
										<bean:write name="element" property="chgPay" />
									</td>
									<td>
										<bean:write name="element" property="thisMonthCollect" />
									</td>
									<td>
										<bean:write name="element" property="rate" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<input type="hidden" name="listCount"
							value="<bean:write name="theCsAF" property="list"/>">
						<logic:empty name="theCsAF" property="list">
							<tr>
								<td colspan="16" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
						</logic:empty>

					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td align="center">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="../../../../inc/pagination.jsp">
												<jsp:param name="url" value="to_showAC.do" />
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
										<td width="70">
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.print" />
											</html:submit>
											&nbsp;&nbsp;
										</td>
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
