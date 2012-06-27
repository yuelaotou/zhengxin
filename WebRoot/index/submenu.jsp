<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="org.xpup.common.util.BSUtils" %>
<%@page import="org.xpup.security.wsf.bizsrvc.IMenuControlBS" %>
<%@page import="org.xpup.security.common.domain.MenuItem" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>

<%
	IMenuControlBS menuControlBS = (IMenuControlBS)BSUtils.getBusinessService("securityControlBS",session.getServletContext());
	String username = request.getRemoteUser();
	String parentId = request.getParameter("parentId");

	List items = menuControlBS.findAllMenu(username, parentId);
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title>无标题文档</title>
<script type="text/javascript">
function over1(t) {
  t.style.color="#ff0000";
}
function out1(t) {
  t.style.color="#000000";
}
function op(url) {
  if(url=="null") {
    alert("你没有为些菜单指定URL！")
  }
 else {
    open("../"+url,"workground");
  }
}
</script>
</head>

<body style="margin:2; padding:0px">
<table width="97%" style="margin:2px;padding:0px">
  <tr>
    <th style="font-size:16px; border-bottom-style:solid; border-bottom-width:1px; border-bottom-color:#000000; text-align:left">菜单区</th>
  </tr>
  <tr>
    <td>
    <%
      Iterator it = items.iterator();
      while(it.hasNext()) {
      	MenuItem item = (MenuItem)it.next();
      	StringBuffer a = new StringBuffer();
      	a.append("<li type=\"square\">");
      	a.append("<label style=\"cursor:pointer; color:#000000\" onmouseover=\"over1(this)\"  onmouseout=\"out1(this)\" ");
      	a.append("onclick=\"op(\'");
      	a.append(item.getUrl());
      	a.append("\')\">");
      	a.append(item.getCaption());
      	a.append("</label>");
      	out.println(a);
      }    
    %>
    </td>
  </tr>
</table>
</body>

</html>
