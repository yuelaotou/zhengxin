<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page import="org.xpup.hafmis.orgstrct.action.ShowHafRolesAC" %>
<bean:define id="pagination" name="<%=ShowHafRolesAC.PAGINATION_KEY%>" toScope="request" />

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
<html:form action="/find_roles">
  <table cellspacing="1" class="search">
    <thead>
      <tr>
        <th colspan="6">查询条件</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th width="7%"><label for="name">角色名称：</label></th>
        <td width="26%"><html:text property="name" styleId="name" styleClass="text" onkeydown="enterToTab()" />
        </td>
        <th width="7%"><label for="description">角色描述：</label></th>
        <td width="26%"><html:text property="description" styleId="description" styleClass="text" onkeydown="enterToTab()" /></td>
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
    <th>角色列表：</th>
  </tr>
</table>
<html:form action="/maintain_role">
  <table cellspacing="1" class="list">
    <thead>
      <tr>
        <th width="4%"></th>
        <th width="19%">
        	<jsp:include page="inc/order_by.jsp">
        		<jsp:param name="orderBy" value="hafRole.name"/>
        		<jsp:param name="column" value="角色名称"/>
        	</jsp:include>
        </th>
        <th width="46%">角色描述</th>
        <th width="31%">所属部门</th>
      </tr>
    </thead>
    <tbody>
      <logic:notEmpty name="roles">
      <logic:iterate id="role" name="roles" indexId="i" >
      <tr id="tr_n" onmouseover="onRowMouseOver(this)" onmouseout="onRowMouseOut(this, id<%=i%>)" onclick="onRowClick(this, id<%=i%>)" ondblclick="modify()">
        <td><input type="radio" id="id<%=i%>" name="id" value="<bean:write name="role" property="id"/>" <% if(new Integer(0).equals(i)) out.print("checked"); %> /></td>
        <td><bean:write name="role" property="name" /></td>
        <td><bean:write name="role" property="description" /></td>
        <td><bean:write name="role" property="organizationUnit.name" /> </td>
      </tr>
      </logic:iterate>
      </logic:notEmpty>
      <logic:empty name="roles">
      <tr>
        <td colspan="4">没有找到与条件相符合结果！</td>
      </tr>
      </logic:empty>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="4"><table class="pagination" cellspacing="0">
            <tr>
              <td >共 <bean:write name="pagination" property="nrOfElements"/> 项</td>
              <td style="text-align:right">
                    <jsp:include page="inc/pagination.jsp">
                		<jsp:param name="url" value="show_roles.do"/>
                	</jsp:include>
              </td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td colspan="4" style="text-align:right; height:26px;">
          <input type="hidden" name="method" value="preCreate" />
          <input type="button" name="preCreate" value="添加" class="button" onclick="onSubmit(this)" />
          <input type="button" name="preModify" value="修改" class="button" onclick="onSubmit(this)" <logic:empty name="roles">disabled="disabled"</logic:empty> />
          <input type="button" name="remove" value="删除" class="button" onclick="onSubmit(this)" <logic:empty name="roles">disabled="disabled"</logic:empty> />&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" name="preAssignMenuItems" value="分配菜单" class="button" style="width: 80px" onclick="onSubmit(this)" <logic:empty name="roles">disabled="disabled"</logic:empty> />
          <%-- 
          <input type="button" name="preAssignOperations" value="分配操作" class="button" style="width: 80px" onclick="onSubmit(this)" <logic:empty name="roles">disabled="disabled"</logic:empty> />
          <input type="button" name="preAssignDataAccesses" value="数据授权" class="button" style="width: 80px" onclick="onSubmit(this)" <logic:empty name="roles">disabled="disabled"</logic:empty> />
          --%>
        </td>
      </tr>
    </tfoot>
  </table>
</html:form>
</body>
</html>
