<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.lang.*" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.*" %>
<%@ include file="/checkUrl.jsp"%>
<%
   String path=request.getContextPath();
 %>
<html>
<head>
<script src="<%=path%>/js/common.js">
</script>
</head>
<script type="text/javascript">
	function load(){
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/paymentpickstatistics/searchLackInfo/report/searchlackInfo_jj.cll","");
	<%
    		SearchLackInfoContentDTO dto=(SearchLackInfoContentDTO)request.getSession().getAttribute("searchLackInfoContentDTO");
    		String bizDate=(String)request.getSession().getAttribute("bizDate");
    		String year=bizDate.substring(0,4);
    		String month=bizDate.substring(4,6);
    		String day=bizDate.substring(6,8);
	%>
		
		document.forms(0).Cell1.S(1,5,0,format("<%=dto.getOrgcode()%>")+"<%=dto.getOrgcode()%>");
		document.forms(0).Cell1.S(2,5,0,"<%=dto.getOrgname()%>");
		document.forms(0).Cell1.S(4,5,0,"<%=dto.getZcjcrs()%>");
		document.forms(0).Cell1.S(5,5,0,"<%=dto.getOrgPay()%>");
		document.forms(0).Cell1.S(6,5,0,"<%=dto.getEmpPay()%>");
		document.forms(0).Cell1.S(7,5,0,"<%=dto.getOrgMonth()%>");
		document.forms(0).Cell1.S(8,5,0,"<%=dto.getEmpMonth()%>");
		document.forms(0).Cell1.S(9,5,0,"<%=dto.getSumPay()%>");
		document.forms(0).Cell1.S(10,5,0,"<%=dto.getOrgPayMonth()%>");
		document.forms(0).Cell1.S(11,5,0,"<%=dto.getEmpPayMonth()%>");
		document.forms(0).Cell1.S(3,7,0,"<%=dto.getOrgname()%>");
		document.forms(0).Cell1.S(7,7,0,"<%=dto.getArtificialPerson()%>");
		document.forms(0).Cell1.S(5,11,0,"<%=year%>年<%=month%>月<%=day%>日");
		
		
		
		document.forms(0).Cell1.S(1,17,0,format("<%=dto.getOrgcode()%>")+"<%=dto.getOrgcode()%>");
		document.forms(0).Cell1.S(2,17,0,"<%=dto.getOrgname()%>");
		document.forms(0).Cell1.S(4,17,0,"<%=dto.getZcjcrs()%>");
		document.forms(0).Cell1.S(5,17,0,"<%=dto.getOrgPay()%>");
		document.forms(0).Cell1.S(6,17,0,"<%=dto.getEmpPay()%>");
		document.forms(0).Cell1.S(7,17,0,"<%=dto.getOrgMonth()%>");
		document.forms(0).Cell1.S(8,17,0,"<%=dto.getEmpMonth()%>");
		document.forms(0).Cell1.S(9,17,0,"<%=dto.getSumPay()%>");
		document.forms(0).Cell1.S(10,17,0,"<%=dto.getOrgPayMonth()%>");
		document.forms(0).Cell1.S(11,17,0,"<%=dto.getEmpPayMonth()%>");
		document.forms(0).Cell1.S(3,19,0,"<%=dto.getOrgname()%>");
		document.forms(0).Cell1.S(7,19,0,"<%=dto.getArtificialPerson()%>");
		document.forms(0).Cell1.S(5,23,0,"<%=year%>年<%=month%>月<%=day%>日");
					
		document.forms(0).Cell1.AllowExtend=false;
		document.forms(0).Cell1.AllowDragdrop=false;
		document.forms(0).Cell1.WorkbookReadonly=true;	
}
	function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面
		document.forms(0).Cell1.printPreviewEx(1,k,false);
	}
	function Button1_onclick()
	{
		document.forms(0).Cell1.SaveFile();
	}
	function Button2_onclick()
	{
		document.forms(0).Cell1.ExportPdfFile("d:\\aa",-1,1,1);
	}
	function Button3_onclick()
	{
		document.forms(0).Cell1.PrintPageSetup();
	}
	function Button4_onclick()
	{
		document.forms(0).Cell1.FindDialogEx( 0,"");
	}
		function Button5_onclick()
	{
		document.forms(0).Cell1.ImportExcelDlg();
	}
	
	function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
	}		
</script>

<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>	
<body onload = "load();"> 
<form action="">
<table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
<tr>
<td><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/></td>
<td><INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1"></td>
<td><INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1"></td>
<td><INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入"></td>
<td><INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 "></td>	
</table>
</form>
</body>
</html>
