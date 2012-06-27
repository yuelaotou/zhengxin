<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>

<%
String path = request.getContextPath();
%>

<html:html>
<head>
	<title>财务系统-会计科目-添加</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
	<script src="<%=path%>/js/common.js"></script>
	<script type="text/javascript">

function loads(){
	var actionflag = document.forms[0].elements["actionflag"].value;
	if(actionflag==1){
		document.forms[0].elements["name"].focus();
    	document.forms[0].elements["code"].readOnly=true;
    	var direction=document.forms[0].elements["direction"];
		for(var i=0;i<direction.length;i++){
				direction[i].disabled="true";
		}
    	document.forms[0].elements["sortcode"].disabled="true";
    	document.forms[0].elements["property"].disabled="true";
    	document.all.button1.style.display="none";
	}else{
		document.forms[0].elements["code"].focus();
    	document.all.button2.style.display="none";
	}
	
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}

function executeAjax(){
	var code = document.forms[0].elements["code"].value.trim();
	var actionflag=document.forms[0].elements["actionflag"].value;
	if(actionflag=="0"){
		if(code==""){
			return true;
		}else if(isNaN(code)){
	        	alert("请输入正确的科目代码！");
	        	document.forms[0].elements["code"].focus();
	        	return false;
	    }else{
			    var queryString = "subjectLoadmesageAAC.do?";
				queryString = queryString + "subjectcode="+document.forms[0].elements["code"].value.trim()+"&sortcodeflag="+document.forms[0].elements["sortcodeflag"].value+""; 
				findInfo(queryString);
	    }
	}
}
function display(code,name,dirc,sort,prop,flag,sortflag){
	document.forms[0].elements["code"].value=code;
	var temp_name=name;
	if(temp_name!=""){
		document.forms[0].elements["name"].value=name;
	    if(dirc=="0"){
	    	var temp=document.forms[0].elements["direction"];
	    	temp[0].checked="true";
	    }else{
	    	var temp=document.forms[0].elements["direction"];
	    	temp[1].checked="true";
	    }
		document.forms[0].elements["sortcode"].value=sort;
		document.forms[0].elements["property"].value=prop;
		document.forms[0].elements["actionflag"].value=flag;
		document.forms[0].elements["sortcodeflag"].value=sortflag;
	    document.forms[0].elements["sortcode"].disabled="true";
	    document.forms[0].elements["property"].disabled="true";
	    document.all.button2.style.display="none";
	    var direction=document.forms[0].elements["direction"];
		for(var i=0;i<direction.length;i++){
			direction[i].disabled="true";
		}
	}
}
function backErrors(message){
	alert(message);
	document.forms[0].elements["code"].value="";
	document.forms[0].elements["code"].focus();
}
function checkSaveData(){
      var code=document.forms[0].elements["code"].value.trim();
      var name=document.forms[0].elements["name"].value.trim();
      var sortcode=document.forms[0].elements["sortcode"].value.trim();
      var property=document.forms[0].elements["property"].value.trim();
      var direction="0";
   	  var id=document.getElementsByName("direction");
		for(var i=0;i<id.length;i++){
			if(id[i].checked){
				direction="1";
			}
		}
      if(code.length==0){
      	alert("请录入科目代码!!");
      	document.forms[0].elements["code"].focus();
		return false;
      }else if(name.length==0){
      	alert("请录入科目名称!!");
      	document.forms[0].elements["name"].focus();
		return false;
      }else if(direction==0){
      	alert("请录入余额方向!!");
		return false;
      }else if(sortcode.length==0){
      	alert("请录入科目类别!!");
      	document.forms[0].elements["sortcode"].focus();
		return false;
      }else if(property.length==0){
      	alert("请录入科目属性!!");
      	document.forms[0].elements["property"].focus();
		return false;
	  }else{
	  	document.getElementById("method").value="save";
	  }

}

function checkModifyData(){
    var name=document.forms[0].elements["name"].value.trim();
    if(name.length==0){
      	alert("请录入科目名称!!");
      	document.forms[0].elements["name"].focus();
		return false;
     }else{
     	document.getElementById("method").value="modify";
     }
}

function callback(){
	document.getElementById("method").value="back";
}

</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="return loads();">

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
							&nbsp;
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="216" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">账套管理&gt;会计科目</font>
									</td>
									<td width="47" class=font14>
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
				<html:form action="/subjectSave" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="130">
											<b class="font14">会 计 科 目</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="750">
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
							<html:hidden name="subjectShowAF" property="actionflag" />
							<html:hidden name="subjectShowAF" property="sortcodeflag" />
							<html:hidden name="subjectShowAF" property="id" />
							<td width="15%" class="td1">
								科目代码
								<font color="red">*</font>
							</td>
							<td width="35%">
								<html:text name="subjectShowAF" property="code"
									onblur="executeAjax();" onkeydown="enterNextFocus1();"
									maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="15%" class="td1">
								科目名称
								<font color="red">*</font>
							</td>
							<td width="35%">
								<html:text name="subjectShowAF" property="name" maxlength=""
									onkeydown="enterNextFocus1();" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								余额方向
								<font color="red">*</font>
							</td>
							<td width="35%" class="td7">
								<html:radio name="subjectShowAF" property="direction"
									onkeydown="enterNextFocus1();" value="0" />
								借
								<html:radio name="subjectShowAF" property="direction"
									onkeydown="enterNextFocus1();" value="1" />
								贷
							</td>
							<td width="15%" class="td1">
								科目类别
								<font color="red">*</font>
							</td>
							<td width="35%">
								<html:select name="subjectShowAF" property="sortcode"
									onkeydown="enterNextFocus1();" styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="sortcodeMap"
										name="subjectShowAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								科目属性
								<font color="red">*</font>
							</td>
							<td width="35%">
								<html:select name="subjectShowAF" property="property"
									onkeydown="enterNextFocus1();" styleClass="input4">
									<html:optionsCollection property="propertyMap"
										name="subjectShowAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="15%" class="td1"></td>
							<td width="35%" class="td7">
							</td>
						</tr>

					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<input type="hidden" name="method" id="method" value="" />
										<td width="70" id="button1">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkSaveData()">
												<bean:message key="button.sure" />
											</html:submit>
										</td>
										<td width="70" id="button2">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkModifyData()">
												<bean:message key="button.confirm" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return callback()">
												<bean:message key="button.back" />
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
