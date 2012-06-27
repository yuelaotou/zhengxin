<%@page contentType="text/html; charset=UTF-8"%>
<%
   String param = "";
   System.out.println(request.getParameter("system"));
   if(request.getParameter("system") != null)
     param = "?parentId=" + request.getParameter("system");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>住房公积金管理信息系统</title>
</head>

<frameset rows="78,*,22" cols="*" framespacing="1" frameborder="yes" border="1" bordercolor="#000000">
  <frame src="index/header.jsp<%=param %>" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" style="padding:0" />
  <frameset rows="*" cols="197,*" framespacing="1" frameborder="yes" border="1">
		  <!--<frame src="index/submenu.jsp" name="leftFrame"  scrolling="yes"   noresize="noresize" id="leftFrame" title="leftFrame"></frame>-->
		<frame src="index/Menu.jsp" name="leftFrame" scrolling="no" noresize/>
		<frame src="index/workground.html" name="workground" scrolling="yes" id="workground" title="workground" />
  </frameset>
  <frame src="index/footer.html" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" title="bottomFrame" />
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
