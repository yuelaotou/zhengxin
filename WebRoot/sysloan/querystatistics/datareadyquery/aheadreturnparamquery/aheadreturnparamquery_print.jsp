<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ page import="org.xpup.hafmis.sysloan.querystatistics.datareadyquery.aheadreturnparamquery.form.*" %>
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
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/report/aheadreturnparamquery.cll","");
	  var loanBankId;//银行
	  var aheadReturnAfter;//1、提前还款后
	  var partReturnMonthLT;//2、还款时间小于多少月
	  var isPartAheadReturn;//2、是否允许部分提前还款
	  var allReturnMonthLT;//3、还款时间小于多少月
	  var isAllReturn;//3、是否允许一次性结清还款
	  var leastReturnMoney;//4、最低还款金额
	  var mostAheadReturnYearTimes;//5、年度内最多允许提前还款次数
	  var mostAheadReturnTimes;//6、贷款期限内最多允许提前还款次数
	  var isAccept;//7、提前还款是否收取手续费
	  var aheadReturnMoneyPercent;//7、提前还款额百分比
	  var money;//7、按额
	  
	
	<%
    		AheadReturnParamQueryAF aheadReturnParamQueryAF=(AheadReturnParamQueryAF)request.getSession().getAttribute("aheadReturnParamQueryAF");	    
 	%>		
				loanBankId="<%=aheadReturnParamQueryAF.getAheadReturnParamQueryDTO().getTemp_loanBankId()%>";			
				aheadReturnAfter="<%=aheadReturnParamQueryAF.getAheadReturnParamQueryDTO().getAheadReturnAfter()%>";	
				partReturnMonthLT="<%=aheadReturnParamQueryAF.getAheadReturnParamQueryDTO().getPartReturnMonthLT()%>";	
				isPartAheadReturn="<%=aheadReturnParamQueryAF.getAheadReturnParamQueryDTO().getIsPartAheadReturn()%>";	
				allReturnMonthLT="<%=aheadReturnParamQueryAF.getAheadReturnParamQueryDTO().getAllReturnMonthLT()%>";	
				isAllReturn="<%=aheadReturnParamQueryAF.getAheadReturnParamQueryDTO().getIsAllReturn()%>";	
				leastReturnMoney="<%=aheadReturnParamQueryAF.getAheadReturnParamQueryDTO().getLeastReturnMoney()%>";	
				mostAheadReturnYearTimes="<%=aheadReturnParamQueryAF.getAheadReturnParamQueryDTO().getMostAheadReturnYearTimes()%>";	
				mostAheadReturnTimes="<%=aheadReturnParamQueryAF.getAheadReturnParamQueryDTO().getMostAheadReturnTimes()%>";
				isAccept="<%=aheadReturnParamQueryAF.getAheadReturnParamQueryDTO().getIsAccept()%>";
				aheadReturnMoneyPercent="<%=aheadReturnParamQueryAF.getAheadReturnParamQueryDTO().getAheadReturnMoneyPercent()%>";
				money="<%=aheadReturnParamQueryAF.getAheadReturnParamQueryDTO().getMoney()%>";
				
				document.forms(0).Cell1.S(3,3,0,loanBankId);
				if(aheadReturnAfter==1){
					document.forms(0).Cell1.S(3,4,0,'保持原来月还款额');
				}
				if(aheadReturnAfter==2){
					document.forms(0).Cell1.S(3,4,0,'保持剩余期限');
				}
				if(aheadReturnAfter==3){
					document.forms(0).Cell1.S(3,4,0,'允许改变剩余期限');
				}
				document.forms(0).Cell1.S(3,5,0,partReturnMonthLT);
				if(isPartAheadReturn==1){
					document.forms(0).Cell1.S(7,5,0,'不允许');
				}
				if(isPartAheadReturn==2){
					document.forms(0).Cell1.S(7,5,0,'允许');
				}
				document.forms(0).Cell1.S(3,6,0,allReturnMonthLT);
				if(isAllReturn==1){
					document.forms(0).Cell1.S(8,6,0,'不允许');
				}
				if(isAllReturn==2){
					document.forms(0).Cell1.S(8,6,0,'允许');
				}
				document.forms(0).Cell1.S(5,7,0,leastReturnMoney);
				document.forms(0).Cell1.S(5,8,0,mostAheadReturnYearTimes);
				document.forms(0).Cell1.S(6,9,0,mostAheadReturnTimes);
				if(isAccept==3){
					document.forms(0).Cell1.S(4,10,0,'否');
				}
				if(isAccept==4){
					document.forms(0).Cell1.S(4,10,0,'是');
				}
				document.forms(0).Cell1.S(7,10,0,aheadReturnMoneyPercent);
				document.forms(0).Cell1.S(10,10,0,money);
				
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

