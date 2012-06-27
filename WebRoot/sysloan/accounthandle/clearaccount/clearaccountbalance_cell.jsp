<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearAccountBalanceInfoAF" %>
<%@ include file="/checkUrl.jsp"%>
<%

   String path=request.getContextPath();
   String makePerson=(String)request.getAttribute("makePerson");
   String endDate=(String)request.getAttribute("endDate");
   String startDate=(String)request.getAttribute("startDate");
   
 %>
<html>
  <head>
  <script src="<%=path%>/js/common.js">
</script>
  </head>
  <script type="text/javascript">
	function load(){
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/accounthandle/report/clearaccountbalance.cll","");
	<%
    		ClearAccountBalanceInfoAF f=(ClearAccountBalanceInfoAF)request.getAttribute("clearAccountBalanceInfoAF");
    		    	
	%>	
		//银行
		var bank="<%=f.getLoanBankId()%>";
		if(bank=="null"){
			bank=""
		}	
		document.forms(0).Cell1.S(2,2,0,bank);		   
		//时间
		document.forms(0).Cell1.S(5,2,0,"<%=startDate%>"+"-"+"<%=endDate%>");
		//操作员
		document.forms(0).Cell1.S(9,2,0,"<%=makePerson%>");
		document.forms(0).Cell1.S(5,16,0,"<%=f.getCheckPerson() %>");
		document.forms(0).Cell1.S(8,16,0,"<%=f.getClearAccountPerson() %>");
		
		//贷
		//单笔
		document.forms(0).Cell1.d(5,5,0,"<%=f.getSingle_count() %>");
		document.forms(0).Cell1.d(6,5,0,"<%=f.getSingle_corpus() %>");
		document.forms(0).Cell1.d(7,5,0,"<%=f.getSingle_overdueCorpus() %>");
		document.forms(0).Cell1.d(8,5,0,"<%=f.getSingle_interest() %>");
		document.forms(0).Cell1.d(9,5,0,"<%=f.getSingle_punishInterest() %>");
		document.forms(0).Cell1.d(10,5,0,"<%=f.getSingle_occurMoney() %>");
		//部份
		document.forms(0).Cell1.d(5,6,0,"<%=f.getPart_count() %>");
		document.forms(0).Cell1.d(6,6,0,"<%=f.getPart_corpus() %>");
		document.forms(0).Cell1.d(7,6,0,"<%=f.getPart_overdueCorpus() %>");
		document.forms(0).Cell1.d(8,6,0,"<%=f.getPart_interest() %>");
		document.forms(0).Cell1.d(9,6,0,"<%=f.getPart_punishInterest() %>");
		document.forms(0).Cell1.d(10,6,0,"<%=f.getPart_occurMoney() %>");
		//一次性
		document.forms(0).Cell1.d(5,7,0,"<%=f.getAll_count() %>");
		document.forms(0).Cell1.d(6,7,0,"<%=f.getAll_corpus() %>");
		document.forms(0).Cell1.d(7,7,0,"<%=f.getAll_overdueCorpus() %>");
		document.forms(0).Cell1.d(8,7,0,"<%=f.getAll_interest() %>");
		document.forms(0).Cell1.d(9,7,0,"<%=f.getAll_punishInterest() %>");
		document.forms(0).Cell1.d(10,7,0,"<%=f.getAll_occurMoney() %>");
		//批量
		document.forms(0).Cell1.d(5,8,0,"<%=f.getBatch_count() %>");
		document.forms(0).Cell1.d(6,8,0,"<%=f.getBatch_corpus() %>");
		document.forms(0).Cell1.d(7,8,0,"<%=f.getBatch_overdueCorpus() %>");
		document.forms(0).Cell1.d(8,8,0,"<%=f.getBatch_interest() %>");
		document.forms(0).Cell1.d(9,8,0,"<%=f.getBatch_punishInterest() %>");
		document.forms(0).Cell1.d(10,8,0,"<%=f.getBatch_occurMoney() %>");
		//呆核
		/*document.forms(0).Cell1.d(2,9,0,"<%=f.getDestroy_count() %>");
		document.forms(0).Cell1.d(3,9,0,"<%=f.getDestroy_corpus() %>");
		document.forms(0).Cell1.d(4,9,0,"<%=f.getDestroy_overdueCorpus() %>");
		document.forms(0).Cell1.d(5,9,0,"<%=f.getDestroy_interest() %>");
		document.forms(0).Cell1.d(6,9,0,"<%=f.getDestroy_punishInterest() %>");
		document.forms(0).Cell1.d(7,9,0,"<%=f.getDestroy_occurMoney() %>");*/
		//收回
		/*document.forms(0).Cell1.d(2,10,0,"<%=f.getDestroyback_count() %>");
		document.forms(0).Cell1.d(3,10,0,"<%=f.getDestroyback_corpus() %>");
		document.forms(0).Cell1.d(4,10,0,"<%=f.getDestroy_overdueCorpus() %>");
		document.forms(0).Cell1.d(5,10,0,"<%=f.getDestroy_interest() %>");
		document.forms(0).Cell1.d(6,10,0,"<%=f.getDestroyback_punishInterest() %>");
		document.forms(0).Cell1.d(7,10,0,"<%=f.getDestroyback_occurMoney() %>");*/
		//挂账
		document.forms(0).Cell1.d(5,9,0,"<%=f.getOverpay_count() %>");
		document.forms(0).Cell1.d(10,9,0,"<%=f.getOverpay_occurMoney() %>");
		//错账调整
		document.forms(0).Cell1.d(5,10,0,"<%=f.getAdjustaccount_count() %>");
		document.forms(0).Cell1.d(6,10,0,"<%=f.getAdjustaccount_corpus() %>");
		document.forms(0).Cell1.d(7,10,0,"<%=f.getAdjustaccount_overdueCorpus() %>");
		document.forms(0).Cell1.d(8,10,0,"<%=f.getAdjustaccount_interest() %>");
		document.forms(0).Cell1.d(9,10,0,"<%=f.getAdjustaccount_punishInterest()%>");
		document.forms(0).Cell1.d(10,10,0,"<%=f.getAdjustaccount_occurMoney() %>");
		//借
		//发放
		document.forms(0).Cell1.d(2,5,0,"<%=f.getLoanaccord_count() %>");
		document.forms(0).Cell1.d(3,5,0,"<%=f.getLoanaccord_occurMoney() %>");
		//错账调整
		document.forms(0).Cell1.d(2,6,0,"<%=f.getAdjustaccount_loanaccordCount() %>");
		document.forms(0).Cell1.d(3,6,0,"<%=f.getAdjustaccount_loanaccordOccurMoney() %>");
		//保证金
		/*document.forms(0).Cell1.d(11,7,0,"<%=f.getDebit_bail_count() %>");
		document.forms(0).Cell1.d(12,7,0,"<%=f.getDebit_bail_occurMoney() %>");*/
		//提取利息
		/*document.forms(0).Cell1.d(11,8,0,"<%=f.getDebit_interest_count() %>");
		document.forms(0).Cell1.d(12,8,0,"<%=f.getDebit_interest_occurMoney() %>");*/
		//合计
		document.forms(0).Cell1.d(5,11,0,"<%=f.getCredit_count() %>");
		document.forms(0).Cell1.d(6,11,0,"<%=f.getCredit_corpus() %>");
		document.forms(0).Cell1.d(7,11,0,"<%=f.getCredit_overdueCorpus() %>");
		document.forms(0).Cell1.d(8,11,0,"<%=f.getCredit_interest() %>");
		document.forms(0).Cell1.d(9,11,0,"<%=f.getCredit_punishInterest() %>");
		document.forms(0).Cell1.d(10,11,0,"<%=f.getCredit_overpayOccurMoney() %>");	
		
		document.forms(0).Cell1.d(2,7,0,"<%=f.getDebit_count() %>");
		document.forms(0).Cell1.d(3,7,0,"<%=f.getDebit_occurMoney() %>");
		
		document.forms(0).Cell1.d(2,12,0,"<%=f.getInitialStages_corpus() %>");
		document.forms(0).Cell1.d(5,12,0,"<%=f.getCorpus_occurMoney().add(f.getOverdue_occurMoney()) %>");
		//document.forms(0).Cell1.d(8,12,0,"<%=f.getOverdue_occurMoney() %>");
		document.forms(0).Cell1.d(10,12,0,"<%=f.getFinal_corpus() %>");
		document.forms(0).Cell1.d(2,13,0,"<%=f.getInitialStages_interest() %>");
		document.forms(0).Cell1.d(5,13,0,"<%=f.getInterest_occurMoney() %>");		
		document.forms(0).Cell1.d(10,13,0,"<%=f.getFinal_interest() %>");
		document.forms(0).Cell1.d(2,14,0,"<%=f.getInitialStages_punishInterest() %>");
		document.forms(0).Cell1.d(5,14,0,"<%=f.getPunishInterest_occurMoney() %>");
		document.forms(0).Cell1.d(10,14,0,"<%=f.getFinal_punishInterest()%>");
		
		/*document.forms(0).Cell1.d(2,17,0,"<%=f.getInitialStages_destroyOccurMoney() %>");
		document.forms(0).Cell1.d(5,17,0,"<%=f.getDestroyOccurMoney() %>");
		document.forms(0).Cell1.d(8,17,0,"<%=f.getDestroybackOccurMoney() %>");
		document.forms(0).Cell1.d(11,17,0,"<%=f.getFinal_destroyOccurMoney() %>");*/
		
		document.forms(0).Cell1.d(2,15,0,"<%=f.getInitialStages_overpayOccurMoney() %>");		
		document.forms(0).Cell1.d(5,15,0,"<%=f.getOverpayOccurMoney() %>");
		document.forms(0).Cell1.d(10,15,0,"<%=f.getFinal_overpayOccurMoney() %>");
		
		document.forms(0).Cell1.d(10,15,0,"<%=f.getFinal_overpayOccurMoney() %>");
		document.forms(0).Cell1.d(10,15,0,"<%=f.getFinal_overpayOccurMoney() %>");
		document.forms(0).Cell1.d(10,15,0,"<%=f.getFinal_overpayOccurMoney() %>");
		
		/*document.forms(0).Cell1.d(2,19,0,"<%=f.getInitialStages_bailOccurMoney() %>");
		document.forms(0).Cell1.d(5,19,0,"<%=f.getBailOccurMoney()%>");
		document.forms(0).Cell1.d(8,19,0,"<%=f.getBailInterestOccurMoney()%>");
		document.forms(0).Cell1.d(11,19,0,"<%=f.getFinal_bailOccurMoney()%>");*/
					
				document.forms(0).Cell1.AllowExtend=false;
				document.forms(0).Cell1.AllowDragdrop=false;
				document.forms(0).Cell1.WorkbookReadonly=true;	
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
  <body onload = "load();"> 
    <form action="">
    <table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
<tr>
<td><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/></td>
<td><INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1"></td>
<td><INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1"></td>
<td><INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入"></td>
<td><INPUT id="Button3" onclick="location.href('javascript:history.back();')" type="button" value=" 返回 "></td>	
</table>
</form>
  </body>
</html>
