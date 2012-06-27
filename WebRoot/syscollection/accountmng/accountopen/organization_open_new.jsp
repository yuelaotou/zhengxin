<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();

	String openstatus = null;
	if (request.getAttribute("openstatus") == null) {
		openstatus = "1";
	} else {
		openstatus = request.getAttribute("openstatus").toString();
	}

	String payMode = (String) request.getAttribute("payMode");

	String state = null;
	if (request.getAttribute("state") == null) {
		state = "2";
	} else {
		state = request.getAttribute("state").toString();
	}
	String orgid = null;
	if (request.getAttribute("orgid") != null) {
		orgid = (String) request.getAttribute("orgid");
	}
%>

<html:html>
<head>
	<title>开户销户&gt;&gt;单位开户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js" />	


</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<script language="javascript">
</script>
<script type="text/javascript" language="javascript">
var flag = "0";
function init(){
	if(<%=openstatus%>!=1){
		document.forms[0].elements["org.firstPayMonth"].readOnly="true";
		document.forms[0].elements["org.empRate"].readOnly="true";
		document.forms[0].elements["org.orgRate"].readOnly="true";
		document.forms[0].elements["org.orgInfo.officecode"].disabled=true;
		document.forms[0].elements["org.orgInfo.collectionBankId"].disabled=true;
		// document.forms[0].elements["org.payMode"].disabled=true;
		//document.forms[0].elements["org.orgInfo.collectionBankId"].disabled=true;
	}
   verdictPayMode();
	if(<%=openstatus%>!=1){
		//document.forms[0].elements["org.payPrecision"].disabled=true;
	}
	
}
// 检验e_mail
function isEmail(s){ 
  if(s == "") return true; 
  s = s.replace(/＠/ig, "@"); 
  if (s.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1) return true; 
  else return false; 
} 

// 判断是否为数字
function isNumber(String)
{ 
    var Letters = "1234567890-"; //可以自己增加可输入值
    var i;
    var c;
      if(String.charAt( 0 )=='-')
 return false;
      if( String.charAt( String.length - 1 ) == '-' )
          return false;
     for( i = 0; i < String.length; i ++ )
     {
          c = String.charAt( i );
   if (Letters.indexOf( c ) < 0)
          return false;
}
     return true;
}


function check(){
	
	var ArtificialPerson=document.forms[0].elements["org.orgInfo.artificialPerson"].value.trim();
		document.forms[0].elements["org.orgInfo.artificialPerson"].value=ArtificialPerson;
	var Address=document.forms[0].elements["org.orgInfo.address"].value.trim();
		document.forms[0].elements["org.orgInfo.address"].value=Address;
	var payBankName=document.forms[0].elements["org.orgInfo.payBank.name"].value.trim();
		document.forms[0].elements["org.orgInfo.payBank.name"].value=payBankName;
	var Inspector=document.forms[0].elements["org.orgInfo.inspector"].value.trim();
		document.forms[0].elements["org.orgInfo.inspector"].value=Inspector;
<%--	var orgAgentNum=document.forms[0].elements["org.orgAgentNum"].value.trim();--%>
<%--		document.forms[0].elements["org.orgAgentNum"].value=orgAgentNum;--%>
	var code=document.forms[0].elements["org.orgInfo.code"].value.trim();
	  	document.forms[0].elements["org.orgInfo.code"].value=code;
	var taxRegNum=document.forms[0].elements["org.orgInfo.taxRegNum"].value.trim();
		document.forms[0].elements["org.orgInfo.taxRegNum"].value=taxRegNum;
	
	
  if(document.forms[0].elements["org.orgInfo.name"].value.trim()!=""){
  document.forms[0].elements["org.orgInfo.name"].value=document.forms[0].elements["org.orgInfo.name"].value.trim();
  	collectionBankId=document.forms[0].elements["org.orgInfo.collectionBankId"].value.trim();
  	if(collectionBankId==null||collectionBankId==""){
  		alert("归集银行不能为空！");
	  		return false;
  	}else{
	  	officecode=document.forms[0].elements["org.orgInfo.officecode"].value.trim();
	  	if(officecode==null||officecode==""){
	  		alert("办事处不能为空！");
	  		return false;
	  	}
	  	if(payBankName==null || payBankName==""){
	  	    alert("单位发薪银行不能为空！");
	  		return false;
	  	}
	  	var accountnum=document.forms[0].elements["org.orgInfo.payBank.accountNum"].value.trim();
	  	if(accountnum==null||accountnum==""){
	  	    alert("发薪行账号不能为空！");
	  		return false;
	  	}else{
	  		
		  			postalcode=document.forms[0].elements["org.orgInfo.postalcode"].value.trim();
		  			document.forms[0].elements["org.orgInfo.postalcode"].value=postalcode;
		  			if(!isNumber(postalcode)){
			  			alert("邮政编码必须是数字！");
			  			return false;
		  			}else{
		  				tel=document.forms[0].elements["org.orgInfo.tel"].value.trim();
		  				document.forms[0].elements["org.orgInfo.tel"].value=tel;
		  				if(!isNumber(tel)){
			  				alert("单位电话必须是数字！");
				  			return false;
		  				}else{
		  					accountNum=document.forms[0].elements["org.orgInfo.payBank.accountNum"].value.trim();
		  					document.forms[0].elements["org.orgInfo.payBank.accountNum"].value=accountNum;
		  					if(!isNumber(accountNum)){
			  					alert("发薪行帐号必须是数字！");
					  			return false;
		  					}else{
			  					paydate=document.forms[0].elements["org.orgInfo.paydate"].value.trim();
			  					document.forms[0].elements["org.orgInfo.paydate"].value=paydate;
			  					if(!isNumber(paydate)||paydate<0||paydate>30){
				  					alert("发薪日必须是数字！1~31号");
						  			return false;
			  					}else{
			  						transactorName=document.forms[0].elements["org.orgInfo.transactor.name"].value.trim();
			  						document.forms[0].elements["org.orgInfo.transactor.name"].value=transactorName;
			  						if(transactorName==null){
			  							alert("请填写单位经办人！");
			  							return false;
			  						}else{
			  						  	email=document.forms[0].elements["org.orgInfo.transactor.email"].value.trim();
			  						  	document.forms[0].elements["org.orgInfo.transactor.email"].value=email;
				  						if(!isEmail(email)){
				  							alert("邮箱的格式不正确!格式如：abc@163.com");
				  							return false;
				  						}else{
				  							telephone=document.forms[0].elements["org.orgInfo.transactor.telephone"].value.trim();
				  							document.forms[0].elements["org.orgInfo.transactor.telephone"].value=telephone;
				  							if(!isNumber(telephone)){
				  								alert("经办人电话必须是数字！");
				  								return false;
				  							}else{
				  								mobileTelephone=document.forms[0].elements["org.orgInfo.transactor.mobileTelephone"].value.trim();
				  								document.forms[0].elements["org.orgInfo.transactor.mobileTelephone"].value=mobileTelephone;
				  								if(!isNumber(mobileTelephone)){
				  									alert("经办人移动电话必须是数字！");
				  									return false;
				  								}else{
					  								firstPayMonth=document.forms[0].elements["org.firstPayMonth"].value.trim();
													if (firstPayMonth==null||firstPayMonth==0||!isNumber(firstPayMonth)||firstPayMonth.substring(0,4)<1900||firstPayMonth.substring(4,6)>12||firstPayMonth.substring(4,6)<1){
												        alert("请录入缴存月份！格式如：200606");
														return false;
													}else{
														var payMode=document.forms[0].elements["org.payMode"].value;
														if(payMode==null||payMode==0){
															alert("请选择缴存方式！");
															return false;
														}else{
													  	  var orgRate=document.forms[0].elements["org.orgRate"].value.trim();
													      var empRate=document.forms[0].elements["org.empRate"].value.trim();
													      if(<%=state%>==1&<%=openstatus%>!=1){
													      	// 修改状态
													      	if (document.forms[0].elements["org.payMode"].value==1&&(orgRate==null||empRate==null||orgRate>1||orgRate<0||empRate>1||empRate<0||!checkRate(empRate)||!checkRate(orgRate))){
													        	alert("请录入缴率！格式如：0.23");
																return false;
															}else{			
																var payPrecision=document.forms[0].elements["org.payPrecision"].value.trim();	
																if (document.forms[0].elements["org.payMode"].value==1&&(payPrecision==""||payPrecision==null||payPrecision==0)){
																	alert("请录入缴存精度!");
																	return false;
																}else{
																   var aaa=document.orgkhAF.type.value;
																    if(temptype==1){
																	   if(confirm("是否添加单位版?")){
																	   document.orgkhAF.isOrgorcenter.value='1';
																	   }
																   }
																	return true;
																}
															}
													      }else{
													      	if (document.forms[0].elements["org.payMode"].value==1&&(orgRate==null||orgRate==0||empRate==null||empRate==0||orgRate>1||orgRate<0||empRate>1||empRate<0||!checkRate(empRate)||!checkRate(orgRate))){
													        	alert("请录入缴率！格式如：0.23");
																return false;
															}else{			
																var payPrecision=document.forms[0].elements["org.payPrecision"].value.trim();	
																if (document.forms[0].elements["org.payMode"].value==1&&(payPrecision==""||payPrecision==null||payPrecision==0)){
																	alert("请录入缴存精度!");
																	return false;
																}else{
																   var temptype=document.orgkhAF.type.value;
																   if(temptype==1){
																   	   <security:orghave>
																		   if(confirm("是否添加单位版?")){
																		   document.orgkhAF.isOrgorcenter.value='1';
																		   }
																   	   </security:orghave>
																   }
																	return true;
																}
															}
													      }
													      	
														}
													}
				  								}
				  							}
				  						}
			  						}
			
			  					}
		  					}
		  					
		  				}
			  			
		  		
		  	} 
	  	}
  	}
  	
 
  }else{
  	alert("请录入单位名称！");
  	return false;
  }
  
}

function checkOrgpayDay(){
    var payday=document.forms[0].elements["org.firstPayMonth"].value.trim();
    var payday_ = payday.match(/^([0-9]{6})$/); 
    if(payday_==null){
    	alert("请正确录入初缴年月信息！格式如：195605");
    	document.forms[0].elements["org.firstPayMonth"].focus();
    	return false;
    }
}
//验证单位缴率
function validateOrgJl(){
	var v = document.forms[0].elements["org.orgRate"].value;
	var r =v.match('^0\.\d{1,2}$');
	if(r==null){
		alert("单位缴率输入格式错误!必须小于1,小数点保留两位.");
		document.forms[0].elements["org.orgRate"].focus();
				return false;
			}else{
				return true;
			}	
}
//验证职工缴率
function validateEmpJl(){
	var v = document.forms[0].elements["org.empRate"].value;
	var r =v.match('/^0\.\d{1,2}$/');
	if(r==null){
		alert("职工缴率输入格式错误!必须小于1,小数点保留两位.");
		document.forms[0].elements["org.empRate"].focus();
				return false;
			}else{
				return true;
			}
}
//根据缴存方式判断缴存率与精度是否可用
function verdictPayMode(){
	var i = document.forms[0].elements["org.payMode"].value;
	if(i==1){
	// 按比率
		if(<%=state%>==1){
			if(flag=="1"){
				document.forms[0].elements["org.empRate"].disabled=false;
				document.forms[0].elements["org.empRate"].value="0.00";
				document.forms[0].elements["org.orgRate"].disabled=false;
				document.forms[0].elements["org.orgRate"].value="0.00";
				document.forms[0].elements["org.payPrecision"].disabled=false;
				//document.forms[0].elements["org.payPrecision"].value=1;
			}else{
				document.forms[0].elements["org.empRate"].disabled=false;
				document.forms[0].elements["org.orgRate"].disabled=false;
				document.forms[0].elements["org.payPrecision"].disabled=false;
				//document.forms[0].elements["org.payPrecision"].disabled=false;
			}
				
			if(<%=payMode%>==2){
				document.forms[0].elements["org.empRate"].disabled=false;
				document.forms[0].elements["org.empRate"].value="0.00";
				document.forms[0].elements["org.orgRate"].disabled=false;
				document.forms[0].elements["org.orgRate"].value="0.00";
				document.forms[0].elements["org.payPrecision"].disabled=false;
				//document.forms[0].elements["org.payPrecision"].disabled=false;
			}
			
		}else{
			document.forms[0].elements["org.empRate"].disabled=false;
			document.forms[0].elements["org.empRate"].value="0.00";
			document.forms[0].elements["org.orgRate"].disabled=false;
			document.forms[0].elements["org.orgRate"].value="0.00";
			document.forms[0].elements["org.payPrecision"].disabled=false;
			//document.forms[0].elements["org.payPrecision"].value=1;
		}
	}
	if(i==2){
	// 按缴额
	flag="1";
		document.forms[0].elements["org.empRate"].disabled=true;
		document.forms[0].elements["org.empRate"].value="";
		document.forms[0].elements["org.orgRate"].disabled=true;
		document.forms[0].elements["org.orgRate"].value="";
		document.forms[0].elements["org.payPrecision"].disabled=true;
		//document.forms[0].elements["org.payPrecision"].value="";
	}

}
function verdictOffice(){
	var officecode=document.forms[0].elements["org.orgInfo.officecode"].value;

	if(<%=orgid%>!=null){
		document.URL=('orgopenshowAC.do?officecode='+officecode+'&orgid='+<%=orgid%>);
	}else{
		document.URL=('orgopenshowAC.do?officecode='+officecode);
	}
}
function checkback(){
	document.forms[0].elements["org.payMode"].value=0;
	document.forms[0].elements["org.orgRate"].value=0.00;
	document.forms[0].elements["org.empRate"].value=0.00;
}
function reportErrors() {
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>
}
function toPop() {
	gotoOrgpop(1234,'<%=path%>','0');
}
function executeAjax() {
  var x = document.forms[0].elements["org.id"].value.trim();
	if(x==""){
		toPop();
	}
	location.href='<%=path%>/syscollection/accountmng/accountopen/orgOpenInfoAAC.do?orgId='+x;
}
function enterToSubmit(){
  if(event.keyCode == 13){
   var x = document.forms[0].elements["org.id"].value.trim();
	if(x==""){
		toPop();
	}
	location.href='<%=path%>/syscollection/accountmng/accountopen/orgOpenInfoAAC.do?orgId='+x;
  }
}
function orgchange(){
var changeInfo="<%=request.getAttribute("orgnotchang")%>";
	if(changeInfo=="centerOrg"){
		document.forms[0].elements["org.orgInfo.code"].disabled=true;
		document.forms[0].elements["org.orgInfo.name"].disabled=true;
		document.forms[0].elements["org.orgInfo.taxRegNum"].disabled=true;
		document.forms[0].elements["org.orgInfo.artificialPerson"].disabled=true;
		document.forms[0].elements["org.orgInfo.character"].disabled=true;
		document.forms[0].elements["org.orgInfo.industry"].disabled=true;
		document.forms[0].elements["org.orgInfo.deptInCharge"].disabled=true;
		document.forms[0].elements["org.orgInfo.address"].disabled=true;
		document.forms[0].elements["org.orgInfo.postalcode"].disabled=true;
		document.forms[0].elements["org.orgInfo.tel"].disabled=true;
		document.forms[0].elements["org.orgInfo.region"].disabled=true;
		document.forms[0].elements["org.orgInfo.payBank.name"].disabled=true;
		document.forms[0].elements["org.orgInfo.paydate"].disabled=true;
		document.forms[0].elements["org.orgInfo.collectionBankId"].disabled=true;
		document.forms[0].elements["org.orgInfo.transactor.name"].disabled=true;
		document.forms[0].elements["org.orgInfo.transactor.email"].disabled=true;
		document.forms[0].elements["org.orgInfo.transactor.telephone"].disabled=true;
		document.forms[0].elements["org.orgInfo.transactor.mobileTelephone"].disabled=true;
		document.forms[0].elements["org.orgInfo.inspector"].disabled=true;
		document.forms[0].elements["org.payMode"].disabled=true;
		document.forms[0].elements["org.orgRate"].disabled=true;
		document.forms[0].elements["org.empRate"].disabled=true;
		document.forms[0].elements["org.firstPayMonth"].disabled=true;
		//document.forms[0].elements["org.payPrecision"].disabled=true;
		document.forms[0].elements["org.orgInfo.payBank.accountNum"].disabled=true;
		document.forms[0].elements["org.orgInfo.officecode"].disabled=true;
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onLoad="reportErrors();init();orgchange();">
	<html:form action="/orgInfo_kh_save" method="post">
		<html:hidden name="orgkhAF" property="isOrgorcenter"></html:hidden>
		<html:hidden name="orgkhAF" property="type"></html:hidden>
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
											办理开户
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="organization_open_show.do" class=a2>开户维护</a>
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
										<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">开户销户&gt;单位开户</font>
										</td>
										<td width="115" class=font14>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">自 然 信 息</b>
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
						<security:orgcan>
							<tr>
								<td width="13%" class="td1">
									单位编号
									<font color="#FF0000">*</font>
								</td>
								<td>
									<html:text property="org.id" styleClass="input3"
										ondblclick="executeAjax();" onkeydown="enterToSubmit();"
										maxlength="50"></html:text>
								</td>
								<td width="8%">
									<input type="button" name="searchbutton" value="..."
										onclick="toPop()" class="buttona" />

								</td>
							</tr>
						</security:orgcan>
						<tr>
							<td width="12%" class="td1">
								办事处名称
								<font color="#FF0000">*</font>
							</td>
							<td width="25%">
								<html:select property="org.orgInfo.officecode"
									styleClass="input4" onkeydown="enterNextFocus1();"
									onchange="verdictOffice();">
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="13%" class="td1">
								组织机构代码
							</td>
							<td>
								<html:text property="org.orgInfo.code" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="50"></html:text>
							</td>

						</tr>
						<tr>
							<td width="13%" class="td1">
								单位名称
								<font color="#FF0000">*</font>
							</td>
							<td>
								<html:text property="org.orgInfo.name" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="50"></html:text>
							</td>

							<td width="13%" class="td1">
								税务登记号
							</td>
							<td>
								<html:text property="org.orgInfo.taxRegNum" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								单位法人
							</td>
							<td>
								<html:text property="org.orgInfo.artificialPerson"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="50"></html:text>

							</td>
							<td width="12%" class="td1">
								单位性质
							</td>
							<td width="25%">

								<html:select property="org.orgInfo.character"
									onkeydown="enterNextFocus1();" styleClass="input4">
									<html:optionsCollection property="natureofunitsMap"
										name="orgkhAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								所属行业
							</td>
							<td>

								<html:select property="org.orgInfo.industry"
									onkeydown="enterNextFocus1();" styleClass="input4">
									<html:optionsCollection property="industyMap" name="orgkhAF"
										label="value" value="key" />
								</html:select>
							</td>
							<td width="11%" class="td1">
								主管部门
							</td>
							<td width="21%">
								<html:select property="org.orgInfo.deptInCharge"
									onkeydown="enterNextFocus1();" styleClass="input4">
									<html:optionsCollection property="authoritiesMap"
										name="orgkhAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>

							<td width="13%" class="td1">
								单位地址
							</td>
							<td width="18%">

								<html:text property="org.orgInfo.address" styleClass="input3"
									maxlength="25" onkeydown="enterNextFocus1();" maxlength="50"></html:text>
							</td>

							<td width="12%" class="td1">
								邮政编码
							</td>
							<td width="25%">
								<html:text property="org.orgInfo.postalcode" styleClass="input3"
									maxlength="6" onkeydown="enterNextFocus1();" maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<td class="td1">
								单位电话
							</td>
							<td>
								<html:text property="org.orgInfo.tel" styleClass="input3"
									maxlength="20" onkeydown="enterNextFocus1();" maxlength="50"></html:text>
							</td>
							<td class="td1">
								所在地区
							</td>
							<td>
								<html:select property="org.orgInfo.region"
									onkeydown="enterNextFocus1();" styleClass="input4">
									<html:optionsCollection property="inareaMap" name="orgkhAF"
										label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1" width="17%">
								单位发薪银行
								<font color="#ff0000">*</font>
							</td>
							<td width="33%">
								<html:text property="org.orgInfo.payBank.name"
									styleClass="input3" maxlength="20"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td class="td1" width="17%">
								发薪行账号
								<font color="#ff0000">*</font>
							</td>
							<td width="33%">
								<html:text property="org.orgInfo.payBank.accountNum"
									styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td class="td1">
								发薪日
							</td>
							<td>
								<html:text property="org.orgInfo.paydate" styleClass="input3"
									maxlength="2" onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td class="td1">
								归集银行
							</td>
							<td>

								<html:select property="org.orgInfo.collectionBankId"
									styleClass="input4" onkeydown="enterNextFocus1();">
									<html:options collection="collBankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1">
								单位经办人
								<font color="#FF0000"></font>
							</td>
							<td>
								<html:text property="org.orgInfo.transactor.name"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="20"></html:text>

							</td>
							<td class="td1">
								经办人E-mail
							</td>
							<td>
								<html:text property="org.orgInfo.transactor.email"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="35"></html:text>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								经办人电话
							</td>
							<td width="18%">
								<html:text property="org.orgInfo.transactor.telephone"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="20"></html:text>
							</td>
							<td width="11%" class="td1">
								经办人移动电话
							</td>
							<td width="21%">
								<html:text property="org.orgInfo.transactor.mobileTelephone"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<td width="11%" class="td1">
								稽查员
							</td>
							<td width="21%">
								<html:text property="org.orgInfo.inspector" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="20"></html:text>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">归 集 信 息</b>
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
							<td width="18%" class="td1">
								缴存方式
							</td>
							<td width="29%">

								<html:select property="org.payMode"
									onkeydown="enterNextFocus1();" styleClass="input4"
									onchange="verdictPayMode();">
									<html:option value=""></html:option>
									<html:optionsCollection property="orgpaywayMap" name="orgkhAF"
										label="value" value="key" />
								</html:select>
							</td>
							<!--<td width="18%" class="td1">
								单位代扣号
							</td>
							<td width="35%">
								<html:text property="org.orgAgentNum" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="20"></html:text>
							</td>-->

						</tr>
						<tr id="trdis1">
							<td width="18%" class="td1">
								单位缴率
								<font color="#FF0000">*</font>
							</td>
							<td width="29%">
								<html:text property="org.orgRate" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="4"></html:text>
							</td>
							<td width="18%" class="td1">
								职工缴率
								<font color="#FF0000">*</font>
							</td>
							<td width="35%">
								<html:text property="org.empRate" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="4"></html:text>
							</td>
						</tr>
						<tr>
							<td width="18%" class="td1">
								初缴年月
								<font color="#FF0000">*</font>
							</td>
							<td width="29%">
								<html:text property="org.firstPayMonth" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="6"></html:text>
							</td>
							<td width="18%" class="td1">
								缴存精度
							</td>
							<td width="35%">
								<html:select property="org.payPrecision"
									onkeydown="enterNextFocus1();" styleClass="input4">
									<html:optionsCollection property="paymentaccuracyMap"
										name="orgkhAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<security:orghave>
										</security:orghave>
										<security:orgcannot>
											<td>
												<logic:equal name="orgkhAF" property="type" value="1">
													<html:submit property="method" styleClass="buttona"
														onclick="return check();">
														<bean:message key="button.add" />
													</html:submit>
												</logic:equal>
											</td>
											<td>
												<logic:equal name="orgkhAF" property="type" value="2">
													<html:submit property="method" styleClass="buttona"
														onclick="return check();">
														<bean:message key="button.update" />
													</html:submit>
													<td width="70">
														<html:submit property="method" styleClass="buttona"
															onclick="checkback();">
															<bean:message key="button.back" />
														</html:submit>
													</td>
												</logic:equal>
											</td>
										</security:orgcannot>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<form action="orgCollBankShowListAAC.do" name="form1">
		<input type="hidden" name="hname">
	</form>
</body>
</html:html>
