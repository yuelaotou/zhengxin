<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<html>
<head>
<title>
  menuitem
</title>
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

function init() {
  reportErrors();
  document.all("caption").focus();
}

function cancel() {
  document.location.href="menu_editor_right.jsp";
}
//-->
</script>
</head>
<body onload="init()">
  <br />
  <html:form action="/bt_create_menuitem" onsubmit="return validateMenuItemAF(this);">
    <html:hidden property="activity" />
    <html:hidden property="menuItem.id" />
    <html:hidden property="parentId" />
    <table class="basicinfo" cellspacing="1">
      <thead>
      <tr>
        <th align="left" colspan="4">
          <logic:equal name="menuItemAF" property="activity" value="CREATE">
            <logic:notEmpty name="menuItemAF" property="parentId">
              添加子菜单
            </logic:notEmpty>
            <logic:empty name="menuItemAF" property="parentId">
              添加根菜单
            </logic:empty>
          </logic:equal>
          <logic:equal name="menuItemAF" property="activity" value="MODIFY">
            编辑菜单项
          </logic:equal>
        </th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <th width="20%" nowrap="nowrap">
          菜单标题：
        </th>
        <td width="30%">
          <html:text property="menuItem.caption" styleId="caption" styleClass="text" maxlength="20" onkeydown="enterToTab()" />
        </td>
        <th width="20%" nowrap="nowrap">
          菜单URL：
        </th>
        <td width="30%">
          <html:text property="menuItem.url" styleClass="text" maxlength="100" onkeydown="enterToTab()" />
        </td>
      </tr>
      <tr>
        <th nowrap="nowrap">
          URL目标：
        </th>
        <td >
          <html:select property="menuItem.target" styleClass="text" onkeydown="enterToTab()" >
            <html:option value=""></html:option>
            <html:option value="workaround">workaround</html:option>
            <html:option value="_self">_self</html:option>
            <html:option value="_parent">_parent</html:option>
            <html:option value="_top">_top</html:option>
            <html:option value="_blank">_blank</html:option>
          </html:select>
        </td>
        <th nowrap="nowrap">
          菜单位置：
        </th>
        <td >
          <html:text property="menuItem.position" maxlength="4" styleClass="text" onkeydown="enterToTab()" />
        </td>
      </tr>
      <tr>
      <th nowrap="nowrap">
          系统类型：
        </th>
        <td>
        <html:select property="menuItem.opSystemType">
          <html:option value="0">住房公积金系统管理子系统</html:option>
          <html:option value="1">住房公积金归集子系统</html:option>
          <html:option value="2">住房公积金贷款子系统</html:option>
          <html:option value="3">住房公积金财务子系统</html:option>
        </html:select>
        </td>
        <th colspan="2" nowrap="nowrap"></th>
      </tr>
      <tr>
        <th colspan="4" nowrap="nowrap">
          菜单描述：
        </th>
      </tr>
      <tr>
        <td colspan="4" >
          <html:textarea property="menuItem.description" styleClass="text" />
        </td>
      </tr>
      </tbody>
      <tfoot>
      <tr>
        <td colspan="4" >
          <html:submit styleClass="button">
            保存
          </html:submit>
          <input type="button" value="取消" class="button" onclick="cancel()" />
        </td>
      </tr>
      </tfoot>
      </table>
  </html:form>
  <html:javascript formName="menuItemAF"/>
</body>
</html>
