<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据权限设置</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<script src="../js/common.js" type="text/javascript" language="javascript1.2"></script>
<script language="javascript" type="text/javascript">
<!--
function reportErrors() {
  <logic:messagesPresent>
  var errors = "<html:messages id="error"><bean:write name="error"/>\n</html:messages>"
  alert(errors);
  </logic:messagesPresent>
}

function cancel() {
  document.location.href="show_employees.do";
}

function init() {
  reportErrors();
}

function enable(chk) {
  var t = chk.name.slice(-3);
  var a1 = "queryLevels" + t;
  var a2 = "operationLevels" + t;
  if(chk.checked) {
	document.all(a1).disabled=false;
	document.all(a2).disabled=false;
  }
  else {
    document.all(a1).disabled=true;
	document.all(a2).disabled=true;
  }
}
//-->
</script>
</head>
<body>
<html:form action="/assign_data_accesses_to_user">
<table class="basicinfo" cellspacing="1" >
  <thead>
    <tr>
      <th colspan="4">用户数据权限设置</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th width="25%">登录名称:</th>
      <td width="25%">
        <logic:notEmpty name="employee">
			<bean:write name="employee" property="username" />
		</logic:notEmpty></td>
      <th width="25%">用户姓名:</th>
      <td width="25%">
        <logic:notEmpty name="employee">
			<bean:write name="employee" property="realName" />
		</logic:notEmpty></td>
    </tr>
  </tbody>
</table>
<table cellspacing="1" class="basicinfo" >
  <thead>
    <tr>
      <th colspan="4">业务数据权限</th>
    </tr>
  </thead>
  <tr>
    <th width="4%"></th>
    <th width="40%">业务类型</th>
    <th width="28%">查询权限</th>
    <th width="28%">操作权限</th>
  </tr>
  <tbody>
    <bean:write name="dataAuthzAF" property="daRelationsHtml" filter="false" />
    <logic:empty name="dataAuthzAF" property="daRelations" >
    <tr>
      <td colspan="4">&nbsp;</td>
    </tr>
    </logic:empty>
  </tbody>
  <tfoot>
    <tr>
      <td colspan="4">
        <input type="submit" name="save" value="确定" class="button" />
        <input type="button" name="goBack" value="取消" class="button" onclick="cancel()" />
      </td>
    </tr>
  </tfoot>
</table>
</html:form>
</body>
</html>
