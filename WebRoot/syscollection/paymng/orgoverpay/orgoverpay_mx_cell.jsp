<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ page import="org.xpup.hafmis.syscollection.paymng.orgoverpay.form.*" %>
<%@ page import="org.xpup.hafmis.common.util.BusiTools"%>
<%@ include file="/checkUrl.jsp"%>
<%

   String path=request.getContextPath();
   String url = (String)request.getAttribute("ForwardUrl");
   if(url.equals("C")){
  		url=path+"/syscollection/querystatistics/operationflow/orgoperationflow/empOperationFlowTaShowAC.do";
   }
 %>
<html>
  <head>
  <script src="<%=path%>/js/common.js">
</script>
  <script type="text/javascript">
	function load(){	

	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/paymng/report/getOverPay1.cll","");

	var orgId;
	var unitName;
	var noteNum;
	var banlance;
	var sumPay;
	var reason="";
	var orgBalance;
	var maker="";
	var bizDate="";
	var checker=""
	var creNum=""
	<%
    		OrgoverpayAF orgoverpayAF=(OrgoverpayAF)request.getAttribute("orgoverpayAF");	    
 	%>		
				orgId="<%=BusiTools.convertSixNumber(orgoverpayAF.getOrgId().toString())%>";			
				unitName="<%=orgoverpayAF.getUnitName()%>";
				noteNum="<%=orgoverpayAF.getNoteNum()%>";
				banlance="<%=orgoverpayAF.getBanlance()%>";
				sumPay="<%=orgoverpayAF.getAmount()%>";
				reason="<%=orgoverpayAF.getReason()%>";
				orgBalance=<%=orgoverpayAF.getOrgBalance()%>;
				maker="<%=orgoverpayAF.getMaker()%>";
				if("<%=orgoverpayAF.getMaker()%>"=="null"){
					maker="";
				}
				bizDate="<%=orgoverpayAF.getBizDate()%>";
				checker="<%=orgoverpayAF.getChecker()%>";
				if("<%=orgoverpayAF.getChecker()%>"=="null"){
					checker="";
				}
				creNum="<%=orgoverpayAF.getCreNum()%>";
				if("<%=orgoverpayAF.getCreNum()%>"=="null"){
					creNum="";
				}

	    if(reason==null){
	      reason="";
	    }
	/**	var chiao="0";
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
			document.forms(0).Cell1.S((15-i),9,0,temp);
		}
		if(count!=-1){//计算小数后面
			if((len-count)>2)//两位
			{
				chiao=sumPay.substring(count+1,sumPay.length-1);
				fin=sumPay.substring(count+2,sumPay.length);
				
				document.forms(0).Cell1.S(16,9,0,chiao);
				document.forms(0).Cell1.S(17,9,0,fin);
			}
			else{//只有一位
				chiao=sumPay.substring(count+1,sumPay.length)
				document.forms(0).Cell1.S(16,9,0,chiao);
				document.forms(0).Cell1.S(17,9,0,"0");
				}	
		}else{
			document.forms(0).Cell1.S(16,9,0,chiao);
			document.forms(0).Cell1.S(17,9,0,fin);
		}
		sumPay=ChangeToBig(sumPay);
		  
		document.forms(0).Cell1.S(3,4,0,orgId);
		document.forms(0).Cell1.S(3,5,0,unitName);
		document.forms(0).Cell1.S(3,6,0,"<%=orgoverpayAF.getBankname() %>");
		document.forms(0).Cell1.S(3,7,0,"<%=orgoverpayAF.getBankid() %>");
		document.forms(0).Cell1.S(8,5,0,"<%=orgoverpayAF.getOffice() %>");
		document.forms(0).Cell1.S(8,6,0,"<%=orgoverpayAF.getCollbankname() %>");
		document.forms(0).Cell1.S(4,8,0,sumPay);
		document.forms(0).Cell1.S(1,11,0,reason);
		*/
		document.forms(0).Cell1.S(8,3,0,orgId);
		document.forms(0).Cell1.S(2,3,0,unitName);
		document.forms(0).Cell1.d(2,4,0,sumPay);
		document.forms(0).Cell1.S(2,6,0,reason);
		document.forms(0).Cell1.D(8,4,0,orgBalance);
		document.forms(0).Cell1.S(1,2,0,bizDate.substr(0,4)+'年'+bizDate.substr(4,2)+'月'+bizDate.substr(6,2)+'日');
		document.forms(0).Cell1.S(9,2,0,creNum);
		document.forms(0).Cell1.d(2,5,0,banlance);
		document.forms(0).Cell1.S(6,2,0,bizDate.substr(6,2));
		document.forms(0).Cell1.S(2,2,0,'结算号　'+noteNum);
		document.forms(0).Cell1.S(2,7,0,maker);
		document.forms(0).Cell1.S(6,7,0,checker);
						
		
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
<INPUT id="Button3" onclick="location.href='<%=url %>'" type="button" value=" 返回 ">
</table>
</form>
  </body>
</html>

