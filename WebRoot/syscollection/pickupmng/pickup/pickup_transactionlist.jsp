<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="org.xpup.hafmis.syscollection.pickupmng.pickup.action.PickupShowAC"%>
<%@ page import="org.xpup.common.util.Pagination"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Pagination pagination = (Pagination) request.getSession()
			.getAttribute(PickupShowAC.PAGINACTION_KEY);
	request.setAttribute("pagination", pagination);
	String type = (String) request.getAttribute("type");
%>
<html:html>
<head>
	<title>提取列表</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	
	<script language="javascript" src="js/common.js"></script>
</head>
<script language="javascript"></script>
<script language="javascript" type="text/javascript">
var s1="";
var s2="";
var s3="";
var s4="";
var s5="";
var s6="";
var s7="";
var s8="";
var s9="";
var s10="";
var f="";
var f1="";
var type=<%=type%>
function gotoSelect(state){
	gotoOrgpop(state,"<%=path%>" ,'0');
}
function executeAjax(){
	var queryString = "pickupTransactionAAC.do?";
    var id = document.pickupGetCompanyIdAF.elements["id"].value.trim();
    queryString = queryString + "id="+id; 	     
	findInfo(queryString);
}
function excHz1(url){
 	var l=window.screen.width ;
  	var w= window.screen.height; 
  	var al=l/2-350;
  	var aw=w/2-350;
  	var ur="../../../syscommon/picture/browse.jsp?path="+url;
  	var newwin=window.open(ur,"homeWin","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=800,height=600,top="+aw+",left="+al+"");
  	newwin.focus();
}
function loads1(){
	var counts="<bean:write name="pagination" property="nrOfElements" />";
  	var pdpb1=0;
  	var pdpb2=0;
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="批量导入"){
				s1=i;
			}
			if(document.all.item(i).value=="批量导出"){
				s2=i;
			}
			if(document.all.item(i).value=="添加"){
				s3=i;
			}
			if(document.all.item(i).value=="删除"){
				s4=i;
			}
			if(document.all.item(i).value=="全部删除"){
				s5=i;
			}
			if(document.all.item(i).value=="完成提取"){
				s6=i;
			}
			if(document.all.item(i).value=="扫描"){
				s10=i;
			}
			<security:orghave>
			if(type==2){
			if(document.all.item(i).value=="提取单位版数据"){
				s7=i;
			}
			pdpb2=11;
			}
		    if(type==1){
			if(document.all.item(i).value=="提交数据"){
				s8=i;
			}
			if(document.all.item(i).value=="撤销提交数据"){
				s9=i;
			}
			pdpb1=11;
			}
			</security:orghave>
		}
	}
	var id = document.pickupGetCompanyIdAF.elements["id"].value;
	if(id != ""){//把单位编号转换成6位数的样子
	    document.pickupGetCompanyIdAF.elements["id"].value=format(id)+id;
	}
	var orgname = document.pickupGetCompanyIdAF.elements["name"].value;
	if(orgname !=""){
	document.getElementById("add").focus();
	}
	var initiaButton = document.getElementById("buttonState").value;
	if(initiaButton=='状态!=5显示按钮'){
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="true";
		if(type==1){
			if(pdpb1=='11'){
				document.all.item(s8).disabled="true";
				document.all.item(s9).disabled="true";
				document.all.item(s10).disabled="true";
			}
		}
	}else if(initiaButton=="不是特殊提取"){
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="true";
		document.all.item(s10).disabled="true";
		if(type==2){
			if(pdpb2=='11'){
				document.all.item(s7).disabled="";
			}
		}
		if(type==1){
			if(pdpb1=='11'){
				document.all.item(s8).disabled="true";
				document.all.item(s9).disabled="true";
				document.all.item(s10).disabled="true";
			}
		}
	}else if(initiaButton=="显示"){
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="";
		document.all.item(s5).disabled="";
		document.all.item(s6).disabled="";
		if(type==2){
			if(pdpb2=='11'){
				document.all.item(s7).disabled="true";
			}
		}
		if(type==1){
			if(pdpb1=='11'){
		 	    var version=document.getElementById("versionflag").value;
		 	    if(version=='1'){
			   		document.all.item(s8).disabled="true";
				   	document.all.item(s9).disabled="";
				   	document.all.item(s10).disabled="";}
			   	if(version=='2'){
				   	document.all.item(s8).disabled="true";
				   	document.all.item(s9).disabled="true";
				   	document.all.item(s10).disabled="true";}
			   	if(version=='0'){
				   	document.all.item(s8).disabled="";
				   	document.all.item(s9).disabled="true";
				   	document.all.item(s10).disabled="true";
				}
			}
		}
	}else{
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="true";
		document.all.item(s10).disabled="true";
		if(type==2){
			if(pdpb2=='11'){
				document.all.item(s7).disabled="true";
			}
		}
		if(type==1){
			if(pdpb1=='11'){
				document.all.item(s8).disabled="true";
				document.all.item(s9).disabled="true";
				document.all.item(s10).disabled="true";
			}
		}
	}
	if(counts!='0'){
		if(type==2){
			if(pdpb2=='11'){
				document.all.item(s7).disabled="true";
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
function huiche(){
	if(event.keyCode==13){
		event.keyCode=9;
		findName();
	}
}
function findName() {
	var queryString = "pickupTransactionAAC.do?";
	var id = document.pickupGetCompanyIdAF.elements["id"].value.trim();
	if(id==""){
		alert("请你输入编号");
	}else if(isNaN(id) || id.indexOf(".")>0){
		alert("请你输入合法的整数数字");
	}else{
		queryString = queryString + "id="+id; 	     
		findInfo(queryString);
	}
}
function reportConfirm(){
	if(confirm("是否要打印?")){
		document.pickupMaintainAF.report.value="print";
	}else{
		document.all.report.value="notprint";
	}
	document.all.notenum.value=document.pickupGetCompanyIdAF.elements["noteNumber"].value.trim();
}
function reportConfirmYG(){
	document.all.notenum.value=document.pickupGetCompanyIdAF.elements["noteNumber"].value.trim();
}
function displays(f,f1){
  if((f!="")&&(f1==""))
  {
  	alert('该单位存在未记账的错账调整业务！');
  }
  	if((f=="")&&(f1!=""))
  {
  	alert('该单位存在未记账的转出业务！');
  }
  	if((f!="")&&(f1!=""))
  {
  	alert('该单位存在未记账的错账调整和转出业务！');
  }
  showlist(); 
}
function showlist() {
  document.Form1.submit();
}
function addNoteNumber(){
	var note = document.pickupGetCompanyIdAF.elements["noteNumber"].value.trim();
	document.pickupMaintainAF.elements["noteNumber"].value = note;
	var num = document.pickupGetCompanyIdAF.elements["other_card_num"].value.trim();
	document.pickupMaintainAF.elements["other_card_num"].value = num;
}
function exports(){
	window.open ('<%=path%>/syscollection/pickupmng/pickup/pickupexport_array.jsp','newwindow','height=190,width=350,top='+(window.screen.availHeight-190)/2+',left='+(window.screen.availWidth-350)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	return false;
}
function delConfirm(){
	if(confirm("请确认是否要删除所选择的记录!")){
		getradiovalue();
		return true;
	}else{
		return false;
	}
}
function getradiovalue(){
	var val = "";
	var theForm = document.forms[2];
	for(var i=0;i<theForm.elements.length;i++){
		if(theForm.elements[i].type == "radio"){
			if(theForm.elements[i].checked){
				val = theForm.elements[i].value;
				}
		}
	}
	document.all.eeemmmpppid.value = val;
}
</script>
<body bgcolor="#FFFFFF" text="#000000"
	onload="loads1();return reportErrors();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="pickupShowAC.do" />
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
										background="<%=path%>/img/buttonbl.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										办理提取
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="vindicateActionAC.do" class=a2>提取维护</a>
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
										<font color="00B5DB">提取管理&gt;办理提取</font>
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
				<html:form action="/pickupShowAC.do" focus="id">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">提 取 信 息</b>
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
							<logic:empty name="liwenhao">
								<td width="17%" class="td1">
									单位编号
								</td>
								<td width="22%">
									<html:text property="id" styleClass="input3"
										ondblclick="findName();" onkeydown="huiche()" name="af">
									</html:text>
								</td>
							</logic:empty>
							<logic:notEmpty name="liwenhao">
								<td width="17%" class="td1">
									单位编号
								</td>
								<td width="22%">
									<html:text property="id" styleClass="input3" name="af"
										readonly="true">
									</html:text>
								</td>
							</logic:notEmpty>
							<td width="11%">
								<logic:empty name="liwenhao">
									<input type="button" name="Submit3222" value="..."
										class="buttona" onclick="gotoSelect('234')">
								</logic:empty>
								<logic:notEmpty name="liwenhao">
									<input type="button" name="Submit3222" value="..."
										class="buttona" onclick="gotoSelect('234')" disabled="true">
								</logic:notEmpty>
							</td>
							<td class="td1" width="17%">
								单位名称
								<span class="STYLE1"></span>
							</td>
							<td width="33%">
								<html:text property="name" styleClass="input3" readonly="true"
									name="af"></html:text>
							</td>
						</tr>
						<tr>
							<logic:empty name="liwenhao">
								<td width="17%" class="td1">
									结算号
								</td>
								<td colspan="2">
									<html:text property="noteNumber" styleClass="input3" name="af"
										maxlength="50">
									</html:text>
								</td>
								<td width="17%" class="td1">
									受委托人身份证号
								</td>
								<td width="33%">
									<html:text property="other_card_num" styleClass="input3"
										name="af" maxlength="50" onclick="addNoteNumber()">
									</html:text>
								</td>
							</logic:empty>
							<logic:notEmpty name="liwenhao">
								<td width="17%" class="td1">
									结算号
								</td>
								<td colspan="2">
									<html:text property="noteNumber" styleClass="input3" name="af"
										readonly="true">
									</html:text>
								</td>
								<td width="17%" class="td1">
									受委托人身份证号
								</td>
								<td width="33%">
									<html:text property="other_card_num" styleClass="input3"
										name="af" readonly="true">
									</html:text>
								</td>
							</logic:notEmpty>
						</tr>
					</table>
				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td class=h4>
							合计：部分提取人数
							<u>:<bean:write name="af" property="somePickupNumber" /> </u>
							销户提取人数
							<u>:<bean:write name="af" property="distoryPickupNumber" />
							</u> 提取总人数
							<u>:<bean:write name="af" property="sumPerson" /> </u>提取金额
							<u>:<bean:write name="af" property="sumBalance" /> </u>
							<security:orgcannot> 销户利息
							<u>:<bean:write name="af" property="sumInterese" /> </u>
							</security:orgcannot>
							<security:orgcannot>提取总金额
							<u>:<bean:write name="af" property="sumTotal" /> </u>
							</security:orgcannot>
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
										<b class="font14">办理提取列表</b>
									</td>
									<td width="750" height="22" align="center"
										background="../../../img/bg2.gif">
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
				<html:form action="/pickupMiantianAC.do" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<html:hidden name="af" property="versionflag" />
							<html:hidden name="af" property="id" />
							<td align="center" height="23" bgcolor="C4F0FF">
								&nbsp;
								<br>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('pt.empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="pt.empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('pt.empName')">职工姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="pt.empName">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								证件号码
								<br>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('pt.pickSalary')">提取金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="pt.pickSalary">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<security:orghave>
								<security:orgcannot>
									<td align="center" class=td2>
										销户利息
										<br>
									</td>
								</security:orgcannot>
							</security:orghave>
							<security:orghave>
								<security:orgcannot>
									<td align="center" class=td2>
										提取总额
										<br>
									</td>
								</security:orgcannot>
							</security:orghave>
							<td align="center" class=td2>
								提取原因
								<br>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('pt.pickType')">提取类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="pt.pickType">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								房照号
								<br>
							</td>

							<td align="center" class=td2>
								浏览文件
								<br>
							</td>
						</tr>
						<input type="hidden" name="report" />
						<input type="hidden" name="notenum" />
						<input type="hidden" name="other_card_num" />
						<input type="hidden" name="eeemmmpppid" />
						<logic:notEmpty name="af" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="ele" name="af" property="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>",pickupMaintainAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",pickupMaintainAF);'
									class="<%=strClass%>">
									<td>
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="ele" property="empId"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %> />
									</td>
									<input type="hidden" name="eemmppid"
										value="<bean:write name="ele" property="empId"/>" />
									<input type="hidden" name="tailHeadId"
										value="<bean:write name="ele" property="pickHead.id"/>" />

									<td valign="top">
										<bean:write name="ele" property="empId" format="000000" />
									</td>
									<td valign="top">
										<bean:write name="ele" property="emp.empInfo.name" />
									</td>
									<td valign="top">
										<bean:write name="ele" property="emp.empInfo.cardNum" />
									</td>
									<td valign="top">
										<bean:write name="ele" property="pickSalary" format="0.00" />
									</td>
									<security:orghave>
										<security:orgcannot>
											<td valign="top">
												<bean:write name="ele" property="pickInterest" format="0.00" />
												<br>
											</td>
										</security:orgcannot>
									</security:orghave>
									<security:orghave>
										<security:orgcannot>
											<td valign="top">
												<bean:write name="ele" property="total" format="0.00" />
												<br>
											</td>
										</security:orgcannot>
									</security:orghave>
									<td valign="top">
										<bean:write name="ele" property="reason" />
									</td>
									<td valign="top">
										<bean:write name="ele" property="pickDisplayType" />
									</td>
									<td valign="top">
										<bean:write name="ele" property="reserveaA" />
									</td>
									<td>
										<a
											href='javascript:excHz1("<bean:write name="ele" property="reserveaB"/>");'><img
												src="<%=path%>/img/lookinfo.gif" width="37" height="24">
										</a>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="af" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合结果！
									<br>
								</td>
							</tr>
						</logic:empty>
						<html:hidden name="af" property="buttonState" />
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
												<jsp:param name="url" value="pickupShowAC.do" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:hidden property="noteNumber" />
					<input type="hidden" name="result" />
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								&nbsp;
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<security:orghave>
											<security:orgcannot>
												<td width="110">
													<html:submit property="method" styleClass="buttonc">
														<bean:message key="button.pickup.data" />
													</html:submit>
												</td>
											</security:orgcannot>
										</security:orghave>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return exports()">
												<bean:message key="button.exports" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="addNoteNumber();">
												<bean:message key="button.imports" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleId="add"
												styleClass="buttona" disabled="false"
												onclick="addNoteNumber();">
												<bean:message key="button.add" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return delConfirm();" disabled="false">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return confirm('请确认是否要删除所选择的记录');">
												<bean:message key="button.deleteall" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="getradiovalue();">
												<bean:message key="button.scan" />
											</html:submit>
										</td>
										<td width="90">
											<html:submit property="method" styleClass="buttonb" onclick="return reportConfirmYG();">
												<bean:message key="button.over.pickup" />
											</html:submit>
										</td>
										<security:orghave>
											<security:orgcan>
												<td width="70">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.referring.data" />
													</html:submit>
												</td>
											</security:orgcan>
										</security:orghave>
										<security:orghave>
											<security:orgcan>
												<td width="70">
													<html:submit property="method" styleClass="buttonc">
														<bean:message key="button.pproval.data" />
													</html:submit>
												</td>
											</security:orgcan>
										</security:orghave>
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
<form action="pickupShowAC.do" method="POST" name="Form1" id="Form1"></form>
</html:html>
