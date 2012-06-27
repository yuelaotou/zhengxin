<%@ page contentType="text/html; charset=GBK" %>
<%@page import="org.xpup.security.wsf.bizsrvc.IMenuControlBS" %>
<%@page import="org.xpup.common.util.BSUtils" %>
<%@page import="org.xpup.hafmis.orgstrct.dto.SecurityInfo" %>
<%
     IMenuControlBS menuControlBS = (IMenuControlBS)BSUtils.getBusinessService("securityControlBS",session.getServletContext());
	SecurityInfo securityInfo = (SecurityInfo)session.getAttribute("SecurityInfo");
	if(securityInfo==null){
   	String path=request.getContextPath();%>
    <script language="JavaScript">
      parent.window.location="<%=path%>/login.jsp";
    </script>
   	<% }
	String username = securityInfo.getUserInfo().getUsername();
String sParentID,sHasChild,sTmpID,sSelfURL,strApplication,strUser;
sParentID	= request.getParameter("nParentID");
sSelfURL	= request.getRequestURI();
sSelfURL	= sSelfURL + "?nParentID=";
if (sParentID == null || sParentID.length() == 0 || sParentID.equals("null")) sParentID = "SA1000";
String node = menuControlBS.getMenu(username, sParentID,sSelfURL);  
out.print(node);
   
%>
