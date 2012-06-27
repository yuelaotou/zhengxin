<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.beforeloanapply.dto.BeforeLoanApplyDTO"%>
<%@ page import="java.math.BigDecimal"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String url = (String) request.getAttribute("URL");
	if (url == null) {
		url = "beforeLoanApplyForwardURLAC.do";
	}
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js">
</script>
	</head>
	<script type="text/javascript">
	function load(){	
		loginReg();
		document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/report/caculatecorpus.cll","");
		<%
			List list=(List)request.getAttribute("list");
			String minMonthRate=list.get(0).toString();
			String maxMonthRate=list.get(1).toString();
			for(int j=1;j<=30;j++)
	  		{
	  			String corpusInterest_list[]=(String[])list.get(j+1);
	  			for(int k=1;k<=30;k++){
	  				if(j<=10){
	 	%>
	 					document.forms(0).Cell1.S("<%=j + 1%>","<%=k + 4%>",0,"<%=corpusInterest_list[k - 1]%>");
	 					document.forms(0).Cell1.S("<%=j + 1%>",3,0,"<%=minMonthRate%>");
	 	<%
	 				}else if(j<=20){
	 	%>
	 					document.forms(0).Cell1.S("<%=j - 9%>",37,0,"<%=maxMonthRate%>");
	 					document.forms(0).Cell1.S("<%=j - 9%>","<%=k + 38%>",0,"<%=corpusInterest_list[k - 1]%>");
	 	<%				
	 				}else{
	 	%>				
	 					document.forms(0).Cell1.S("<%=j - 19%>",71,0,"<%=maxMonthRate%>");
	 					document.forms(0).Cell1.S("<%=j - 19%>","<%=k + 72%>",0,"<%=corpusInterest_list[k - 1]%>");
	 	<%	
	 				}
	 			}
	 		}
	 	%>
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
						style="LEFT:0px;WIDTH:800px;  TOP:0px;HEIGHT:500px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
						<PARAM NAME="_Version" VALUE="65536">
						<PARAM NAME="_ExtentX" VALUE="10266">
						<PARAM NAME="_ExtentY" VALUE="7011">
						<PARAM NAME="_StockProps" VALUE="0">
					</OBJECT>
				</tr>
				<tr align="center">
					<td>
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
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入">
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
