<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>

<logic:notEmpty name="pagination">
<logic:equal name="pagination" property="firstPage" value="true">
［第一页|上一页］
</logic:equal>
<logic:notEqual name="pagination" property="firstPage" value="true">
［<a href="javascript:goToPage('first')">第一页</a>|<a href="javascript:goToPage('prev')">上一页</a>］
</logic:notEqual>
<bean:write name="pagination" property="page" />
/
<bean:write name="pagination" property="pageCount" />
<logic:equal name="pagination" property="lastPage" value="true">
［下一页|最后页］
</logic:equal>
<logic:notEqual name="pagination" property="lastPage" value="true">
［<a href="javascript:goToPage('next')">下一页</a>|<a href="javascript:goToPage('last')">最后页</a>］
</logic:notEqual>

<script>
function goToPage(toPage) {
	document.location="<%=request.getParameter("url")%>" + "?use=pagination&page=" + toPage;
}
function sort(by) {
	document.location="<%=request.getParameter("url")%>" + "?use=sort&orderBy=" + by;
}
</script>
</logic:notEmpty>
<logic:empty name="pagination">
	<font color="red">在request的作用域中没有找到key为pagination的对象！</font>
</logic:empty>