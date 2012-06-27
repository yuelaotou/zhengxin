<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.tranmng.tranout.action.Tran_showFindTbAC"%>
<%@ include file="/checkUrl.jsp"%>


<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			Tran_showFindTbAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String countsize = (String) request.getAttribute("countsize");
	String typetran = (String) request.getAttribute("typetran");
	String listnum = (String) request.getAttribute("listnum");
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>tranout_showtb.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="styles.css">



	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>

</head>

<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script language="javascript" src="<%=path%>/js/common.js"></script>

<link rel="stylesheet" href="css/index.css" type="text/css">
<script src="js/common.js"></script>

<script language="javascript">
var oldColor="#ffffff";                            //原来的颜色
var newColor="#E8FCFD";                     //要变成的颜色
function chgBGColor(oTD){
	oldColor=oTD.style.backgroundColor;//保存当前颜色
	oTD.style.backgroundColor=newColor;//改变表格颜色
	newColor=oldColor;                 //改变下次要变成的颜色
}
</script>

<script language="javascript">
var s1="";
var s2="";
var s3="";
var s4="";
var s5="";
var s6="";
var s6="";
var s7="";
var s8="";
var s9="";
var s11="";
var s22="";
var tr="tr0"; 
var ttt=<%=countsize%>;
var typetran=<%=typetran%>;
var listnum=<%=listnum%>;
function loads(){
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="完成转出"){
				s1=i;
			}
			if(document.all.item(i).value=="撤消转出"){
				s2=i;
			}
			if(document.all.item(i).value=="修改"){
				s3=i;
			}
			if(document.all.item(i).value=="删除"){
				s4=i;
			}
			if(document.all.item(i).value=="打印明细"){
				s5=i;
			}
			if(document.all.item(i).value=="打印列表"){
				s9=i;
			}
			if(document.all.item(i).value=="登记"){
				s11=i;
			}
			if(document.all.item(i).value=="撤销登记"){
				s22=i;
			}
			if(typetran==1){
				if(document.all.item(i).value=="提交数据"){
					s6=i;
				}
				if(document.all.item(i).value=="撤销提交数据"){
					s7=i;
				}
			}
			if(document.all.item(i).value=="打印凭证"){
				s8=i;
			}
			
		}
	}
	    document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s8).disabled="true";
		document.all.item(s9).disabled="true";
		document.all.item(s11).disabled="true";
		document.all.item(s22).disabled="true";
		if(ttt!=0){
		gettr('tr0');  //　默认加载
		}		
	
}
//转出维护--点鼠标判断状态
function gettr(trindex) {
  tr=trindex;
  update1();
}
function update1() {
	var bizStatus="";
	var pickupStatus="";
	if(listnum==0){
   		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s8).disabled="true";
		document.all.item(s9).disabled="true";
		document.all.item(s11).disabled="true";
		document.all.item(s22).disabled="true";
		if(typetran==1){
			document.all.item(s6).disabled="true";
			document.all.item(s7).disabled="true";
		}
	}else{
		document.all.item(s9).disabled="";
		if(typetran==2){
		 	bizStatus=document.getElementById(tr).cells[12].childNodes[0].innerHTML.trim();
		}else{
		 	bizStatus=document.getElementById(tr).cells[11].childNodes[0].innerHTML.trim();
		 	pickupStatus=document.getElementById(tr).cells[11].childNodes[0].innerHTML.trim();
		}
	  	if(bizStatus=="录入清册"){
			document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
			document.all.item(s3).disabled="";
			document.all.item(s4).disabled="";
			document.all.item(s5).disabled="";		
			document.all.item(s8).disabled="";
			document.all.item(s11).disabled="";
			document.all.item(s22).disabled="true";
	  	}
	  	if(bizStatus=="登记"){	
			document.all.item(s1).disabled="";
			document.all.item(s2).disabled="true";
			document.all.item(s3).disabled="true";
			document.all.item(s4).disabled="true";
			document.all.item(s5).disabled="";
			document.all.item(s8).disabled="";
			document.all.item(s11).disabled="true";
			document.all.item(s22).disabled="";
	  	}  
	  	if(bizStatus=="确认"){	
			document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="";
			document.all.item(s3).disabled="true";
			document.all.item(s4).disabled="true";
			document.all.item(s5).disabled="";
			document.all.item(s8).disabled="";
			document.all.item(s11).disabled="true";
			document.all.item(s22).disabled="true";
	  	}  
	  	if(bizStatus=="复核"){	
			document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
			document.all.item(s3).disabled="true";
			document.all.item(s4).disabled="true";
			document.all.item(s5).disabled="";
			document.all.item(s8).disabled="";
			document.all.item(s11).disabled="true";
			document.all.item(s22).disabled="true";
	  	}  
	  	if(bizStatus=="入账"){	
			document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
			document.all.item(s3).disabled="true";
			document.all.item(s4).disabled="true";
			document.all.item(s5).disabled="";
			document.all.item(s8).disabled="";
			document.all.item(s11).disabled="true";
			document.all.item(s22).disabled="true";
	  	}
	    if(bizStatus==" " && bizStatus=='null'){	
	  	    document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
			document.all.item(s3).disabled="true";
			document.all.item(s4).disabled="true";
			document.all.item(s5).disabled="";
			document.all.item(s8).disabled="";
			document.all.item(s11).disabled="true";
			document.all.item(s22).disabled="true";
			if(typetran==1){
			document.all.item(s6).disabled="true";
			document.all.item(s7).disabled="true";
			}
	  	} 
	  	if(typetran==1){
		  	if(pickupStatus=="未提取"){
				document.all.item(s6).disabled="true";
				document.all.item(s7).disabled="";
			}
			if(pickupStatus=="已提取"){
				document.all.item(s6).disabled="true";
				document.all.item(s7).disabled="true";
			}
			if(pickupStatus=="未提交"){
				document.all.item(s6).disabled="";
				document.all.item(s7).disabled="true";
			}
		}
  	}
}
//删除提示
function remove()
{ 
  var x = confirm("是否删除!!");
  if(!x){
    return false;
  }
 }
 
 //撤消转出
 function deltran()
 {
   var x = confirm("该笔业务以办理，是否撤消!!");
   if(!x){
     return false;
   }
 }

function tc3()
{
   return false;
}


function test()
{ 
  alert(list);
}
function getCheckStatus(){
	var status;
	var i=0;
	if (document.getElementsByName("id").length!=1){
		for(i;i<document.getElementsByName("id").length;i++){  
			if(document.idAF.id[i].checked){
				status=document.idAF.id[i].value;
			}
		}
	}else{
		status=document.idAF.id.value;
	}
		return status;
}
 function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	if(message=='yes'){
		if(!confirm("该单位已经进行转入登记，是否确定撤消！")){
			return false;
		}else{
			var id=getCheckStatus();
			document.location.href="tranoutTbBackAC.do?headid="+id;
		}
	}else{
		alert(message);
	}
	</logic:messagesPresent>
} 
function gotoPrint(){
	
		document.all.report.value="noprint";
	
	
}  

function datescc(){
	var dates = document.tranTbAF.dates;
	if(checkDate(dates)){
		document.tranTbAF.datesa.value=dates.value;
	}else{
		document.tranTbAF.dates.focus();
	}
}


</script>

<body onContextmenu="return false"
	onload="return loads(),reportErrors();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="tran_showFindTbAC.do" />
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
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="tranoutTaForwardURLAC.do" class=a2>办理转出</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										转出维护
									</td>
								</tr>
							</table>
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
										<font color="00B5DB">转入转出<font color="00B5DB">&gt;&gt;转出维护</font>
									</td>
									<td width="15" class=font14>
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

				<html:form action="/tran_FindTbAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">

								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">查 询 条 件</b>
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
						cellpadding=0 align="center">
						<tr>
							<td width="20%" class="td1">
								转出单位编号
							</td>
							<td width="29%" colspan="3">
								<html:text name="tranTbAF" property="outOrgId"
									styleClass="input3" />

							</td>
							<td width="14%" class="td1">
								转出单位名称
							</td>
							<td width="37%" colspan="3">
								<html:text name="tranTbAF" property="outOrgname"
									styleClass="input3" maxlength="50" />
							</td>
						</tr>
						<tr>
							<td width="14%" class="td1">
								转入单位编号
							</td>
							<td width="35%" colspan="3">

								<html:text name="tranTbAF" property="inOrgId"
									styleClass="input3" />
							</td>
							<td width="14%" class=td1>
								转入单位名称
							</td>
							<td width="37%" colspan="3">
								<html:text name="tranTbAF" property="inOrgName"
									styleClass="input3" maxlength="50" />
							</td>
						</tr>
						<tr>
							<td width="14%" class="td1">
								转出结算号
							</td>
							<td width="35%" colspan="3">
								<html:text name="tranTbAF" property="note_num"
									styleClass="input3" maxlength="50" />
							</td>
							<td width="14%" class=td1>
								转出凭证编号
							</td>
							<td width="37%" colspan="3">
								<html:text name="tranTbAF" property="doc_num"
									styleClass="input3" maxlength="50" />
							</td>
						</tr>
						<tr>
							<td width="14%" class="td1">
								转出日期
							</td>
							<td width="15%">
								<html:text name="tranTbAF" property="dates" styleClass="input3"
									maxlength="8" onkeydown="enterNextFocus1();" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="tranTbAF" property="datesa" styleClass="input3"
									maxlength="8" />
							</td>
							<td width="14%" class=td1>
								业务状态
							</td>
							<td width="37%" class=input3 colspan="3">
								<html:select property="payStatus" styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="map" name="tranTbAF"
										label="value" value="key" />
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
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/tran_TbMaintainAC.do" method="post">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计
								<u>：人数 <bean:write name="tranTbAF" property="sum_counts" />
								</u>转出金额
								<u> ：<bean:write name="tranTbAF" property="sum_salary" /> </u>
								<security:orgcannot>利息
							<u> ：<bean:write name="tranTbAF" property="sum_Interst" /> </u>转出总额
							<u> ：<bean:write name="tranTbAF" property="sum_sum" /> </u>
								</security:orgcannot>
								<input type="hidden" name="report" value="">
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="142">
											<b class="font14">办理转出列表 </b>
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
							<td align="center" height="23" bgcolor="C4F0FF"></td>
							<td rowspan="2" align="center" class=td2>
								<a href="javascript:sort('tot.noteNum')">结算号</a>
								<logic:equal name="pagination" property="orderBy"
									value="tot.noteNum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td rowspan="2" align="center" class=td2>
								<a href="javascript:sort('tot.docNum')">凭证编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="tot.docNum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td colspan="2" align="center" class=td2>
								转出单位
							</td>
							<td colspan="2" align="center" class=td2>
								转入单位
							</td>
							<td rowspan="2" align="center" class=td2>
								人数
							</td>
							<td rowspan="2" align="center" class=td2>
								金额
							</td>
							<security:orgcannot>
								<td rowspan="2" align="center" class=td2>
									利息
								</td>
								<td rowspan="2" align="center" class=td2>
									总额
								</td>
							</security:orgcannot>
							<td rowspan="2" align="center" class=td2>
								日期
							</td>
							<td rowspan="2" align="center" class=td2>
								状态
							</td>
							<security:orgcan>
								<td rowspan="2" align="center" class=td2>
									提取状态
								</td>
							</security:orgcan>
						</tr>
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('tot.tranOutOrg.id')">编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="tot.tranOutOrg.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('tot.tranOutOrg.orgInfo.name')">名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="tot.tranOutOrg.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('tot.tranInOrg.id')">编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="tot.tranInOrg.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('tot.tranInOrg.orgInfo.name')">名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="tot.tranInOrg.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
						</tr>
						<logic:notEmpty name="tranTbAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="tranTbAF" property="list" id="element"
								indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);gettr("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td align="center">
										<bean:write name="element" property="noteNum" />
									</td>

									<td align="center">
										<bean:write name="element" property="docNum" />
									</td>
									<td align="center">
										<bean:write name="element" property="orgOutid"
											format="0000000000" />
									</td>
									<td align="center">
										<p>
											<a href="#"
												onclick="window.open('tranoutTcForwardURLAC.do?headid=<bean:write name="element" property="id" />','window','height=450,width=700,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes, status=yes');return tc3();">
												<bean:write name="element" property="orgOutName" /> </a>
									</td>
									<td align="center">
										<bean:write name="element" property="orgInid"
											format="0000000000" />
									</td>

									<td align="center">
										<bean:write name="element" property="orgInName" />
									</td>

									<td align="center">
										<bean:write name="element" property="counts" />
									</td>

									<td align="center">
										<bean:write name="element" property="money" />
									</td>

									<security:orgcannot>
										<td align="right">
											<bean:write name="element" property="interest" />
										</td>

										<td align="center">
											<bean:write name="element" property="sumMoney" />
										</td>
									</security:orgcannot>
									<td align="center">
										<bean:write name="element" property="setDate" />
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="status" />
										</p>
									</td>
									<security:orgcan>
										<td valign="top">
											<p>
												<bean:write name="element" property="temp_pickStatus" />
											</p>
										</td>
									</security:orgcan>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="tranTbAF" property="list">
							<tr>
								<td colspan="19">
									<font color="red" size="2pt">没有找到与条件相符合结果！</font>
								</td>
							</tr>
						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr>
							<td>
								<input type="Hidden" name="nrOfElements"
									value="<bean:write name="pagination" property="nrOfElements"/>">
							</td>
						</tr>
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
												<jsp:param name="url" value="tran_showFindTbAC.do" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="40%" align="center">
						<tr>
							<td>
								<html:submit property="method" styleClass="buttona"
									onclick="return gotoPrint();">
									<bean:message key="button.over.tranout" />
								</html:submit>
							</td>
							<td>
								<html:submit property="method" styleClass="buttona"
									onclick="return deltran();">
									<bean:message key="button.del.tranout" />
								</html:submit>
							</td>
							<td>
								<html:submit property="method" styleClass="buttona">
									<bean:message key="button.update" />
								</html:submit>
							</td>
							<td>
								<html:submit property="method" styleClass="buttona"
									onclick="return remove();">
									<bean:message key="button.delete" />
								</html:submit>
							</td>
							<td>
								<html:submit property="method" styleClass="buttona">
									<bean:message key="button.print.machine_1" />
								</html:submit>
							</td>
							<td>
								<html:submit property="method" styleClass="buttona">
									<bean:message key="button.print.tao_1" />
								</html:submit>
							</td>
							<td width="70">
								<html:submit property="method" styleClass="buttonb">
									<bean:message key="button.printList" />
								</html:submit>
							</td>
							<td width="70">
								<html:submit property="method" styleClass="buttona">
									<bean:message key="button.dengji.yangg" />
								</html:submit>
							</td>
							<td width="70">
								<html:submit property="method" styleClass="buttonb">
									<bean:message key="button.dengji.chexiao.yangg" />
								</html:submit>
							</td>
							<!--  
							<td>
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
							</security:orgcan>


							<security:orgcan>
								<td width="70">
									<html:submit property="method" styleClass="buttonc">
										<bean:message key="button.pproval.data" />
									</html:submit>
								</td>
							</security:orgcan>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
