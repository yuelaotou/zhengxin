<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>操作维护</title>
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
	document.location.href="bt_show_data_accesses.do"
}

function init() {
	reportErrors();
	document.all("name").focus();
}

function validate(f) {
	if(!validateDataAccessAF(f))
	  return false;
	if('<bean:write name="dataAccessAF" property="activity" />'=='CREATE') {
		if(confirm("您是否要继续添加？")) {
			document.dataAccessAF.createAgain.value="true";
		}
		else {
			document.dataAccessAF.createAgain.value="false";
		}
	}
	return true;
}

document.onkeypress = function() {
	if (event.ctrlKey) {
		dataAccessAF.submit();
	}
	
	if(event.keyCode == 27) {
		cancel();
	}
}

// End -->
</script>
</head>
<body onload="init()">
<html:form action="/bt_create_data_access" onsubmit="return validate(this);">
  <table cellspacing="1" class="basicinfo">
    <thead>
      <tr>
        <th colspan="4">基本信息</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th width="25%"><label for="name">数据资源名称：</label></th>
        <td width="25%"><html:text property="dataAccess.name" styleId="name" styleClass="text" onkeydown="enterToTab()" maxlength="50" /></td>
        <th width="25%"><label for="innerName">内部名称：</label></th>
        <td width="25%"><html:text property="dataAccess.innerName" styleId="innerName" styleClass="text" onkeydown="enterToTab()" maxlength="50" /></td>
      </tr>
      <tr>
        <th><label for="queryLevelEnum">查询权限枚举：</label></th>
        <td ><html:text property="dataAccess.queryLevelEnum" styleId="queryLevelEnum" styleClass="text" onkeydown="enterToTab()" maxlength="100" /></td>
		<th><label for="queryValueOrder">查询枚举值排序：</label></th>
        <td ><html:text property="dataAccess.queryValueOrder" styleId="queryValueOrder" styleClass="text" onkeydown="enterToTab()" maxlength="100" /></td>
	  </tr>
	  <tr>
        <th><label for="queryDefauldLevel">查询权限默认值：</label></th>
        <td ><html:text property="dataAccess.queryDefauldLevel" styleId="queryDefauldLevel" styleClass="text" onkeydown="enterToTab()" maxlength="50" /></td>
		<td colspan="2"></td>
	  </tr>
	  <tr>
        <th><label for="operationLevelEnum">操作权限枚举：</label></th>
        <td ><html:text property="dataAccess.operationLevelEnum" styleId="operationLevelEnum" styleClass="text" onkeydown="enterToTab()" maxlength="100" /></td>
		<th><label for="operationValueOder">操作枚举值排序：</label></th>
        <td ><html:text property="dataAccess.operationValueOder" styleId="operationValueOder" styleClass="text" onkeydown="enterToTab()" maxlength="100" /></td>
	  </tr>
	  <tr>
        <th><label for="operationDefaultLevel">操作权限默认值：</label></th>
        <td ><html:text property="dataAccess.operationDefaultLevel" styleId="operationDefaultLevel" styleClass="text" onkeydown="enterToTab()" maxlength="50" /></td>
		<td colspan="2"></td>
	  </tr>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="4">
          <html:hidden property="activity" />
          <html:hidden property="dataAccess.id" />
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
<html:javascript formName="dataAccessAF" />