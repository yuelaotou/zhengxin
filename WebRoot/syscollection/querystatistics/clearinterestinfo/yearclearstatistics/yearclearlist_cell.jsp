<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.dto.YearclearstatisticsHeadDTO" %>
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
	var pageCurrent=0;						//当前页
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/clearinterestinfo/yearclearstatistics/report/interest.cll","");
	document.forms(0).Cell1.setSheetLabel(pageCurrent,"第1页")	
	<%
			List list = (List)request.getAttribute("printList");
    		String setDate = (String)request.getAttribute("setDate");
    		String userName = (String)request.getAttribute("userName");
    		String bankName = (String)request.getAttribute("bankName");
    		for(int j=0;j<list.size();j++)
  			{
    			YearclearstatisticsHeadDTO f=(YearclearstatisticsHeadDTO)list.get(j);
    			%>
    			if(pageCurrent!=0){
    				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/clearinterestinfo/yearclearstatistics/report/interest1.cll",0,1,pageCurrent);
					document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");
				    document.forms(0).Cell1.PrintSetCustomPaper(2400,1280,1);
				    document.forms(0).Cell1.PrintSetMargin(150,100,150,100);
    			}
    			document.forms(0).Cell1.S(3,3,pageCurrent,"<%=setDate.substring(0,4)%>年"+"<%=setDate.substring(4,6)%>月"+"<%=setDate.substring(6,8)%>日");
				document.forms(0).Cell1.S(5,9,pageCurrent,"<%=userName%>");
				document.forms(0).Cell1.S(2,5,pageCurrent,"<%=f.getOrgname() %>");
				document.forms(0).Cell1.S(5,5,pageCurrent,formatTen("<%=f.getOrgcode() %>")+"<%=f.getOrgcode() %>");
				document.forms(0).Cell1.d(2,6,pageCurrent,"<%=f.getOldcurblance().add(f.getOldpreblance()) %>");
				document.forms(0).Cell1.d(2,7,pageCurrent,"<%=f.getPreinterest().add(f.getCurinterest()) %>");
				document.forms(0).Cell1.S(5,6,pageCurrent,"<%=f.getEmpcounts() %>");
				document.forms(0).Cell1.S(2,8,pageCurrent,"<%=bankName%>");
				document.forms(0).Cell1.d(5,7,pageCurrent,"<%=f.getBlance() %>");		
				document.forms(0).Cell1.d(5,8,pageCurrent,"<%=f.getGuazhang() %>");	
			    pageCurrent++;
    			<%
    		}
    	
	%>	
		//中心
		
		//银行
		
			   
		//时间
		
		//贷
			
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
