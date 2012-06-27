<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色操作权限设置</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<script src="js/lj-sel.js" type=""></script>
<script language="JavaScript">
<!--
function reportErrors() {
  <logic:messagesPresent>
  var errors = "<html:messages id="error"><bean:write name="error"/>\n</html:messages>"
  alert(errors);
  </logic:messagesPresent>
}

function cancel() {
  document.location.href="show_roles.do";
}

function save() {
  var idString = "";
  var s = document.all("selected");
  for(i=0;i<s.options.length;i++) {
    idString += s.options[i].value + ","
  }
  idStringAF.idString.value=idString;
  idStringAF.submit();
}
//-->
</script>
</head>
<body>
<table cellspacing="1" class="basicinfo" >
  <thead>
    <tr>
      <th colspan="4">角色操作权限设置</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th width="25%">角色名称:</th>
      <td width="25%"><logic:notEmpty name="role">
          <bean:write name="role" property="name" />
        </logic:notEmpty></td>
      <td colspan="2"></td>
    </tr>
    <tr>
      <td colspan="4" style="text-align:center;"><table width="460px">
          <tr>
            <td width="200">可用操作</td>
            <td></td>
            <td>已选操作</td>
          </tr>
          <tr>
            <td><select name="available" size="10" class="text" multiple="multiple">
                <logic:iterate id="operation" name="available" indexId="index">
                  <option value="<bean:write name="operation" property="id"/>" ><bean:write name="operation" property="name"/> </option>
                </logic:iterate>
              </select>
            </td>
            <td width="60px" style="text-align:center;">
              <input name="AlltoRight" type="button" id="AlltoRight" value=" &gt;&gt; " onclick="allToSelected('available','selected')" />
              <br />
              <input name="toRight" type="button" id="toRight" value="  &gt;  " onclick="toSelected('available','selected')" />
              <br />
              <input name="toLeft" type="button" id="toLeft" value="  &lt;  " onclick="toAvailable('available','selected')" />
              <br />
              <input name="AlltoLeft" type="button" id="AlltoLeft" value=" &lt;&lt; " onclick="allToAvailable('available','selected')" />
            </td>
            <td width="200px"><select name="selected" size="10" class="text" multiple="multiple">               
                <logic:iterate id="operation" name="selected" indexId="index">
                  <option value="<bean:write name="operation" property="id"/>" ><bean:write name="operation" property="name"/> </option>
                </logic:iterate>
              </select>
            </td>
          </tr>
        </table></td>
    </tr>
  </tbody>
  <tfoot>
    <tr>
      <td colspan="4"><input type="button" name="save" value="确定" class="button" onclick="save()" />
        <input type="button" name="goBack" value="取消" class="button" onclick="cancel()" />
      </td>
    </tr>
  </tfoot>
</table>
<html:form action="/assign_operations_to_role">
  <input type="hidden" name="idString" value="" />
  <input type="hidden" name="itemId" value="<bean:write name="role" property="id" />" />
</html:form>
</body>
</html>
