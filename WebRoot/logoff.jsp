<%@ page language="java"%>
<%@ page import = "java.util.*"%>
<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>  
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<html:html xhtml="true" locale="true">
<link href="<%=request.getContextPath()%>/css/index.css" type="text/css" rel="stylesheet"/>
<head>
<title>
重新登录
</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<%
HttpSession hsession=request.getSession(false);
hsession.setAttribute("SecurityInfo",null);
hsession.setAttribute("systemId",null);
			hsession.invalidate() ;
%>
<body>
<script language="JavaScript">
parent.window.location="login.jsp";
</script>

</body>
</html:html>