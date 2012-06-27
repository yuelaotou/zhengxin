<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="org.xpup.common.util.BSUtils" %>
<%@page import="org.xpup.security.wsf.bizsrvc.IMenuControlBS" %>
<%@page import="org.xpup.security.common.domain.MenuItem" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>

<%
	IMenuControlBS menuControlBS = (IMenuControlBS)BSUtils.getBusinessService("securityControlBS",session.getServletContext());
	String username = request.getRemoteUser();
	String parentId = request.getParameter("parentId");
	List systems = menuControlBS.findAllRootMenu(username);
	List menus = new ArrayList();
	String subSysName = "";
	if(parentId == null) {
	  if(systems.size() > 0)
		parentId = (String)((MenuItem)systems.get(0)).getId();
	}
	if(parentId != null) {
		menus = menuControlBS.findAllMenu(username, parentId);
		try {
		  MenuItem item = menuControlBS.findMenuItem(parentId);
		  subSysName = " - " + item.getCaption();
		} catch(Exception ex)
		{}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title>无标题文档</title>
<style type="text/css">
<!--
.STYLE1 {
	font-size: 24px;
	font-weight: bold;
}
-->
</style>
<script type="text/javascript">
function openSubMenu(id) {
  open("submenu.jsp?parentId="+id,"leftFrame");
}
function over1(t) {
  t.style.color="#ff0000";
}
function out1(t) {
  t.style.color="#000000";
}

function logoff() {
	top.document.location.href="../security/logoff.jsp";
}

function changeSystem() {
	top.document.location.href="../systems.jsp";
}
</script>
</head>

<body style="padding:0; margin:0">
<table width="100%" border="0" height="78px">
  <tr>
    <th style="font-size:28px; text-align:left; height:48px; color:#000000; font-family:"华文行楷"">&nbsp;住房公积金管理信息系统<small><%=subSysName %></small></th>
    <td width="130px" style="font-size:12px;text-align:right;padding:0; margin:0">登录账号：<%=username %></td>
  </tr>
  <tr style="background-color:#ffffff; text-align:left">
    <td colspan="2">&nbsp;
    <%if(systems.size()>1) { %>
    <label style="cursor:pointer; color:#000000" onmouseover="over1(this)" onmouseout="out1(this)" onclick="changeSystem();">系统切换</label> |
    <%} %>
    <label style="cursor:pointer; color:#000000" onmouseover="over1(this)" onmouseout="out1(this)">我的桌面</label> |
    <%
    	Iterator it = menus.iterator();
    	while(it.hasNext()) {
    	  MenuItem item = (MenuItem)it.next();
    	  StringBuffer a = new StringBuffer();
    	  a.append("<label style=\"cursor:pointer; color:#000000\" onmouseover=\"over1(this)\" onmouseout=\"out1(this)\" ");
    	  a.append("onclick=\"openSubMenu('" + item.getId() + "')\">");
    	  a.append(item.getCaption());
    	  a.append("</label> | ");
    	  out.println(a);
    	}
    %> 
    <label style="cursor:pointer; color:#000000" onmouseover="over1(this)" onmouseout="out1(this)">帮助</label> |
    <label style="cursor:pointer; color:#000000" onmouseover="over1(this)" onmouseout="out1(this)" onclick="logoff();">退出</label>
    </td>
  </tr>
</table>
</body>
</html>
