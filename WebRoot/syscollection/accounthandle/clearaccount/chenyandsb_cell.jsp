<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@page import="org.xpup.hafmis.syscollection.accounthandle.clearaccount.dto.ChenYanDSBDTO" %>
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
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/accounthandle/clearaccount/report/chenyandsb.cll","");
	var bizdate=[];
	var monthpay=[];
	var pickup=[];
	var tranin=[];
	var tranout=[];
	var chgup=[];
	var chgdown=[];
	var pickloan=[];
	var balance=[];
	var i=0;
	<%
    		List list = (List)request.getSession().getAttribute("chenyandsb");
    		for(int j=0;j<list.size();j++){
    			ChenYanDSBDTO dto = (ChenYanDSBDTO)list.get(j);
	%>
    			bizdate[i]="<%=dto.getBizdate()%>";
    			monthpay[i]="<%=dto.getMonthpay()%>";
    			pickup[i]="<%=dto.getPickup()%>";
    			tranin[i]="<%=dto.getTranin()%>";
    			tranout[i]="<%=dto.getTranout()%>";
    			chgup[i]="<%=dto.getChgup()%>";
    			chgdown[i]="<%=dto.getChgdown()%>";
    			pickloan[i]="<%=dto.getPickloan()%>";
    			balance[i]="<%=dto.getBalance()%>";
    			i++;
	<%
    		}
	%>	
		var totalLine=bizdate.length;
		for(k = 0 ; k < totalLine; k++){
			document.forms(0).Cell1.S(1,k+3,0,bizdate[k]);
			document.forms(0).Cell1.d(2,k+3,0,monthpay[k]);
			document.forms(0).Cell1.d(3,k+3,0,pickup[k]);
			document.forms(0).Cell1.d(4,k+3,0,tranin[k]);
			document.forms(0).Cell1.d(5,k+3,0,tranout[k]);
			document.forms(0).Cell1.d(6,k+3,0,chgup[k]);
			document.forms(0).Cell1.d(7,k+3,0,chgdown[k]);
			document.forms(0).Cell1.d(8,k+3,0,pickloan[k]);
			document.forms(0).Cell1.d(9,k+3,0,balance[k]);
		}
			document.forms(0).Cell1.S(1,totalLine+3,0,"合计：");
			document.forms(0).Cell1.SetFormula(2,totalLine+3,0,"Sum(B3:B"+(totalLine+2)+")");
			document.forms(0).Cell1.SetFormula(3,totalLine+3,0,"Sum(C3:C"+(totalLine+2)+")");
			document.forms(0).Cell1.SetFormula(4,totalLine+3,0,"Sum(D3:D"+(totalLine+2)+")");
			document.forms(0).Cell1.SetFormula(5,totalLine+3,0,"Sum(E3:E"+(totalLine+2)+")");
			document.forms(0).Cell1.SetFormula(6,totalLine+3,0,"Sum(F3:F"+(totalLine+2)+")");
			document.forms(0).Cell1.SetFormula(7,totalLine+3,0,"Sum(G3:G"+(totalLine+2)+")");
			document.forms(0).Cell1.SetFormula(8,totalLine+3,0,"Sum(H3:H"+(totalLine+2)+")");
			document.forms(0).Cell1.DeleteRow(totalLine+4,33-totalLine-3,0);
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
<td><INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 "></td>	
</table>
</form>
  </body>
</html>
