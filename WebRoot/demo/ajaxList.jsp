<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<script language="JavaScript">
//ajax
var xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
} 

function findInfo(fatherValue,url) {
 createXMLHttpRequest();  
	    xmlHttp.onreadystatechange = handleStateChange;
	    xmlHttp.open("GET", url, true);
	    xmlHttp.send(null);   
}

function handleStateChange() {
  if(xmlHttp.readyState == 4) {
      if(xmlHttp.status == 200) {
        var xmlDoc = xmlHttp.responseXML;
		var values = xmlDoc.getElementsByTagName("value");
		var texts  = xmlDoc.getElementsByTagName("text");

		var selectObj = document.getElementById("childNode");
		selectObj.length = 0;
		for ( i=0; i < values.length; i++ ) {
			var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
			selectObj.options[selectObj.length++] = childOption;
		}
      }
      }
   }

function getChildOption() {
	var fatherObj = document.getElementById("fatherNode");
	var fatherValue = fatherObj.options[fatherObj.selectedIndex].value;
	
	var url = "childXmlAAC.do?fatherValue="+fatherValue;
	findInfo(fatherValue, url);
}

function getChildValue(){
  var selectObj = document.getElementById("childNode");
  document.demoImportAF.elements["url"].value=selectObj.options[selectObj.selectedIndex].value;
 // alert(selectObj.options[selectObj.selectedIndex].value);
}
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

   <head>
    <title>My JSP 'ajaxList.jsp' starting page</title>
  </head>
<html:html>
 <body> 
  <html:form action="/getAjaxListValueAC.do">
<select id="fatherNode" onChange="getChildOption()">
	<option value="0">选择</option>
	<option value="1">性别</option>
	<option value="2">婚姻状况</option>
</select>
<select id="childNode" onChange="getChildValue()">
</select>
<html:submit property="method">提交</html:submit>
<html:text property="url"/>
</html:form>

 </body>
 </html:html>