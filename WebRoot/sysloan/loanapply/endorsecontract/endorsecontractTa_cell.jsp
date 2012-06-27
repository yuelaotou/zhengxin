<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page  import="java.lang.*" import="java.util.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaAF" %>
<%@ page import="org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS" %>
<%@ page import="java.math.BigDecimal" %>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
 %>
<html>
  <head>
  <title>打印借款合同信息</title>
  <script src="<%=path%>/js/common.js">
</script>
  </head>
  
<script type="text/javascript">
	function load(){	
	loginReg();
	//加载模板文件..
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/report/loanContract_1.cll","");

	<%
	 EndorsecontractTaAF endorsecontractTaAF = new EndorsecontractTaAF();
	 endorsecontractTaAF=(EndorsecontractTaAF)request.getSession().getAttribute("printendorsecontractTaAF");
	 String userName=(String)request.getAttribute("userName");
	 String plbizDate=(String)request.getAttribute("plbizDate");
	 String contractId = endorsecontractTaAF.getContractId();
	 if(contractId == null){
	 contractId="";
	 }
	 String bankName = endorsecontractTaAF.getBankName();
	 if(bankName == null){
	 bankName="";
	 }
	 String debitter = endorsecontractTaAF.getDebitter();
	 if(debitter == null){
	 debitter="";
	 }
	 String certificateNum = endorsecontractTaAF.getCertificateNum();
	 if(certificateNum == null){
	 certificateNum="";
	 }
	 String term = endorsecontractTaAF.getTerm();
	 if(term == null){
	 term="";
	 }
	 String creditType = endorsecontractTaAF.getCreditType();
	 if(creditType == null){
	 creditType="";
	 }
	 String debitMoneyStaDate = endorsecontractTaAF.getDebitMoneyStaDate();
	 if(debitMoneyStaDate == null){
	 debitMoneyStaDate="";
	 }
	 String entruster = endorsecontractTaAF.getEntruster();
	 if(entruster == null){
	 entruster="";
	 }
	 String assurer = endorsecontractTaAF.getAssurer();
	 if(assurer == null){
	 assurer="";
	 }
	 String certificateType = endorsecontractTaAF.getCertificateType();
	 if(certificateType == null){
	 certificateType="";
	 }
	 String debitMoney = endorsecontractTaAF.getDebitMoney();
	 if(debitMoney == null){
	 debitMoney="";
	 }
	 String assorgId = endorsecontractTaAF.getAssistantOrgId();
	 if(assorgId==null){
	  assorgId="";
	 }
	 String monthInterest = endorsecontractTaAF.getMonthInterest();
	 if(monthInterest == null){
	 monthInterest="";
	 }
	 String contractSureDate = endorsecontractTaAF.getContractSureDate();
	 if(contractSureDate == null){
	 contractSureDate="";
	 }
	 String debitMoneyEndDate = endorsecontractTaAF.getDebitMoneyEndDate();
	 if(debitMoneyEndDate == null){
	 debitMoneyEndDate="";
	 }
	%>
		
		document.forms(0).Cell1.S(13,7,0,"<%=bankName %>");
		document.forms(0).Cell1.S(13,8,0,"<%=debitter %>");
		
		document.forms(0).Cell1.S(16,15,0,"<%=term%>");
		document.forms(0).Cell1.S(19,15,0,"<%=Integer.parseInt(term)*12%>");
		
		
		
		
		
		
		document.forms(0).Cell1.S(7,10,0,"<%=assurer %>");
		document.forms(0).Cell1.s(4,14,0,ChangeToBig("<%=debitMoney%>"));
		document.forms(0).Cell1.S(25,17,0,"<%=(new BigDecimal(monthInterest.substring(0,monthInterest.length()-1))).multiply(new BigDecimal(10))%>");
		document.forms(0).Cell1.S(28,19,0,"<%=endorsecontractTaAF.getCorpusInterest() %>");
		document.forms(0).Cell1.S(26,15,0,"<%=contractSureDate %>".substring(0,4));
		document.forms(0).Cell1.S(29,15,0,"<%=contractSureDate %>".substring(4,6));
		document.forms(0).Cell1.S(4,16,0,"<%=contractSureDate %>".substring(6,8));
		document.forms(0).Cell1.S(8,16,0,"<%=debitMoneyEndDate %>".substring(0,4));
		document.forms(0).Cell1.S(12,16,0,"<%=debitMoneyEndDate %>".substring(4,6));
		document.forms(0).Cell1.S(15,16,0,"<%=debitMoneyEndDate %>".substring(6,8));
		document.forms(0).Cell1.S(4,11,0,"<%=plbizDate%>");
		document.forms(0).Cell1.S(15,3,0,"<%=plbizDate%>".substring(0,4));
		document.forms(0).Cell1.S(21,3,0,"<%=contractId%>");
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
						<INPUT id="Button3" onclick="location.href='to_EndorsecontractTeShowAC.do'"
							type="button" value=" 返回 " />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
