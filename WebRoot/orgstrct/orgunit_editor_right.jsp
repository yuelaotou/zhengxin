<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>
</title>
<script language="javascript" type="text/javascript">
<!--
function init() {
  reportErrors()
  <logic:present name="script">
    <bean:write name="script" filter="false"/>
  </logic:present>
}

function removeMenuItem(id) {
  parent.menuTree.removeMenuItem(id);
}
function addRootMenuItem(id,caption) {
  parent.menuTree.addRootMenuItem(id,caption);
}
function addSubMenuItem(parentId,id,caption) {
  parent.menuTree.addSubMenuItem(parentId,id,caption);
}
function editMenuItem(oldId,id,caption) {
  parent.menuTree.editMenuItem(oldId,id,caption);
}

function reportErrors() {
  <logic:messagesPresent>
  var errors = "<html:messages id="error"><bean:write name="error"/>\n</html:messages>"
  alert(errors);
  </logic:messagesPresent>
}
//-->
</script>
</head>
<body onload="init()">
  <br />
  &nbsp;&nbsp;&nbsp;&nbsp;提示：您可以用鼠标右击右边组织机构树区域，使用浮动菜单编辑组织机构树！
</body>
</html>
