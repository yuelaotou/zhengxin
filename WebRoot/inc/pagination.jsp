<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<logic:notEmpty name="pagination">
<body>
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
跳转到第<input type=text name="changpage" maxlength="10" onchange="return changvalue(this.value)" onkeydown="enterNextFocus1();" size="2" style= "border-color: black #000000 black black; height: 18px; background-color: #BBE6F2; font-size: 12px; border-style: solid; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 1px; border-left-width: 0px" >
页 <input type=hidden name="changvale">
<logic:equal name="pagination" property="pageCount" value="1">
GO
</logic:equal>
<logic:notEqual name="pagination" property="pageCount" value="1">
<a href="#" onclick="return changpageinfo();"  style="text-decoration: none" >GO</a>
</logic:notEqual>
<input type="hidden" name="rowArrayHid" value=""/>
</body>
<script>
function goToPage(toPage) {
	var rowArray=document.all.rowArrayHid.value.trim();
	if(rowArray.indexOf(",")!=-1){
		rowArray=rowArray.substring(0,rowArray.lastIndexOf("-"));
	}
	document.location="<%=request.getParameter("url")%>" + "?use=pagination&page=" + toPage + "&rowArray=" + rowArray;
}
function sort(by) {
	var rowArray=document.all.rowArrayHid.value.trim();
	if(rowArray.indexOf(",")!=-1){
		rowArray=rowArray.substring(0,rowArray.lastIndexOf("-"));
	}
	document.location="<%=request.getParameter("url")%>" + "?use=sort&orderBy=" + by + "&rowArray=" + rowArray;
}
function changpageinfo(){
    var temppage=document.all.changvale.value.trim();
    if(temppage==''){
    	alert("请输入你要跳转的页码！");
    	return false;
    }
    if(!isNumberPage(temppage)){
    	return false;
    }
    goToPage(temppage);
}
function changvalue(changpage){
    isNumberPage(changpage);
    document.all.changvale.value=changpage;
}
//校验数字
function isNumberPage(String){ 
    var Letters = "1234567890"; //可以自己增加可输入值
    var i;
    var c;
    for( i = 0; i < String.length; i ++ ){
	    c = String.charAt( i );
	   	if (Letters.indexOf( c ) < 0){
	   	    alert("请正确输入跳转页码，例如：1 或 2 等数字！");
	    	return false;
	    }else{
	    	return true;
	    }
	}
}
</script>
</logic:notEmpty>
<logic:empty name="pagination">
	<font color="red">在request的作用域中没有找到key为pagination的对象！</font>
</logic:empty>