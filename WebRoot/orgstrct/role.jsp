<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色维护</title>
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
	document.location.href="show_roles.do"
}

function init() {
	reportErrors();
	document.all("name").focus();
}

function validate(f) {
	if(!validateHafRoleAF(f))
	  return false;
	if('<bean:write name="hafRoleAF" property="activity" />'=='CREATE') {
		if(confirm("您是否要继续添加？")) {
			document.hafRoleAF.createAgain.value="true";
		}
		else {
			document.hafRoleAF.createAgain.value="false";
		}
	}
	return true;
}

document.onkeypress = function() {
	if (event.ctrlKey) {
		hafRoleAF.submit();
	}
	
	if(event.keyCode == 27) {
		cancel();
	}
}

function openOrgTree() {
	//window.open('show_orgtree.do');
	var arr = new Array();
	window.showModalDialog("show_role_orgtree.do",arr,"dialogWidth=400px;dialogHeight=300px;status=no;scroll=no;");
	if(arr[0] != undefined) {
	    document.all("orgUnitName").value=arr[0];
	    document.all("orgUnitId").value=arr[1];
	}
}

// End -->
</script>
</head>
<body onload="init()">
<html:form action="/create_role" onsubmit="return validate(this);">
  <table cellspacing="1" class="basicinfo">
    <thead>
      <tr>
        <th colspan="4">角色信息</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th width="25%"><label for="name">角色名称：</label></th>
        <td width="25%"><html:text property="hafRole.name" styleId="name" styleClass="text" onkeydown="enterToTab()" /></td>
        <th width="25%"><label for="orgUnitName">所属部门：</label></th>
        <td width="25%" nowrap="nowrap">
          <html:text property="hafRole.organizationUnit.name" styleId="orgUnitName" readonly="true" style="width:80%" ondblclick="openOrgTree();" />
          <input type="button" value="..." style="width:26px" onclick="openOrgTree();" />
        </td>
      </tr>
      <tr>
        <th colspan="4"><label for="description">角色描述：</label></th>
      </tr>
      <tr>
        <td colspan="4"><html:textarea property="hafRole.description" styleId="description" styleClass="text" /></td>
      </tr>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="4">
          <input type="hidden" id="orgUnitId" name="orgUnitId" value="<bean:write name="hafRoleAF" property="hafRole.organizationUnit.id"/>" />
          <html:hidden property="hafRole.id"/>
          <html:hidden property="activity" />
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
<html:javascript formName="hafRoleAF" />