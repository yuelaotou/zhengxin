<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page import="org.xpup.security.buildtime.action.ShowOperationsAC" %>
<bean:define id="pagination" name="<%=ShowOperationsAC.PAGINATION_KEY%>" toScope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>操作权限维护</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<script src="../js/common.js" type="text/javascript" language="javascript1.2"></script>
<script language="JavaScript1.2">
<!-- Begin
function reportErrors() {
  <logic:messagesPresent>
  var errors = "<html:messages id="error"><bean:write name="error"/>\n</html:messages>"
  alert(errors);
  </logic:messagesPresent>
}

function onSubmit(btn) {
	document.all["method"].value=btn.name;
  	if(btn.name=="remove") {
    	if(!confirm("删除后将无法恢复，您真的要删除吗？")) {
      		return;
    	}
  	}
  	idAF.submit();
}

function modify() {
	document.all("preModify").click();
}

function init() {
	reportErrors();
	document.all("name").focus();
}
// End -->
</script>
</head>
<body onload="init()">
<html:form action="/bt_find_operations">
  <table cellspacing="1" class="search">
    <thead>
      <tr>
        <th colspan="6">查询条件</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th width="7%"><label for="name">操作名称：</label></th>
        <td width="26%"><html:text property="name" styleId="name" styleClass="text" onkeydown="enterToTab()" />
        </td>
        <th width="7%"><label for="innerName">内部名称：</label></th>
        <td width="26%"><html:text property="innerName" styleId="innerName" styleClass="text" onkeydown="enterToTab()" /></td>
        <th width="7%"><label for="pageSize">每页显示：</label></th>
        <td width="27%"><html:select property="pageSize" styleId="pageSize" styleClass="text" onkeydown="enterToTab()" >
        	<html:option value="05">05条</html:option>
        	<html:option value="10">10条</html:option>
        	<html:option value="15">15条</html:option>
        	<html:option value="30">30条</html:option>
        	<html:option value="100">100条</html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <th><label for="group">操作分组：</label></th>
        <td><html:text property="group" styleId="group" styleClass="text" onkeydown="enterToTab()" /></td>
        <th><label for="description">操作描述：</label></th>
        <td><html:text property="description" styleId="description" styleClass="text" /></td>
        <td colspan="2">&nbsp;</td>
      </tr>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="6"><html:submit styleClass="button">查询</html:submit>
        </td>
      </tr>
    </tfoot>
  </table>
</html:form>
<br/>
<table style="text-align:left; width:98%; font-size:16px">
  <tr>
    <th>操作权限列表：</th>
  </tr>
</table>
<html:form action="/bt_maintain_operation">
  <table cellspacing="1" class="list">
    <thead>
      <tr>
        <th width="4%"></th>
        <th width="20%">
        	<jsp:include page="inc/order_by.jsp">
        		<jsp:param name="orderBy" value="operation.name"/>
        		<jsp:param name="column" value="操作名称"/>
        	</jsp:include>
        </th>
        <th width="20%">
          	<jsp:include page="inc/order_by.jsp">
        		<jsp:param name="orderBy" value="operation.innerName"/>
        		<jsp:param name="column" value="内部名称"/>
        	</jsp:include>
        </th>
        <th width="10%">
        	<jsp:include page="inc/order_by.jsp">
        		<jsp:param name="orderBy" value="operation.group"/>
        		<jsp:param name="column" value="操作分组"/>
        	</jsp:include>
        </th>
        <th width="46%">操作描述</th>
      </tr>
    </thead>
    <tbody>
      <logic:notEmpty name="operations">
      <logic:iterate id="operation" name="operations" indexId="i" >
      <tr id="tr_n" onmouseover="onRowMouseOver(this)" onmouseout="onRowMouseOut(this, id<%=i%>)" onclick="onRowClick(this, id<%=i%>)" ondblclick="modify()">
        <td><input type="radio" id="id<%=i%>" name="id" value="<bean:write name="operation" property="id"/>" <% if(new Integer(0).equals(i)) out.print("checked"); %> /></td>
        <td><bean:write name="operation" property="name" /></td>
        <td><bean:write name="operation" property="innerName" /></td>
        <td><bean:write name="operation" property="group" /></td>
        <td><bean:write name="operation" property="description" /></td>
      </tr>
      </logic:iterate>
      </logic:notEmpty>
      <logic:empty name="operations">
      <tr>
        <td colspan="5">没有找到与条件相符合结果！</td>
      </tr>
      </logic:empty>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="5"><table class="pagination" cellspacing="0">
            <tr>
              <td >共 <bean:write name="pagination" property="nrOfElements"/> 项</td>
              <td style="text-align:right">
                    <jsp:include page="inc/pagination.jsp">
                		<jsp:param name="url" value="bt_show_operations.do"/>
                	</jsp:include>
              </td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td colspan="5" style="text-align:right; height:26px;">
          <input type="hidden" name="method" value="preCreate" />
          <input type="button" name="preCreate" value="添加" class="button" onclick="onSubmit(this)" />
          <input type="button" id="preModify" name="preModify" value="修改" class="button" onclick="onSubmit(this)" <logic:empty name="operations">disabled="disabled"</logic:empty> />
          <input type="button" name="remove" value="删除" class="button" onclick="onSubmit(this)" <logic:empty name="operations">disabled="disabled"</logic:empty> />
        </td>
      </tr>
    </tfoot>
  </table>
</html:form>
</body>
</html>
