<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="java.util.List"%>
<%@ page import="org.xpup.hafmis.orgstrct.domain.enums.OUTypeEnum"%>
<%
	List ouTypes = OUTypeEnum.getEnumList();
	pageContext.setAttribute("ouTypes",ouTypes);
%>
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
  document.all("name").focus();
}

function cancel() {
  document.location.href="orgunit_editor_right.jsp";
}

function changeOupt() {
  var ouptId = document.all("ouptId").value;
  document.orgUnitAF.action="maintain_orgunit.do?method=changeOupt&ouptId="+ouptId;
  document.orgUnitAF.onsubmit=null;
  document.orgUnitAF.submit();
}
//-->
</script>
</head>
<body onload="init()">
  <br />
  <html:form action="/create_orgunit" onsubmit="return validateOrgUnitAF(this);">
    <html:hidden property="activity" />
    <html:hidden property="orgUnit.id" />
    <html:hidden property="parentId" />
    <table class="basicinfo" cellspacing="1">
      <thead>
      <tr>
        <th align="left" colspan="4">
          <logic:equal name="orgUnitAF" property="activity" value="CREATE">
            <logic:notEmpty name="orgUnitAF" property="parentId">
              添加下级机构
            </logic:notEmpty>
            <logic:empty name="orgUnitAF" property="parentId">
              添加顶级机构
            </logic:empty>
          </logic:equal>
          <logic:equal name="orgUnitAF" property="activity" value="MODIFY">
            编辑选定机构
          </logic:equal>
        </th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <th width="20%" nowrap="nowrap">
          <label for="name">组织机构名称：</label>
        </th>
        <td width="30%">
          <html:text property="orgUnit.name" styleId="name" styleClass="text" maxlength="20" onkeydown="enterToTab()" />
        </td>
        <th width="20%" nowrap="nowrap">
          <label for="position">组织机构位置：</label>
        </th>
        <td width="30%">
          <html:text property="orgUnit.position" styleId="position" maxlength="4" styleClass="text" onkeydown="enterToTab()" />
        </td>
      </tr>
      <tr>
         <th width="20%" nowrap="nowrap">
          <label for="type">组织机构类型：</label>
        </th>
        <td width="30%">
          <html:select property="type" styleId="type" styleClass="text" onkeydown="enterToTab()">
          	<html:optionsCollection name="ouTypes" label="name" value="value"/>
          </html:select>
        </td>
        <th width="20%" nowrap="nowrap">
          <label for="ouptId">组织机构属性模板：</label>
        </th>
        <td width="30%">
          <html:select property="orgUnit.ouptId" styleId="ouptId" styleClass="text" onkeydown="enterToTab()" onchange="changeOupt()" >
            <html:option value="">&nbsp;</html:option>
        	<html:optionsCollection name="oupts" label="name" value="id"/>
          </html:select>
        </td>
      </tr>
      <bean:write name="orgUnitAF" property="attributes" filter="false"/>
      <tr>
        <th colspan="4" nowrap="nowrap">
          <label for="description">组织机构描述：</label>
        </th>
      </tr>
      <tr>
        <td colspan="4" >
          <html:textarea property="orgUnit.description" styleId="description" styleClass="text" />
        </td>
      </tr>
      </tbody>
      <logic:notEmpty name="orgUnitAF" property="orgUnit.parameters">
      <thead>
      <tr>
      	<th colspan="4">参数设置</th>
      </tr>
      </thead>
      <tbody>
      	<bean:write name="orgUnitAF" property="parameters"  filter="false"/>
      </tbody>
      </logic:notEmpty>
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
  <html:javascript formName="orgUnitAF"/>
</body>
</html>
