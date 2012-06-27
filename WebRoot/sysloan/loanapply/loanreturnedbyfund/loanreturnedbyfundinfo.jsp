<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.form.LoanreturnedbyfundTaAF"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	request.removeAttribute("error");
%>
<%@page import = "org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.dto.EmpinfoDto"%>
<html>
	<head>
		<script src="<%=path%>/js/common.js">
</script>
	</head>
	<script type="text/javascript">
	function load(){
	<%
		LoanreturnedbyfundTaAF loanreturnedbyfundTaAF=(LoanreturnedbyfundTaAF)request.getAttribute("loanreturnedbyfundTaAF");
   		String bizdate=loanreturnedbyfundTaAF.getBizTime();   		
   		String status = loanreturnedbyfundTaAF.getPrint_status();
   		String userName=(String)request.getAttribute("username");
   		
   		String bankName=(String)request.getAttribute("bankName");
   		String houseAddr=(String)request.getAttribute("houseAddr");
   		String loanstartdate=(String)request.getAttribute("loanstartdate");
   		EmpinfoDto empinfoDto_1=(EmpinfoDto)request.getSession().getAttribute("empinfoDto_sa");
   		EmpinfoDto empinfoDto_2=(EmpinfoDto)request.getSession().getAttribute("empinfoDto_sb");
	%>
	var bizDateYear;
	var bizDateMonth;
	var bizDate;
	
	var bizDateYear_stop;
	var bizDateMonth_stop;
	var bizDate_stop;
	
	var userName;
	var status;
    bizDateYear="<%=bizdate.substring(0, 4)%>";		
	bizDateMonth="<%=bizdate.substring(4, 6)%>";	// 办理日期年				
	bizDate="<%=bizdate.substring(6, 8)%>";	//办理日期月
	userName="<%=userName%>";//办理日期日		
	status ="<%=status%>";
	var contractId = <%=loanreturnedbyfundTaAF.getBorrower().getContractId()%>;//合同编号
	var cardNum= "<%=loanreturnedbyfundTaAF.getBorrower().getCardNum()%>";  //借款人身份证号
		
	loginReg();
	
		document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/report/loanbackbyfundinfo.cll","");	
   
		document.forms(0).Cell1.S(2,2,0,"<%=loanreturnedbyfundTaAF.getBorrowerName()%>");
		document.forms(0).Cell1.S(6,2,0,"<%=loanreturnedbyfundTaAF.getBorrower_cardNum()%>");
		document.forms(0).Cell1.S(2,3,0,"<%=loanreturnedbyfundTaAF.getBorrow_orgname()%>");
		document.forms(0).Cell1.S(6,3,0,"<%=loanreturnedbyfundTaAF.getBorrow_ablncc()%>");
		document.forms(0).Cell1.S(2,4,0,"<%=loanreturnedbyfundTaAF.getBorrow_monthpay()%>");
		document.forms(0).Cell1.S(6,4,0,"<%=loanreturnedbyfundTaAF.getBorrow_enddate()%>");
		
		if("<%=loanreturnedbyfundTaAF.getBorrow_s_ablncc()%>"!=""){
			document.forms(0).Cell1.S(2,6,0,"<%=loanreturnedbyfundTaAF.getBorrow_s_empname()%>");
			document.forms(0).Cell1.S(6,6,0,"<%=loanreturnedbyfundTaAF.getBorrow_s_cardnum()%>");
			document.forms(0).Cell1.S(2,7,0,"<%=loanreturnedbyfundTaAF.getBorrow_s_orgname()%>");
			document.forms(0).Cell1.S(6,7,0,"<%=loanreturnedbyfundTaAF.getBorrow_s_ablncc()%>");
			document.forms(0).Cell1.S(2,8,0,"<%=loanreturnedbyfundTaAF.getBorrow_s_monthpay()%>");
			document.forms(0).Cell1.S(6,8,0,"<%=loanreturnedbyfundTaAF.getBorrow_s_enddate()%>");
		}
		
		<%if(empinfoDto_1!=null){%>
			document.forms(0).Cell1.S(2,10,0,"<%=empinfoDto_1.getEmp_name_s()%>");
			document.forms(0).Cell1.S(6,10,0,"<%=empinfoDto_1.getEmp_card_num_s()%>");
		<%}%>
		document.forms(0).Cell1.S(2,11,0,"<%=loanreturnedbyfundTaAF.getBorrow_s_orgnamea()%>");
		document.forms(0).Cell1.S(6,11,0,"<%=loanreturnedbyfundTaAF.getBorrow_s_ablncca()%>");
		document.forms(0).Cell1.S(2,12,0,"<%=loanreturnedbyfundTaAF.getBorrow_s_monthpaya()%>");
		document.forms(0).Cell1.S(6,12,0,"<%=loanreturnedbyfundTaAF.getBorrow_s_enddatea()%>");
		<%if(empinfoDto_2!=null){%>
			document.forms(0).Cell1.S(2,14,0,"<%=empinfoDto_2.getEmp_name_s()%>");
			document.forms(0).Cell1.S(6,14,0,"<%=empinfoDto_2.getEmp_card_num_s()%>");
		<%}%>
		document.forms(0).Cell1.S(2,15,0,"<%=loanreturnedbyfundTaAF.getBorrow_s_orgnameb()%>");
		document.forms(0).Cell1.S(6,15,0,"<%=loanreturnedbyfundTaAF.getBorrow_s_ablnccb()%>");
		document.forms(0).Cell1.S(2,16,0,"<%=loanreturnedbyfundTaAF.getBorrow_s_monthpayb()%>");
		document.forms(0).Cell1.S(3,16,0,"<%=loanreturnedbyfundTaAF.getBorrow_s_enddateb()%>");
		
		document.forms(0).Cell1.S(2,18,0,"<%=loanreturnedbyfundTaAF.getContractId()%>");
		
		document.forms(0).Cell1.S(2,19,0,"<%=loanreturnedbyfundTaAF.getLoanYuE()%>");
		
		document.forms(0).Cell1.S(2,20,0,"<%=loanreturnedbyfundTaAF.getYueHuanBenXi()%>");
		
		document.forms(0).Cell1.S(2,21,0,"<%=loanreturnedbyfundTaAF.getLoanTime()%>");
		
		
		document.forms(0).Cell1.S(6,18,0,"<%=bankName%>");
		
		document.forms(0).Cell1.S(6,21,0,"<%=houseAddr%>");
		
		document.forms(0).Cell1.S(6,19,0,"<%=loanstartdate%>");
		
		document.forms(0).Cell1.S(6,20,0,"<%=loanreturnedbyfundTaAF.getReturnMonth()%>");
		document.forms(0).Cell1.S(7,22,0,bizDateYear+"年"+bizDateMonth+"月"+bizDate+"日");
	
	document.forms(0).Cell1.S(5,22,0,userName);		
	document.forms(0).Cell1.ReDraw();
   	document.forms(0).Cell1.AllowExtend=false;
   	document.forms(0).Cell1.AllowDragdrop=false;
	//document.forms(0).Cell1.WorkbookReadonly=true;	
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
	function back(){
	document.location="loanreturnedbyfundTbShowAC.do";
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
					<td>
						<input type="button" name="print" value="打印预览"
							onclick="printPreview();" />
					</td>
					<td>
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1">
					</td>
					<td>
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入">
					</td>
					<td>
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
					</td>
					<td>
						<INPUT id="Button3" onclick="back();" type="button" value=" 返回 ">
					</td>
			</table>
		</form>
	</body>
</html>

