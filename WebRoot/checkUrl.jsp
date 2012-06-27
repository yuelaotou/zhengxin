<%@page import="org.xpup.hafmis.orgstrct.dto.SecurityInfo" %>
<%
   	SecurityInfo securityInfo = (SecurityInfo)session.getAttribute("SecurityInfo");
    if(securityInfo==null){
    String path=request.getContextPath();%>
    <script language="JavaScript">
      parent.window.location="<%=path%>/login.jsp";
    </script>
    <% 
    }
 %>