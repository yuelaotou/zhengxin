<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF"%>
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
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/report/loanapply_ta.cll","");

	<%
			LoanapplyNewAF loanapplynewAF=(LoanapplyNewAF)request.getAttribute("loanapplynewAF");
			String opertname=(String)request.getAttribute("opertname");
			String time=(String)request.getAttribute("time");
 	%>

		document.forms(0).Cell1.S(2,3,0,"<%=loanapplynewAF.getBorrower().getOffice() %>");
		var empId = "<%=loanapplynewAF.getBorrower().getEmpId() %>";
		if(empId!=""){
			empId = format(empId)+empId;
			document.forms(0).Cell1.S(2,4,0,empId);
		}
		document.forms(0).Cell1.S(2,5,0,"<%=loanapplynewAF.getBorrower().getCardKind() %>");
		document.forms(0).Cell1.S(2,6,0,"<%=loanapplynewAF.getBorrower().getAge() %>");
		document.forms(0).Cell1.S(2,7,0,"<%=loanapplynewAF.getBorrower().getNation() %>");
		document.forms(0).Cell1.S(2,8,0,"<%=loanapplynewAF.getBorrower().getDegree() %>");
		document.forms(0).Cell1.S(2,9,0,"<%=loanapplynewAF.getBorrower().getHouseTel() %>");
		var orgId = "<%=loanapplynewAF.getBorrower().getOrgId() %>";
		if(orgId!=""){
			orgId = formatTen(orgId)+orgId;
			document.forms(0).Cell1.S(2,10,0,orgId);
		}
		document.forms(0).Cell1.S(2,11,0,"<%=loanapplynewAF.getBorrower().getOrgMail() %>");
		document.forms(0).Cell1.S(2,12,0,"<%=loanapplynewAF.getBorrower().getMonthSalary() %>");
		document.forms(0).Cell1.S(2,13,0,"<%=loanapplynewAF.getBorrower().getBgnDate() %>");
		document.forms(0).Cell1.S(2,14,0,"<%=loanapplynewAF.getBorrower().getContactAName() %>");
		document.forms(0).Cell1.S(2,15,0,"<%=loanapplynewAF.getBorrower().getContactAOrgTel() %>");
		document.forms(0).Cell1.S(2,16,0,"<%=loanapplynewAF.getBorrower().getContactBName() %>");
		document.forms(0).Cell1.S(2,17,0,"<%=loanapplynewAF.getBorrower().getContactBOrgTel() %>");
				
    	document.forms(0).Cell1.S(4,4,0,"<%=loanapplynewAF.getBorrower().getBorrowerName() %>");
    	document.forms(0).Cell1.S(4,5,0,"<%=loanapplynewAF.getBorrower().getCardNum() %>");
    	document.forms(0).Cell1.S(4,6,0,"<%=loanapplynewAF.getBorrower().getBusiness() %>");
    	document.forms(0).Cell1.S(4,7,0,"<%=loanapplynewAF.getBorrower().getNativePlace() %>");
    	document.forms(0).Cell1.S(4,8,0,"<%=loanapplynewAF.getBorrower().getHomeAddr() %>");
    	document.forms(0).Cell1.S(4,9,0,"<%=loanapplynewAF.getBorrower().getHomeMobile() %>");
    	document.forms(0).Cell1.S(4,10,0,"<%=loanapplynewAF.getBorrower().getOrgName() %>");
    	document.forms(0).Cell1.S(4,11,0,"<%=loanapplynewAF.getBorrower().getAccBlnce() %>");
    	document.forms(0).Cell1.S(4,12,0,"<%=loanapplynewAF.getBorrower().getMonthPay() %>");
    	document.forms(0).Cell1.S(4,13,0,"<%=loanapplynewAF.getBorrower().getEndDate() %>");
    	document.forms(0).Cell1.S(4,14,0,"<%=loanapplynewAF.getBorrower().getRelationA() %>");
    	document.forms(0).Cell1.S(4,15,0,"<%=loanapplynewAF.getBorrower().getContactAHouseTel() %>");
    	document.forms(0).Cell1.S(4,16,0,"<%=loanapplynewAF.getBorrower().getRelationB() %>");
    	document.forms(0).Cell1.S(4,17,0,"<%=loanapplynewAF.getBorrower().getContactBHouseTel() %>");
    	document.forms(0).Cell1.S(4,18,0,"<%=opertname %>");
    	document.forms(0).Cell1.S(6,4,0,"<%=loanapplynewAF.getBorrower().getSex() %>");
    	document.forms(0).Cell1.S(6,5,0,"<%=loanapplynewAF.getBorrower().getBirthday() %>");
    	document.forms(0).Cell1.S(6,6,0,"<%=loanapplynewAF.getBorrower().getTitle() %>");
    	document.forms(0).Cell1.S(6,7,0,"<%=loanapplynewAF.getBorrower().getMarriageSt() %>");
    	document.forms(0).Cell1.S(6,8,0,"<%=loanapplynewAF.getBorrower().getHomeMail() %>");
    
    	document.forms(0).Cell1.S(6,10,0,"<%=loanapplynewAF.getBorrower().getOrgTel() %>");
    	document.forms(0).Cell1.S(6,11,0,"<%=loanapplynewAF.getBorrower().getOrgAddr() %>");
    	document.forms(0).Cell1.S(6,12,0,"<%=loanapplynewAF.getBorrower().getEmpSt_() %>");
    	document.forms(0).Cell1.S(6,14,0,"<%=loanapplynewAF.getBorrower().getContactAOrgName() %>");
    	document.forms(0).Cell1.S(6,15,0,"<%=loanapplynewAF.getBorrower().getContactAMobile() %>");
    	document.forms(0).Cell1.S(6,16,0,"<%=loanapplynewAF.getBorrower().getContactBOrgName() %>");
    	document.forms(0).Cell1.S(6,17,0,"<%=loanapplynewAF.getBorrower().getContactBMobile() %>");
    	document.forms(0).Cell1.S(6,18,0,"<%=time%>");

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
				<td>
						<input type="button" name="print" value="打印预览"
							onclick="printPreview();" />
					</td>
					<td>
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1" />
					</td>
					<td>
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入" />
					</td>
					<td>
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1" />
					</td>
					<td>
					<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 ">
					</td>
			</table>
		</form>
	</body>
</html>
