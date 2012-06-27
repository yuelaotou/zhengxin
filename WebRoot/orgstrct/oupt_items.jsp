<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page import="org.xpup.hafmis.orgstrct.action.ShowOuptItemsAC" %>
<bean:define id="pagination" name="<%=ShowOuptItemsAC.PAGINATION_KEY%>" toScope="request" />
<%@ page import="java.util.List"%>
<%@ page import="org.xpup.hafmis.orgstrct.domain.enums.ValueTypeEnum"%>
<%@ page import="org.xpup.hafmis.orgstrct.domain.enums.PropertyTypeEnum"%>
<%
	List valueTypes = ValueTypeEnum.getEnumList();
	List types = PropertyTypeEnum.getEnumList();
	pageContext.setAttribute("types",types);
	pageContext.setAttribute("valueTypes",valueTypes);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>模板项维护</title>
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

function goBack1() {
	document.location.href="show_oupts.do"
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
<html:form action="/find_oupt_items">
  <table cellspacing="1" class="search">
    <thead>
      <tr>
        <th colspan="6">查询条件</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th width="7%"><label for="ouptName">模板名称：</label><html:hidden property="ouptId" /></th>
        <td width="26%"><html:text property="ouptName" styleId="ouptName" styleClass="text" onkeydown="enterToTab()" readonly="true" /></td>
        <th width="7%"><label for="ouptDescription">模板描述：</label></th>
        <td width="26%"><html:text property="ouptDescription" styleId="ouptDescription" styleClass="text" onkeydown="enterToTab()"  readonly="true" /></td>
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
        <th><label for="name">属性名称：</label></th>
        <td><html:text property="name" styleId="name" styleClass="text" onkeydown="enterToTab()" /></td>
        <th><label for="type">属性类型：</label></th>
        <td><html:select property="type" styleId="type" styleClass="text" onkeydown="enterToTab()" >
        	<html:option value="-1">&nbsp;</html:option>
        	<html:optionsCollection name="types" label="name" value="value"/>
          </html:select>        
        </td>
        <th><label for="nullable">是否可空：</label></th>
        <td><html:select property="nullable" styleId="nullable" styleClass="text" onkeydown="enterToTab()" >
        	<html:option value="-1">&nbsp;</html:option>
        	<html:option value="1">可空</html:option>
        	<html:option value="0">必填</html:option>
          </html:select></td>
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
    <th>模板属性参数明细：</th>
  </tr>
</table>
<html:form action="/maintain_oupt_item">
  <table cellspacing="1" class="list">
    <thead>
      <tr>
        <th width="4%"></th>
        <th width="16%">
        	<jsp:include page="inc/order_by.jsp">
        		<jsp:param name="orderBy" value="ouptItem.name"/>
        		<jsp:param name="column" value="属性名称"/>
        	</jsp:include>
        </th>
        <th width="16%">
        	<jsp:include page="inc/order_by.jsp">
        		<jsp:param name="orderBy" value="ouptItem.innerName"/>
        		<jsp:param name="column" value="内部名称"/>
        	</jsp:include>
        </th>
        <th width="16%">
        	<jsp:include page="inc/order_by.jsp">
        		<jsp:param name="orderBy" value="ouptItem.type"/>
        		<jsp:param name="column" value="属性类型"/>
        	</jsp:include>
        </th>
        <th width="16%">
        	<jsp:include page="inc/order_by.jsp">
        		<jsp:param name="orderBy" value="ouptItem.valueType"/>
        		<jsp:param name="column" value="属性取值类型"/>
        	</jsp:include>
        </th>
        <th width="16%">
        	<jsp:include page="inc/order_by.jsp">
        		<jsp:param name="orderBy" value="ouptItem.nullable"/>
        		<jsp:param name="column" value="是否可以为空"/>
        	</jsp:include>
        </th>
        <th width="16%">默认取值</th>
      </tr>
    </thead>
    <tbody>
      <logic:notEmpty name="ouptItems">
      <logic:iterate id="ouptItem" name="ouptItems" indexId="i" >
      <tr id="tr_n" onmouseover="onRowMouseOver(this)" onmouseout="onRowMouseOut(this, id<%=i%>)" onclick="onRowClick(this, id<%=i%>)" ondblclick="modify()">
        <td><input type="radio" id="id<%=i%>" name="id" value="<bean:write name="ouptItem" property="id"/>" <% if(new Integer(0).equals(i)) out.print("checked"); %> /></td>
        <td><bean:write name="ouptItem" property="name" /></td>
        <td><bean:write name="ouptItem" property="innerName" /></td>
        <td><bean:write name="ouptItem" property="type.name" /></td>
        <td><bean:write name="ouptItem" property="valueType.name" /></td>
        <td><bean:write name="ouptItem" property="nullable" /></td>
        <td><bean:write name="ouptItem" property="value" /></td>
      </tr>
      </logic:iterate>
      </logic:notEmpty>
      <logic:empty name="ouptItems">
      <tr>
        <td colspan="7">没有找到与条件相符合结果！</td>
      </tr>
      </logic:empty>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="7"><table class="pagination" cellspacing="0">
            <tr>
              <td >共 <bean:write name="pagination" property="nrOfElements"/> 项</td>
              <td style="text-align:right">
                    <jsp:include page="inc/pagination.jsp">
                		<jsp:param name="url" value="show_oupt_items.do"/>
                	</jsp:include>
              </td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td colspan="7" style="text-align:right; height:26px;">
          <input type="hidden" name="method" value="preCreate" />
          <input type="button" name="preCreate" value="添加" class="button" onclick="onSubmit(this)" />
          <input type="button" id="preModify" name="preModify" value="修改" class="button" onclick="onSubmit(this)" <logic:empty name="ouptItems">disabled="disabled"</logic:empty> />
          <input type="button" name="remove" value="删除" class="button" onclick="onSubmit(this)" <logic:empty name="ouptItems">disabled="disabled"</logic:empty> />
          <input type="button" name="goBack" value="返回" class="button" onclick="goBack1()" />
        </td>
      </tr>
    </tfoot>
  </table>
</html:form>
</body>
</html>
