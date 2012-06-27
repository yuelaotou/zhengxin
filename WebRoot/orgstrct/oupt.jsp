<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>

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
	document.location.href="show_oupts.do"
}

function init() {
	reportErrors();
	document.all("name").focus();
}

function validate(f) {
	if(!validateOuptAF(f))
	  return false;
	if('<bean:write name="ouptAF" property="activity" />'=='CREATE') {
		if(confirm("您是否要继续添加？")) {
			document.ouptAF.createAgain.value="true";
		}
		else {
			document.ouptAF.createAgain.value="false";
		}
	}
	return true;
}

document.onkeypress = function() {
	if (event.ctrlKey) {
		ouptAF.submit();
	}
	
	if(event.keyCode == 27) {
		cancel();
	}
}

// End -->
</script>
</head>
<body onload="init()">
<html:form action="/create_oupt" onsubmit="return validate(this);">
  <table cellspacing="1" class="basicinfo">
    <thead>
      <tr>
        <th colspan="4">基本信息</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th width="25%"><label for="name">模板名称：</label></th>
        <td width="25%"><html:text property="oupt.name" styleId="name" styleClass="text" onkeydown="enterToTab()" /></td>
        <th width="25%"><label for="innerName">内部名称：</label></th>
        <td width="25%"><html:text property="oupt.innerName" styleId="innerName" styleClass="text" onkeydown="enterToTab()" /></td>
      </tr>
      <tr>
        <th colspan="4"><label for="description">模板描述：</label></th>
      </tr>
      <tr>
        <td colspan="4"><html:textarea property="oupt.description" styleId="description" styleClass="text" /></td>
      </tr>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="4">
          <html:hidden property="activity" />
          <html:hidden property="oupt.id" />
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
<html:javascript formName="ouptAF" />