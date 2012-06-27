<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>	
<%
   String path=request.getContextPath();
%>

<html:html>
<head>
<script src="<%=path%>/js/common.js"></script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
<form name="mform">
<input type="text" name="text1">
<input type="button" value="弹出" onclick="gotoOrgpop();">

</form>

</body>
</html:html>
<script>
function gotoOrgpop(status){

	window.open("orgpopTaFindAC.do?status="+status,"window","height=450,width=700,top=300,left=300,scrollbars=yes,location=yes, status=yes"); 

}

function executeAjax(){
}

</script>