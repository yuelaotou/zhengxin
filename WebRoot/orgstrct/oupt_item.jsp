<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.List"%>
<%@ page import="org.xpup.hafmis.orgstrct.domain.enums.ValueTypeEnum"%>
<%@ page import="org.xpup.hafmis.orgstrct.domain.enums.PropertyTypeEnum"%>
<%
	List valueTypes = ValueTypeEnum.getEnumList();
	List types = PropertyTypeEnum.getEnumList();
	pageContext.setAttribute("types",types);
	pageContext.setAttribute("valueTypes",valueTypes);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>模板维护</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<script src="../js/common.js" type="text/javascript" language="javascript1.2"></script>
<script language="JavaScript1.2">
<!-- Begin
function reportErrors() {
  <logic:messagesPresent>
  var errors = "<html:messages id="error" ><bean:write name="error" />\n</html:messages>"
  alert(errors);
  </logic:messagesPresent>
}

function cancel() {
	document.location.href="show_oupt_items.do"
}

function init() {
	reportErrors();
	document.all("name").focus();
	display();
}

function validate(f) {
	if(!validateOuptItemAF(f))
	  return false;
	if('<bean:write name="ouptItemAF" property="activity" />'=='CREATE') {
		if(confirm("您是否要继续添加？")) {
			document.ouptItemAF.createAgain.value="true";
		}
		else {
			document.ouptItemAF.createAgain.value="false";
		}
	}
	return true;
}

document.onkeypress = function() {
	if (event.ctrlKey) {
		ouptItemAF.submit();
	}
	
	if(event.keyCode == 27) {
		cancel();
	}
}

function display() {
	if(document.all("valueType").value==5) {
		th1.style.display="block";
		td1.style.display="block";
		td2.style.display="none";
	}
	else {
		th1.style.display="none";
		td1.style.display="none";
		td2.style.display="block";
	}	
}

// End -->
</script>
</head>
<body onload="init()">
<html:form action="/create_oupt_item" onsubmit="return validate(this);">
  <table cellspacing="1" class="basicinfo">
    <thead>
      <tr>
        <th colspan="4">基本信息</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th width="25%"><label for="name">属性名称：</label><html:hidden property="ouptId" /> </th>
        <td width="25%"><html:text property="ouptItem.name" styleId="name" styleClass="text" onkeydown="enterToTab()" /></td>
        <th width="25%"><label for="innerName">内部名称：</label><html:hidden property="ouptId" /> </th>
        <td width="25%"><html:text property="ouptItem.innerName" styleId="innerName" styleClass="text" onkeydown="enterToTab()" /></td>
      </tr>
      <tr>
        <th width="25%"><label for="type">属性类型：</label></th>
        <td width="25%"><html:select property="type" styleId="type" styleClass="text" onkeydown="enterToTab()" >
        	<html:optionsCollection name="types" label="name" value="value"/>
          </html:select>
        </td>
        <td width="50%" colspan="2"></td>
      </tr>
      <tr>
        <th width="25%"><label for="valueType">值域类型：</label></th>
        <td width="25%"><html:select property="valueType" styleId="valueType" styleClass="text" onkeydown="enterToTab()" onchange="display()" >
        	<html:optionsCollection name="valueTypes" label="name" value="value"/>
          </html:select></td>
        <th id="th1" width="25%" style="display:none;"><label for="enumValue">枚举序列：</label></th>
        <td id="td1" width="25%" style="display:none;"><html:text property="ouptItem.enumValue" styleId="enumValue" styleClass="text" onkeydown="enterToTab()" /></td>
        <td id="td2" width="50%" style="display:block;" colspan="2"></td>
      </tr>
      <tr>
        <th width="25%"><label for="nullable">是否可空：</label></th>
        <td width="25%"><html:select property="ouptItem.nullable" styleId="nullable" styleClass="text" onkeydown="enterToTab()" >
        	<html:option value="true">可以为空</html:option>
        	<html:option value="false">不能为空</html:option>
          </html:select></td>
        <th width="25%"><label for="value">默认取值：</label></th>
        <td width="25%"><html:text property="ouptItem.value" styleId="value" styleClass="text" onkeydown="enterToTab()" /></td>
        
      </tr>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="4">
          <html:hidden property="activity" />
          <html:hidden property="ouptItem.id" />
          <html:hidden property="createAgain" />
          <html:submit styleClass="button" >确定</html:submit>
          <input type="button" value="取消" onclick="cancel()" class="button" />
        </td>
      </tr>
    </tfoot>
  </table>
</html:form>
</body>
</html>
<html:javascript formName="ouptItemAF" />