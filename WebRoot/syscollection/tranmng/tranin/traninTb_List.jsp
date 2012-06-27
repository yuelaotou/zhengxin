<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowTraninVidListAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ShowTraninVidListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>

<html:html>
<head>
	<title>tranin</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">


	
	<script language="javascript"></script>
	<script language="javascript" type="text/javascript">
var s1="";
var s2="";   
var s3="";
var s4="";
var s5="";
var s6=""
var s7=""
var s8=""
var s9=""
var tr="tr0"; 
var isorgOrCenter="<%=request.getSession().getAttribute("isorgOrCenter")%>";
function checkAdjust(){
var x=confirm("确定撤消记录?");
		if(x){ 
		  	return true;	
		}else{
		  return false;
		}
}



function checkDetele(){
	var x=confirm("确定删除记录?");
		if(x){ 
		  	return true;
		}else{
		  return false;
		}
}
function loads(){
var count = "<bean:write name="pagination" property="nrOfElements"/>";
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="完成转入登记"){    
				s1=i;
			}
			if(document.all.item(i).value=="修改"){
				s2=i;
			}
			if(document.all.item(i).value=="删除"){
				s3=i;
			}
			if(document.all.item(i).value=="机打凭证"){
				s4=i;
			}
			if(document.all.item(i).value=="撤消转入登记"){
				s5=i;
			}
			if(document.all.item(i).value=="认证单打印"){
				s8=i;
			}
			if(document.all.item(i).value=="打印列表"){
				s9=i;
			}
		    if(isorgOrCenter==1){
				if(document.all.item(i).value=="提交数据"){
					s6=i;
				}
				if(document.all.item(i).value=="撤销提交数据"){
					s7=i;
				}
			}
		}
	} 
	//初始状态按钮全部禁用
	document.all.item(s1).disabled="true";
	document.all.item(s2).disabled="true";
	document.all.item(s3).disabled="true";
	document.all.item(s4).disabled="true";
	document.all.item(s5).disabled="true";
	document.all.item(s8).disabled="true";
	if(count==0){
		
	document.all.item(s9).disabled="true";
	  if(isorgOrCenter==1){
				document.all.item(s6).disabled="true";
	            document.all.item(s7).disabled="true";
			}
	}else{document.all.item(s9).disabled="";
		update();
	}	
}
function tc3()
{
   return false;
}
function gettr(trindex) {
  tr=trindex;
  update();
}
function update() {
  	
  var status=document.getElementById(tr).childNodes[10].childNodes[0].innerHTML.trim();
  var outid=document.getElementById(tr).childNodes[5].childNodes[0].innerHTML.trim();
  
     if(status=='登记'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
	    document.all.item(s5).disabled="true";
		document.all.item(s8).disabled="";
  	}else if(status=='确认'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
		document.all.item(s8).disabled="";
	    document.all.item(s5).disabled="";
  	}else if(status=='录入清册'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
		document.all.item(s8).disabled="true";
	    document.all.item(s5).disabled="true";
  	}else if(status=='复核'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
        document.all.item(s4).disabled="";
        document.all.item(s8).disabled="";
	    document.all.item(s5).disabled="true";
  	}
  	else if(status=='入账'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
        document.all.item(s4).disabled="";
        document.all.item(s8).disabled="";
	    document.all.item(s5).disabled="true";
  	}
  	if(outid!='' && outid!=null){
  		document.all.item(s8).disabled="true";
  	}else{
  		document.all.item(s8).disabled="";
  	}
  	if(isorgOrCenter==1){
	var statusPickup=document.getElementById(tr).childNodes[11].childNodes[0].innerHTML.trim();
		if(statusPickup=='未提交'){
		document.all.item(s6).disabled="";
		document.all.item(s7).disabled="true";
		}else if(statusPickup=='未提取'){
		document.all.item(s6).disabled="true";
		document.all.item(s7).disabled="";
		}else if(statusPickup=='已提取'){
		document.all.item(s6).disabled="true";
		document.all.item(s7).disabled="true";
		}
	}
}

function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function datescc(){
	var settDate = document.traninVidAF.settDate;
	if(checkDate(settDate)){
		document.traninVidAF.settDatea.value=settDate.value;
	}else{
		document.traninVidAF.settDate.focus();
	}
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" onload="loads(); reportErrors();">

	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="showTraninVidListAC.do" />
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
										<table border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="112" height="37"
													background="<%=path%>/img/buttong.gif" align="center"
													style="PADDING-top: 7px" valign="top">
													<a href="showTraninStayListURLAC.do" class=a2>待转入登记</a>
												</td>
												<td width="112" height="37"
													background="<%=path%>/img/buttong.gif" align="center"
													style="PADDING-top: 7px" valign="top">
													<a href="showTraninListURLAC.do" class=a2> 转入登记</a>
												</td>
												<td width="112" height="37"
													background="<%=path%>/img/buttonbl.gif" align="center"
													valign="top" style="PADDING-top: 7px">
													转入维护
												</td>
											</tr>
										</table>
									<td background="<%=path%>/img/table_bg_line.gif" align="right">
										<table width="300" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="37">
													<img src="<%=path%>/img/title_banner.gif" width="37"
														height="24">
												</td>
												<td width="189" class=font14 bgcolor="#FFFFFF"
													align="center" valign="bottom">
													<font color="00B5DB">转入信息</font><font color="00B5DB">&gt;&gt;</font><font
														color="00B5DB">转入维护</font>
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
							<html:form action="/findTraninVidMaintainListAC.do">
								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td height="35">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="22" bgcolor="#CCCCCC" align="center"
														width="117">
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
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=3 align="center">
									<tr>
										<td width="17%" class="td1" algin="center">
											转出单位编号
										</td>
										<td width="33%" colspan="3">
											<html:text name="traninVidAF" property="outOrgId"
												styleClass="input3" onkeydown="enterNextFocus1();"
												styleId="txtsearch">
											</html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											转出单位名称
										</td>
										<td width="33%" colspan="3">
											<html:text name="traninVidAF" property="outOrgName"
												styleClass="input3" onkeydown="enterNextFocus1();"
												styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1" algin="center">
											转入单位编号
										</td>
										<td width="33%" colspan="3">
											<html:text name="traninVidAF" property="inOrgId"
												styleClass="input3" onkeydown="enterNextFocus1();"
												styleId="txtsearch">
											</html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											转入单位名称
										</td>
										<td width="33%" colspan="3">
											<html:text name="traninVidAF" property="inOrgName"
												styleClass="input3" onkeydown="enterNextFocus1();"
												styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td class="td1" width="17%" algin="center">
											转出结算号
										</td>
										<td width="33%" colspan="3">
											<html:text name="traninVidAF" property="noteNum"
												styleClass="input3" onkeydown="enterNextFocus1();"
												styleId="txtsearch"></html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											转出凭证编号
										</td>
										<td width="33%" colspan="3">
											<html:text name="traninVidAF" property="docNum"
												styleClass="input3" onkeydown="enterNextFocus1();"
												styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td class="td1" width="17%" algin="center">
											转入日期
										</td>
										<td width="15%">
											<html:text name="traninVidAF" property="settDate"
												styleClass="input3" onkeydown="enterNextFocus1();"
												styleId="txtsearch"></html:text>
											<!-- onblur="datescc();"  -->
										</td>
										<td width="3%">
											至
										</td>
										<td width="15%">
											<html:text name="traninVidAF" property="settDatea"
												styleClass="input3" onkeydown="enterNextFocus1();"
												styleId="txtsearch"></html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											转入状态
										</td>
										<td width="33%" colspan="3">
											<html:select property="tranStatus" styleClass="input4"
												onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:optionsCollection property="stateMap"
													name="traninVidAF" label="value" value="key" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td width="14%" class="td1">
											归集银行
										</td>
										<td width="29%" colspan="3">
											<html:select property="collBankId" styleClass="input4">
												<html:option value=""></html:option>
												<html:options collection="collBankList1" property="value"
													labelProperty="label" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td colspan="7"></td>
										<td align="right">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.search" />
											</html:submit>
										</td>
									</tr>
								</table>
							</html:form>
							<html:form action="/traninVidMaintainAC.do" style="margin: 0">
								<table border="0" width="95%" id="table1" align="center"
									border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class=h4>
											合计：笔数
											<u>：<bean:write name="traninVidAF" property="count" /> </u>
											人数
											<u>：<bean:write name="traninVidAF" property="sumCount" />
											</u> 金额
											<security:orgcannot>
												<u>：<bean:write name="traninVidAF"
														property="sumTranInAmount" /> </u>
											</security:orgcannot>
											<security:orgcan>
												<u>：<bean:write name="traninVidAF"
														property="tranInSumBalance" /> </u>
											</security:orgcan>
										</td>
									</tr>
								</table>
								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td height="35">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="22" bgcolor="#CCCCCC" align="center"
														width="154">
														<b class="font14">单位列表</b>
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
								<input type="hidden" name="report" value="">
								<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
									cellpadding="3" align="center">
									<tr>
										<td height="23" align="center" bgcolor="C4F0FF">
											&nbsp;
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('tranInHead.noteNum')">结算号</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranInHead.noteNum">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('tranInHead.docNum')">凭证编号</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranInHead.docNum">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('tranInHead.tranInOrg.orgInfo.id')">转入单位编号</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranInHead.tranInOrg.orgInfo.id">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a
												href="javascript:sort('tranInHead.tranInOrg.orgInfo.name')">转入单位名称</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranInHead.tranInOrg.orgInfo.name">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>

										</td>
										<td align="center" class=td2>
											转出单位编号
										</td>
										<td align="center" class=td2>
											转出单位名称

										</td>

										<td align="center" class=td2>
											人数
										</td>
										<td align="center" class=td2>
											转入总额

										</td>
										<td align="center" class=td2>
											转入日期
										</td>
										<td align="center" class=td2>
											业务状态
										</td>
										<security:orgcan>
											<td align="center" class=td2>
												提取状态
											</td>
										</security:orgcan>
									</tr>
									<logic:notEmpty name="traninVidAF" property="list">
										<%
												int j = 0;
												String strClass = "";
										%>
										<logic:iterate name="traninVidAF" property="list" id="element"
											indexId="i">
											<%
														j++;
														if (j % 2 == 0) {
															strClass = "td8";
														} else {
															strClass = "td9";
														}
											%>
											<tr id="tr<%=i%>" onclick='document.all.s<%=i%>.checked="true";'
												class="<%=strClass%>">
												<td valign="top">
													<p align="left">
														<input id="s<%=i%>" type="radio" name="id"
															value="<bean:write name="element" property="id"/>"
															<%if(new Integer(0).equals(i)) out.print("checked"); %>>
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="noteNum" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="docNum" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="tranInOrg.id"
															format="0000000000" />
													</p>
												</td>
												<td valign="top">
													<a href="#"
														onclick="window.open('showTraninListAAC.do?taninHeadIdAAC=<bean:write name="element"
															property="id" />','window','height=450,width=700,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes,location=yes, status=yes,location=no,status=no');return tc3();">

														<bean:write name="element"
															property="tranInOrg.orgInfo.name" /> </a>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="tranOutOrg.id"
															format="0000000000" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element"
															property="tranOutOrg.orgInfo.name" />
													</p>
												</td>

												<td valign="top">
													<bean:write name="element" property="countTranInpeople" />
												</td>
												<security:orgcan>
													<td valign="top">
														<p>
															<bean:write name="element" property="tranInSumBalance" />
														</p>
													</td>
												</security:orgcan>
												<security:orgcannot>
													<td valign="top">
														<p>
															<bean:write name="element" property="traninAll" />
														</p>
													</td>
												</security:orgcannot>
												<td valign="top">
													<p>
														<bean:write name="element" property="settDate" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="status" />
													</p>
												</td>
												<security:orgcan>
													<td valign="top">
														<p>
															<bean:write name="element" property="pickUpStatus" />
														</p>
													</td>
												</security:orgcan>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="traninVidAF" property="list">
										<tr>
											<td colspan="12" height="30" style="color:red">
												没有找到与条件相符合结果！
											</td>
										</tr>
										<tr>
											<td colspan="12"></td>
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
															<jsp:param name="url" value="showTraninVidListAC.do" />
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
													<td width="90">
														<html:submit property="method" styleClass="buttonb">
															<bean:message key="button.del.tranin" />
														</html:submit>
													</td>
													<td width="90">
														<html:submit property="method" styleClass="buttonb"
															onclick="return checkAdjust()">
															<bean:message key="button.adjust.tranin" />
														</html:submit>
													</td>
													<td width="70">
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.update" />
														</html:submit>
													</td>
													<td width="70">
														<html:submit property="method" styleClass="buttona"
															onclick="return checkDetele()">
															<bean:message key="button.delete" />
														</html:submit>
													</td>
													<td width="70">
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.print.machine" />
														</html:submit>
													</td>
													<td width="90">
														<html:submit property="method" styleClass="buttonb">
															<bean:message key="button.print.renzhengdan" />
														</html:submit>
													</td>
													<td width="70">
														<html:submit property="method" styleClass="buttonb">
															<bean:message key="button.printList" />
														</html:submit>
													</td>
													<!--  
													<td width="70">
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.printall" />
														</html:submit>
													</td>-->
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
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</html:form>
				</table>
			</td>
		</tr>
	</table>
</body>
</html:html>
