<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.endorsecontract.action.EndorsecontractTcShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			EndorsecontractTcShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js"></script>
	<script type="text/javascript" src="<%=path%>/js/picture.js"></script>

</head>
<script type="text/javascript">

var s1="";
var s2="";
var s3="";
function synchronization(){
 var date1 = document.forms[0].elements["impawnPerson"].value;
 document.forms[0].elements["paperPersonName"].value=date1;
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function checkParamV(){
  var paramvalue = document.forms[0].paramV.value;
  if(paramvalue == 'AB'){
   gotoContractpop('4','<%=path%>','0','1');
  }else{
   gotoContractpop('2','<%=path%>','0','1');
  }
 
}

//function gotoAssistantor(){
 
 //gotoAssistantorgpop('<%=path%>','6');
  
//}

function executeAjax() {
      
        var contractId = document.forms[0].elements["contractId"].value.trim();
	    location.href='endorsecontractTcShowAC.do?contractId='+contractId;
}

function gotoEdit(){
    
     var v1 = document.forms[0].elements["impawnPerson"].value;
     var v2 = document.forms[0].elements["impawnMatterName"].value;
     var v3 = document.forms[0].elements["impawnValue"].value;
     var v4 = document.forms[0].elements["cardKind"].value;
     var v5 = document.forms[0].elements["carNum"].value.trim();
     var v6 = document.forms[0].elements["paperPersonName"].value.trim();
     var tel = document.forms[0].elements["tel"].value.trim();
     var mobile=document.forms[0].elements["mobile"].value.trim();
     if(!(v1 != "" && v2 != "" && v3 != "" && v6 != "")){
       alert('请输入必添项！');
       return false;
     }
     if(v3 != ""){
     if(isNaN(v3)){
     alert("请输入正确格式的质押物价值！");
     document.forms[0].elements["impawnValue"].value="";
     return false;
     }
     if(v3.length>15){
     alert("质押物价值的位数不能大于15！");
     document.forms[0].elements["impawnValue"].value="";
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
	if(!confirm("确定要进行该次删除吗？")){
		return false;
	}

}
function loads(){

  
  	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	
	<logic:equal name="theEndorsecontractTcAF" property="isNeedDel" value="1">
 	 	del();
 	 </logic:equal>
	
	var contractId = document.forms[0].elements["contractId"].value;
	var isreadonly = document.forms[0].elements["isReadOnly"].value;
	if(contractId != "" && isreadonly != ""){
	  document.forms[0].elements["contractId"].readOnly="readonly";//合同编号
	  document.forms[0].elements["debitter"].readOnly="readonly";//
	  document.forms[0].elements["impawnPerson"].readOnly="";//
	  document.forms[0].elements["office"].readOnly="readonly";//
	  document.forms[0].elements["assistantOrgName"].readOnly="readonly";//
	  document.forms[0].elements["submit4"].disabled="true"
	}else if(contractId != "" && isreadonly == ""){
	  document.forms[0].elements["contractId"].readOnly="";//合同编号
	  document.forms[0].elements["debitter"].readOnly="";//
	  document.forms[0].elements["office"].readOnly="";//
	  document.forms[0].elements["submit4"].disabled=""
	}
	 for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="修改"){
				s1=i;
			}
			if(document.all.item(i).value=="删除"){
				s2=i;
			}
			if(document.all.item(i).value=="扫描"){
				s3=i;
			}
		}
	}
	
	var islist = document.forms[1].elements["isList"].value;
	if(islist == '[]'){
	  document.all.item(s1).disabled="true";
	  document.all.item(s2).disabled="true";
	  document.all.item(s3).disabled="true";
	}
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
										<a href="./endorsecontractTaShowAC.do" class=a2>借款合同信息</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="<%=path%>/sysloan/endorsecontractTbShowAC.do"
											class=a2>抵押合同信息</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										质押合同信息
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="<%=path%>/sysloan/endorsecontractTdShowAC.do"
											class=a2>保证人信息</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="<%=path%>/sysloan/endorsecontractTeShowAC.do"
											class=a2>签订合同维护</a>
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
										<font color="00B5DB">申请贷款&gt;签订合同</font>
									</td>
									<td width="35" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="10">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="166"
										class="font14">
										<b>质押信息</b>
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
				<html:form action="/endorsecontractTcTopmaintainAC.do" method="post"
					style="margin: 0">
					<table width="95%" id="table9" cellspacing=1 cellpadding=3
						align="center">
						<tr>
							<td width="17%" class=td1>
								合同编号
							</td>
							<td class="td4" width="23%">
								<html:text name="theEndorsecontractTcAF" property="contractId"
									onkeydown="gotoEnter();" ondblclick="return executeAjax();"
									onkeydown="gotoEnter();" styleClass="input3" />
								<input type="hidden" name="paramV"
									value="<bean:write name="theEndorsecontractTcAF" property="paramValue"/>">
								<input type="hidden" name="isReadOnly"
									value="<bean:write name="theEndorsecontractTcAF" property="isReadOnly"/>">
							</td>
							<td class="td4" width="10%">
								<input type="button" name="submit4" value="..." class="buttona"
									onclick="checkParamV();">
							</td>
							<td class="td1" width="17%">
								借款人姓名
							</td>
							<td class="td4" width="33%" colspan="2">
								<html:text name="theEndorsecontractTcAF" property="debitter"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="21%">
								质押人
								<font color="#FF0000">*</font>
							</td>
							<td class="td4" colspan="2">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTcAF" property="impawnPerson"
										onkeydown="enterNextFocus1();" onkeyup="synchronization();"
										styleClass="input3" /> </font>
							</td>
							<td class=td1 width="20%">
								质押权人
							</td>
							<td class="td4" width="20%" colspan="2">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTcAF" property="office"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
						</tr>
						<tr>
							<td width="21%" class=td1>
								担保公司名称
							</td>
							<td class="td4" width="20%" colspan="2">
								<html:text name="theEndorsecontractTcAF"
									property="assistantOrgName" onkeydown="enterNextFocus1();"
									styleClass="input3" readonly="true" />
							</td>
							<td width="21%" class=td1>
								质押合同编号
							</td>
							<td class="td4" colspan="2">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTcAF" property="impawnContractId"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
						</tr>
						<tr>
							<td class=td1 width="21%">
								质押物名称
								<font color="#FF0000">*</font>
							</td>
							<td class="td4" colspan="2">
								<html:text name="theEndorsecontractTcAF"
									property="impawnMatterName" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
							<td class=td1 width="20%">
								质押物价值
								<font color="#FF0000">*</font>
							</td>
							<td class="td4" width="20%" colspan="2">
								<html:text name="theEndorsecontractTcAF" property="impawnValue"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="21%">
								所有权人姓名
								<font color="#FF0000">*</font>
							</td>
							<td class="td4" colspan="2">
								<html:text name="theEndorsecontractTcAF"
									property="paperPersonName" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
							<td class=td1 width="20%">
								所有权人证件类型
							</td>
							<td class="td4" width="20%" colspan="2">
								<html:select name="theEndorsecontractTcAF" property="cardKind"
									onkeydown="enterNextFocus1();" styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="map"
										name="theEndorsecontractTcAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td class=td1 width="21%">
								所有权人证件号码
							</td>
							<td class="td4" colspan="2">
								<html:text name="theEndorsecontractTcAF" property="carNum"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td class=td1 width="20%">
								所有权证编号
							</td>
							<td class="td4" width="20%" colspan="2">
								<html:text name="theEndorsecontractTcAF" property="paperNum"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="21%">
								所有权证名称
							</td>
							<td class="td4" colspan="2">
								<html:text name="theEndorsecontractTcAF" property="paperName"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="17%" class=td1>
								&nbsp;
							</td>
							<td height="31" class="td4" width="33%" colspan="2">
								<input name="textfield3022112282" type="text"
									id="txtsearch45225" onkeydown="enterNextFocus1();"
									class="input3" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td class=td1 width="21%">
								所有权人固定电话
							</td>
							<td class="td4" colspan="2">
								<html:text name="theEndorsecontractTcAF" property="tel"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td class=td1 width="20%">
								所有权人移动电话
							</td>
							<td class="td4" width="20%" colspan="2">
								<html:text name="theEndorsecontractTcAF" property="mobile"
									onkeydown="enterNextFocus1();" styleClass="input3" />
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
											<html:submit property="method" styleClass="buttonb"
												onclick="return gotoEdit(); ">
												<bean:message key="button.edit" />
											</html:submit>
											&nbsp;
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.add" />
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="202">
										<b class="font14">质押信息列表</b>
									</td>
									<td width="703" height="22" align="center"
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
				<html:form action="/endorsecontractTcDownmaintainAC.do"
					method="post" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center" bgcolor="C4F0FF">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								质押人
								<br>
							</td>
							<td align="center" class=td2>
								质押权人
							</td>
							<td align="center" class=td2>
								质押物名称
							</td>
							<td align="center" class=td2>
								质押物价值
							</td>
							<td align="center" class=td2>
								所有权人
							</td>
							<td align="center" class=td2>
								状态
							</td>
							<td align="center" class=td2>
								浏览附件
							</td>
						</tr>
						<logic:notEmpty name="theEndorsecontractTcAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="theEndorsecontractTcAF"
								property="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>", idAF);'
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
										<bean:write name="element" property="impawnPerson" />
									</td>
									<td>
										<bean:write name="element" property="office" />
									</td>
									<td>
										<bean:write name="element" property="impawnMatterName" />
									</td>
									<td>
										<bean:write name="element" property="impawnValue" />
									</td>
									<td>
										<bean:write name="element" property="paperPersonName" />
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
							value="<bean:write name="theEndorsecontractTcAF" property="list"/>">
						<logic:empty name="theEndorsecontractTcAF" property="list">
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
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonb"
												onclick="return gotoDelete(); ">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.scan" />
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
</body>
</html:html>
