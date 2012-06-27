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

function reportsErrors(){
<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"

		alert(message);
	</logic:messagesPresent>	
}   

	function gotoType1(){

		document.loanapplytcnewAF.elements["houseTypehidden"].value="01";			
	}
	function gotoType2(){
	
		document.loanapplytcnewAF.elements["houseTypehidden"].value="02";
	}
	
	

var s1="";
var s2="";

function loads(){
 	<logic:equal name="loanapplytcnewAF" property="isNeedDel" value="1">
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
		}
	} 


	var coodm = document.loanapplytcnewAF.elements["coodm"].value.trim();
	if(coodm==15){
		document.all.item(s1).disabled="true";
<%--		document.all.item(s2).disabled="true";--%>
	}

	var houseTypehidden = document.loanapplytcnewAF.elements["houseTypehidden"].value.trim();
	
	if(houseTypehidden=="01"){
		document.forms[0].elements["bargainorName"].disabled="disabled";
		document.forms[0].elements["bargainorCardKind"].disabled="disabled";
		document.forms[0].elements["bargainorCardNum"].disabled="disabled";
		document.forms[0].elements["bargainorTel"].disabled="disabled";
		document.forms[0].elements["bargainorHousepropertyCode"].disabled="disabled";
		document.forms[0].elements["bargainorMobile"].disabled="disabled";
		document.forms[0].elements["bargainorHouseAddr"].disabled="disabled";
		document.forms[0].elements["bargainorPaybank"].disabled="disabled";
		document.forms[0].elements["bargainorPaybankAcc"].disabled="disabled";
		document.forms[0].elements["bargainorHouseArea"].disabled="disabled";
		document.forms[0].elements["bargainorTotlePrice"].disabled="disabled";
		document.forms[0].elements["bargainorHouseAge"].disabled="disabled";
		document.forms[0].elements["bargainorAgreementDate"].disabled="disabled";
		document.forms[0].elements["remark2"].disabled="disabled";
	}
	if(houseTypehidden=="02"){
		document.forms[0].elements["orgName"].disabled="disabled";
		document.forms[0].elements["developerTel"].disabled="disabled";
		document.forms[0].elements["developerPaybank"].disabled="disabled";
		document.forms[0].elements["developerPaybankAAcc"].disabled="disabled";
		document.forms[0].elements["floorId"].disabled="disabled";
		document.forms[0].elements["floorNum"].disabled="disabled";
		document.forms[0].elements["roomNum"].disabled="disabled";
		document.forms[0].elements["firstPay"].disabled="disabled";
		document.forms[0].elements["houseTotlePrice"].disabled="disabled";
		document.forms[0].elements["houseArea"].disabled="disabled";
		document.forms[0].elements["housePrice"].disabled="disabled";
		document.forms[0].elements["buyHouseContractId"].disabled="disabled";
		document.forms[0].elements["firstTol"].disabled="disabled";
		document.forms[0].elements["isNowhouse"].disabled="disabled";
		document.forms[0].elements["overDate"].disabled="disabled";
		document.forms[0].elements["buildRightNum"].disabled="disabled";
		document.forms[0].elements["agreementDate"].disabled="disabled";
		document.forms[0].elements["houseAddr"].disabled="disabled";
		document.forms[0].elements["remark1"].disabled="disabled";
	}

}

</script>
	<body bgcolor="#FFFFFF" text="#000000" onload="loads();">
		<jsp:include page="/syscommon/picture/progressbar.jsp" />
		<html:form action="/baseinfochgtdmaintainAC.do">
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
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/baseinfochgtashowAC.do" class=a2>基本信息变更维护</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/baseinfochgtbshowAC.do" class=a2>借款人信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/baseinfochgtcshowAC.do" class=a2>共同借款人信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttonbl.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/baseinfochgtdshowAC.do" class=a2>购房信息</a>
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
											<td height="22" bgcolor="#CCCCCC" align="center" width="122"
												class="font14">
												<b>购 房 信 息</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="802">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<table width="95%" id="table9" cellspacing=1 cellpadding=3
							align="center">
							<tr>
								<td rowspan="12" class=td1 width="11%">
									<html:radio name="loanapplytcnewAF" property="houseType"
										value="01" disabled="true" />
									商品房
								</td>
								<td width="21%" class=td1>
									合同编号
								</td>
								<td class="td4" width="16%">
									<html:text property="contractId" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>

								<td class="td1" width="20%">
									借款人姓名
								</td>
								<td class="td4" width="20%">
									<html:text property="borrowerName" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="21%" class=td1>
									售房单位
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="16%">
									<html:text property="orgName" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>

								<td class="td1" width="20%">
									联系电话
								</td>
								<td class="td4" width="20%">
									<html:text property="developerTel" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="21%" class=td1>
									售房单位售房款开户行
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="20%">
									<html:text property="developerPaybank" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td width="20%" class=td1>
									售房款开户账号
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="20%">
									<html:text property="developerPaybankAAcc"
										name="loanapplytcnewAF" styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td class=td1 width="21%">
									所在楼盘
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="20%">
									<html:text property="floorId" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>

								<td class=td1 width="20%">
									楼盘号
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="20%">
									<html:text property="floorNum" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>

							</tr>
							<tr>
								<td class=td1 width="21%">
									层次室号
									<font color="#FF0000">*</font>
								</td>
								<td class="td4">
									<html:text property="roomNum" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td class=td1 width="20%">
									首期付款
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="20%">
									<html:text property="firstPay" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td class=td1 width="21%">
									房屋总价（元）
									<font color="#FF0000">*</font>
								</td>
								<td class="td4">
									<html:text property="houseTotlePrice" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td class=td1 width="20%">
									建筑面积（M
									<sup>
										2
									</sup>
									）
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="20%">
									<html:text property="houseArea" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td class=td1 width="21%">
									&#25151;&#23627;&#21333;&#20215;&#65288;&#20803;/ M
									<sup>
										2）
									</sup>
									<font color="#FF0000">*</font>
								</td>
								<td class="td4">
									<html:text property="housePrice" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td class=td1 width="20%">
									购房合同编号
								</td>
								<td class="td4" width="20%">
									<html:text property="buyHouseContractId"
										name="loanapplytcnewAF" styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="21%" class=td1>
									首期占房屋总价
								</td>
								<td height="31" class="td4">
									<html:text property="firstTol" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td width="20%" class=td1>
									是否现房
								</td>
								<td height="31" class="td4">
									<html:text property="isNowhouse" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>

							</tr>
							<tr>
								<td width="21%" class=td1>
									竣工日期
								</td>
								<td height="31" class="td4">
									<html:text property="overDate" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td width="20%" class=td1>
									产权证号码
								</td>
								<td height="31" class="td4" width="20%">
									<html:text property="buildRightNum" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="21%" class=td1>
									购房合同签字日期
								</td>
								<td height="31" class="td4">
									<html:text property="agreementDate" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td width="20%" class=td1>
									&nbsp;
								</td>
								<td height="31" class="td4" width="20%">
									<input name="textfield3022112283" type="text"
										id="txtsearch45225" class="input3" readonly="true">
								</td>
							</tr>
							<tr>
								<td width="21%" class="td1">
									购房地址
								</td>
								<td colspan="4" class="td4">
									<html:text property="houseAddr" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="21%" class="td1">
									备注
								</td>
								<td colspan="4" class="td4">
									<html:text property="remark1" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<html:hidden name="loanapplytcnewAF" property="houseTypehidden" />
								<html:hidden name="loanapplytcnewAF" property="coodm" />
								<td width="11%" rowspan="8" bgcolor="#D1EDD7">
									<html:radio name="loanapplytcnewAF" property="houseType"
										value="02" disabled="true" />
									二手房
								</td>
								<td width="21%" bgcolor="#D1EDD7">
									售房人姓名
									<font color="#FF0000">*</font>
								</td>
								<td class="td4">
									<html:text property="bargainorName" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td bgcolor="#D1EDD7" width="20%">
									售房人证件类型
								</td>
								<td class="td4" colspan="2">
									<html:text property="bargainorCardKind" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>

							</tr>
							<tr>
								<td width="21%" bgcolor="#D1EDD7">
									售房人证件号码
								</td>
								<td class="td4">
									<html:text property="bargainorCardNum" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td bgcolor="#D1EDD7" width="20%">
									固定电话
								</td>
								<td class="td4" width="20%">
									<html:text property="bargainorTel" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="21%" bgcolor="#D1EDD7">
									原房产权编号
								</td>
								<td class="td4">
									<html:text property="bargainorHousepropertyCode"
										name="loanapplytcnewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td bgcolor="#D1EDD7" width="20%">
									移动电话
								</td>
								<td class="td4" width="20%">
									<html:text property="bargainorMobile" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="21%" bgcolor="#D1EDD7">
									房屋坐落
									<font color="#FF0000">*</font>
								</td>
								<td colspan="4">
									<html:text property="bargainorHouseAddr"
										name="loanapplytcnewAF" styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="21%" bgcolor="#D1EDD7">
									售房人收款银行
									<font color="#FF0000">*</font>
								</td>
								<td class="td4">
									<html:text property="bargainorPaybank" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td width="20%" bgcolor="#D1EDD7">
									售房人收款银行账号
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="20%">
									<html:text property="bargainorPaybankAcc"
										name="loanapplytcnewAF" styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="21%" bgcolor="#D1EDD7">
									建筑面积（M
									<sup>
										2
									</sup>
									）
									<font color="#FF0000">*</font>
								</td>
								<td class="td4">
									<html:text property="bargainorHouseArea"
										name="loanapplytcnewAF" styleClass="input3" readonly="true" />
								</td>
								<td width="20%" bgcolor="#D1EDD7">
									评估价值（元）
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="20%">
									<html:text property="bargainorTotlePrice"
										name="loanapplytcnewAF" styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td height="31" width="21%" bgcolor="#D1EDD7">
									房龄（年）
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" height="31">
									<html:text property="bargainorHouseAge" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td bgcolor="#D1EDD7" height="31" width="20%">
									协议签订日期
								</td>
								<td class="td4" height="31" width="20%">
									<html:text property="bargainorAgreementDate"
										name="loanapplytcnewAF" styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="21%" bgcolor="#D1EDD7">
									备注
								</td>
								<td class="td4" colspan="4">
									<html:text property="remark2" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr valign="bottom">
								<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.sure" />
												</html:submit>
											<td>
<%--											<td width="70">--%>
<%--												<html:submit property="method" styleClass="buttona">--%>
<%--													<bean:message key="button.scan" />--%>
<%--												</html:submit>--%>
<%--											<td>--%>
											<td width="33%" colspan="2">
												<a
													href='javascript:excHz("<bean:write name="loanapplytcnewAF" property="photoUrl"/>");'>浏览证书</a>
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
</html>


