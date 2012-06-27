<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTbNewAF"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTdNewAF"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>
<html>
	<head>
	</head>
	<script src="<%=path%>/js/common.js">
</script>
	<script type="text/javascript">
function load(){
	//加载默版文件..
	loginReg();	
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/report/loanapplyprint.cll","");
	
	<%
		String opertname=(String)request.getAttribute("opertname");
		String bizDate=(String)request.getAttribute("bizDate");
		LoanapplyNewAF loanapplynewAF=(LoanapplyNewAF)request.getAttribute("loanapplynewAF");
		LoanapplyTbNewAF loanapplytbnewAF=(LoanapplyTbNewAF)request.getAttribute("loanapplytbnewAF");
		LoanapplyTdNewAF loanapplytdnewAF=(LoanapplyTdNewAF)request.getAttribute("loanapplytdnewAF");
	%>
	var bigLoanmoney;
	var loanmoney = "<%=loanapplytdnewAF.getLoanMoney()%>";
	
	if(loanmoney!='null'){
		bigLoanmoney = ChangeToBig(loanmoney);
	}
	var borrowerName_assistant = "<%=loanapplytbnewAF.getName()%>";
	if(borrowerName_assistant=='null'){
		borrowerName_assistant = "";
	}
	var cardNum_assistant = "<%=loanapplytbnewAF.getCardNum()%>";
	if(cardNum_assistant=='null'){
		cardNum_assistant = "";
	}
	var loanTimeLimit = "<%=loanapplytdnewAF.getLoantimeLimit()%>";
	if(loanTimeLimit=='null'){
		loanTimeLimit = "";
	}
	var privilege_borrower_id = "<%=loanapplynewAF.getPrivilege_borrower_id()%>";
	if(privilege_borrower_id!='0'){
		document.forms(0).Cell1.S(9,1,0,"*");
	}
	document.forms(0).Cell1.S(3,3,0,"<%=loanapplynewAF.getBorrower().getBorrowerName()%>");
	document.forms(0).Cell1.S(7,3,0,"<%=loanapplynewAF.getBorrower().getCardNum()%>");
	document.forms(0).Cell1.S(3,4,0,borrowerName_assistant);
	document.forms(0).Cell1.S(7,4,0,cardNum_assistant);
	document.forms(0).Cell1.S(4,5,0,bigLoanmoney);
	document.forms(0).Cell1.S(4,11,0,"<%=loanapplynewAF.getOperator()%>");
	document.forms(0).Cell1.S(8,11,0,"<%=loanapplynewAF.getChkPerson()%>");
	document.forms(0).Cell1.S(6,24,0,"<%=loanapplynewAF.getVipChkPerson()%>");
	document.forms(0).Cell1.S(2,6,0,loanTimeLimit);
	document.forms(0).Cell1.S(3,16,0,"<%=bizDate.substring(0, 4)%>"+"年"+"<%=bizDate.substring(4, 6)%>"+"月"+"<%=bizDate.substring(6, 8)%>"+"日");
	document.forms(0).Cell1.S(7,16,0,"<%=bizDate.substring(0, 4)%>"+"年"+"<%=bizDate.substring(4, 6)%>"+"月"+"<%=bizDate.substring(6, 8)%>"+"日");
	document.forms(0).Cell1.S(7,27,0,"<%=bizDate.substring(0, 4)%>"+"年"+"<%=bizDate.substring(4, 6)%>"+"月"+"<%=bizDate.substring(6, 8)%>"+"日");
}
 	function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();
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
	<body onContextmenu="return false" onload="load();">
		<form action="">
			<table align="center">
				<tr>
					<OBJECT id=Cell1
						style="LEFT:0px;WIDTH:900px;  TOP:0px;HEIGHT:500px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
						<PARAM NAME="_Version" VALUE="65536">
						<PARAM NAME="_ExtentX" VALUE="10266">
						<PARAM NAME="_ExtentY" VALUE="7011">
						<PARAM NAME="_StockProps" VALUE="0">
					</OBJECT>
				</tr>
				<tr>
					<td align="center">
						<input type="button" name="print" value="打印预览"
							onclick="printPreview();" />
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1" />
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1" />
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置" />
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框" />
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入" />
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1" />
						<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 ">
					</td>
			</table>
		</form>
	</body>
</html>
