<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.lang.*" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.*" %>
<%@ include file="/checkUrl.jsp"%>
<%

   String path=request.getContextPath();
 %>
<html>
  <head>
  <script src="<%=path%>/js/common.js">
</script>
  </head>
  <script type="text/javascript">
	function load(){
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/accounthandle/clearaccount/report/21.cll","");
	<%
    		ClearAccountBalanceForm f=(ClearAccountBalanceForm)request.getAttribute("clearAccountBalanceForm");
    		String bankname=(String)request.getAttribute("bankname");
    		String office=(String)request.getAttribute("office");
    		String setDate = (String)request.getAttribute("setDate");
    		String userName = (String)request.getAttribute("userName");
	%>	
		//中心
		//document.forms(0).Cell1.S(1,4,0,"<%=office%>");
		//银行
		var bank="<%=bankname%>";
		
		if(bank=="null"){
			bank=""
		}	
		document.forms(0).Cell1.S(2,4,0,bank);		   
		//时间
		document.forms(0).Cell1.S(5,4,0,"<%=setDate.substring(0,4)%>年"+"<%=setDate.substring(4,6)%>月");
		document.forms(0).Cell1.S(11,4,0,"<%=userName%>");
		//贷
		document.forms(0).Cell1.S(2,7,0,"<%=f.getOrg_payment_count() %>");
		document.forms(0).Cell1.S(4,7,0,"<%=f.getOrg_payment_balance() %>");
		document.forms(0).Cell1.S(2,8,0,"<%=f.getOrg_add_payment_count() %>");
		document.forms(0).Cell1.S(4,8,0,"<%=f.getOrg_add_payment_balance() %>");
		document.forms(0).Cell1.S(2,9,0,"<%=f.getEmp_add_payment_count() %>");
		document.forms(0).Cell1.S(4,9,0,"<%=f.getEmp_add_payment_balance() %>");
		//document.forms(0).Cell1.S(4,9,0,"<%=f.getEmp_add_payment_count() %>");
		//document.forms(0).Cell1.S(6,9,0,"<%=f.getEmp_add_payment_balance() %>");
		document.forms(0).Cell1.S(2,10,0,"<%=f.getOrg_over_pay_count() %>");
		document.forms(0).Cell1.S(4,10,0,"<%=f.getOrg_over_paybalance() %>");
		document.forms(0).Cell1.S(2,11,0,"<%=f.getXiaoji1_credit_count() %>");
		document.forms(0).Cell1.S(4,11,0,"<%=f.getXiaoji1_credit_paybalance() %>");
		document.forms(0).Cell1.S(2,12,0,"<%=f.getAdjustaccout_credit_count() %>");
		document.forms(0).Cell1.S(4,12,0,"<%=f.getAdjustaccout_credit_paybalance() %>");
		document.forms(0).Cell1.S(2,13,0,"<%=f.getOrg_tranin_count() %>");
		document.forms(0).Cell1.S(4,13,0,"<%=f.getOrg_tranin_paybalance() %>");
		
		document.forms(0).Cell1.S(2,15,0,"<%=f.getClearinteres_count() %>");
		document.forms(0).Cell1.S(4,15,0,"<%=f.getClearinteres_paybalance() %>");
		document.forms(0).Cell1.S(2,16,0,"<%=f.getClearinteres_count() %>");
		document.forms(0).Cell1.S(4,16,0,"<%=f.getClearinteres_paybalance_1() %>");
		document.forms(0).Cell1.S(2,17,0,"<%=f.getClearinteres_count() %>");
		document.forms(0).Cell1.S(4,17,0,"<%=f.getXiaoji3_credit_paybalance() %>");
		
		document.forms(0).Cell1.S(2,14,0,"<%=f.getXiaoji2_credit_count() %>");
		document.forms(0).Cell1.S(4,14,0,"<%=f.getXiaoji2_credit_paybalance() %>");
		document.forms(0).Cell1.S(2,18,0,"<%=f.getCredit_count() %>");
		document.forms(0).Cell1.S(4,18,0,"<%=f.getCredit_paybalance() %>");
		//document.forms(0).Cell1.S(4,14,0,"<%=f.getCredit_count() %>");
		//document.forms(0).Cell1.S(6,14,0,"<%=f.getCredit_paybalance() %>");
		//document.forms(0).Cell1.S(4,16,0,"<%=f.getPre_rest_paybalance() %>");
		//document.forms(0).Cell1.S(4,17,0,"<%=f.getCur_rest_paybalance() %>");
		
		//借
		document.forms(0).Cell1.S(8,13,0,"<%=f.getOrg_tranout_count() %>");
		document.forms(0).Cell1.S(10,13,0,"<%=f.getOrg_tranout_balance() %>");
		document.forms(0).Cell1.S(8,7,0,"<%=f.getPick_count() %>");
		document.forms(0).Cell1.S(10,7,0,"<%=f.getPick_balance() %>");
		document.forms(0).Cell1.S(8,8,0,"<%=f.getPick_payload_count() %>");
		document.forms(0).Cell1.S(10,8,0,"<%=f.getPick_payload_balance() %>");
		document.forms(0).Cell1.S(8,9,0,"<%=f.getPick_payload_count_ld() %>");
		document.forms(0).Cell1.S(10,9,0,"<%=f.getPick_payload_balance_ld() %>");
		
		document.forms(0).Cell1.S(8,10,0,"<%=f.getPick_count_xiaohu() %>");
		document.forms(0).Cell1.S(10,10,0,"<%=f.getPick_balance_xiaohu() %>");
		document.forms(0).Cell1.S(8,11,0,"<%=f.getDebit_count_xiaoji()%>");
		document.forms(0).Cell1.S(10,11,0,"<%=f.getDebit_paybalance_xiaoji() %>");
		document.forms(0).Cell1.S(11,13,0,"<%=f.getOrg_tranout_count() %>");
		document.forms(0).Cell1.S(13,13,0,"<%=f.getOrg_tranout_balance() %>");
		
		document.forms(0).Cell1.S(8,14,0,"<%=f.getDebit_count_xiaoji_1() %>");
		document.forms(0).Cell1.S(10,14,0,"<%=f.getDebit_paybalance_xiaoji_1() %>");
		
		
		
		document.forms(0).Cell1.S(8,12,0,"<%=f.getAdjustaccout_debit_count() %>");
		document.forms(0).Cell1.S(10,12,0,"<%=f.getAdjustaccout_debit_paybalance() %>");
		document.forms(0).Cell1.S(8,18,0,"<%=f.getDebit_count() %>");
		document.forms(0).Cell1.S(10,18,0,"<%=f.getDebit_paybalance() %>");
		
		
		document.forms(0).Cell1.S(2,19,0,"<%=f.getPre_rest_paybalance() %>");
		document.forms(0).Cell1.S(5,19,0,"<%=f.getPre_debit_paybalance() %>");
		document.forms(0).Cell1.S(10,19,0,"<%=f.getGjjYuE()%>");
		document.forms(0).Cell1.S(10,20,0,"<%=f.getCur_rest_paybalance() %>");
		document.forms(0).Cell1.S(2,20,0,"<%=f.getDebit_interest_paybalance()%>");
		document.forms(0).Cell1.S(5,20,0,"<%=f.getOrg_overpay_sum()%>");
		
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
