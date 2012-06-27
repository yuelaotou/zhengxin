<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>  
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ include file="./checkUrl.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
<title>东软住房公积金管理系统V7.0</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<frameset rows="20,27,*,22" cols="*" framespacing="0" frameborder="NO" border="0">
  <frame src="Top.jsp" name="topFrame" scrolling="NO" noresize>
  <frame src="mainMenu.jsp" name="mainMenuFrame" scrolling="NO" noresize >
  <frameset rows="*" cols="190,5,*" framespacing="0" frameborder="NO" border="0" ID="CLIENTFRAMESET">
		<frame src="Menu.jsp" name="leftFrame" scrolling="NO" noresize>
		<frame src="Banner.jsp" name="midFrame" scrolling="NO" noresize>
		<frame src="Blank.jsp" name="rightFrame" scrolling="AUTO" >
	</frameset>
  <frame src="Bottom.jsp" name="bottomFrame" scrolling="NO" noresize>
</frameset><noframes></noframes>

<body bgcolor="#CEE7F4" text="#000000">
<html:form action="toIndexAction">
</html:form>
</body>
</html>
