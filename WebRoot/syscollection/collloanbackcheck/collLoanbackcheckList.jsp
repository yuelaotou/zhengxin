<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.collloanbackcheck.action.CollLoanbackcheckShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			CollLoanbackcheckShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>公积金还贷-业务复核</title>
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js"></script>

	<script type="text/javascript">
var s1="";
var s2="";
var s3="";
var s4="";
var tr="tr0";
var s = "<bean:write name="collLoanbackcheckAF" property="flag" />"; 
function loads(){
	document.all.officeCode.value="";
	document.all.collectionBankId.value="";
	document.all.bizStatus.value="";
	document.all.batch_num.value="";
	document.all.startDate.value="";
	document.all.endDate.value="";
	
	document.all.orgid.value="";
	document.all.orgname.value="";
	document.all.empid.value="";
	document.all.empname.value="";
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(s=="1"){
				if(document.all.item(i).value=="复核通过"){    
					s1=i;
				}
				if(document.all.item(i).value=="批量复核"){
					s2=i;
				}
				if(document.all.item(i).value=="撤消复核"){
					s3=i;
				}
				if(document.all.item(i).value=="撤消批量复核"){
					s4=i;
				}
			}
		}
	} 
  	var status=document.getElementById(tr);
	if(status!=null){
		update();
	}else{
		if(s=="1"){
			document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
			document.all.item(s3).disabled="true";
			document.all.item(s4).disabled="true";
		}
	}
	
}
function on_Submit(eee){
	setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
	return true;
} 
function gettr(trindex) {
  tr=trindex;
  update();
}
function update() {
  	var status=document.getElementById(tr).childNodes[11].innerHTML.trim();
  	var statusType=document.all.statusType.value;
	  	if(status=="确认"){
	  		if(s=="1"){
				document.all.item(s1).disabled="";
				document.all.item(s2).disabled="";
				document.all.item(s3).disabled="true";
				document.all.item(s4).disabled="true";
			}
	  	}
	  	if(status=="复核"){
		  	if(s=="1"){
				document.all.item(s1).disabled="true";
				document.all.item(s2).disabled="true";
				document.all.item(s3).disabled="";
				document.all.item(s4).disabled="";
			}
	  	}  
	  	if(status=="入账"){
	  		if(s=="1"){
				document.all.item(s1).disabled="true";
				document.all.item(s2).disabled="true";
				document.all.item(s3).disabled="true";
				document.all.item(s4).disabled="true";
			}
	  	}
}

function a(){
	var p = "<%=path%>";
	officeAjax(p);
}
function checkdata(){
    var batch_num = document.collLoanbackcheckAF.batch_num.value;
    if(batch_num == ""){
      alert("请输入批次号！");
      return false;
    }
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	onContextmenu="return false">
	<jsp:include page="../../inc/sort.jsp">
		<jsp:param name="url" value="collLoanbackcheckShowAC.do" />
	</jsp:include>
	<jsp:include flush="true" page="/waiting.inc" />
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
										<font color="00B5DB">公积金还贷</font><font color="00B5DB">&gt;业务复核</font>
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
				<html:form action="/collLoanbackcheckFindAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="150">
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
								<html:select property="officeCode" styleClass="input4"
									onchange="a();">
									<option value=""></option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								归集银行
							</td>
							<td width="33%" colspan="3">
								<html:select property="collectionBankId" styleClass="input4">
									<option value=""></option>
									<html:options collection="collBankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								业务状态
							</td>
							<td width="33%" colspan="3">
								<html:select property="bizStatus" styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="map"
										name="collLoanbackcheckAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								批次号
								<font color="#ff0000">*</font>
							</td>
							<td width="33%" colspan="3">
								<html:text name="collLoanbackcheckAF" property="batch_num"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td width="15%" colspan="3">
								<html:text name="collLoanbackcheckAF" property="orgid"
									styleClass="input3" styleId="txtsearch" />
							</td>

							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="15%" colspan="3">
								<html:text name="collLoanbackcheckAF" property="orgname"
									styleClass="input3" styleId="txtsearch" />

							</td>
						</tr>

						<tr>
							<td width="17%" class="td1">
								职工编号
							</td>
							<td width="15%" colspan="3">
								<html:text name="collLoanbackcheckAF" property="empid"
									styleClass="input3" styleId="txtsearch" />
							</td>

							<td width="17%" class="td1">
								职工姓名
							</td>
							<td width="14%" colspan="3">
								<html:text name="collLoanbackcheckAF" property="empname"
									styleClass="input3" styleId="txtsearch" />
							</td>
						</tr>

						<tr>
							<td width="17%" class="td1">
								办理日期
							</td>
							<td width="15%">
								<html:text name="collLoanbackcheckAF" property="startDate"
									styleClass="input3" styleId="txtsearch" size="12" />
							</td>
							<td width="4%">
								至
							</td>
							<td width="14%">
								<html:text name="collLoanbackcheckAF" property="endDate"
									styleClass="input3" styleId="txtsearch" size="10" />
							</td>
							<td width="17%" class="td1"></td>
							<td width="14%">
								<input type="text" class="input3" readonly="true">
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return checkdata();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>


				<html:form action="/collLoanbackcheckMainTainAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">

						<tr>
							<html:hidden name="collLoanbackcheckAF" property="statusType" />
							<td class=h4>
								合计：发生笔数:
								<u><bean:write name="collLoanbackcheckAF"
										property="totalCount" />&nbsp;</u> 发生金额:
								<u><bean:write name="collLoanbackcheckAF"
										property="totalDcitsum" />&nbsp;</u>
							</td>
						</tr>
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="180">
											<b class="font14">业务复核列表</b>
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
						cellpadding="0" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('batch_num')">批次号</a>
								<logic:equal name="pagination" property="orderBy"
									value="batch_num">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('doc_num')">凭证编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="doc_num">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								合同编号
							</td>
							<td align="center" class=td2>
								借款人姓名
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('orgid')">单位编号</a>
								<logic:equal name="pagination" property="orderBy" value="orgid">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('orgname')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="orgname">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('empid')">职工编号</a>
								<logic:equal name="pagination" property="orderBy" value="empid">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('empname')">职工姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="empname">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('money')">发生金额</a>
								<logic:equal name="pagination" property="orderBy" value="money">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								办理日期
							</td>
							<td align="center" class=td2>
								业务状态
							</td>
						</tr>


						<logic:notEmpty name="collLoanbackcheckAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="collLoanbackcheckAF"
								property="list" indexId="i">
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
												value="<bean:write name="element" property="batch_num"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td>
										<bean:write name="element" property="batch_num" />
									</td>
									<td>
										<bean:write name="element" property="doc_num" />
									</td>
									<td>
										<bean:write name="element" property="contractId" />
									</td>
									<td>
										<bean:write name="element" property="borrowerName" />
									</td>
									<td>
										<bean:write name="element" property="org_id" format="0000000000"/>
									</td>
									<td>
										<bean:write name="element" property="org_name" />
									</td>
									<td>
										<bean:write name="element" property="emp_id" format="000000" />
									</td>
									<td>
										<bean:write name="element" property="emp_name" />
									</td>
									<td>
										<bean:write name="element" property="money" />
									</td>
									<td>
										<bean:write name="element" property="sett_date" />
									</td>
									<td>
										<bean:write name="element" property="pick_satatus" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>

						<logic:empty name="collLoanbackcheckAF" property="list">
							<tr>
								<td colspan="12" height="30" style="color:red">
									没有找到与条件相符合的结果！
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
											<jsp:include page="../../inc/pagination.jsp">
												<jsp:param name="url" value="collLoanbackcheckShowAC.do" />
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

								<logic:notEmpty name="collLoanbackcheckAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<logic:equal name="collLoanbackcheckAF" property="flag" value="1">
												<td width="70">
													<html:submit property="method" styleClass="buttona" onclick="return on_Submit(this);">
														<bean:message key="button.check.through" />
													</html:submit>
													&nbsp;
												</td>
												<td width="70" style="display:none">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.checkall" />
													</html:submit>
													&nbsp;
												</td>
												<td width="70">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.del.check" />
													</html:submit>
													&nbsp;
												</td>
												<td width="70" style="display:none">
													<html:submit property="method" styleClass="buttonb">
														<bean:message key="button.del.checkall" />
													</html:submit>
													&nbsp;
												</td>
												<td width="70" align="right">
													<html:submit property="method" styleClass="buttona"
														styleId="disp5">
														<bean:message key="button.print" />
													</html:submit>
												</td>
											</logic:equal>
											<logic:equal name="collLoanbackcheckAF" property="flag" value="2">
												<td width="70" align="center">
													<html:submit property="method" styleClass="buttona" onclick="return on_Submit(this);"
														styleId="disp5">
														<bean:message key="button.in.account" />
													</html:submit>
												</td>
											</logic:equal>
										</tr>
									</table>
								</logic:notEmpty>
								<logic:empty name="collLoanbackcheckAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<logic:equal name="collLoanbackcheckAF" property="flag" value="1">
												<td width="70">
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.check.through" />
													</html:submit>
													&nbsp;
												</td>
												<td width="70" style="display:none">
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.checkall" />
													</html:submit>
													&nbsp;
												</td>
												<td width="70">
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.del.check" />
													</html:submit>
													&nbsp;
												</td>
												<td width="70" style="display:none">
													<html:submit property="method" styleClass="buttonb"
														disabled="true">
														<bean:message key="button.del.checkall" />
													</html:submit>
													&nbsp;
												</td>
												<td width="70" align="right">
													<html:submit property="method" styleClass="buttona"
														styleId="disp5" disabled="true">
														<bean:message key="button.print" />
													</html:submit>
												</td>
											</logic:equal>
											<logic:equal name="collLoanbackcheckAF" property="flag" value="2">
												<td width="70" align="center">
													<html:submit property="method" styleClass="buttona"
														styleId="disp5" disabled="true">
														<bean:message key="button.in.account" />
													</html:submit>
												</td>
											</logic:equal>
										</tr>
									</table>
								</logic:empty>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>

</body>
</html:html>
