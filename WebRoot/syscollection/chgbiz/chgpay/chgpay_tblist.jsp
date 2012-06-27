<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>

<%@ page
	import="org.xpup.hafmis.syscollection.chgbiz.chgpay.action.ChgpayTbSouwAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ChgpayTbSouwAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String type = (String) request.getAttribute("type");
%>
<html:html>
<head>
	<title>缴额调整</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
</head>
<script language="javascript"
	src="<%=request.getContextPath()%>/js/common.js">


<script language="javascript"></script>

<script language="javascript" type="text/javascript">
var s1="";
var s2="";
var s3="";
var s4="";
var s5="";
var s6="";
var tr="tr0"; 
var type=<%=type%>
function gettr(trindex) {
  tr=trindex;
  update1();
}
function update1() {
  	var status=document.getElementById(tr).childNodes[8].childNodes[0].innerHTML.trim();
  	if(status=="未启用"){
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
  	}
  	if(status=="启用"){	
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
  	}
  	if(type!=2){
	var picktypes=document.getElementById(tr).childNodes[9].childNodes[0].innerHTML.trim();
	if(picktypes=="未提取"){
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="";
	}
	if(picktypes=="已提取"){
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="true";
	}
	if(picktypes=="未提交"){
		document.all.item(s5).disabled="";
		document.all.item(s6).disabled="true";
	}
	}  
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	var count=document.chgpayListAF.listCount.value;

	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="修改"){
				s1=i;
			}
			if(document.all.item(i).value=="删除"){
				s2=i;
			}
			if(document.all.item(i).value=="启用"){
				s3=i;
			}
			if(document.all.item(i).value=="撤消启用"){
				s4=i;
			}
			if(type!=2){
			if(document.all.item(i).value=="提交数据"){
				s5=i;
			}
			if(document.all.item(i).value=="撤销提交数据"){
				s6=i;
			}
			}
		}
	}
   if(count!=1){
    	
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		if(type!=2){
			document.all.item(s5).disabled="true";
			document.all.item(s6).disabled="true";
		}
     }else{
		update1();
    }
}
function backErrors(errors){
   alert(errors);
}

function toPop(status){
	gotoOrgpop(status,'<%=request.getContextPath()%>');
}
  function executeAjax()
  {

    //var queryString = "chgpayTbFindAAC.do?";  
   // var orgid=document.chgpayListAF.elements["org.id"].value.trim();
   // var typetb=document.chgpayListAF.elements["typetb"].value;
   // var name=document.chgpayListAF.elements["org.orgInfo.name"].value.trim();
    var chgMonth=document.chgpayListAF.elements["chgMonth"].value.trim();

    if(chgMonth!=null&&chgMonth!=""){
		if(!checkYearMonth(chgMonth)){
			document.chgpayListAF.chgMonth.focus();
			return false;
		}
	}
	return true;
	
   // queryString = queryString + "org.id="+orgid+"&org.orgInfo.name="+name+"&chgMonth="+chgMonth+"&typetb="+typetb;
   // findInfo(queryString);
    //alert("id="+orgid+";name="+name+";month="+chgMonth);
	//return false;
   // showlist();
  }
  


/*function showlist() {
   document.Form1.submit();
}*/

function remove() {
 var x=confirm("是否确定删除该记录?");
	if(!x){
	   return false;
   }
}
function removeall() {
 var x=confirm("是否确定全部删除该记录?");
	if(!x){
	   return false;
   }
}
function checkDataUse() {
	  var chgMonth=document.chgpayListAF.elements["chgMonth"].value;
	document.all.chgMonth2.value=chgMonth;
 

	 var x=confirm("是否确定启用?");
	if(!x){	 
	   return false;
	}
}
function checkDataDeluse() {

	 var x=confirm("是否确定撤消启用?");
	if(!x){	 
	   return false;
	}
}
function gotoShow(){
	return false;
}
function checkDataReferring() {
  
	 var x=confirm("是否确定提交该笔业务?");
	if(!x){	 
	   return false;
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()"
	onContextmenu="return false">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="chgpayTbSouwAC.do" />
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
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" valign="top" style="PADDING-top: 7px">
										<a href="chgpayTaForwardURLAC.do" class=a2>办理变更</a>
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttonbl.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										变更维护
									</td>
								</tr>
							</table>
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
										<font color="00B5DB">变更业务 &gt; 缴额调整 </font>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="117">
										<b class="font14">单 位 信 息</b>
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
				<html:form action="/chgpayTbFindAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td width="22%">
								<html:text name="chgpayListAF" property="org.id"
									styleClass="input3" styleId="txtsearch"></html:text>

							</td>

							<td class="td1" width="17%">
								单位名称
							</td>
							<td width="33%">
								<html:text name="chgpayListAF" property="org.orgInfo.name"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								调整年月
							</td>
							<td width="22%" colspan="1">
								<html:text name="chgpayListAF" property="chgMonth"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>

							<td width="17%" class="td1"></td>
							<td width="33%" colspan="2">

							</td>
						</tr>
						<tr>
							<td>
								<html:hidden name="chgpayListAF" property="typetb"></html:hidden>
								<html:hidden name="chgpayListAF" property="listCount"
									styleClass="input3" styleId="txtsearch"></html:hidden>
							</td>

						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="26%" colspan="6">
							</td>
							<td width="17%" align="right">
								<html:submit onclick="return executeAjax();"
									styleClass="buttona">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="154">
										<b class="font14">缴额调整列表 </b>
									</td>
									<td width="750" height="22" align="center"
										background="<%=request.getContextPath()%>/img/bg2.gif">
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

				<html:form action="/chgpayTbMaintainAC" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								业务编号
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgPaymentPayment.org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPaymentPayment.org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								<a href="javascript:sort('chgPaymentPayment.org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPaymentPayment.org.orgInfo.name">
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
								<a href="javascript:sort('oldPaymenSum')">原应缴总额</a>
								<logic:equal name="pagination" property="orderBy"
									value="oldPaymenSum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								新应缴总额
							</td>

							<td align="center" class=td2>
								<a href="javascript:sort('chgPaymentPayment.chgMonth')">调整年月</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPaymentPayment.chgMonth">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								变更状态
							</td>

							<security:orgcan>
								<td width="10%" align="center" class=td2>
									提取状态
								</td>
							</security:orgcan>
						</tr>
						<logic:notEmpty name="chgpayListAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="chgpayListAF" property="list" id="element"
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
											<bean:write name="element" property="id" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="org.id" format="000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<a href="#"
												onClick="window.open('chgpayTaWindowForwardAC.do?id=<bean:write name="element" property="id"/>','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return gotoShow();">
												<bean:write name="element" property="org.orgInfo.name" />
											</a>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="oldPaymenSum" />
										</p>
									</td>

									<td valign="top">
										<p>
											<bean:write name="element" property="oldPayment" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="paySum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="chgMonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="wuhtChgStatus" />
										</p>
									</td>
									<security:orgcan>
										<td valign="top">
											<p>
												<bean:write name="element" property="temp_pick" />
											</p>
										</td>
									</security:orgcan>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="chgpayListAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
							<tr>
								<td colspan="11"></td>
							</tr>
						</logic:empty>
						<tr>
							<td colspan="9"></td>
						</tr>
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
												<jsp:param name="url" value="chgpayTbSouwAC.do" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr>
							<td align="center" height="6" colspan="1">
								<input type="Hidden" name="PKID"
									value="<bean:write name="chgpayListAF" property="id"/>">
								<input type="hidden" name="chgMonth2" value="">
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
												<bean:message key="button.update" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return remove()">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return checkDataUse()">
												<bean:message key="button.use" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return checkDataDeluse()">
												<bean:message key="button.deluse" />
											</html:submit>
										</td>
										<security:orgcan>
											<td>
												<html:submit property="method" styleClass="buttona"
													onclick="return checkDataReferring()">
													<bean:message key="button.referring.data" />
												</html:submit>
											</td>
										</security:orgcan>
										<security:orgcan>
											<td>
												<html:submit property="method" styleClass="buttonc">
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
</html:html>
