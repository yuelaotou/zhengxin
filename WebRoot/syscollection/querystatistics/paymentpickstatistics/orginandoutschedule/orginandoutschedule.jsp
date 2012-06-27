<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.action.ShowListAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			ShowListAC.PAGINATION_KEY);
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
		var officeCode = document.displayAF.elements["searchDTO.officeCode"].value.trim();
		var collectionBankId = document.displayAF.elements["searchDTO.collectionBankId"].value.trim();
		var orgId = document.displayAF.elements["searchDTO.orgId"].value.trim();
		var orgName = document.displayAF.elements["searchDTO.orgName"].value.trim();
		var character = document.displayAF.elements["searchDTO.character"].value.trim();
		var deptInCharge = document.displayAF.elements["searchDTO.deptInCharge"].value.trim();
		var dateStart = document.displayAF.elements["searchDTO.dateStart"].value.trim();
		var dateEnd = document.displayAF.elements["searchDTO.dateEnd"].value.trim();
		var region = document.displayAF.elements["searchDTO.region"].value.trim();
		if(dateStart==""||dateEnd==""){
			alert("发生日期的始末必需输入！");
			return false;
		}
		if(orgId.trim()!=""){//这个地方必须用这样的方法来取值
			if(isNaN(orgId.trim())){alert("请你输入正确的单位编号");return false;}
			if(orgId.trim().indexOf(".")!=-1){alert("单位编号不能有小数点");return false;}
			if(orgId.trim()<=0){alert("单位编号必须是正整数");return false;}
		}
		if(dateStart!=""&&dateEnd!=""){
			return checkDate2(document.displayAF.elements["searchDTO.dateStart"],document.displayAF.elements["searchDTO.dateEnd"]);
		}
		return true;
	}
	function checkDate2(date1,date2){
	   var strDate1 = date1.value;
	   var strDate2 = date2.value;
	   if(strDate1.length != 8)
	   {
	     alert("请输入八位的日期，格式为20070707！");
	     document.displayAF.elements["searchDTO.dateStart"].value="";
	     return false;
	   }
	   if(strDate2.length != 8)
	   {
	     alert("请输入八位的日期，格式为20070707！");
	     document.displayAF.elements["searchDTO.dateEnd"].value="";
	     return false;
	   }
	    if(strDate1.substring(0,4)<1900)
	   {
	     alert("年份应该大于1900!");
	     document.displayAF.elements["searchDTO.dateStart"].value="";
	     return false;
	   }
	   if(strDate2.substring(0,4)<1900)
	   {
	     alert("年份应该大于1900!");
	     document.displayAF.elements["searchDTO.dateEnd"].value="";
	     return false;
	   }
	   if(strDate1.substring(4,6)>12 || strDate1.substring(4,6)<1)
	   {
	     alert("月份应该在1-12月之间！");
	     document.displayAF.elements["searchDTO.dateStart"].value="";
	     return false;
	   }
	   if(strDate2.substring(4,6)>12 || strDate2.substring(4,6)<1)
	   {
	     alert("月份应该在1-12月之间！");
	     document.displayAF.elements["searchDTO.dateEnd"].value="";
	     return false;
	   }
	   if((strDate1.substring(4,6)==01 || strDate1.substring(4,6)==03 ||
	   strDate1.substring(4,6)==05 || strDate1.substring(4,6)==07 ||
	   strDate1.substring(4,6)==08 || strDate1.substring(4,6)==10 ||
	   strDate1.substring(4,6)==12)&&strDate1.substring(6,8)>31){
	   alert("大月日期应该在1-31之间");
	   document.displayAF.elements["searchDTO.dateStart"].value="";
	   return false;
	   }
	   if((strDate1.substring(4,6)==04 || 
	   strDate1.substring(4,6)==05 || strDate1.substring(4,6)==09 || 
	   strDate1.substring(4,6)==11)&&strDate1.substring(6,8)>30){
	   alert("小月日期应该在1-30之间");
	   document.displayAF.elements["searchDTO.dateStart"].value="";
	   return false;
	   }
	   if((strDate2.substring(4,6)==01 || strDate2.substring(4,6)==03 ||
	   strDate2.substring(4,6)==05 || strDate2.substring(4,6)==07 ||
	   strDate2.substring(4,6)==08 || strDate2.substring(4,6)==10 ||
	   strDate2.substring(4,6)==12)&&strDate2.substring(6,8)>31){
	   alert("大月日期应该在1-31之间");
	   document.displayAF.elements["searchDTO.dateEnd"].value="";
	   return false;
	   }
	   if((strDate2.substring(4,6)==04 || 
	   strDate2.substring(4,6)==05 || strDate2.substring(4,6)==09 || 
	   strDate2.substring(4,6)==11)&&strDate2.substring(6,8)>30){
	   alert("小月日期应该在1-30之间");
	   document.displayAF.elements["searchDTO.dateEnd"].value="";
	   return false;
	   }
	   if(strDate1.substring(4,6)==02 && strDate1.substring(6,8)>29){
	   alert("二月份日期应该在1-29之间");
	   document.displayAF.elements["searchDTO.dateStart"].value="";
	   return false;
	   }
	   if((strDate2.substring(4,6)==02)&&strDate2.substring(6,8)>29){
	   alert("二月份日期应该在1-29之间");
	   document.displayAF.elements["searchDTO.dateEnd"].value="";
	   return false;
	   }
	   if(strDate1>strDate2)
	   {
	    alert("起始日期应该不大于终止日期!");
	    document.displayAF.elements["searchDTO.dateStart"].value="";
	    document.displayAF.elements["searchDTO.dateEnd"].value="";
	    return false;
	   }
	   return true;
}
</script>
	<title>统计查询单位收支明细一览表</title>
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="showListAC.do" />
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
										<font color="00B5DB">统计查询&gt;单位收支明细一览表</font>
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
				<html:form action="/findListAC.do">
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
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1">
								办事处
							</td>
							<td colspan="3">
								<html:select property="searchDTO.officeCode" styleClass="input4"
									name="displayAF" onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								归集银行
							</td>
							<td width="33%">
								<html:select property="searchDTO.collectionBankId"
									styleClass="input4" name="displayAF"
									onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="collBankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td width="33%" colspan="3">
								<html:text property="searchDTO.orgId" styleClass="input3"
									name="displayAF" onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="33%">
								<html:text property="searchDTO.orgName" styleClass="input3"
									name="displayAF" onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位性质
							</td>
							<td width="33%" colspan="3">
								<html:select style="input4" property="searchDTO.character"
									styleClass="input4" name="displayAF"
									onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:optionsCollection property="charaterMap" name="displayAF"
										label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								主管部门
							</td>
							<td width="33%">
								<html:select style="input4" property="searchDTO.deptInCharge"
									styleClass="input4" name="displayAF"
									onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:optionsCollection property="deptInChargeMap"
										name="displayAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								发生日期
							</td>
							<td width="15%">
								<html:text property="searchDTO.dateStart" styleClass="input3"
									name="displayAF" onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text property="searchDTO.dateEnd" styleClass="input3"
									name="displayAF" onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="17%" class="td1">
								所在地区
							</td>
							<td width="33%">
								<html:select style="input4" property="searchDTO.region"
									styleClass="input4" name="displayAF"
									onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:optionsCollection property="regionMap" name="displayAF"
										label="value" value="key" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit styleClass="buttona" onclick="return check();">查询</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/maintainAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：汇缴:
								<u><bean:write name="displayAF" property="paymentSum"
										format="0.00" /> </u>补缴:
								<u><bean:write name="displayAF" property="addpaySum"
										format="0.00" /> </u>调账:
								<u><bean:write name="displayAF" property="adjustaccountSum"
										format="0.00" /> </u>转入:
								<u><bean:write name="displayAF" property="traninSum"
										format="0.00" /> </u>结息:
								<u><bean:write name="displayAF" property="interestSum"
										format="0.00" /> </u>提取:
								<u><bean:write name="displayAF" property="pickSum"
										format="0.00" /> </u>转出:
								<u><bean:write name="displayAF" property="tranoutSum"
										format="0.00" /> </u>挂账：
								<u><bean:write name="displayAF" property="guazhangSum"
										format="0.00" /> </u>账面余额：
								<u><bean:write name="displayAF" property="zmbalanceSum"
										format="0.00" /> </u>
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
											<b class="font14">归集情况统计列表</b>
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
							<td align="center" height="46" bgcolor="C4F0FF" rowspan="2">
								&nbsp;
							</td>
							<td align="center" class=td2 rowspan="2">
								<a href="javascript:sort('a.org_id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="a.org_id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2 rowspan="2">
								<a href="javascript:sort('orgname')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="orgname">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2 rowspan="2">
								结转余额
							</td>
							<td align="center" class=td2 rowspan="2">
								结转挂账余额
							</td>
							<td align="center" class=td2 rowspan="2">
								结转账面余额
							</td>
							<td align="center" class=td2 rowspan="2">
								挂账金额
							</td>
							<td align="center" class=td2 colspan="5">
								增加金额
							</td>
							<td align="center" class=td2 colspan="2">
								减少金额
							</td>
							<td align="center" class=td2 rowspan="2">
								当前余额
							</td>
							<td align="center" class=td2 rowspan="2">
								挂账余额
							</td>
							<td align="center" class=td2 rowspan="2">
								<a href="javascript:sort('zmbalance')">账面余额</a>
								<logic:equal name="pagination" property="orderBy"
									value="zmbalance">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
						</tr>
						<tr>
							<td align="center" class=td2>
								汇缴
							</td>
							<td align="center" class=td2>
								补缴
							</td>
							<td align="center" class=td2>
								调账
							</td>
							<td align="center" class=td2>
								转入
							</td>
							<td align="center" class=td2>
								结息
							</td>
							<td align="center" class=td2>
								提取
							</td>
							<td align="center" class=td2>
								转出
							</td>
						</tr>
						<logic:notEmpty name="displayAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="e" name="displayAF" property="list"
								indexId="i">
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
											value="<bean:write name="e" property="orgid"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td>
										<bean:write name="e" property="orgid" format="0000000000" />
									</td>
									<td>
										<bean:write name="e" property="orgname" />
									</td>
									<td>
										<bean:write name="e" property="jzbalance" />
									</td>
									<td>
										<bean:write name="e" property="jzgzbalance" />
									</td>
									<td>
										<bean:write name="e" property="jzzmbalance" />
									</td>
									<td>
										<bean:write name="e" property="gzpay" />
									</td>
									<td>
										<bean:write name="e" property="payment" />
									</td>
									<td>
										<bean:write name="e" property="addpay" />
									</td>
									<td>
										<bean:write name="e" property="adjustaccount" />
									</td>
									<td>
										<bean:write name="e" property="tanin" />
									</td>
									<td>
										<bean:write name="e" property="interest" />
									</td>
									<td>
										<bean:write name="e" property="pick" />
									</td>
									<td>
										<bean:write name="e" property="tranout" />
									</td>
									<td>
										<bean:write name="e" property="currentbalance" />
									</td>
									<td>
										<bean:write name="e" property="gzbalance" />
									</td>
									<td>
										<bean:write name="e" property="zmbalance" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="displayAF" property="list">
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
												<jsp:param name="url" value="showListAC.do" />
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
										<logic:notEmpty name="displayAF" property="list">
											<td width="70">
												<html:submit property="method" styleClass="buttona">打印</html:submit>
											</td>
										</logic:notEmpty>
										<logic:empty name="displayAF" property="list">
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">打印</html:submit>
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
