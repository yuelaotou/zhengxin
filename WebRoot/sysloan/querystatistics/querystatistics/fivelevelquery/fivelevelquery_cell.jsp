<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.dto.FiveLevelQueryDTO"%>
<%@ include file="/checkUrl.jsp"%>

<%
	String path = request.getContextPath();
	List list = (List) request.getAttribute("printList");
	String url = (String) request.getAttribute("URL");
	if (url == null) {
		url = "fiveLevelQueryShowAC.do";
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
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/report/fivelevelquery.cll","");
	var office=[];
	var loanBankName=[];
	var developerName=[];
	var floorNum=[];
	var assistantOrgName=[];
	var normalCount=[];
	var normalValue=[];
	var attentionCount=[];
	var attentionValue=[];
	var secondaryCount=[];
	var secondaryValue=[];
	var shadinessCount=[];
	var shadinessValue=[];
	var damnifyCount=[];
	var damnifyValue=[];
	var totalCount=[];
	var totalValue=[];
	var badRateCount=[];
	var badRateValue=[];
	var i=0;
	
	<%
		FiveLevelQueryDTO fiveLevelQueryDTO = new FiveLevelQueryDTO();
		for(int j=0;j<list.size();j++){
		fiveLevelQueryDTO = (FiveLevelQueryDTO)list.get(j);
	%>
	    //把数据传到JS的数组里面..
		office[i] = "<%=fiveLevelQueryDTO.getOffice()%>"; 
		loanBankName[i] = "<%=fiveLevelQueryDTO.getLoanBankName()%>";
		developerName[i] = "<%=fiveLevelQueryDTO.getDeveloperName()%>";
		floorNum[i] = "<%=fiveLevelQueryDTO.getFloorId()%>";
		assistantOrgName[i] = "<%=fiveLevelQueryDTO.getAssistantOrgName()%>";
		normalCount[i] = "<%=fiveLevelQueryDTO.getNormalCount()%>";
		normalValue[i] = "<%=fiveLevelQueryDTO.getNormalValue()%>";
		attentionCount[i] = "<%=fiveLevelQueryDTO.getAttentionCount()%>";
		attentionValue[i] = "<%=fiveLevelQueryDTO.getAttentionValue()%>";
		secondaryCount[i] = "<%=fiveLevelQueryDTO.getSecondaryCount()%>";
		secondaryValue[i] = "<%=fiveLevelQueryDTO.getSecondaryValue()%>";
		shadinessCount[i] = "<%=fiveLevelQueryDTO.getShadinessCount()%>";
		shadinessValue[i] = "<%=fiveLevelQueryDTO.getShadinessValue()%>";
		damnifyCount[i] = "<%=fiveLevelQueryDTO.getDamnifyCount()%>";
		damnifyValue[i] = "<%=fiveLevelQueryDTO.getDamnifyValue()%>";
		totalCount[i] = "<%=fiveLevelQueryDTO.getTotalCount()%>";
		totalValue[i] = "<%=fiveLevelQueryDTO.getTotalValue()%>";
		badRateCount[i] = "<%=fiveLevelQueryDTO.getBadRateCount()%>";
		badRateValue[i] = "<%=fiveLevelQueryDTO.getBadRateValue()%>";
		i++;
	<%}%>
	var totalLine=office.length;				//总的行数 数组的长度
	var totalPageLine=40;					//每页显示多少行--除了第一行
	var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
	var pageCurrent=0;						//当前页
	var completeline=0;						//记录行的
	var normalCounttotal=0;					//正常户数
	var normalValuetotal=0;					//正常金额
	var attentionCounttotal=0;				//关注户数
	var attentionValuetotal=0;				//关注金额
	var secondaryCounttotal=0;				//次级户数
	var secondaryValuetotal=0;				//次级金额
	var shadinessCounttotal=0;				//可疑户数
	var shadinessValuetotal=0;				//可疑金额
	var damnifyCounttotal=0;				//损失户数
	var damnifyValuetotal=0;				//损失金额
	var pageCounttotal=0;					//合计户数
	var pageMoneytotal=0;					//合计金额
	var badRateCounttotal=0;				//不良率户数
	var badRateValuetotal=0;				//不良率金额

	for(k = 0 ; k < totalLine; k++){
		if(k%totalPageLine==0&&k>0)
		{
			document.forms(0).Cell1.S(1,totalPageLine+5,pageCurrent,"本页小计:");
			document.forms(0).Cell1.SetFormula (6, totalPageLine+5, pageCurrent, "Sum(F5:F"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (7, totalPageLine+5, pageCurrent, "Sum(G5:G"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (8, totalPageLine+5, pageCurrent, "Sum(H5:H"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (9, totalPageLine+5, pageCurrent, "Sum(I5:I"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (10, totalPageLine+5, pageCurrent, "Sum(J5:J"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (11, totalPageLine+5, pageCurrent, "Sum(K5:K"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (12, totalPageLine+5, pageCurrent, "Sum(L5:L"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (13, totalPageLine+5, pageCurrent, "Sum(M5:M"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (14, totalPageLine+5, pageCurrent, "Sum(N5:N"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (15, totalPageLine+5, pageCurrent, "Sum(O5:O"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (16, totalPageLine+5, pageCurrent, "Sum(P5:P"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (17, totalPageLine+5, pageCurrent, "Sum(Q5:Q"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (18, totalPageLine+5, pageCurrent, "(J"+(totalPageLine+5)+"+L"+(totalPageLine+5)+"+N"+(totalPageLine+5)+")/P"+(totalPageLine+5)+"" );
			document.forms(0).Cell1.SetFormula (19, totalPageLine+5, pageCurrent, "(K"+(totalPageLine+5)+"+M"+(totalPageLine+5)+"+O"+(totalPageLine+5)+")/Q"+(totalPageLine+5)+"" );
			document.forms(0).Cell1.ReDraw();//重画一个表格
			pageCurrent++;//当前页++	
			completeline=k-2;
			//绘制标签 param 	表页索引号。param 要设置的表页页签名称					
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/querystatistics/report/fivelevelquery.cll",0,1,pageCurrent);
			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
		}
		if(pageCurrent==0)
		{	
			document.forms(0).Cell1.S(1,k+5,0,office[k]);
			document.forms(0).Cell1.S(2,k+5,0,loanBankName[k]);
			document.forms(0).Cell1.S(3,k+5,0,developerName[k]);
			document.forms(0).Cell1.S(4,k+5,0,floorNum[k]);
			document.forms(0).Cell1.S(5,k+5,0,assistantOrgName[k]);
			document.forms(0).Cell1.d(6,k+5,0,normalCount[k]);
			document.forms(0).Cell1.d(7,k+5,0,normalValue[k]);
			document.forms(0).Cell1.d(8,k+5,0,attentionCount[k]);
			document.forms(0).Cell1.d(9,k+5,0,attentionValue[k]);
			document.forms(0).Cell1.d(10,k+5,0,secondaryCount[k]);
			document.forms(0).Cell1.d(11,k+5,0,secondaryValue[k]);
			document.forms(0).Cell1.d(12,k+5,0,shadinessCount[k]);
			document.forms(0).Cell1.d(13,k+5,0,shadinessValue[k]);
			document.forms(0).Cell1.d(14,k+5,0,damnifyCount[k]);
			document.forms(0).Cell1.d(15,k+5,0,damnifyValue[k]);
			document.forms(0).Cell1.d(16,k+5,0,totalCount[k]);
			document.forms(0).Cell1.d(17,k+5,0,totalValue[k]);
			document.forms(0).Cell1.S(18,k+5,0,badRateCount[k]+"%");
			document.forms(0).Cell1.S(19,k+5,0,badRateValue[k]+"%");
			normalCounttotal = normalCounttotal + parseInt(normalCount[k]);
			normalValuetotal = normalValuetotal + parseFloat(normalValue[k]);
			attentionCounttotal = attentionCounttotal + parseInt(attentionCount[k]);
			attentionValuetotal = attentionValuetotal + parseFloat(attentionValue[k]);
			secondaryCounttotal = secondaryCounttotal + parseInt(secondaryCount[k]);
			secondaryValuetotal = secondaryValuetotal + parseFloat(secondaryValue[k]);
			shadinessCounttotal = shadinessCounttotal + parseInt(shadinessCount[k]);
			shadinessValuetotal = shadinessValuetotal + parseFloat(shadinessValue[k]);
			damnifyCounttotal = damnifyCounttotal + parseInt(damnifyCount[k]);
			damnifyValuetotal = damnifyValuetotal + parseFloat(damnifyValue[k]);
			pageCounttotal = pageCounttotal + parseInt(totalCount[k]);
			pageMoneytotal = pageMoneytotal + parseFloat(totalValue[k]);
			//badRateCounttotal = badRateCounttotal + parseInt(badRateCount[k]);
			//badRateValuetotal = badRateValuetotal + parseFloat(badRateValue[k]);
			}
		else{//向第一页后的所有页插数据
			document.forms(0).Cell1.S(1,k-completeline+3,pageCurrent,office[k]);
			document.forms(0).Cell1.S(2,k-completeline+3,pageCurrent,loanBankName[k]);
			document.forms(0).Cell1.S(3,k-completeline+3,pageCurrent,developerName[k]);
			document.forms(0).Cell1.S(4,k-completeline+3,pageCurrent,floorNum[k]);
			document.forms(0).Cell1.S(5,k-completeline+3,pageCurrent,assistantOrgName[k]);
			document.forms(0).Cell1.d(6,k-completeline+3,pageCurrent,normalCount[k]);
			document.forms(0).Cell1.d(7,k-completeline+3,pageCurrent,normalValue[k]);
			document.forms(0).Cell1.d(8,k-completeline+3,pageCurrent,attentionCount[k]);
			document.forms(0).Cell1.d(9,k-completeline+3,pageCurrent,attentionValue[k]);
			document.forms(0).Cell1.d(10,k-completeline+3,pageCurrent,secondaryCount[k]);
			document.forms(0).Cell1.d(11,k-completeline+3,pageCurrent,secondaryValue[k]);
			document.forms(0).Cell1.d(12,k-completeline+3,pageCurrent,shadinessCount[k]);
			document.forms(0).Cell1.d(13,k-completeline+3,pageCurrent,shadinessValue[k]);
			document.forms(0).Cell1.d(14,k-completeline+3,pageCurrent,damnifyCount[k]);
			document.forms(0).Cell1.d(15,k-completeline+3,pageCurrent,damnifyValue[k]);
			document.forms(0).Cell1.d(16,k-completeline+3,pageCurrent,totalCount[k]);
			document.forms(0).Cell1.d(17,k-completeline+3,pageCurrent,totalValue[k]);
			document.forms(0).Cell1.S(18,k-completeline+3,pageCurrent,badRateCount[k]+"%");
			document.forms(0).Cell1.S(19,k-completeline+3,pageCurrent,badRateValue[k]+"%");
			normalCounttotal = normalCounttotal + parseInt(normalCount[k]);
			normalValuetotal = normalValuetotal + parseFloat(normalValue[k]);
			attentionCounttotal = attentionCounttotal + parseInt(attentionCount[k]);
			attentionValuetotal = attentionValuetotal + parseFloat(attentionValue[k]);
			secondaryCounttotal = secondaryCounttotal + parseInt(secondaryCount[k]);
			secondaryValuetotal = secondaryValuetotal + parseFloat(secondaryValue[k]);
			shadinessCounttotal = shadinessCounttotal + parseInt(shadinessCount[k]);
			shadinessValuetotal = shadinessValuetotal + parseFloat(shadinessValue[k]);
			damnifyCounttotal = damnifyCounttotal + parseInt(damnifyCount[k]);
			damnifyValuetotal = damnifyValuetotal + parseFloat(damnifyValue[k]);
			pageCounttotal = pageCounttotal + parseInt(totalCount[k]);
			pageMoneytotal = pageMoneytotal + parseFloat(totalValue[k]);
		}		
	}
	if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
	{
		document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"本页小计");
		document.forms(0).Cell1.SetFormula (6, totalLine+5, pageCurrent, "Sum(F5:F"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (7, totalLine+5, pageCurrent, "Sum(G5:G"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (8, totalLine+5, pageCurrent, "Sum(H5:H"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (9, totalLine+5, pageCurrent, "Sum(I5:I"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (10, totalLine+5, pageCurrent, "Sum(J5:J"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (11, totalLine+5, pageCurrent, "Sum(K5:K"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (12, totalLine+5, pageCurrent, "Sum(L5:L"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (13, totalLine+5, pageCurrent, "Sum(M5:M"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (14, totalLine+5, pageCurrent, "Sum(N5:N"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (15, totalLine+5, pageCurrent, "Sum(O5:O"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (16, totalLine+5, pageCurrent, "Sum(P5:P"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (17, totalLine+5, pageCurrent, "Sum(Q5:Q"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (18, totalLine+5, pageCurrent, "(J"+(totalLine+5)+"+L"+(totalLine+5)+"+N"+(totalLine+5)+")/P"+(totalLine+5)+"" );
		document.forms(0).Cell1.SetFormula (19, totalLine+5, pageCurrent, "(K"+(totalLine+5)+"+M"+(totalLine+5)+"+O"+(totalLine+5)+")/Q"+(totalLine+5)+"" );
		document.forms(0).Cell1.S(1,totalLine+6,pageCurrent,"总计");
		document.forms(0).Cell1.d(6,totalLine+6,pageCurrent,normalCounttotal);
		document.forms(0).Cell1.d(7,totalLine+6,pageCurrent,normalValuetotal);
		document.forms(0).Cell1.d(8,totalLine+6,pageCurrent,attentionCounttotal);
		document.forms(0).Cell1.d(9,totalLine+6,pageCurrent,attentionValuetotal);
		document.forms(0).Cell1.d(10,totalLine+6,pageCurrent,secondaryCounttotal);
		document.forms(0).Cell1.d(11,totalLine+6,pageCurrent,secondaryValuetotal);
		document.forms(0).Cell1.d(12,totalLine+6,pageCurrent,shadinessCounttotal);
		document.forms(0).Cell1.d(13,totalLine+6,pageCurrent,shadinessValuetotal);
		document.forms(0).Cell1.d(14,totalLine+6,pageCurrent,damnifyCounttotal);
		document.forms(0).Cell1.d(15,totalLine+6,pageCurrent,damnifyValuetotal);
		document.forms(0).Cell1.d(16,totalLine+6,pageCurrent,pageCounttotal);
		document.forms(0).Cell1.d(17,totalLine+6,pageCurrent,pageMoneytotal);
		document.forms(0).Cell1.SetFormula (18, totalLine+6, pageCurrent, "(J"+(totalLine+6)+"+L"+(totalLine+6)+"+N"+(totalLine+6)+")/P"+(totalLine+6)+"" );
		document.forms(0).Cell1.SetFormula (19, totalLine+6, pageCurrent, "(K"+(totalLine+6)+"+M"+(totalLine+6)+"+O"+(totalLine+6)+")/Q"+(totalLine+6)+"" );

		document.forms(0).Cell1.S(16,totalLine+7,pageCurrent,"操作员");
		document.forms(0).Cell1.S(17,totalLine+7,pageCurrent,"<%=userName%>");
		document.forms(0).Cell1.S(18,totalLine+7,pageCurrent,"操作时间");
		document.forms(0).Cell1.S(19,totalLine+7,pageCurrent,"<%=plbizDate%>");
		document.forms(0).Cell1.DeleteRow(totalLine+8,47-totalLine-7,pageCurrent);
		document.forms(0).Cell1.ReDraw();
	}	
	else
	{
		document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"本页小计");   
		//F1 第F列的第1行: N9 到第N列的第9行  求和
		document.forms(0).Cell1.SetFormula (6, totalLine-completeline+3, pageCurrent, "Sum(F5:F"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (7, totalLine-completeline+3, pageCurrent, "Sum(G5:G"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (8, totalLine-completeline+3, pageCurrent, "Sum(H5:H"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (9, totalLine-completeline+3, pageCurrent, "Sum(I5:I"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (10, totalLine-completeline+3, pageCurrent, "Sum(J5:J"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (11, totalLine-completeline+3, pageCurrent, "Sum(K5:K"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (12, totalLine-completeline+3, pageCurrent, "Sum(L5:L"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (13, totalLine-completeline+3, pageCurrent, "Sum(M5:M"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (14, totalLine-completeline+3, pageCurrent, "Sum(N5:N"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (15, totalLine-completeline+3, pageCurrent, "Sum(O5:O"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (16, totalLine-completeline+3, pageCurrent, "Sum(P5:P"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (17, totalLine-completeline+3, pageCurrent, "Sum(Q5:Q"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (18, totalLine-completeline+3, pageCurrent, "(J"+(totalLine-completeline+3)+"+L"+(totalLine-completeline+3)+"+N"+(totalLine-completeline+3)+")/P"+(totalLine-completeline+3)+"" );
		document.forms(0).Cell1.SetFormula (19, totalLine-completeline+3, pageCurrent, "(K"+(totalLine-completeline+3)+"+M"+(totalLine-completeline+3)+"+O"+(totalLine-completeline+3)+")/Q"+(totalLine-completeline+3)+"" );
		document.forms(0).Cell1.S(1,totalLine-completeline+4,pageCurrent,"总计");
		document.forms(0).Cell1.d(6,totalLine-completeline+4,pageCurrent,normalCounttotal);
		document.forms(0).Cell1.d(7,totalLine-completeline+4,pageCurrent,normalValuetotal);
		document.forms(0).Cell1.d(8,totalLine-completeline+4,pageCurrent,attentionCounttotal);
		document.forms(0).Cell1.d(9,totalLine-completeline+4,pageCurrent,attentionValuetotal);
		document.forms(0).Cell1.d(10,totalLine-completeline+4,pageCurrent,secondaryCounttotal);
		document.forms(0).Cell1.d(11,totalLine-completeline+4,pageCurrent,secondaryValuetotal);
		document.forms(0).Cell1.d(12,totalLine-completeline+4,pageCurrent,shadinessCounttotal);
		document.forms(0).Cell1.d(13,totalLine-completeline+4,pageCurrent,shadinessValuetotal);
		document.forms(0).Cell1.d(14,totalLine-completeline+4,pageCurrent,damnifyCounttotal);
		document.forms(0).Cell1.d(15,totalLine-completeline+4,pageCurrent,damnifyValuetotal);
		document.forms(0).Cell1.d(16,totalLine-completeline+4,pageCurrent,pageCounttotal);
		document.forms(0).Cell1.d(17,totalLine-completeline+4,pageCurrent,pageMoneytotal);
		document.forms(0).Cell1.SetFormula (18, totalLine-completeline+4, pageCurrent, "(J"+(totalLine-completeline+4)+"+L"+(totalLine-completeline+4)+"+N"+(totalLine-completeline+4)+")/P"+(totalLine-completeline+4)+"" );
		document.forms(0).Cell1.SetFormula (19, totalLine-completeline+4, pageCurrent, "(K"+(totalLine-completeline+4)+"+M"+(totalLine-completeline+4)+"+O"+(totalLine-completeline+4)+")/Q"+(totalLine-completeline+4)+"" );

		document.forms(0).Cell1.S(16,totalLine-completeline+5,pageCurrent,"操作员");
		document.forms(0).Cell1.S(17,totalLine-completeline+5,pageCurrent,"<%=userName%>");
		document.forms(0).Cell1.S(18,totalLine-completeline+5,pageCurrent,"操作时间");
		document.forms(0).Cell1.S(19,totalLine-completeline+5,pageCurrent,"<%=plbizDate%>");
		document.forms(0).Cell1.DeleteRow(totalLine-completeline+6,47-(totalLine-completeline+5),pageCurrent);
		document.forms(0).Cell1.ReDraw();
	}
	
	document.forms(0).Cell1.AllowExtend=false;
	document.forms(0).Cell1.AllowDragdrop=false;
	document.forms(0).Cell1.WorkbookReadonly=true;	
	document.forms(0).Cell1.HideFormulaErrorInfo=true;

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
