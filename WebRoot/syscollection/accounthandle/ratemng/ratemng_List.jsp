<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.ratemng.action.ShowRatemngListAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ShowRatemngListAC.PAGINATION_KEY);
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
var s1="";
var s2="";   
var s3="";
var tr="tr0"; 
function loads(){
document.all.officecode.value="";
document.all.usetime.value="";
document.all.ratetype.value="";
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="添加"){    
				s1=i;
			}
			if(document.all.item(i).value=="删除"){
				s2=i;
			}
			if(document.all.item(i).value=="启用"){
				s3=i;
			}
		}
	} 
	//初始状态按钮全部禁用
	document.all.item(s1).disabled="";
	document.all.item(s2).disabled="true";
	document.all.item(s3).disabled="true";
	update();
}
function gettr(trindex) {
  tr=trindex;
  update();
}
function search(){
	var temp_usetime=document.all.usetime.value;
	var usetime=document.rateShowAF.usetime;
	if(temp_usetime!=null&&temp_usetime!=""){
		if(usetime!=null&&usetime!=""){
			if(!checkDate(usetime)){
				document.all.usetime.focus();
				return false;
			}
		}
	}
}
function update() {
  var a=document.all.id;
  if(a!=undefined){
  	var status=document.getElementById(tr).childNodes[7].childNodes[0].innerHTML.trim();
  var loadsMassage=document.getElementById('loadsMassage').value.trim();
     if(status=='启用'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
  	   
  	}else if(status=='未启用'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		if(loadsMassage=='hi'){
		document.all.item(s3).disabled="";
  	    }else{
  	    document.all.item(s3).disabled="true";
  	    }
  	}
  }
  
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function checkDetele(){
	var x=confirm("确定删除记录?");
		if(x){ 
		  	return true;
		}else{
		  return false;
		}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads(); reportErrors();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="showRatemngListAC.do" />
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
													<font color="00B5DB">公积金利率维护</font>
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

					</tr>
					<tr>


						<td class=td3>
							<html:form action="/rateTaFindAC.do">
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=3 align="center">
									<tr>
										<td width="17%" class="td1" algin="center">
											办事处
										</td>
										<td width="22%">
											<html:select property="officecode" styleClass="input4"
												onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:options collection="officeList1" property="value"
													labelProperty="label" />
											</html:select>
										</td>
										<td class="td1" width="17%" algin="center">
											调整日期
										</td>
										<td width="22%">
											<html:text name="rateShowAF" property="usetime"
												onkeydown="enterNextFocus1();" styleClass="input3"
												styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1" algin="center">
											&#35843;&#25972;&#29366;&#24577;
										</td>
										<td width="22%">
											<html:select property="ratetype" styleClass="input4"
												onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:optionsCollection property="ratetypemap"
													name="rateShowAF" label="value" value="key" />
											</html:select>
										</td>
										<td width="17%" algin="center"></td>
										<td width="22%">
										</td>
									</tr>
									<tr>
										<td width="17%" algin="center"></td>
										<td width="22%">
										</td>
										<td width="17%" algin="center"></td>
										<td align="right">
											<html:submit property="method" styleClass="buttona"
												onclick="return search()">
												<bean:message key="button.search" />
											</html:submit>
										</td>
									</tr>

								</table>
							</html:form>
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="180">
													<b class="font14">公积金利率维护列表</b>
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
							<html:form action="/ratemngMaintainAC.do" style="margin: 0">
								<input type=hidden name="loadsMassage" id="loadsMassage"
									value="<bean:write name="ratemngAF" property="loadsMassage" />">
								<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
									cellpadding="3" align="center">
									<tr>
										<td height="23" align="center" bgcolor="C4F0FF">
											&nbsp;
										</td>

										<td align="center" class=td2>
											办事处
										</td>

										<td align="center" class=td2>
											<a
												href="javascript:sort('to_number(hafInterestRate.preRate)')">往年利率</a>
											<logic:equal name="pagination" property="orderBy"
												value="to_number(hafInterestRate.preRate)">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>


										<td align="center" class=td2>
											<a
												href="javascript:sort('to_number(hafInterestRate.curRate)')">本年利率</a>
											<logic:equal name="pagination" property="orderBy"
												value="to_number(hafInterestRate.curRate)">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>


										<td align="center" class=td2>
											活期是否分段
										</td>
										<td align="center" class=td2>
											定期是否分段
										</td>

										<td align="center" class=td2>
											<a
												href="javascript:sort('to_number(hafInterestRate.bizDate)')">调整时间</a>
											<logic:equal name="pagination" property="orderBy"
												value="to_number(hafInterestRate.bizDate)">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>

										<td align="center" class=td2>
											调整状态
										</td>
									</tr>
									<logic:notEmpty name="ratemngAF" property="list">
										<%
												int j = 0;
												String strClass = "";
										%>
										<logic:iterate name="ratemngAF" property="list" id="element"
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
												onMouseOut='gotoColorpp("<%=i%>",idAF);'
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
														<bean:write name="element" property="officecodeName" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="preRate" />
														%
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="curRate" />
														%
													</p>
												</td>
												<td valign="top">
													<p>
														<logic:equal name="element" property="isSubDemand"
															value="1">是</logic:equal>
														<logic:equal name="element" property="isSubDemand"
															value="2">否</logic:equal>
													</p>
												</td>
												<td valign="top">
													<p>
														<logic:equal name="element" property="isSubtRegular"
															value="1">是</logic:equal>
														<logic:equal name="element" property="isSubtRegular"
															value="2">否</logic:equal>
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="bizDate" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="showIsStart" />
													</p>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="ratemngAF" property="list">
										<tr>
											<td colspan="4" height="30" style="color:red">
												没有找到与条件相符合的结果！
											</td>
										</tr>
										<tr>
											<td colspan="7"></td>
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
															<jsp:param name="url" value="showRatemngListAC.do" />
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
															<bean:message key="button.add" />
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
															<bean:message key="button.use" />
														</html:submit>
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
