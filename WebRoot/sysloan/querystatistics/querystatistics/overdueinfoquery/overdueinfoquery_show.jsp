<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.action.OverDueinfoQueryShowListAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			OverDueinfoQueryShowListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>

<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	
function findorghouses(){

   window.open("<%=path%>/sysloan/develepershowAC.do?indexs="+5+"&objInput=headname"+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
//楼盘弹出框
function findBuilding(){
	var developerId = document.all.buyhouseorgid.value.trim();
	window.open("<%=path%>/sysloan/buildingPopShowAC.do?indexs="+5+"&developerId="+developerId+
		"&objInput=floorName"+"&qx=no","window","height=600,width=700,top="+(window.screen.availHeight-500)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=no"); 
}

function executeAjaxIn(){}

function findInfo_floorNum(url) {
 	createXMLHttpRequest();  
    xmlHttp.onreadystatechange = handleStateChange1;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);   
}
function handleStateChange1() {
  	if(xmlHttp.readyState == 4) {
      	if(xmlHttp.status == 200) {
        	var xmlDoc = xmlHttp.responseXML;
			var values = xmlDoc.getElementsByTagName("value");
			var texts  = xmlDoc.getElementsByTagName("text");
			var selectObj = document.getElementById("select1");
			selectObj.length = 0;
			for ( i=0; i < values.length; i++ ) {
				var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
				selectObj.options[selectObj.length++] = childOption;
			}
      	}
   	}
}
//弹出窗口回调的方法
function findFloorNum(headId,floorName){
	document.all.buyhouseorgid.value = headId;
	document.all.floorName.value = floorName;
	var url = "<%=path %>/sysloan/loanapplytcfindfloorAAC.do?floorId="+encodeURI(floorName)+"&buyhouseorgid="+headId;
	findInfo_floorNum(url);
}
//楼盘号下拉发生改变触发
function getChildValue(){
	var selectObj = document.getElementById("select1");
	var floorId = selectObj.options[selectObj.selectedIndex].value;
	document.forms[0].floorid.value = floorId;
  	var url = "<%=path %>/sysloan/loanapplytcfindHouseAddAAC.do?floorId="+floorId;
	findInfo_house(url);
}
//层次屋号的ajax
function findInfo_house(url) {
 	createXMLHttpRequest();
    xmlHttp.onreadystatechange = handleStateChange_house;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);   
}

function handleStateChange_house() {
  	if(xmlHttp.readyState == 4) {
      	if(xmlHttp.status == 200) {
        	var xmlDoc = xmlHttp.responseXML;
			var values = xmlDoc.getElementsByTagName("value");
			var texts  = xmlDoc.getElementsByTagName("text");
			var selectObj = document.getElementById("houseAddNode");
			selectObj.length = 0;
			for ( i=0; i < values.length; i++ ) {
				var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
				selectObj.options[selectObj.length++] = childOption;
			}
      	}
   	}
}
function giveValues(){
	var selectObj = document.getElementById("select1");
	var floorId = selectObj.options[selectObj.selectedIndex].value;
	var floorNum = selectObj.options[selectObj.selectedIndex].text;
	document.forms[0].floorid.value = floorId;
	if(floorId!=""&&floorId!="all"){
		document.forms[0].floorNum_.value = floorNum;
	}
	selectObj = document.getElementById("houseAddNode");
	var roomNum = selectObj.options[selectObj.selectedIndex].value;
	document.forms[0].roomNum.value = roomNum;
}
function findInfo123(url) {
 createXMLHttpRequest();  
	    xmlHttp.onreadystatechange = handleStateChange123;
	    xmlHttp.open("GET", url, true);
	    xmlHttp.send(null);   
}

function handleStateChange123() {
  if(xmlHttp.readyState == 4) {
      if(xmlHttp.status == 200) {
        var xmlDoc = xmlHttp.responseXML;
		var values = xmlDoc.getElementsByTagName("value");
		var texts  = xmlDoc.getElementsByTagName("text");

		var selectObj = document.getElementById("childNode");
		selectObj.length = 0;
		for ( i=0; i < values.length; i++ ) {
			var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
			selectObj.options[selectObj.length++] = childOption;
			if(i==0){
			document.all.floorId.value=values[i].firstChild.data;
			}
		}
	
      }
   }
}


function executeAjax(){
	var buyhouseorgid = document.all.buyhouseorgid.value.trim();
	var url = "overDueinfoQueryFindFloorNameAAC.do?buyhouseorgid="+buyhouseorgid;
	findInfo123(url);
}

function gotoPincheck() {
	if(confirm("是否确认打印？")){
		return true;
	} else {
		return false;
	}
}
function tocontrractInfo(){
	var contractIdHL=getCheckId();
	window.open("<%=path%>/sysloan/showLoanCheckTaAC.do?contractIdWY="+contractid,"window","height=700,width=770,top="+(window.screen.availHeight-770)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes,location=no,status=no");
	return false;
}
var contractid;
	function gotoindividualflow(){
	window.open("<%=path%>/sysloan/loanDeskaccQueryTbForwardAC.do?contractIdHl="+contractid,"window","height=700,width=770,top="+(window.screen.availHeight-770)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes,location=no,status=no");
	return false;
}
function checkMonth()
{ 
  	var month1 = document.forms[0].elements["beginoweMonth"].value;
  	var month2 = document.forms[0].elements["endoweMonth"].value;
   	if(month1 == "" ){
    	alert("请输入起始月份！");
    	document.forms[0].elements["beginoweMonth"].value="";
    	return false;
   	}
   	if(month2 == "" ){
    	alert("请输入终止月份！");
    	document.forms[0].elements["endoweMonth"].value="";
   		return false;
   	}
   	if(month1 != "" && month2 != ""){
	    if(parseFloat(month1)>parseFloat(month2))
	    {
	     	alert("起始的月份应小于终止的月份!");
	     	document.forms[0].elements["beginoweMonth"].value="";
	     	document.forms[0].elements["endoweMonth"].value="";
	     	return false;
	    }
   	}   
}
function gotoCreate(eee){
	var loanBankId=document.overDueinfoQueryShowListAF.loanBankName.value;
	if(loanBankId==''){
		alert("请选择放款银行！");
		return false;
	}else{
		setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
		location.href="overDueinfoQueryTaCreateAC.do?loanBankId="+loanBankId;
	}
}
function gotoImport(){
	location.href="overDueinfoQueryTaToImportAC.do";
}
function gotoReutrn(){
	return false;
}
function loads(){
var a=document.all.id;
if(a!=undefined){
	contractid=document.getElementById("tr0").childNodes[1].innerHTML;	
}
	
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>
	var plLoanReturnType=<bean:write name="plLoanReturnType"/>;
}
var tr=0;
function gettr(tr){
contractid=document.getElementById(tr).childNodes[1].innerHTML;	
}
</script>
	<body bgcolor="#FFFFFF" text="#000000"  onload="loads();">
	<jsp:include flush="true" page="/waiting.inc"/>
		<table width="2200" border="0" cellspacing="0" cellpadding="0" align="center"
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
							<td background="<%=path%>/img/table_bg_line.gif" align="left">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">统计查询&gt;逾期催还查询</font>
										</td>
										<td width="29" class=font14>
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
					<table width="95%" border="0" cellspacing="0" cellpadding="0" 
						align="center">
						<tr>
							<td height="35">
								<input id="createButton" type="button" class="buttonb" value="生成逾期数据" onclick="gotoCreate(this);">
							</td>
						</tr>
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="205">
											<b class="font14">查 询 条 件</b>
										</td>
										<td width="720" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:form action="/overDueinfoQueryFindAC" style="margin: 0">
						<table width=95% border="0" cellpadding=0 align="center">
						<tr><td width=250 align="left">
						<table width=900 border="0" cellpadding=0
							cellspacing=1 id="table1">
							<tr>
								<td width="17%" class="td1">
									合同编号
								</td>
								<td align="center" colspan="3">
									<html:text onkeydown="enterNextFocus1();" property="contractId"
										name="overDueinfoQueryShowListAF" styleClass="input3" />
								</td>
								<td width="17%" class="td1">
									借款人姓名
								</td>
								<td align="center" colspan="3">
									<html:text onkeydown="enterNextFocus1();" property="borrowerName"
										name="overDueinfoQueryShowListAF" styleClass="input3" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									身份证号码
								</td>
								<td align="center" colspan="3">
									<html:text onkeydown="enterNextFocus1();" property="cardNum" name="overDueinfoQueryShowListAF"
										styleClass="input3" />
								</td>
								<td width="17%" class="td1">
									扣款账号
								</td>
								<td align="center" colspan="3">
									<html:text onkeydown="enterNextFocus1();" property="loanKouAcc"
										name="overDueinfoQueryShowListAF" styleClass="input3" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									办事处
								</td>
								<td align="center" colspan="3">
									<html:select onkeydown="enterNextFocus1();" name="overDueinfoQueryShowListAF" property="offic"
										styleClass="input4">
										<html:option value=""></html:option>
										<html:options collection="officeList" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<td width="17%" class="td1">
									放款银行
								</td>
								<td width="33%" align="center" colspan="3">
									<html:select onkeydown="enterNextFocus1();" name="overDueinfoQueryShowListAF"
										property="loanBankName" styleClass="input4">
										<html:option value=""></html:option>
										<html:options collection="loanBankNameList" property="value"
											labelProperty="label" />
									</html:select>
								</td>
							</tr>
							<tr id="gjtr">
								<td width="17%" class="td1">
									开发商
								</td>
								<td width="30%" colspan="2">
									<html:text property="headname" name="overDueinfoQueryShowListAF" styleClass="input3" readonly="true" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="3%" align="center">
									<input type="button" name="submit12" class="buttona" value="..." onclick="findorghouses();" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
									楼盘
								</td>
								<td width="30%" colspan="2">
									<html:text property="floorName" name="overDueinfoQueryShowListAF" styleClass="input3" readonly="true" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="3%" align="center">
									<input type="button" name="submit12" class="buttona" value="..." onclick="findBuilding();" onkeydown="enterNextFocus1();"/>
									<html:hidden property="buyhouseorgid" name="overDueinfoQueryShowListAF"/>
									<html:hidden property="floorid" name="overDueinfoQueryShowListAF"/>
									<html:hidden property="floorNum_" name="overDueinfoQueryShowListAF"/>
								</td>
							</tr>
							<tr id="gjtr">
								<td width="17%" class="td1">
									楼栋
								</td>
								<td width="33%" align="center" colspan="3">
									<html:select name="overDueinfoQueryShowListAF" property="floorNum"
										styleClass="input4" onchange="getChildValue()"
										styleId="select1" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
									</html:select>
								</td>
								<td width="17%" class="td1">
									层次室号
								</td>
								<td width="33%" align="center" colspan="3">
									<html:select name="overDueinfoQueryShowListAF" property="roomNum"
										styleClass="input4" styleId="houseAddNode" 
										onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									担保公司
								</td>
								<td align="center" colspan="3">
									<html:select onkeydown="enterNextFocus1();" name="overDueinfoQueryShowListAF"
										property="assistantorg" styleClass="input4">
										<html:option value=""></html:option>
										<html:options collection="assistantorgList" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<td width="17%" class="td1">
									逾期到期未清还
								</td>
								<td align="center" colspan="3">
									<html:select onkeydown="enterNextFocus1();" name="overDueinfoQueryShowListAF"
										property="isNoAcquittance" styleClass="input4">
										<html:option value=""></html:option>
										<html:options collection="isNoAcquittanceList" property="value"
											labelProperty="label" />
									</html:select>
								</td>
							</tr>
							
							<tr>
								<td width="17%" class="td1">
									逾期月数
								</td>
								<td width="11%" align="center" colspan="1">
									<html:text onkeydown="enterNextFocus1();"property="beginoweMonth"
										name="overDueinfoQueryShowListAF" styleClass="input3" />
								</td>
								<td width="11%" align="center">
									至
								</td>
								<td width="11%" align="center" colspan="1">
									<html:text onkeydown="enterNextFocus1();"property="endoweMonth"
										name="overDueinfoQueryShowListAF" styleClass="input3" />
								</td>
								<td width="17%" class="td1">
									面积
								</td>
								<td width="11%" align="center">
									<html:text onkeydown="enterNextFocus1();" property="buildAreaMin"
										name="overDueinfoQueryShowListAF" styleClass="input3" />
								</td>
								<td width="11%" align="center">
									至
								</td>
								<td width="11%" align="center">
									<html:text onkeydown="enterNextFocus1();" property="buildAreaMax"
										name="overDueinfoQueryShowListAF" styleClass="input3" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									银行扣款日期 
								</td>
								<td align="center" colspan="3">
									<html:text onkeydown="enterNextFocus1();" property="applyDate"
										name="overDueinfoQueryShowListAF" styleClass="input3" />
								</td>
								<td width="17%" class="td1">
									房屋类型
								</td>
								<td width="11%" align="center" colspan="3">
									<html:select onkeydown="enterNextFocus1();" name="overDueinfoQueryShowListAF"
										property="houseType" styleClass="input4">
										<html:option value=""></html:option>
										<html:optionsCollection property="housetypemap"
											name="overDueinfoQueryShowListAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									公积金协议
								</td>
								<td align="center" colspan="3">
									<html:select onkeydown="enterNextFocus1();" name="overDueinfoQueryShowListAF"
										property="agreement" styleClass="input4">
										<html:option value=""></html:option>
										<html:optionsCollection property="yesNoMap"
											name="overDueinfoQueryShowListAF" label="value" value="key" />
									</html:select>
								</td>
								<td width="17%" class="td1">
									其他欠款
								</td>
								<td align="center" colspan="3">
									<html:select onkeydown="enterNextFocus1();" name="overDueinfoQueryShowListAF"
										property="otherMoney" styleClass="input4">
										<html:option value=""></html:option>
										<html:optionsCollection property="yesNoMap"
											name="overDueinfoQueryShowListAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									发放日期
								</td>
								<td align="center" colspan="1">
									<html:text onkeydown="enterNextFocus1();" property="startDateMin"
										name="overDueinfoQueryShowListAF" styleClass="input3" />
								</td>
								<td width="11%" align="center">
									至
								</td>
								<td align="center" colspan="1">
									<html:text onkeydown="enterNextFocus1();" property="startDateMax"
										name="overDueinfoQueryShowListAF" styleClass="input3" />
								</td>
								<td width="17%" class="td1">
									&nbsp;
								</td>
								<td align="center" colspan="3">
									&nbsp;
								</td>
							</tr>
						</table></td></tr><tr><td width=250 align="right">
						<table width=900 border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="right">
									<html:submit property="method" styleClass="buttona"
										onclick="return checkMonth();">
										<bean:message key="button.search" />
									</html:submit>
								</td>
							</tr>
						</table></td></tr></table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td class=h4>
									合计：欠还本金总额
									<u>：<bean:write name="overDueinfoQueryShowListAF"
											property="oweCorpusTotle" /> </u> 欠还利息总额
									<u>：<bean:write name="overDueinfoQueryShowListAF"
											property="oweInterestTotle" /> </u> 欠还罚息利息总额
									<u>：<bean:write name="overDueinfoQueryShowListAF"
											property="punishInterest" /> </u>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="204">
											<b class="font14">逾 期 催 还 列 表</b>
										</td>
										<td width="721" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:form action="/overDueinfoQueryMaintainAC.do"
						style="margin: 0">
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr bgcolor="1BA5FF">
								<td align="center" height="6" colspan="1"></td>
							</tr>
						</table>

						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
								<td align="center" class=td2>
									合同编号
								</td>
								<td align="center" class=td2>
									扣款账号
								</td>
								<td align="center" class=td2>
									借款人姓名
								</td>
								<td align="center" class=td2>
									证件类型
								</td>
								<td align="center" class=td2>
									证件号码
								</td>
								<td align="center" class=td2>
									单位名称
								</td>
								<td align="center" class=td2>
									住宅电话
								</td>
								<td align="center" class=td2>
									移动电话
								</td>
								<td align="center" class=td2>
									单位电话
								</td>
								<td align="center" class=td2>
									配偶姓名
								</td>
								<td align="center" class=td2>
									配偶身份证号
								</td>
								<td align="center" class=td2>
									配偶工作单位
								</td>
								<td align="center" class=td2>
									移动电话
								</td>
								<td align="center" class=td2>
									配偶单位电话
								</td>
								<td align="center" class=td2>
									家庭住址
								</td>
								<td align="center" class=td2>
									贷款金额
								</td>
								<td align="center" class=td2>
									贷款期限
								</td>
								<td align="center" class=td2>
									贷款余额
								</td>
								<td align="center" class=td2>
									欠还本金
								</td>
								<td align="center" class=td2>
									欠还利息
								</td>
								<td align="center" class=td2>
									欠还罚息利息
								</td>
								<td align="center" class=td2>
									欠还本息合计
								</td>
								<td align="center" class=td2>
									下月应还本息
								</td>
								<td align="center" class=td2>
									下月应还金额
								</td>
								<td align="center" class=td2>
									逾期月数
								</td>
								<td align="center" class=td2>
									公积金协议
								</td>
								<td align="center" class=td2>
									扣款日期
								</td>
								<td align="center" class=td2>
									逾期年月
								</td>
							</tr>
							<logic:notEmpty name="overDueinfoQueryShowListAF" property="list">
							<% int j=0;
			String strClass="";
		%>
								<logic:iterate name="overDueinfoQueryShowListAF" property="list"
									id="elments" indexId="i">
									<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
									<tr id="tr<%=i%>"
										onclick='gotoClickpp("<%=i %>", idAF);gettr("tr<%=i%>");' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>">
										<td valign="top">
											<p align="left">
												<input id="s<%=i%>" type="radio" name="id"
													value="<bean:write name="elments" property="id"/>"
													<%if(new Integer(0).equals(i)) out.print("checked"); %>>
											</p>
										</td>
										<td valign="top">
											<bean:write name="elments" property="contractId" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loanKouAcc" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="borrowerName" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="cardKind" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="cardNum" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="orgName" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="houseTel" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="homeMobile" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="orgTel" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="name" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="card_num" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="org_name" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="home_mobile" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="org_tel" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="home_addr" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loan_money" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loan_time_limit" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="overplus_loan_money" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="oweCorpus" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="oweInterest" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="punishInterest" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="corpusInterestAll" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="nextCorpusInterest" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="nextMoney" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="oweMonth" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="agreement" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="applyDate" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="oweDate" />
										</td>
									</tr>
									
								</logic:iterate>

							</logic:notEmpty>
							<logic:empty name="overDueinfoQueryShowListAF" property="list">
								<tr>
									<td colspan="7" height="30" style="color:red">
										没有找到与条件相符合的结果！
									</td>
								</tr>
								
							</logic:empty>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr class="td1">
								<td align="center">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr align="center">
											<td align="left">
												共
												<bean:write name="pagination" property="nrOfElements" />
												项
											</td>
											<td align="right">
												<jsp:include page="/inc/pagination.jsp">
													<jsp:param name="url" value="overDueinfoQueryShowListAC.do" />
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
										<logic:notEmpty name="overDueinfoQueryShowListAF"
											property="list">
											<tr>
												<td width="90">
													<html:submit property="method" styleClass="buttonb"
														onclick="return gotoindividualflow();">
														<bean:message key="button.individual.flow" />
													</html:submit>
												</td>
												<td width="70">
													<html:submit property="method" styleClass="buttona"
														onclick="return tocontrractInfo();">
														<bean:message key="button.contract.info" />
													</html:submit>
												</td>
												<td width="70">
													<html:submit property="method" styleClass="buttona"
														onclick="return gotoPincheck();">
														<bean:message key="button.printone" />
													</html:submit>
												</td>
												<td>
													<html:submit property="method" styleClass="buttona"
														onclick="return gotoPincheck();">
														<bean:message key="button.print" />
													</html:submit>
												</td>
											</tr>
										</logic:notEmpty>
										<logic:empty name="overDueinfoQueryShowListAF" property="list">
											<tr>
												<td width="90">
													<html:submit property="method" styleClass="buttonb"
														disabled="true" >
														<bean:message key="button.individual.flow" />
													</html:submit>
												</td>
												<td width="70">
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.contract.info" />
													</html:submit>
												</td>
												<td width="70">
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.printone" />
													</html:submit>
												</td>
												<td>
													<html:submit property="method" styleClass="buttona"
														disabled="true" >
														<bean:message key="button.print" />
													</html:submit>
												</td>
											</tr>
										</logic:empty>
									</table>

								</td>
							</tr>
						</table>
					</html:form>
		</table>

	</body>
</html>


