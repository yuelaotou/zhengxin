<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security" %>
<%@ include file="/checkUrl.jsp"%>
<%  
   String path = request.getContextPath();
%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
<script language="javascript">
function executeAjax1(){
	var officeCode = document.forms[0].elements["officeCode"].value;
	var url = "cumulativeinfoAjaxAC.do?officeCode="+officeCode;
	findInfo123(url);
}
function findInfo123(url) {
 createXMLHttpRequest();
	    xmlHttp.onreadystatechange = handleStateChange123;
	    xmlHttp.open("GET", url, true);
	    xmlHttp.send(null);   
}
function handleStateChange123() {
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
			if(i==0){
			document.all.collectionBankId.value=values[i].firstChild.data;
			}
		}	
      }
   }
}
function getChildValue(){
 var selectObj = document.getElementById("childNode");
  document.all.collectionBankId.value=selectObj.options[selectObj.selectedIndex].value;
}


	function load(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
		document.all.collectionBankId.value="";
		document.all.officeCode.value="";
		document.all.queryTime.value="";
		<logic:present name="printList" scope="request">
			document.forms(1).Cell1.Login("沈阳金软科技有限公司","","13100104509","0740-1662-7775-3003"); 
			document.forms(1).Cell1.openfile("<%=path%>/syscollection/querystatistics/collectionuseinfo/report/to_collectionuseinfo_cell.cll","");
			<%
				List list=(List)request.getAttribute("printList");
			%>
			//纵向添加
			document.forms(1).Cell1.d(5,12,0,"<%=list.get(28)%>");
			document.forms(1).Cell1.d(5,13,0,"<%=list.get(29)%>");
			document.forms(1).Cell1.d(5,14,0,"<%=list.get(2)%>");
			document.forms(1).Cell1.d(5,15,0,"<%=list.get(3)%>");
			document.forms(1).Cell1.d(5,16,0,"<%=list.get(4)%>");
			document.forms(1).Cell1.d(5,17,0,"<%=list.get(21)%>");
			document.forms(1).Cell1.d(5,18,0,"<%=list.get(22)%>");
			document.forms(1).Cell1.d(5,19,0,"<%=list.get(8)%>");
			document.forms(1).Cell1.d(5,20,0,"<%=list.get(23)%>");
			document.forms(1).Cell1.d(5,21,0,"<%=list.get(24)%>");
			document.forms(1).Cell1.d(5,22,0,"<%=list.get(10)%>");
			document.forms(1).Cell1.d(5,23,0,"<%=list.get(11)%>");
			document.forms(1).Cell1.d(5,25,0,"<%=list.get(30)%>");
			document.forms(1).Cell1.d(5,26,0,"<%=list.get(31)%>");
			document.forms(1).Cell1.d(5,27,0,"<%=list.get(32)%>");
			document.forms(1).Cell1.d(5,28,0,"<%=list.get(33)%>");
			document.forms(1).Cell1.d(5,29,0,"<%=list.get(34)%>");
			document.forms(1).Cell1.d(5,30,0,"<%=list.get(35)%>");
			document.forms(1).Cell1.d(5,31,0,"<%=list.get(36)%>");
			document.forms(1).Cell1.d(5,32,0,"<%=list.get(37)%>");
			document.forms(1).Cell1.d(5,33,0,"<%=list.get(38)%>");
			//期初数---纵向添加
			document.forms(1).Cell1.d(10,12,0,"<%=list.get(12)%>");
			document.forms(1).Cell1.d(10,13,0,"<%=list.get(13)%>");
			document.forms(1).Cell1.d(10,14,0,"<%=list.get(14)%>");
			document.forms(1).Cell1.d(10,15,0,"<%=list.get(15)%>");
			document.forms(1).Cell1.d(10,16,0,"<%=list.get(25)%>");
			document.forms(1).Cell1.d(10,17,0,"<%=list.get(16)%>");
			document.forms(1).Cell1.d(10,18,0,"<%=list.get(17)%>");
			document.forms(1).Cell1.d(10,19,0,"<%=list.get(18)%>");
			document.forms(1).Cell1.d(10,20,0,"<%=list.get(19)%>");
			document.forms(1).Cell1.d(10,21,0,"<%=list.get(26)%>");
			document.forms(1).Cell1.d(10,22,0,"<%=list.get(27)%>");
			document.forms(1).Cell1.d(10,23,0,"<%=list.get(27)%>");
			document.forms(1).Cell1.d(10,24,0,"<%=list.get(26)%>");
			document.forms(1).Cell1.d(10,25,0,"<%=list.get(27)%>");
			document.forms(1).Cell1.d(10,26,0,"<%=list.get(27)%>");
			document.forms(1).Cell1.d(10,27,0,"<%=list.get(27)%>");
			document.forms(1).Cell1.d(10,28,0,"<%=list.get(27)%>");
			document.forms(1).Cell1.d(10,29,0,"<%=list.get(27)%>");
			document.forms(1).Cell1.d(10,30,0,"<%=list.get(27)%>");
			document.forms(1).Cell1.d(10,31,0,"<%=list.get(27)%>");
			document.forms(1).Cell1.d(10,32,0,"<%=list.get(27)%>");
			document.forms(1).Cell1.d(10,33,0,"<%=list.get(27)%>");
			document.forms(1).Cell1.d(10,34,0,"<%=list.get(27)%>");
			document.forms(1).Cell1.d(10,35,0,"<%=list.get(27)%>");
			document.forms(1).Cell1.d(10,36,0,"<%=list.get(27)%>");			
			
			document.forms(1).Cell1.S(4,6,0,"<%=list.get(39)%>");
			document.forms(1).Cell1.S(6,6,0,"<%=list.get(40)%>");
			document.forms(1).Cell1.S(8,37,0,"<%=list.get(41)%>");
			
			document.forms(1).Cell1.AllowExtend=false;
			document.forms(1).Cell1.AllowDragdrop=false;
			document.forms(1).Cell1.WorkbookReadonly=true;
		</logic:present>
		<logic:notPresent name="printList" scope="request">
			
		</logic:notPresent>
	}
	<logic:present name="printList" scope="request">
		function printPreview(){
			var k=document.forms(1).Cell1.GetCurSheet();//显示打印预览那个页面
			document.forms(1).Cell1.printPreviewEx(1,k,false);
		}
		function Button1_onclick()
		{
			document.forms(1).Cell1.SaveFile();
		}
		function Button2_onclick()
		{
			document.forms(1).Cell1.ExportPdfFile("d:\\aa.pdf",-1,1,1);
		}
		function Button3_onclick()
		{
			document.forms(1).Cell1.PrintPageSetup();
		}
		function Button4_onclick()
		{
			document.forms(1).Cell1.FindDialogEx( 0,"");
		}
		function Button5_onclick()
		{
			document.forms(1).Cell1.ImportExcelDlg();
		}
		function printsheet()//真正的打印
		{
			var k=document.forms(1).Cell1.GetCurSheet();//显示打印预览那个页面--固定
			document.forms(1).Cell1.PrintSheet(1,k);//固定...
		}
	</logic:present>
</script>
<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%281%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>	
<script type="text/javascript">
	function a(){
		var p = "<%=path%>";
		officeAjax(p);
	}
</script>
    <title>归集使用查询</title>
  </head>
  <link rel="stylesheet" href="<%=path %>/css/index.css" type="text/css">
  <script language="javascript" src="<%=path%>/js/common.js"></script>
  <body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="load();">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
    <td class=td3>
      
      <html:form action="/collectionuseinfoFindAC.do" >
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">归集使用查询</b></td>
                <td height="22" background="<%=path %>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
          <tr> 
            <td width="17%"   class="td1">办事处</td>
            <td width="33%"  >
              <html:select property="officeCode" styleClass="input4" onchange="executeAjax1();">
          						<option value=""></option>
          						<html:options  collection="officeList1" property="value" labelProperty="label"/>
          					</html:select>
            </td>
            <td width="17%" class="td1" >归集银行</td>
            <td>
									<td width="35%" height="18" colspan="2" align="center">
								<html:hidden property="collectionBankId" name="collectionuseinfoFindAF" />
								<select class="input4" id="childNode" onChange="getChildValue()" >
								</select>
							</td>
			
          </tr>
          <tr>
          	<td width="17%"   class="td1">年月<font color="#FF0000">*</font></td>
            <td width="33%"  >
              <html:text name="collectionuseinfoFindAF" property="queryTime"  styleClass="input3" styleId="txtsearch"></html:text>
            </td>
          </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right"><br>
            <html:submit styleClass="buttona" onclick="return checkMonth('queryTime');">查询</html:submit>
        </tr>
      </table>
      </html:form>
      <logic:present name="printList" scope="request">
		<form action="">
		    <table align="center">
				<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:850px;  TOP:0px;HEIGHT:300px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
				<tr><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/>
				<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1">
				<INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1">
				<INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置">
				<INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框">
				<INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入">	
				<INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1">
				<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">	
			</table>
		</form>
	</logic:present>
	</td>
  </tr>
</table>
</body>
</html:html>
