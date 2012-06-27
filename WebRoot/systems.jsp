<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>  
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@page import="org.xpup.common.util.BSUtils" %>
<%@page import="org.xpup.security.wsf.bizsrvc.IMenuControlBS" %>
<%@page import="org.xpup.security.common.domain.MenuItem" %>
<%@page import="java.util.List" %>
<%@page import="org.xpup.hafmis.orgstrct.dto.SecurityInfo" %>
<%
	IMenuControlBS menuControlBS = (IMenuControlBS)BSUtils.getBusinessService("securityControlBS",session.getServletContext());
	HttpSession se = request.getSession(false);
    String username="";
   	SecurityInfo securityInfo = (SecurityInfo)se.getAttribute("SecurityInfo");
	username= securityInfo.getUserInfo().getUsername();
	List systems = menuControlBS.findAllRootMenu(username);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统列表</title>
<script type="">
function toSystem(id) {
	loginActionForm.systemId.value=id;
    loginActionForm.submit();
}
</script>
</head>

<body style="padding-top:100px; text-align:center" bgcolor="#CEE7F4" text="#000000">
<html:form action="/changSystemToIndexAction" >
<table width="360px" border="0" style="text-align:center">
  <tr>
    <th style="font-size:24px;">请选择要登录的子系统<hr /></th>
  </tr>
  <tr>
    <td><table width="100%" border="0">
      <%for(int i=0;i<systems.size();i++) {%>
      <tr>
        <td width="22%" style="text-align:left">&nbsp;</td>
        <td width="78%" style="text-align:left;font-size:24px;"><li type="square"><a href="javascript:toSystem('<%=((MenuItem)systems.get(i)).getId() %>')"><%=((MenuItem)systems.get(i)).getCaption()%></a></li></td>
      </tr>
      <%} %>
    </table></td>
  </tr>
</table>
   <html:hidden  property="systemId"/>
   <html:hidden  property="userName"/>
</html:form>
</body>
</html:html>
