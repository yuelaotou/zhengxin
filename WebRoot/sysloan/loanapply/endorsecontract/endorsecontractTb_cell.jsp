<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.lang.*" import="java.util.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="java.math.BigDecimal"%>

<%
	String path = request.getContextPath();
	String data_1 = "";
	String data_2 = "";
	String data_3 = "";
	String data_4 = "";
	String data_5 = "";
	String data_6 = "";
	String data_7 = "";
	String data_8 = "";
	String data_9 = "";
	String data_10 = "";
	String data_11 = "";
	String data_12 = "";
	String data_13 = "";
	String data_14 = "";
	String data_15 = "";
	String data_16 = "";
	String data_17 = "";
	String data_18 = "";
	String data_19 = "";
	String data_20 = "";
	String data_21 = "";
	String data_22 = "";
%>
<html>
	<head>
		<title>打印抵押合同信息</title>
		<script src="<%=path%>/js/common.js">
</script>
	</head>

	<script type="text/javascript">
  function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/report/newloan1.cll","");
	   
	<%
	 EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
	 endorsecontractTbAF=(EndorsecontractTbAF)request.getSession().getAttribute("printendorsecontractTbAF");
	 String userName=(String)request.getAttribute("userName");
	 String plbizDate=(String)request.getAttribute("plbizDate");
	 String contractId = endorsecontractTbAF.getContractId();
	 
	 
	 if(endorsecontractTbAF.getBorrower()!=null){
	 	
	    if(endorsecontractTbAF.getBorrower().getBorrowerName() != null&&!"".equals(endorsecontractTbAF.getBorrower().getBorrowerName())){
		 	data_1=endorsecontractTbAF.getBorrower().getBorrowerName();
		 }
		 if(endorsecontractTbAF.getBorrower().getContractId() != null&&!"".equals(endorsecontractTbAF.getBorrower().getContractId())){
		 	data_2=endorsecontractTbAF.getBorrower().getContractId();
		 }
	     if(endorsecontractTbAF.getSex() != null&&!"".equals(endorsecontractTbAF.getSex())){
		 	data_3=endorsecontractTbAF.getSex();
		 }
		 if(endorsecontractTbAF.getBorrower().getAge() != null&&!"".equals(endorsecontractTbAF.getBorrower().getAge())){
		 	data_4=endorsecontractTbAF.getBorrower().getAge().toString();
		 }
		 if(endorsecontractTbAF.getBorrower().getCardNum() != null&&!"".equals(endorsecontractTbAF.getBorrower().getCardNum())){
		 	data_5=endorsecontractTbAF.getBorrower().getCardNum();
		 }
		 if(endorsecontractTbAF.getBorrower().getOrgName() != null&&!"".equals(endorsecontractTbAF.getBorrower().getOrgName())){
		 	data_6=endorsecontractTbAF.getBorrower().getOrgName();
		 }
		 if(endorsecontractTbAF.getBorrower().getOrgAddr() != null&&!"".equals(endorsecontractTbAF.getBorrower().getOrgAddr())){
		 	data_7=endorsecontractTbAF.getBorrower().getOrgAddr();
		 }
		 if(endorsecontractTbAF.getBorrower().getOrgTel() != null&&!"".equals(endorsecontractTbAF.getBorrower().getOrgTel())){
		 	data_8=endorsecontractTbAF.getBorrower().getOrgTel();
		 }
		 if(endorsecontractTbAF.getBorrower().getHomeMobile() != null&&!"".equals(endorsecontractTbAF.getBorrower().getHomeMobile())){
		 	data_8=endorsecontractTbAF.getBorrower().getHomeMobile();
		 }
		 if(endorsecontractTbAF.getBorrower().getHomeMobile() != null&&!"".equals(endorsecontractTbAF.getBorrower().getHomeMobile())&&
		  	endorsecontractTbAF.getBorrower().getHomeMobile() != null&&!"".equals(endorsecontractTbAF.getBorrower().getHomeMobile())){
		 	data_8=endorsecontractTbAF.getBorrower().getOrgTel()+" "+endorsecontractTbAF.getBorrower().getHomeMobile();
		 }
		 if(endorsecontractTbAF.getBorrowerAcc().getLoanMoney() != null){
		 	data_17=endorsecontractTbAF.getBorrowerLoanInfo().getLoanMoney().toString();
		 }
		 if(endorsecontractTbAF.getLoanContract() != null&&!"".equals(endorsecontractTbAF.getLoanContract().getAgreementDate())){
		 	data_18=endorsecontractTbAF.getLoanContract().getAgreementDate().substring(0,4);
		  	data_19=endorsecontractTbAF.getLoanContract().getAgreementDate().substring(4,6);
		  	data_20=endorsecontractTbAF.getLoanContract().getAgreementDate().substring(6,8);
		 }
		 List list = endorsecontractTbAF.getList();
		 for(int j=0;j<list.size();j++){
		 	EndorsecontractTbAF temp_endorsecontractTbAF = (EndorsecontractTbAF)list.get(j);

		 	data_9 = temp_endorsecontractTbAF.getPledgeMatterName();
		 	if(data_9 == null){
			 	data_9="";
			}
		 	data_10 = temp_endorsecontractTbAF.getPaperNum();
		 	if(data_10 == null){
		 		data_10="";
		 	}
		
		 	data_15 = temp_endorsecontractTbAF.getPledgeAddr();
		 	if(data_15 == null){
		  		data_15="";
		 	}
		
	 		data_12 = endorsecontractTbAF.getPledgeValue();
		 	if(data_12 == null){
		 		data_12="";
		 	}
		  	data_13 = endorsecontractTbAF.getRebate();
		 	if(data_13 == null){
		 		data_13="";
		 	}
		 	if(data_13 != null&&!"0".equals(data_13)&&!"".equals(data_13)){
		 		data_22=String.valueOf(new BigDecimal(data_13).multiply(new BigDecimal("100")));
	 			data_22=data_22+"%";
		 	}
		 	if(!"".equals(data_12)&&!"".equals(data_13)&&!"0".equals(data_13)&&!"".equals(data_13)){
		 		data_14=String.valueOf((new BigDecimal(data_12)).multiply(new BigDecimal(data_13)));
		 	}else{
		 		data_14=data_12;
		 	}
		
		 	data_11 = temp_endorsecontractTbAF.getArea();
	 		if(data_11 == null){
		 		data_11="";
		 	}
	 	}
	 }%>
	
		document.forms(0).Cell1.S(8,7,0,"<%=data_1%>");
		document.forms(0).Cell1.S(9,8,0,"<%=data_2%>");
		document.forms(0).Cell1.S(5,12,0,"<%=data_1%>");
		document.forms(0).Cell1.S(10,12,0,"<%=data_3%>");
		document.forms(0).Cell1.S(14,12,0,"<%=data_4%>");
		document.forms(0).Cell1.S(19,12,0,"<%=data_5%>");	
		document.forms(0).Cell1.S(5,13,0,"<%=data_6%>");
		document.forms(0).Cell1.S(15,13,0,"<%=data_7%>");	
		document.forms(0).Cell1.S(24,13,0,"<%=data_8%>");
		
		document.forms(0).Cell1.S(3,15,0,"<%=data_9%>");
		document.forms(0).Cell1.S(5,15,0,"<%=data_10%>");
		document.forms(0).Cell1.S(8,15,0,"<%=data_11%>");	
		document.forms(0).Cell1.S(11,15,0,"<%=data_12%>");
		document.forms(0).Cell1.S(15,15,0,"");
		document.forms(0).Cell1.S(18,15,0,"<%=data_12%>");		
		document.forms(0).Cell1.S(21,15,0,"<%=data_15%>");	
		document.forms(0).Cell1.S(16,17,0,ChangeToBig("<%=data_17%>"));
		document.forms(0).Cell1.S(25,17,0,"<%=data_18%>");	
		document.forms(0).Cell1.S(28,17,0,"<%=data_19%>");		
		document.forms(0).Cell1.S(30,17,0,"<%=data_20%>");	
		document.forms(0).Cell1.S(12,4,0,"<%=plbizDate%>".substring(0,4));
		document.forms(0).Cell1.S(18,4,0,"<%=contractId%>");
		document.forms(0).Cell1.S(3,18,0,"，抵押期限自<%=data_18%>年<%=data_19%>月<%=data_20%>日起至主合同项下贷款本息全部还清止。");
			
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
						<INPUT id="Button3"
							onclick="location.href='to_EndorsecontractTeShowAC.do'"
							type="button" value=" 返回 " />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
