<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<html:html>
<head>
	<%
			String path = request.getContextPath();
			String modify_wsh = null;
			if (request.getAttribute("modify_wsh") != null) {
				modify_wsh = "1";
			}
	%>
	<title>特殊申请</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<script type="text/javascript">
function reportsErrors(){
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>	
}
function open_findEmps(path,orgid,st,cardnum,empname,indexs){
 	//returnValues=window.showModalDialog("showEmployeesListAC.do?orgid="+orgid+"&st="+st+"","window","dialogHeight:350px;dialogWidth:850px;dialogLeft:100;dialogTop:200;help:no;status=no;scroll=yes;center:yes"); 
	window.open(path+"/sysloan/showEmployeesListAC.do?orgid="+orgid+"&st="+st+"&cardnum="+cardnum+"&empname="+empname+"&indexs="+indexs+'&objInput=empId',"window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}

function findEmpInfo(){
 	open_findEmps('<%=path%>','','','','','0');
}
function executeAjax(){
    var queryString = "specialapplyTaFindACC.do?";
    var privilegeBorrowerId = document.SpecialapplyNewAF.elements["privilegeBorrowerId"].value.trim();
    var org_Id = document.forms[0].elements["org_Id"].value.trim();
    queryString = queryString + "privilegeBorrowerId="+privilegeBorrowerId+"&org_Id="+org_Id;
	findInfo(queryString);
}
			
function displays(privilegeBorrowerId,borrowerName,cardKind,cardNum,orgId,orgName,loanTimeLimit,loanMoney,bankName,bankAcc,remark,error){
	document.SpecialapplyNewAF.elements["privilegeBorrowerId"].value=privilegeBorrowerId;
 	document.SpecialapplyNewAF.elements["borrowerName"].value=borrowerName;
 	document.SpecialapplyNewAF.elements["borrowerName"].readOnly = true;
 	document.SpecialapplyNewAF.elements["cardKind"].value=cardKind;
 	document.SpecialapplyNewAF.elements["cardKind"].disabled = true;
 	document.SpecialapplyNewAF.elements["cardNum"].value=cardNum;
 	document.SpecialapplyNewAF.elements["cardNum"].readOnly = true;
 	document.SpecialapplyNewAF.elements["orgName"].value=orgName;
 	document.SpecialapplyNewAF.elements["orgId"].value=orgId;
 	document.SpecialapplyNewAF.elements["orgName"].readOnly = true;
 	document.SpecialapplyNewAF.elements["loanTimeLimit"].value=loanTimeLimit;
 	document.SpecialapplyNewAF.elements["loanMoney"].value=loanMoney;
 	//document.SpecialapplyNewAF.elements["perBank"].value=bankName;
 	//document.SpecialapplyNewAF.elements["perBankAcc"].value=bankAcc;
 	document.SpecialapplyNewAF.elements["remark"].value=remark;
}
function checkValues(){
	var privilegeBorrowerId = document.SpecialapplyNewAF.elements["privilegeBorrowerId"].value.trim();
	var headId = document.SpecialapplyNewAF.elements["headId"].value.trim();
	var floorId = document.SpecialapplyNewAF.elements["floorId"].value.trim();
	if(privilegeBorrowerId == null || privilegeBorrowerId == ""){
		var orgName = document.SpecialapplyNewAF.elements["orgName"].value.trim();
		var borrowerName = document.SpecialapplyNewAF.elements["borrowerName"].value.trim();
		var cardKind = document.SpecialapplyNewAF.elements["cardKind"].value.trim();
		var cardNum = document.SpecialapplyNewAF.elements["cardNum"].value.trim();
		if(borrowerName == null || borrowerName == ""){
			alert("借款人姓名不能为空!");
			return false;
		}else if(cardKind == null || cardKind == ""){
			alert("证件类型不能为空!");
			return false;
		}else if(cardNum == null || cardNum == ""){
			alert("证件号码不能为空!");
			return false;
		}else if(orgName == null || orgName == ""){
			alert("单位名称不能为空!");
			return false;
		}
		
	}
	var loanMoney = document.SpecialapplyNewAF.elements["loanMoney"].value.trim();
	var loanTimeLimit = document.SpecialapplyNewAF.elements["loanTimeLimit"].value.trim();
	if(loanMoney == null || loanMoney == ""){
		alert("贷款金额不能为空");
		return false;
	}
	if(loanTimeLimit == null || loanTimeLimit == ""){
		alert("贷款期限不能为空");
		return false;
	}else{
		var ursun = loanTimeLimit%12;
		if(ursun!=0){
			alert("贷款期限为月，不是年，谢谢！！！");
			document.SpecialapplyNewAF.elements["loanTimeLimit"].focus();
			return false;
		}else{
			if(loanTimeLimit==12){
				if(confirm("确定是一年吗？")){
					return true;
				}else{
					document.SpecialapplyNewAF.elements["loanTimeLimit"].focus();
					return false;
				}
			}
		}
	}
	if(headId != "") {
		if(floorId=="all"||floorId=="") {
			alert("请选择楼盘");
			return false;
		}
	}
}
			
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
//校验数字
function isNumber(String){ 
    var Letters = "1234567890-"; //可以自己增加可输入值
    var i;
    var c;
    if(String.charAt( 0 )=='-')
 		return false;
    if( String.charAt( String.length - 1 ) == '-' )
    	return false;
    for( i = 0; i < String.length; i ++ ){
	    c = String.charAt( i );
	   	if (Letters.indexOf( c ) < 0)
	    	return false;
	}
    return true;
}
function clears(){
	if("<%=modify_wsh%>"!="1"){
		document.SpecialapplyNewAF.elements["privilegeBorrowerId"].value="";
		document.SpecialapplyNewAF.elements["borrowerName"].value="";
		document.SpecialapplyNewAF.elements["cardKind"].value="";
		document.SpecialapplyNewAF.elements["cardNum"].value="";
		document.SpecialapplyNewAF.elements["orgName"].value="";
		document.SpecialapplyNewAF.elements["orgId"].value="";
		document.SpecialapplyNewAF.elements["loanTimeLimit"].value="";
		document.SpecialapplyNewAF.elements["loanMoney"].value="";
	}
	
}
function checkIdCard(){
	var cardKind = document.SpecialapplyNewAF.elements["cardKind"].value.trim();
	var cardNum = document.SpecialapplyNewAF.elements["cardNum"].value.trim();
	if(cardKind == 0){
		if(isIdCardNo(cardNum)){
			return true;
		}else{
			return false;
		}
	}
}
function findDeveloper(){
  	window.open("<%=path%>/sysloan/develepershowAC.do?indexs="+5+"&objInput=specialapply"+"&flag=0"+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=no"); 
}
function executeAjaxIn(){
	var headId = document.all.headId.value;
	var queryString = "specialapplyTaFindFloorAAC.do?";
    queryString = queryString + "headId="+headId;	     
	findInfo1(queryString);	  
}
function findInfo1(url) {
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
			var selectObj = document.getElementById("floorList");
			selectObj.length = 0;
			for ( i=0; i < values.length; i++ ) {
				var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
				selectObj.options[selectObj.length++] = childOption;
				if(i==0){
					document.all.floorName.value=values[i].firstChild.data;
					getChildOption();
				}
			}
      	}
   	}
}

function handleStateChange1() {
  	if(xmlHttp.readyState == 4) {
      	if(xmlHttp.status == 200) {
	        var xmlDoc = xmlHttp.responseXML;
			var values = xmlDoc.getElementsByTagName("value");
			var texts  = xmlDoc.getElementsByTagName("text");
			var selectObj = document.getElementById("floorList");
			selectObj.length = 0;
			for ( i=0; i < values.length; i++ ) {
				var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
				selectObj.options[selectObj.length++] = childOption;
				if(i==0){
					document.all.floorName.value=values[i].firstChild.data;
					getChildOption();
				}
			}
      	}
   	}
}
//楼盘下拉发生改变触发
function getChildOption() {
	var floorId = document.SpecialapplyNewAF.elements["floorName"].value.trim();
	var headId = document.SpecialapplyNewAF.elements["headId"].value.trim();
	var url = "loanapplytcfindfloorAAC.do?floorId="+encodeURI(floorId)+"&buyhouseorgid="+headId;
	findInfo12(url);
}
function findInfo12(url) {
	createXMLHttpRequest();  
    xmlHttp.onreadystatechange = handleStateChange_;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);   
}

function handleStateChange_() {
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
function setFloorId(){
	var selectObj = document.getElementById("select1");
	var floorNum = selectObj.options[selectObj.selectedIndex].value;
	document.SpecialapplyNewAF.elements["floorId"].value = floorNum;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000"
	onload="clears();reportsErrors();" onContextmenu="return false">
	<html:form action="/specialapplyTaNewAC.do">
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
											&#21150;&#29702;&#30003;&#35831;
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/specialapplyTbForwardAC.do"
												class=a2>&#30003;&#35831;&#32500;&#25252;</a>
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
										<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">&#30003;&#35831;&#36151;&#27454;&gt;&#36151;&#27454;&#39044;&#23457;</font>
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
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="172">
											<b class="font14">&#20511;&#27454;&#20154;&#22522;&#26412;&#20449;&#24687;</b>
										</td>
										<td width="753" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
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
								借款人职工编号
							</td>
							<td width="22%">
								<html:text name="SpecialapplyNewAF"
									property="privilegeBorrowerId" styleClass="input3"
									readonly="true" />
							</td>
							<td width="11%">
								<input type="button" class="buttona" value="..." name="button"
									onClick="findEmpInfo();" />
							</td>
							<td class="td1" width="17%">
								借款人姓名
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="SpecialapplyNewAF" property="borrowerName"
									styleClass="input3" onkeydown="enterNextFocus1();"
									styleId="txtsearch" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								证件类型
								<font color="#FF0000">*</font>
							</td>
							<td colspan="2">
								<html:select name="SpecialapplyNewAF" property="cardKind"
									styleClass="input4" onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="map" name="SpecialapplyNewAF"
										label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								证件号码
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="SpecialapplyNewAF" property="cardNum"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td colspan="2">
								<html:text name="SpecialapplyNewAF" property="orgId"
									styleClass="input3" onkeydown="enterNextFocus1();"
									readonly="true" />
							</td>
							<td width="17%" class="td1">
								单位名称
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="SpecialapplyNewAF" property="orgName"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>


						<tr>
							<td width="17%" class="td1">
								贷款期限（月）
								<font color="#FF0000">*</font>
							</td>
							<td colspan="2">
								<html:text name="SpecialapplyNewAF" property="loanTimeLimit"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								贷款金额（元）
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="SpecialapplyNewAF" property="loanMoney"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								是否将贷款拨入个人账户
							</td>
							<td colspan="2">
								<html:select property="sfbrzh" name="SpecialapplyNewAF"
									styleClass="input3" onkeydown="enterNextFocus1();">
									<html:option value="0">否</html:option>
									<html:option value="1">是</html:option>
								</html:select>
							</td>
							<td width="17%" class="td1">
								备注
							</td>
							<td width="33%">
								<html:text name="SpecialapplyNewAF" property="remark"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								售房单位
							</td>
							<td width="25%">
								<html:text name="SpecialapplyNewAF" property="developerName"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<html:hidden name="SpecialapplyNewAF" property="headId" />
							<html:hidden name="SpecialapplyNewAF" property="floorId" />
							<td class="td4" width="8%">
								<input type="button" class="buttona" value="..." name="button9"
									onclick="findDeveloper();">
							</td>
							<td width="17%" class="td1">
								所在楼盘
							</td>
							<td width="33%">
								<html:select name="SpecialapplyNewAF" property="floorName"
									onchange="getChildOption();" styleId="floorList"
									styleClass="input4" onkeydown="enterNextFocus1();">
									<logic:notEmpty name="floorOList">
										<html:options collection="floorOList" property="value"
												labelProperty="label" />
									</logic:notEmpty>
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								楼盘号
							</td>
							<td colspan="2">
								<html:select name="SpecialapplyNewAF" property="floorNum"
									onchange="setFloorId();"
									styleClass="input4" styleId="select1"
									onkeydown="enterNextFocus1();">
									<logic:notEmpty name="floorOList">
										<html:options collection="floorOListnum" property="value"
													labelProperty="label" />
									</logic:notEmpty>
								</html:select>
							</td>
						</tr>
					</table>
					<input type="hidden" name="org_Id" />
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkValues();">
												<bean:message key="button.sure" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
