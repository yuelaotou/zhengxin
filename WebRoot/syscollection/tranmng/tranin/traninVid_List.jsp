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
var tr="tr0"; 
function checkAdjust(){
var x=confirm("确定撤消记录?");
		if(x){ 
		if(confirm("是否要打印？")){
		document.all.report.value="print";
	      }else{
		document.all.report.value="noprint";
	      }
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
			if(document.all.item(i).value=="打印"){
				s4=i;
			}
			if(document.all.item(i).value=="撤消调整"){
				s5=i;
			}
		
		}
	} 
	//初始状态按钮全部禁用
	document.all.item(s1).disabled="true";
	document.all.item(s2).disabled="true";
	document.all.item(s3).disabled="true";
	document.all.item(s4).disabled="true";
	document.all.item(s5).disabled="true";
		update();
}
function gettr(trindex) {
  tr=trindex;
  update();
}
function update() {
  	
  var status=document.getElementById(tr).childNodes[10].childNodes[0].innerHTML.trim();
     if(status=='登记'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
	    document.all.item(s5).disabled="true";
  	}else if(status=='确认'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
	    document.all.item(s5).disabled="";
  	}else if(status=='录入清册'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="";
	    document.all.item(s5).disabled="true";
  	}else if(status=='复核'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
        document.all.item(s4).disabled="";
	    document.all.item(s5).disabled="true";
  	}
  	else if(status=='入帐'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
        document.all.item(s4).disabled="";
	    document.all.item(s5).disabled="true";
  	}
}

function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000"
	onload="return loads(); reportErrors();" onContextmenu="return true">

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
													<a href="showTraninStayListAC.do" class=a2>待转入登记</a>
												</td>
												<td width="112" height="37"
													background="<%=path%>/img/buttong.gif" align="center"
													style="PADDING-top: 7px" valign="top">
													<a href="showTraninListAC.do" class=a2> 转入登记</a>
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
													<a href="#" class=a1>转入转出</a><font color="00B5DB">&gt;</font><a
														href="#" class=a1>转入维护</a>
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
							<html:form action="/showTraninVidListAC.do">
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
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=3 align="center">
									<tr>
										<td width="17%" class="td1" algin="center">
											转出单位编号
										</td>
										<td width="33%">
											<html:text name="traninVidAF" property="outOrgId"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch">
											</html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											转出单位名称
										</td>
										<td width="33%">
											<html:text name="traninVidAF" property="outOrgName"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1" algin="center">
											转入单位编号
										</td>
										<td width="33%">
											<html:text name="traninVidAF" property="inOrgId"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch">
											</html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											转入单位名称
										</td>
										<td width="33%">
											<html:text name="traninVidAF" property="inOrgName"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td class="td1" width="17%" algin="center">
											转出结算号
										</td>
										<td width="33%">
											<html:text name="traninVidAF" property="noteNum"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch"></html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											转出凭证编号
										</td>
										<td width="33%">
											<html:text name="traninVidAF" property="docNum"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td class="td1" width="17%" algin="center">
											转入日期
										</td>
										<td width="33%">
											<html:text name="traninVidAF" property="settDate"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch"></html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											转入状态
										</td>
										<td width="33%">
											<html:select property="tranStatus" styleClass="input4">
												<html:option value=""></html:option>
												<html:optionsCollection property="stateMap"
													name="traninVidAF" label="value" value="key" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
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
											<u>：<bean:write name="traninVidAF" property="count" />
											</u> 人数
											<u>：<bean:write name="traninVidAF" property="sumCount" />
											</u> 金额
											<u>：<bean:write name="traninVidAF"
													property="sumTranInAmount" />
											</u>
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
								<table width="95%" border="0"  bgcolor="#76BEE9" cellspacing="1" cellpadding="3"
									align="center">
									<tr>
										<td height="23"  align="center" bgcolor="C4F0FF" >&nbsp;</td>
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
											<a href="javascript:sort('tranInHead.tranOutOrg.orgInfo.id')">转出单位编号</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranInHead.tranOutOrg.orgInfo.id">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a
												href="javascript:sort('tranInHead.tranOutOrg.orgInfo.name')">转出单位名称</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranInHead.tranOutOrg.orgInfo.name">
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
									</tr>
									<logic:notEmpty name="traninVidAF" property="list"><%
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
								<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
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
														<bean:write name="element" property="tranOutOrg.id" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element"
															property="tranOutOrg.orgInfo.name" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="tranInOrg.id" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element"
															property="tranInOrg.orgInfo.name" />

													</p>
												</td>
												<td valign="top">
													<bean:write name="element" property="countTranInpeople" />
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="traninAll" />
													</p>
												</td>
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
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="traninVidAF" property="list">
										<tr>
											<td colspan="11" height="30" style="color:red">
												没有找到与条件相符合结果！
											</td>
										</tr>
										<tr>
											<td colspan="11"></td>
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
													<td>
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.del.tranin" />
														</html:submit>
													</td>
													<td>
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.update" />
														</html:submit>
													</td>
													<td>
														<html:submit property="method" styleClass="buttona"
															onclick="return checkDetele()">
															<bean:message key="button.delete" />
														</html:submit>
													</td>
													<td>
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.print" />
														</html:submit>
													</td>
													<td>
														<html:submit property="method" styleClass="buttona"
															onclick="return checkAdjust()">
															<bean:message key="button.del.adjust" />
														</html:submit>
													</td>
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
