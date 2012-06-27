<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymntmonthreport.dto.PaymntMonRepInfoDTO"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	List list = (List) request.getSession().getAttribute("infoList");
	String bizDate = (String) request.getAttribute("bizDate");
	String operator = (String) request.getAttribute("operator");
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/paymentpickstatistics/paymntmonthreport/report/paymntmonthreport.cll","");
	var prevBalance=[];
	var monthpay = [];
	var addpay = [];	
	var overpay = [];
	var sumpay = [];
	var overpayBalance = [];
	var curYearMoney = [];	
	var orgCount = [];
	var personCount = [];
	var collBank = [];
    var bizdate = "<%=bizDate%>";
    bizdate = bizdate.substring(0,4)+"年"+bizdate.substring(4,6)+"月";
	var i=0;
	<%	
		for(int j=0; j<list.size(); j++){
			PaymntMonRepInfoDTO dto = (PaymntMonRepInfoDTO) list.get(j);
	%>
			prevBalance[i] = "<%=dto.getPrevMonBalance()%>";
			monthpay[i] = "<%=dto.getCurMonthPay()%>";
			addpay[i] = "<%=dto.getCurAddPay()%>";
			overpay[i] = "<%=dto.getCurOverPay()%>";
			sumpay[i] = "<%=dto.getCurSumPay()%>";
			curYearMoney[i] = "<%=dto.getCurYearSumPay()%>";
			orgCount[i] = "<%=dto.getCurMonthOrgCount()%>";
			personCount[i] = "<%=dto.getCurMonthPsnCount()%>";
			collBank[i] = "<%=dto.getCollBank()%>";
			overpayBalance[i] = "<%=dto.getOverPayBalance()%>";
			i++;
	<%
		}
	%>
	var totalLine=collBank.length;		//总的行数 数组的长度
	var totalPageLine=29;					//每页显示多少行--除了第一行
	var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
	var pageCurrent=0;						//当前页
	var completeline=0;						//记录行的

	for(k = 0 ; k < totalLine; k++){
		if(k%totalPageLine==0&&k>0)
		{
			pageCurrent++;//当前页++	
			completeline=k-2;
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/paymentpickstatistics/paymntmonthreport/report/paymntmonthreport.cll",0,1,pageCurrent);
			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
		}
		document.forms(0).Cell1.S(3,3,0,bizdate);
		document.forms(0).Cell1.S(1,k+5,0,collBank[k]);
		document.forms(0).Cell1.d(2,k+5,0,prevBalance[k]);
		document.forms(0).Cell1.d(3,k+5,0,monthpay[k]);
		document.forms(0).Cell1.d(4,k+5,0,addpay[k]);
		document.forms(0).Cell1.d(5,k+5,0,overpay[k]);
		document.forms(0).Cell1.d(6,k+5,0,sumpay[k]);
		document.forms(0).Cell1.d(7,k+5,0,overpayBalance[k]);
		document.forms(0).Cell1.d(8,k+5,0,curYearMoney[k]);
		document.forms(0).Cell1.S(9,k+5,0,orgCount[k]);
		document.forms(0).Cell1.S(10,k+5,0,personCount[k]);
		document.forms(0).Cell1.S(7,k+6,0,"制单人:");
		document.forms(0).Cell1.S(8,k+6,0,"<%=operator%>");
	}
	document.forms(0).Cell1.DrawGridLine(1, totalLine+5, 10, totalLine+5,0, 1, 0);
	document.forms(0).Cell1.DrawGridLine(1, totalLine+5, 10, totalLine+5,4, 2, 0);
	document.forms(0).Cell1.DeleteRow(totalLine+6,28-totalLine,pageCurrent);
	document.forms(0).Cell1.ReDraw();
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

	<body onload="load();">
		<form action="">
			<table align="center">
				<tr>
					<td>
						<OBJECT id="Cell1"
							style="LEFT:0px;WIDTH:900px;  TOP:0px;HEIGHT:500px"
							codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
							classid="clsid:3F166327-8030-4881-8BD2-EA25350E574A" VIEWASTEXT>
							<PARAM NAME="_Version" VALUE="65536">
							<PARAM NAME="_ExtentX" VALUE="10266">
							<PARAM NAME="_ExtentY" VALUE="7011">
							<PARAM NAME="_StockProps" VALUE="0">
						</OBJECT>
					</td>
				</tr>
				<tr>
					<td align="center">
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
						<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 " />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
