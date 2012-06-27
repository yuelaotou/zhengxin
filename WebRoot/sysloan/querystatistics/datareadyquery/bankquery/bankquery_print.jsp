<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.dto.BankQueryDTO"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();	
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js">
</script>
	</head>
	<script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/datareadyquery/bankquery/bankqueryinfo_print.cll","");	
	<%
    		BankQueryDTO bankQueryDTO=(BankQueryDTO)request.getAttribute("bankQueryDTO");	
    		String bizdate=(String)request.getAttribute("bizDate");	
    		 String userName=(String)request.getAttribute("username");
	%>		var bizDateYear;
	var bizDateMonth;
	var bizDate;
	var userName;
	bizDateYear="<%=bizdate.substring(0,4)%>";		
				bizDateMonth="<%=bizdate.substring(4,6)%>";	
				bizDate="<%=bizdate.substring(6,8)%>";	
				userName="<%=userName%>";	
		document.forms(0).Cell1.S(6,3,0,bizDateYear);
		document.forms(0).Cell1.S(8,3,0,bizDateMonth);
		document.forms(0).Cell1.S(10,3,0,bizDate);		
		document.forms(0).Cell1.S(3,4,0,"<%=bankQueryDTO.getLoanBankId()%>");
		document.forms(0).Cell1.S(7,4,0,"<%=bankQueryDTO.getOffice()%>");
		document.forms(0).Cell1.S(3,5,0,"<%=bankQueryDTO.getLoanAcc()%>");
		document.forms(0).Cell1.S(7,5,0,"<%=bankQueryDTO.getInterestAcc()%>");
	    document.forms(0).Cell1.S(3,6,0,"<%=bankQueryDTO.getBankPrisident()%>");
		document.forms(0).Cell1.S(7,6,0,"<%=bankQueryDTO.getBankPrisidentTel()%>");
		document.forms(0).Cell1.S(3,7,0,"<%=bankQueryDTO.getContactPrsn()%>");
		document.forms(0).Cell1.S(7,7,0,"<%=bankQueryDTO.getContactTel()%>");
	    document.forms(0).Cell1.S(3,8,0,"<%=bankQueryDTO.getBusiness()%>");
		document.forms(0).Cell1.S(7,8,0,"<%=bankQueryDTO.getLoanBnakSt()%>");	
		document.forms(0).Cell1.S(7,9,0,userName);
		document.forms(0).Cell1.ReDraw();
	    document.forms(0).Cell1.AllowExtend=false;
	    document.forms(0).Cell1.AllowDragdrop=false;
		document.forms(0).Cell1.WorkbookReadonly=true;	
}
function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
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
</script>
	<script language="JScript.Encode">
		eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
  </script>
	<body onload="load();" onContextmenu="return false">
		<form action="">
			<table align="center">
				<tr>
					<OBJECT id=Cell1
						style="LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
						<PARAM NAME="_Version" VALUE="65536">
						<PARAM NAME="_ExtentX" VALUE="10266">
						<PARAM NAME="_ExtentY" VALUE="7011">
						<PARAM NAME="_StockProps" VALUE="0">
					</OBJECT>
				</tr>
				<tr>
					<input type="button" name="print" value="打印预览"
						onclick="printPreview();" />
					<INPUT id="Button1" onclick="Button1_onclick()" type="button"
						value="另存为Excel" name="Button1">
					<INPUT id="Button1" onclick="Button2_onclick()" type="button"
						value="另存为pdf" name="Button1">
					<INPUT id="Button3" style="WIDTH: 100px"
						onclick="Button3_onclick()" type="button" value="页面设置">
					<INPUT id="Button3" style="WIDTH: 100px"
						onclick="Button4_onclick()" type="button" value="查找对话框">
						<INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1">
					<INPUT id="Button3" style="WIDTH: 100px"
						onclick="Button5_onclick()" type="button" value="excel导入">
					<INPUT id="Button3" onclick="javascript:history.back();"
						type="button" value=" 返回 ">
			</table>
		</form>
	</body>
</html>


