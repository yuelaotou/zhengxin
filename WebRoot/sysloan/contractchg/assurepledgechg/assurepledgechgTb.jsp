<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.contractchg.assurepledgechg.action.AssurepledgechgTbShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			AssurepledgechgTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/picture.js"></script>

</head>
<script type="text/javascript">

var s1="";
var s2="";
var s3="";
var tr="tr0"; 
function gettr(trindex) {
  tr=trindex;
  update1();
}
function update1() {
  	var status=document.getElementById(tr).childNodes[7].innerHTML;
  	if(status=='作废'){
		document.all.item(s2).disabled="true";
  	}
  	if(status=='正常'){
  	document.all.item(s2).disabled="";
  	}
}
function gotoAssistantor(){
 
 gotoAssistantorgpop('<%=path%>','7');
  
}

function checkCard(){
 var cardKind = document.forms[0].elements["cardKind"].value.trim();
 if(cardKind == "0"){
 var num = document.forms[0].elements["carNum"].value.trim();
 if(num!=""){
  isIdCardNo(num);
 }
 }
 
}
function checkParamV(){
  var paramvalue = document.forms[0].paramV.value;
  alert("==="+paramvalue);
  if(paramvalue == 'AB'){
   gotoContractpop('4','<%=path%>','0');
  }else{
   gotoContractpop('2','<%=path%>','0');
  }
 
}

function executeAjax() {
      
        var contractId = document.forms[0].elements["contractId"].value.trim();
	    location.href='endorsecontractTbShowAC.do?contractId='+contractId;
}
function gotoEdit(){
    
     var v1 = document.forms[0].elements["pledgePerson"].value;
     var v2 = document.forms[0].elements["pledgeMatterName"].value;
     var v3 = document.forms[0].elements["pledgeValue"].value;
     var v4 = document.forms[0].elements["cardKind"].value;
     var v5 = document.forms[0].elements["carNum"].value.trim();
     var v6 = document.forms[0].elements["paperPersonName"].value.trim();
     var area = document.forms[0].elements["area"].value.trim();
     var evaluateValue = document.forms[0].elements["evaluateValue"].value.trim();
     var tel = document.forms[0].elements["tel"].value.trim();
     var mobile=document.forms[0].elements["mobile"].value.trim();
      if(area != ""){
       if(isNaN(area)){
       document.forms[0].elements["area"].value="";
       alert("请输入正确格式的建筑面积！");
       return false;
     }
     if(area.length>10){
       alert("建筑面积位数过大！");
       return false;
     }
   }
     if(!(v1 != "" && v2 != "" && v3 != "" && v6 != "")){
       alert('请输入必添项！');
       return false;
     }
    if(v3 != ""){
       if(isNaN(v3)){
       alert("请输入正确格式的抵押物价值！");
       document.forms[0].elements["pledgeValue"].value="";
       return false;
      }
      if(v3.length>15){
       alert("抵押物价值的位数不能大于15！");
       document.forms[0].elements["pledgeValue"].value="";
       return false;
      }
     }
     if(evaluateValue != ""){
       if(isNaN(evaluateValue)){
       alert("请输入正确格式的评估值 ！");
       document.forms[0].elements["evaluateValue"].value="";
       return false;
      }
       if(evaluateValue.length>10){
       alert("评估值的位数不能大于10 ！");
       document.forms[0].elements["evaluateValue"].value="";
       return false;
      }
     }
     if(v5!= ""){
     if(v4==0){
     return isIdCardNo(v5);
      }
     }
     if(tel != ""){
       if(isNaN(tel)){
       alert("请输入正确格式的电话号码 ！");
       document.forms[0].elements["tel"].value="";
       return false;
      }
     }
     if(mobile != ""){
       if(isNaN(mobile)){
       alert("请输入正确格式的移动电话号码 ！");
       document.forms[0].elements["mobile"].value="";
       return false;
      }
     }
}

function gotoDelete(){
	if(!confirm("确定要进行该次作废吗？")){
		return false;
	}

}
function loads(){
  
  	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	
	 <logic:equal name="theEndorsecontractTbAF" property="isNeedDel" value="1">
 	 	del();
 	 </logic:equal>
	
	var contractId = document.forms[0].elements["contractId"].value;
	var isreadonly = document.forms[0].elements["isReadOnly"].value;
	//if(contractId != "" && isreadonly != ""){
	//  document.forms[0].elements["contractId"].readOnly="readonly";//合同编号
	//  document.forms[0].elements["debitter"].readOnly="readonly";//
	//  document.forms[0].elements["pledgePerson"].readOnly="readonly";//
	//  document.forms[0].elements["office"].readOnly="readonly";//
	//}else 
	
	if(isreadonly == "0"){//可以修改的字段：所有权证编号，所有权人电话，移动电话 其余只读
	  document.forms[0].elements["contractId"].readOnly="readonly";//合同编号
	  document.forms[0].elements["debitter"].readOnly="readonly";//
	  document.forms[0].elements["pledgePerson"].readOnly="readonly";//
	  document.forms[0].elements["office"].readOnly="readonly";//
	  document.forms[0].elements["pledgeContractId"].readOnly="readonly";//
	  document.forms[0].elements["assistantOrgName"].readOnly="readonly";//
	  document.forms[0].elements["pledgeMatterName"].readOnly="readonly";//
	  document.forms[0].elements["paperNum"].readOnly="";//所有权证编号
	  document.forms[0].elements["paperName"].readOnly="readonly";//
	  document.forms[0].elements["paperPersonName"].readOnly="readonly";//
	  document.forms[0].elements["cardKind"].disabled="readonly";//
	  document.forms[0].elements["carNum"].readOnly="readonly";//
	  document.forms[0].elements["tel"].readOnly="";//电话
	  document.forms[0].elements["mobile"].readOnly="";//移动电话
	  document.forms[0].elements["pledgeAddr"].readOnly="readonly";//
	  document.forms[0].elements["area"].readOnly="readonly";//
	  document.forms[0].elements["buyHouseContractId"].readOnly="readonly";//
	  document.forms[0].elements["pledgeValue"].readOnly="readonly";//
	  document.forms[0].elements["evaluateValue"].readOnly="readonly";//
	}else if(isreadonly == "1"){
	  document.forms[0].elements["contractId"].readOnly="readonly";//合同编号
	  document.forms[0].elements["debitter"].readOnly="readonly";//
	  document.forms[0].elements["pledgePerson"].readOnly="";//
	  document.forms[0].elements["office"].readOnly="readonly";//
	  //document.forms[0].elements["submit4"].disabled="disabled"
	}
	 for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="修改"){
				s1=i;
			}
			if(document.all.item(i).value=="作废"){
				s2=i;
			}
<%--			if(document.all.item(i).value=="扫描"){--%>
<%--				s3=i;--%>
<%--			}--%>
		}
	}
	
	var islist = document.all.isList.value;
	if(islist == '[]'){
	  document.all.item(s1).disabled="true";
	  document.all.item(s2).disabled="true";
<%--	  document.all.item(s3).disabled="true";--%>
	}else{
	 update1();
	}
}
function synchronization(){
 var date1 = document.forms[0].elements["pledgePerson"].value;
 document.forms[0].elements["paperPersonName"].value=date1;
} 
</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="loads();">
	<jsp:include page="/syscommon/picture/progressbar.jsp" />
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
							width="10">
							&nbsp;
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							valign="top">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" valign="top" style="PADDING-top: 7px">
										<a href="<%=path%>/sysloan/assurepledgechgTaShowAC.do"
											class=a2>担保抵押变更维护</a>
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttonbl.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										抵押合同信息
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										<a href="<%=path%>/sysloan/assurepledgechgTcShowAC.do"
											class=a2>质押合同信息</a>
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										<a href="<%=path%>/sysloan/assurepledgechgTdShowAC.do"
											class=a2>保证人信息</a>
									</td>
								</tr>
							</table>
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							align="right">
							<table width="200" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=request.getContextPath()%>/img/title_banner.gif"
											width="37" height="24">
									</td>
									<td width="228" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">合同变更&gt;基本信息变更</font>
									</td>
									<td width="35" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="10">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="151"
										class="font14">
										<b>抵押合同信息</b>
									</td>
									<td height="22"
										background="<%=request.getContextPath()%>/img/bg2.gif"
										align="center" width="773">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/assurepledgechgTbTopMaintainAC.do" method="post"
					style="margin: 0">
					<table width="95%" id="table9" cellspacing=1 cellpadding=3
						align="center">
						<tr>
							<td width="17%" class=td1>
								合同编号
							</td>
							<td class="td4" colspan="2">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTbAF" property="contractId"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
							<input type="hidden" name="paramV"
								value="<bean:write name="theEndorsecontractTbAF" property="paramValue"/>">
							<input type="hidden" name="isReadOnly"
								value="<bean:write name="theEndorsecontractTbAF" property="isReadOnly"/>">
							<td class="td1" width="17%">
								借款人姓名
							</td>
							<td class="td4" width="33%" colspan="2">
								<html:text name="theEndorsecontractTbAF" property="debitter"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>

						<tr>
							<td class=td1 width="17%">
								抵押人
								<font color="#FF0000">*</font>
							</td>
							<td class="td4" colspan="2">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTbAF" property="pledgePerson"
										onkeydown="enterNextFocus1();" onkeyup="synchronization();"
										styleClass="input3" /> </font>
							</td>
							<td class=td1 width="17%">
								抵押权人
							</td>
							<td class="td4" width="33%" colspan="2">
								<html:text name="theEndorsecontractTbAF" property="office"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class=td1>
								抵押合同编号
							</td>
							<td class="td4" colspan="2">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTbAF" property="pledgeContractId"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
							<td width="21%" class=td1>
								担保公司名称
							</td>
							<td class="td4" width="33%" colspan="2">
								<html:text name="theEndorsecontractTbAF"
									property="assistantOrgName" onkeydown="enterNextFocus1();"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								抵押物名称
								<font color="#FF0000">*</font>
							</td>
							<td class="td4" colspan="2">
								<html:text name="theEndorsecontractTbAF"
									property="pledgeMatterName" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
							<td class=td1 width="17%">
								所有权证编号
							</td>
							<td class="td4" width="33%" colspan="2">
								<html:text name="theEndorsecontractTbAF" property="paperNum"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								所有权证名称
							</td>
							<td class="td4" colspan="2">
								<html:text name="theEndorsecontractTbAF" property="paperName"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td class=td1 width="17%">
								所有权人姓名
								<font color="#FF0000">*</font>
							</td>
							<td class="td4" width="33%" colspan="2">
								<html:text name="theEndorsecontractTbAF"
									property="paperPersonName" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
						</tr>

						<tr>
							<td class=td1 width="17%">
								所有权人证件类型
							</td>
							<td class="td4" colspan="2">
								<html:select name="theEndorsecontractTbAF" property="cardKind"
									onkeydown="enterNextFocus1();" styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="map"
										name="theEndorsecontractTbAF" label="value" value="key" />
								</html:select>
							</td>
							<td class=td1 width="17%">
								所有权人证件号码
							</td>
							<td class="td4" width="33%" colspan="2">
								<html:text name="theEndorsecontractTbAF" property="carNum"
									onkeydown="enterNextFocus1();" styleClass="input3"
									onblur="checkCard();" />
							</td>
						</tr>

						<tr>
							<td class=td1 width="17%">
								所有权人固定电话
							</td>
							<td class="td4" colspan="2">
								<html:text name="theEndorsecontractTbAF" property="tel"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td class=td1 width="17%">
								所有权人移动电话
							</td>
							<td class="td4" width="33%" colspan="2">
								<html:text name="theEndorsecontractTbAF" property="mobile"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class=td1>
								抵押物地址
							</td>
							<td height="31" class="td4" colspan="2">
								<html:text name="theEndorsecontractTbAF" property="pledgeAddr"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="17%" class=td1>
								建筑面积（M
								<sup>
									2
								</sup>
								）
							</td>
							<td height="31" class="td4" width="33%" colspan="2">
								<html:text name="theEndorsecontractTbAF" property="area"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class=td1>
								购房合同编号
							</td>
							<td height="31" class="td4" colspan="2">
								<html:text name="theEndorsecontractTbAF"
									property="buyHouseContractId" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
							<td width="17%" class=td1>
								&nbsp;
							</td>
							<td height="31" class="td4" width="33%" colspan="2">
								<input name="textfield3022112282" type="text"
									id="txtsearch45225" class="input3" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td width="17%" class=td1>
								抵押值
								<font color="#FF0000">*</font>
							</td>
							<td height="31" class="td4" colspan="2">
								<html:text name="theEndorsecontractTbAF" property="pledgeValue"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="17%" class=td1>
								评估值
							</td>
							<td height="31" class="td4" width="33%" colspan="2">
								<html:text name="theEndorsecontractTbAF"
									property="evaluateValue" onkeydown="enterNextFocus1();"
									styleClass="input3" />
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
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.add" />
											</html:submit>
											&nbsp;
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonb"
												onclick="return gotoEdit(); ">
												<bean:message key="button.edit" />
											</html:submit>
											&nbsp;
										</td>
									</tr>
								</table>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="151">
										<b class="font14">抵押信息列表</b>
									</td>
									<td width="749" height="22" align="center"
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
				<html:form action="/assurepledgechgTbDownmaintainAC.do"
					method="post" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center" bgcolor="C4F0FF">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								抵押人
							</td>
							<td align="center" class=td2>
								抵押权人
							</td>
							<td align="center" class=td2>
								抵押物名称
							</td>
							<td align="center" class=td2>
								抵押物地址
							</td>
							<td align="center" class=td2>
								抵押值
							</td>
							<td align="center" class=td2>
								评估值
							</td>
							<td align="center" class=td2>
								状态
							</td>
							<td align="center" class=td2>
								浏览附件
							</td>
						</tr>
						<logic:notEmpty name="theEndorsecontractTbAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="theEndorsecontractTbAF"
								property="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>", idAF);gettr("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>", idAF);' class="<%=strClass%>"
									onDblClick="">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td>
										<bean:write name="element" property="debitter" />
									</td>
									<td>
										<bean:write name="element" property="office" />
									</td>
									<td>
										<bean:write name="element" property="pledgeMatterName" />
									</td>
									<td>
										<bean:write name="element" property="pledgeAddr" />
									</td>
									<td>
										<bean:write name="element" property="pledgeValue" />
									</td>
									<td>
										<bean:write name="element" property="evaluateValue" />
									</td>
									<td>
										<bean:write name="element" property="status" />
									</td>
									<td>
										<a
											href='javascript:excHz("<bean:write name="element" property="photoUrl"/>");'><img
												src="<%=path%>/img/lookinfo.gif" width="37" height="24">
										</a>
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>

						<input type="hidden" name="isList"
							value="<bean:write name="theEndorsecontractTbAF" property="list"/>">
						<logic:empty name="theEndorsecontractTbAF" property="list">
							<tr>
								<td colspan="16" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>

						</logic:empty>

					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td>
								&nbsp;
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
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.update" />
											</html:submit>
											&nbsp;
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonb"
												onclick="return gotoDelete(); ">
												<bean:message key="button.canceled" />
											</html:submit>
											&nbsp;
										</td>
<%--										<td width="70">--%>
<%--											<html:submit property="method" styleClass="buttonb">--%>
<%--												<bean:message key="button.scan" />--%>
<%--											</html:submit>--%>
<%--											&nbsp;--%>
<%--										</td>--%>
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
</html:html>

