<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%
   String path = request.getContextPath();
  // int i=0;
 %>
<html:html>
<head>
<title>公式生成</title>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script language="javascript" src="<%=path%>/sysfinance/reportmng/financereport/js/fncommon.js"></script>
</head>
<script>
function findInfo1(url) {
	createXMLHttpRequest();  
	xmlHttp.onreadystatechange = handleStateChange1;
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);   
}
var k=0;
var m=0;
function handleStateChange1() {
  if(xmlHttp.readyState == 4){
    if(xmlHttp.status == 200){
      if(k==m||k<m){
   		var xmlDoc = xmlHttp.responseXML;
		var values = xmlDoc.getElementsByTagName("value");
		var texts  = xmlDoc.getElementsByTagName("text");
		var selectObj = document.getElementById("childNode"+k);
		k++;
		selectObj.length = 0;
		for ( i=0; i < values.length; i++ ) {
			var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
			selectObj.options[selectObj.length++] = childOption;
		}
      }
    }
  }
}
function loadMessage(j){
if(j!=0){
	m=j;
}
	var queryString = "<%=path%>/sysfinance/loadMessageAAC.do";
	findInfo1(queryString);
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loadMessage(0);" >

<form>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
  <b>
   <select id="childNode0" name="select0" style="width=100%">
   </select>
  </b>
</td>
 <td width="100" >
	<input name="num0" type="text" id="num0" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal0" type="text"  id="cal0" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22">
   <input     type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px "  value="" onclick="insert_row(0)">
  </td>
 <td width="22"> <input  type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(0)"> </td>
 </tr>
 </table>

  <DIV id=dis_div1 style="DISPLAY: none">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
 <b>
 <select id="childNode1" name="select1" style="width=100%">
      </select>
     </b>
　</td>
 <td width="100" >
  <input name="num1" type="text" id="num1" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal1" type="text"  id="cal1" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22">
   <input    type="button" style="buttona1" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " value="" onclick="insert_row(1)">
  </td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " value="" onclick="insert_row2(1)"> </td>
 </tr>

 </table>

  </DIV>

  <DIV id=dis_div2 style="DISPLAY: none">
     <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode2" name="select2" style="width=100%">
      </select>
　</td>
 <td width="100" >
  <input name="num2" type="text" id="num2" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal2" type="text"  id="cal2" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22"><input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(2)"> </td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(2)"> </td>
 </tr>
</table>
</DIV>


<DIV id=dis_div3 style="DISPLAY: none">
   <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
      <select  id="childNode3" name="select3" style="width=100%">
      </select>
　</td>
 <td width="100" >
	<input name="num3" type="text" id="num3" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal3" type="text"  id="cal3" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22"> <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(3)"> </td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(3)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div4 style="DISPLAY: none">
   <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode4" name="select4" style="width=100%">
      </select>
　</td>
 <td width="100">
	<input name="num4" type="text" id="num4" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal4" type="text"  id="cal4" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22"><input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(4)"> </td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(4)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div5 style="DISPLAY: none">
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode5" name="select5" style="width=100%">
      </select>
　</td>
 <td width="100" >
  <input name="num5" type="text" id="num5" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
 </td>
 <td width="22" >
  <input name="cal5" type="text"  id="cal5" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
 </td>
 <td width="22"> <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(5)"> </td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(5)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div6 style="DISPLAY: none">
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode6" name="select6" style="width=100%">
      </select>
　</td>
 <td width="100" >
   <input name="num6" type="text" id="num6" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal6" type="text"  id="cal6" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22"> <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px "  onclick="insert_row(6)"></td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(6)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div7 style="DISPLAY: none">
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode7" name="select7" style="width=100%">
      </select>
　</td>
 <td width="100" >
	<input name="num7" type="text" id="num7" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal7" type="text"  id="cal7" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22"> <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(7)"></td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(7)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div8 style="DISPLAY: none">
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode8" name="select8" style="width=100%">
      </select>
　</td>
 <td width="100" >
	<input name="num8" type="text" id="num8" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal8" type="text"  id="cal8" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22"> <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(8)"></td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(8)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div9 style="DISPLAY: none">
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode9" name="select9" style="width=100%">
      </select>
　</td>
 <td width="100" >
	<input name="num9" type="text" id="num9" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal9" type="text"  id="cal9" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22"> <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(9)"></td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(9)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div10 style="DISPLAY: none">
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode10" name="select10" style="width=100%">
      </select>
　</td>
 <td width="100" >
	<input name="num10" type="text" id="num10" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal10" type="text"  id="cal10" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22"> <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(10)"> </td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(10)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div11 style="DISPLAY: none">
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode11" name="select11" style="width=100%">
      </select>
　</td>
 <td width="100" >
 <input name="num11" type="text" id="num11" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal11" type="text"  id="cal11" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22">
   <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(11)">
  </td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(11)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div12 style="DISPLAY: none">
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode12" name="select12" style="width=100%">
      </select>
　</td>
 <td width="100" >
	<input name="num12" type="text" id="num12" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal12" type="text"  id="cal12" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22"> <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(12)"> </td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(12)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div13 style="DISPLAY: none" >
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode13" name="select13" style="width=100%">
      </select>
　</td>
 <td width="100" >
	<input name="num13" type="text" id="num13" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal13" type="text"  id="cal13" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22"> <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(13)"></td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(13)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div14 style="DISPLAY: none">
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode14" name="select14" style="width=100%">
      </select>
　</td>
 <td width="100" >
	<input name="num14" type="text" id="num14" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal14" type="text"  id="cal14" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22"> <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(14)"> </td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(14)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div15 style="DISPLAY: none">
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode15" name="select15" style="width=100%">
      </select>
　</td>
 <td width="100" >
	<input name="num15" type="text" id="num15" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal15" type="text"  id="cal15" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22"> <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(15)"> </td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(15)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div16 style="DISPLAY: none">
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode16" name="select16" style="width=100%">
      </select>
　</td>
 <td width="100" >
	<input name="num16" type="text" id="num16" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal16" type="text"  id="cal16" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22">  <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(16)"> </td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(16)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div17 style="DISPLAY: none">
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
   <select id="childNode17" name="select17" style="width=100%">
   </select>
　</td>
 <td width="100" >
	<input name="num17" type="text" id="num17" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal17" type="text"  id="cal17" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22"> <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(17)"> </td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(17)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div18 style="DISPLAY: none">
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode18" name="select18" style="width=100%">
      </select>
　</td>
 <td width="100" >
	<input name="num18" type="text" id="num18" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal18" type="text"  id="cal18" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22">  <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(18)"> </td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(18)"> </td>
 </tr>
</table>
</DIV>

<DIV id=dis_div19 style="DISPLAY: none">
    <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="1" ></td>
    </tr>
  </table>
<table name='tb0' id="tb0" width="650"  border="0" cellspacing="1" cellpadding="1" bgcolor="#2D7DC6"   >
 <tr  valign=middle style=" background-color: #E1F4FD">
 <td width="484" >
	  <select id="childNode19" name="select19" style="width=100%">
      </select>
　</td>
 <td width="100" >
	<input name="num19" type="text" id="num19" class="input4" style="background-color:White;border-color:#345600;width=100%;" />
　</td>
 <td width="22" >
 <input name="cal19" type="text"  id="cal19" class="input4" style="background-color:White;border-color:#345600;width=100%;"  value="" readonly="true"/>
　</td>
 <td width="22"> <input   type="button" style=" background-image: url(<%=path %>/img/buttone1.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row(19)"></td>
 <td width="22"> <input type="button" style=" background-image: url(<%=path %>/img/buttone2.gif); height: 22px; width: 27px; background-color: #9AD5ED; font-size:
12px; padding-top: 2px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px " onclick="insert_row2(19)"> </td>
 </tr>
</table>
</DIV>

<table>
  <tr >
   <td colspan="5" align="center">
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <input type="button" value="保存" class="buttona" onclick='fsetValue();'>
  </td>
  </tr>
</table>
</form>
</body>

<script type="text/javascript">
var j=0
var t="";
function fsetValue(){
  var s="J";
  var b="!公式：";
  if(document.all.select0.value!=0)
  {
    s+=document.all.select0.value
    s+=document.all.num0.value
    s+=document.all.cal0.value
    b+=document.all.num0.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal0.value
  }
   if(document.all.select1.value!=0)
  {
    s+=document.all.select1.value
    s+=document.all.num1.value
    s+=document.all.cal1.value
    b+=document.all.num1.value
    var m=document.all.select1.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal1.value
  }
    if(document.all.select2.value!=0)
  {
    s+=document.all.select2.value
    s+=document.all.num2.value
    s+=document.all.cal2.value
    b+=document.all.num2.value
    var m=document.all.select2.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal2.value
  }
    if(document.all.select3.value!=0)
  {
    s+=document.all.select3.value
    s+=document.all.num3.value
    s+=document.all.cal3.value
    b+=document.all.num3.value
    var m=document.all.select3.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal3.value
  }
    if(document.all.select4.value!=0)
  {
    s+=document.all.select4.value
    s+=document.all.num4.value
    s+=document.all.cal4.value
    b+=document.all.num4.value
    var m=document.all.select4.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal4.value
  }
    if(document.all.select5.value!=0)
  {
    s+=document.all.select5.value
    s+=document.all.num5.value
    s+=document.all.cal5.value
    b+=document.all.num5.value
    var m=document.all.select5.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal5.value
  }
    if(document.all.select6.value!=0)
  {
    s+=document.all.select6.value
    s+=document.all.num6.value
    s+=document.all.cal6.value
    b+=document.all.num6.value
    var m=document.all.select6.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal6.value
  }
  if(document.all.select7.value!=0)
  {
    s+=document.all.select7.value
    s+=document.all.num7.value
    s+=document.all.cal7.value
    b+=document.all.num7.value
    var m=document.all.select7.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal7.value
  }
    if(document.all.select8.value!=0)
  {
    s+=document.all.select8.value
    s+=document.all.num8.value
    s+=document.all.cal8.value
    b+=document.all.num8.value
    var m=document.all.select8.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal8.value
  }
   if(document.all.select9.value!=0)
  {
    s+=document.all.select9.value
    s+=document.all.num9.value
    s+=document.all.cal9.value
    b+=document.all.num9.value
    var m=document.all.select9.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal9.value
  }
    if(document.all.select10.value!=0)
  {
    s+=document.all.select10.value
    s+=document.all.num10.value
    s+=document.all.cal10.value
    b+=document.all.num10.value
    var m=document.all.select10.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal10.value
  }
  if(document.all.select11.value!=0)
  {
    s+=document.all.select11.value
    s+=document.all.num11.value
    s+=document.all.cal11.value
    b+=document.all.num11.value
    var m=document.all.select11.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal11.value
  }
    if(document.all.select12.value!=0)
  {
    s+=document.all.select12.value
    s+=document.all.num12.value
    s+=document.all.cal12.value
    b+=document.all.num12.value
    var m=document.all.select12.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal12.value
  }
      if(document.all.select13.value!=0)
  {
    s+=document.all.select13.value
    s+=document.all.num13.value
    s+=document.all.cal13.value
    b+=document.all.num13.value
    var m=document.all.select13.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal13.value
  }
    if(document.all.select14.value!=0)
  {
    s+=document.all.select14.value
    s+=document.all.num14.value
    s+=document.all.cal14.value
    b+=document.all.num14.value
    var m=document.all.select14.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal14.value
  }
  if(document.all.select15.value!=0)
  {
    s+=document.all.select15.value
    s+=document.all.num15.value
    s+=document.all.cal15.value
    b+=document.all.num15.value
    var m=document.all.select15.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal15.value
  }
    if(document.all.select16.value!=0)
  {
    s+=document.all.select16.value
    s+=document.all.num16.value
    s+=document.all.cal16.value
    b+=document.all.num16.value
    var m=document.all.select16.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal16.value
  }
     if(document.all.select17.value!=0)
  {
    s+=document.all.select17.value
    s+=document.all.num17.value
    s+=document.all.cal17.value
    b+=document.all.num17.value
    var m=document.all.select17.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal17.value
  }
  if(document.all.select18.value!=0)
  {
    s+=document.all.select18.value
    s+=document.all.num18.value
    s+=document.all.cal18.value
    b+=document.all.num18.value
    var m=document.all.select18.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
    b+=document.all.cal18.value
  }
    if(document.all.select19.value!=0)
  {
    s+=document.all.select19.value
    s+=document.all.num19.value //最后一行没加运算符
    b+=document.all.num19.value
    var m=document.all.select19.value
    var m=document.all.select0.value
    changeformla(m);
    b+=t
   }
  var tp = new Array(s,b)
  window.returnValue=tp;
  window.close();
}

function changeformla(m){
	var queryString = "<%=path%>/sysfinance/getFormlaCharAAC.do?";
	queryString = queryString + "key="+m+""; 
	findInfo(queryString);
}
function display_getvalue(value){
	t=value;
}

</script>
</html:html>
