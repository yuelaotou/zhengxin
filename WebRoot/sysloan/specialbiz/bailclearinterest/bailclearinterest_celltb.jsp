<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.dto.BailClearInterestTbDTO"%>
<%@ include file="/checkUrl.jsp"%>

<%
	String path = request.getContextPath();
	List list = (List) request.getAttribute("printList");
	String url = (String) request.getAttribute("URL");
	if (url == null) {
		url = "bailClearInterestTbShowAC.do";
	}
	String userName = (String) request.getAttribute("userName");
	String plbizDate = (String) request.getAttribute("plbizDate");
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	function load(){
	loginReg();	
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/specialbiz/report/bailclearinteresttb.cll","");
	var bizYear=[];
	var loanBankName=[];
	var loanKouAcc=[];
	var borrowerName=[];
	var firstBailBalance=[];
	var occurMoney=[];
	var lastBailBalance=[];
	var i=0;
	
	<%
		BailClearInterestTbDTO bailClearInterestTbDTO = new BailClearInterestTbDTO();
		for(int j=0;j<list.size();j++){
		bailClearInterestTbDTO = (BailClearInterestTbDTO)list.get(j);
	%>
	    //把数据传到JS的数组里面..
		bizYear[i] = "<%=bailClearInterestTbDTO.getBizYear()%>"; 
		loanBankName[i] = "<%=bailClearInterestTbDTO.getLoanBankName()%>";
		loanKouAcc[i] = "<%=bailClearInterestTbDTO.getLoanKouAcc()%>";
		borrowerName[i] = "<%=bailClearInterestTbDTO.getBorrowerName()%>";
		firstBailBalance[i] = "<%=bailClearInterestTbDTO.getBailBalance()%>";
		occurMoney[i] = "<%=bailClearInterestTbDTO.getOccurMoney()%>";
		lastBailBalance[i] = "<%=bailClearInterestTbDTO.getLastBalance()%>";
		i++;
	<%}%>
	var totalLine=bizYear.length;				//总的行数 数组的长度
	var totalPageLine=40;					//每页显示多少行--除了第一行
	var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
	var pageCurrent=0;						//当前页
	var completeline=0;						//记录行的
	var firstBailBalancetotal=0;			//结息前保证金合计
	var occurMoneytotal=0;					//结息利息合计
	var lastBailBalancetotal=0;				//结息后保证金合计
	for(k = 0 ; k < totalLine; k++){
		if(k%totalPageLine==0&&k>0)
		{
			document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计:");
			document.forms(0).Cell1.SetFormula (5, totalPageLine+4, pageCurrent, "Sum(E4:E"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F4:F"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (7, totalPageLine+4, pageCurrent, "Sum(G4:G"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.ReDraw();//重画一个表格
			pageCurrent++;//当前页++	
			completeline=k-2;
			//绘制标签 param 	表页索引号。param 要设置的表页页签名称					
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/specialbiz/report/bailclearinteresttb.cll",0,1,pageCurrent);
			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
		}
		if(pageCurrent==0)
		{	
			document.forms(0).Cell1.S(1,k+4,0,bizYear[k]);
			document.forms(0).Cell1.S(2,k+4,0,loanBankName[k]);
			document.forms(0).Cell1.S(3,k+4,0,loanKouAcc[k]);
			document.forms(0).Cell1.S(4,k+4,0,borrowerName[k]);
			document.forms(0).Cell1.d(5,k+4,0,firstBailBalance[k]);
			document.forms(0).Cell1.d(6,k+4,0,occurMoney[k]);
			document.forms(0).Cell1.d(7,k+4,0,lastBailBalance[k]);
			firstBailBalancetotal=firstBailBalancetotal+parseFloat(firstBailBalance[k]);
			occurMoneytotal=occurMoneytotal+parseFloat(occurMoney[k]);
			lastBailBalancetotal=lastBailBalancetotal+parseFloat(lastBailBalance[k]);
			}
		else{//向第一页后的所有页插数据
			document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,bizYear[k]);
			document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,loanBankName[k]);
			document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,loanKouAcc[k]);
			document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,borrowerName[k]);
			document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,firstBailBalance[k]);
			document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,occurMoney[k]);
			document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,lastBailBalance[k]);
			firstBailBalancetotal=firstBailBalancetotal+parseFloat(firstBailBalance[k]);
			occurMoneytotal=occurMoneytotal+parseFloat(occurMoney[k]);
			lastBailBalancetotal=lastBailBalancetotal+parseFloat(lastBailBalance[k]);
		}		
	}
	if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
	{
		document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
		document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(E4:E"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (7, totalLine+4, pageCurrent, "Sum(G4:G"+(totalLine+3)+")" );
		document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
		document.forms(0).Cell1.d(5,totalLine+5,pageCurrent,firstBailBalancetotal);
		document.forms(0).Cell1.d(6,totalLine+5,pageCurrent,occurMoneytotal);
		document.forms(0).Cell1.d(7,totalLine+5,pageCurrent,lastBailBalancetotal);
		document.forms(0).Cell1.S(4,totalLine+6,pageCurrent,"操作员");
		document.forms(0).Cell1.S(5,totalLine+6,pageCurrent,"<%=userName%>");
		document.forms(0).Cell1.S(6,totalLine+6,pageCurrent,"操作时间");
		document.forms(0).Cell1.S(7,totalLine+6,pageCurrent,"<%=plbizDate%>");
		document.forms(0).Cell1.DeleteRow(totalLine+7,46-totalLine-6,pageCurrent);
		document.forms(0).Cell1.ReDraw();
	}	
	else
	{
		document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");   
		//F1 第F列的第1行: N9 到第N列的第9行  求和
		document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E4:E"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G4:G"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
		document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,firstBailBalancetotal);
		document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,occurMoneytotal);
		document.forms(0).Cell1.d(7,totalLine-completeline+3,pageCurrent,lastBailBalancetotal);
		document.forms(0).Cell1.S(4,totalLine-completeline+4,pageCurrent,"操作员");
		document.forms(0).Cell1.S(5,totalLine-completeline+4,pageCurrent,"<%=userName%>");
		document.forms(0).Cell1.S(6,totalLine-completeline+4,pageCurrent,"操作时间");
		document.forms(0).Cell1.S(7,totalLine-completeline+4,pageCurrent,"<%=plbizDate%>");
		document.forms(0).Cell1.DeleteRow(totalLine-completeline+5,46-(totalLine-completeline+4),pageCurrent);
		document.forms(0).Cell1.ReDraw();
	}
	
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
						<INPUT id="Button3" onclick="location.href='<%=url%>'"
							type="button" value=" 返回 " />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
