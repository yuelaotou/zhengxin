<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ page import="org.xpup.hafmis.sysloan.querystatistics.datareadyquery.paramquery.form.*" %>
<%@ include file="/checkUrl.jsp"%>
<%

   String path=request.getContextPath();
 %>
<html>
  <head>
  <script src="<%=path%>/js/common.js">
</script>
  <script type="text/javascript">
	function load(){	

	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/report/paramquery.cll","");
	var loanBankId;//银行
	var kouAccMode;//1、扣款方式
	var decideDateMode;//2、定日方式
	var uniteDate;//2、统一日期
	var corpus;//3、正常本金
	var interest;//3、正常利息
	var overdueCorpus;//3、逾期本金
	var overdueInterest;//3、逾期利息
	var punishInterest;//3、罚息
	var punishInterestRateMode;//4、罚息利率方式
	var punishInterestDateRate;//4、按罚息日利率
	var contractDateRate;//4、按合同日利率
	var moneyDateInterest;//4、按额每日利息
	var permitDateNum;//5、宽限天数
	var isRecord;//5、是否记罚息
	var commonMonthNum;//6、正常
	var attentionMonthNum;//6、关注
	var subMonthNum;//6、次级
	var shadinessMonthNum;//6、可疑
	var lossMonthNum;//6、损失
	var loanVipCheck;//7、审批贷款
	var endorseLoan;//7、签定贷款
	var currentRate;//8、活期利率
	var terminalRate;//8、死期利率
	var isAdopt;//9、是否采用新利率  
	<%
    	ParamQueryAF paramQueryAF=(ParamQueryAF)request.getSession().getAttribute("paramQueryAF");	    
 	%>			
 	loanBankId="<%=paramQueryAF.getParamQueryDTO().getTemp_loanBankId()%>";	
 	kouAccMode="<%=paramQueryAF.getParamQueryDTO().getKouAccMode()%>";	
 	decideDateMode="<%=paramQueryAF.getParamQueryDTO().getDecideDateMode()%>";	
 	uniteDate="<%=paramQueryAF.getParamQueryDTO().getUniteDate()%>";	
 	corpus="<%=paramQueryAF.getParamQueryDTO().getCorpus()%>";	
 	interest="<%=paramQueryAF.getParamQueryDTO().getInterest()%>";	
 	overdueCorpus="<%=paramQueryAF.getParamQueryDTO().getOverdueCorpus()%>";	
 	overdueInterest="<%=paramQueryAF.getParamQueryDTO().getOverdueInterest()%>";
 	punishInterest="<%=paramQueryAF.getParamQueryDTO().getPunishInterest()%>";
 	punishInterestRateMode="<%=paramQueryAF.getParamQueryDTO().getPunishInterestRateMode()%>";
 	punishInterestDateRate="<%=paramQueryAF.getParamQueryDTO().getPunishInterestDateRate()%>";
 	contractDateRate="<%=paramQueryAF.getParamQueryDTO().getContractDateRate()%>";
 	moneyDateInterest="<%=paramQueryAF.getParamQueryDTO().getMoneyDateInterest()%>";
 	permitDateNum="<%=paramQueryAF.getParamQueryDTO().getPermitDateNum()%>";
 	isRecord="<%=paramQueryAF.getParamQueryDTO().getIsRecord()%>";
 	commonMonthNum="<%=paramQueryAF.getParamQueryDTO().getCommonMonthNum()%>";
 	attentionMonthNum="<%=paramQueryAF.getParamQueryDTO().getAttentionMonthNum()%>";
 	subMonthNum="<%=paramQueryAF.getParamQueryDTO().getSubMonthNum()%>";
 	shadinessMonthNum="<%=paramQueryAF.getParamQueryDTO().getShadinessMonthNum()%>";
 	lossMonthNum="<%=paramQueryAF.getParamQueryDTO().getLossMonthNum()%>";
 	loanVipCheck="<%=paramQueryAF.getParamQueryDTO().getLoanVipCheck()%>";
 	endorseLoan="<%=paramQueryAF.getParamQueryDTO().getEndorseLoan()%>";
 	currentRate="<%=paramQueryAF.getParamQueryDTO().getCurrentRate()%>";
 	terminalRate="<%=paramQueryAF.getParamQueryDTO().getTerminalRate()%>";
 	isAdopt="<%=paramQueryAF.getParamQueryDTO().getIsAdopt()%>";
 	
 	document.forms(0).Cell1.S(3,3,0,loanBankId);
 	if(kouAccMode=='1'){
 		document.forms(0).Cell1.S(2,4,0,'足额扣款');
 	}
 	if(kouAccMode=='2'){
 		document.forms(0).Cell1.S(2,4,0,'全额扣款');
 	}
 	if(decideDateMode=='1'){
 		document.forms(0).Cell1.S(2,5,0,'按户定日');
 	}
 	if(decideDateMode=='2'){
 		document.forms(0).Cell1.S(2,5,0,'统一定日'+uniteDate+'号');
 	}
 	document.forms(0).Cell1.S(6,6,0,corpus);
 	document.forms(0).Cell1.S(9,6,0,interest);
 	document.forms(0).Cell1.S(12,6,0,overdueCorpus);
 	document.forms(0).Cell1.S(15,6,0,overdueInterest);
 	document.forms(0).Cell1.S(17,6,0,punishInterest);
 	if(punishInterestRateMode=='1'){
 		document.forms(0).Cell1.S(3,7,0,'按罚息日利率'+punishInterestDateRate+'计息');
 	}
 	if(punishInterestRateMode=='2'){
 		document.forms(0).Cell1.S(3,7,0,'按合同日利率'+contractDateRate+'%计息');
 	}
 	if(punishInterestRateMode=='3'){
 		document.forms(0).Cell1.S(3,7,0,'按额每日'+moneyDateInterest+'元计息');
 	}
 	document.forms(0).Cell1.S(3,8,0,permitDateNum);
 	if(isRecord=='0'){
 		document.forms(0).Cell1.S(10,8,0,'是');
 	}
 	if(isRecord=='1'){
 		document.forms(0).Cell1.S(10,8,0,'否');
 	}
 	document.forms(0).Cell1.S(6,9,0,commonMonthNum);
 	document.forms(0).Cell1.S(9,9,0,attentionMonthNum);
 	document.forms(0).Cell1.S(12,9,0,subMonthNum);
 	document.forms(0).Cell1.S(15,9,0,shadinessMonthNum);
 	document.forms(0).Cell1.S(18,9,0,lossMonthNum);
 	document.forms(0).Cell1.S(5,10,0,loanVipCheck);
 	document.forms(0).Cell1.S(8,10,0,endorseLoan);
 	document.forms(0).Cell1.S(5,11,0,currentRate);
 	document.forms(0).Cell1.S(9,11,0,terminalRate);
 	if(isAdopt=='0'){
 		document.forms(0).Cell1.S(10,12,0,'是');
 	}
 	if(isAdopt=='1'){
 		document.forms(0).Cell1.S(10,12,0,'否');
 	}
 				
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
  <body onload = "load();" onContextmenu="return false"> 
    <form action="">
    <table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
<tr>
<input type="button" name="print" value = "打印预览" onclick = "printPreview();"/>
<INPUT id="Button1" onclick="printsheet();" type="button" value=" 打印 " name="Button1">
<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1">
<INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1">
<INPUT id="Button3" style="WIDTH: 90px" onclick="Button3_onclick()" type="button" value="页面设置">
<INPUT id="Button3" style="WIDTH: 90px" onclick="Button4_onclick()" type="button" value="查找对话框">
<INPUT id="Button3" style="WIDTH: 90px" onclick="Button5_onclick()" type="button" value="excel导入">
<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">	
</table>
</form>
  </body>
</html>

