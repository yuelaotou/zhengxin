<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>

<%
String path = request.getContextPath();
%>
<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
		<script language="javascript" src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	
var s1="";
var s3="";
var lock = "0";
function loads(){

 	if(loanMood=='4'||loanMood=='5'){
 		kh_1.style.display="none";	
 		kh_2.style.display="";	
 	}else{
	 	kh_1.style.display="";	
	 	kh_2.style.display="none";	
 	}

	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"

		alert(message);
	</logic:messagesPresent>	
	 <logic:equal name="loanapplytdnewAF" property="isNeedDel" value="1">
 	 	del();
 	 </logic:equal>
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){

			if(document.all.item(i).value=="确定"){
				s1=i;
			}
<%--			if(document.all.item(i).value=="扫描"){--%>
<%--				s2=i;--%>
<%--			}--%>
			if(document.all.item(i).value=="删除"){
				s3=i;
			}
		}
	} 

 	var loanMonthRates = document.loanapplytdnewAF.elements["loanMonthRate"].value.trim();
 	if(loanMonthRates==""){
  		document.all.item(s3).disabled="true";
 	}
	var spid = document.loanapplytdnewAF.elements["spid"].value.trim();
	if(spid=="1"){
		document.forms[0].elements["loanMoney"].readOnly="readOnly";
		document.forms[0].elements["loantimeLimit"].readOnly="readOnly";
	}

	var coodm = document.loanapplytdnewAF.elements["coodm"].value.trim();

	if(coodm==15){
	 	document.all.item(s1).disabled="true";
	}

	var loanMoney = document.loanapplytdnewAF.elements["loanMoney"].value.trim();

	var money=ChangeToBig(loanMoney);

	document.loanapplytdnewAF.elements["loanMoneyBig"].value=money;
	var temp_ty = document.loanapplytdnewAF.elements["temp_ty"].value.trim();
 	if(temp_ty=="1"){
		document.forms[0].elements["button2"].disabled="true";
  		document.forms[0].elements["contractId"].readOnly="true";
 	}
 	var loanMood = document.loanapplytdnewAF.elements["loanMood"].value.trim();
	document.loanapplytdnewAF.loanMoney.focus();
}
function accantMoney(){
	var loanMoney = document.loanapplytdnewAF.elements["loanMoney"].value.trim();
	var loantimeLimit = document.loanapplytdnewAF.elements["loantimeLimit"].value.trim();
	loantimeLimit = loantimeLimit*12;
 	var loanMonthRate = document.loanapplytdnewAF.elements["loanMonthRate"].value.trim();
	var loanMood = document.loanapplytdnewAF.elements["loanMood"].value.trim();
	var office = document.loanapplytdnewAF.elements["office"].value.trim();
	var houseTotlePrice = document.loanapplytdnewAF.elements["houseTotlePrice"].value.trim();
	if(loanMoney!=""&&houseTotlePrice!=""){
		var m=parseInt(houseTotlePrice-loanMoney)/parseInt(houseTotlePrice)*parseInt("100");
		var mm=m+"";
		var mmm=m+"";
		mm=mm.indexOf(".",0)
		if(mm!=-1){
			mmm=mmm.substring(0,parseInt(mm));
		}
		document.loanapplytdnewAF.elements["firstTol"].value=mmm+'%';
 	}
	var money=ChangeToBig(loanMoney);
	document.loanapplytdnewAF.elements["loanMoneyBig"].value=money;
	if(loanMoney==""||loantimeLimit==""||loanMonthRate==""||loanMood==""){
		
	}else{
		if(loanMood=="1"){
			document.forms[0].elements["corpusInterest"].value="0";
		}else{
			var queryString = "loanapplytdfindmonthinterestAAC.do?";
  			queryString = queryString + "office=" +office+ "&loanMoney="+loanMoney+"&loantimeLimit="+loantimeLimit+"&loanMonthRate="+loanMonthRate+"&loanMood="+loanMood; 	     
		 	findInfo(queryString);	
		}		
	}	
}
function checkLoantimeLimit(){
 	var loanMoney = document.loanapplytdnewAF.elements["loanMoney"].value.trim();
	var loantimeLimit = document.loanapplytdnewAF.elements["loantimeLimit"].value.trim();
	loantimeLimit = loantimeLimit*12;
 	var loanMonthRate = document.loanapplytdnewAF.elements["loanMonthRate"].value.trim();
	var loanMood = document.loanapplytdnewAF.elements["loanMood"].value.trim();
 
  	var office = document.loanapplytdnewAF.elements["office"].value.trim();
	if(loantimeLimit==""){
	
	}else{
		if(!isNaN(loantimeLimit)){
			if(loanMood=="4"||loanMood=="5"){
				if(loanMood=="4"){
			 		document.loanapplytdnewAF.elements["loantimeLimit"].value="12";
			 	}
			 	if(loanMood=="5"){
			 		document.loanapplytdnewAF.elements["loantimeLimit"].value="24";
			 	}
			}
		 	var queryString = "loanapplytdfindmonthrateAAC.do?";
	  	 	queryString = queryString + "office="+office +"&loantimeLimit="+loantimeLimit+"&loanMoney="+loanMoney+"&loanMonthRate="+loanMonthRate+"&loanMood="+loanMood; 	     
		 	findInfo(queryString);	
		}else{
			alert('请输入数字');
			document.loanapplytdnewAF.elements["loantimeLimit"].value="";
		}
	}
}
 
 function display1(monthRate,monthRatess,interest){
	
	document.forms[0].elements["loanMonthRate"].value=monthRate;
	document.forms[0].elements["loanMonthRatess"].value=monthRatess;
	document.forms[0].elements["corpusInterest"].value=interest;
	document.forms[0].elements["entireYearMoney"].value=interest;
 }
 
  function display(monthInterest){

	document.forms[0].elements["corpusInterest"].value=monthInterest;
 }
 
function changes(){
	var loanMoney = document.loanapplytdnewAF.elements["loanMoney"].value.trim();
 	var loantimeLimit = document.loanapplytdnewAF.elements["loantimeLimit"].value.trim();
 	var loanMonthRate = document.loanapplytdnewAF.elements["loanMonthRate"].value.trim();
	var loanMood = document.loanapplytdnewAF.elements["loanMood"].value.trim();
	var office = document.loanapplytdnewAF.elements["office"].value.trim();
	if(loanMood=="4"||loanMood=="5"){
	 	if(loanMood=="4"){
	 		document.loanapplytdnewAF.elements["loantimeLimit"].value="12";
	 	}
	 	if(loanMood=="5"){
	 		document.loanapplytdnewAF.elements["loantimeLimit"].value="24";
	 	}
	 	kh_1.style.display="none";
	 	kh_2.style.display="";
	 }else{
	 	kh_1.style.display="";
	 	kh_2.style.display="none";
	 }
	 
	 if(loanMoney==""||loantimeLimit==""||loanMonthRate==""||loanMood==""){
	 	if(loanMood=="1"){
	 	 document.forms[0].elements["corpusInterest"].value=0;
	    }
	 }else{
		if(loanMood=="1"){
			 document.forms[0].elements["corpusInterest"].value=0;
		}
		var queryString = "loanapplytdfindmonthinterestAAC.do?";
		queryString = queryString + "office="+office + "&loanMoney="+loanMoney+"&loantimeLimit="+loantimeLimit+"&loanMonthRate="+loanMonthRate+"&loanMood="+loanMood; 	     
		findInfo(queryString);	
	 }
}
 
function executeAjax() {
   	var queryString = "loanapplytdfindcontractAAC.do?";
    var contractId = document.loanapplytdnewAF.elements["contractId"].value.trim();
    queryString = queryString + "contractId="+contractId;	  
   	findInfo(queryString);	     
}

function displayerrors(mes){
 	alert(mes);
 	if(mes=="该合同额度信息已经存在，不能添加额度信息"){
 		document.all.item(s1).disabled="true";
 	}
 	location='loanapplytdshowAC.do';
}
function displaycon(){
 	showlist();
}
 
function showlist() {
  	document.Form1.submit();
}
function ischekcard(){
	var contractId = document.loanapplytdnewAF.elements["contractId"].value.trim();
	var borrowerName = document.loanapplytdnewAF.elements["borrowerName"].value.trim();
    var loanMonthRate = document.loanapplytdnewAF.elements["loanMonthRate"].value.trim();
    var loanPoundage = document.loanapplytdnewAF.elements["loanPoundage"].value.trim();
    var loanMood = document.loanapplytdnewAF.elements["loanMood"].value.trim();
        
    if(contractId==""){
       alert("请输入合同编号");
       return false;
    }
    if(borrowerName==""){
      	alert("请输入借款人姓名");
       	return false;
    }
  	if(loanMonthRate==""){
       	alert("请输入月利率");
       	return false;
    }
    if(loanMood==""){
      	alert("请选择还款方式");
      	return false;
    }
    if(loanPoundage==""){
       	document.loanapplytdnewAF.elements["loanPoundage"].value="0";
    }
    if(lock == "1") {
    	return false;
    } else {
    	lock = "1";
    }
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function loanMoneyFocus(){
	if(event.keyCode==13){
		document.loanapplytdnewAF.loantimeLimit.focus();
		return false;
	}
}
</script>
	<body bgcolor="#FFFFFF" text="#000000" onload="loads();">
		<jsp:include page="/syscommon/picture/progressbar.jsp" />
		<html:form action="/loanapplytdmaintianAC.do">
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				align="center">
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="7">
									<img src="<%=path%>/img/table_left.gif" width="7" height="37">
								</td>
								<td background="<%=path%>/img/table_bg_line.gif" width="10">
									&nbsp;
								</td>
								<td background="<%=path%>/img/table_bg_line.gif" valign="top">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												valign="top" style="PADDING-top: 7px">
												<a href="<%=path%>/sysloan/showLoanapplyAC.do" class=a2>借款人信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/loanapplytbshowAC.do" class=a2>共同借款人信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/loanapplytcshowAC.do" class=a2>购房信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttonbl.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/loanapplytdshowAC.do" class=a2>借款人额度信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/loanapplyteshowAC.do" class=a2>申请贷款维护</a>
											</td>
										</tr>
									</table>
								</td>
								<td background="<%=path%>/img/table_bg_line.gif" align="right">
									<table width="200" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="37">
												<img src="<%=path%>/img/title_banner.gif" width="37"
													height="24">
											</td>
											<td width="228" class=font14 bgcolor="#FFFFFF" align="center"
												valign="bottom">

												<font color="00B5DB">申请贷款&gt;申请贷款</font>
											</td>
											<td width="35" class=font14>
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
											<td height="22" bgcolor="#CCCCCC" align="center" width="166">
												<b class="font14">借款人额度信息</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="738">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center" style="line-height:150%">
							<tr>
								<td width="17%" class=td1>
									合同编号
								</td>
								<td class="td4" width="20%">
									<html:text property="contractId" name="loanapplytdnewAF"
										styleClass="input3" onkeydown="gotoEnter();" />
								</td>
								<td width="11%">
									<input type="button" class="buttona" value="..." name="button2"
										onClick="gotoContractpop('','<%=path%>','0','1');">
								</td>
								<td class="td1" width="20%">
									借款人姓名
								</td>
								<td width="34%" class=td4>
									<html:text property="borrowerName" name="loanapplytdnewAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="17%" class=td1>
									证件类型
								</td>
								<td colspan="2" class="td4">
									<html:text property="cardKind" name="loanapplytdnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td class="td1" width="20%">
									证件号码
								</td>
								<td width="15%" class=td4>
									<html:text property="cardNum" name="loanapplytdnewAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="17%" class=td1>
									贷款金额
									<font color="#FF0000">*</font>
								</td>
								<td colspan="2" class="td4">
									<html:text property="loanMoney" name="loanapplytdnewAF"
										styleClass="input3" onblur="accantMoney();"
										onkeydown="return loanMoneyFocus();" />
								</td>
								<td class="td1" width="20%">
									贷款金额（大写）
									<font color="#FF0000">*</font>
								</td>
								<td width="15%" class=td4>
									<html:text property="loanMoneyBig" name="loanapplytdnewAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="17%" class=td1>
									贷款期限
									<font color="#FF0000">* <font color="#000000">&#65288;年&#65289;</font>
									</font>
								</td>
								<td colspan="2" class="td4">
									<html:text property="loantimeLimit" name="loanapplytdnewAF"
										styleClass="input3" onblur="return checkLoantimeLimit();"
										onkeydown="enterNextFocus1();" />
								</td>
								<td class="td1" width="20%">
									还款方式
									<font color="#FF0000">*</font>
								</td>
								<td width="15%" class=td4>
									<html:select property="loanMood" name="loanapplytdnewAF"
										styleClass="input4" onchange="changes();"
										onkeydown="enterNextFocus1();">

										<html:optionsCollection property="loanMoodMap"
											name="loanapplytdnewAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="17%" class=td1>
									&#27599;&#26376;&#21033;&#29575; (%)
								</td>
								<td colspan="2" class="td4">
									<html:text property="loanMonthRatess" name="loanapplytdnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<html:hidden property="loanMonthRate" name="loanapplytdnewAF" />
								<td class="td1" width="20%">
									手续费率
								</td>
								<td width="15%" class=td4>
									<html:text property="loanPoundage" name="loanapplytdnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="17%" class=td1>
									房屋总价
								</td>
								<td colspan="2" class="td4">
									<html:text property="houseTotlePrice" name="loanapplytdnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td class="td1" width="20%">
									首付款占房屋总价百分比
								</td>
								<td width="15%" class=td4>
									<html:text property="firstTol" name="loanapplytdnewAF"
										styleClass="input3" readonly="true" />

								</td>
							</tr>
							<tr id="kh_1">
								<td width="17%" class=td1>
									月还本息
								</td>
								<td colspan="2" class="td4">
									<html:text property="corpusInterest" name="loanapplytdnewAF"
										styleClass="input3" readonly="true" />
								</td>

								<td width="15%" class=td4>

								</td>

							</tr>
							<tr id="kh_2">
								<td width="17%" class=td1>
									整年期总还款额
								</td>
								<td colspan="2" class="td4">
									<html:text property="entireYearMoney" name="loanapplytdnewAF"
										styleClass="input3" readonly="true" />
								</td>

								<td width="15%" class=td4>

								</td>

							</tr>
							<html:hidden name="loanapplytdnewAF" property="spid" />
							<html:hidden name="loanapplytdnewAF" property="temp_ty" />
							<html:hidden name="loanapplytdnewAF" property="office" />
							<html:hidden name="loanapplytdnewAF" property="coodm" />
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr valign="bottom">
								<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return ischekcard();">
													<bean:message key="button.sure" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td width="33%" colspan="2">
												<a
													href='javascript:excHz("<bean:write name="loanapplytdnewAF" property="photoUrl"/>");'>浏览证书</a>
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
		<form action="<%=path%>/sysloan/loanapplytdshowAC.do" method="POST"
			name="Form1" id="Form1">
		</form>
	</body>

</html>

