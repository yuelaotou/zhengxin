<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTbNewAF"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTcNewAF"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTdNewAF"%>
	<%@ page
	import="java.math.*"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String url = (String) request.getAttribute("url");
	if (url == null) {
		url = "loanapplyteshowAC.do";
	}
%>
<html>
	<head>
	</head>
	<script src="<%=path%>/js/common.js">
</script>
	<script type="text/javascript">
function load(){	
	//加载默版文件..
	document.forms(0).Cell1.Login("沈阳金软科技有限公司","","13100104509","0740-1662-7775-3003"); 
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/report/loanapplytable.cll","");
	
	<%
		String opertname=(String)request.getAttribute("opertname");
		String plbizDate=(String)request.getAttribute("time");
		String year = plbizDate.substring(0,4);
		String month = plbizDate.substring(4,6);
		String day = plbizDate.substring(6,8); 
		LoanapplyNewAF loanapplynewAF=(LoanapplyNewAF)request.getAttribute("loanapplynewAF");
		LoanapplyTcNewAF loanapplytcnewAF=(LoanapplyTcNewAF)request.getAttribute("loanapplytcnewAF");
		LoanapplyTdNewAF loanapplytdnewAF=(LoanapplyTdNewAF)request.getAttribute("loanapplytdnewAF");
		LoanapplyTbNewAF loanapplytbnewAF=(LoanapplyTbNewAF)request.getAttribute("loanapplytbnewAF");
	%>
	var moneybig="<%=loanapplytdnewAF.getLoanMoney()%>";
	var money=ChangeToBig(moneybig);
	var houseType = "<%=loanapplytcnewAF.getHouseType()%>";
	document.forms(0).Cell1.S(4,3,0,"<%=loanapplynewAF.getBorrower().getBorrowerName()%>");
	document.forms(0).Cell1.S(9,3,0,"<%=loanapplynewAF.getSexc()%>");
	document.forms(0).Cell1.S(13,3,0,"<%=loanapplynewAF.getBorrower().getAge().toString()%>");
	document.forms(0).Cell1.S(18,3,0,"<%=loanapplynewAF.getBorrower().getCardNum()%>");
	
	document.forms(0).Cell1.S(5,4,0,"<%=loanapplynewAF.getBorrower().getOrgName()%>");
	document.forms(0).Cell1.S(18,4,0,"<%=loanapplynewAF.getBorrower().getBusiness()%>");
	document.forms(0).Cell1.S(24,4,0,"<%=loanapplynewAF.getBorrower().getTitle() + ""%>");
	
	document.forms(0).Cell1.d(5,5,0,"<%=loanapplynewAF.getBorrower().getMonthSalary()%>");
	document.forms(0).Cell1.S(12,5,0,"<%=loanapplynewAF.getBorrower().getOrgTel()%>");
	document.forms(0).Cell1.S(18,5,0,"<%=loanapplynewAF.getBorrower().getHouseTel()%>");
	document.forms(0).Cell1.S(24,5,0,"<%=loanapplynewAF.getBorrower().getHomeMobile()%>");
	
	document.forms(0).Cell1.d(24,6,0,"<%=loanapplynewAF.getBorrower().getMonthPay()%>");
	document.forms(0).Cell1.S(5,6,0,formatTen("<%=loanapplynewAF.getBorrower().getOrgId()%>")+"<%=loanapplynewAF.getBorrower().getOrgId()%>");
	document.forms(0).Cell1.S(11,6,0,format("<%=loanapplynewAF.getBorrower().getEmpId()%>")+"<%=loanapplynewAF.getBorrower().getEmpId()%>");
	
	//辅助借款人A
	var	telA = "";
	if("<%=loanapplynewAF.getBorrower().getContactAOrgTel()%>"!=""&&"<%=loanapplynewAF.getBorrower().getContactAOrgTel()%>"!="null"){
		telA += "<%=loanapplynewAF.getBorrower().getContactAOrgTel()%>,";
	}
	if("<%=loanapplynewAF.getBorrower().getContactAHouseTel()%>"!=""&&"<%=loanapplynewAF.getBorrower().getContactAHouseTel()%>"!="null"){
		telA += "<%=loanapplynewAF.getBorrower().getContactAHouseTel()%>,";
	}
	if("<%=loanapplynewAF.getBorrower().getContactAMobile()%>"!=""&&"<%=loanapplynewAF.getBorrower().getContactAMobile()%>"!="null"){
		telA += "<%=loanapplynewAF.getBorrower().getContactAMobile()%>,";
	}
	if(telA!=""){
		telA = telA.substr(0,telA.lastIndexOf(","));
	}
	document.forms(0).Cell1.S(2,16,0,"<%=loanapplynewAF.getBorrower().getContactAName()%>");
	document.forms(0).Cell1.S(5,16,0,"<%=loanapplynewAF.getBorrower().getRelationA()%>");
	document.forms(0).Cell1.S(9,16,0,"<%=loanapplynewAF.getBorrower().getContactAOrgName()%>");
	document.forms(0).Cell1.S(18,16,0,telA);
	
	//辅助借款人B
	var	telB = "";
	if("<%=loanapplynewAF.getBorrower().getContactBOrgTel()%>"!=""&&"<%=loanapplynewAF.getBorrower().getContactBOrgTel()%>"!="null"){
		telB += "<%=loanapplynewAF.getBorrower().getContactBOrgTel()%>,";
	}
	if("<%=loanapplynewAF.getBorrower().getContactBHouseTel()%>"!=""&&"<%=loanapplynewAF.getBorrower().getContactBHouseTel()%>"!="null"){
		telB += "<%=loanapplynewAF.getBorrower().getContactBHouseTel()%>,";
	}
	if("<%=loanapplynewAF.getBorrower().getContactBMobile()%>"!=""&&"<%=loanapplynewAF.getBorrower().getContactBMobile()%>"!="null"){
		telB += "<%=loanapplynewAF.getBorrower().getContactBMobile()%>,";
	}
	if(telB!=""){
		telB = telB.substr(0,telB.lastIndexOf(","));
	}
	document.forms(0).Cell1.S(2,18,0,"<%=loanapplynewAF.getBorrower().getContactBName()%>");
	document.forms(0).Cell1.S(5,18,0,"<%=loanapplynewAF.getBorrower().getRelationB()%>");
	document.forms(0).Cell1.S(9,18,0,"<%=loanapplynewAF.getBorrower().getContactBOrgName()%>");
	document.forms(0).Cell1.S(18,18,0,telB);
	//辅助借款人C
	var	telC = "";
	if("<%=loanapplynewAF.getBorrower().getContactCOrgTel()%>"!=""&&"<%=loanapplynewAF.getBorrower().getContactCOrgTel()%>"!="null"){
		telC += "<%=loanapplynewAF.getBorrower().getContactCOrgTel()%>,";
	}
	if("<%=loanapplynewAF.getBorrower().getContactCHouseTel()%>"!=""&&"<%=loanapplynewAF.getBorrower().getContactCHouseTel()%>"!="null"){
		telC += "<%=loanapplynewAF.getBorrower().getContactCHouseTel()%>,";
	}
	if("<%=loanapplynewAF.getBorrower().getContactCMobile()%>"!="null"&&"<%=loanapplynewAF.getBorrower().getContactCMobile()%>"!="null"){
		telC += "<%=loanapplynewAF.getBorrower().getContactCMobile()%>,";
	}
	if(telC!=""){
		telC = telC.substr(0,telC.lastIndexOf(","));
	}
	document.forms(0).Cell1.S(2,20,0,"<%=loanapplynewAF.getBorrower().getContactCName()%>");
	document.forms(0).Cell1.S(5,20,0,"<%=loanapplynewAF.getBorrower().getRelationC()%>");
	document.forms(0).Cell1.S(9,20,0,"<%=loanapplynewAF.getBorrower().getContactCOrgName()%>");
	document.forms(0).Cell1.S(18,20,0,telC);
	var orgRate = "<%=loanapplynewAF.getOrgRate()%>";
	var empRate = "<%=loanapplynewAF.getEmpRate()%>";
	if(orgRate!="")
		orgRate = parseFloat(orgRate*100)+"%";
	if(empRate!="")
		empRate = parseFloat(empRate*100)+"%";
	document.forms(0).Cell1.S(20,6,0,orgRate);
	document.forms(0).Cell1.S(20,7,0,empRate);
	
	document.forms(0).Cell1.d(5,8,0,"<%=loanapplynewAF.getBorrower().getAccBlnce()%>");
	document.forms(0).Cell1.S(12,8,0,"<%=loanapplynewAF.getBorrower().getEmpSt_()%>");
	
	
	document.forms(0).Cell1.S(4,9,0,"<%=loanapplytbnewAF.getName()%>");
	document.forms(0).Cell1.S(9,9,0,"<%=loanapplytbnewAF.getSex()%>");	
	document.forms(0).Cell1.S(13,9,0,"<%=loanapplytbnewAF.getAge()%>");	
	document.forms(0).Cell1.S(18,9,0,"<%=loanapplytbnewAF.getCardNum()%>");
	var orgRateAssist = "<%=loanapplytbnewAF.getOrgRate()%>";
	var empRateAssist = "<%=loanapplytbnewAF.getEmpRate()%>";
	if(orgRateAssist!="")
		orgRateAssist = parseFloat(orgRateAssist*100)+"%";
	if(empRateAssist!="")
		empRateAssist = parseFloat(empRateAssist*100)+"%";
	document.forms(0).Cell1.S(20,12,0,orgRateAssist);	
	document.forms(0).Cell1.S(20,13,0,empRateAssist);	
	
	document.forms(0).Cell1.S(5,10,0,"<%=loanapplytbnewAF.getOrgName()%>");
	document.forms(0).Cell1.S(18,10,0,"<%=loanapplytbnewAF.getBusiness()%>");
	document.forms(0).Cell1.S(24,10,0,"<%=loanapplytbnewAF.getTitle() + ""%>");
	
	document.forms(0).Cell1.d(5,11,0,"<%=loanapplytbnewAF.getMonthSalary()%>");
	document.forms(0).Cell1.S(12,11,0,"<%=loanapplytbnewAF.getOrgTel()%>");
	document.forms(0).Cell1.S(18,11,0,"<%=loanapplytbnewAF.getHouseTel()%>");
	document.forms(0).Cell1.S(24,11,0,"<%=loanapplytbnewAF.getHomeMobile()%>");
	if("<%=loanapplytbnewAF.getMonthPay()%>"==""){
		document.forms(0).Cell1.d(24,12,0,"0.00");
	}else{
		document.forms(0).Cell1.d(24,12,0,"<%=loanapplytbnewAF.getMonthPay()%>");
	}
	
	var empIdtb = "<%=loanapplytbnewAF.getEmpId()%>";
	var orgIdtb = "<%=loanapplytbnewAF.getOrgId()%>";
	if(empIdtb!=""){
		empIdtb = format(empIdtb)+empIdtb
	}
	if(orgIdtb!=""){
		orgIdtb = formatTen(orgIdtb)+orgIdtb;
	}
	document.forms(0).Cell1.S(11,12,0,empIdtb);
	document.forms(0).Cell1.S(5,12,0,orgIdtb);
	
	document.forms(0).Cell1.S(5,14,0,"<%=loanapplytbnewAF.getAccBlnce()%>");
	document.forms(0).Cell1.S(12,14,0,"<%=loanapplytbnewAF.getEmpSt()%>");
	
	document.forms(0).Cell1.S(5,22,0,"<%=loanapplytcnewAF.getBorrowerName()%>");
	document.forms(0).Cell1.S(18,22,0,"<%=loanapplytcnewAF.getOrgName()%>");
	
	document.forms(0).Cell1.S(5,23,0,"<%=loanapplytcnewAF.getHouseType()%>");
		if(houseType!=""){
		if(houseType=="商品房"){
			document.forms(0).Cell1.S(5,24,0,"<%=loanapplytcnewAF.getHouseAddr() %>");
			document.forms(0).Cell1.s(5,25,0,"<%=loanapplytcnewAF.getHouseArea() %>"+"㎡");
			document.forms(0).Cell1.d(18,25,0,"<%=loanapplytcnewAF.getHouseTotlePrice() %>");
			document.forms(0).Cell1.S(7,27,0,"<%=loanapplytcnewAF.getDeveloperPaybank() %>");
			document.forms(0).Cell1.S(7,28,0,"<%=loanapplytcnewAF.getDeveloperPaybankAAcc() %>");
		}else if((houseType=="二手房")){
			document.forms(0).Cell1.S(5,24,0,"<%=loanapplytcnewAF.getBargainorHouseAddr() %>");
			document.forms(0).Cell1.s(5,25,0,"<%=loanapplytcnewAF.getBargainorHouseArea() %>"+"㎡");
			document.forms(0).Cell1.d(18,25,0,"<%=loanapplytcnewAF.getBargainorTotlePrice() %>");
			document.forms(0).Cell1.S(7,27,0,"<%=loanapplytcnewAF.getBargainorPaybank() %>");
			document.forms(0).Cell1.S(7,28,0,"<%=loanapplytcnewAF.getBargainorPaybankAcc() %>");
		}
	}
	document.forms(0).Cell1.S(6,26,0,money);
	document.forms(0).Cell1.S(18,26,0,"<%=loanapplytdnewAF.getLoantimeLimit() %>"+"个月");
	
	
	document.forms(0).Cell1.S(17,34,0,"<%=year%>"+"年"+"<%=month%>"+"月"+"<%=day%>"+"日");
	var privilege_borrower_id = "<%=loanapplynewAF.getPrivilege_borrower_id()%>";
	if(privilege_borrower_id!='0'){
		document.forms(0).Cell1.S(26,1,0,"*");
	}
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
	<body onload="load();">
		<form action="">
			<table align="center">
				<tr>
					<OBJECT id="Cell1"
						style="LEFT:0px;WIDTH:800px;  TOP:0px;HEIGHT:500px"
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
						<INPUT id="Button3" onclick="javascript:document.URL='<%=url%>';"
							type="button" value=" 返回 ">
					</td>
			</table>
		</form>
	</body>
</html>
