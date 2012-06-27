<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.lang.*" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.paymng.monthpay.form.*" %>
<%@ include file="/checkUrl.jsp"%>
<%

   String path=request.getContextPath();
   String url=(String)request.getAttribute("monthpayURL");
   if(url==null){
   	url="monthpayTaForwardUrlAC.do";
   } 
   if(url.equals("mx")){
   		url="javascript:history.back();";
   }
 %>
<html>
  <head>
  <script src="<%=path%>/js/common.js">
</script>
  </head>
  <script type="text/javascript">
	function load(){
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/paymng/report/pay_wsh.cll","");
	document.forms(0).Cell1.setSheetLabel(0,"第1页");//这句话必须放在加载文件的下边
	<%
    		MonthpayJYAF monthpayJYAF=(MonthpayJYAF)request.getAttribute("monthpayJYAF");
    		String seq_aa300=(String)request.getAttribute("seq_aa300");
    		String m=monthpayJYAF.getSumPay().toString();
	%>
		
		var sumPay = <%=monthpayJYAF.getSumPay()%>;
		var payWay="<%=monthpayJYAF.getPayWay()%>"
		var payKind="<%=monthpayJYAF.getPayKind()%>";
		var noteNum="<%=monthpayJYAF.getNoteNum()%>";
		if(noteNum=="null")
		{
		  noteNum="";
		}
		var chiao="0";
		var fin="0";
		sumPay=""+sumPay;
		var count=sumPay.indexOf(".",0);
		var length;
		len=sumPay.length;
		if(count==-1){
			length=len;
		}else{
			length=count;
		}
		for(var i=0;i<length;i++){//从后向前插入（从元起）
			var temp=sumPay.substring((length-i-1),length-i);
			document.forms(0).Cell1.S((53-i*2),12,0,temp);
		}
		if(count!=-1){//计算小数后面
			if((len-count)>2)//两位
			{
				chiao=sumPay.substring(count+1,sumPay.length-1);
				fin=sumPay.substring(count+2,sumPay.length);
				
				document.forms(0).Cell1.S(55,12,0,chiao);
				document.forms(0).Cell1.S(57,12,0,fin);
			}
			else{//只有一位
				chiao=sumPay.substring(count+1,sumPay.length)
				document.forms(0).Cell1.S(55,12,0,chiao);
				document.forms(0).Cell1.S(57,12,0,"0");
				}	
		}else{
			document.forms(0).Cell1.S(55,12,0,chiao);
			document.forms(0).Cell1.S(57,12,0,fin);
		}
		
		sumPay=ChangeToBig(sumPay);
		/**
		document.forms(0).Cell1.S(3,6,0,"<%=monthpayJYAF.getBankid() %>");
		document.forms(0).Cell1.S(3,7,0,"<%=monthpayJYAF.getBankname()%>");
		document.forms(0).Cell1.S(8,5,0,"<%=monthpayJYAF.getOffice()%>");
		document.forms(0).Cell1.S(8,6,0,"<%=monthpayJYAF.getCollbankname()%>");
		document.forms(0).Cell1.S(3,4,0,formatTen("<%=monthpayJYAF.getOrgid()%>")+"<%=monthpayJYAF.getOrgid()%>");
		document.forms(0).Cell1.S(3,5,0,"<%=monthpayJYAF.getName()%>");
		document.forms(0).Cell1.S(4,8,0,sumPay);
		document.forms(0).Cell1.S(1,12,0,"<%=monthpayJYAF.getUltimoPersonCounts()%>");
		document.forms(0).Cell1.S(2,12,0,"<%=monthpayJYAF.getUltimoPayMoney()%>");
		document.forms(0).Cell1.S(4,12,0,"<%=monthpayJYAF.getPersonCountsAdd()%>");
		document.forms(0).Cell1.S(5,12,0,"<%=monthpayJYAF.getPayMoneyAdd()%>");
		document.forms(0).Cell1.S(7,12,0,"<%=monthpayJYAF.getPersonCountsLess()%>");
		document.forms(0).Cell1.S(8,12,0,"<%=monthpayJYAF.getPayMoneyLess()%>");
		document.forms(0).Cell1.S(11,12,0,"<%=monthpayJYAF.getPersonCounts()%>");
		document.forms(0).Cell1.S(14,12,0,"<%=monthpayJYAF.getPayMoney()%>");
<%--		document.forms(0).Cell1.S(1,14,0,"<%=monthpayJYAF.getInceptMonth()%>"+"-"+"<%=monthpayJYAF.getPayMonth()%>");--%>
		document.forms(0).Cell1.S(2,15,0,"<%=monthpayJYAF.getPrintDate()%>"); */
		
		
		document.forms(0).Cell1.S(51,1,0,noteNum);
        document.forms(0).Cell1.S(9,1,0,formatTen("<%=monthpayJYAF.getOrgid()%>")+"<%=monthpayJYAF.getOrgid()%>");
        document.forms(0).Cell1.S(22,1,0,"<%=monthpayJYAF.getPrintDate().substring(0,4)%>");
        document.forms(0).Cell1.S(28,1,0,"<%=monthpayJYAF.getPrintDate().substring(4,6)%>");
        document.forms(0).Cell1.S(32,1,0,"<%=monthpayJYAF.getPrintDate().substring(6,8)%>");
        document.forms(0).Cell1.S(37,1,0,format("<%=seq_aa300%>")+"<%=seq_aa300%>");
        document.forms(0).Cell1.S(9,11,0,""+sumPay); 
        if(payWay==1)
        {
          document.forms(0).Cell1.S(9,15,0,"√");
        }
        if(payWay==2)
        {
           document.forms(0).Cell1.S(16,15,0,"√");
        }
         if(payWay==3)
        {
           document.forms(0).Cell1.S(22,15,0,"√");
        }
<%--        if(payKind==1)--%>
<%--        {--%>
<%--           document.forms(0).Cell1.S(38,15,0,"√");--%>
<%--        }--%>
<%--        if(payKind==2)--%>
<%--        {--%>
           document.forms(0).Cell1.S(45,15,0,"√");
<%--        }--%>
<%--        if(payKind==3)--%>
<%--        {--%>
<%--           document.forms(0).Cell1.S(52,15,0,"√  ");--%>
<%--        }--%>
		
		
       document.forms(0).Cell1.S(8,3,0,"  <%=monthpayJYAF.getReceivables_orgname()%>");
        document.forms(0).Cell1.S(8,6,0,"  <%=monthpayJYAF.getReceivables_bank_acc()%>");
        if("<%=monthpayJYAF.getReceivables_bank_name()%>"=="农行柳城支行"){
        	document.forms(0).Cell1.S(8,8,0,"  "+"中行渤海支行");
        }else{
        	document.forms(0).Cell1.S(8,8,0,"  <%=monthpayJYAF.getReceivables_bank_name()%>");
        }
        document.forms(0).Cell1.S(38,3,0,"  <%=monthpayJYAF.getPayment_orgname()%>");
        document.forms(0).Cell1.S(38,6,0,"  <%=monthpayJYAF.getPayment_bank_acc()%>");
        document.forms(0).Cell1.S(38,8,0,"  <%=monthpayJYAF.getPayment_bank_name()%>");
        
<%--         document.forms(0).Cell1.S(42,17,0,"<%=monthpayJYAF.getPayMonth()%>");--%>
         document.forms(0).Cell1.S(42,20,0,"<%=monthpayJYAF.getPersonCounts()%>");
         document.forms(0).Cell1.d(48,20,0,"<%=monthpayJYAF.getSumPay()%>");					
				document.forms(0).Cell1.AllowExtend=false;
				document.forms(0).Cell1.AllowDragdrop=false;
				
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
<td><INPUT id="Button3" onclick="location.href('<%=url%>')" type="button" value=" 返回 "></td>	
</table>
</form>
  </body>
</html>
